/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Administrateur
 */
public class plat {
    private int idplat ;
    private String nomplat;
    private String recette;
    private String chef;
    private String region;

    public plat() {
    }

    public plat(int idplat, String nomplat, String recette, String chef, String region) {
        this.idplat = idplat;
        this.nomplat = nomplat;
        this.recette = recette;
        this.chef = chef;
        this.region = region;
    }

    public plat(String nomplat, String recette, String chef, String region) {
        this.nomplat = nomplat;
        this.recette = recette;
        this.chef = chef;
        this.region = region;
    }

    public int getIdplat() {
        return idplat;
    }

    public void setIdplat(int idplat) {
        this.idplat = idplat;
    }

    public String getNomplat() {
        return nomplat;
    }

    public void setNomplat(String nomplat) {
        this.nomplat = nomplat;
    }

    public String getRecette() {
        return recette;
    }

    public void setRecette(String recette) {
        this.recette = recette;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
        
    
}
