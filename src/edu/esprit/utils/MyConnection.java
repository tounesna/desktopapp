/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author chaym
 */
public class MyConnection {
     public String url="jdbc:mysql://localhost:3306/tounesna";
    public String login="root";
    public String pwd="";
    public static Connection cnx;
    String filename= null;
    public static String path ;
    static MyConnection instance=null;
   public MyConnection() {
        
        try {
           cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
    }

    public static MyConnection getInstance() {
      if(instance ==null)
          instance =new MyConnection();
      
          return instance;
      
    }
    /*public void filen(){
        try{
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("choisir une image");
        chooser.setApproveButtonText("ajouter une image");
        chooser.showOpenDialog(null);
        File f= chooser.getSelectedFile();
        filename =f.getAbsolutePath();
        this.path=(filename);
        } catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Veuillez choisir une image");
        }
    }
        public String getp(){
        return path;
    }

    public void filename() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
    
    

    

