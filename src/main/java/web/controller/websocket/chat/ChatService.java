package web.controller.websocket.chat;

import java.io.IOException;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import web.model.api.dto.Message;

@ServerEndpoint(value = "/chat/{username}",
                decoders = MessageDecoder.class,
                encoders = MessageEncoder.class)
public class ChatService {

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException{
        Message message = new Message();
        message.setType("system");
        message.setText("Добро пожаловать, " + username + " ^-^");
        broadcast(message, session);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException{
        message.setType("user");
        broadcast(message, session);
    }

    private void broadcast(Message message, Session session) throws IOException, EncodeException{
        for(Session sess : session.getOpenSessions()){
            sess.getBasicRemote().sendObject(message);
        }
    }
}