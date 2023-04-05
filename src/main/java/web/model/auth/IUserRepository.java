package web.model.auth;

public interface IUserRepository {
    boolean check(User user);
    boolean add(User user);
}
