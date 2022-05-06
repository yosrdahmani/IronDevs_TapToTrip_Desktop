/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Voyage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
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
    @FXML
    private TextField searchVoyage;
    @FXML
    private ScrollPane scrollPaneVoyage;
    
     private final List<Voyage> voyageSorted = new ArrayList<>();
    
    

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
    
    
    //Recherche
    public void afficherAfterSearch(){
        int colonne = 0;
        int rang =1;
        
        List<Voyage> voyagesSearch=new ArrayList();
        VoyageService vs = new VoyageService();
         
          if(!" ".equals(searchVoyage.getText())){
              voyageContainer.getChildren().clear();
              voyagesSearch= vs.displayAllAfterSearch(searchVoyage.getText()); 

        try {
               for (Voyage v : voyagesSearch) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
          }  
     }


    @FXML
    private void recherche(KeyEvent event) {
        this.afficherAfterSearch();
    }
    
   

    @FXML
    private void triDestA(ActionEvent event) {
            VoyageService vs = new VoyageService();
   
        List <Voyage> listeVoyage = vs.trierVoyageDestinationasc();
        voyageContainer.getChildren().clear();
       
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
    private void triDestD(ActionEvent event) {
        VoyageService vs = new VoyageService();
   
        List <Voyage> listeVoyage = vs.trierVoyageDestinationdsc();
        voyageContainer.getChildren().clear();
       
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
    private void triPrixA(ActionEvent event) {
        VoyageService vs = new VoyageService();
   
        List <Voyage> listeVoyage = vs.trierVoyagePrixdsc();
        voyageContainer.getChildren().clear();
       
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
    private void triPrixD(ActionEvent event) {
    VoyageService vs = new VoyageService();
   
        List <Voyage> listeVoyage = vs.trierVoyagePrixasc();
        voyageContainer.getChildren().clear();
       
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
    
    
    
    

}
   