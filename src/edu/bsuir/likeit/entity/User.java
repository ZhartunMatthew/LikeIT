package edu.bsuir.likeit.entity;


import java.sql.Date;

public class User extends Entity {
    private String login;
    private String email;
    private String password;
    private Date registrationDate;
    private Date enableDate;
    private int rating;
    private int role;

    public User(long id, String login, String email, String password, Date registrationDate, Date enableDate,
                int rating, int role) {
        super(id);
        this.login = login;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.enableDate = enableDate;
        this.rating = rating;
        this.role = role;
    }

    public User(long id, String login, String email, String password) {
        super(id);
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = 2;
    }

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = 2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String toString() {
        return (id +"\n" +login +"\n" + email+"\n" + role);
    }


}
