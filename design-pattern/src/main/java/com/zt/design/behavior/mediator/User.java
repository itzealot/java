package com.zt.design.behavior.mediator;

public class User {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(String name) {
		this.name = name;
	}

	/**
	 * 通过ChatRoom 的静态方法来显示信息
	 * 
	 * @param message
	 */
	public void sendMessage(String message) {
		ChatRoom.showMessage(this, message);
	}
}