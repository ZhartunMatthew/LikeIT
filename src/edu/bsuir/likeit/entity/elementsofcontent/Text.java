package edu.bsuir.likeit.entity.elementsofcontent;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Victoria on 28.01.2017.
 */
public class Text implements ElementOfContent {
    private String text;

    public Text(String text) {
        this.text = text;
    }

    public void prepareStatement(PreparedStatement ps) throws SQLException {
        ps.setString(2, text);
        ps.setNull(3, Types.BLOB);
    }

    public void prepareUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, text);
        ps.setNull(2, Types.BLOB);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
