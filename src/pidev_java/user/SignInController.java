/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pidev_java.entities.User;
import pidev_java.entities.role;
import pidev_java.services.UserService;

/**
 * FXML Controller class
 *
 * @author seifeddine
 */
public class SignInController implements Initializable {

    Stage primaryStage;
    @FXML
    private TextField tf_email;
    @FXML
    private Label email_validator;
    @FXML
    private Label compte_validator;
    @FXML
    private Label compte_validator1;
    @FXML
    private Label compte_validator11;
    @FXML
    private AnchorPane vbox;
    private Parent fxml;
    @FXML
    private PasswordField tf_pwd;
    @FXML
    private Label pwd_validator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SignIn(ActionEvent event) throws IOException {

        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tf_email.getText());
        if (matcher.matches() == true) {
            email_validator.setVisible(false);
            pwd_validator.setVisible(false);
            compte_validator.setVisible(false);
            if (tf_pwd.getText().length() < 3) {
                pwd_validator.setText("Votre Mot De Pass doit comporter au moins 3 caractères");
                pwd_validator.setVisible(true);
            } else {
                System.err.println(tf_email.getText());
                User s = new UserService().getUser(tf_email.getText());
                if (s.getEmail() != null) {
                    if (s.getConfirm_password().equals(tf_pwd.getText())) {
                        if (s.getState() == 0) {
                            User.setInstance(s);
                            System.out.println(s);
                            if (s.getRole().name().equals(role.CLIENT.name())) {
                                try {
                                    Image img = new Image("pidev_java/assets/logo.jpg");
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_java/bases/BaseGui.fxml"));
                                    Parent root = (Parent) fxmlLoader.load();
                                    Stage stage = new Stage();
                                    primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    primaryStage.close();
                                    stage.setScene(new Scene(root));
                                    stage.setMaximized(true);
                                    stage.getIcons().add(img);
                                    stage.setTitle("User Vol");
                                    stage.show();
                                } catch (IOException e) {
                                    System.err.println(e);
                                }
                            } else {
                                try {
                                    Image img = new Image("pidev_java/assets/logo.jpg");
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_java/bases/BaseGuiAdmin.fxml"));
                                    Parent root = (Parent) fxmlLoader.load();
                                    Stage stage = new Stage();
                                    primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    primaryStage.close();
                                    stage.setScene(new Scene(root));
                                    stage.setMaximized(true);
                                    stage.getIcons().add(img);
                                    stage.setTitle("User Vol");
                                    stage.show();
                                } catch (IOException e) {
                                    System.err.println(e);
                                }
                            }
                        } else {
                            compte_validator.setText("Compte Désactiver");
                            compte_validator.setVisible(true);
                        }

                    } else {
                        compte_validator.setText("Mot de passe est incorrect");
                        compte_validator.setVisible(true);
                    }
                } else {
                    compte_validator.setText("Utilisateur n'existe pas");
                    compte_validator.setVisible(true);
                }
            }

        } else {
            pwd_validator.setVisible(false);
            compte_validator.setVisible(false);
            email_validator.setText("Ce n'est pas un email valide");
            email_validator.setVisible(true);
            if (tf_pwd.getText().length() < 3) {
                pwd_validator.setText("Votre Mot De Pass doit comporter au moins 3 caractères");
                pwd_validator.setVisible(true);
            }
        }
    }

    @FXML
    private void PasswordForget(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource("ForgetPassword.fxml"));
        vbox.getChildren().removeAll();
        vbox.getChildren().setAll(fxml);
    }

    @FXML
    private void SignUp(MouseEvent event) throws IOException {

       fxml = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        vbox.getChildren().removeAll();
        vbox.getChildren().setAll(fxml);
    }

}
