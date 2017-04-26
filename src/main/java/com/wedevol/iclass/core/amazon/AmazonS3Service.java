package com.wedevol.iclass.core.amazon;

import java.io.InputStream;

import com.amazonaws.services.s3.model.ObjectMetadata;

/**
 * Amazon S3 Service Interface
 * 
 * @author charz
 *
 */
public interface AmazonS3Service {

	InputStream getFileStream(String fileId);
	
	ObjectMetadata getObjectMetadata(String objectUrl);
	
	String uploadFile(String directory, MediaFile mediaFile);
	
	String uploadFile(MediaFile pictureFile);
	
	String uploadFileWithOriginalFileName(String directory, MediaFile pictureFile);

}
