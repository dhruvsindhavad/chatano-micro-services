package com.chatano.serverwebsocketbackend.handlers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

	final static Logger logger = Logger.getLogger(SocketHandler.class);
	@Autowired
	SessionHandler sessionHandler;
	@Autowired 
	MessageHandler messageHandler;
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			messageHandler.sendMessage(session, message);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		logger.info("New session arrived user = " + session.getLocalAddress() + " and id = " + session.getId() );
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionHandler.afterConnectionClosed(session);
		logger.info("Connection closed for user = " + session.getLocalAddress() + " and id = " + session.getId() + " status : " + status );
		super.afterConnectionClosed(session, status);
	}
}
