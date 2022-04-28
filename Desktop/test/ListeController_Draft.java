/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Billet;
import Entities.Transport;
import Services.BilletService;
import Services.CompteBancaireService;
import Services.TransportService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ListeController_Draft implements Initializable {

   
    @FXML
    private GridPane transportContainer;
    @FXML
    private GridPane triContainer;
    @FXML
    private TextField chercherSiege;
    @FXML
    private AnchorPane anchorAffiche;
    @FXML
    private AnchorPane anchorTri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorAffiche.setVisible(true);
        anchorTri.setVisible(false);
        
        TransportService ts = new TransportService();
        List <Transport> listeTransport = ts.afficher();
        
        int colonne = 0;
        int rang =1;
        try {
            for (Transport t : listeTransport) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("TransportFront.fxml"));
                    VBox transportBox = fxmlLoader.load();
                    TransportFrontController transportFrontController = fxmlLoader.getController();
                    transportFrontController.setData(t);
                    
                    if (colonne == 2) {
                        colonne = 0;
                        rang++;
                    }
                    
                    transportContainer.add(transportBox, colonne++, rang);
                    GridPane.setMargin(transportBox, new Insets(10));
            }
        } 
        catch (IOException ex) {
                Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void chercherSiege(ActionEvent event) {        
        triContainer.setGridLinesVisible(false);
        triContainer.getColumnConstraints().clear();
        triContainer.getRowConstraints().clear();
        triContainer.getChildren().clear();
        triContainer.setGridLinesVisible(true);
        
        TransportService ts = new TransportService();
        System.out.println(chercherSiege.getText());
        List <Transport> transports = ts.afficherByNbSiege(chercherSiege.getText());
        
        anchorAffiche.setVisible(false);
        anchorTri.setVisible(true);
        
        int colonne = 0;
        int rang =1;
        try {
            for (Transport t : transports) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("TransportFront.fxml"));
                VBox transportBox = fxmlLoader.load();
                TransportFrontController transportFrontController = fxmlLoader.getController();
                transportFrontController.setData(t);

                if (colonne == 2) {
                    colonne = 0;
                    rang++;
                }

                triContainer.add(transportBox, colonne++, rang);
                GridPane.setMargin(transportBox, new Insets(10));
            }

        } 
        catch (IOException ex) {
                 Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void favoris(ActionEvent event) {
        triContainer.setGridLinesVisible(false);
        triContainer.getColumnConstraints().clear();
        triContainer.getRowConstraints().clear();
        triContainer.getChildren().clear();
        triContainer.setGridLinesVisible(true);
        
        TransportService ts = new TransportService();
        List <Transport> transports = ts.afficherFavoris(2);
        
        anchorAffiche.setVisible(false);
        anchorTri.setVisible(true);
        
        int colonne = 0;
        int rang =1;
        try {
            for (Transport t : transports) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("TransportFront.fxml"));
                VBox transportBox = fxmlLoader.load();
                TransportFrontController transportFrontController = fxmlLoader.getController();
                transportFrontController.setData(t);

                if (colonne == 2) {
                    colonne = 0;
                    rang++;
                }

                triContainer.add(transportBox, colonne++, rang);
                GridPane.setMargin(transportBox, new Insets(10));
            }

        } 
        catch (IOException ex) {
                 Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reserves(ActionEvent event) {
        triContainer.setGridLinesVisible(false);
        triContainer.getColumnConstraints().clear();
        triContainer.getRowConstraints().clear();
        triContainer.getChildren().clear();
        triContainer.setGridLinesVisible(true);
        
        TransportService ts = new TransportService();
        List <Transport> transports = ts.afficherReserves(2);
        
        anchorAffiche.setVisible(false);
        anchorTri.setVisible(true);
        
        int colonne = 0;
        int rang =1;
        try {
            for (Transport t : transports) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("TransportFront.fxml"));
                VBox transportBox = fxmlLoader.load();
                TransportFrontController transportFrontController = fxmlLoader.getController();
                transportFrontController.setData(t);

                if (colonne == 2) {
                    colonne = 0;
                    rang++;
                }

                triContainer.add(transportBox, colonne++, rang);
                GridPane.setMargin(transportBox, new Insets(10));
            }

        } 
        catch (IOException ex) {
                 Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
