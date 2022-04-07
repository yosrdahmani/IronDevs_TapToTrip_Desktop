/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DELL
 */
public class Attraction {
    
    //Les attributs
    private int id ;
    private String nom, lieu, description;
    
    //Constructeurs
    //Surchargés
    public Attraction(int id, String nom, String lieu, String description) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
    }

    public Attraction(String nom, String lieu, String description) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
    }
    
    //Par défaut
    public Attraction() {
        
    }
    
    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    //Méthode toString
    @Override
    public String toString() {
        return "Attraction{" + "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", description=" + description + '}';
    }
    
    
        
}
