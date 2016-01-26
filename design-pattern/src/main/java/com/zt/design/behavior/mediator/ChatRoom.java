package com.zt.design.behavior.mediator;

import java.util.Date;

/**
 * 创建中介类ChatRoom, 使用 User 对象来显示他们之间的通信
 * 
 * @author zengtao
 *
 */
public class ChatRoom {
	public static void showMessage(User user, String message) {
		System.out.println(new Date().toString() + " [" + user.getName()
				+ "] : " + message);
	}
}