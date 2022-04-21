/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Voyage;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import services.VoyageService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FrontVoyageController implements Initializable {

    
    @FXML
    private Button pont;
    @FXML
    private GridPane voyageContainer;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                VoyageService vs = new VoyageService();
        List <Voyage> listeVoyage = vs.afficherVoyages();
       
        int colonne = 0;
        int rang =1;
        try {
            for (Voyage v : listeVoyage) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("showVoyage.fxml"));
                    VBox voyageBox = fxmlLoader.load();
                    ShowVoyageController voyageFrontController = fxmlLoader.getController();
                    voyageFrontController.setData(v);
                   
                    if (colonne == 3) {
                        colonne = 0;
                        rang++;
                    }
                   
                    voyageContainer.add(voyageBox, colonne++, rang);
                    GridPane.setMargin(voyageBox, new Insets(10));
            }
        } catch (IOException ex) {
                Logger.getLogger(FrontVoyageController.class.getName()).log(Level.SEVERE, null, ex);
           
               
            }
    }    

    @FXML
    private void SeConnecter(ActionEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilBack.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
