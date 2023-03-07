/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javafx.geometry.Insets;
import edu.esprit.entities.Activity;
import edu.esprit.services.ActivityService;
//import java.awt.Insets;

import java.io.File;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author chaym
 */
public class AfficherActivityController implements Initializable {

    @FXML
    private TableView<Activity> table;
    @FXML
    private TableColumn<Activity, String> descrip_col;
    @FXML
    private TableColumn<Activity, String> categorie_col;
    @FXML
    private TableColumn<Activity, String> adresse_col;
    @FXML
    private TableColumn<Activity, String> num_col;
    @FXML
    private TableColumn<Activity, String> date_col;
    @FXML
    private TableColumn<Activity, String> gouver_col;
    @FXML
    private TableColumn<Activity, String> nbrjaime_col;
    @FXML
    private TableColumn<Activity, String> aimer_col;
    @FXML
    private TableColumn<Activity, String> supprime_col;
    @FXML
    private TableColumn<Activity, String> modifier_col;
    @FXML
    private TextField tf_gouvernorat;
    @FXML
    private ImageView iv_logo;
    @FXML
    private ImageView iv_admin;
      ActivityService as = new ActivityService();
      ObservableList<Activity> actslist = as.displayActivities();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          File logof = new File("images/logo.png");
        Image logom = new Image(logof.toURI().toString());
        
          File adminf = new File("images/admin.png");
        Image adminm = new Image(adminf.toURI().toString());
        iv_admin.setImage(adminm);
        iv_logo.setImage(logom);
        showActivity(as.displayActivities());
        tf_gouvernorat.textProperty().addListener((observable, oldValue, newValue) -> {
            // Use a stream to search the list of events

            List<Activity> searchResults = actslist.stream()
                    .filter(event -> event.getDescription().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());
            ObservableList<Activity> search = FXCollections.observableArrayList();

            search.addAll(searchResults);
            showActivity(search);
        });
        // TODO
    }    

    @FXML
    private void GoToAjouterActivity(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterActivity.fxml"));
            Parent root = loader.load();
            table.getScene().setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showActivity(ObservableList<Activity> ActivityList) {
       table.setItems(ActivityList);
        descrip_col.setCellValueFactory(new PropertyValueFactory<Activity, String>("description"));
        categorie_col.setCellValueFactory(new PropertyValueFactory<Activity, String>("categorie"));
        adresse_col.setCellValueFactory(new PropertyValueFactory<Activity, String>("adresse"));
         num_col.setCellValueFactory(new PropertyValueFactory<Activity, String>("num_contact"));
        date_col.setCellValueFactory(new PropertyValueFactory<Activity, String>("date"));
        gouver_col.setCellValueFactory(new PropertyValueFactory<Activity, String>("gouvernorat"));
        
          Callback<TableColumn<Activity, String>, TableCell<Activity, String>> deletecellFactory = (TableColumn<Activity, String> param) -> {
            final TableCell<Activity, String> cell = new TableCell<Activity, String>() {
          
            
          
                    
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
                             Activity selectedActivity = getTableView().getItems().get(getIndex());
                           
                                    //alert
                                    if (selectedActivity != null) {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.initStyle(StageStyle.UTILITY);
                                        alert.setTitle("Supprimer l'activité");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Etes vous sur de vouloir supprimer l'activité ?");
                                        Optional<ButtonType> result = alert.showAndWait();
                                        if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                                        {
                                            
                                            if (as.DeletePost(selectedActivity.getIdActivity())) {
                                                Alert alerts = new Alert(Alert.AlertType.INFORMATION);
                                                alerts.initStyle(StageStyle.UTILITY);
                                                alerts.setTitle("Success");
                                                alerts.setHeaderText(null);
                                                alerts.setContentText("Activity a été supprimée");
                                                alerts.showAndWait();
                                                showActivity(as.displayActivities());
                                            } else {
                                                Alert alertz = new Alert(Alert.AlertType.ERROR);
                                                alertz.initStyle(StageStyle.UTILITY);
                                                alertz.setTitle("Error");
                                                alertz.setHeaderText(null);
                                                alertz.setContentText("Activity n'a pas été supprimée");
                                                alertz.showAndWait();
                                            }
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
        
   Callback<TableColumn<Activity, String>, TableCell<Activity, String>> modifCellFactory = (TableColumn<Activity, String> param) -> {
            final TableCell<Activity, String> cell = new TableCell<Activity, String>() {
             
                   public void updateItem(String item, boolean empty) {
                       super.updateItem(item, empty);
                       //that cell created only on non-empty rows
                       if (empty) {
                           setGraphic(null);
                           setText(null);
                           
                       } else {
                           
                           Button edtIcon = new Button();
                           edtIcon.setText("Modifier");
                           
                         
                               edtIcon.setOnMouseClicked((MouseEvent event) -> {
                                Activity selectedActivity = getTableView().getItems().get(getIndex());
                               
                               try {
                                   FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierActivity.fxml"));
                                   Parent root = loader.load();
                                   ModifierActivityController ActivityController = loader.getController();
                                   ActivityController.setActivity(selectedActivity);
                                   table.getScene().setRoot(root);
                                   
                               } catch (Exception ex) {
                                   ex.printStackTrace();
                               }
                               
                          // });
                               
                           
                       
                   
                   
               });
                           HBox managebtn = new HBox(edtIcon);
                           managebtn.setStyle("-fx-alignment:center");
                           HBox.setMargin(edtIcon, new Insets(2, 2, 0, 3));
                           
                           setGraphic(managebtn);
                           
                           setText(null);

                              // @Override
                             //  public void handle(javafx.scene.input.MouseEvent event) {
                                 //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                               }

                   }
               };
                       
                   
                   
               
               return cell;
           
       };
        supprime_col.setCellFactory(deletecellFactory);
        aimer_col.setCellFactory(likeCellFactory);
        
        nbrjaime_col.setCellFactory(cellFactory2);
        modifier_col.setCellFactory(modifCellFactory);

    }
      private void goToEditActivity(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierActivity.fxml"));
            Parent root = loader.load();
            table.getScene().setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

   
    }
     