package web.application.chat;

import web.application.chat.message.Message;

public interface Chatable {
    void setSender(Sendable sendable);
    void sendAll(Message message);
}
