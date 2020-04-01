package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;


@Service
public class ManageServe {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate; 
	
	//注入jms模板
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
	//开启事务
	@Transactional
	public void sendmanage(String name){
		for(int i=0; i<10;i++){
			jmsMessagingTemplate.convertAndSend(name,i+"消息");
		}
	}
	
	//发送text类型的数据
	
		public void textmanage(String name){
			jmsTemplate.send(name,  new MessageCreator(){
				@Override
				public Message createMessage(Session session) throws JMSException {
			        TextMessage Message = session.createTextMessage("张三");
					return Message;
				}
			});
			
		}
	
	
	//发送map类型的数据
	public void mapmanage(String name){
		jmsTemplate.send(name,  new MessageCreator(){

			@Override
			public Message createMessage(Session session) throws JMSException {
		         MapMessage mapMessage = session.createMapMessage();
		         mapMessage.setString("name", "张三");
		         mapMessage.setInt("age", 12);
				
				return mapMessage;
			}
		});
	}
	
	//发送对象类型的数据
	
	public void objectmanage(String name,User user){
		jmsTemplate.send(name,  new MessageCreator(){

			@Override
			public Message createMessage(Session session) throws JMSException {
		         ObjectMessage Message = session.createObjectMessage(user);
				return Message;
			}
		});
		
	}
	
	//发送bytemessage流
	
	public void bytemessage(String name){
		jmsTemplate.send(name,  new MessageCreator(){

			@Override
			public Message createMessage(Session session) throws JMSException {
		         BytesMessage Message = session.createBytesMessage();
		         //1:读取文件
		         File file = new File("F:/HYLiteResources/video/test.mp4");
		         try {
					//构建输入流
		        	 FileInputStream inputStream = new FileInputStream(file);
		        	
		        	 //放入缓冲数组
		              byte[] buffer = new byte[(int) file.length()];
		        	 //写入inputStream
		              inputStream.read(buffer);
		              
		              //放入到Message中
		              Message.writeBytes(buffer);
		              
		        	 
				} catch (Exception e) {
					e.printStackTrace();
				}
		         
				return Message;
			}
		});
		
	}
	
	
	
	//发送stream类型的数据
		public void streammanage(String name){
			jmsTemplate.send(name,  new MessageCreator(){

				@Override
				public Message createMessage(Session session) throws JMSException {
			         StreamMessage Message = session.createStreamMessage();
			         Message.writeString("张三");
			         Message.writeInt(20);
					return Message;
				}
			});
			
		}
		
	
}
