package com.example.demo.entity;

import java.io.Serializable;

//需要实现序列化
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String name;
	
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public User() {
		super();
	}
	
	
}
