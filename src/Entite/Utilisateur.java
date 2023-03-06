/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Utilisateur {
   private int  IdUser;
    private int  cin;
  private String Nom;
 private String prenom;
 private String adresse;
    private int age;
    private String email;
    private String password;
    private String roles;

    public Utilisateur(int IdUser, int cin, String Nom, String prenom, String adresse, int age, String email, String password, String roles) {
        this.IdUser = IdUser;
        this.cin = cin;
        this.Nom = Nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Utilisateur(int cin, String Nom, String prenom, String adresse, int age, String email, String password, String roles) {
        this.cin = cin;
        this.Nom = Nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    
    
    public Utilisateur(){}
    

    

    public Utilisateur(int idUser) {
        this.IdUser=idUser;
    }


    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}