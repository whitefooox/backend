package web.application.chat;

import web.application.chat.message.Message;

public interface Sendable {
    void sendAll(Message message);
}
