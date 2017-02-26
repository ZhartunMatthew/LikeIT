package edu.bsuir.likeit.entity.elementsofcontent;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ElementOfContent {

    public void prepareStatement(PreparedStatement ps) throws SQLException;
    public void prepareUpdateStatement(PreparedStatement ps) throws SQLException;
}
