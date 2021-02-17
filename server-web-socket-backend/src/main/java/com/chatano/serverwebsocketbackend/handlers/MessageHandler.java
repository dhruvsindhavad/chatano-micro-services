package com.chatano.serverwebsocketbackend.handlers;

import java.io.IOException;

import com.chatano.serverwebsocketbackend.common.Constants;
import com.chatano.serverwebsocketbackend.models.AbstractMessagePOJO;
import com.chatano.serverwebsocketbackend.models.SystemMessagePOJO;
import com.chatano.serverwebsocketbackend.models.UserMessagePOJO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import com.google.gson.Gson;
@Component
public class MessageHandler {

	@Autowired
	SessionHandler sessionHandler;
	@Autowired
	Gson gson;
	final static Logger logger = Logger.getLogger(MessageHandler.class);

	public void sendMessage(WebSocketSession session, TextMessage message) {
		// received Message from page
		handleMessage(session, message);
	}

	public void handleMessage(WebSocketSession session, TextMessage message) {
		String msg = message.getPayload().toString();
		if (msg.contains(Constants.CLIENT_TICKET_MESSAGE)) {
			handleTicketFromClient(session, (msg.split("-"))[1]);
		} else {
			handleUserMessage(session, msg);
		}
	}

	public void handleUserMessage(WebSocketSession session, String msg) {
		AbstractMessagePOJO messagePOJO = new UserMessagePOJO(msg);
		WebSocketSession partnerSession = sessionHandler.getPairSessions().getPairSessionsMap().get(session);
		if (null != partnerSession && partnerSession.isOpen() == true) {
			try {
				partnerSession.sendMessage(new TextMessage(gson.toJson(messagePOJO)));
			} catch (IOException e) {
				logger.error("Sending message failed for user = " + partnerSession.getRemoteAddress() + "id = " + partnerSession.getId() + " "  + e);
			}
		}
	}

	public void handleTicketFromClient(WebSocketSession session, String ticket) {
		if (sessionHandler.authenticateSession(ticket)) {
			notifyAuthenticationResult(session, Constants.AUTHENTICCATION_SUCCESSFUL);
			sessionHandler.afterConnectionEstablished(session);
		} else {
			logger.info("Authentication failed for user = " + session.getRemoteAddress() + " id = " + session.getId());
			notifyAuthenticationResult(session, Constants.AUTHENTICATION_UNSUCCESSFUL);
		}
	}

	public void notifyAuthenticationResult(WebSocketSession session, String result) {

		if (null != session && session.isOpen() == true) {
			try {
				session.sendMessage(new TextMessage(gson.toJson(new SystemMessagePOJO(result))));
			} catch (IOException e) {
				logger.error("Sending message failed for user = " + session.getRemoteAddress() + "id = " + session.getId() + " "  + e);
			}
		}
	}

	public void notifyPartiesForConnection(WebSocketSession session1, WebSocketSession session2) {

		if (null != session1 && session1.isOpen() == true) {
			try {
				session1.sendMessage(new TextMessage(gson.toJson(new SystemMessagePOJO(Constants.PARTNER_CONNECTION))));
			} catch (IOException e) {
				logger.error("Sending message failed for user = " + session1.getRemoteAddress() + "id = " + session1.getId() + " "  + e);
			}
		}
		if (null != session2 && session2.isOpen() == true) {
			try {
				session2.sendMessage(new TextMessage(gson.toJson(new SystemMessagePOJO(Constants.PARTNER_CONNECTION))));
			} catch (IOException e) {
				logger.error("Sending message failed for user = " + session2.getRemoteAddress() + "id = " + session2.getId() + " "  + e);
			}
		}
	}

	public void notifyPartnerForDisconnection(WebSocketSession partnerSession) {
		if (null != partnerSession && partnerSession.isOpen() == true) {
			try {
				partnerSession.sendMessage(
						new TextMessage(gson.toJson(new SystemMessagePOJO(Constants.PARTNER_DISCONNECTION))));
			} catch (IOException e) {
				logger.error("Sending message failed for user = " + partnerSession.getRemoteAddress() + "id = " + partnerSession.getId() + " "  + e);
			}
		}
	}
}
