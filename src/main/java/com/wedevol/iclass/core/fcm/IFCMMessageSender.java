package com.wedevol.iclass.core.fcm;

import java.io.IOException;
import java.util.List;

/**
 * Message Sender Interface
 * 
 * @author charz
 *
 */
public interface IFCMMessageSender {

	/**
	 * Send a notification to a token.
	 * 
	 * @param notificationRequest
	 * @param token
	 * @throws IOException
	 */
	public void send(NotificationRequest notificationRequest, String token);

	/**
	 * Send a notification to the token list.
	 * 
	 * @param notificationRequest
	 * @param tokens
	 * @throws IOException
	 */
	public void send(NotificationRequest notificationRequest, List<String> tokens);

}
