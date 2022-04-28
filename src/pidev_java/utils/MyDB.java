/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author DELL
 */
public class MyDB {
    
    /*  singleton c'est un patron de conception 
                c'est une instance unique d'une classe
                un point d'acces
    
        jdbc = pilote
        mysql = sqbd
    */
    
    final String url = "jdbc:mysql://localhost:3306/userwvol";
    final String user="root";
    final String pwd="";
    public static MyDB instance;
    Connection con;
    
    private MyDB(){
        try {
            con = DriverManager.getConnection(url,user,pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static MyDB getInstance() {
        if(instance == null)
            instance = new MyDB();
        return instance;
    }

    public Connection getCon() {
        return con;
    }
    
    
    
}


