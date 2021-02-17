package com.chatano.serverwebsocketbackend.models;

import com.chatano.serverwebsocketbackend.common.Constants;
import org.springframework.stereotype.Component;


//@Component
public class UserMessagePOJO extends AbstractMessagePOJO {

	public UserMessagePOJO(String message) {
		this.setFrom(Constants.USER);
		this.setMessage(message);
	}
}
