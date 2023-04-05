package web.repository.user;

import java.util.List;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import web.model.auth.IUserRepository;
import web.model.auth.User;

public class UserRepository implements IUserRepository {

    @PersistenceUnit(unitName = "PostgreSQL")
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;
    
    @Override
    public boolean check(User user) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            List<EUser> users = entityManager
            .createQuery("SELECT p FROM EUser p WHERE p.login = :login AND p.password = :password", EUser.class)
            .setParameter("login", user.getLogin())
            .setParameter("password", user.getPassword())
            .getResultList();
            userTransaction.commit();
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
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            EUser eUser = new EUser();
            eUser.setAll(user);
            entityManager.persist(eUser);
            userTransaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
