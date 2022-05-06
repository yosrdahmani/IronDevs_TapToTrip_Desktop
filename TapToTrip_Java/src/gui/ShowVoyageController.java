/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Voyage;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.VoyageService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ShowVoyageController implements Initializable {

    @FXML
    private ImageView imageVoyage;
    @FXML
    private Label destF;
    @FXML
    private Label dureeF;
    @FXML
    private Label progF;
    @FXML
    private Label hotelF;
    @FXML
    private Label prixF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
      public void setData (Voyage v) {
        FileSystem fileSys = FileSystems.getDefault();
        Path destPath= fileSys.getPath("C:\\wamp64\\www\\IronDevs_TapToTrip_Desktop\\TapToTrip_Java\\src\\Images"+v.getImage());
        Image i = new Image(destPath.toAbsolutePath().toUri().toString());  
        imageVoyage.setImage(i);
        destF.setText(v.getDestination());
        dureeF.setText(v.getDuree());
        progF.setText(v.getProgramme());
        hotelF.setText(v.getHotel());
        prixF.setText(v.getPrix());
       
        VoyageService vs = new VoyageService();
//        Categorie c = cs.findById(t.getCategorie());
//       
//        categorieTransport.setText(c.getNom());
    
}
      
      

}