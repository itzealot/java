package com.apusic.skynet.curator.listener;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;

public class MySharedCounterListener implements SharedCountListener {

	@Override
	public void stateChanged(CuratorFramework client, ConnectionState state) {
		System.out.println("State changed: " + state.toString());
	}

	@Override
	public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
		System.out.println("Counter's value is changed to: " + newCount);
	}

}