package edu.bsuir.likeit.entity;

public class Theme extends Entity {
    private String name;

    public Theme(String name) {
        this.name = name;
    }

    public Theme(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " - " + name + "\n";
    }
}
