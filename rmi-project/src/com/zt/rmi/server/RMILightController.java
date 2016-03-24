package com.zt.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI : Զ�̷������ã��ֲ�ʽϵͳ����������JVM֮��ͨ��.<br />
 * RMILightController extends java.rmi.Remote.<br />
 * ����Զ�̿��Ƶ���ݵķ���ӿ�.<br />
 * �ڲ����з��������׳�RemoteException.<br />
 * 
 * @author zengtao
 *
 */
public interface RMILightController extends Remote {
	/**
	 * ����.<br />
	 * To turn on the light.<br />
	 */
	public void on() throws RemoteException;

	/**
	 * �ص�.<br />
	 * To turn off the light.<br />
	 */
	public void off() throws RemoteException;

	/**
	 * The light's state. If it's on then return true;else return false.
	 * 
	 * @return true or false.
	 */
	public boolean isOn() throws RemoteException;
}
