package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDao{
    ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }


    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection con = connectionMaker.makeConnection();
        PreparedStatement pstmt = con.prepareStatement("insert into users(id, name, password)" +
                "values (?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());
        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }
    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection con = connectionMaker.makeConnection();
        PreparedStatement pstmt = con.prepareStatement("select id, name, password from users where id = ?");
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        con.close();

        return user;
    }
}

