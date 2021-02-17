package com.chatano.serverwebsocketbackend.models;


import com.chatano.serverwebsocketbackend.common.Constants;

//@Component
public class SystemMessagePOJO extends AbstractMessagePOJO {
	
	public SystemMessagePOJO(String message) {
		this.setFrom(Constants.SYSTEM);
		this.setMessage(message);
	}
}
