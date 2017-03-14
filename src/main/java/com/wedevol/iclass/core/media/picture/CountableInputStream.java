package com.wedevol.iclass.core.media.picture;

import java.io.IOException;
import java.io.InputStream;

public class CountableInputStream extends InputStream {

	private InputStream source;
	private int count;
	
	public CountableInputStream(InputStream source) {
		this.source = source;
		count = 0;
	}
	
	@Override
	public int read() throws IOException {
		int r = source.read();
		++count;
		return r;
	}

	public int getStreamLength() {
		return count;
	}

}
