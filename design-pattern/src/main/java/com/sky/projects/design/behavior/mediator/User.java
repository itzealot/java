package com.sky.projects.design.behavior.mediator;

/**
 * 用户实体
 * 
 * @author zealot
 */
public class User {

	private String name;

	public User(String name) {
		this.name = name;
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 */
	public void sendMessage(String message) {
		ChatRoom.showMessage(this, message);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}