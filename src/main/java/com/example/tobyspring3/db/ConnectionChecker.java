package com.example.tobyspring3.db;

import java.sql.*;

public class ConnectionChecker {
    public void check() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/spring-db", "root", "1234");

        Statement st = conn.createStatement(); //statement 객체는 sql 쿼리를 데이터베이스로 전송하는데 사용된다.
        ResultSet rs = st.executeQuery("SHOW DATABASES");
        rs = st.getResultSet();

        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    public void add() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/spring-db", "root", "1234");

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, "1");
        pstmt.setString(2, "yunseok");
        pstmt.setString(3, "1234");
        pstmt.executeUpdate();
    }

    public void select() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/spring-db", "root", "1234");

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        rs = st.getResultSet();
        while (rs.next()) {
            String col1 = rs.getString(1);
            String col2 = rs.getString(2);
            String col3 = rs.getString(3);
            System.out.println(col1 + col2 + col3);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionChecker cc = new ConnectionChecker();
        cc.check();
        cc.select();
    }
}

