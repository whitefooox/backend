package web.model.api.out;

import web.model.api.dto.User;

public interface IUserRepository {
    boolean check(User user);
    boolean add(User user);
}
