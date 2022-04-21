/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author DELL
 */
public class Voyage {
    
    //Les attributs 
    private int id;
    private String destination, duree, programme, image, hotel, prix;
    private ImageView image_attraction;
    
    
    //Jointure
    private int attraction_id;
    
    
    
    //Constructeurs
    //Surchargés
    public Voyage(int id, String destination, String duree, String programme, String image, String hotel, String prix,int attraction_id) {
        this.id = id;
        this.destination = destination;
        this.duree = duree;
        this.programme = programme;
        this.image = image;
        this.hotel = hotel;
        this.prix = prix;
        this.attraction_id = attraction_id;
    }
    
    

    public Voyage(String destination, String duree, String programme, String image, String hotel, String prix, int attraction_id) {
        this.destination = destination;
        this.duree = duree;
        this.programme = programme;
        this.image = image;
        this.hotel = hotel;
        this.prix = prix;
        this.attraction_id = attraction_id;
    }

    public Voyage(int id, String destination, String duree, String programme, String image, String hotel, String prix, int attraction_id, ImageView image_attraction) {
        this.id = id;
        this.destination = destination;
        this.duree = duree;
        this.programme = programme;
        this.image = image;
        this.hotel = hotel;
        this.prix = prix;
        this.attraction_id = attraction_id;
        this.image_attraction = image_attraction;
    }

    public Voyage(String destination, String duree, String programme, String image, String hotel, String prix, int attraction_id, ImageView image_attraction) {
        this.destination = destination;
        this.duree = duree;
        this.programme = programme;
        this.image = image;
        this.hotel = hotel;
        this.prix = prix;
        this.attraction_id = attraction_id;
        this.image_attraction = image_attraction;
    }

    public Voyage(int id, String destination, String duree, String programme, String hotel, String prix, int attraction_id, ImageView image_attraction) {
        this.id = id;
        this.destination = destination;
        this.duree = duree;
        this.programme = programme;
        this.hotel = hotel;
        this.prix = prix;
        this.attraction_id = attraction_id;
        this.image_attraction = image_attraction;
    }
    
    
    
    

    public Voyage() {
    }

   
    
    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getAttraction_id() {
        return attraction_id;
    }

    public void setAttraction_id(int attraction_id) {
        this.attraction_id = attraction_id;
    }

    public ImageView getImage_attraction() {
        return image_attraction;
    }

    public void setImage_attraction(ImageView image_attraction) {
        this.image_attraction = image_attraction;
    }

   
    
    
   
    //Méthode toString
    @Override
    public String toString() {
        return "Voyage{" + "id=" + id + ", destination=" + destination + ", duree=" + duree + ", programme=" + programme + ", image=" + image + ", hotel=" + hotel + ", prix=" + prix + ", attraction_id=" + attraction_id + '}';
    }
    
    
    
    
}
