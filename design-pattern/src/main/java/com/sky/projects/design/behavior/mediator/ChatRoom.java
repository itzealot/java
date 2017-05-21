package com.sky.projects.design.behavior.mediator;

import java.util.Date;

/**
 * 中介类(通过中间者传递信息)
 * 
 * @author zealot
 *
 */
public final class ChatRoom {

	/**
	 * 中介者传达用户 User 想要传达的信息
	 * 
	 * @param user
	 * @param message
	 */
	public static void showMessage(User user, String message) {
		System.out.println(new Date().toString() + " [" + user.getName() + "] : " + message);
	}

	private ChatRoom() {
	}
}