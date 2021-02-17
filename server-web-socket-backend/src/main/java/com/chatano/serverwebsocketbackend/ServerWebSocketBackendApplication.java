package com.chatano.serverwebsocketbackend;

import com.chatano.serverwebsocketbackend.utilities.MatchMakerUtilityThread;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class ServerWebSocketBackendApplication {

	@Bean
	public Gson getGson()
	{
		return new Gson();
	}
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	@Bean
	public MatchMakerUtilityThread getMatchMakerUtilityThread()
	{
		return  new MatchMakerUtilityThread();
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerWebSocketBackendApplication.class, args);
	}

}
