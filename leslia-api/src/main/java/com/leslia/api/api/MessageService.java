package com.leslia.api.api;

public interface MessageService {

    public void sendQueue();

    public void sendTopic();

    public void fanout();

    public void direct();

    public void topic();


}
