package com.chatano.webbasedgui;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WebBasedGuiApplication {

	@Bean
	@LoadBalanced
	RestTemplate getRestRemplate()
	{
		return  new RestTemplate();
	}

	@Bean
	public FilterRegistrationBean someFilterRegistration() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(siteMeshFilter());
		registration.addUrlPatterns("/*");
		registration.setName("siteMeshFilter");
		registration.setOrder(1);
		return registration;
	}

	public Filter siteMeshFilter() {
		return new ConfigurableSiteMeshFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(WebBasedGuiApplication.class, args);
	}

}
