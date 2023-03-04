/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Gouvernorat;
import edu.esprit.entities.Region;
import edu.esprit.services.EvenementService;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Home extends Application {

    @Override
    public void start(Stage primaryStage) {

      
            try {
                Parent root = FXMLLoader.load(getClass().getResource("AfficherEvenement.fxml")); // list ajout affichage pour admin
                Scene scene = new Scene(root);
                primaryStage.setTitle("Tounesna");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      /*  EvenementService ec=new EvenementService();
           Evenement evt = new Evenement();
           evt.setIdev(3);
            evt.setTitre("xxxx");
            evt.setDescription("xxx");
            evt.setDatev("2023-02-06 00:00:00");
            evt.setRegion(Region.CENTRE);
            evt.setGouvernorat(Gouvernorat.BIZERTE);
            evt.setAuteur(1);
            ec.updateEvenement(evt);*/
      
     EvenementService ec=new EvenementService();
      /* ec.rateEvent(1, 9, 5);
      ec.avgRatesPerEvent(9);*/
      ec.getRateEvent(1,9);
        launch(args);
    }

}
