package web.model.chat;

public class Chat implements IChat {

    @Override
    public Message getUserMessage(String text, String username) {
        Message message = new Message();
        message.setText(text);
        message.setUsername(username);
        message.setType("user");
        return message;
    }

    @Override
    public Message getHello(String username) {
        Message message = new Message();
        message.setText("Добро пожаловать, " + username + " ^-^");
        message.setType("system");
        return message;
    }

    @Override
    public Message getGoodbye(String username) {
        Message message = new Message();
        message.setText("До скорой встречи, " + username + " ^-^");
        message.setType("system");
        return message;
    }

}
