/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tounesna.Presentation;

import Entite.Utilisateur;
import Service.UtilisateurCRUD;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class GestionUsersController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private Button btn_aj_user;
    @FXML
    private Button btn_mod_user;
    @FXML
    private Button btn_vider_user;
    @FXML
    private TableView<Utilisateur> tableUser;
    @FXML
    private TableColumn<?, ?> nom_co1;
    @FXML
    private TableColumn<?, ?> prenom_co1;
    @FXML
    private TableColumn<?, ?> adresse_co1;
    @FXML
    private TableColumn<?, ?> age_co1;
    @FXML
    private TableColumn<?, ?> cin_co1;
    @FXML
    private TableColumn<?, ?> email_co1;
    @FXML
    private TableColumn<?, ?> roles_co1;
    @FXML
    private Button Menu;
    @FXML
    private TextField age;
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<String> role;
    
     private Label label;
    @FXML
    private TextField inputRech;
    @FXML
    private TextField password;
public ObservableList<Utilisateur> list;
    @FXML
    private Button supp;
    @FXML
    private Label labelid;
    @FXML
    private Button confirmer;
    @FXML
    private Button pdf2;
    @FXML
    private Hyperlink prec;
    @FXML
    private Hyperlink Deconnexion;
    @FXML
    private Hyperlink Stat;
    
    
    /**
     * Initializes the controller class.
     */
  
      private void showEvenements(ObservableList<Utilisateur> a) throws SQLException {
             UtilisateurCRUD es = new UtilisateurCRUD();
    ObservableList<Utilisateur> evtslist = es.displayEvent();
           

           Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> deletecellFactory = (TableColumn<Utilisateur, String> param) -> {
            final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button deleteIcon = new Button();
                        deleteIcon.setText("Supprimer");

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Utilisateur selectedEvenement = getTableView().getItems().get(getIndex());

                            //alert
                            if (selectedEvenement != null) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setTitle("Supprimer l'Evenement");
                                alert.setHeaderText(null);
                                alert.setContentText("Etes vous sur de vouloir supprimer l'Utilisateur ?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                                {
         
                                    if (es.DeleteUser(selectedEvenement.getIdUser())) {
                                        Alert alerts = new Alert(Alert.AlertType.INFORMATION);
                                        alerts.initStyle(StageStyle.UTILITY);
                                        alerts.setTitle("Success");
                                        alerts.setHeaderText(null);
                                        alerts.setContentText("Evenement a été supprimée");
                                        alerts.showAndWait();
                                        try {
                                            showEvenements(es.displayEvent());
                                        } catch (SQLException ex) {
                                        }
                                    } else {
                                        Alert alertz = new Alert(Alert.AlertType.ERROR);
                                        alertz.initStyle(StageStyle.UTILITY);
                                        alertz.setTitle("Error");
                                        alertz.setHeaderText(null);
                                        alertz.setContentText("Evenement n'a pas été supprimée");
                                        alertz.showAndWait();
                                    }
                                }

                            } else {
                                Alert alertz = new Alert(Alert.AlertType.ERROR);
                                alertz.initStyle(StageStyle.UTILITY);
                                alertz.setTitle("Error");
                                alertz.setHeaderText(null);
                                alertz.setContentText("selectionnez un Evenement");
                                alertz.showAndWait();
                            }
                        });

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };
            return cell;
        };
         
      }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list_ne = FXCollections.observableArrayList( "Admin", "Client");
        role.setItems(list_ne); 
     //////////
     TableColumn test = new TableColumn("aaaa");
    //   tableUser.getColumns().add(test);
       
      ////////////
        
        ArrayList<Utilisateur> c = new ArrayList<>();
  
        try {
             UtilisateurCRUD pss = new UtilisateurCRUD();
            c = (ArrayList<Utilisateur>) pss.AfficherAllUsers();
             //  showEvenements(pss.displayEvent());
        } catch (SQLException ex) {
        }

        
        ObservableList<Utilisateur> obs2 = FXCollections.observableArrayList(c);
        tableUser.setItems(obs2);
        
         nom_co1.setCellValueFactory(new PropertyValueFactory<>("nom"));
 prenom_co1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
  adresse_co1.setCellValueFactory(new PropertyValueFactory<>("adresse"));
 age_co1.setCellValueFactory(new PropertyValueFactory<>("age"));
  cin_co1.setCellValueFactory(new PropertyValueFactory<>("cin"));
 email_co1.setCellValueFactory(new PropertyValueFactory<>("email"));
  roles_co1.setCellValueFactory(new PropertyValueFactory<>("roles"));
         try {
               UtilisateurCRUD pss = new UtilisateurCRUD();
            list = FXCollections.observableArrayList(
                    pss.AfficherAllUsers()
            );        
                              showEvenements(pss.displayEvent());

        
   FilteredList<Utilisateur> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Utilisateur>) Utilisateurs -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        String tostring = Utilisateurs.getNom();
                        if (tostring.toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableUser.comparatorProperty());
                tableUser.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
  
      
        
    }    

    @FXML
    private void ajouter_user(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
         UtilisateurCRUD userService = new UtilisateurCRUD();

   
       
      
        
 
        if (cin.getText().equals("") || (nom.getText().equals(""))||prenom.getText().equals("")||adresse.getText().equals("")||
                age.getText().equals("")||email.getText().equals("")
           ||password.getText().equals("")||role.getValue().equals("")) {
               
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
                Integer.parseInt(age.getText()),email.getText(),password.getText(),role.getValue());        
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
          
          
          
                 userService.ajouter_utilisateur(c);
       resetTableData();
        
           TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Ajouté avec succées");
            tray.setMessage("Ajouté avec succées");
            tray.setNotificationType(NotificationType.INFORMATION);//
            tray.showAndDismiss(Duration.millis(3000));
       
      

      
      
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
      

          

        }; 
        
    }
  public void resetTableData() throws SQLDataException, SQLException {
        UtilisateurCRUD cs = new UtilisateurCRUD();
        List<Utilisateur> listrec = new ArrayList<>();
        listrec = cs.AfficherAllUsers();
        ObservableList<Utilisateur> data = FXCollections.observableArrayList(listrec);
        tableUser.setItems(data);
    }
    @FXML
    private void supp(ActionEvent event) throws SQLException {
          if (event.getSource() == supp) {
            Utilisateur rec = new Utilisateur();

            rec.setIdUser(tableUser.getSelectionModel().getSelectedItem().getIdUser());
            UtilisateurCRUD cs = new UtilisateurCRUD();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete");
      alert.setHeaderText("Are you sure want to delete this user");
      alert.setContentText(" ");

      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
           cs.SupprimerUser(rec);
                  TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Supprimé avec succés");
            tray.setMessage("Suppriméavec succés");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
       
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
          
           
         
            
            
            resetTableData();

        }
        
        
    }

    @FXML
    private void modifier_user(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        
     
  
  labelid.setText( Integer.toString(tableUser.getSelectionModel().getSelectedItem().getIdUser())); 
        nom.setText( tableUser.getSelectionModel().getSelectedItem().getNom()); 
        prenom.setText( tableUser.getSelectionModel().getSelectedItem().getPrenom()); 
              role.setValue(tableUser.getSelectionModel().getSelectedItem().getRoles()); 
        adresse.setText( tableUser.getSelectionModel().getSelectedItem().getAdresse()); 
        cin.setText( Integer.toString(tableUser.getSelectionModel().getSelectedItem().getCin())); 
        email.setText(tableUser.getSelectionModel().getSelectedItem().getEmail()); 
        age.setText( Integer.toString(tableUser.getSelectionModel().getSelectedItem().getAge())); 
          password.setText( tableUser.getSelectionModel().getSelectedItem().getPassword()); 

    
        confirmer.setVisible(true);      
        
        
    }

    @FXML
    private void vider_user(ActionEvent event) {
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void Menu(ActionEvent event) {
    }

    @FXML
    private void confirmer(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
         UtilisateurCRUD userService = new UtilisateurCRUD();

   
       
      
        
 
        if (cin.getText().equals("") || (nom.getText().equals(""))||prenom.getText().equals("")||adresse.getText().equals("")||
                age.getText().equals("")||email.getText().equals("")
           ||password.getText().equals("")||role.getValue().equals("")) {
               
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

           
             Utilisateur c = new Utilisateur( Integer.parseInt(labelid.getText()),Integer.parseInt(cin.getText()),nom.getText(),prenom.getText(),adresse.getText(),
                Integer.parseInt(age.getText()),email.getText(),password.getText(),role.getValue());        
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
          
          
          
                 userService.modifierUser(c);
       resetTableData();
        
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

     private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("..\\ListUsers.pdf"));
        d.open();
        
        PdfPTable pTable = new PdfPTable(5);
       

     
        
        tableUser.getItems().forEach((t) -> {
       pTable.addCell(String.valueOf(t.getNom()));
         pTable.addCell(String.valueOf(t.getPrenom()));
           pTable.addCell(String.valueOf(t.getEmail()));
     pTable.addCell(String.valueOf(t.getAge()));
 pTable.addCell(String.valueOf(t.getRoles()));

       
        });
        
                        d.add(pTable);

        d.close();
        Desktop.getDesktop().open(new File("..\\ListUsers.pdf"));

    } 
    
    
    
    
    
    @FXML
    private void pdf2(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
         if (event.getSource() == pdf2) {
            
             printPDF();
            
   
        }
    
}

    @FXML
    private void prec(ActionEvent event) throws IOException {
             Parent page1 = FXMLLoader.load(getClass().getResource("GestionPlat.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }
           @FXML
    private void precUser(ActionEvent event) throws IOException {
             Parent page1 = FXMLLoader.load(getClass().getResource("GestionUsers.fxml"));
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

    @FXML
    private void Stat(ActionEvent event) throws IOException {
               Parent page1 = FXMLLoader.load(getClass().getResource("StatistiquePlats.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}