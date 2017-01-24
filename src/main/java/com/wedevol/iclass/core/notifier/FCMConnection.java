package com.wedevol.iclass.core.notifier;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Sender;

/**
 * Class that gets the connection from Google API to send messages
 * 
 * @author Charz
 */
@Component
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
