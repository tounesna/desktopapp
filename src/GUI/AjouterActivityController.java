/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.entities.Activity;
import edu.esprit.entities.CategorieActivity;
import edu.esprit.entities.Gouvernorat;
import edu.esprit.services.ActivityService;
import edu.esprit.services.GouvernoratService;
import edu.esprit.utils.MyConnection;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author chaym
 */
public class AjouterActivityController implements Initializable {

    @FXML
    private ImageView iv_logo;
    @FXML
    private ImageView id_admin;
    @FXML
    private TextField tf_description;
    @FXML
    private ComboBox<String> cb_cat;
    @FXML
    private ComboBox<Gouvernorat> cb_gouver;
    @FXML
    private TextField tf_adr;
    @FXML
    private TextField tf_num;
    @FXML
    private DatePicker dp_date;

    /**
     * Initializes the controller class.
     */
    ObservableList<Activity> actlist = FXCollections.observableArrayList();
    ActivityService as = new ActivityService();
    GouvernoratService gs = new GouvernoratService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      File logof = new File("images/logo.png");
        Image logom = new Image(logof.toURI().toString());
        iv_logo.setImage(logom);   
        // TODO
         File adminf = new File("images/admin.png");
        Image adminm = new Image(adminf.toURI().toString());
        id_admin.setImage(adminm);  
         initListDeroulante();

        dp_date.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())|| item.isEqual(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // set the cell color to pink for disabled dates
                        }
                    }
                };
            }
        });
        
        
        
    }  
     private void initListDeroulante() {

        ObservableList<String> listCat = FXCollections.observableArrayList();
        listCat.add(CategorieActivity.Parapente.name());
        listCat.add(CategorieActivity.Saut_Parachute.name());
        listCat.add(CategorieActivity.Camping.name());
         listCat.add(CategorieActivity.Balade_en_bateau.name());
        listCat.add(CategorieActivity.Rafting.name());
        listCat.add(CategorieActivity.Bapteme_de_plongé.name());
        listCat.add(CategorieActivity.Randonné_quad.name());
   
        cb_cat.setItems(listCat);
        cb_cat.setValue(CategorieActivity.Saut_Parachute.name());

        ObservableList<Gouvernorat> listGov = gs.displayGouvernorats();
        cb_gouver.setItems(listGov);
        cb_gouver.setValue(listGov.get(0));
    }


   

    //@FXML
    /*private void btnpathAction(ActionEvent event) {
        MyConnection v= new MyConnection();
        v.filen();
        String vpath=v.getp();
        if(vpath==null){
        }else
        { 
            txtpath.setText(vpath);
        }
        
    }*/

    @FXML
    private void addActivity(ActionEvent event) {
         if (tf_description.getText().isEmpty()
                || tf_adr.getText().isEmpty() || tf_num.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs du formulaire");
            alert.showAndWait();
        } else {
            Activity post = new Activity();
            post.setDescription(tf_description.getText());
            post.setAdresse(tf_adr.getText());
            post.setCategorie(edu.esprit.entities.CategorieActivity.valueOf(cb_cat.getValue()));
            post.setGouvernorat(cb_gouver.getValue().getId_gouver());
            post.setNum_contact(tf_num.getText());
            post.setDate(dp_date.getValue().toString());
            post.setImage("");
            post.setAuteur(1);

            if (as.addActivity(post)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("post a été ajouté");
                alert.showAndWait();
               
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("post n'a pas été ajouté");
                alert.showAndWait();
            }

        }
    }
 @FXML
    private void AffichageActivity(ActionEvent event) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherActivity.fxml"));
            Parent root = loader.load();
            tf_description.getScene().setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }
   
    
}
