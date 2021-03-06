package com.wedevol.iclass.core.amazon;

import java.io.InputStream;
import java.util.Optional;

/**
 * Media File Entity
 * 
 * @author Charz++
 */
public class MediaFile {

	private String fileName;
	private String contentType;
	private Long size;
	private InputStream inputStream;
	private FileMetadata metadata;

	public static MediaFile from(BasicFile basicFile) {
		return new MediaFile(basicFile.getFileName(), basicFile.getContentType(), basicFile.getSize(), basicFile.getInputStream());
	}

	public MediaFile(String fileName, String contentType, Long size, InputStream inputStream, FileMetadata metadata) {
		this(fileName, contentType, size, inputStream);
		this.metadata = metadata;
	}

	public MediaFile(String fileName, String contentType, Long size, InputStream inputStream) {
		this.fileName = fileName;
		this.contentType = contentType;
		this.size = size;
		this.inputStream = inputStream;
	}

	public void addMetadata(FileMetadata metadata) {
		Optional.ofNullable(this.metadata)
				.orElseGet(FileMetadata::new)
				.addAll(metadata);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public FileMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(FileMetadata metadata) {
		this.metadata = metadata;
	}
}
