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

	//ע�����
	@Autowired
	private Queue queue;
	
	//ע���װ��
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
	
	//����map����
	@RequestMapping("/sendmap")
	public String sendmap(String name){
		
		manageServe.mapmanage(name);
		return "success";
	}
	
	
	//���Ͷ�������
	@RequestMapping("/sendobject")
	public String sendobject(String name){
		User user= new User("����",12);
		manageServe.objectmanage(name, user);
		return "success";
	}
	
	//�����ֽ�����
		@RequestMapping("/sendbytemessage")
		public String sendbytemessage(String name){
		 long startTime=System.currentTimeMillis(); 
		 manageServe.bytemessage(name);
		 long  end=	System.currentTimeMillis();	
		 
		 long alltime = end-startTime;
		 System.out.println("���͹�����ʱ��"+alltime);
		
			return "success";
		}
		
		
		//����stream����
		@RequestMapping("/sendstream")
		public String sendstream(String name){
			manageServe.streammanage(name);
			return "success";
		}
}
