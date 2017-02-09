package com.wedevol.iclass.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wedevol.iclass.core.entity.enums.UserType;
import com.wedevol.iclass.core.service.MediaService;

/**
 * Media Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/media")
public class MediaController {

	protected static final Logger logger = LoggerFactory.getLogger(MediaController.class);

	@Autowired
	private MediaService mediaService;

	@RequestMapping(value = "/students/{userId}/picture/upload", method = RequestMethod.POST, headers = ("content-type=multipart/*"), consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public String uploadPictureStudent(@PathVariable Long userId,
			@RequestParam(value = "file", required = true) MultipartFile file) {
		final String url = mediaService.addUserPicture(userId, UserType.STUDENT, file);
		return url;
	}
	
	@RequestMapping(value = "/instructors/{userId}/picture/upload", method = RequestMethod.POST, headers = ("content-type=multipart/*"), consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public String uploadPictureInstructor(@PathVariable Long userId,
			@RequestParam(value = "file", required = true) MultipartFile file) {
		final String url = mediaService.addUserPicture(userId, UserType.INSTRUCTOR, file);
		return url;
	}

}
