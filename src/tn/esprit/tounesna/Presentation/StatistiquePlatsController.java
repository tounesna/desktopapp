/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tounesna.Presentation;

import Entite.Utilisateur;
import Service.PlatService;
import Service.UtilisateurCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class StatistiquePlatsController implements Initializable {

    @FXML
    private PieChart pie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
 List<String> listAges = new ArrayList<>();
 
  List<Utilisateur> listusers = new ArrayList<>();
  
        try {
             UtilisateurCRUD cs = new UtilisateurCRUD();
            listusers = cs.AfficherAllUsers();
        } catch (SQLException ex) {
        }
for (Utilisateur s : listusers){
    listAges.add(s.getAdresse());
    
}
        
        Map<String, Integer> occurences = new HashMap<>();
        
        for (String element : listAges) {
            if (occurences.containsKey(element)) {
                int count = occurences.get(element);
                occurences.put(element, count + 1);
            } else {
                occurences.put(element, 1);
            }
        }
        
        System.out.println("Nombre d'utilisateurs par adresse: ");
        for (Map.Entry<String, Integer> entry : occurences.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
             ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(entry.getKey(), entry.getValue())
                       );
              pie.getData().addAll(pieChartData);
                   pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), "  ", data.pieValueProperty()
                        )
                )
        );
        }
    }    
    
}
