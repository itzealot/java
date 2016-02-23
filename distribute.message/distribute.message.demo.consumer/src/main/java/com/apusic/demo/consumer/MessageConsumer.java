package com.apusic.demo.consumer;

import com.apusic.demo.model.User;
import com.apusic.distribute.kafka.bus.KafkaMessageEventBus;
import com.apusic.distribute.message.bus.MessageEventBus;
import com.apusic.distribute.message.listener.MessageEventListener;
import com.apusic.distribute.message.model.MessageEvent;

/**
 * Created by a on 2016/1/19.
 */
public class MessageConsumer implements MessageEventListener<User>{


    public void handler(MessageEvent<User> eventMessage) {
        User usr = eventMessage.getMessage();
        System.out.println("Message:"+usr);
    }

    public static void main(String[] args){
        MessageConsumer consumer = new MessageConsumer();
        MessageEventBus bus = new KafkaMessageEventBus();
        bus.addMessageEventListener("groupId-1","event-1",consumer);
    }

}
