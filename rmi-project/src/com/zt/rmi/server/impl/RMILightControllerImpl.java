package com.zt.rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.zt.rmi.server.RMILightController;

/**
 * class RMILightControllerImpl extends UnicastRemoteObject.<br />
 * implements RMILightController.<br />
 * 点灯控制实现类 RMILightControllerImpl.<br />
 * 
 * @author zengtao
 *
 */
public class RMILightControllerImpl extends UnicastRemoteObject implements
		RMILightController {

	private static final long serialVersionUID = -1271737012713843162L;

	/**
	 * The light is on or off flag. <br />
	 * The default value is false. <br />
	 */
	private boolean lightOn = false;

	public RMILightControllerImpl() throws RemoteException {
		super();
	}

	public void on() throws RemoteException {
		// TODO Auto-generated method stub
		setLightOn(true);
	}

	public void off() throws RemoteException {
		// TODO Auto-generated method stub
		setLightOn(false);
	}

	public boolean isOn() throws RemoteException {
		// TODO Auto-generated method stub
		return lightOn;
	}

	public boolean isLightOn() {
		return lightOn;
	}

	public void setLightOn(boolean lightOn) {
		this.lightOn = lightOn;
	}
}