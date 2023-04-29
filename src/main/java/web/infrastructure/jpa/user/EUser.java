package web.infrastructure.jpa.user;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import web.application.authorization.user.User;

@Entity
@Table(name = "\"accounts\"")
public class EUser implements Serializable {
    
    @Id
    @Column(name = "\"id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "\"login\"")
    private String login;

    @Column(name = "\"password\"")
    private String password;

    @Column(name = "\"email\"")
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setAll(User user){
        setLogin(user.getLogin());
        setPassword(user.getPassword());
        setEmail(user.getEmail());
    }
}
