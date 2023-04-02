package web.model.chat;

import web.model.api.dto.Message;

public interface IChat {
    Message openMessage(String username);
    Message setUserType(Message message);
}
