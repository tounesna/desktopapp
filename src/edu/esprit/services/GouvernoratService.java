/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Gouvernorat;
import edu.esprit.entities.Region;
import edu.esprit.utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author garbo
 */
public class GouvernoratService {


    public ObservableList<Gouvernorat> getAllGouvernorat() {
        //instance liste de Evenement
        ObservableList<Gouvernorat> list = FXCollections.observableArrayList();
        //ecrire requete sql pour recuperer les terrains
        String req = "SELECT * FROM `gouvernorat`";

        try {
            //creation de statement
            Statement st = MyConnection.getInstance().cnx.createStatement();
            // executer la requette et recuperer le resultat 
            ResultSet rs = st.executeQuery(req);
            // tant que on a un resultat
            while (rs.next()) {
                Gouvernorat gov = new Gouvernorat();
                gov.setId(rs.getInt("id"));
                gov.setName(rs.getString("name"));
                //ajouter a la liste
                list.add(gov);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }


}
