package web.infrastructure.jpa.user;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import web.application.authorization.user.IUserRepository;
import web.application.authorization.user.User;

@Stateless
public class UserRepository implements IUserRepository {

    @PersistenceContext(unitName = "PostgreSQL")
    private EntityManager entityManager;
    
    @Override
    public boolean check(User user) {
        try {
            List<EUser> users = entityManager
            .createQuery("SELECT p FROM EUser p WHERE p.login = :login AND p.password = :password", EUser.class)
            .setParameter("login", user.getLogin())
            .setParameter("password", user.getPassword())
            .getResultList();
            if(users != null && !users.isEmpty()){
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean add(User user) {
        try {
            EUser eUser = new EUser();
            eUser.setAll(user);
            entityManager.persist(eUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
