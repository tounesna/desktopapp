/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.services.EvenementService;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Region;
import edu.esprit.entities.Gouvernorat;
import java.io.File;

import java.net.URL;
import java.util.List;

import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author garbo
 */
public class AfficherEvenementController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Evenement> table;

    @FXML
    private TableColumn<Evenement, String> titre_col;
    @FXML
    private TableColumn<Evenement, String> reg_col;
    @FXML
    private TableColumn<Evenement, String> Gouvernorat_col;
    @FXML
    private TableColumn<Evenement, String> Description_col;
    @FXML
    private TableColumn<Evenement, String> date_col;
    @FXML
    private TableColumn<Evenement, String> auteur_col;

    EvenementService es = new EvenementService();
    ObservableList<Evenement> evtslist = es.displayEvent();

    @FXML
    private TableColumn<Evenement, String> avg_rate_col;
    @FXML
    private TableColumn<Evenement, String> rate_col;
    @FXML
    private TableColumn<Evenement, String> delete_col;
    @FXML
    private ImageView iv_logo;
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
        /*       initListDeroulante();*/
        showEvenements(es.displayEvent());

        tf_titre.textProperty().addListener((observable, oldValue, newValue) -> {
            // Use a stream to search the list of events

            List<Evenement> searchResults = evtslist.stream()
                    .filter(event -> event.getTitre().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());
            ObservableList<Evenement> search = FXCollections.observableArrayList();

            search.addAll(searchResults);
            showEvenements(search);
        });

    }

    private void showEvenements(ObservableList<Evenement> a) {
        table.setItems(a);
        titre_col.setCellValueFactory(new PropertyValueFactory<Evenement, String>("titre"));
        reg_col.setCellValueFactory(new PropertyValueFactory<Evenement, String>("region"));
        Gouvernorat_col.setCellValueFactory(new PropertyValueFactory<Evenement, String>("gouvernorat"));
        Description_col.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        auteur_col.setCellValueFactory(new PropertyValueFactory<Evenement, String>("auteur"));
        date_col.setCellValueFactory(new PropertyValueFactory<Evenement, String>("datev"));

        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> deletecellFactory = (TableColumn<Evenement, String> param) -> {
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
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
                            Evenement selectedEvenement = table.getSelectionModel().getSelectedItem();
                            //alert
                            if (selectedEvenement != null) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.initStyle(StageStyle.UTILITY);
                                alert.setTitle("Supprimer l'Evenement");
                                alert.setHeaderText(null);
                                alert.setContentText("Etes vous sur de vouloir supprimer l'Evenement ?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                                {

                                    if (es.DeleteEvenement(selectedEvenement.getIdev())) {
                                        Alert alerts = new Alert(Alert.AlertType.INFORMATION);
                                        alerts.initStyle(StageStyle.UTILITY);
                                        alerts.setTitle("Success");
                                        alerts.setHeaderText(null);
                                        alerts.setContentText("Evenement a été supprimée");
                                        alerts.showAndWait();
                                        showEvenements(es.displayEvent());
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

        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> ratingCellFactory = (TableColumn<Evenement, String> param) -> {
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button rateBtn = new Button();
                        rateBtn.setText("Rate");

                        rateBtn.setOnMouseClicked((MouseEvent event) -> {
                            Evenement selectedEvenement = table.getSelectionModel().getSelectedItem();
                            //alert
                            if (selectedEvenement != null) {

                                Dialog<Double> dialog = new Dialog<>();
                                ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

                                // Set the button types
                                VBox vbox = new VBox();
                                vbox.setSpacing(10);
                                vbox.setPadding(new Insets(20));

// Create a label for the reason
                                Label reasonLabel = new Label("Rating :");
                                vbox.getChildren().add(reasonLabel);

// Create a text area and add it to the VBox container
                                Rating rating;
                                if (es.checkIfUserAlreadyRated(1, selectedEvenement.getIdev())) {
                                    rating = new Rating(5, (int) es.getRateEvent(1, selectedEvenement.getIdev()));

                                } else {
                                    rating = new Rating(5, 0);
                                }
                                VBox.setMargin(rating, new Insets(10, 0, 0, 0)); // set top margin of 10
                                vbox.getChildren().add(rating);

// Add the VBox container to the dialog pane
                                dialog.getDialogPane().setContent(vbox);

                                dialog.setResultConverter((ButtonType dialogButton) -> {
                                    if (dialogButton == okButtonType) {
                                        return rating.getRating();
                                    }
                                    return null;
                                });
                                dialog.setTitle("Rate cet evenement");

                                // Convert the result to a string when the OK button is clicked
                                // Show the dialog and wait for a response
                                Optional<Double> result = dialog.showAndWait();
                                String reason = "";
                                if (result.isPresent()) {
                                    if (result.get() != 0.0) {
                                        if (es.checkIfUserAlreadyRated(1, selectedEvenement.getIdev())) {
                                            es.updateRateEvent(1, selectedEvenement.getIdev(), result.get());

                                        } else {
                                            es.rateEvent(1, selectedEvenement.getIdev(), result.get());

                                        }
                                        showEvenements(es.displayEvent());
                                    }

                                }
                                dialog.getDialogPane().getChildren().clear();

                            } else {
                                Alert alertz = new Alert(Alert.AlertType.ERROR);
                                alertz.initStyle(StageStyle.UTILITY);
                                alertz.setTitle("Error");
                                alertz.setHeaderText(null);
                                alertz.setContentText("selectionnez un evenement");
                                alertz.showAndWait();
                            }
                        });

                        HBox managebtn3 = new HBox(rateBtn);
                        managebtn3.setStyle("-fx-alignment:center");
                        HBox.setMargin(rateBtn, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn3);

                        setText(null);

                    }
                }

            };
            return cell;
        };
        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> ratesCellFac = (TableColumn<Evenement, String> param) -> {
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Label avgRating = new Label();
                        avgRating.setText(es.avgRatesPerEvent(table.getItems().get(getIndex()).getIdev()) + "");

                        avgRating.setStyle("-fx-alignment:center");
                        setGraphic(avgRating);

                        setText(null);

                    }
                }

            };
            return cell;
        };
        delete_col.setCellFactory(deletecellFactory);
        rate_col.setCellFactory(ratingCellFactory);
        avg_rate_col.setCellFactory(ratesCellFac);

    }

    @FXML
    private void triParRate(ActionEvent event) {
        showEvenements(es.displayEventOrdredByRate());

    }

    @FXML
    private void goToEditEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEvenement.fxml"));
            Parent root = loader.load();
            table.getScene().setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goToAddEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutEvenement.fxml"));
            Parent root = loader.load();
            table.getScene().setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void cancelTri(ActionEvent event) {
        showEvenements(es.displayEvent());

    }
}
