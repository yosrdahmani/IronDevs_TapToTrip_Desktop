/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Mail;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class SendMailController_Draft implements Initializable {

    @FXML
    private JFXTextField sujet;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton EnvoyerReclamation;
    @FXML
    private JFXTextField To;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyer(ActionEvent event) {
        Mail ma = new Mail();
        ma.send(To.getText(),sujet.getText(),description.getText(),"karhabtyapplication@gmail.com","hafnaoui");
    }

    @FXML
    private void back(ActionEvent event) {
    }
    
}
