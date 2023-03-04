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
public class Gouvernorat {
      
   private int idg;
   private String nom_gouver;
   private Region region;
   
   public Gouvernorat() {
   }
    public Gouvernorat(int idg, String nom_gouver,Region region) {
      this.idg = idg;
      this.nom_gouver = nom_gouver;
      this.region=region;
   }
   
   public Gouvernorat( String nom_gouver,Region region) {
      this.nom_gouver = nom_gouver;
      this.region=region;
   }
   
   public int getIdg() {
      return idg;
   }
   
   public void setIdg(int idg) {
      this.idg = idg;
   }
   
   public String getNom_gouver() {
      return nom_gouver;
   }
   
   public void setNom_gouver(String nom_gouver) {
      this.nom_gouver = nom_gouver;
   }
    public Region getRegion() {
      return region;
   }
   
   public void setRegion(Region region) {
      this.region  = region;
   }
   
   @Override
   public String toString() {
      return nom_gouver;
   }
}
