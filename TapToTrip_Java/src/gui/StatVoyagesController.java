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
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.VoyageService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class StatVoyagesController implements Initializable {

    @FXML
    private Label pont;
    @FXML
    private Button pont1;
    @FXML
    private BarChart<?, ?> chartVoyage;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VoyageService vs=new VoyageService();
        List<Voyage> v= vs.afficherVoyages();
        XYChart.Series set=new XYChart.Series<>();
        for(Voyage vo : v )
            {
            set.getData().add(new XYChart.Data(vo.getDestination(),Integer.valueOf(vo.getPrix())) );
            
            }

        chartVoyage.getData().addAll(set);
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

    @FXML
    private void BoutonBack(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listVoyagesFXML.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FrontVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
