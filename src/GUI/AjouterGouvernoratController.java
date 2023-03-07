/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.entities.Gouvernorat;
import edu.esprit.entities.Region;
import edu.esprit.services.GouvernoratService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author chaym
 */
public class AjouterGouvernoratController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private ComboBox<String> cb_region;
  
    ObservableList<Gouvernorat> actlist = FXCollections.observableArrayList();
   
    GouvernoratService gs = new GouvernoratService();
    @FXML
    private ImageView iv_logo;
    @FXML
    private ImageView iv_admin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File logof = new File("images/logo.png");
        Image logom = new Image(logof.toURI().toString());
        iv_logo.setImage(logom);   
        // TODO
         File adminf = new File("images/admin.png");
        Image adminm = new Image(adminf.toURI().toString());
        iv_admin.setImage(adminm);  
         initListDeroulante();
 // TODO
    }    

    @FXML
    private void AjoutActivity(ActionEvent event) {
          if (tf_nom.getText().isEmpty()
                  ) {
              Notifications notificationBuilder = Notifications.create()
                  .title("Alert").text("Il faut saisir le nom de gouvernorat").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.CENTER_LEFT)
                        .onAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent event)
                            { System.out.println("clicked ON");
                            }
                            });
                notificationBuilder.darkStyle();
                notificationBuilder.show();

          /*  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs du formulaire");
            alert.showAndWait();*/
          
        } else {
            Gouvernorat post = new Gouvernorat();
            post.setNom_gouver(tf_nom.getText());
          
            post.setRegion_gouver(edu.esprit.entities.Region.valueOf(cb_region.getValue()));
          

            if (gs.addGouvernorat(post)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Gouvernorat a été ajouté");
                alert.showAndWait();
               
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Gouvernorat n'a pas été ajouté");
                alert.showAndWait();
            }

        }
    }

    @FXML
    private void GoToAfficherGouvernorat(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherGouvernorat.fxml"));
            Parent root = loader.load();
            tf_nom.getScene().setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initListDeroulante() {
          ObservableList<String> listCat = FXCollections.observableArrayList();
        listCat.add(Region.NORD.name());
         listCat.add(Region.CENTRE.name());
          listCat.add(Region.SUD.name());
       
   
        cb_region.setItems(listCat);
        ;//To change body of generated methods, choose Tools | Templates.
    }
    
}
