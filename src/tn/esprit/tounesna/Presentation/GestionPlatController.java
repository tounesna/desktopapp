/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tounesna.Presentation;

import Entite.Utilisateur;
import Entite.plat;
import Service.PlatService;
import Service.UtilisateurCRUD;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class GestionPlatController implements Initializable {
private Label label;
    @FXML
    private Label inputregion;
    @FXML
    private TextField inputrecette;
    @FXML
    private TextField inputchef;
    @FXML
    private Button btn_aj_user;
    @FXML
    private Button supp;
    @FXML
    private Button btn_mod_user;
    @FXML
    private Button btn_vider_user;
    @FXML
    private Button Menu;
    @FXML
    private Label inputnomplat;
    @FXML
    private TextField inputRech;
    @FXML
    private TableView<plat> tableUser;
    @FXML
    private TableColumn<?, ?> nomplat;
    @FXML
    private TableColumn<?, ?> recette;
    @FXML
    private TableColumn<?, ?> chef;
    @FXML
    private TableColumn<?, ?> region;
    @FXML
    private Label labelid;
    @FXML
    private Button confirmer;
public ObservableList<plat> list;
    @FXML
    private TextField nomplatinput;
    @FXML
    private TextField aze;
    @FXML
    private Hyperlink Deconnexion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

     
        
       
      
        
        ArrayList<plat> c = new ArrayList<>();
  
        try {
             PlatService pss = new PlatService();
            c = (ArrayList<plat>) pss.AfficherAllplat();
        } catch (SQLException ex) {
        }
   
        
        ObservableList<plat> obs2 = FXCollections.observableArrayList(c);
        tableUser.setItems(obs2);
        
         nomplat.setCellValueFactory(new PropertyValueFactory<>("nomplat"));
 recette.setCellValueFactory(new PropertyValueFactory<>("recette"));
  chef.setCellValueFactory(new PropertyValueFactory<>("chef"));
 region.setCellValueFactory(new PropertyValueFactory<>("region"));

         try {
               PlatService pss = new PlatService();
            list = FXCollections.observableArrayList(
                    pss.AfficherAllplat()
            );        
        
        
   FilteredList<plat> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super plat>) plats -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        String tostring = plats.getNomplat();
                        if (tostring.toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<plat> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableUser.comparatorProperty());
                tableUser.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    

    @FXML
    private void ajouter_user(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
          PlatService userService = new PlatService();

   
       
      
        
 
        if (inputchef.getText().equals("") || (inputnomplat.getText().equals(""))||inputregion.getText().equals("")||
                inputrecette.getText().equals("") )
              {
               
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        }
     
    
        
        
        else {

           
             plat c = new plat(nomplatinput.getText(),inputrecette.getText(),inputchef.getText(),aze.getText());        
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
          
          
          
                 userService.ajouter_plat(c);
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
        PlatService cs = new PlatService();
        List<plat> listrec = new ArrayList<>();
        listrec = cs.AfficherAllplat();
        ObservableList<plat> data = FXCollections.observableArrayList(listrec);
        tableUser.setItems(data);
    }
    @FXML
    private void supp(ActionEvent event) throws SQLException {
          if (event.getSource() == supp) {
            plat rec = new plat();

            rec.setIdplat(tableUser.getSelectionModel().getSelectedItem().getIdplat());
            PlatService cs = new PlatService();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete");
      alert.setHeaderText("Are you sure want to delete this plat");
      alert.setContentText(" ");

      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
           cs.Supprimerplat(rec);
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
    private void modifier_user(ActionEvent event) {
        
       
  labelid.setText( Integer.toString(tableUser.getSelectionModel().getSelectedItem().getIdplat())); 
        inputchef.setText( tableUser.getSelectionModel().getSelectedItem().getChef()); 
        nomplatinput.setText( tableUser.getSelectionModel().getSelectedItem().getNomplat()); 
              inputrecette.setText(tableUser.getSelectionModel().getSelectedItem().getRecette()); 
        aze.setText( tableUser.getSelectionModel().getSelectedItem().getRegion()); 
      
        confirmer.setVisible(true);         
        
        
    }

    @FXML
    private void vider_plat(ActionEvent event) {
        nomplatinput.clear();
        inputrecette.clear();
        inputchef.clear();
        aze.clear();
    }

    @FXML
    private void Menu(ActionEvent event) {
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void confirmer(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
              PlatService userService = new PlatService();

   
       
      
        
 
        if (inputchef.getText().equals("") || (nomplatinput.getText().equals(""))||aze.getText().equals("")||
                inputrecette.getText().equals("") )
              {
               
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        }
     
    
        
        
        else {

           
             plat c = new plat(Integer.parseInt(labelid.getText()),inputnomplat.getText(),inputrecette.getText(),inputchef.getText(),inputregion.getText());        
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
          
          
          
                 userService.modifierplat(c);
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
       @FXML
    private void prec(ActionEvent event) throws IOException {
             Parent page1 = FXMLLoader.load(getClass().getResource("GestionPlat.fxml"));
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
