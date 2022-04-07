/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author lenovo
 */
public class Categorie {
    private int id;
    private String nom;
    private String boiteVitesse;

    public Categorie() {
    }

    public Categorie(int id) {
        this.id = id;
    }

    public Categorie(String nom, String boiteVitesse) {
        this.nom = nom;
        this.boiteVitesse = boiteVitesse;
    }

    public Categorie(int id, String nom, String boiteVitesse) {
        this.id = id;
        this.nom = nom;
        this.boiteVitesse = boiteVitesse;
    }

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
    
    public String getBoiteVitesse() {
        return boiteVitesse;
    }

    public void setBoiteVitesse(String boiteVitesse) {
        this.boiteVitesse = boiteVitesse;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + nom + ", boiteVitesse=" + boiteVitesse + '}';
    }
}
