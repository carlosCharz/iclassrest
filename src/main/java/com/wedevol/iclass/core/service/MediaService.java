package com.wedevol.iclass.core.service;

import org.springframework.web.multipart.MultipartFile;

import com.wedevol.iclass.core.amazon.MediaFile;
import com.wedevol.iclass.core.entity.enums.UserType;
import com.wedevol.iclass.core.util.DefaultInterface;


/**
 * Media Service Interface
 * 
 * @author charz
 *
 */
public interface MediaService extends DefaultInterface {
	
	String uploadUserPicture(Long userId, UserType userType, MediaFile file);
	
	String addUserPicture(Long userId, UserType userType, MultipartFile multipart);

}
