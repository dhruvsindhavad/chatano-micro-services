package com.chatano.serverwebsocketbackend.collections;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)

public class LiveSessionsList {

	List<WebSocketSession> sessionsLive = new CopyOnWriteArrayList<>();
	
	public void addSession(WebSocketSession session)
	{
		sessionsLive.add(session);
	}
	
	public void removeSession(WebSocketSession session)
	{
		sessionsLive.remove(session);
	}
	
	public List<WebSocketSession> getLiveSessionsList()
	{
		return sessionsLive;
	}

}
