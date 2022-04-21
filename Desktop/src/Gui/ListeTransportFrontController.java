/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Transport;
import Services.TransportService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ListeTransportFrontController implements Initializable {

    @FXML
    private GridPane transportContainer;
    @FXML
    private Button pont;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                    
                    if (colonne == 3) {
                        colonne = 0;
                        rang++;
                    }
                    
                    transportContainer.add(transportBox, colonne++, rang);
                    GridPane.setMargin(transportBox, new Insets(10));
            }
        } catch (IOException ex) {
                Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
            
                
            }
        
    }    

    @FXML
    private void SeConnecter(ActionEvent event) {
    }
    
}
