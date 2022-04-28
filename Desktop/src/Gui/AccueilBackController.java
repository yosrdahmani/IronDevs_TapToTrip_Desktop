/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AccueilBackController implements Initializable {

    @FXML
    private Label pont;

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
    private void transport(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TransportBack.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(TransportBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @FXML
    private void categorie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieBack.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SeConnecter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeTransportFront.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fermer(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
