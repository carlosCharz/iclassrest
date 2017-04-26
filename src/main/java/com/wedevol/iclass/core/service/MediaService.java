package com.wedevol.iclass.core.service;

import org.springframework.web.multipart.MultipartFile;

import com.wedevol.iclass.core.entity.enums.MaterialType;
import com.wedevol.iclass.core.entity.enums.UserType;
import com.wedevol.iclass.core.util.DefaultInterface;

/**
 * Media Service Interface
 * 
 * @author charz
 *
 */
public interface MediaService extends DefaultInterface {

	String addUserPicture(Long userId, UserType userType, MultipartFile multipart);

	String uploadFile(MultipartFile multipart);

	String uploadMaterialFile(Long userId, MultipartFile multipart, MaterialType materialType);
	
	String resizePicture(String pictureUrl, int maxWidth);

}
