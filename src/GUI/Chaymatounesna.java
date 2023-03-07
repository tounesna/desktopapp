/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.entities.Activity;
import edu.esprit.entities.CategorieActivity;
import edu.esprit.entities.Gouvernorat;
import edu.esprit.entities.Region;
import edu.esprit.services.ActivityService;
import edu.esprit.services.GouvernoratService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javax.swing.Spring.height;
//import static javafx.application.Application.launch;

/**
 *
 * @author chaym
 */
public class Chaymatounesna extends Application {
    

    /**
     * @param args the command line arguments
     */
 

    @Override
    public void start(Stage primaryStage) throws Exception {
         try {
         //Parent root = FXMLLoader.load(getClass().getResource("afficherGouvernorat.fxml"));
          //Parent root = FXMLLoader.load(getClass().getResource("ajouterActivity.fxml")); 
         Parent root = FXMLLoader.load(getClass().getResource("afficherActivity.fxml"));
         //Parent root = FXMLLoader.load(getClass().getResource("afficherActivityUtilisateur.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Tounesna");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            System.out.println(ex);
       } //To change body of generated methods, choose Tools | Templates.
     }
       public static void main(String[] args) {
        // TODO code application logic here
          // ActivityService ps = new ActivityService();
    //GouvernoratService ps = new GouvernoratService();
      //  Gouvernorat g = new Gouvernorat();
        // g.setNom_gouver("Nabeul");
        // g.setRegion_gouver(Region.NORD);
         //ps.addGouvernorat(g);
        // g.setId_gouver(1);
        // ps.updateGouvernorat(g);
       //  ps.DeleteGouvernorat(1);
       // ps.displayPosts();
         //ActivityService ps = new ActivityService();
        /*Activity p = new Activity();
            p.setDescription("you will never forget this trip, Come and join us");
            p.setAdresse("korbaaa");
            p.setNum_contact("55555");
            p.setGouvernorat(1);
            p.setAuteur(1);
           
            p.setCategorie(CategorieActivity.Balade_en_bateau);
            p.setDate("2023-02-06 00:00:00");
            p.setImage("imggg");
         p.setIdActivity(1);*/
      // ps.addActivity(p); 
          // ps.updatePost(p);
      // ps.DeletePost(1);
        launch(args);
    }
}

      

    