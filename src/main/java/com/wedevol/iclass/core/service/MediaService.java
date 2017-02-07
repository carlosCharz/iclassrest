package com.wedevol.iclass.core.service;

import com.wedevol.iclass.core.amazon.MediaFile;
import com.wedevol.iclass.core.entity.enums.UserType;


/**
 * Media Service Interface
 * 
 * @author charz
 *
 */
public interface MediaService {
	
	String addPicture(Long userId, UserType userType, MediaFile file);

}
