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
public class CompteBancaire {
    private int id;
    private double solde;

    public CompteBancaire() {
    }

    public CompteBancaire(int id, double solde) {
        this.id = id;
        this.solde = solde;
    }
    
    public CompteBancaire(double solde) {
        this.solde = solde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
    
    
    
}
