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
import java.util.Date;
public class Evenement {
    public int idev;
    public String titre;
    public Region region ;
    public int gouvernorat ;
    public String description ;
    public String datev ;
    public int auteur;

    public Evenement() {
    }

    public Evenement(String titre, Region region, int gouvernorat, String description, String datev, int auteur) {
        this.titre = titre;
        this.region = region;
        this.gouvernorat = gouvernorat;
        this.description = description;
        this.datev = datev;
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idev=" + idev + ", titre=" + titre + ", region=" + region + ", gouvernorat=" + gouvernorat + ", description=" + description + ", datev=" + datev + ", auteur=" + auteur + '}';
    }

    public void setIdev(int idev) {
        this.idev = idev;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

   

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDatev(String datev) {
        this.datev = datev;
    }

    public void setAuteur(int auteur) {
        this.auteur = auteur;
    }

    public int getIdev() {
        return idev;
    }

    public String getTitre() {
        return titre;
    }

    public Region getRegion() {
        return region;
    }

    public int getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(int gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

  

    public String getDescription() {
        return description;
    }

    public String getDatev() {
        return datev;
    }

    public int getAuteur() {
        return auteur;
    }


    
    
}
