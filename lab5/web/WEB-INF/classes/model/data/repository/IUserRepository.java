package model.data.repository;

public interface IUserRepository {
    boolean check(String login, String password);
}
