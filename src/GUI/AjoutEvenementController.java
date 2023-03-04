/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Gouvernorat;
import edu.esprit.entities.Region;
import edu.esprit.services.EvenementService;
import edu.esprit.services.GouvernoratService;
import java.io.File;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Houissa
 */
public class AjoutEvenementController implements Initializable {

    @FXML
    private TextField tf_titre;
    @FXML
    private DatePicker dp_date;
    @FXML
    private ComboBox<String> cb_reg;
    @FXML
    private ComboBox<Gouvernorat> cb_gov;
    @FXML
    private ImageView iv_logo;
    @FXML
    private TextArea tf_desc;

    ObservableList<Evenement> evtslist = FXCollections.observableArrayList();
    EvenementService es = new EvenementService();
    GouvernoratService gs = new GouvernoratService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File logof = new File("images/logo.png");
        Image logom = new Image(logof.toURI().toString());
        iv_logo.setImage(logom);

        initListDeroulante();

        dp_date.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
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

        ObservableList<String> listReg = FXCollections.observableArrayList();
        listReg.add(Region.NORD.name());
        listReg.add(Region.CENTRE.name());
        listReg.add(Region.SUD.name());
        cb_reg.setItems(listReg);
        cb_reg.setValue(Region.NORD.name());

        ObservableList<Gouvernorat> listGov = gs.getAllGouvernorat();
        cb_gov.setItems(listGov);
        cb_gov.setValue(listGov.get(0));
    }

    @FXML
    private void ajouterEvnement(ActionEvent event) {
        if (tf_titre.getText().isEmpty() || tf_titre.getText().length() < 4
                || tf_desc.getText().isEmpty()
                || tf_desc.getText().length() < 4
                || dp_date.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs du formulaire correctement");
            alert.showAndWait();
        } else {
            Evenement evt = new Evenement();
            evt.setTitre(tf_titre.getText());
            evt.setDescription(tf_desc.getText());
            evt.setDatev(dp_date.getValue().toString());
            evt.setRegion(edu.esprit.entities.Region.valueOf(cb_reg.getValue()));
            evt.setGouvernorat(cb_gov.getValue().getId());
            evt.setAuteur(1);

            if (es.ajoutEvent(evt)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Evenement a été ajouté");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Evenement n'a pas été ajouté");
                alert.showAndWait();
            }

        }
    }

    @FXML
    private void affichageEvenement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEvenement.fxml"));
            Parent root = loader.load();
            iv_logo.getScene().setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
