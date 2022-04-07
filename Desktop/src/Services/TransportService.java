/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Transport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyDB;

/**
 *
 * @author lenovo
 */
public class TransportService implements IService<Transport> {
    
    Connection con;
    Statement stmm;
    
    public TransportService(){
            con = MyDB.getInstance().getCon();
        
    }

    @Override
    public void ajouter(Transport transport) {
        try {
            String req="INSERT INTO `transport`(`matricule`, `marque`, `modele`, `nbsiege`, `categorie_id`, `user_id`, `prix`) VALUES ('"+ transport.getMatricule() +"','"+ transport.getMarque() +"','"+ transport.getModele() +"','"+ transport.getNbSiege() +"','"+ transport.getCategorie() +"','"+ transport.getUser() +"','"+ transport.getPrix() +"')";
            stmm = con.createStatement();
            stmm.executeUpdate(req); 
            System.out.println("Transport ajouté");
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    @Override
    public List<Transport> afficher() {
        List<Transport> transports = new ArrayList<>();
        
        try {
            String req = "SELECT * FROM `transport`";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
           
            while (rst.next()) {
                Transport t = new Transport(rst.getInt("Id"),rst.getString("matricule"),rst.getString("marque"),rst.getString("modele"),rst.getInt("nbSiege"),rst.getInt("categorie_id"),rst.getInt("user_id"),rst.getDouble("prix"));
                transports.add(t);   
            }
           
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return transports;
    }
    

    @Override
    public void modifier(Transport transport){
        try {
            String req="UPDATE `transport` SET `matricule`='"+ transport.getMatricule() +"',`marque`='"+ transport.getMarque() +"',`modele`='"+ transport.getModele() +"',`nbsiege`='"+ transport.getNbSiege() +"',`categorie_id`='"+ transport.getCategorie() +"',`user_id`='"+ transport.getUser() +"',`prix`='"+ transport.getPrix() +"' WHERE `id`='"+ transport.getId() +"'";
            stmm = con.createStatement();
            stmm.executeUpdate(req); 
            System.out.println("Transport modifié");
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
    public Connection getCon() {
        return con;
    }

    @Override
    public void supprimer(Transport transport) {
        try {
            String req="DELETE FROM `transport` WHERE `id`='"+ transport.getId() +"'";
            stmm = con.createStatement();
            stmm.executeUpdate(req); 
            System.out.println("Transport supprimé");
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }
}
