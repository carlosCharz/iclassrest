package com.wedevol.iclass.core.util;

import java.util.Optional;

import org.apache.commons.io.FilenameUtils;

/**
 * File Util Class
 * 
 * @author charz
 * 
 */
public class FileUtil {

	public static final char DIRECTORY_SEPARATOR = '/';
	public static final char EXTENSION_SEPARATOR = '.';
	public static final String ASPECT_RATIO_HEADER_NAME = "aspect-ratio";
	public static final String DIRECTORY_PICTURES = "picture";
	
	/**
	 * 
	 * @param prefix
	 * @param userTypeDirectory
	 * @param userId
	 * @param mediaTypeDirectory
	 * @param origFileName
	 * @return prefix/userTypeDirectory/userId/mediaTypeDirectory/origFileName
	 */
	public static String generateFileId(String prefix, String userTypeDirectory, Long userId, String mediaTypeDirectory, String origFileName) {
		StringBuilder fileId = new StringBuilder(prefix);
		Optional.ofNullable(userTypeDirectory).ifPresent(directoryName -> fileId.append(directoryName).append(DIRECTORY_SEPARATOR));
		Optional.ofNullable(userId).ifPresent(id -> fileId.append(id).append(DIRECTORY_SEPARATOR));
		Optional.ofNullable(mediaTypeDirectory).ifPresent(directoryName -> fileId.append(directoryName)
				.append(DIRECTORY_SEPARATOR));
		fileId.append(generateUniqueFileName(origFileName));
		return fileId.toString();
	}

	public static String generateUniqueFileName(String origFileName) {
		String uuid = CryptoUtil.getRandomBasedUUIDString();
		String extension = FilenameUtils.getExtension(origFileName);
		return new StringBuilder().append(uuid).append(EXTENSION_SEPARATOR).append(extension).toString();
	}

}
