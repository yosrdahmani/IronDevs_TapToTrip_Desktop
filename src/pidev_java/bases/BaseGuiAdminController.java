/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.bases;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pidev_java.entities.User;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class BaseGuiAdminController implements Initializable {

    Stage primaryStage;
    private Parent fxml;
    @FXML
    private Button profile;
    @FXML
    private Circle myCircle;
    @FXML
    private Label nom;
    @FXML
    private Label email;
    @FXML
    private Button btn_dashboard;
    @FXML
    private Button sign_out_btn;
    @FXML
    private ScrollPane scroll_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img = new Image("./pidev_java/assets/user.png");
        myCircle.setFill(new ImagePattern(img));
        User u = User.getInstance();
        nom.setText(u.getNom() + " " + u.getPrenom());
        email.setText(u.getEmail());
        try {
            System.out.println("cccccccc");
            fxml = FXMLLoader.load(getClass().getResource("../admin/adminDashboard.fxml"));
            System.out.println("aaaaaaaaaa");
            scroll_pane.setContent(fxml);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void Profile(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../user/userProfile.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void Dashboard(ActionEvent event) {
        try {
            System.out.println("eeeeeeee");
            fxml = FXMLLoader.load(getClass().getResource("../admin/adminDashboard.fxml"));
            scroll_pane.setContent(fxml);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void Deconnect(ActionEvent event) {
        try {
            User.cleanFreelancer();
            Image img = new Image("pidev_java/assets/logo.jpg");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../user/SignIn.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.close();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(img);
            stage.setTitle("User Vol");
            stage.show();

        } catch (IOException e) {

        }
    }

}
