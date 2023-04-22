package web.model.chat;

public interface IChat {
    Message getUserMessage(String text, String username);
    Message getHello(String username);
    Message getGoodbye(String username);
    Message getRecommendation(String animeName);
}
