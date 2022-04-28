/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pidev_java.entities.User;
import pidev_java.services.UserService;
import pidev_java.user.SignUpController;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AddUserController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private TextField tf_nom;
    @FXML
    private Label nom_validator;
    @FXML
    private TextField tf_prenom;
    @FXML
    private Label prenom_validator;
    @FXML
    private TextField tf_email;
    @FXML
    private Label email_validator;
    @FXML
    private PasswordField tf_pwd;
    @FXML
    private Label pwd_validator;
    @FXML
    private PasswordField tf_confirm_pwd;
    @FXML
    private Label confirm_pwd_validator1;
    @FXML
    private TextField tf_numtel;
    @FXML
    private Label numtel_validator;
    @FXML
    private Button btn_signup;
    @FXML
    private Label compte_validator;
    private Parent fxml;
    @FXML
    private Button btn_retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SignUp(ActionEvent event) {
        String regex = "^(.+)@(.+)$";

        btn_signup.setOnAction((e) -> {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(tf_email.getText());
            if (matcher.matches() == true) {
                pwd_validator.setVisible(false);
                nom_validator.setVisible(false);
                prenom_validator.setVisible(false);
                compte_validator.setVisible(false);
                email_validator.setVisible(false);
                confirm_pwd_validator1.setVisible(false);
                numtel_validator.setVisible(false);
                if (tf_pwd.getText().length() < 3 || tf_confirm_pwd.getText().length() < 3 || tf_numtel.getText().length() != 8 || tf_nom.getText().length() < 3 || tf_prenom.getText().length() < 3) {
                    if (tf_pwd.getText().length() < 3) {
                        pwd_validator.setText("Votre Mot De Pass doit comporter au moins 3 caractères");
                        pwd_validator.setVisible(true);
                    }
                    if (!tf_confirm_pwd.getText().equals(tf_pwd.getText())) {
                        confirm_pwd_validator1.setText("les mot de passes ne sont pas conformes");
                        confirm_pwd_validator1.setVisible(true);
                    }
                    if (tf_numtel.getText().length() == 8) {
                        numtel_validator.setText("Votre numero doit comporter 8 chiffres");
                        numtel_validator.setVisible(true);
                    }
                    if (tf_nom.getText().length() < 3) {
                        pwd_validator.setText("Votre Nom doit comporter au moins 3 caractères");
                        pwd_validator.setVisible(true);
                    }

                    if (tf_prenom.getText().length() < 3) {
                        pwd_validator.setText("Votre Prenom doit comporter au moins 3 caractères");
                        pwd_validator.setVisible(true);
                    }

                } else {

                    User f = new User();
                    f.setNom(tf_nom.getText());
                    f.setPrenom(tf_prenom.getText());
                    f.setEmail(tf_email.getText());
                    f.setPassword(tf_pwd.getText());
                    f.setConfirm_password(tf_confirm_pwd.getText());
                    f.setNumtel(tf_numtel.getText());
                    System.out.println(f.toString());

                    boolean test = new UserService().ajouteruser(f);
                    if (test) {
                        compte_validator.setText("Compte a été créer avec succées");
                        compte_validator.setVisible(true);
                        // get a handle to the stage
                        Stage stage = (Stage) btn_signup.getScene().getWindow();
                        // do what you have to do
                        stage.close();
                    } else {
                        compte_validator.setText("Compte déja exist");
                        compte_validator.setVisible(true);
                    }
                }
            } else {
                pwd_validator.setVisible(false);
                nom_validator.setVisible(false);
                prenom_validator.setVisible(false);
                compte_validator.setVisible(false);
                confirm_pwd_validator1.setVisible(false);
                numtel_validator.setVisible(false);
                email_validator.setText("Ce n'est pas un email valide");
                email_validator.setVisible(true);
                if (tf_pwd.getText().length() < 3) {
                    pwd_validator.setText("Votre Mot De Pass doit comporter au moins 3 caractères");
                    pwd_validator.setVisible(true);
                }
                if (tf_nom.getText().length() < 3) {
                    nom_validator.setText("Votre Nom doit comporter au moins 3 caractères");
                    nom_validator.setVisible(true);
                }
                if (tf_prenom.getText().length() < 3) {
                    prenom_validator.setText("Votre Prenom doit comporter au moins 3 caractères");
                    prenom_validator.setVisible(true);
                }
                if (!tf_confirm_pwd.getText().equals(tf_pwd.getText())) {
                    confirm_pwd_validator1.setText("les mot de passes ne sont pas conformes");
                    confirm_pwd_validator1.setVisible(true);
                }
                if (tf_numtel.getText().length() == 8) {
                    numtel_validator.setText("Votre numero doit comporter 8 chiffres");
                    numtel_validator.setVisible(true);
                }
            }

        });
    }

    @FXML
    private void retour(ActionEvent event) {
        Stage stage = (Stage) btn_retour.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
