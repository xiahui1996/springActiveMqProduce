package com.example.demo.controll;

import java.sql.Time;
import java.util.Date;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.ManageServe;

@RestController
public class ProduceControl {

	//注入队列
	@Autowired
	private Queue queue;
	
	//注入封装类
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate; 
	
	@Autowired
	private ManageServe manageServe;
	
	
	@RequestMapping("/send")
	public String send(String name){
		
		
		jmsMessagingTemplate.convertAndSend(queue,name);
		return name;
		
	}
	@RequestMapping("/send2")
	public String send2(String name){
		
		manageServe.sendmanage(name);
		return "success";
	}
	
	//发送map类型
	@RequestMapping("/sendmap")
	public String sendmap(String name){
		
		manageServe.mapmanage(name);
		return "success";
	}
	
	
	//发送对象类型
	@RequestMapping("/sendobject")
	public String sendobject(String name){
		User user= new User("张三",12);
		manageServe.objectmanage(name, user);
		return "success";
	}
	
	//发送字节类型
		@RequestMapping("/sendbytemessage")
		public String sendbytemessage(String name){
		 long startTime=System.currentTimeMillis(); 
		 manageServe.bytemessage(name);
		 long  end=	System.currentTimeMillis();	
		 
		 long alltime = end-startTime;
		 System.out.println("发送共花费时间"+alltime);
		
			return "success";
		}
		
		
		//发送stream类型
		@RequestMapping("/sendstream")
		public String sendstream(String name){
			manageServe.streammanage(name);
			return "success";
		}
}
