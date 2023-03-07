/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.entities.Activity;
import edu.esprit.services.ActivityService;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author chaym
 */
public class AfficherActivityUtilisateurController implements Initializable {

    @FXML
    private ImageView iv_logo;
    @FXML
    private TableColumn<Activity, String> col_descrip;
    @FXML
    private TableColumn<Activity, String> col_categorie;
    @FXML
    private TableColumn<Activity, String> col_adresse;
    @FXML
    private TableColumn<Activity, String> col_numero;
    @FXML
    private TableColumn<Activity, String> col_date;
    @FXML
    private TableColumn<Activity, String> col_gouvernorat;
    @FXML
    private TableColumn<Activity, String> col_nbrjaime;
    @FXML
    private TableColumn<Activity, String> col_aimer;
    @FXML
    private TextField tf_categorie;
    ActivityService as = new ActivityService();
    ObservableList<Activity> actslist = as.displayActivities();
    @FXML
    private TableView<Activity> table;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         File logof = new File("images/logo.png");
        Image logom = new Image(logof.toURI().toString());
        iv_logo.setImage(logom);
        /*       initListDeroulante();*/
        showActivity(as.displayActivities());

        tf_categorie.textProperty().addListener((observable, oldValue, newValue) -> {
            // Use a stream to search the list of events

         List<Activity> searchResults = actslist.stream()
                    .filter(event -> event.getCategorie().name().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());
            ObservableList<Activity> search = FXCollections.observableArrayList();

            search.addAll(searchResults);
            showActivity(search);
        });
        // TODO
    }    


    private void showActivity(ObservableList<Activity> ActivityList) {
         table.setItems(ActivityList);
        col_descrip.setCellValueFactory(new PropertyValueFactory<Activity, String>("description"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<Activity, String>("categorie"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<Activity, String>("adresse"));
         col_numero.setCellValueFactory(new PropertyValueFactory<Activity, String>("num_contact"));
        col_date.setCellValueFactory(new PropertyValueFactory<Activity, String>("date"));
        col_gouvernorat.setCellValueFactory(new PropertyValueFactory<Activity, String>("gouvernorat"));
        Callback<TableColumn<Activity, String>, TableCell<Activity, String>> cellFactory2 = (TableColumn<Activity, String> param) -> {
            final TableCell<Activity, String> cell = new TableCell<Activity, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Label nbLikes = new Label();
                        nbLikes.setText(as.countLikesPerAct(table.getItems().get(getIndex()).getIdActivity()) + "");

                        nbLikes.setStyle("-fx-alignment:center");
                        setGraphic(nbLikes);

                        setText(null);

                    }
                }

            };
            return cell;
        };
  Callback<TableColumn<Activity, String>, TableCell<Activity, String>> likeCellFactory = (TableColumn<Activity, String> param) -> {
            final TableCell<Activity, String> cell = new TableCell<Activity, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button likebtn = new Button();
                        likebtn.setText("like");
                        // current user can like a com only once 

                        if (as.ActivityIsLiked(1, table.getItems().get(getIndex()).getIdActivity())) {
                            likebtn.setText("dislike");

                        }

                        likebtn.setOnMouseClicked((MouseEvent event) -> {
                            Activity selectedActivity = getTableView().getItems().get(getIndex());
                            //alert
                            if (selectedActivity != null) {

                                if (likebtn.getText().equals("like")) {
                                    as.likeActivity(1, selectedActivity.getIdActivity());
                                    showActivity(as.displayActivities());
                                    likebtn.setText("dislike");
                                } else {
                                    as.dislikeActivity(1, selectedActivity.getIdActivity());
                                    showActivity(as.displayActivities());
                                    likebtn.setText("like");
                                }

                            } else {
                                Alert alertz = new Alert(Alert.AlertType.ERROR);
                                alertz.initStyle(StageStyle.UTILITY);
                                alertz.setTitle("Error");
                                alertz.setHeaderText(null);
                                alertz.setContentText("selectionnez une activité");
                                alertz.showAndWait();
                            }
                        });

                        HBox managebtn2 = new HBox(likebtn);
                        managebtn2.setStyle("-fx-alignment:center");
                        HBox.setMargin(managebtn2, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn2);

                        setText(null);

                    }
                }

            };
            return cell;
        };
        col_aimer.setCellFactory(likeCellFactory);
        
        col_nbrjaime.setCellFactory(cellFactory2);
       
  //To change body of generated methods, choose Tools | Templates.
    }
     @FXML
    private void TriParNombreDeJaime(ActionEvent event) {
         showActivity(as.displayActivityOrdredByNbLikes());
    }
    }

    
    

