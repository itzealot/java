package com.zt.hello.sei.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.zt.hello.sei.IHello;

/**
 * Զ�̵Ľӿڵ�ʵ��
 */
public class HelloImpl extends UnicastRemoteObject implements IHello {
	private static final long serialVersionUID = 1409253392282794246L;

	/**
	 * ��ΪUnicastRemoteObject�Ĺ��췽���׳���RemoteException�쳣���������Ĭ�ϵĹ��췽������д��
	 * ���������׳�RemoteException�쳣
	 * 
	 * @throws RemoteException
	 */
	public HelloImpl() throws RemoteException {
	}

	/**
	 * �򵥵ķ��ء�Hello World��"����
	 * 
	 * @return ���ء�Hello World��"����
	 * @throws java.rmi.RemoteException
	 */
	public String helloWorld() throws RemoteException {
		return "Hello World!";
	}

	/**
	 * һ���򵥵�ҵ�񷽷������ݴ��������������Ӧ���ʺ���
	 * 
	 * @param someBodyName
	 *            ����
	 * @return ������Ӧ���ʺ���
	 * @throws java.rmi.RemoteException
	 */
	public String sayHelloToSomeBody(String someBodyName)
			throws RemoteException {
		return "��ã�" + someBodyName + "!";
	}
}