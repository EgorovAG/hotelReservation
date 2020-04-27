package com.github.egorovag.hotelreserv.dao.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class MysqlDataBase {

    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String pass = resource.getString("password");

        return DriverManager.getConnection(url, user, pass);
    }
}
