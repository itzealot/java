package com.zt.design.behavior.mediator;

import java.util.Date;

/**
 * 创建中介类 ChatRoom, 并使用 User 对象来显示他们之间的通信
 * 
 * @author zengtao
 *
 */
public final class ChatRoom {
	private ChatRoom() {
	}

	/**
	 * 中介者传达用户 User 想要传达的信息
	 * 
	 * @param user
	 * @param message
	 */
	public static void showMessage(User user, String message) {
		System.out.println(new Date().toString() + " [" + user.getName() + "] : " + message);
	}
}