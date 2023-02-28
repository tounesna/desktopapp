/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author samar
 */

public class Hotel {
    int idh;
    String Nom_hotel;
    nbetoile nb_etoile;
    String Site ;
    String photos ;
    int nb_recommandation ;
    int utilisateur;

     public Hotel() {
      
    }
    public Hotel(int idh, String Nom_hotel, nbetoile nb_etoile, String Site, String photos, int nb_recommandation, int utilisateur) {
        this.idh = idh;
        this.Nom_hotel = Nom_hotel;
        this.nb_etoile = nb_etoile;
        this.Site = Site;
        this.photos = photos;
        this.nb_recommandation = nb_recommandation;
        this.utilisateur = utilisateur;
    }
     public Hotel(String Nom_hotel, nbetoile nb_etoile, String Site, String photos, int nb_recommandation, int utilisateur) {
        
        this.Nom_hotel = Nom_hotel;
        this.nb_etoile = nb_etoile;
        this.Site = Site;
        this.photos = photos;
        this.nb_recommandation = nb_recommandation;
        this.utilisateur = utilisateur;
    }


    public int getIdh() {
        return idh;
    }

    public String getNom_hotel() {
        return Nom_hotel;
    }

    public nbetoile getNb_etoile() {
        return nb_etoile;
    }

    public String getSite() {
        return Site;
    }

    public String getPhotos() {
        return photos;
    }

    public int getNb_recommandation() {
        return nb_recommandation;
    }

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setIdh(int idh) {
        this.idh = idh;
    }

    public void setNom_hotel(String Nom_hotel) {
        this.Nom_hotel = Nom_hotel;
    }

    public void setNb_etoile(nbetoile nb_etoile) {
        this.nb_etoile = nb_etoile;
    }

    public void setSite(String Site) {
        this.Site = Site;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public void setNb_recommandation(int nb_recommandation) {
        this.nb_recommandation = nb_recommandation;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    
   
    
    
}
