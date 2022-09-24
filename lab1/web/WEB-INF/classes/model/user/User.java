package model.user;

public class User {
    private int id;
    private String login;
    private String password;
    private String email;

    public User(int id, String login, String password, String email){
        this.login = login;
        this.password = password;
        this.id = id;
        this.email = email;
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
}
