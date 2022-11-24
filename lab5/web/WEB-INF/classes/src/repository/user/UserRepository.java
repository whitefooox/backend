package src.repository.user;

import java.util.List;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import src.model.api.out.IUserRepository;

public class UserRepository implements IUserRepository {

    @PersistenceUnit(unitName = "PostgreSQL")
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;
    
    @Override
    public boolean check(String login, String password) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            userTransaction.begin();
            entityManager.joinTransaction();
            List<EUser> user = entityManager
            .createQuery("SELECT p FROM EUser p WHERE p.login = :login AND p.password = :password", EUser.class)
            .setParameter("login", login)
            .setParameter("password", password)
            .getResultList();
            userTransaction.commit();
            if(user != null && !user.isEmpty()){
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
