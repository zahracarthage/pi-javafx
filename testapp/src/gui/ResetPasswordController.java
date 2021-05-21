/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.Start.Userconnected;
import service.UserService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class ResetPasswordController implements Initializable {
    @FXML
    private TextField pass1;
    @FXML
    private TextField pass2;
    @FXML
    private TextField pass3;
    @FXML
    private ImageView ImageUserLogedIn;
    @FXML
    private Label NomPrenom;
    UserService su = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file1 = new File("C:/wamp64/Image_Pi/" + Userconnected.getImage());
        ImageUserLogedIn.setImage(new Image(file1.toURI().toString()));

        NomPrenom.setText(Userconnected.getUsername());
    }    

    @FXML
    public void Reset(ActionEvent event) throws IOException, Exception {
        if (pass1.getText().equals(Userconnected.getPassword()) && pass2.getText().equals(pass3.getText())) {
            if (su.ResetPassword(pass2.getText(), Userconnected.getId())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Mr/Mme" + Userconnected.getUsername() + " " + Userconnected.getEmail() + " Votre mot de passe a été bien modifier !", ButtonType.CLOSE);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, " il ya un petit probleme ressayer plus tard !", ButtonType.CLOSE);
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, " l'une des mot de passes ne correspand pas merci de ressayer !", ButtonType.CLOSE);
            alert.show();
        }
    }

    @FXML
    public void DeleteAccount(MouseEvent event) throws IOException, Exception {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression ");
        alert.setContentText("Voulez-vous vraiment supprimer Votre compte ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (su.delete(Userconnected)) {
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                    LoginUserController cntr = LOADER.getController();
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {

                }
            }

        }

    }

    @FXML
    public void Logout(MouseEvent event) throws IOException, Exception {
        Userconnected.setId(0);
        Userconnected.setUsername("");
        Userconnected.setEmail("");
        Userconnected.setPassword("");
        Userconnected.setImage("");
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            LoginUserController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    public void ResetPassword(MouseEvent event) throws IOException, Exception {
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            ResetPasswordController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    public void UpdateProfile(MouseEvent event) throws IOException, Exception {
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("UpdateProfil.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            UpdateProfileController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }
    
    
    @FXML
    private void BackToPage(ActionEvent event) throws IOException, Exception {
     
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("MenuBack.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      MenuBackController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
            
        
      

    
    }
    
}
