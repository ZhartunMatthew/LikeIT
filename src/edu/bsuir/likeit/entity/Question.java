package edu.bsuir.likeit.entity;

import java.sql.Date;

/**
 * Created by Victoria on 27.01.2017.
 */
public class Question extends Entity {
    private String title;
    private Theme theme;
    private User user;
    private Date creatingTime;

    public Question(String title, Theme theme, User user, Date creatingTime) {
        this.title = title;
        this.theme = theme;
        this.user = user;
        this.creatingTime = creatingTime;
    }

    public Question(long id, String title, Theme themeId, User user, Date creatingTime) {
        super(id);
        this.title = title;
        this.theme = themeId;
        this.user = user;
        this.creatingTime = creatingTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatingTime() {
        return creatingTime;
    }

    public void setCreatingTime(Date creatingTime) {
        this.creatingTime = creatingTime;
    }

    @Override
    public String toString() {
        return title;
    }
}
