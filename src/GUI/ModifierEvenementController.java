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
import java.time.LocalDateTime;
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
public class ModifierEvenementController implements Initializable {

    @FXML
    private ImageView iv_logo;
    @FXML
    private DatePicker dp_date;
    @FXML
    private ComboBox<String> cb_reg;
    @FXML
    private ComboBox<Gouvernorat> cb_gov;
    @FXML
    private TextArea tf_desc;

    ObservableList<Evenement> evtslist = FXCollections.observableArrayList();
    ObservableList<Integer> evtslistIds = FXCollections.observableArrayList();

    EvenementService es = new EvenementService();
    GouvernoratService gs = new GouvernoratService();

    ObservableList<Gouvernorat> listGov = gs.getAllGouvernorat();
    @FXML
    private ComboBox<Integer> cb_id_event;
    @FXML
    private TextField tf_titre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File logof = new File("images/logo.png");
        Image logom = new Image(logof.toURI().toString());
        iv_logo.setImage(logom);

        initListDeroulante();

        evtslist = es.displayEvent();

        for (Evenement ev : evtslist) {
            evtslistIds.add(ev.getIdev());
        }
        cb_id_event.setItems(evtslistIds);
        if (evtslistIds.size() != 0) {
            cb_id_event.setValue(evtslistIds.get(0));
            setupFields();
        }

        cb_id_event.valueProperty().addListener((observable, oldValue, newValue) -> {
            setupFields();
        });

        dp_date.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // set the cell color to pink for disabled dates
                        }
                    }
                };
            }
        });

    }

    @FXML
    private void ModifEvenement(ActionEvent event) {
        if (tf_desc.getText().isEmpty() || tf_desc.getText().length() < 5
                || tf_titre.getText().isEmpty() || tf_titre.getText().length() < 5) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs du formulaire correctement");
            alert.showAndWait();
        } else {
            Evenement evt = evtslist.get(evtslistIds.indexOf(cb_id_event.getValue()));
            evt.setTitre(tf_titre.getText());
            evt.setDatev(dp_date.getValue().toString());
            evt.setDescription(tf_desc.getText());
            evt.setGouvernorat(cb_gov.getValue().getId());
            evt.setRegion(Region.valueOf(cb_reg.getValue()));
            if (es.updateEvenement(evt)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Evenement a été modifié");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Evenement n'a pas été modifié");
                alert.showAndWait();
            }

        }
    }

    private void initListDeroulante() {

        ObservableList<String> listReg = FXCollections.observableArrayList();
        listReg.add(Region.NORD.name());
        listReg.add(Region.CENTRE.name());
        listReg.add(Region.SUD.name());
        cb_reg.setItems(listReg);

        cb_gov.setItems(listGov);
    }

    private void setupFields() {
        Evenement ev = evtslist.get(evtslistIds.indexOf(cb_id_event.getValue()));
        tf_titre.setText(ev.getTitre());
        cb_reg.setValue(ev.getRegion().name());
        cb_gov.setValue(listGov.stream()
                .filter(g -> g.getId() == ev.getGouvernorat())
                .findFirst()
                .orElse(null));
        dp_date.setValue(LocalDate.parse(ev.getDatev()));
        tf_desc.setText(ev.getDescription());

    }

    @FXML
    private void affichageEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEvenement.fxml"));
            Parent root = loader.load();
            iv_logo.getScene().setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
