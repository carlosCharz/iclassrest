package com.wedevol.iclass.core.view.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * FCM Token view
 * 
 * @author charz
 *
 */
public class FCMTokenView implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 2, max = 300, message = "FCM token must be between 2 - 300 characters")
	private String fcmToken;
	
	@NotNull
	@Size(min = 2, max = 300, message = "Device id must be between 2 - 300 characters")
	private String deviceId;

	protected FCMTokenView() {
	}

	public FCMTokenView(String fcmToken, String deviceId) {
		super();
		this.fcmToken = fcmToken;
		this.deviceId = deviceId;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
