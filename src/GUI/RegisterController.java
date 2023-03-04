/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tounesna.Presentation;

import Entite.Utilisateur;
import Service.UtilisateurCRUD;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField age;
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<String> rolesc;
    @FXML
    private Button ajo;
 private Label label;
    @FXML
    private TextField password;
    @FXML
    private Hyperlink prec;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ObservableList<String> list_ne = FXCollections.observableArrayList( "Admin", "Client");
        rolesc.setItems(list_ne);    }    

    @FXML
    private void ajo(ActionEvent event) throws NoSuchAlgorithmException, IOException, MessagingException, SQLException {
   UtilisateurCRUD userService = new UtilisateurCRUD();

   
       
      
        
 
        if (cin.getText().equals("") || (nom.getText().equals(""))||prenom.getText().equals("")||adresse.getText().equals("")||
                age.getText().equals("")||email.getText().equals("")
           ||password.getText().equals("")||rolesc.getValue().equals("")) {
               
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        }
        else if (password.getText().length()<8){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Le mot de passe doit contenir au moins 8 chiffres ");
            a.setHeaderText(null);
            a.showAndWait();  
        }
    
        
        
        else {

           
             Utilisateur c = new Utilisateur( Integer.parseInt(cin.getText()),nom.getText(),prenom.getText(),adresse.getText(),
                Integer.parseInt(age.getText()),email.getText(),password.getText(),rolesc.getValue());        
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
          
          
          
                 userService.ajouter_utilisateur(c);
       
        
           TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Bienvenue votre inscription est passé avec succées");
            tray.setMessage("Bienvenue votre inscription est passé avec succées");
            tray.setNotificationType(NotificationType.INFORMATION);//
            tray.showAndDismiss(Duration.millis(3000));
       
      
       sendMail("hammoudi.hayfa@esprit.tn", "Bienvenue votre inscription est passé avec succées", 
                    "Bienvenue votre inscription est passé avec succées");
                 Parent page1 = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Se connecter");
        stage.setScene(scene);
        stage.show();  
      
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
      

          

        }; 
        
        
    
        
     
        
    }

    @FXML
    private void prec(ActionEvent event) throws IOException {
                   Parent page1 = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Utilisateurs");
        stage.setScene(scene);
        stage.show();   
        
    }
     public static void sendMail(String recipient,String Subject,String Text) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "isitapp11@gmail.com";
        String password = "cdjgkltnjmzbrlhj";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            
        Message message = prepareMessage(session, myAccountEmail, recipient,Subject,Text);

        javax.mail.Transport.send(message);
        System.out.println("Message sent successfully");
    }  
   
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient,String Subject,String Text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(Subject);
            message.setText(Text);
            return message;
        } catch (MessagingException ex) {
          
        }
        return null;}  
}
