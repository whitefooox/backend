package src.model.api.out;

import src.model.api.dto.User;

public interface IUserRepository {
    boolean check(User user);
    boolean add(User user);
}
