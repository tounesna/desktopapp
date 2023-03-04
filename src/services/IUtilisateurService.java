/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Utilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dell
 */
public interface IUtilisateurService <T> {

    void ajouterUser1(T t) throws SQLException;
     public int ajouterUser(Utilisateur u)throws SQLException;

    void updateUser(T t) throws SQLException;

    boolean supprimeUser(T t) throws SQLException;

    List<T> readAllUser() throws SQLException;

    T findByIdUser(int id) throws SQLException;
}
