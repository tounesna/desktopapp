/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.entities.Gouvernorat;
import edu.esprit.services.ActivityService;
import edu.esprit.services.GouvernoratService;
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
import javafx.scene.control.ButtonType;
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
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author chaym
 */
public class AfficherGouvernoratController implements Initializable {

    @FXML
    private ImageView iv_admin;
    @FXML
    private ImageView iv_logo;
    @FXML
    private TableView<Gouvernorat> table;
    @FXML
    private TableColumn<Gouvernorat, String> col_gouver;
    @FXML
    private TableColumn<Gouvernorat, String> col_region;
    @FXML
    private TableColumn<Gouvernorat, String> col_modifier;
    @FXML
    private TableColumn<Gouvernorat, String> col_supprimer;
    @FXML
    private TextField tf_region;
    
    
    GouvernoratService gs = new GouvernoratService();
    ObservableList<Gouvernorat> govlist = gs.displayGouvernorats();
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
        showGouvernorats(gs.displayGouvernorats());
        tf_region.textProperty().addListener((observable, oldValue, newValue) -> {
            // Use a stream to search the list of events

            List<Gouvernorat> searchResults = govlist.stream()
                    .filter(event -> event.getRegion_gouver().name().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());
            ObservableList<Gouvernorat> search = FXCollections.observableArrayList();

            search.addAll(searchResults);
            showGouvernorats(search);
        });// TODO
    }    

    @FXML
    private void GoToAjouterGov(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterGouvernorat.fxml"));
            Parent root = loader.load();
            table.getScene().setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showGouvernorats(ObservableList<Gouvernorat> GouvernoratsList) {
       table.setItems(GouvernoratsList);
        col_gouver.setCellValueFactory(new PropertyValueFactory<Gouvernorat, String>("nom_gouver"));
        col_region.setCellValueFactory(new PropertyValueFactory<Gouvernorat, String>("region_gouver"));
        Callback<TableColumn<Gouvernorat, String>, TableCell<Gouvernorat, String>> deletecellFactory = (TableColumn<Gouvernorat, String> param) -> {
            final TableCell<Gouvernorat, String> cell = new TableCell<Gouvernorat, String>() {
          
            
          
                    
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
                             Gouvernorat selectedGouvernorat = getTableView().getItems().get(getIndex());
                           
                                    //alert
                                    if (selectedGouvernorat!= null) {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.initStyle(StageStyle.UTILITY);
                                        alert.setTitle("Supprimer gouvernorat");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Etes vous sur de vouloir supprimer le gouvernorat ?");
                                        Optional<ButtonType> result = alert.showAndWait();
                                        if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                                        {
                                            
                                            if (gs.DeleteGouvernorat(selectedGouvernorat.getId_gouver())) {
                                                Alert alerts = new Alert(Alert.AlertType.INFORMATION);
                                                alerts.initStyle(StageStyle.UTILITY);
                                                alerts.setTitle("Success");
                                                alerts.setHeaderText(null);
                                                alerts.setContentText("Gouvernorat a été supprimée");
                                                alerts.showAndWait();
                                                showGouvernorats(gs.displayGouvernorats());
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
                                        alertz.setContentText("selectionnez une gouvernorat");
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
    Callback<TableColumn<Gouvernorat, String>, TableCell<Gouvernorat, String>> modifCellFactory = (TableColumn<Gouvernorat, String> param) -> {
            final TableCell<Gouvernorat, String> cell = new TableCell<Gouvernorat, String>() {
             
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
                                  Gouvernorat selectedGouvernorat = getTableView().getItems().get(getIndex());
                               
                               try {
                                   FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierGouvernorat.fxml"));
                                   Parent root = loader.load();
                                   ModifierGouvernoratController GouvernoratController = loader.getController();
                                   GouvernoratController.setGouvernorat(selectedGouvernorat);
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
        col_supprimer.setCellFactory(deletecellFactory);
        
        col_modifier.setCellFactory(modifCellFactory);

    } 
       private void goToEditGouvernorant(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierGouvernorat.fxml"));
            Parent root = loader.load();
            table.getScene().setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
