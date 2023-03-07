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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import java.time.LocalDateTime;
/**import java.time.LocalDate;
 * FXML Controller class
 *
 * @author chaym
 */
public class ModifierActivityController implements Initializable {

    @FXML
    private ImageView iv_logo;
    @FXML
    private ImageView iv_admin;
    @FXML
    private TextField tf_descrip;
    @FXML
    private TextField tf_adr;
    @FXML
    private TextField tf_num;
    @FXML
    private DatePicker dp_date;
    @FXML
    private ComboBox<String> cb_categorie;
    @FXML
    private ComboBox<Gouvernorat> cb_gouver;
    GouvernoratService gs = new GouvernoratService();
     ActivityService  as = new ActivityService();
     Activity activity;
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
         
          dp_date.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            
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
        // TODO
    }    

    void setActivity(Activity act) {
        activity=act;
        tf_descrip.setText(act.getDescription()); 
         cb_categorie.setValue(act.getCategorie().name());
        cb_gouver.setValue(listGov.stream()
                .filter(g -> g.getId_gouver()== act.getGouvernorat())
                .findFirst()
                .orElse(null));
        dp_date.setValue(LocalDate.parse(act.getDate()));
         tf_num.setText(act.getNum_contact()); 
          tf_adr.setText(act.getAdresse()); 
//To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void GotoAfficherActivity(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherActivity.fxml"));

            Parent root = loader.load();
            tf_descrip.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void editActivity(ActionEvent event) {
         if (tf_descrip.getText().isEmpty() || tf_num.getText().length() < 8
                || tf_adr.getText().isEmpty() || tf_num.getText().isEmpty() ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs Correctement");
            alert.showAndWait();
        } else {
            activity.setDescription(tf_descrip.getText());
            activity.setDate(dp_date.getValue().toString());
            activity.setNum_contact(tf_num.getText());
            activity.setGouvernorat(cb_gouver.getValue().getId_gouver());
            activity.setCategorie(CategorieActivity.valueOf(cb_categorie.getValue()));
            activity.setAdresse(tf_adr.getText());
            if (as.updatePost(activity)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Activity a été modifié");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Activity n'a pas été modifié");
                alert.showAndWait();
            }

        }
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
        cb_categorie.setItems(listCat);

        cb_gouver.setItems(listGov);
    }
    }
    

