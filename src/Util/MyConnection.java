/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MyConnection {
      public String url="jdbc:mysql://localhost:3306/tounesna";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public static MyConnection instance;
    private MyConnection() {
        try {
          cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion établie !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection getinstance()
    {
        if(instance==null)
        {
            instance=new MyConnection();
            }
        return instance;
    }
    public static int checkLogin(String username, String password) {
       Connection con = MyConnection.getinstance().getCnx();
        if(con == null)
            return -1;
        
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try {
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, username);
            prest.setString(2, password);
            
            ResultSet rs = prest.executeQuery();
            
            while(rs.next()) {
                return 0;
            }
            
        } catch(SQLException se) {
            //se.printStackTrace();
            System.out.println("SQL Error !");
        }
        
        return 1;
    }
    
}
