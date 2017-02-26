package edu.bsuir.likeit.entity;

/**
 * Created by Victoria on 27.01.2017.
 */
public class Entity {
    protected long id;

    public Entity(long id) {
        this.id = id;
    }

    public Entity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
