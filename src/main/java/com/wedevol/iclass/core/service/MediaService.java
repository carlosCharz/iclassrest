package com.wedevol.iclass.core.service;

import org.springframework.web.multipart.MultipartFile;

import com.wedevol.iclass.core.amazon.MediaFile;
import com.wedevol.iclass.core.entity.enums.UserType;


/**
 * Media Service Interface
 * 
 * @author charz
 *
 */
public interface MediaService {
	
	String uploadPicture(Long userId, UserType userType, MediaFile file);
	
	String addPicture(Long userId, UserType userType, MultipartFile multipart);

}
