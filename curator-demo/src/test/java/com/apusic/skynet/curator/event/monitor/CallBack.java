package com.apusic.skynet.curator.event.monitor;

import com.apusic.skynet.curator.event.Button;

public interface CallBack {

	public void callBack(Button btn, Object... objects);
}
