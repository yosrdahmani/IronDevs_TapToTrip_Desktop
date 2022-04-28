/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Billet;
import Services.BilletService;
import Services.CompteBancaireService;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ReserverTransportController_Draft implements Initializable {

    @FXML
    private DatePicker dateverif;
    @FXML
    private TextField idBillet;
    @FXML
    private AnchorPane anchor1;
    @FXML
    private TextField numCarte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchor1.setVisible(false);
    }    

    @FXML
    private void verifBillet(ActionEvent event) {
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
                anchor1.setVisible(true);
            }
        }
    }

    @FXML
    private void payer(ActionEvent event) {
        CompteBancaireService cbs = new CompteBancaireService();
        
        if(cbs.getNumCarteByUser(2) != Integer.parseInt(numCarte.getText())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("numero de carte invalide");
            alert1.setHeaderText(null);
            alert1.show();
        }
        else {
            //if(cbs.getSoldeByUser(2) )){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("date invalide");
            alert1.setHeaderText(null);
            alert1.show();
            }
            //else{
               // anchor1.setVisible(true);
            //}
    }       
}
