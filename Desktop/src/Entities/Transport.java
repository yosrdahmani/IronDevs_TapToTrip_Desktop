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
public class Transport {
    private int id;
    private String matricule;
    private String marque;
    private String modele;
    private int nbSiege;
    private int categorie;
    private int user;
    private double prix;

    public Transport(int id, String matricule, String marque, String modele, int nbSiege, int categorie, int user, double prix) {
        this.id = id;
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.nbSiege = nbSiege;
        this.categorie = categorie;
        this.user = user;
        this.prix = prix;
    }

    public Transport(String matricule, String marque, String modele, int nbSiege, int categorie, int user, double prix) {
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.nbSiege = nbSiege;
        this.categorie = categorie;
        this.user = user;
        this.prix = prix;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNbSiege() {
        return nbSiege;
    }

    public void setNbSiege(int nbSiege) {
        this.nbSiege = nbSiege;
    }
    
    public double getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }
    
    public double getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Transport{" + "id=" + id + ", matricule=" + matricule + ", marque=" + marque + ", modele=" + modele + ", nbSiege=" + nbSiege + ", categorie=" + categorie + ", user=" + user + ", prix=" + prix + '}';
    }
}
