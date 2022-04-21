/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie;
import Entities.Transport;
import Services.CategorieService;
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

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class TransportFrontController implements Initializable {

    @FXML
    private ImageView imageTransport;
    @FXML
    private Label matriculeTransport;
    @FXML
    private Label marqueTransport;
    @FXML
    private Label modeleTransport;
    @FXML
    private Label nbSiegeTransport;
    @FXML
    private Label prixTransport;
    @FXML
    private Label categorieTransport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setData (Transport t) {
        FileSystem fileSys = FileSystems.getDefault();
        Path destPath= fileSys.getPath("E:\\3A46\\pi\\transport\\"+t.getImage());
        Image i = new Image(destPath.toAbsolutePath().toUri().toString());  
        imageTransport.setImage(i);
        matriculeTransport.setText(t.getMatricule());
        marqueTransport.setText(t.getMarque());
        modeleTransport.setText(t.getModele());
        nbSiegeTransport.setText(String.valueOf(t.getNbSiege()));
        prixTransport.setText(String.valueOf(t.getPrix()));
        
        CategorieService cs = new CategorieService();
        Categorie c = cs.findById(t.getCategorie());
        
        categorieTransport.setText(c.getNom());
        
        
    }
    
}
