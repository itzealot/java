package com.apusic.distribute.message.listener;


import com.apusic.distribute.message.model.MessageEvent;

import java.io.Serializable;

/**
 * Created by a on 2016/1/19.
 * �¼����սӿ�
 */
public interface MessageEventListener<T extends Serializable> {

    void handler(MessageEvent<T> eventMessage);

}
