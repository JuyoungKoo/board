package com.example.board.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfiguration {
	
	@Bean
	public MessageSource messageSource() {
		
		ReloadableResourceBundleMessageSource messageSource 
		= new ReloadableResourceBundleMessageSource();
	
		// 메세지 프로퍼티파일의 위치와 이름을 지정
		messageSource.setBasename("classpath:/messages/messages");
		
		// 기본 인코딩 지정
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;	
		
	}
	
	@Bean
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(messageSource());
	}
	
	

}
