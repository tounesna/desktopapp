/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.esprit.tounesna.Presentation;

import Entite.plat;
import Service.PlatService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class PlatFrontController implements Initializable {
public ObservableList<plat> list;
    @FXML
    private Button Menu;
    @FXML
    private TextField inputRech;
    @FXML
    private TableView<plat> tableUser;
    @FXML
    private TableColumn<?, ?> nomplat;
    @FXML
    private TableColumn<?, ?> recette;
    @FXML
    private TableColumn<?, ?> chef;
    @FXML
    private TableColumn<?, ?> region;
    @FXML
    private Label labelid;
    @FXML
    private Button confirmer;
    @FXML
    private Hyperlink Deconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        ArrayList<plat> c = new ArrayList<>();
  
        try {
             PlatService pss = new PlatService();
            c = (ArrayList<plat>) pss.AfficherAllplat();
        } catch (SQLException ex) {
        }
   
        
        ObservableList<plat> obs2 = FXCollections.observableArrayList(c);
        tableUser.setItems(obs2);
        
         nomplat.setCellValueFactory(new PropertyValueFactory<>("nomplat"));
 recette.setCellValueFactory(new PropertyValueFactory<>("recette"));
  chef.setCellValueFactory(new PropertyValueFactory<>("chef"));
 region.setCellValueFactory(new PropertyValueFactory<>("region"));

         try {
               PlatService pss = new PlatService();
            list = FXCollections.observableArrayList(
                    pss.AfficherAllplat()
            );        
        
        
   FilteredList<plat> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super plat>) plats -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        String tostring = plats.getNomplat();
                        if (tostring.toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<plat> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableUser.comparatorProperty());
                tableUser.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    


    @FXML
    private void Menu(ActionEvent event) {
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void confirmer(ActionEvent event) {
    }

    @FXML
    private void Deconnexion(ActionEvent event) throws IOException {
                 Parent page1 = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
