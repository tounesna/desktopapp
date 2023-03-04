/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.esprit.entities;

import java.util.Date;

/**
 *
 * @author Houissa
 */
public class Evenement {
 public int idev;
    public String titre;
    public Region region ;
    public int  gouvernorat ;
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
        return  titre;
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

    public void setGouvernorat(int gouvernorat) {
        this.gouvernorat = gouvernorat;
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
