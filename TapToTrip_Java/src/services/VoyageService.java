/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Voyage;
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
public class VoyageService implements VService<Voyage> {
    
    Connection con;
    Statement stm;
    
    public VoyageService(){
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void ajouterVoyage(Voyage v) {
        try {
            String req="INSERT INTO `voyage_organise`(`attraction_id`, `destination`, `duree`, `programme`, `image`, `hotel`, `prix` )  VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setInt(1, v.getAttraction_id());
            pstm.setString(2, v.getDestination());
            pstm.setString(3, v.getDuree());
            pstm.setString(4, v.getProgramme());
            pstm.setString(5, v.getImage());
            pstm.setString(6, v.getHotel());
            pstm.setString(7, v.getPrix());
 
            pstm.executeUpdate();
            System.out.println("Voyage Organisé ajouté avec succes !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @Override
    public List afficherVoyages() {
    List<Voyage> attractions = new ArrayList<>();
        try {
            String req = "SELECT * FROM `voyage_organise`";
            stm = con.createStatement();
            ResultSet rst = stm.executeQuery(req);
           
            while (rst.next()) {
                Voyage v = new Voyage(rst.getInt("id"),rst.getString("destination"),rst.getString("duree"),rst.getString("programme"),
                                      rst.getString("image"),rst.getString("hotel"),rst.getString("prix"),rst.getInt("attraction_id"));
                attractions.add(v);   
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return attractions;
    }

   @Override
    public void modifierVoyage(Voyage v,int id){
       try {
            String requete = "UPDATE voyage_organise SET destination=?, duree=?, programme=?, image=?, hotel=?, prix=?, attraction_id=? WHERE id=?"+id;
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, v.getDestination());
            pst.setString(2, v.getDuree());
            pst.setString(3, v.getProgramme());
            pst.setString(4, v.getImage());
            pst.setString(5, v.getHotel());
            pst.setString(6, v.getPrix());
            pst.setInt(7, v.getAttraction_id());
            pst.setInt(8, v.getId());
            pst.executeUpdate();
            System.out.println("Voyage Organisé modifiée avec succes !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerVoyage(Voyage v) throws SQLException {
         try {
            String requete = "DELETE FROM `voyage_organise` WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, v.getId());
             int row= pst.executeUpdate();
           
            if(row>0)
            System.out.println("voyage supprimé avec succsés!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
 
}
