package com.example.demo.config;



import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BeanConfig {

	//定义消息队列这是一个测试
	@Bean
	public Queue queue(){
		
		return new ActiveMQQueue("ActiveMQQueue");
	}
	
	
	//开启事务管理器
	@Bean
	public PlatformTransactionManager  createTransactionManager(ConnectionFactory connectionFactory){
		
		return new JmsTransactionManager(connectionFactory);
	}
	
}
