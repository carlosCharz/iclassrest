package com.wedevol.iclass.core.notifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Notification message content
 * 
 * @author charz
 */
public class NotificationMessageContent {

	private String messageBase;
	private List<String> variables;
	private String content;

	public NotificationMessageContent() {
		this.messageBase = "";
		this.variables = new ArrayList<>();
		this.content = null;
	}

	public NotificationMessageContent(String messageBase) {
		this.messageBase = messageBase;
		this.variables = new ArrayList<>();
		this.content = null;
	}

	public String getMessageBase() {
		return messageBase;
	}

	public void setMessageBase(String messageBase) {
		this.messageBase = messageBase;
	}

	public List<String> getVariables() {
		return variables;
	}

	public void addVariable(Object variable) {
		this.variables.add(variable.toString());
	}

	public void setVariables(List<String> variables) {
		this.variables = variables;
	}

	public String build() {
		if (this.content == null) {
			this.content = String.format(this.messageBase, this.variables.toArray());
		}
		return content;
	}

}
