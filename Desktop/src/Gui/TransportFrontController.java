/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Billet;
import Entities.Categorie;
import Entities.CompteBancaire;
import Entities.Mail;
import Entities.Transport;
import Services.BilletService;
import Services.CategorieService;
import Services.CompteBancaireService;
import Services.TransportService;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    
    private int transportId;
    @FXML
    private AnchorPane anchoraa;
    @FXML
    private AnchorPane anchorVerif;
    @FXML
    private DatePicker dateverif;
    @FXML
    private TextField idBillet;
    @FXML
    private AnchorPane anchorPaye;
    @FXML
    private TextField numCarte;
    @FXML
    private Button reserverButton;
    @FXML
    private Button aimerButton;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorVerif.setVisible(false);
        anchorPaye.setVisible(false);
        
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
        
        transportId=t.getId();
    }

    @FXML
    private void aimer(ActionEvent event) {
        anchorVerif.setVisible(false);
        anchorPaye.setVisible(false);
        
        
        Transport t = new Transport();
        TransportService ts = new TransportService();
        
        t=ts.findById(transportId);
        
        System.out.println(t);
        
        ts.aimer(2, transportId);
        aimerButton.setVisible(false);
        
    }

    @FXML
    private void reserver(ActionEvent event) {
        anchorVerif.setVisible(true);
        anchorPaye.setVisible(false);
    }

    @FXML
    private void verifBillet(ActionEvent event) {
        anchorVerif.setVisible(true);
                
        Billet b = new Billet();
        BilletService bs = new BilletService();
        
        if(bs.getIdBilletByUser(2) != Integer.parseInt(idBillet.getText())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("billet ne vous appartient pas");
            alert1.setHeaderText(null);
            alert1.show();
        }
        else {
            if(bs.getDateBilletByUser(2).after(Date.valueOf(dateverif.getValue()))){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("date invalide");
            alert1.setHeaderText(null);
            alert1.show();
            }
            else{
                anchorPaye.setVisible(true);
            }
        }
        
    }

    @FXML
    private void payer(ActionEvent event) {
        CompteBancaireService cbs = new CompteBancaireService();
        TransportService ts = new TransportService();
        CompteBancaire cb = cbs.findByIdUser(2);
        
        if(cbs.getNumCarteByUser(2) != Integer.parseInt(numCarte.getText())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("numero de carte invalide");
            alert1.setHeaderText(null);
            alert1.show();
        }
        else {
            if(cbs.getSoldeByUser(2) < ts.getPrixById(transportId)){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("solde insuffisant");
            alert1.setHeaderText(null);
            alert1.show();
            }
            else {
                cb.setSolde(cbs.getSoldeByUser(2) - ts.getPrixById(transportId));
                System.out.println(cb.getSolde());
                cbs.modifier(cb);
                ts.reserver(2, transportId); 
                Mail ma = new Mail();
                ma.send("yosrdahmeni6@gmail.com","Reservation d'un transport","Bonjour, TapToTrip vous informe que votre réservation a été bien effectuée avec succés.","toptotrip@gmail.com","toptotrip123");
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("Succès");
                alert1.setContentText("Réservation effectuée");
                alert1.setHeaderText(null);
                alert1.show();
            }
        }
    }
}
