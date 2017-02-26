package edu.bsuir.likeit.entity;

/**
 * Created by Victoria on 26.01.2017.
 */
public enum UserRole {
    ADMINISTRATOR(1),
    CLIENT(2);

    private long id;

    UserRole(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static UserRole fromLong(long i) {
        UserRole role;
        switch ((int)i) {
            case 1:
                role = UserRole.ADMINISTRATOR;
                break;
            case 2:
            default:
                role = UserRole.CLIENT;
                break;
        }
        return role;
    }
}
