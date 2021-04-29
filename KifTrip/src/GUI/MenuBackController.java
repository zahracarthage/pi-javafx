/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class MenuBackController implements Initializable {
    @FXML
    private Button btn_boutique;
    @FXML
    private Button btn_event;
    @FXML
    private Button btn_maison;
    @FXML
    private Button btn_repas;
    @FXML
    private Button btn_activite;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_user1;
    @FXML
    private Button btn_user11;
    @FXML
    private Button btn_user111;
    @FXML
    private Button btn_user1111;
    @FXML
    private Button btn_user1112;
    @FXML
    private Button btn_user1113;
    @FXML
    private Button btn_user11131;
    @FXML
    private Button btn_user11132;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void boutique(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) {
    }

    @FXML
    private void maison(ActionEvent event) {
    }

    @FXML
    private void repas(ActionEvent event) {
    }

    @FXML
    private void activite(ActionEvent event) {
    }

    @FXML
    private void user(ActionEvent event) throws IOException, Exception {

        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("AdminBack.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            AdminBackController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }
    
}
