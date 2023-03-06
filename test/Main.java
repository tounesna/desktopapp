//
//import Entite.Utilisateur;
//import Service.UtilisateurCRUD;
//import Util.MyConnection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author dell
// */
//public class Main {
//      
//     public static void main (String []args) throws SQLException {
//      
//   Utilisateur nouvelUtilisateur = new Utilisateur(25,9628356,"malek","sghaier","boumhal",24,"sghaiermalek87@gmail.com","123456789","user");
//     UtilisateurCRUD crud = new UtilisateurCRUD();
//    
//    crud.ajouter_utilisateur(nouvelUtilisateur);
//    
//      List<Utilisateur> l1 = null;
//
//        try {
//            l1 = crud.readAll();
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        l1.forEach(e -> System.out.println(e));}}
//         
//     
//             
///*      
//UtilisateurCRUD crud = new UtilisateurCRUD();
// Utilisateur u3 = new Utilisateur("malek", "sghaier", "tunis", 23);
//             
// crud.modifierUtilisateur(u3);
//     }}*/
//       
//        
//     
//     /*Utilisateur utilisateur7 = null;
//   try {
//        utilisateur7=findById(279); 
//    } 
//    catch (SQLException e) {
//        e.printStackTrace();
//    }
//    System.out.println(utilisateur7);*/
// /*    
//UtilisateurCRUD crud = new UtilisateurCRUD();
//Utilisateur u4 = new Utilisateur(12, "emna", "sghaier", "boumhal", 24);
//boolean isDeleted = crud.supprimer_utilisateur(u4);
//if (isDeleted) {
//    System.out.println("L'utilisateur a été supprimé avec succès");
//} else {
//    System.out.println("Erreur lors de la suppression de l'utilisateur");
//}*/
//     
///*Utilisateur u = new Utilisateur(1, "John", "john", "johndoe@example.com", 60);
//UtilisateurCRUD crud = new UtilisateurCRUD();
//
//boolean isUpdated = crud.modifier_utilisateur(u);
//if (isUpdated) {
//    System.out.println("L'utilisateur a été modifié avec succès");
//} else {
//    System.out.println("Erreur lors de la modification de l'utilisateur");
//}
//}}*/
//
