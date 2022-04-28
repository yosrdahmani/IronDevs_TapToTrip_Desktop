/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.entities;

/**
 *
 * @author hp
 */
public class User {
    private int   id; 
    private String   nom,prenom, email, numtel ;
    private String password , confirm_password;
    private int state; 
    private role role; 
    private static User instance;
    public User() {
    }

    public User(String nom, String prenom, String email, String numtel, String password, String confirm_password, int state, role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.password = password;
        this.confirm_password = confirm_password;
        this.state = state;
        this.role = role;
    }

    public User(int id, String nom, String prenom, String email, String numtel, String password, String confirm_password, int state, role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.password = password;
        this.confirm_password = confirm_password;
        this.state = state;
        this.role = role;
    }

   

  
    

    public User(String email, String password, String confirm_password) {
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
    }
    public static void setInstance(User a) {
        instance=a;
     }
     
     public static User getInstance() {
        return instance;
    }
     
    public static void cleanFreelancer() {
        instance=null;
    }

    public int getId() {
        return id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    

    public String getNom() {
        return nom;
    }

    public role getRole() {
        return role;
    }

    public void setRole(role role) {
        this.role = role;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getNumtel() {
        return numtel;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", numtel=" + numtel + ", password=" + password + ", confirm_password=" + confirm_password + ", state=" + state + ", role=" + role + '}';
    }

   
    
}
