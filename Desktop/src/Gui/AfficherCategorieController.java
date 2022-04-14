/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie;
import Services.CategorieService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AfficherCategorieController implements Initializable {

    @FXML
    private Label pont;
    
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<Categorie> listeCategorie;
    @FXML
    private TableColumn<Categorie, String> idCategorie;
    @FXML
    private TableColumn<Categorie, String> nomCategorie;
    @FXML
    private TableColumn<Categorie, String> boiteVitesse;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieService cs = new CategorieService();
        List categories = cs.afficher();
        ObservableList list = FXCollections.observableArrayList(categories);
        listeCategorie.setItems(list);
        idCategorie.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nom"));
        boiteVitesse.setCellValueFactory(new PropertyValueFactory<>("boiteVitesse"));
    }  
    
        @FXML
    private void retourCategorie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Categorie.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
