package com.zt.design.behavior.mediator;

/**
 * 
 * 
 * @author zt
 */
public class User {
	private String name;

	public User(String name) {
		this.name = name;
	}

	/**
	 * 通过中介者 ChatRoom 来传达信息
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