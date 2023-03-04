/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.MyConnection;
import entites.Hotel;
import entites.Region;
import entites.nbetoile;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author samar
 */
public class HotelService {

    Connection myconnex = MyConnection.getInstance().getConnection();

    public boolean addHotel(Hotel h) {
        boolean res = false;
        try {
            String req = "INSERT INTO `hotel`(`nomhotel`, `nb_etoile`, `site`, `photos`,`nb_recommandation`,`gouvernorat`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = myconnex.prepareStatement(req);
            ps.setString(1, h.getNom_hotel());
            ps.setString(2, h.getNb_etoile().name());
            ps.setString(3, h.getSite());
            ps.setString(4, h.getPhotos());
            ps.setInt(5, h.getNb_recommandation());
            ps.setInt(6, h.getGouvernorat());
            
            ps.executeUpdate();
            System.out.println("hotel ajouté avec succes");
            res = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return res;
    }

    public boolean updateHotel(Hotel h) {
        boolean res = false;
        try {

            String requete = "UPDATE `hotel` SET "
                    + "`nomhotel`='" + h.getNom_hotel()
                    + "',`nb_etoile`='" + h.getNb_etoile().name()
                    + "',`site`='" + h.getSite()
                    + "',`photos`='" + h.getPhotos()
                    + "',`nb_recommandation`='" + h.getNb_recommandation()
                    + "',`gouvernorat`='" + h.getGouvernorat()
                    
                    + "' WHERE `idh`=" + h.getIdh() + "";
            Statement st = myconnex.createStatement();
            st.executeUpdate(requete);
            res = true;
            System.out.println("Mise à jour effectuée avec succès");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public boolean DeleteHotel(int idh) {
        boolean res = false;
        try {
            String req = " DELETE FROM `hotel` WHERE `idh` =" + idh;

            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("hotel supprimé avec succes");
            res = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return res;
    }
     public ObservableList<Hotel> displayHotel() {
        //instance liste de Evenement
        ObservableList<Hotel> list = FXCollections.observableArrayList();
        //ecrire requete sql pour recuperer les terrains
        String req = "select * from hotel";

        try {
            //creation de statement
            Statement st = myconnex.createStatement();
            // executer la requette et recuperer le resultat 
            ResultSet rs = st.executeQuery(req);
            // tant que on a un resultat
            while (rs.next()) {
                Hotel h = new Hotel();
                h.setIdh(rs.getInt("idh"));
                h.setNom_hotel(rs.getString("Nom_hotel"));
                h.setNb_etoile(nbetoile.valueOf(rs.getString("nb_etoile")));
                h.setSite(rs.getString("site"));
                h.setPhotos(rs.getString("photos"));
                h.setNb_recommandation(rs.getInt("nb_recommandation"));
                h.setGouvernorat(rs.getInt("gouvernorat"));
                
                list.add(h);
                System.out.println(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    public ObservableList<Hotel> displayHotelByGouvernorat(String idg) {
        ObservableList<Hotel> list = FXCollections.observableArrayList();
        String req = "select * from hotel where idg=+" + idg + " ORDER BY `idg` DESC";
        try {
            Statement st = myconnex.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Hotel h = new Hotel();
                h.setIdh(rs.getInt("idh"));
                h.setNom_hotel(rs.getString("Nom_hotel"));
                h.setNb_etoile(nbetoile.valueOf(rs.getString("nb_etoile")));
                h.setSite(rs.getString("site"));
                h.setPhotos(rs.getString("photos"));
                h.setNb_recommandation(rs.getInt("nb_recommandation"));
                h.setGouvernorat(rs.getInt("gouvernorat"));
                
                list.add(h);
                System.out.println(h);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
}
