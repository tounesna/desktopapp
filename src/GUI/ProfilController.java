/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tounesna.Presentation;

import Entite.Utilisateur;
import Service.UtilisateurCRUD;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class ProfilController implements Initializable {

    @FXML
    private Button modif;
    @FXML
    private Label labelNom;
    @FXML
    private TextField nomtextf;
    @FXML
    private Label labelPrenom;
    @FXML
    private TextField prenomtextf;
    @FXML
    private Label labelDateNaissance;
    @FXML
    private TextField dateNtextf;
    @FXML
    private Label labelTelephone;
    @FXML
    private TextField teltextf;
    @FXML
    private Label labelEmail;
    @FXML
    private TextField emailtextf;
    @FXML
    private Button enregistrer;
    @FXML
    private Label labelid;
    @FXML
    private Label gg;
  private Label label;
    @FXML
    private Hyperlink prec;
    @FXML
    private Hyperlink Deconnexion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
String nom = SignInController.connectedUser.getNom().concat(" ");
String fullname = nom.concat(SignInController.connectedUser.getPrenom());
        labelid.setText(Integer.toString(SignInController.connectedUser.getIdUser()));
         nomtextf.setText(SignInController.connectedUser .getNom());
     //   tel.setText(Integer.toString(ListController.connectedUser.getTelephone()));
        prenomtextf.setText(SignInController.connectedUser.getPrenom());
        dateNtextf.setText(Integer.toString(SignInController.connectedUser.getAge()));
       // teltextf.setText(SignInController.connectedUser.get());
                emailtextf.setText(SignInController.connectedUser.getEmail());
                gg.setText(SignInController.connectedUser.getEmail());

        
    }    

    @FXML
    private void onSauvegarderButtonClicked(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
              UtilisateurCRUD userService = new UtilisateurCRUD();

   
       
      
        
 
        if (  (nomtextf.getText().equals(""))||prenomtextf.getText().equals("")||
                dateNtextf.getText().equals("")||emailtextf.getText().equals("")
           ) {
               
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        }
    
        
        
        else {

//int IdUser, int cin, String Nom, String prenom, String adresse, int age, String email, String password, String roles) {

Utilisateur c = new Utilisateur( Integer.parseInt(labelid.getText()),SignInController.connectedUser.getCin(),
        prenomtextf.getText(),nomtextf.getText() ,SignInController.connectedUser.getAdresse(),
                    
              Integer.parseInt(  dateNtextf.getText()),emailtextf.getText(),SignInController.connectedUser.getPassword(),
                SignInController.connectedUser.getRoles());        
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
          
          
          
                 userService.modifierUser(c);
        
           TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Modifié avec succées");
            tray.setMessage("Modifié avec succées");
            tray.setNotificationType(NotificationType.INFORMATION);//
            tray.showAndDismiss(Duration.millis(3000));
       
      

      
      
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
      

          

        };  
    }

    @FXML
    private void prec(ActionEvent event) throws IOException {
             Parent page1 = FXMLLoader.load(getClass().getResource("PlatFront.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }

    @FXML
    private void Deconnexion(ActionEvent event) throws IOException {
              Parent page1 = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }
        
        
    }
    

