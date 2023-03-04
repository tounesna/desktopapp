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
import java.text.DecimalFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author garbo
 */
public class EvenementService {

    public boolean ajoutEvent(Evenement evt) {
        boolean result = false;
        try {
            String req = "INSERT INTO `evenement`(`region`, `gouvernorat`, `description`, `datev`, `auteur`, `titre`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = MyConnection.getInstance().cnx.prepareStatement(req);
            ps.setString(1, evt.getRegion().name());
            ps.setInt(2, evt.getGouvernorat());
            ps.setString(3, evt.getDescription());
            ps.setString(4, evt.getDatev());
            ps.setInt(5, evt.getAuteur());
            ps.setString(6, evt.getTitre());

            ps.executeUpdate();
            System.out.println("Evenement ajouter avec succes+ ");
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
            Statement st = MyConnection.getInstance().cnx.createStatement();
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

            PreparedStatement ps = MyConnection.getInstance().cnx.prepareStatement(req);

            ps.executeUpdate();
            System.out.println("evenement supprime avec succes");
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

            st = MyConnection.getInstance().cnx.createStatement();

            st.executeUpdate(requete);
            result = true;
            System.out.println("Mise à jour effectuée avec succès");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;

    }
    
    
    
    

    public double avgRatesPerEvent(int id_event) {
        double avg = 0.0;
        String req = "select AVG(rate) from rate_evenement where id_event=" + id_event;
        try {

            PreparedStatement pst = MyConnection.getInstance().cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            rs.next();
            DecimalFormat decimalFormat = new DecimalFormat("#.##");

            avg = Double.parseDouble(decimalFormat.format(rs.getDouble("AVG(rate)")));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return avg;
    }

    public ObservableList<Evenement> displayEventOrdredByRate() {
        //instance liste de Evenement
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        //ecrire requete sql pour recuperer les terrains
        String req = "SELECT e.* FROM evenement e LEFT JOIN rate_evenement r ON e.idev = r.id_event GROUP BY e.idev ORDER BY r.rate DESC;";

        try {
            //creation de statement
            Statement st = MyConnection.getInstance().cnx.createStatement();
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

    public boolean checkIfUserAlreadyRated(int userId, int id_event) {
        boolean isRated = false;
        String req = "SELECT count(*) FROM `rate_evenement` where id_user =? and id_event=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().cnx.prepareStatement(req);
            pst.setInt(1, userId);
            pst.setInt(2, id_event);
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (rs.getInt("COUNT(*)") != 0) {
                isRated = true;
            }
            //  System.out.println("commentaire_likes isLiked or not ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return isRated;
    }

    public void rateEvent(int currentUser, int id_event, double rate) {
        String req = "INSERT INTO `rate_evenement`(`id_user`, `id_event`, `rate`) VALUES (?,?,?)";
        try {

            PreparedStatement pst = MyConnection.getInstance().cnx.prepareStatement(req);
            pst.setInt(1, currentUser);
            pst.setInt(2, id_event);
            pst.setDouble(3, rate);
            pst.executeUpdate();
            System.out.println("Comm rated with " + rate);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateRateEvent(int currentUser, int id_event, double rate) {

        String req = "UPDATE `rate_evenement` SET `rate`=? where id_user =? and id_event=?";
        try {

            PreparedStatement pst = MyConnection.getInstance().cnx.prepareStatement(req);
            pst.setDouble(1, rate);
            pst.setInt(2, currentUser);
            pst.setInt(3, id_event);

            pst.executeUpdate();
            System.out.println("Comm rated with " + rate);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public double getRateEvent(int currentUser, int id_event) {
        double rate = 0.0;
        String req = "SELECT `rate` FROM `rate_evenement` WHERE id_user=? and id_event=?;";
        try {

            PreparedStatement pst = MyConnection.getInstance().cnx.prepareStatement(req);
            pst.setInt(1, currentUser);
            pst.setInt(2, id_event);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                rate = rs.getInt("rate");

            }
            System.out.println(rate);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rate;
    }

}
