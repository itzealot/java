package com.zt.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI : 远程方法调用，分布式系统技术，运行JVM之间通信.<br />
 * RMILightController extends java.rmi.Remote.<br />
 * 定义远程控制电灯泡的服务接口.<br />
 * 内部所有方法必须抛出RemoteException.<br />
 * 
 * @author zengtao
 *
 */
public interface RMILightController extends Remote {
	/**
	 * 开灯.<br />
	 * To turn on the light.<br />
	 */
	public void on() throws RemoteException;

	/**
	 * 关灯.<br />
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
