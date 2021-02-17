package com.chatano.serverwebsocketbackend.utilities;

import java.util.concurrent.ThreadPoolExecutor;

import com.chatano.serverwebsocketbackend.admin.services.AdminServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ThreadPoolMonitor implements Runnable
{
	final static Logger logger = Logger.getLogger(ThreadPoolMonitor.class);
    private ThreadPoolExecutor executor;
    @Autowired
    private static AdminServices adminservices;
    private int seconds;
    private boolean run=true;

    public ThreadPoolMonitor(ThreadPoolExecutor executor, int delay)
    {
        this.executor = executor;
        this.seconds=delay;
    }
    
	public void shutdown(){
        this.run=false;
    }
    @Override
    public void run()
    {
        while(run){
        		logger.info("Session info : " + adminservices.getSessionInfo());
                logger.info(
                    String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                        this.executor.getPoolSize(),
                        this.executor.getCorePoolSize(),
                        this.executor.getActiveCount(),
                        this.executor.getCompletedTaskCount(),
                        this.executor.getTaskCount(),
                        this.executor.isShutdown(),
                        this.executor.isTerminated()));
                try {
                    Thread.sleep(seconds*1000);
                } catch (InterruptedException e) {
                	 logger.error("failed while puting thread to sleep" + e);
                }
        }
            
    }
}