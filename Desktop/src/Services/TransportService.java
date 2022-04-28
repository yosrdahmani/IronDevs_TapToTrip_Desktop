/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.Transport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
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
            String req="INSERT INTO `transport`(`matricule`, `image`, `marque`, `modele`, `nbsiege`, `categorie_id`, `prix`) VALUES ('"+ transport.getMatricule() +"','"+ transport.getImage() +"','"+ transport.getMarque() +"','"+ transport.getModele() +"','"+ transport.getNbSiege() +"','"+ transport.getCategorie() +"','"+ transport.getPrix() +"')";
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
            String req = "SELECT * FROM `transport` where user_id is null ";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
           
            while (rst.next()) {
                Transport t = new Transport(rst.getInt("id"),rst.getString("matricule"),rst.getString("image"),rst.getString("marque"),rst.getString("modele"),rst.getInt("nbSiege"),rst.getInt("categorie_id"),rst.getInt("user_id"),rst.getDouble("prix"));
                transports.add(t);   
            }
           
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return transports;
    }
    
    
    public List<Transport> afficherAll() {
        List<Transport> transports = new ArrayList<>();
        try {
            String req = "SELECT * FROM `transport`";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
           
            while (rst.next()) {
                Transport t = new Transport(rst.getInt("id"),rst.getString("matricule"),rst.getString("image"),rst.getString("marque"),rst.getString("modele"),rst.getInt("nbSiege"),rst.getInt("categorie_id"),rst.getInt("user_id"),rst.getDouble("prix"));
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
            System.out.println("*******************"+transport.toString());
            String req="UPDATE `transport` SET `matricule`='"+ transport.getMatricule() +"',`image`='"+ transport.getImage()+"',`marque`='"+ transport.getMarque() +"',`modele`='"+ transport.getModele() +"',`nbsiege`='"+ transport.getNbSiege() +"',`categorie_id`='"+ transport.getCategorie() +"',`prix`='"+ transport.getPrix() +"' WHERE `id`='"+ transport.getId() +"'";
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
    
     public boolean chercherMatriculeTransport(String matricule)  {
        String req = "SELECT `matricule` FROM `transport`";
        List<String> list = new ArrayList<>();
        
        try {
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
            while(rst.next()){
                list.add(rst.getString(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list.contains(matricule);
    }
     
    public List<Transport> ChercherBySiegeAndNomCategorie(String nbsiege, String nomCategorie) {
        List<Transport> transports = new ArrayList<>();
        
        CategorieService cs = new CategorieService();
        int idCategorie = cs.getIdCategorie(nomCategorie);
        
        String req = "select * from transport where nbsiege =? and categorie_id=? and user_id is null";
        PreparedStatement preparedStatement;
        
        
        try {
            preparedStatement = con.prepareStatement(req);
            preparedStatement.setString(1, nbsiege);
            preparedStatement.setInt(2, idCategorie);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transport t = new Transport(resultSet.getInt("id"),resultSet.getString("matricule"),resultSet.getString("image"),resultSet.getString("marque"),resultSet.getString("modele"),resultSet.getInt("nbSiege"),resultSet.getInt("categorie_id"),resultSet.getInt("user_id"),resultSet.getDouble("prix"));
                transports.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                
        return transports;
    } 
    
    public List<Transport> afficherByNbSiege(String nbsiege) {
        List<Transport> transports = new ArrayList<>();
        
        String req = "select * from transport where nbsiege =?";
        PreparedStatement preparedStatement;
        
        
        try {
            preparedStatement = con.prepareStatement(req);
            preparedStatement.setString(1, nbsiege);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transport t = new Transport(resultSet.getInt("id"),resultSet.getString("matricule"),resultSet.getString("image"),resultSet.getString("marque"),resultSet.getString("modele"),resultSet.getInt("nbSiege"),resultSet.getInt("categorie_id"),resultSet.getInt("user_id"),resultSet.getDouble("prix"));
                transports.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                
        return transports;
    } 
    
    public List<Transport> afficherByPrix() {
        List<Transport> transports = new ArrayList<>();
        
        try {
            String req = "SELECT * FROM `transport` order by prix";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
           
            while (rst.next()) {
                Transport t = new Transport(rst.getInt("id"),rst.getString("matricule"),rst.getString("image"),rst.getString("marque"),rst.getString("modele"),rst.getInt("nbSiege"),rst.getInt("categorie_id"),rst.getInt("user_id"),rst.getDouble("prix"));
                transports.add(t);   
            }
           
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                
        return transports;
    } 
    
    public void aimer(int user, int transport) {
        try {
            String req="INSERT INTO `transport_user`(`transport_id`, `user_id`) VALUES ('"+ transport +"','"+ user +"')";
            stmm = con.createStatement();
            stmm.executeUpdate(req); 
            System.out.println("Transport ajouté");
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
    public Transport findById(int id) {
        Transport t = null;
        String req = "select * from transport where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                t = new Transport(resultSet.getInt("id"),resultSet.getString("matricule"),resultSet.getString("image"),resultSet.getString("marque"),resultSet.getString("modele"),resultSet.getInt("nbSiege"),resultSet.getInt("categorie_id"),resultSet.getInt("user_id"),resultSet.getDouble("prix"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return t;
    }
    
    public List<Transport> afficherFavoris(int user) {
        List<Integer> transportIds = new ArrayList<>();
        List<Transport> transports = new ArrayList<>();
        try {
            String req = "select transport_id from transport_user where user_id =?";
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(req);
            preparedStatement.setInt(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int i = resultSet.getInt("transport_id");
                transportIds.add(i);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        for (int i=0; i < transportIds.size(); i++) {
            Transport t = findById(transportIds.get(i));
            transports.add(t);   
        }
        return transports;
    }    
 
    public void reserver(int user, int transport) {
        try {
            String req="UPDATE `transport` SET `user_id`='"+user +"' where id ='"+transport+"'";
            stmm = con.createStatement();
            stmm.executeUpdate(req); 
            System.out.println("Transport reservé");
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Transport> afficherReserves(int user) {
        List<Transport> transports = new ArrayList<>();
        
        try {
            String req = "SELECT * FROM `transport` where user_id ='"+user+"'";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
           
            while (rst.next()) {
                Transport t = new Transport(rst.getInt("id"),rst.getString("matricule"),rst.getString("image"),rst.getString("marque"),rst.getString("modele"),rst.getInt("nbSiege"),rst.getInt("categorie_id"),rst.getInt("user_id"),rst.getDouble("prix"));
                transports.add(t);   
            }
           
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return transports;
    } 
    
    
    public int findNumberUserByCategorieId(int categorieId) {
        List<Transport> transports = new ArrayList<>();
        
        try {
            String req = "SELECT * FROM `transport` where categorie_id ='"+categorieId+"' and user_id is not null group By user_id";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
           
            while (rst.next()) {
                Transport t = new Transport(rst.getInt("id"),rst.getString("matricule"),rst.getString("image"),rst.getString("marque"),rst.getString("modele"),rst.getInt("nbSiege"),rst.getInt("categorie_id"),rst.getInt("user_id"),rst.getDouble("prix"));
                transports.add(t);   
            }
           
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return transports.size();
    } 
   
    public double getPrixById(int id) { 
        try {
            String req ="SELECT prix from `transport` WHERE id ='"+id+"'";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
            if (rst.next()){
                double i = rst.getInt("prix");
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }

    public List<Transport> afficherByPrixFront() {
        List<Transport> transports = new ArrayList<>();
        
        try {
            String req = "SELECT * FROM `transport` where user_id is null order by prix";
            stmm = con.createStatement();
            ResultSet rst = stmm.executeQuery(req);
           
            while (rst.next()) {
                Transport t = new Transport(rst.getInt("id"),rst.getString("matricule"),rst.getString("image"),rst.getString("marque"),rst.getString("modele"),rst.getInt("nbSiege"),rst.getInt("categorie_id"),rst.getInt("user_id"),rst.getDouble("prix"));
                transports.add(t);   
            }
           
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                
        return transports;
    }
}
