/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.entities;

/**
 *
 * @author Houissa
 */
public class Gouvernorat {
   private int id;
   private String name;
   
   public Gouvernorat() {
   }
   
   public Gouvernorat(int id, String name) {
      this.id = id;
      this.name = name;
   }
   
   public Gouvernorat( String name) {
      this.name = name;
   }
   
   public int getId() {
      return id;
   }
   
   public void setId(int id) {
      this.id = id;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   @Override
   public String toString() {
      return name;
   }
}
