package web.application.chat;

import web.application.chat.message.Message;

public class Chat implements Chatable {

    private Sendable sendable;

    @Override
    public void setSender(Sendable sendable) {
        this.sendable = sendable;
    }

    @Override
    public void sendAll(Message message) {
        sendable.sendAll(message);
    }
}
