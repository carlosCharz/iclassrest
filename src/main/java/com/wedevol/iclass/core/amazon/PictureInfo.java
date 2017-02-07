package com.wedevol.iclass.core.amazon;

import java.io.InputStream;
import java.util.Optional;

/**
 * Picture Info Entity
 * 
 * @author Charz++
 */
public class PictureInfo {

	private String fileName;
	private String format;
	private Long size;
	private InputStream inputStream;
	private FileMetadata metadata;
	private String height;
	private String width;

	public PictureInfo(String fileName, String format, Long size, InputStream inputStream, String height, String width,
			FileMetadata metadata) {
		this(fileName, format, size, inputStream, metadata);
		this.height = height;
		this.width = width;
	}

	public PictureInfo(String fileName, String format, Long size, InputStream inputStream, FileMetadata metadata) {
		this.fileName = fileName;
		this.format = format;
		this.size = size;
		this.inputStream = inputStream;
		this.metadata = metadata;
	}

	public PictureInfo(String fileName, String format, Long size, InputStream inputStream) {
		this.fileName = fileName;
		this.format = format;
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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
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

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
}
