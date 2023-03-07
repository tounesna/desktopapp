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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author chaym
 */
public class ModifierGouvernoratController implements Initializable {

    @FXML
    private ImageView iv_logo;
    @FXML
    private ImageView iv_admin;
    @FXML
    private TextField tf_gouver;
    @FXML
    private ComboBox<String> cb_region;
  GouvernoratService gs = new GouvernoratService();
 
     Gouvernorat gouvernorat;
   ObservableList<Gouvernorat> listGov = gs.displayGouvernorats();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File logof = new File("images/logo.png");
         File adminf = new File("images/admin.png");
         Image logom = new Image(logof.toURI().toString());
         Image adminm = new Image(adminf.toURI().toString());
         iv_logo.setImage(logom);
         iv_admin.setImage(adminm);
          // TODO
    }    

    void setGouvernorat(Gouvernorat gouver) {
         gouvernorat= gouver;
        tf_gouver.setText(gouver.getNom_gouver()); 
         cb_region.setValue(gouver.getRegion_gouver().name());
        
    }

    @FXML
    private void EditGouvernorat(ActionEvent event) {
         if (tf_gouver.getText().isEmpty()  ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs Correctement");
            alert.showAndWait();
        } else {
            gouvernorat.setNom_gouver(tf_gouver.getText());
          
            gouvernorat.setRegion_gouver(Region.valueOf(cb_region.getValue()));
        
            if (gs.updateGouvernorat(gouvernorat)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("gouvernorat a été modifié");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("gouvernorat n'a pas été modifié");
                alert.showAndWait();
            }

        }
        
    }

    @FXML
    private void GoToAfficherGouver(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherGouvernorat.fxml"));

            Parent root = loader.load();
            tf_gouver.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     private void initListDeroulante() {

        ObservableList<String> listCat = FXCollections.observableArrayList();
         listCat.add(Region.NORD.name());
          listCat.add(Region.CENTRE.name());
           listCat.add(Region.SUD.name());
      
        cb_region.setItems(listCat);

       
    }
    
}
