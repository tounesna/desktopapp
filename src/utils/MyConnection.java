/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author samar
 */
public class MyConnection {

    String url = "jdbc:mysql://localhost:3306/tounes";
    String login = "root";
    String password = "";
    Connection myconnex;
    private static MyConnection instance;

    public MyConnection() {
        try {
            myconnex = DriverManager.getConnection(url, login, password);
            System.out.println("reussi!!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance; //To change body of generated methods, choose Tools | Templates.
    }

    public Connection getConnection() {
        return myconnex;
    }

}
