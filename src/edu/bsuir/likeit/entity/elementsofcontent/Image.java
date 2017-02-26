package edu.bsuir.likeit.entity.elementsofcontent;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Victoria on 28.01.2017.
 */
public class Image implements ElementOfContent {
    private Blob item;

    public Image(Blob item) {
        this.item = item;
    }

    public void prepareStatement(PreparedStatement ps) throws SQLException {
        ps.setBlob(3, item);
        ps.setNull(2, Types.LONGNVARCHAR);
    }

    public void prepareUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setBlob(2, item);
        ps.setNull(1, Types.LONGNVARCHAR);
    }

    public Blob getItem() {
        return item;
    }

    public void setItem(Blob item) {
        this.item = item;

    }
}
