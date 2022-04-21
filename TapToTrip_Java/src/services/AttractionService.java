/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Attraction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author DELL
 */
public class AttractionService implements AService<Attraction> {
    
    Connection con;
    Statement stm;
    
    public AttractionService(){
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void ajouterAttraction(Attraction a) {
        try {
            String req="INSERT INTO `attraction`(`nom`, `lieu`, `description`) VALUES (?,?,?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, a.getNom());
            pstm.setString(2, a.getLieu());
            pstm.setString(3, a.getDescription());
            pstm.executeUpdate();
            System.out.println("Attraction ajoutée avec succes !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @Override
    public List afficherAttractions() {
    List<Attraction> attractions = new ArrayList<>();
        try {
            String req = "SELECT * FROM `attraction`";
            stm = con.createStatement();
            ResultSet rst = stm.executeQuery(req);
           
            while (rst.next()) {
                Attraction a = new Attraction(rst.getInt("id"),rst.getString("nom"),rst.getString("lieu"),rst.getString("description"));
                attractions.add(a);   
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return attractions;
    }

   @Override
    public void modifierAttraction(Attraction a,int id){
       try {
            String requete = "UPDATE attraction SET nom=? , lieu=? , description=? WHERE id=?"+id;
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, a.getNom());
            pst.setString(2, a.getLieu());
            pst.setString(3, a.getDescription());
            pst.setInt(4, a.getId());
            pst.executeUpdate();
            System.out.println("Attraction modifiée avec succes !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerAttraction(Attraction a) {
          try {
            String requete = "DELETE FROM `attraction` WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, a.getId());
             int row= pst.executeUpdate();
           
            if(row>0)
            System.out.println("attraction supprimée avec succés!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            }
 
}


