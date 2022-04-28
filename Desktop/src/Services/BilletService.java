/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Billet;
import Entities.Transport;
import java.sql.Connection;
import java.sql.Date;
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
public class BilletService {
    
    Connection con;
    Statement stmm;
    
    public BilletService(){
            con = MyDB.getInstance().getCon();
        
    }
    
    public Billet findByIdUser(int user) {
        Billet b = null;
        String req = "select * from billet where user_id =?";
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(req);
            preparedStatement.setInt(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                b = new Billet(resultSet.getInt("id"),resultSet.getInt("vol_billet_id"),resultSet.getInt("num"),resultSet.getDate("date"),resultSet.getString("destination"),resultSet.getString("categorie"),resultSet.getDouble("prix"),resultSet.getInt("user_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    public int getIdBilletByUser(int user) { 
        try {
            String req ="SELECT id from `billet` WHERE user_id ='"+user+"'";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
            if (rst.next()){
                int i = rst.getInt("id");
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }   
    
    public Date getDateBilletByUser(int user) { 
        try {
            String req ="SELECT date from `billet` WHERE user_id ='"+user+"'";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
            if (rst.next()){
                Date d = rst.getDate("date");
                return d;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }    
}
