/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.AjouterActiviteController;
import entite.Reservation;
import java.io.IOException;
import service.MaisonService;
import service.ReservationService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



/**
 *
 * @author Asus
 */
public class ReservationViewService {

    @FXML
    private Button TFAjouter;
    @FXML
    private TableView<Reservation> tabReservation;
    @FXML
    private TableColumn<Reservation, Integer> colID;
    @FXML
    private TableColumn<Reservation, Date> colDateentre;
    @FXML
    private TableColumn<Reservation, Date> colDatesortie;
    @FXML
    private DatePicker DPdateentre;
    @FXML
    private DatePicker DPdatesortie;
    private ListView listMaisonRes;
    @FXML
    private Button Aupdate;
    @FXML
    private Button Adelete;
    @FXML
    private Button btn_boutique;
    @FXML
    private Button btn_commande;
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
    private Button btnact;
    @FXML
    private Button btn_resact;
    @FXML
    private Button btn_resmai;
    @FXML
    private Button btn_event;
    
      public void initialize(URL url, ResourceBundle rb) {
     
     
        afficherReservation();
        
        
    }              
    
    private void OnClickedPrint(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.tabReservation;
       
       
     if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
     }
     }
    }
   

    /**
     * Initializes the controller class.
     */
    

    @FXML
    private void ajouterReservation(ActionEvent event) {    
       LocalDate dateEntree = DPdateentre.getValue();
       Date dateEntreeResult = Date.valueOf(dateEntree);
       LocalDate dateSortie = DPdatesortie.getValue();
       Date dateSortieResult = Date.valueOf(dateSortie);
       

        Reservation r = new Reservation((dateEntreeResult),(dateSortieResult));
        ReservationService rS = new ReservationService();
        if (rS.addReservation(r)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succès");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout a été effectué avec succès ");
        alert.showAndWait();
        afficherReservation();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Oups! un echec d'ajout");
        alert.showAndWait();   
        afficherReservation();
        }
        
    }
        
    @FXML
        public void modifierReservation(ActionEvent event) throws SQLException {
        Reservation R= tabReservation.getSelectionModel().getSelectedItem();
        
       LocalDate dateEntree = DPdateentre.getValue();
       Date dateEntreeResult = Date.valueOf(dateEntree);
       LocalDate dateSortie = DPdatesortie.getValue();
       Date dateSortieResult = Date.valueOf(dateSortie);
        
        R.setDateentre(dateEntreeResult);
        R.setDatesortie(dateSortieResult);
        
        ReservationService rs = new ReservationService();
        
        
          try {
                       
        rs.updateReservation(R);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.show();
                        alert.setTitle("updated !");
                        alert.setContentText("updated succesfully");
                        tabReservation.refresh();

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed succesfully");

                    }
       
       tabReservation.setItems(rs.getReservationlistnew());
    }

    
     
   
 /*@FXML
    private void selectReservation(MouseEvent event) {
    Reservation r = tabReservation.getSelectionModel().getSelectedItem();
    
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
     String dateEntre = r.getDateentre();
     LocalDate dateEntreFinal = LocalDate.parse(dateEntre, formatter);
     DPdateentre.setValue(dateEntreFinal);
    
     String dateSortie = r.getDatesortie();
     LocalDate dateSortieFinal = LocalDate.parse(dateSortie, formatter);
     DPdatesortie.setValue(dateSortieFinal);
}*/

    @FXML
     void supprimerReservation(ActionEvent event) {
         Reservation R= tabReservation.getSelectionModel().getSelectedItem();
        ReservationService rS = new ReservationService();
        if (rS.deleteReservation(R)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succès");
        alert.setHeaderText(null);
        alert.setContentText("La suppression a été effectuée avec succès");
        alert.showAndWait();
        afficherReservation();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Oups! un echec de suppression");
        alert.showAndWait();   
        afficherReservation();
        }
    }
    private void afficherReservation() {
        ReservationService rS = new ReservationService();
        ObservableList<Reservation> liste = rS.showReservation();
        colID.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id"));
        colDateentre.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("dateentre"));
        colDatesortie.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("datesortie"));
      
       
        tabReservation.setItems(liste);
    }
    
    private void afficherReservationMaison() {
        MaisonService mS = new MaisonService();
        ObservableList<Integer> liste = mS.showMaisonIds();
        listMaisonRes.setItems(liste);
    
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
    private void commande(ActionEvent event) {
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
    private void act(ActionEvent event) {
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
    private void resact(ActionEvent event) {
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
    private void event(ActionEvent event) {
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
    

    }

