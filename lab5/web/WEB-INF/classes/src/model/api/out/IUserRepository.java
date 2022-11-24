package src.model.api.out;

public interface IUserRepository {
    boolean check(String login, String password);
}
