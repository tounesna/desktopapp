/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Activity;
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
 * @author chaym
 */
public class GouvernoratService {
     public boolean addGouvernorat(Gouvernorat gouv) {
        boolean resultat = false;

        try {
            String req = "INSERT INTO `gouvernorats`(`nom_gouver`, `region`)"
                    + " VALUES (?,?)";
            PreparedStatement ps;
            ps = MyConnection.getInstance().cnx.prepareStatement(req);
            ps.setString(1, gouv.getNom_gouver());
             ps.setString(2, gouv.getRegion_gouver().name());
          
            
            ps.executeUpdate();
            System.out.println("Gouvernorat est ajoutée avec succes+ ");
            resultat = true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return resultat;

    }
    public ObservableList<Gouvernorat> displayGouvernorats() {
        
        ObservableList<Gouvernorat> list = FXCollections.observableArrayList();
        String req = "select * from gouvernorats";

        try {
            Statement st = MyConnection.getInstance().cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
              Gouvernorat gouv = new Gouvernorat();
                gouv.setId_gouver(rs.getInt("id_gouver"));
                gouv.setNom_gouver(rs.getString("nom_gouver"));
                gouv.setRegion_gouver(Region.valueOf(rs.getString("region")));
                
                list.add(gouv);
                            System.out.println(gouv);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
      public boolean DeleteGouvernorat(int idGouvernorat) {
        boolean resultat = false;

        try {
            String req = " DELETE FROM `gouvernorats` WHERE `id_gouver` =" + idGouvernorat;

            PreparedStatement ps = MyConnection.getInstance().cnx.prepareStatement(req);

            ps.executeUpdate();
            System.out.println("gouvernorat est supprimée avec succes");
            resultat = true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return resultat;
    }
       public boolean updateGouvernorat(Gouvernorat gouv) {
        boolean res = false;
        try {
            String requete = "UPDATE `gouvernorats` SET "+
                    "`nom_gouver`='" + gouv.getNom_gouver()+ "'"+
                    
                     ",region='" + gouv.getRegion_gouver().name()+ "'"+
                     " where id_gouver= " + gouv.getId_gouver()+ " ;";
            Statement st;

            st = MyConnection.getInstance().cnx.createStatement();

            st.executeUpdate(requete);
            res = true;
        System.out.println("Mise à jour a été effectuée avec succès");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return res;

    }

    public void updatGouvernorat(Gouvernorat g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updatePost(Activity p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteGouvernorat(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
