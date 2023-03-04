/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Gouvernorat;
import entites.Region;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author samar
 */
public class GouvernoratService {
       Connection myconnex = MyConnection.getInstance().getConnection();
    
    public ObservableList<Gouvernorat> getAllGouvernorat() {
        //instance liste de Evenement
        ObservableList<Gouvernorat> list = FXCollections.observableArrayList();
        //ecrire requete sql pour recuperer les terrains
        String req = "SELECT * FROM `gouvernorat`";

        try {
            //creation de statement
            Statement st = myconnex.createStatement();
            // executer la requette et recuperer le resultat 
            ResultSet rs = st.executeQuery(req);
            // tant que on a un resultat
            while (rs.next()) {
                Gouvernorat gov = new Gouvernorat();
                gov.setIdg(rs.getInt("idg"));
                gov.setNom_gouver(rs.getString("nom_gouver"));
                gov.setRegion(Region.valueOf(rs.getString("region")));
                //ajouter a la liste
                list.add(gov);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
}
