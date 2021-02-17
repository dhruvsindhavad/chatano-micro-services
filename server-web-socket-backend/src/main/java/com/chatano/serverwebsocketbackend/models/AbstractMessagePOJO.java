package com.chatano.serverwebsocketbackend.models;

public abstract class AbstractMessagePOJO {

	private String from;
	private String message;
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
