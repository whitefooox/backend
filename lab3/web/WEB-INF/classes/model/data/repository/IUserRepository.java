package model.data.repository;

import model.data.entity.User;

public interface IUserRepository {
    void add(User user);
    boolean check(User user);
}
