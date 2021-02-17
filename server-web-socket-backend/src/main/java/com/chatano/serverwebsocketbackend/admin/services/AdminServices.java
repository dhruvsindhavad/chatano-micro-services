package com.chatano.serverwebsocketbackend.admin.services;

import com.chatano.serverwebsocketbackend.collections.HungrySessionsList;
import com.chatano.serverwebsocketbackend.collections.LiveSessionsList;
import com.chatano.serverwebsocketbackend.collections.PairSessionsMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AdminServices {

	final static Logger logger = Logger.getLogger(AdminServices.class);
	
	@Autowired
	LiveSessionsList liveSessions;
	@Autowired
	HungrySessionsList hungrySessions;
	@Autowired
	PairSessionsMap pairSessions;

	public String getSessionInfo()
	{
		logger.info("Inside getSessionInfo");
		long liveSessionCount=liveSessions.getLiveSessionsList().size();;
		long hungrySessionCount=hungrySessions.getHungrySessionsList().size();
		long pairSessionsCount=(pairSessions.getPairSessionsMap().size())/2;
		return "Live Session count = " + liveSessionCount + "\n Hungry Session count = " + hungrySessionCount + "\n Pair Session count = " +  pairSessionsCount ;
	}
}
