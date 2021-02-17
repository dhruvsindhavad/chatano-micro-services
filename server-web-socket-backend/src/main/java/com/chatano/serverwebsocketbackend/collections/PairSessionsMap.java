package com.chatano.serverwebsocketbackend.collections;

import java.io.IOException;
import java.util.HashMap;

import com.chatano.serverwebsocketbackend.handlers.MessageHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PairSessionsMap {

	final static Logger logger = Logger.getLogger(PairSessionsMap.class);
	private HashMap<WebSocketSession,WebSocketSession> sessionPairManager = new HashMap<>();
	@Autowired
	private MessageHandler messageHandler;
	
	public HashMap<WebSocketSession,WebSocketSession> getPairSessionsMap()
	{
		return sessionPairManager;
	}
	
	public void addPair(WebSocketSession session1, WebSocketSession session2) 
	{
		
		sessionPairManager.put(session1, session2);
		sessionPairManager.put(session2, session1);
		messageHandler.notifyPartiesForConnection(session1,session2);
	}
	
	public WebSocketSession removePair(WebSocketSession session) throws IOException
	{
		WebSocketSession hungrySession = sessionPairManager.get(session);
		messageHandler.notifyPartnerForDisconnection(hungrySession);
		sessionPairManager.remove(session);
		sessionPairManager.remove(hungrySession);
		return hungrySession;
	}
	
	
	
	
}
