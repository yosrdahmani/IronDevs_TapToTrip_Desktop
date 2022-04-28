/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev_java.entities.User;
import pidev_java.services.UserService;
import pidev_java.utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class forgetPasswordController implements Initializable {

    @FXML
    private AnchorPane vbox;
    @FXML
    private TextField tf_email;
    @FXML
    private Label email_validator;
    @FXML
    private Label compte_validator;
    @FXML
    private Label compte_validator1;
    private Parent fxml;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sendMail(ActionEvent event) throws IOException {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tf_email.getText());
        if (matcher.matches() == true) {
            email_validator.setVisible(false);
            User s = new UserService().getUser(tf_email.getText());
            if (s.getEmail() != null) {
                int id = new UserService().getUser( tf_email.getText()).getId();
                try {
                    JavaMail mail = new JavaMail();
                    mail.recipient = tf_email.getText();
                    mail.id = id;
                    mail.start();
                    notification();
                    fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                    vbox.getChildren().removeAll();
                    vbox.getChildren().setAll(fxml);

                } catch (Exception ex) {
                    System.out.println("error sending mail");
                }

            } else {
                compte_validator.setText("Email n'exist pas");
                compte_validator.setVisible(true);
            }

        } else {
            email_validator.setText("Ce n'est pas un email valide");
            email_validator.setVisible(true);
        }
    }

    @FXML
    private void signIn(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        vbox.getChildren().removeAll();
        vbox.getChildren().setAll(fxml);
    }

    public void notification() {
        // Image img = new Image("tt.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Email Envoyé")
                .text("Un email de changement de mot de passe à été envoyé")
                //  .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked on notification");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.showInformation();

    }

}
