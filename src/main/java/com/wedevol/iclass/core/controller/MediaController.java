package com.wedevol.iclass.core.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wedevol.iclass.core.entity.Admin;
import com.wedevol.iclass.core.service.AdminService;

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
	private AdminService adminService;

	@RequestMapping(value = "/picture/upload", method = RequestMethod.POST, headers = ("content-type=multipart/*"), produces = "application/json", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public void handleFileUpload(@RequestParam(value = "file", required = true) MultipartFile file) {
		String tempFileName;
		if (!file.isEmpty()) {
			tempFileName = "/Users/charz/Downloads/" + file.getOriginalFilename();
			try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tempFileName))) {
				stream.write(file.getBytes());
				stream.close();
				logger.info("You successfully uploaded " + file.getOriginalFilename() + "!");
			} catch (Exception e) {
				logger.info("You failed to upload " + e.getMessage());
			}
		} else {
			logger.info("You failed to upload because the file was empty.");
		}
	}

}
