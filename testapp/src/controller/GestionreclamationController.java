/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.reclamationService;
import utils.mail;

/**
 * FXML Controller class
 *
 * @author zcart
 */
public class GestionreclamationController implements Initializable {
    public String imagecomp; 
               Integer id, idd; 

    @FXML
    private Button btn_boutique;
    @FXML
    private Button btn_commandes;
    @FXML
    private Button btn_maison;
    @FXML
    private Button btn_repas;
    @FXML
    private Button btn_resrepas;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_user1;
    @FXML
    private Button btn_activite;
    @FXML
    private Button btn_resact;
    @FXML
    private Button btn_user1111;
    @FXML
    private Button btn_resmais;
    @FXML
    private Button btn_resevenement;
    @FXML
    private Button btn_evenements;
    @FXML
    private ImageView imagefield;
    @FXML
    private Button ajouterreclamationbtn;
    @FXML
    private TextField reclamationnom;
    @FXML
    private TextField reclamationprenom;
    @FXML
    private TextField reclamationemail;
    @FXML
    private ComboBox<String> reclamationsujet;
    ObservableList<String> op = FXCollections.observableArrayList(
                "paiement",
                "probleme de connexion",
                "SAV",
                "autre"
        );
    @FXML
    private TextArea reclamationmessage;
    @FXML
    private TableView<reclamation> affichagereclamation;
    @FXML
    private TableColumn<reclamation, String> idreccol;
    @FXML
    private TableColumn<reclamation, String> nomrec;
    @FXML
    private TableColumn<reclamation, String> prencolrec;
    @FXML
    private TableColumn<reclamation, String> emailcolrec;
    @FXML
    private TableColumn<reclamation, String> sujetcolrec;
    @FXML
    private TableColumn<reclamation, String> etatcolrec;
    @FXML
    private TableColumn<reclamation, String> msgcolrec;
    @FXML
    private Button modifierreclamationbtn;
    @FXML
    private Button supprimerreclamationbtn;
                   reclamationService recs = new reclamationService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    reclamationsujet.setItems(op);

                    
              affichagereclamation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                id = recs.readAll()
                        .get(affichagereclamation.getSelectionModel().getSelectedIndex())
                        .getId();
                //System.out.println(iddd);
                
                reclamationmessage.setText(recs.readAll()
                        .get(affichagereclamation.getSelectionModel()
                                .getSelectedIndex())
                        .getMessage()
                ); 
                
                reclamationnom.setText(recs.readAll()
                        .get(affichagereclamation.getSelectionModel().getSelectedIndex())
                        .getNom());
                
                reclamationprenom.setText(recs.readAll()
                         .get(affichagereclamation.getSelectionModel().getSelectedIndex())
                        .getPrenom());
                
                
                
                reclamationemail.setText(recs.readAll()
                        .get(affichagereclamation.getSelectionModel()
                                .getSelectedIndex())
                        .getEmail());
                
                reclamationsujet.setValue(recs.readAll()
                        .get(affichagereclamation.getSelectionModel()
                                .getSelectedIndex())
                        .getSubject()
                );
                
  
                
               
                };
            
         
               
         }); 
       
         ObservableList<reclamation> list = null;

         
        try {
            list = recs.getReclamationList(); // TODO
        } catch (SQLException ex) {
            Logger.getLogger(GestionreclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         idreccol.setCellValueFactory(new PropertyValueFactory<>("id"));
         nomrec.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prencolrec.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         emailcolrec.setCellValueFactory(new PropertyValueFactory<>("email"));
         sujetcolrec.setCellValueFactory(new PropertyValueFactory<>("subject"));
         msgcolrec.setCellValueFactory(new PropertyValueFactory<>("message"));
         etatcolrec.setCellValueFactory(new PropertyValueFactory<>("etat"));
         affichagereclamation.setItems(list);
    }    

    @FXML
    private void boutique(ActionEvent event) {
    }

    @FXML
    private void commandes(ActionEvent event) {
    }

    @FXML
    private void maison(ActionEvent event) {
    }

    @FXML
    private void repas(ActionEvent event) throws IOException {
        
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/gestiondesrepas.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
          //  stage.setTitle("Blocker/Deblocker Utilisateur");
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

          
       
    }

    @FXML
    private void resrepas(ActionEvent event) {
        
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/gestionresrepas.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
          //  stage.setTitle("Blocker/Deblocker Utilisateur");
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
    private void activite(ActionEvent event) {
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
    private void resactivite(ActionEvent event) {
        try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/gui/Reservation.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void resmaison(ActionEvent event) {
    }

    @FXML
    private void resevent(ActionEvent event) {
    }

    @FXML
    private void evenement(ActionEvent event) {
    }

    @FXML
    private void ajouterreclamation(ActionEvent event) throws Exception {
          reclamation rec = new reclamation(reclamationnom.getText(),reclamationprenom.getText(),reclamationemail.getText(),reclamationsujet.getValue().toString(),reclamationmessage.getText());
        
        reclamationService rs = new reclamationService();
        
        rs.ajoutereclamation(rec);
        
         try {
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Addeded!");
                alert.show();
                
              
                
                affichagereclamation.refresh();
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
            affichagereclamation.setItems(rs.getReclamationList());
            mail.sendMail(reclamationemail.getText());
        
    }

    @FXML
    private void modifierrecbtn(ActionEvent event) {
    }

    @FXML
    private void supprimerrecbtn(ActionEvent event) {
         Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer cet reclamation  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            reclamationService rs = new reclamationService();
            try {
                rs.supprimerreclamation(id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                affichagereclamation.setItems(rs.getReclamationList());
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
    
}
