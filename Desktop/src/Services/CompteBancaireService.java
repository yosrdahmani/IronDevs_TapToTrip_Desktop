/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Billet;
import Entities.CompteBancaire;
import Entities.Transport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyDB;

/**
 *
 * @author lenovo
 */
public class CompteBancaireService {

    Connection con;
    Statement stmm;
    
    public CompteBancaireService(){
            con = MyDB.getInstance().getCon();
        
    }
    
    public void modifier(CompteBancaire compte){
        try {
            String req="UPDATE `compte_bancaire` SET `solde`='"+ compte.getSolde() +"' WHERE `id`='"+ compte.getId() +"'";
            stmm = con.createStatement();
            stmm.executeUpdate(req); 
            System.out.println("Compte bancaire modifié");
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
    public int getNumCarteByUser(int user) { 
        try {
            String req ="SELECT numcarte from `compte_bancaire` WHERE user_id ='"+user+"'";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
            if (rst.next()){
                int i = rst.getInt("numcarte");
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    public int getSoldeByUser(int user) { 
        try {
            String req ="SELECT solde from `compte_bancaire` WHERE user_id ='"+user+"'";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
            if (rst.next()){
                int i = rst.getInt("solde");
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    public CompteBancaire findByIdUser(int user) {
        CompteBancaire cb = null;
        String req = "select * from compte_bancaire where user_id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(req);
            preparedStatement.setInt(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cb = new CompteBancaire(resultSet.getInt("id"),resultSet.getDouble("solde"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cb;
    }
}
