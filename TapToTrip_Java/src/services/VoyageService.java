/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import entities.Voyage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;
import static utils.MyDB.instance;

/**
 *
 * @author DELL
 */
public class VoyageService implements VService<Voyage> {
    
    Connection con;
    Statement stm;
    
    private ResultSet rs;
    
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
    
       public List<Voyage> rechercherVoyage(String s, int i){
         List<Voyage> voyages = new ArrayList<Voyage>();
       
        try {
            String req="SELECT * FROM `voyage_organise` WHERE destination=? || id=?"; 
            PreparedStatement pst=con.prepareStatement(req);
            pst.setString(1,s+'%' );
            pst.setInt(1,i+'%' );
            ResultSet rst=pst.executeQuery();
       
       while(rst.next()){
            
         Voyage v = new Voyage(
                     rst.getString("id"),
                     rst.getString("destination"),
                     rst.getString("duree"),
                     rst.getString("programme"),
                     rst.getString("hotel"),
                     rst.getString("prix"),
                     rst.getInt("attraction_id")
                     );
             voyages.add(v);
            
        
        }
        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voyages;
     
    }
    
    public List<Voyage> trierVoyageDestinationasc() {
        List<Voyage> voyages = new ArrayList<Voyage>();
        try{

            String requete="SELECT * FROM `voyage_organise` ORDER BY destination ASC";
            PreparedStatement pst=con.prepareStatement(requete);
            ResultSet rst=pst.executeQuery();
            while(rst.next()){      
            
           Voyage v = new Voyage(rst.getInt("id"),rst.getString("destination"),rst.getString("duree"),rst.getString("programme"),
                                      rst.getString("image"),rst.getString("hotel"),rst.getString("prix"),rst.getInt("attraction_id"));
                voyages.add(v);            
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return voyages;
    }
    
      public List<Voyage> trierVoyageDestinationdsc() {
        List<Voyage> voyages = new ArrayList<Voyage>();
        try{

            String requete="SELECT * FROM `voyage_organise` ORDER BY destination DESC";
            PreparedStatement pst=con.prepareStatement(requete);
            ResultSet rst=pst.executeQuery();
            while(rst.next()){      
            
           Voyage v = new Voyage(rst.getInt("id"),rst.getString("destination"),rst.getString("duree"),rst.getString("programme"),
                                      rst.getString("image"),rst.getString("hotel"),rst.getString("prix"),rst.getInt("attraction_id"));
                voyages.add(v);            
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return voyages;
    }
      
        public List<Voyage> trierVoyagePrixasc() {
        List<Voyage> voyages = new ArrayList<Voyage>();
        try{

            String requete="SELECT * FROM `voyage_organise` ORDER BY prix ASC";
            PreparedStatement pst=con.prepareStatement(requete);
            ResultSet rst=pst.executeQuery();
            while(rst.next()){      
            
           Voyage v = new Voyage(rst.getInt("id"),rst.getString("destination"),rst.getString("duree"),rst.getString("programme"),
                                      rst.getString("image"),rst.getString("hotel"),rst.getString("prix"),rst.getInt("attraction_id"));
                voyages.add(v);            
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return voyages;
    }
        
          public List<Voyage> trierVoyagePrixdsc() {
        List<Voyage> voyages = new ArrayList<Voyage>();
        try{

            String requete="SELECT * FROM `voyage_organise` ORDER BY prix DESC";
            PreparedStatement pst=con.prepareStatement(requete);
            ResultSet rst=pst.executeQuery();
            while(rst.next()){      
            
           Voyage v = new Voyage(rst.getInt("id"),rst.getString("destination"),rst.getString("duree"),rst.getString("programme"),
                                      rst.getString("image"),rst.getString("hotel"),rst.getString("prix"),rst.getInt("attraction_id"));
                voyages.add(v);            
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return voyages;
    }
       
     
     public List<Voyage> displayAllAfterSearch(String s) {
        String req="select * from voyage_organise where destination like '%"+s+"%'";
        List<Voyage> list=new ArrayList<>();       
        
        try {
            stm = con.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while(rst.next()){
                Voyage p=new Voyage();
                p.setId(rst.getInt("id"));
                p.setDestination(rst.getString("destination"));
                p.setDuree(rst.getString("duree"));
                p.setProgramme(rst.getString("programme"));
                p.setImage(rst.getString("image"));
                p.setHotel(rst.getString("hotel"));
                p.setPrix(rst.getString("prix"));
                p.setAttraction_id(rst.getInt("attraction_id"));
               
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

   public void qr(Voyage v) throws WriterException, UnsupportedEncodingException, IOException{
         String qrCodeData = v.getDestination();
            String filePath = "C:\\wamp64\\www\\IronDevs_TapToTrip_Desktop\\TapToTrip_Java\\src\\qrCode\\VoyageQR.png";
            String charset = "UTF-8"; 
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
            new String(qrCodeData.getBytes(charset), charset),
            BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
             .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
  }
 
}
