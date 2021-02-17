package com.chatano.serverwebsocketbackend.utilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

public class SessionUtilityCall {

	private static ExecutorService executor;

	static {
		executor = Executors.newFixedThreadPool(5);
		if (executor instanceof ThreadPoolExecutor) {
			ThreadPoolMonitor monitor = new ThreadPoolMonitor((ThreadPoolExecutor) executor, 1800);
			Thread monitorThread = new Thread(monitor);
			monitorThread.start();
		}
	}

	public static void lookForPartner(MatchMakerUtilityThread matchMakerUtilityThread,WebSocketSession session) {
		matchMakerUtilityThread.setCurrentSession(session);
		executor.execute(matchMakerUtilityThread);
	}
}
