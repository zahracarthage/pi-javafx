/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.AjouterActiviteController;
import entite.Maison;
import service.MaisonService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FrontMaisonController implements Initializable {

    @FXML
    private TableView<Maison> tabMaison;
    @FXML
    private TableColumn<Maison,Integer> colID;
    @FXML
    private TableColumn<Maison, String> colNom;
    @FXML
    private TableColumn<Maison, String> colAdresse;
    @FXML
    private TableColumn<Maison, Integer> colNum;
    @FXML
    private TableColumn<Maison, Integer> colPrix;
     @FXML
    private TableColumn<Maison, Integer> colNote;
      @FXML
    private TableColumn<Maison, String> colImage;
    @FXML
    private Button btn_event;
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaisonService ms = new MaisonService();

         ObservableList<Maison> list;
         
         list = ms.showMaison();
         colImage.setPrefWidth(80);
         colID.setCellValueFactory(new PropertyValueFactory<>("Id"));
         colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
         colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
         colPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
         colNote.setCellValueFactory(new PropertyValueFactory<>("Note"));
         colImage.setCellValueFactory(new PropertyValueFactory<>("Image"));
         tabMaison.setItems(list);

    }   
   @FXML
    private void OnClickedréserver(Event event) {
        try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/FrontReservation.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
          
            stage.setScene(scene);
          
            stage.initStyle(StageStyle.UTILITY);
          
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void event(ActionEvent event) {
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
}


  