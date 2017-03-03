package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.FileUtil.DIRECTORY_FILE;
import static com.wedevol.iclass.core.util.FileUtil.DIRECTORY_PICTURE;
import static com.wedevol.iclass.core.util.FileUtil.DIRECTORY_SEPARATOR;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wedevol.iclass.core.amazon.AmazonS3Service;
import com.wedevol.iclass.core.amazon.BasicFile;
import com.wedevol.iclass.core.amazon.MediaFile;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.enums.MaterialType;
import com.wedevol.iclass.core.entity.enums.UserType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.InternalServerException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.ServerErrorType;
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
	public String addUserPicture(Long userId, UserType userType, MultipartFile multipart) {
		validateMultipartFile(multipart);
		final BasicFile basicFile = getMediaFile(multipart);
		return uploadUserPicture(userId, userType, basicFile);
	}
	
	@Override
	public String uploadFile(MultipartFile multipart) {
		validateMultipartFile(multipart);
		final BasicFile basicFile = getMediaFile(multipart);
		// TODO: here we should use a library to get the metadata and validate
		final MediaFile mediaFile = MediaFile.from(basicFile);
		return amazonS3Service.uploadFile(DIRECTORY_FILE, mediaFile);
	}

	private String uploadUserPicture(Long userId, UserType userType, BasicFile basicFile) {
		// TODO: here we should use a library to get the metadata and validate
		final MediaFile mediaFile = MediaFile.from(basicFile);
		final String userTypeDirectory = userType.getDescription();
		String directory = String.join(DIRECTORY_SEPARATOR, userTypeDirectory, userId.toString(), DIRECTORY_PICTURE);
		final String url = amazonS3Service.uploadFile(directory, mediaFile);
		asocciatePictureToProfile(userId, userType, url);
		return url;
	}

	private void validateMultipartFile(MultipartFile multipart) {
		if (multipart.isEmpty()) {
			throw new BadRequestException(BadRequestErrorType.EMPTY_MULTIPART_FILE_SIZE);
		}
	}

	private BasicFile getMediaFile(MultipartFile multipart) {
		try {
			return new BasicFile(multipart.getOriginalFilename(), multipart.getContentType(), multipart.getSize(),
					multipart.getInputStream());
		} catch (IOException e) {
			throw new InternalServerException(ServerErrorType.CANNOT_GET_INPUT_STREAM);
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
	public String uploadMaterialFile(Long userId, MultipartFile multipart, MaterialType materialType) {
		// TODO: missing implementation
		return null;
	}

}
