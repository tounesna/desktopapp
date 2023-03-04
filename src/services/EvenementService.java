/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Evenement;
import entites.Region;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class EvenementService {
    Connection myconnex = MyConnection.getInstance().getConnection();
    public boolean ajoutEvent(Evenement evt) {
        boolean result = false;
        try {
            String req = "INSERT INTO `evenement`(`region`, `gouvernorat`, `description`, `datev`, `auteur`, `titre`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = myconnex.prepareStatement(req);
            ps.setString(1, evt.getRegion().name());
            ps.setInt(2, evt.getGouvernorat());
            ps.setString(3, evt.getDescription());
            ps.setString(4, evt.getDatev());
            ps.setInt(5, evt.getAuteur());
            ps.setString(6, evt.getTitre());

            ps.executeUpdate();
            System.out.println("Evenement ajouté avec succes+ ");
            result = true;

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return result;

    }
    
    public ObservableList<Evenement> displayEvent() {
        //instance liste de Evenement
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        //ecrire requete sql pour recuperer les terrains
        String req = "select * from evenement";

        try {
            //creation de statement
            Statement st = myconnex.createStatement();
            // executer la requette et recuperer le resultat 
            ResultSet rs = st.executeQuery(req);
            // tant que on a un resultat
            while (rs.next()) {
                Evenement evt = new Evenement();
                evt.setIdev(rs.getInt("idev"));
                evt.setRegion(Region.valueOf(rs.getString("region")));
                evt.setGouvernorat(rs.getInt("gouvernorat"));
                evt.setDescription(rs.getString("description"));
                evt.setDatev(rs.getString("datev"));
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

    public boolean DeleteEvenement(int idEvenement) {
        boolean result = false;
        try {
            String req = " DELETE FROM `evenement` WHERE `idev` =" + idEvenement;

            PreparedStatement ps = myconnex.prepareStatement(req);

            ps.executeUpdate();
            System.out.println("evenement supprimé avec succes");
            result = true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    public boolean updateEvenement(Evenement evenement) {
        boolean result = false;
        try {
            String requete = "UPDATE `evenement` SET region='" + evenement.getRegion()
                    + "',gouvernorat='" + evenement.getGouvernorat()
                    + "',titre='" + evenement.getTitre()
                    + "',description='" + evenement.getDescription()
                    + "',datev='" + evenement.getDatev()
                    + "',auteur='" + evenement.getAuteur() + "' WHERE `idev` =" + evenement.getIdev();
            Statement st;

            st =myconnex.createStatement();

            st.executeUpdate(requete);
            result = true;
            System.out.println("Mise à jour effectuée avec succès");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;

    }

    
}
