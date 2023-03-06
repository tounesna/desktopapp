/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Utilisateur;
import Entite.plat;
import Util.MyConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class PlatService {
    
 Connection cnx2;
      private Statement ste;
    
      public PlatService() throws SQLException{
           cnx2 = MyConnection.getinstance().getCnx();
    ste = cnx2.createStatement();
    }
    
  
    
    
public void ajouter_plat(plat u) throws NoSuchAlgorithmException {
    String req = "INSERT INTO plat (nomplat, recette, chef, region) VALUES (?, ?, ?, ?)";
    try {
        PreparedStatement ste = cnx2.prepareStatement(req);
        ste.setString(1, u.getNomplat());
        ste.setString(2, u.getRecette());
        ste.setString(3, u.getChef());
        ste.setString(4, u.getRegion());

        ste.executeUpdate();
       
    } catch (SQLException ex) {
    }
}
     public List<plat> AfficherAllplat() throws SQLException {

        List<plat> plats = new ArrayList<>();
        String req = "select * from plat ";
        Statement stm = cnx2.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            plat u = new plat(rst.getInt("idplat")
                    , rst.getString("nomplat")
                    , rst.getString("recette")
                    , rst.getString("chef")
                 , rst.getString("region")
                   );
            plats.add(u);
        }
        return plats;
    }

public void Supprimerplat(plat u) throws SQLException {
        String req = "DELETE FROM plat WHERE idplat  =?";
        try {
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setInt(1, u.getIdplat());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }


      public void modifierplat(plat u) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE plat SET "
                + " nomplat='"+u.getNomplat()+"'"
           
                      + ", recette='"+u.getRecette()+"'"
     
                
                           + ", chef='"+u.getChef()+"'"

                + ", region='"+u.getRegion()+"' where idplat  = "+u.getIdplat()+"";
                
        
        Statement stm = cnx2.createStatement();
        stm.executeUpdate(req);
    } 
      
       
   

  }

            



