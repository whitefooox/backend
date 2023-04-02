package web.controller.websocket.chat;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import web.model.api.dto.Message;

public class MessageEncoder implements Encoder.Text<Message>{

    private static Jsonb jsonb = JsonbBuilder.create();

    @Override
    public String encode(Message message) throws EncodeException {
        return jsonb.toJson(message);
    }
}
