package com.wedevol.iclass.core.amazon;

import java.io.InputStream;

/**
 * Basic File Class
 * 
 * @author charz
 * 
 */
public class BasicFile  {

	protected static final long serialVersionUID = 8457266600876524592L;

	private String fileName;
	private String contentType;
	private Long size;
	private InputStream inputStream;

	public BasicFile(String fileName, String contentType, Long size, InputStream inputStream) {
		this.fileName = fileName;
		this.contentType = contentType;
		this.size = size;
		this.inputStream = inputStream;
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

}
