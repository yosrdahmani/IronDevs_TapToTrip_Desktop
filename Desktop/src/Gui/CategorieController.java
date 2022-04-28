/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class CategorieController implements Initializable {

    @FXML
    private Label pont;
    @FXML
    private TableView<?> listeCategorie;
    @FXML
    private TableColumn<?, ?> idCategorie;
    @FXML
    private TableColumn<?, ?> nomCategorie;
    @FXML
    private TableColumn<?, ?> boiteVitesse;
    @FXML
    private TextField nom;
    @FXML
    private ComboBox<?> boite;
    @FXML
    private TextField rechBoite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void dashboard(ActionEvent event) {
    }

    @FXML
    private void transport(ActionEvent event) {
    }

    @FXML
    private void categorie(ActionEvent event) {
    }

    @FXML
    private void SeConnecter(ActionEvent event) {
    }

    @FXML
    private void onClick(MouseEvent event) {
    }

    @FXML
    private void ajouterCategorie(ActionEvent event) {
    }

    @FXML
    private void modifierCategorie(ActionEvent event) {
    }

    @FXML
    private void supprimerCategorie(ActionEvent event) {
    }

    @FXML
    private void chercher(ActionEvent event) {
    }
    
}
