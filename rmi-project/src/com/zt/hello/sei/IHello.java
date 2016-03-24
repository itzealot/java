package com.zt.hello.sei;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ����һ��Զ�̽ӿڣ�����̳�Remote�ӿڣ�������ҪԶ�̵��õķ��������׳�RemoteException�쳣
 */
public interface IHello extends Remote {

	/**
	 * �򵥵ķ��ء�Hello World��"����
	 * 
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public String helloWorld() throws RemoteException;

	/**
	 * һ���򵥵�ҵ�񷽷������ݴ��������������Ӧ���ʺ���
	 * 
	 * @param someBodyName
	 *            ����
	 * @return ������Ӧ���ʺ���
	 * @throws java.rmi.RemoteException
	 */
	public String sayHelloToSomeBody(String someBodyName)
			throws RemoteException;
}
