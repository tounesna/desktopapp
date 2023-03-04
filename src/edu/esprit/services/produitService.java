/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.produit_artisanal;
import edu.esprit.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Statement;


/**
 *
 * @author user
 */
public class produitService {
    public boolean ajoutPod(produit_artisanal Prod) {
        boolean result = false;
        try {
            String req = "INSERT INTO `produit_artisanal`( `description`, `type`, `gouvernorat`) VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = MyConnection.getInstance().cnx.prepareStatement(req);
            ps.setString(1, Prod.getDescription());
            ps.setString(2, Prod.getType().name());
            ps.setInt(3, Prod.getGouvernorat());
            

            ps.executeUpdate();
            System.out.println("Produit artisanal ajouter avec succes+ ");
            result = true;

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return result;

    }
    public ObservableList<produit_artisanal> displayProd() {
        //instance liste de Evenement
        ObservableList<produit_artisanal> list = FXCollections.observableArrayList();
        //ecrire requete sql pour recuperer les terrains
        String req = "select * from produit_artisanal";

        try {
            //creation de statement
            Statement st = MyConnection.getInstance().cnx.createStatement();
            // executer la requette et recuperer le resultat 
            ResultSet rs = st.executeQuery(req);
            // tant que on a un resultat
            while (rs.next()) {
                produit_artisanal Prod = new produit_artisanal();
                Prod.set(rs.getInt("idev"));
                Prod.setRegion(Region.valueOf(rs.getString("region")));
                Prod.setGouvernorat(rs.getInt("gouvernorat"));
                Prod.setDescription(rs.getString("description"));
                Prod.setDatev(rs.getString("datev"));
                evt.setTitre(rs.getString("titre"));
                evt.setAuteur(rs.getInt("auteur"));

                //ajouter a la liste
                list.add(evt);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
}
