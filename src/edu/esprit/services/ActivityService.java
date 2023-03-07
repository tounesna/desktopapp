/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Activity;
import edu.esprit.entities.CategorieActivity;
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
public class ActivityService {
    public boolean addActivity(Activity act) {
        boolean resultat = false;

        try {
            String req = "INSERT INTO `activities`(`description`, `adresse`, `num_contact`, `image`, `date`, `gouvernorat`, `auteur`, `type`)"
                    + " VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = MyConnection.getInstance().cnx.prepareStatement(req);
            ps.setString(1, act.getDescription());
            ps.setString(2, act.getAdresse());
            ps.setString(3, act.getNum_contact());
            ps.setString(4, act.getImage());
            ps.setString(5, act.getDate());
            ps.setInt(6, act.getGouvernorat());
            ps.setInt(7, act.getAuteur());
            ps.setString(8, act.getCategorie().name());
            
            ps.executeUpdate();
            System.out.println("Activité est ajoutée avec succes+ ");
            resultat = true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return resultat;

    }
    public ObservableList<Activity> displayActivities() {
        
        ObservableList<Activity> list = FXCollections.observableArrayList();
        String req = "select * from activities";

        try {
            Statement st = MyConnection.getInstance().cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Activity act = new Activity();
                act.setIdActivity(rs.getInt("id_activity"));
                act.setDescription(rs.getString("description"));
                act.setAdresse(rs.getString("adresse"));
                act.setNum_contact(rs.getString("num_contact"));
                 act.setImage(rs.getString("image"));
                 act.setDate(rs.getString("date"));
                  act.setGouvernorat(rs.getInt("gouvernorat"));
                  act.setAuteur(rs.getInt("auteur"));
               
                 act.setCategorie(CategorieActivity.valueOf(rs.getString("type")));
                
                list.add(act);
                            System.out.println(act);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
      public boolean DeletePost(int idActivity) {
        boolean resultat = false;

        try {
            String req = " DELETE FROM `activities` WHERE `id_activity` =" + idActivity;

            PreparedStatement ps = MyConnection.getInstance().cnx.prepareStatement(req);

            ps.executeUpdate();
            System.out.println("activité est supprimée avec succes");
            resultat = true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return resultat;
    }
       public boolean updatePost(Activity act) {
        boolean res = false;
        try {
            String requete = "UPDATE `activities` SET "+
                    "`description`='" + act.getDescription()+ "'"+
                    ",adresse='" + act.getAdresse()+ "'"+
                    ",num_contact='" + act.getNum_contact()+ "'"+
                    ",image='" + act.getImage()+ "'"+
                     ",date='" + act.getDate()+ "'"+
                   ",gouvernorat='" + act.getGouvernorat()+ "'"+
                     ",auteur='" + act.getAuteur()+ "'"+
                     ",type='" + act.getCategorie().name()+ "'"+
                     " where id_activity= " + act.getIdActivity()+ " ;";
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
     public void likeActivity(int currentUser, int postId) {
        String req = "insert into Activity_likes (user_Id,Activity_id) values"
                + "(?,?)";
        try {
            MyConnection connectionDB = new MyConnection();

            PreparedStatement pst = connectionDB.cnx.prepareStatement(req);
            pst.setInt(1, currentUser);
            pst.setInt(2, postId);
            pst.executeUpdate();
            System.out.println("Activity liked");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void dislikeActivity(int currentUser, int postId) {
        String req = "delete from Activity_likes where Activity_id=? and user_Id= ?";
        try {

            PreparedStatement pst = MyConnection.getInstance().cnx.prepareStatement(req);
            pst.setInt(1, postId);
            pst.setInt(2, currentUser);

            pst.executeUpdate();
            System.out.println("Activity disliked");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int countLikesPerAct(int ActId) {
        int likes = 0;
        String req = "select COUNT(*) from Activity_likes where Activity_id=?";
        try {

            PreparedStatement pst = MyConnection.getInstance().cnx.prepareStatement(req);
            pst.setInt(1, ActId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            likes = rs.getInt("COUNT(*)");
         //   System.out.println("commentaire_likes numbs ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return likes;
    }

    public boolean ActivityIsLiked(int userId, int ActId) {
        boolean isLiked = false;
        String req = "select COUNT(*) from Activity_likes where Activity_id=? and user_id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().cnx.prepareStatement(req);
            pst.setInt(1, ActId);
            pst.setInt(2, userId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (rs.getInt("COUNT(*)") != 0) {
                isLiked = true;
            }
          //  System.out.println("commentaire_likes isLiked or not ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return isLiked;
    }
  
    public ObservableList<Activity> displayActivityOrdredByNbLikes() {
        ObservableList<Activity> list = FXCollections.observableArrayList();
        String query = "SELECT activities.*, COUNT(activity_likes.id_like) AS num_likes FROM activities LEFT JOIN activity_likes ON activities.id_activity = activity_likes.Activity_id GROUP BY activities.id_activity ORDER BY num_likes DESC;";
        MyConnection connectionDB = new MyConnection();

        try {
            Statement st = MyConnection.getInstance().cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Activity act = new Activity();
                 act.setIdActivity(rs.getInt("id_activity"));
                act.setDescription(rs.getString("description"));
                act.setAdresse(rs.getString("adresse"));
                act.setNum_contact(rs.getString("num_contact"));
                 act.setImage(rs.getString("image"));
                 act.setDate(rs.getString("date"));
                  act.setGouvernorat(rs.getInt("gouvernorat"));
                  act.setAuteur(rs.getInt("auteur"));
               
                 act.setCategorie(CategorieActivity.valueOf(rs.getString("type")));
                list.add(act);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }   
}
