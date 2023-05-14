package web.application.chat.message;

public interface MessageWritable {
    Message getUserMessage(String text, String username);
    Message getHello(String username);
    Message getGoodbye(String username);
    Message getRecommendation(String animeName);
}
