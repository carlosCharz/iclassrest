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

	public static final String DIRECTORY_SEPARATOR = "/";
	public static final String EXTENSION_SEPARATOR = ".";
	public static final String ASPECT_RATIO_HEADER_NAME = "aspect-ratio";
	public static final String DIRECTORY_PICTURE = "picture";
	public static final String DIRECTORY_FILE = "file";
	public static final String DIRECTORY_COURSE = "course";

	/**
	 * 
	 * @param prefix
	 * @param directory
	 * @param origFileName
	 * @return prefix/directory/origFileName
	 */
	public static String generateBasicFileId(String prefix, String directory, String origFileName) {
		StringBuilder fileId = new StringBuilder(prefix);
		Optional.ofNullable(directory).ifPresent(
				directoryName -> fileId.append(directoryName).append(DIRECTORY_SEPARATOR));
		fileId.append(generateUniqueFileName(origFileName));
		return fileId.toString();
	}

	public static String generateUniqueFileName(String origFileName) {
		String uuid = CryptoUtil.getRandomBasedUUIDString();
		String extension = FilenameUtils.getExtension(origFileName);
		return new StringBuilder().append(uuid).append(EXTENSION_SEPARATOR).append(extension).toString();
	}

}
