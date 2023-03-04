/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Commentaire;
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
public class CommentaireService {
    Connection myconnex = MyConnection.getInstance().getConnection();
      public boolean addCommentaire(Commentaire commentaire) {
        boolean res = false;
        try {
            String req = "INSERT INTO `commentaire`(`contenu`, `date`, `auteur`,`hotel`, `id_event`) VALUES (?,?,?,?,?)";
            PreparedStatement ps;
            ps = myconnex.prepareStatement(req);
            ps.setString(1, commentaire.getContent());
            ps.setString(2, commentaire.getDateajc());
            ps.setInt(3, commentaire.getAuteur());
            ps.setInt(4, commentaire.getHotel());
            ps.setInt(5, commentaire.getEvenement());

            ps.executeUpdate();
            System.out.println("commentaire ajouté avec succes");
            res = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return res;
    }

     public ObservableList<Commentaire> displayCommentaireByHotel(int idh) {
        ObservableList<Commentaire> list = FXCollections.observableArrayList();
        String req = "select * from commentaire where id_event=" + idh + " ORDER BY `id` DESC";
        try {
            Statement st = myconnex.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Commentaire comment = new Commentaire();
                comment.setIdcom(rs.getInt("idcom"));
                comment.setContent(rs.getString("contenu"));
                comment.setDateajc(rs.getString("dateajc"));
                comment.setAuteur(rs.getInt("auteur"));
                comment.setHotel(rs.getInt("id_hotel"));
                comment.setEvenement(rs.getInt("id_event"));
                list.add(comment);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
      public ObservableList<Commentaire> displayCommentaireByEvent(int idevt) {
        ObservableList<Commentaire> list = FXCollections.observableArrayList();
        String req = "select * from commentaire where id_event=" + idevt + " ORDER BY `id` DESC";
        

        try {
            Statement st = myconnex.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Commentaire comment = new Commentaire();
                comment.setIdcom(rs.getInt("idcom"));
                comment.setContent(rs.getString("contenu"));
                comment.setDateajc(rs.getString("dateajc"));
                comment.setAuteur(rs.getInt("auteur"));
                comment.setHotel(rs.getInt("id_hotel"));
                comment.setEvenement(rs.getInt("id_event"));
                list.add(comment);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
     public Commentaire getCommentaireById(int idcom) {
        String req = "select * from commentaire where idcom=" + idcom;
        
                Commentaire comment = new Commentaire();

        try {
            Statement st = myconnex.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                comment.setIdcom(rs.getInt("idcom"));
                comment.setContent(rs.getString("contenu"));
                comment.setDateajc(rs.getString("dateajc"));
                comment.setAuteur(rs.getInt("auteur"));
                comment.setHotel(rs.getInt("id_hotel"));
                comment.setEvenement(rs.getInt("id_event"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return comment;
    }

    public ObservableList<Commentaire> displayCommentaire() {
        ObservableList<Commentaire> list = FXCollections.observableArrayList();
        String req = "select * from commentaire";
        

        try {
            Statement st = myconnex.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Commentaire comment = new Commentaire();
                comment.setIdcom(rs.getInt("idcom"));
                comment.setContent(rs.getString("contenu"));
                comment.setDateajc(rs.getString("dateajc"));
                comment.setAuteur(rs.getInt("auteur"));
                comment.setHotel(rs.getInt("id_hotel"));
                comment.setEvenement(rs.getInt("id_event"));
                list.add(comment);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public boolean DeleteCommentaire(int idCommentaire) {
        boolean res = false;
        try {
            
            String req = " DELETE FROM `commentaire` WHERE `idcom` =" + idCommentaire;

            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("commentaire supprimé avec succes");
            res = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return res;
    }

    public boolean updateCommentaire(Commentaire commentaire) {
        boolean res = false;

        try {
            
            String requete = "UPDATE `commentaire` SET "
                    + "`contenu`='" + commentaire.getContent()
                    + "',`dateajc`='" + commentaire.getDateajc()
                    + "',`auteur`='" + commentaire.getAuteur()
                    + "',`id_hotel`='" + commentaire.getHotel()
                    + "',`id_event`='" + commentaire.getEvenement()
                    + "' WHERE `idcom`=" + commentaire.getIdcom() + "";
            Statement st = myconnex.createStatement();
            st.executeUpdate(requete);
            res = true;
            System.out.println("Mise à jour effectuée avec succès");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return res;
    }
    
    

    public void likeCommentaire(int currentUser, int comId) {
        String req = "insert into commentaire_likes (user_Id,com_id) values"
                + "(?,?)";
        try {
            

            PreparedStatement pst = myconnex.prepareStatement(req);
            pst.setInt(1, currentUser);
            pst.setInt(2, comId);
            pst.executeUpdate();
            System.out.println("Comm liked");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void dislikeCommentaire(int currentUser, int comId) {
        String req = "delete from commentaire_likes where com_id=? and user_Id= ?";
        try {

            PreparedStatement pst = myconnex.prepareStatement(req);
            pst.setInt(1, comId);
            pst.setInt(2, currentUser);

            pst.executeUpdate();
            System.out.println("Comm disliked");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int countLikesPerCom(int comId) {
        int likes = 0;
        String req = "select COUNT(*) from commentaire_likes where com_id=?";
        try {

            PreparedStatement pst = myconnex.prepareStatement(req);
            pst.setInt(1, comId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            likes = rs.getInt("COUNT(*)");
         //   System.out.println("commentaire_likes numbs ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return likes;
    }

    public boolean comIsLiked(int userId, int comId) {
        boolean isLiked = false;
        String req = "select COUNT(*) from commentaire_likes where com_id=? and user_id=?";
        try {
            PreparedStatement pst = myconnex.prepareStatement(req);
            pst.setInt(1, comId);
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

    public ObservableList<Commentaire> displayCommentaireOrdredByNbLikes() {
        ObservableList<Commentaire> list = FXCollections.observableArrayList();
        String query = "SELECT commentaire.*, COUNT(commentaire_likes.id_like) AS num_likes FROM commentaire LEFT JOIN commentaire_likes ON commentaire.id = commentaire_likes.com_id GROUP BY commentaire.id ORDER BY num_likes DESC;";
        

        try {
            Statement st = myconnex.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Commentaire comment = new Commentaire();
                comment.setIdcom(rs.getInt("idcom"));
                comment.setContent(rs.getString("contenu"));
                comment.setDateajc(rs.getString("dateajc"));
                comment.setAuteur(rs.getInt("auteur"));
                comment.setHotel(rs.getInt("id_hotel"));
                comment.setEvenement(rs.getInt("id_event"));
                list.add(comment);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public ObservableList<Commentaire> displayCommentaireOrdredByDate() {
        ObservableList<Commentaire> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM commentaire ORDER BY date DESC;";

        try {
            Statement st = myconnex.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Commentaire comment = new Commentaire();
                comment.setIdcom(rs.getInt("idcom"));
                comment.setContent(rs.getString("contenu"));
                comment.setDateajc(rs.getString("dateajc"));
                comment.setAuteur(rs.getInt("auteur"));
                comment.setHotel(rs.getInt("id_hotel"));
                comment.setEvenement(rs.getInt("id_event"));
                list.add(comment);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public void signlerCommentaire(int currentUser, int comId, String reason) {
        String req = "insert into `commentaire_reported`(`id_com`, `id_user`, `reason`) values"
                + "(?,?,?)";
        try {

            PreparedStatement pst = myconnex.prepareStatement(req);
            pst.setInt(1, comId);
            pst.setInt(2, currentUser);
            pst.setString(3, reason);
            pst.executeUpdate();
         //   System.out.println("Comm reported");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    
    public boolean comIsReported(int userId, int comId) {
        boolean isReported = false;
        String req = "select COUNT(*) from commentaire_reported where id_com=? and id_user=?";
        try {
            PreparedStatement pst =myconnex.prepareStatement(req);
            pst.setInt(1, comId);
            pst.setInt(2, userId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (rs.getInt("COUNT(*)") != 0) {
                isReported = true;
            }
          //  System.out.println("commentaire_reported isReported or not ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return isReported;
    }

}
