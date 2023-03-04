/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tounesna.Presentation;
import Entite.Utilisateur;
import Util.MyConnection;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SignInController {
     public SignInController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = MyConnection.getinstance().getCnx();
    }
    Connection connexion;
    public static Utilisateur connectedUser;
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;


    @FXML
    private Hyperlink forgotPasswordLink;

    @FXML
    private Hyperlink createAccountLink;
    @FXML
    private AnchorPane login_form;
    @FXML
    private Label TravelMe1;

    

public void changeViewToHomePage() throws IOException {
  

}
      private String hashmdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    @FXML
    private void textfieldDesign(MouseEvent event) {
    }

    @FXML
    private void login(ActionEvent event) throws NoSuchAlgorithmException, SQLException, IOException {
        String req = "SELECT * from utilisateur WHERE email LIKE '" + emailField.getText() + "' and password LIKE '" + hashmdp(passwordField.getText()) + "' ";

            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
               Utilisateur u = new Utilisateur(rst.getInt("idUser")
                    , rst.getInt("cin")
                    , rst.getString("nom")
                    , rst.getString("prenom")
                    , rst.getString("adresse")
                     , rst.getInt("age")
                    , rst.getString("email")
                    , rst.getString("password")
                    , rst.getString("roles")
                   );
                      
                SignInController.connectedUser = u;  
         SignInController.connectedUser.setPassword(passwordField.getText());
                
                if(SignInController.connectedUser.getRoles().toLowerCase().equals("admin")){
                       Parent page1 = FXMLLoader.load(getClass().getResource("GestionUsers.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Profil");
        stage.setScene(scene);
        stage.show();    
           
                }
                
                else   if(SignInController.connectedUser.getRoles().toLowerCase().equals("client")){
                    
                          Parent page1 = FXMLLoader.load(getClass().getResource("Profil.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Profil");
        stage.setScene(scene);
        stage.show();       
                }  
       
        
    }  
        
        
    }

    @FXML
    private void createAccount(ActionEvent event) throws IOException {
                  Parent page1 = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(page1 );
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Profil");
        stage.setScene(scene);
        stage.show();    
        
    }

    @FXML
    private void forgotPassword(ActionEvent event) {
    }

}