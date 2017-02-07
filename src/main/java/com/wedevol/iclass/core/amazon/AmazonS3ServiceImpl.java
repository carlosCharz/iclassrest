package com.wedevol.iclass.core.amazon;

import static com.wedevol.iclass.core.util.FileUtil.generateFileId;

import java.io.InputStream;
import java.util.Optional;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.wedevol.iclass.core.entity.enums.UserType;

/**
 * Amazon S3 Service Implementation
 * 
 * @author Charz++
 */
public class AmazonS3ServiceImpl implements AmazonS3Service {

	private AmazonS3 client;
	private String bucket;
	private String prefix;

	public AmazonS3ServiceImpl(String accessKey, String secretKey, String bucket, String prefix) {
		this.bucket = bucket;
		this.prefix = prefix;
		this.client = AmazonS3ClientBuilder.standard()
											.withCredentials(
													new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey,
															secretKey)))
											.build();
	}

	@Override
	public InputStream getFileStream(String fileId) {
		S3Object object;
		try {
			object = client.getObject(bucket, fileId);
		} catch (AmazonClientException e) {
			return null;
		}
		return object.getObjectContent();
	}

	@Override
	public ObjectMetadata getObjectMetadata(String objectUrl) {
		try {
			return client.getObjectMetadata(bucket, objectUrl);
		} catch (AmazonClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String uploadFile(Long userId, UserType userType, String mediaTypeDirectory, PictureFile pictureFile) {
		final String userTypeDirectory = userType.getDescription();
		String fileId = generateFileId(prefix, userTypeDirectory, userId, mediaTypeDirectory, pictureFile.getFileName());
		return uploadFile(fileId, pictureFile.getInputStream(), pictureFile.getContentType(), pictureFile.getSize(),
				pictureFile.getMetadata());
	}

	private String uploadFile(String fileId, InputStream inputStream, String contentType, Long contentLength,
			FileMetadata metadata) {
		ObjectMetadata objectMetadata = buildMetadataObject(contentType, contentLength, metadata);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileId, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);
		client.putObject(putObjectRequest);
		return fileId;
	}

	private ObjectMetadata buildMetadataObject(String contentType, Long contentLength, FileMetadata metadata) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		Optional.ofNullable(contentType)
				.ifPresent(objectMetadata::setContentType);
		Optional.ofNullable(contentLength)
				.ifPresent(objectMetadata::setContentLength);
		Optional.ofNullable(metadata)
				.ifPresent(m -> m.forEach(objectMetadata::addUserMetadata));
		return objectMetadata;
	}

}
