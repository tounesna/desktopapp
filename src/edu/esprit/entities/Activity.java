/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author chaym
 */
public class Activity {
    private int IdActivity;
    private String description;
    private CategorieActivity categorie ;
    private int gouvernorat ;
    private String adresse ;
    private String num_contact ;
    private String image ;
    private String date;
    private int auteur;
    
    public Activity(){
    }

    public Activity(int IdActivity, String description, CategorieActivity categorie, int gouvernorat, String adresse, String num_contact, String image, String date, int auteur) {
        this.IdActivity = IdActivity;
        this.description = description;
        this.categorie = categorie;
        this.gouvernorat = gouvernorat;
        this.adresse = adresse;
        this.num_contact = num_contact;
        this.image = image;
        this.date = date;
        this.auteur = auteur;
    }

    public Activity(String description, CategorieActivity categorie, int gouvernorat, String adresse, String num_contact, String image, String date, int auteur) {
        this.description = description;
        this.categorie = categorie;
        this.gouvernorat = gouvernorat;
        this.adresse = adresse;
        this.num_contact = num_contact;
        this.image = image;
        this.date = date;
        this.auteur = auteur;
    }

    public int getIdActivity() {
        return IdActivity;
    }

    public String getDescription() {
        return description;
    }

    public CategorieActivity getCategorie() {
        return categorie;
    }

    public int getGouvernorat() {
        return gouvernorat;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNum_contact() {
        return num_contact;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public int getAuteur() {
        return auteur;
    }

    public void setIdActivity(int IdActivity) {
        this.IdActivity = IdActivity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(CategorieActivity categorie) {
        this.categorie = categorie;
    }

    public void setGouvernorat(int gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNum_contact(String num_contact) {
        this.num_contact = num_contact;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAuteur(int auteur) {
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        return "Activity{" + "IdActivity=" + IdActivity + ", description=" + description + ", categorie=" + categorie + ", gouvernorat=" + gouvernorat + ", adresse=" + adresse + ", num_contact=" + num_contact + ", image=" + image + ", date=" + date + ", auteur=" + auteur + '}';
    }

    
    
    
}
