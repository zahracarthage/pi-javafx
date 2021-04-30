/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.repas;
import entite.reservationrepas;
import java.awt.SystemTray;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.reservationrepasService;

/**
 * FXML Controller class
 *
 * @author zcart
 */
public class GestionresrepasController implements Initializable {

    public                Integer id, nbrp,nomr;

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
    private TableView<reservationrepas> affichageresrep;
    @FXML
    private TableColumn<reservationrepas,String> idtabres;
    @FXML
    private TableColumn<reservationrepas,String> idrtabres;
    @FXML
    private TableColumn<reservationrepas,String> datetabres;
    @FXML
    private TableColumn<reservationrepas,String> nbrtabres;
    @FXML
    private TableColumn<reservationrepas,String> msgtabres;
    @FXML
    private TableColumn<reservationrepas,String> nomtabres;
    @FXML
    private TableColumn<reservationrepas,String> emailtabres;
    @FXML
    private TableColumn<reservationrepas,String> phonetabres;
    @FXML
    private DatePicker dateres;
    @FXML
    private TextField nbrpersonnes;
    @FXML
    private TextField emailresrep;
    @FXML
    private TextField nomresrep;
    @FXML
    private TextField phoneresrep;
    @FXML
    private Button btn_ajoutreservation;
    @FXML
    private Button btn_supprimerreservation;
    @FXML
    private TextArea messageresrep;
    @FXML
    private ComboBox<String> idr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        reservationrepasService rs = new reservationrepasService();
        
        try {
            idr.setItems(FXCollections.observableArrayList(rs.listidr()));
        } catch (SQLException ex) {
            Logger.getLogger(GestionresrepasController.class.getName()).log(Level.SEVERE, null, ex);
        }
                           System.out.println(idr);
                           
                           
                       
        
         affichageresrep.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                id = rs.liste2()
                        .get(affichageresrep.getSelectionModel().getSelectedIndex())
                        .getId();
                
                //System.out.println(iddd);
                
                
                 nbrpersonnes.setText(String.valueOf(rs.liste2()
                        .get(affichageresrep.getSelectionModel()
                            .getSelectedIndex()
                        ).getNbrpersonnes()
                          ));
                
                 
              
                 
                idr.setValue(String.valueOf(rs.liste2()
                        .get(affichageresrep.getSelectionModel().
                                getSelectedIndex())
                        .getIdr()));
                
                
               
                
                dateres.setValue(LocalDate.parse(rs.liste2()
                    .get(affichageresrep.getSelectionModel()
                            .getSelectedIndex())
                    .getDate()

                ));
                
                messageresrep.setText(rs.liste2()
                        .get(affichageresrep.getSelectionModel()
                                .getSelectedIndex())
                        .getMessage()
                ); 
  
                nomresrep.setText(rs.liste2()
                        .get(affichageresrep.getSelectionModel()
                                .getSelectedIndex())
                        .getNom()
                ); 
                
                
                emailresrep.setText(rs.liste2()
                        .get(affichageresrep.getSelectionModel()
                            .getSelectedIndex()
                        ).getEmail()
                          
                );
                
                phoneresrep.setText(String.valueOf(rs.liste2()
                        .get(affichageresrep.getSelectionModel()
                            .getSelectedIndex()
                        ).getPhone()
                          
                ));
                
                };
            
               
         }); 
       
        
         ObservableList<reservationrepas> list2;      
            try {
            list2 = rs.getresrepasliste();
            
            
    
            idtabres.setCellValueFactory(new PropertyValueFactory<>("id"));
            idrtabres.setCellValueFactory(new PropertyValueFactory<>("idr"));
            datetabres.setCellValueFactory(new PropertyValueFactory<>("date"));
            nbrtabres.setCellValueFactory(new PropertyValueFactory<>("nbrpersonnes"));
            msgtabres.setCellValueFactory(new PropertyValueFactory<>("message"));
            nomtabres.setCellValueFactory(new PropertyValueFactory<>("nom"));
            emailtabres.setCellValueFactory(new PropertyValueFactory<>("email"));
            phonetabres.setCellValueFactory(new PropertyValueFactory<>("phone"));

            
            
            affichageresrep.setItems(list2);

            
        } catch (SQLException ex) {
            Logger.getLogger(GestionresrepasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // TODO
    }    

    @FXML
    private void boutique(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/commande.fxml"));
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
    private void commandes(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/commande.fxml"));
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
    private void maison(ActionEvent event) {
         try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/gui/MaisonView.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void repas(ActionEvent event) {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AddActivite.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void resactivite(ActionEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AddActivite.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void resmaison(ActionEvent event) {
          try{
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/gui/ReservationView.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void resevent(ActionEvent event) {
    }

    @FXML
    private void evenement(ActionEvent event) {
        try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/gui/EventBack.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void dateres(ActionEvent event) {
    }

    @FXML
    private void ajoutreservation(ActionEvent event) throws SQLException {
        
                    String idrep = idr.getValue();
                    int idrepas = Integer.parseInt(idrep);

          
          
          String nbr= nbrpersonnes.getText();
          int nbrpersonnes = Integer.parseInt(nbr);
                  
                  
          String datee = dateres.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

          
 reservationrepas rr = new reservationrepas(idrepas,datee,nbrpersonnes, messageresrep.getText(),nomresrep.getText(),emailresrep.getText(),phoneresrep.getText());
        
        reservationrepasService rrs = new reservationrepasService();
        
        
        
        
         try {
              
             
             rrs.ajouterreservationrepas(rr);
             if (SystemTray.isSupported()) {
                    //TrayIconDemo td = new TrayIconDemo();
                    rrs.displayTray();
                    //System.out.println("aaaaa");
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Addeded!");
                alert.show();
                
             }
                   else {
                    System.err.println("System tray not supported!");
                }
             affichageresrep.refresh();
                
              
                
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
         affichageresrep.setItems(rrs.getresrepasliste());
    }

    @FXML
    private void supprimerreservation(ActionEvent event) throws SQLException {
          Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer cette reservation  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            reservationrepasService rs = new reservationrepasService();
            rs.supprimerreservation(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText(null);
            alert.setContentText(" Done!");
            alert.show();
         affichageresrep.setItems(rs.getresrepasliste());

            
          //  affichageresrep.setItems(rs.getresrepasliste());

        } else {
            alert2.close();
        }
        
        
    }
    }
    
