package com.chatano.serverwebsocketbackend.handlers;

import java.io.IOException;


import com.chatano.serverwebsocketbackend.collections.HungrySessionsList;
import com.chatano.serverwebsocketbackend.collections.LiveSessionsList;
import com.chatano.serverwebsocketbackend.collections.PairSessionsMap;
import com.chatano.serverwebsocketbackend.common.Constants;
import com.chatano.serverwebsocketbackend.utilities.MatchMakerUtilityThread;
import com.chatano.serverwebsocketbackend.utilities.SessionUtilityCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SessionHandler {

	@Autowired
	LiveSessionsList liveSessions;
	@Autowired
	HungrySessionsList hungrySessions;
	@Autowired
	PairSessionsMap pairSessions;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	MatchMakerUtilityThread matchMakerUtilityThread;

	
	public boolean authenticateSession(String ticket)
	{
		return restTemplate.getForObject("http://ticket-manager/ticket/authenticate/"+ Constants.APPLICATION +"/"+ticket, Boolean.class);
		//return ticketManager.authenticate(ticket)
	}
	
	public void afterConnectionEstablished(WebSocketSession session) {
		liveSessions.addSession(session);
		hungrySessions.addSession(session);
		SessionUtilityCall.lookForPartner(matchMakerUtilityThread,session);
	}
	
	public void afterConnectionClosed(WebSocketSession session) throws IOException 
	{
		liveSessions.removeSession(session);
		hungrySessions.removeSession(session);
		pairSessions.removePair(session);
	}

	public PairSessionsMap getPairSessions() {
		return pairSessions;
	}
}
