package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.FileUtil.DIRECTORY_FILE;
import static com.wedevol.iclass.core.util.FileUtil.DIRECTORY_PICTURES;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wedevol.iclass.core.amazon.AmazonS3Service;
import com.wedevol.iclass.core.amazon.MediaFile;
import com.wedevol.iclass.core.amazon.PictureFile;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.enums.UserType;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.MediaService;
import com.wedevol.iclass.core.service.StudentService;

/**
 * Media Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class MediaServiceImpl implements MediaService {

	protected static final Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);

	@Autowired
	private AmazonS3Service amazonS3Service;

	@Autowired
	private StudentService studentService;

	@Autowired
	private InstructorService instructorService;

	@Override
	public String uploadUserPicture(Long userId, UserType userType, MediaFile file) {
		final String url = uploadPicture(userId, userType, file);
		asocciatePictureToProfile(userId, userType, url);
		return url;
	}

	private String uploadPicture(Long userId, UserType userType, MediaFile file) {
		// TODO: here we should use a library to get the metadata and validate
		final PictureFile pictureInfo = PictureFile.from(file);
		return amazonS3Service.uploadFile(userId, userType, DIRECTORY_PICTURES, pictureInfo);
	}

	@Override
	public String addUserPicture(Long userId, UserType userType, MultipartFile multipart) {
		if (!multipart.isEmpty()) {
			MediaFile mediaFile;
			try {
				mediaFile = new MediaFile(multipart.getOriginalFilename(), multipart.getContentType(),
						multipart.getSize(), multipart.getInputStream());
				return uploadUserPicture(userId, userType, mediaFile);
			} catch (IOException e) {
				// TODO: throw exception
				return null;
			}
		} else {
			logger.info("You failed to upload because the file was empty!.");
			return null;
		}
	}

	private void asocciatePictureToProfile(Long userId, UserType userType, String url) {
		if (UserType.STUDENT.equals(userType)) {
			Student newStudent = new Student();
			newStudent.setProfilePictureUrl(url);
			studentService.update(userId, newStudent);
		} else if (UserType.INSTRUCTOR.equals(userType)) {
			Instructor newInstructor = new Instructor();
			newInstructor.setProfilePictureUrl(url);
			instructorService.update(userId, newInstructor);
		}
	}

	@Override
	public String uploadAnyFile(MultipartFile multipart) {
		if (!multipart.isEmpty()) {
			MediaFile mediaFile;
			try {
				mediaFile = new MediaFile(multipart.getOriginalFilename(), multipart.getContentType(),
						multipart.getSize(), multipart.getInputStream());
				final PictureFile pictureInfo = PictureFile.from(mediaFile);
				return amazonS3Service.uploadFile(DIRECTORY_FILE, pictureInfo);
			} catch (IOException e) {
				// TODO: throw exception
				return null;
			}
		} else {
			logger.info("You failed to upload because the file was empty!.");
			return null;
		}
	}

}
