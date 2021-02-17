/**
 * 
 */
package com.chatano.serverwebsocketbackend.utilities;

import com.chatano.serverwebsocketbackend.collections.HungrySessionsList;
import com.chatano.serverwebsocketbackend.collections.PairSessionsMap;
import com.chatano.serverwebsocketbackend.common.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
/**
 * @author dhruvkumar
 *
 */

@Component
@Scope("prototype")
public class MatchMakerUtilityThread implements Runnable {

	final static Logger logger = Logger.getLogger(MatchMakerUtilityThread.class);
	@Autowired
	private PairSessionsMap pairSessions;
	@Autowired
	private HungrySessionsList hungrySessions;
	private WebSocketSession currentSession;

	public MatchMakerUtilityThread() {
	}


	public void setCurrentSession(WebSocketSession session) {
		this.currentSession = session;
	}

	public WebSocketSession getHungrySession() {
		return currentSession;
	}

	@Override
	public void run() {
		while (!match())
			;

	}

	boolean match() {
		if (hungrySessions.getHungrySessionsList().size() > 0) {
			for (WebSocketSession availableSession : hungrySessions.getHungrySessionsList()) {
				if (availableSession != currentSession && availableSession.isOpen() && currentSession.isOpen()) {
					hungrySessions.getHungrySessionsList().remove(currentSession);
					hungrySessions.getHungrySessionsList().remove(availableSession);
					pairSessions.addPair(currentSession, availableSession);
					logger.info("Session paired. Session1 user = " + currentSession.getRemoteAddress() + " id = "
							+ currentSession.getId() + " with Session2 user = " + availableSession.getRemoteAddress()
							+ " id = " + availableSession.getId());
					logger.info("Session paired. Session1 user = " + currentSession.getLocalAddress() + " id = "
							+ currentSession.getId() + " with Session2 user = " + availableSession.getLocalAddress()
							+ " id = " + availableSession.getId());
					return true;
				}
			}
		}
		if (pairSessions.getPairSessionsMap().containsKey(currentSession)) {
			return true;
		}

		if (currentSession.isOpen() == false) {
			return true;
		}
		try {
			Thread.sleep(Constants.WAIT_FOR_PAIRING);
		} catch (InterruptedException e) {
			logger.error("Inside match Something went wrong while puting thread on sleep : " + e);
		}
		return false;
	}
}
