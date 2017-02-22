package com.wedevol.iclass.core.amazon;

import java.io.InputStream;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.wedevol.iclass.core.entity.enums.UserType;

/**
 * Amazon S3 Service Interface
 * 
 * @author charz
 *
 */
public interface AmazonS3Service {

	InputStream getFileStream(String fileId);
	
	ObjectMetadata getObjectMetadata(String objectUrl);
	
	String uploadFile(Long userId, UserType userType, String mediaTypeDirectory, PictureFile pictureFile);
	
	String uploadFile(String directory, PictureFile pictureFile);

}
