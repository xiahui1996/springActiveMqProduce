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
	
	//ע��jmsģ��
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
	//��������
	@Transactional
	public void sendmanage(String name){
		for(int i=0; i<10;i++){
			jmsMessagingTemplate.convertAndSend(name,i+"��Ϣ");
		}
	}
	
	//����text���͵�����
	
		public void textmanage(String name){
			jmsTemplate.send(name,  new MessageCreator(){
				@Override
				public Message createMessage(Session session) throws JMSException {
			        TextMessage Message = session.createTextMessage("����");
					return Message;
				}
			});
			
		}
	
	
	//����map���͵�����
	public void mapmanage(String name){
		jmsTemplate.send(name,  new MessageCreator(){

			@Override
			public Message createMessage(Session session) throws JMSException {
		         MapMessage mapMessage = session.createMapMessage();
		         mapMessage.setString("name", "����");
		         mapMessage.setInt("age", 12);
				
				return mapMessage;
			}
		});
	}
	
	//���Ͷ������͵�����
	
	public void objectmanage(String name,User user){
		jmsTemplate.send(name,  new MessageCreator(){

			@Override
			public Message createMessage(Session session) throws JMSException {
		         ObjectMessage Message = session.createObjectMessage(user);
				return Message;
			}
		});
		
	}
	
	//����bytemessage��
	
	public void bytemessage(String name){
		jmsTemplate.send(name,  new MessageCreator(){

			@Override
			public Message createMessage(Session session) throws JMSException {
		         BytesMessage Message = session.createBytesMessage();
		         //1:��ȡ�ļ�
		         File file = new File("F:/HYLiteResources/video/test.mp4");
		         try {
					//����������
		        	 FileInputStream inputStream = new FileInputStream(file);
		        	
		        	 //���뻺������
		              byte[] buffer = new byte[(int) file.length()];
		        	 //д��inputStream
		              inputStream.read(buffer);
		              
		              //���뵽Message��
		              Message.writeBytes(buffer);
		              
		        	 
				} catch (Exception e) {
					e.printStackTrace();
				}
		         
				return Message;
			}
		});
		
	}
	
	
	
	//����stream���͵�����
		public void streammanage(String name){
			jmsTemplate.send(name,  new MessageCreator(){

				@Override
				public Message createMessage(Session session) throws JMSException {
			         StreamMessage Message = session.createStreamMessage();
			         Message.writeString("����");
			         Message.writeInt(20);
					return Message;
				}
			});
			
		}
		
	
}
