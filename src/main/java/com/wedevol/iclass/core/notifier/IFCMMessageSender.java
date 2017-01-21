package com.wedevol.iclass.core.notifier;

import java.io.IOException;
import java.util.List;

/**
 * Message Sender Interface
 * 
 * @author Charz
 *
 */
public interface IFCMMessageSender {

	/**
	 * Send a notification to a token.
	 * 
	 * @param notification
	 *            request
	 * @param token
	 * @throws IOException
	 */
	public void send(NotificationRequest notificationRequest, String token) throws IOException;

	/**
	 * Send a notification to the token list.
	 * 
	 * @param notification
	 *            request
	 * @param tokens
	 * @throws IOException
	 */
	public void send(NotificationRequest notificationRequest, List<String> tokens) throws IOException;

}
