/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.user;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import pidev_java.entities.User;
import pidev_java.services.UserService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class userProfileController implements Initializable {

    @FXML
    private Circle image;
    @FXML
    private Label nomprenom;
    @FXML
    private Label email;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_numtel;
    @FXML
    private TextField tf_password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = User.getInstance();

        Image img = new Image("./pidev_java/assets/user.png");
        image.setFill(new ImagePattern(img));
        nomprenom.setText(user.getNom() + " " + user.getPrenom());
        email.setText(user.getEmail());
        tf_nom.setText(user.getNom());
        tf_prenom.setText(user.getPrenom());
        tf_email.setText(user.getEmail());
        tf_numtel.setText(user.getNumtel());
    }

    @FXML
    private void Save(ActionEvent event) {
        User user = User.getInstance();

        user.setEmail(tf_email.getText());
        user.setNom(tf_nom.getText());
        user.setNumtel(tf_numtel.getText());
        user.setPrenom(tf_prenom.getText());
        if (!tf_password.getText().equals("")) {
            user.setPassword(tf_password.getText());
            user.setConfirm_password(tf_password.getText());
        }

        boolean test = new UserService().modifieruser(user);
        if (test) {
            User.setInstance(user);
            nomprenom.setText(tf_nom.getText() + " " + tf_prenom.getText());
            email.setText(tf_email.getText());
            Image img = new Image("./pidev_java/assets/user.png");
            image.setFill(new ImagePattern(img));
        }

    }

}
