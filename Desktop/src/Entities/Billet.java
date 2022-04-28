/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author lenovo
 */
public class Billet {
    private int id;
    private int vol_billet;
    private int num;
    private Date date;
    private String destination;
    private String categorie;
    private double prix;
    private int user;

    public Billet() {
    }
    
    public Billet(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    
    public Billet(Date date) {
        this.date = date;
    }

    public Billet(int id, int vol_billet, int num, Date date, String destination, String categorie, double prix, int user) {
        this.id = id;
        this.vol_billet = vol_billet;
        this.num = num;
        this.date = date;
        this.destination = destination;
        this.categorie = categorie;
        this.prix = prix;
        this.user = user;
    }

        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVol_billet() {
        return vol_billet;
    }

    public void setVol_billet(int vol_billet) {
        this.vol_billet = vol_billet;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Billet{" + "id=" + id + ", vol_billet=" + vol_billet + ", num=" + num + ", date=" + date + ", destination=" + destination + ", categorie=" + categorie + ", prix=" + prix + ", user=" + user + '}';
    }



    
    
    
}
