package com.wedevol.iclass.core.amazon;

import static com.wedevol.iclass.core.util.FileUtil.ASPECT_RATIO_HEADER_NAME;
import static com.wedevol.iclass.core.util.FileUtil.generateFileId;

import java.io.InputStream;
import java.util.Optional;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

/**
 * Amazon S3 Service Implementation
 * 
 * @author Charz++
 */
public class AmazonS3ServiceImpl {

    private AmazonS3Client client;
    private String bucket;
    private String prefix;
    
    public AmazonS3ServiceImpl(String accessKey, String secretKey, String bucket, String prefix) {
        this.client = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
        this.bucket = bucket;
        this.prefix = prefix;
    }

	public InputStream getFileStream(String fileId) {
		S3Object object;
		try {
			object = client.getObject(bucket, fileId);
		} catch (AmazonClientException e) {
			return null;
		}
		return object.getObjectContent();
	}
    
	public ObjectMetadata getObjectMetadata(String objectUrl) {
		try {
			return client.getObjectMetadata(bucket, objectUrl);
		} catch (AmazonClientException e) {
			e.printStackTrace();
			return null;
		}
	}
	
    public String uploadFile(Long uploaderId, String directory, PictureInfo pictureInfo) {
        String fileId = generateFileId(prefix, uploaderId, directory, pictureInfo.getFileName());
        return uploadFile(fileId, pictureInfo.getInputStream(), pictureInfo.getFormat(), pictureInfo.getSize(), 
        		pictureInfo.getHeight(), pictureInfo.getWidth(), pictureInfo.getMetadata());
    }

	private ObjectMetadata buildObjectMetadata(String contentType, Long contentLength, FileMetadata metadata) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		Optional.ofNullable(contentType).ifPresent(objectMetadata::setContentType);
		Optional.ofNullable(contentLength).ifPresent(objectMetadata::setContentLength);
		Optional.ofNullable(metadata).ifPresent(m -> m.forEach(objectMetadata::addUserMetadata));
		return objectMetadata;
	}
		
	private ObjectMetadata buildObjectMetadata(String contentType, Long contentLength, String height, String width, 
			FileMetadata metadata) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		Optional.ofNullable(contentType).ifPresent(objectMetadata::setContentType);
		Optional.ofNullable(contentLength).ifPresent(objectMetadata::setContentLength);
		Optional.ofNullable(height).map(Float::parseFloat).ifPresent(h -> {
			Optional.ofNullable(width).map(Float::parseFloat).map(w -> (h / w)).ifPresent(aspectRatio -> {
				objectMetadata.addUserMetadata(ASPECT_RATIO_HEADER_NAME, aspectRatio.toString());
			});
		});
		Optional.ofNullable(metadata).ifPresent(m -> m.forEach(objectMetadata::addUserMetadata));
		return objectMetadata;
	}
	
	private String uploadFile(String fileId, InputStream inputStream, String contentType, Long contentLength,
			String height, String width, FileMetadata metadata)
	{
		ObjectMetadata objectMetadata = buildObjectMetadata(contentType, contentLength, height, width, metadata);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileId, inputStream, objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		client.putObject(putObjectRequest);
		return fileId;
	}

}
