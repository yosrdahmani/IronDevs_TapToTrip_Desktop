/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AccueilBackController implements Initializable {

    @FXML
    private Label pont;
    @FXML
    private Button pont1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void dashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilBack.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    @FXML
    private void Voyage(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listVoyagesFXML.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListVoyagesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Attraction(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listAttractionsFXML.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListAttractionsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deconnecter(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontVoyage.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FrontVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   

  


}
