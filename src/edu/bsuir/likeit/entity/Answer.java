package edu.bsuir.likeit.entity;

import java.sql.Date;

/**
 * Created by Victoria on 27.01.2017.
 */
public class Answer extends Entity{

    long questionId;
    String answer;
    User user;
    Date creatingTime;
    long rating;

    public Answer(long id, long questionId, String answer, User user, Date creatingTime, long rating) {
        super(id);
        this.questionId = questionId;
        this.answer = answer;
        this.user = user;
        this.creatingTime = creatingTime;
        this.rating = rating;
    }

    public Answer(long questionId, String answer, User user, Date creatingTime, long rating) {
        this.questionId = questionId;
        this.answer = answer;
        this.user = user;
        this.creatingTime = creatingTime;
        this.rating = rating;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return answer;
    }
}
