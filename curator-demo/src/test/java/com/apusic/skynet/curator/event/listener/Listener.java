package com.apusic.skynet.curator.event.listener;

import com.apusic.skynet.curator.event.Button;
import com.apusic.skynet.curator.event.monitor.CallBack;

public interface Listener {

	public void onClick(Button btn, CallBack call);
}
