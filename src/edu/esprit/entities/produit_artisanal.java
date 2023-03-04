/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author user
 */
public class produit_artisanal {
     public int code;
     public String description;
     public Type type ;
     public int gouvernorat;

    public produit_artisanal() {
    }

    public produit_artisanal(String description, Type type, int gouvernorat) {
        this.description = description;
        this.type = type;
        this.gouvernorat = gouvernorat;
    }
     public produit_artisanal(int code, String description, Type type, int gouvernorat) {
        this.code = code;
        this.description = description;
        this.type = type;
        this.gouvernorat = gouvernorat; 
     }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(int gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    @Override
    public String toString() {
        return "produit_artisanal{" + "code=" + code + ", description=" + description + ", type=" + type + ", gouvernorat=" + gouvernorat + '}';
    }

    /*@Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.code;
        return hash;
    }*/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final produit_artisanal other = (produit_artisanal) obj;
        return true;
    }
     

    
     
     
    
    
    
    
}
