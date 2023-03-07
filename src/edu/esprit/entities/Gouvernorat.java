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
public class Gouvernorat {

    public static int valueOf(Gouvernorat value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    int id_gouver;
    public String nom_gouver ;
    public Region region_gouver ;
    
    public Gouvernorat(){
    }
  
    public Gouvernorat(int id_gouver, String nom_gouver, Region region_gouver) {
        this.id_gouver = id_gouver;
        this.nom_gouver = nom_gouver;
        this.region_gouver = region_gouver;
    }

    public Gouvernorat(String nom_gouver, Region region_gouver) {
        this.nom_gouver = nom_gouver;
        this.region_gouver = region_gouver;
    }

  

    public int getId_gouver() {
        return id_gouver;
    }

    public String getNom_gouver() {
        return nom_gouver;
    }

    public Region getRegion_gouver() {
        return region_gouver;
    }

    public void setId_gouver(int id_gouver) {
        this.id_gouver = id_gouver;
    }

    public void setNom_gouver(String nom_gouver) {
        this.nom_gouver = nom_gouver;
    }

    public void setRegion_gouver(Region region_gouver) {
        this.region_gouver = region_gouver;
    }

    @Override
    public String toString() {
        return "Gouvernorat{" + "id_gouver=" + id_gouver + ", nom_gouver=" + nom_gouver + ", region_gouver=" + region_gouver + '}';
    }

    public void setRegion_gouver(String nord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}