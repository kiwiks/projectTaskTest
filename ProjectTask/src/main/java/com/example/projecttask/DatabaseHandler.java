package com.example.projecttask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + "," + Const.USERS_EMAIL + ")" + "VALUES(?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());
            prSt.setString(3, user.getEmail());
            //prSt.setInt(4, user.getCorrectAnswers());


            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return  resSet;
    }

    public  ResultSet getUserStatistics(User user) {
        String select = "SELECT * FROM " + Const.USER_TABLE; //+ " WHERE " +  Const.USERS_USERNAME + " = " + user.getUserName()
        ResultSet statistics = null;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement((select));
            statistics = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return statistics;
    }

    public int setNumUser(User user) {
        ResultSet resSet = null;
        int number = 0;
        String select = "UPDATE " + Const.USER_TABLE + " SET " + Const.USERS_CORRECTANSWERS + "=?, " + Const.USERS_ANSWERS + "=?" + " WHERE " + Const.USERS_USERNAME + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setInt(1, user.getCorrectAnswers());
            prSt.setInt(2, user.getAnswers());
            prSt.setString(3, user.getUserName());


            System.out.println(user.getCorrectAnswers() + "heh");
            System.out.println(user.getAnswers() + "heh1");
            System.out.println(user.getUserName() + "heh2");

            number = prSt.executeUpdate();
            prSt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return number;
    }
}
