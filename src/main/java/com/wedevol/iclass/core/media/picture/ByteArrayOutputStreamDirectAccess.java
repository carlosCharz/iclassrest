package com.wedevol.iclass.core.media.picture;

import java.io.ByteArrayOutputStream;

/**
 * Same than {@link ByteArrayOutputStream} with the exception that the internal buffer can be accessed.
 * 
 */
public class ByteArrayOutputStreamDirectAccess extends ByteArrayOutputStream {

	/**
     * Creates a new byte array output stream. The buffer capacity is
     * initially set to X bytes. See {@link ByteArrayOutputStream#ByteArrayOutputStream()}.
     */
	public ByteArrayOutputStreamDirectAccess() {
		super();
	}
	
	 /**
     * Creates a new byte array output stream, with a buffer capacity of
     * the specified size, in bytes. See {@link ByteArrayOutputStream#ByteArrayOutputStream(int)}.
     *
     * @param   size   the initial size.
     * @exception  IllegalArgumentException if size is negative.
     */
    public ByteArrayOutputStreamDirectAccess(int size) {
    	super(size);
    }
    
    public byte[] getInternalBuffer() {
    	return buf;
    }
}
