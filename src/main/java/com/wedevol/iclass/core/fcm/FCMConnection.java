package com.wedevol.iclass.core.fcm;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.wedevol.iclass.core.fcm.sdk.Sender;

/**
 * Class that gets the connection from Google API to send messages
 * 
 * @author charz
 */
public class FCMConnection extends Sender {

	public FCMConnection(String key) {
		super(key);
	}

	@Override
	protected HttpURLConnection getConnection(String url) throws IOException {
		String fcmUrl = "https://fcm.googleapis.com/fcm/send";
		return (HttpURLConnection) new URL(fcmUrl).openConnection();
	}
}
