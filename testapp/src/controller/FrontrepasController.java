/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zcart
 */
public class FrontrepasController implements Initializable {

    @FXML
    private TextField repasprice;
    @FXML
    private TextField repasnom;
    @FXML
    private Label imagepath;
    @FXML
    private TextField repasdescription;
    @FXML
    private TextField repasadresse;
    @FXML
    private ComboBox<?> repascategory;
    @FXML
    private TableView<?> affichagerepas;
    @FXML
    private TableColumn<?, ?> idrepascol;
    @FXML
    private TableColumn<?, ?> nomrepascol;
    @FXML
    private TableColumn<?, ?> descrepascol;
    @FXML
    private TableColumn<?, ?> categoryrepascol;
    @FXML
    private TableColumn<?, ?> pricerepascol;
    @FXML
    private TableColumn<?, ?> adresserepascol;
    @FXML
    private TextField searchrepas;
    @FXML
    private ImageView imagefield;
    @FXML
    private Button btn_boutique;
    @FXML
    private Button btn_maison;
    @FXML
    private Button btn_repas;
    @FXML
    private Button btn_activite;
    @FXML
    private Button btn_event;
    @FXML
    private Button btn_rec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnClickedréserver(ActionEvent event) {
    }

    @FXML
    private void searchrepas(ActionEvent event) {
    }

    @FXML
    private void boutique(ActionEvent event) {
    }

    @FXML
    private void maisonhote(ActionEvent event) {
    }

    @FXML
    private void repas(ActionEvent event) {
         try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/frontrepas.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void activite(ActionEvent event) {
         try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/gui/Front.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void event(ActionEvent event) {
    }

    @FXML
    private void reclmaation(ActionEvent event) {
    }
    
}
