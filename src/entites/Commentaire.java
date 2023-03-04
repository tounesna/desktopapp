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
public class Commentaire {
    
    public int idcom;
    public String content;
    public String dateajc ;
    public int  auteur ;
    public int hotel;
    public int  evenement ;


    public Commentaire() {
    }

    public Commentaire(String content, String dateajc, int auteur,int hotel, int evenement) {
        this.content = content;
        this.dateajc = dateajc;
        this.auteur = auteur;
        this.evenement = evenement;
        this.hotel=hotel;
    }
      public Commentaire(int idcom,String content, String dateajc, int auteur,int hotel, int evenement) {
        this.idcom = idcom;
        this.content = content;
        this.dateajc = dateajc;
        this.auteur = auteur;
        this.hotel=hotel;
        this.evenement = evenement;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idcom=" + idcom + ", content=" + content + ", dateajc=" + dateajc + ", auteur=" + auteur +", hotel=" + hotel + ", event=" + evenement + '}';
    }

    public void setIdcom(int idcom) {
        this.idcom = idcom;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateajc(String dateajc) {
        this.dateajc = dateajc;
    }

    public void setAuteur(int auteur) {
        this.auteur = auteur;
    }

  

    public int getIdcom() {
        return idcom;
    }

    public String getContent() {
        return content;
    }

    public String getDateajc() {
        return dateajc;
    }

    public int getAuteur() {
        return auteur;
    }

     public int getEvenement() {
        return evenement;
    }

    public void setHotel(int hotel) {
        this.hotel =hotel;
    }
    public int getHotel() {
        return hotel;
    }

    public void setEvenement(int evenement) {
        this.evenement = evenement;
    }

   

    
     
}
