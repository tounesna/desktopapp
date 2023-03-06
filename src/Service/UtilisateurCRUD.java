/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entite.Utilisateur;
import Util.MyConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;





/**
 *
 * @author ASUS
 */
public class UtilisateurCRUD  {
      Connection cnx2;
      private Statement ste;
    
      public UtilisateurCRUD() throws SQLException{
           cnx2 = MyConnection.getinstance().getCnx();
    ste = cnx2.createStatement();
    }
    
       public String hashmdp (String mdp) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        StringBuffer hexString = new StringBuffer();
     for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
          if(hex.length()==1) hexString.append('0');
          hexString.append(hex);
     }
     
    
       return hexString.toString();
    }
    
    
public void ajouter_utilisateur(Utilisateur u) throws NoSuchAlgorithmException {
    String req = "INSERT INTO utilisateur (cin, nom, prenom, adresse, age, email, password, roles) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement ste = cnx2.prepareStatement(req);
        ste.setInt(1, u.getCin());
        ste.setString(2, u.getNom());
        ste.setString(3, u.getPrenom());
        ste.setString(4, u.getAdresse());
        ste.setInt(5, u.getAge());
        ste.setString(6, u.getEmail());
        ste.setString(7, hashmdp ( u.getPassword()));
        ste.setString(8, u.getRoles()); // Ajout de la valeur pour la colonne "roles"
        ste.executeUpdate();
        System.out.println("Utilisateur ajouté !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
   //int IdUser, int cin, String Nom, String prenom, String adresse, int age, String email, String password, String roles) {

     public List<Utilisateur> AfficherAllUsers() throws SQLException {

        List<Utilisateur> Users = new ArrayList<>();
        String req = "select * from utilisateur ";
        Statement stm = cnx2.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Utilisateur u = new Utilisateur(rst.getInt("idUser")
                    , rst.getInt("cin")
                    , rst.getString("nom")
                    , rst.getString("prenom")
                    , rst.getString("adresse")
                     , rst.getInt("age")
                    , rst.getString("email")
                    , rst.getString("password")
                    , rst.getString("roles")
                   );
            Users.add(u);
        }
        return Users;
    }
    public ObservableList<Utilisateur> displayEvent() throws SQLException {
        //instance liste de Evenement
        ObservableList<Utilisateur> list = FXCollections.observableArrayList();
        //ecrire requete sql pour recuperer les terrains
        String req = "select * from utilisateur";
    Statement stm = cnx2.createStatement();
        ResultSet rst = stm.executeQuery(req);
        try {
    
            // tant que on a un resultat
            while (rst.next()) {
            Utilisateur u = new Utilisateur(rst.getInt("idUser")
                    , rst.getInt("cin")
                    , rst.getString("nom")
                    , rst.getString("prenom")
                    , rst.getString("adresse")
                     , rst.getInt("age")
                    , rst.getString("email")
                    , rst.getString("password")
                    , rst.getString("roles")
                   );

                //ajouter a la liste
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

public void SupprimerUser(Utilisateur u) throws SQLException {
        String req = "DELETE FROM utilisateur WHERE idUser =?";
        try {
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setInt(1, u.getIdUser());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    public boolean DeleteUser(int idUser) {
        boolean result = false;
        try {
            String req = " DELETE FROM `utilisateur` WHERE `idUser` =" + idUser;

            PreparedStatement ps = cnx2.prepareStatement(req);

            ps.executeUpdate();
            System.out.println("idUser supprime avec succes");
            result = true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

/*
      ste.setInt(1, u.getCin());
        ste.setString(2, u.getNom());
        ste.setString(3, u.getPrenom());
        ste.setString(4, u.getAdresse());
        ste.setInt(5, u.getAge());
        ste.setString(6, u.getEmail());
        ste.setString(7, hashmdp ( u.getPassword()));
        ste.setString(8, u.getRoles()); // Ajout de la valeur pour la colonne "roles"
        ste.executeUpdate();
        System.out.println("Utilisateur ajouté !");
*/
      public void modifierUser(Utilisateur u) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE utilisateur SET "
                + " cin='"+u.getCin()+"'"
                + ", nom='"+u.getNom()+"'"
                      + ", prenom='"+u.getPrenom()+"'"
                     + ", adresse='"+u.getAdresse()+"'"
                     + ", age='"+u.getAge()+"'"
                     + ", email='"+u.getEmail()+"'"
                
                           + ", password='"+ hashmdp (u.getPassword())+"'"

                + ", roles='"+u.getRoles()+"' where idUser  = "+u.getIdUser()+"";
                
        
        Statement stm = cnx2.createStatement();
        stm.executeUpdate(req);
    } 
      
       
   

  }

            


