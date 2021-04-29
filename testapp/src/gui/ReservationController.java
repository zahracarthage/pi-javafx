/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.AjouterActiviteController;
import service.ResactivitesService;
import entite.Resactivites;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zannou
 */
public class ReservationController implements Initializable {

    @FXML
    private TableView<Resactivites> AffichageResActivites;
    @FXML
    private TextField nbpersonnes;
    @FXML
    private DatePicker dateres;
    @FXML
    private TableColumn<Resactivites, String> colidres;
    @FXML
    private TableColumn<Resactivites, String> colnbpersonnes;
    @FXML
    private TableColumn<Resactivites, String> coldate;
    @FXML
    private Button modifierres;
    @FXML
    private Button Supprimer;
   
    int idresactivites;
    @FXML
    private Button btn_boutique;
    @FXML
    private Button btn_event;
    @FXML
    private Button btn_maison;
    @FXML
    private Button btn_repas;
    @FXML
    private Button btn_user1;
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
    @FXML
    private Button btn_activites;
    @FXML
    private Button btn_ressrep;
    @FXML
    private Button btn_rec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResactivitesService ras = new ResactivitesService();
      
              AffichageResActivites.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                idresactivites = ras.liste3()
                        .get(AffichageResActivites.getSelectionModel().getSelectedIndex())
                        .getIdresactivites();
              
                nbpersonnes.setText(String.valueOf(ras.liste3()
                        .get(AffichageResActivites.getSelectionModel().getSelectedIndex())
                        .getNbpersonnes()));
                };
          
              
         }); 
     
         ObservableList<Resactivites> listres;
         
        try {
            listres = ras.getResactivitesList();

            colidres.setCellValueFactory(new PropertyValueFactory<>("idresactivites"));
            coldate.setCellValueFactory(new PropertyValueFactory("dateres"));
            colnbpersonnes.setCellValueFactory(new PropertyValueFactory<>("nbpersonnes"));
           DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
            
           AffichageResActivites.setItems(listres);

            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

               
     
    }    

    @FXML
    private void modifierRes(ActionEvent event) throws SQLException {
         Resactivites r = (Resactivites) AffichageResActivites.getSelectionModel().getSelectedItem();
        
        
         String nb = nbpersonnes.getText();
         int nbpersonnes= Integer.parseInt(nb);
       
        r.setNbpersonnes(nbpersonnes);
        r.setDateres(Date.valueOf(dateres.getValue().toString()));
        ResactivitesService ras = new ResactivitesService();
        
        
          try {
                       
        ras.updateresactivite (r);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.show();
                        alert.setTitle("updated !");
                        alert.setContentText("updated succesfully");
                        AffichageResActivites.refresh();

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed succesfully");

                    }
       
       AffichageResActivites.setItems(ras.getResactiviteslistnew());
    }

    @FXML
    private void supprimerRes(ActionEvent event) {
          Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous vraiment supprimer cette reservation  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ResactivitesService ras = new ResactivitesService();
            try {
                
                ras.deleteresactivite(idresactivites);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
               AffichageResActivites.setItems(ras.getResactivitesList());
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("Delete Failed !");
            }

        } else {
            alert2.close();
        }
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
        try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/gestiondesrepas.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void user(ActionEvent event) {
         try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/gui/AdminBack.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gestionact(ActionEvent event) {
         try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/gui/AddActivite.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       }

    @FXML
    private void resrepa(ActionEvent event) {
        try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/gestionresrepas.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reclamation(ActionEvent event) {
        try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/gestionreclamation.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

