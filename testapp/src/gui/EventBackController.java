/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Event;
import service.EventService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import controller.AjouterActiviteController;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author 21651
 */
public class EventBackController implements Initializable {

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
    private Button btn_user1;
    @FXML
    private Button TFAjouter;
   @FXML
    private TableView<Event> tabAgence;
    @FXML
    private TableColumn<Event,Integer> colID;
    @FXML
    private TableColumn<Event, String> colNom;
    @FXML
    private TableColumn<Event,String > colDate;
    @FXML
    private TableColumn<Event, Integer> colCapacite;
    @FXML
    private TableColumn<Event, String> colDesc;
    @FXML
    private TableColumn<Event, String> colAdresse;
     @FXML
    private TextField TFnom;
    @FXML
    private DatePicker DPdate;
    @FXML
    private TextField TFcapacite;
    @FXML
    private TextField TFdescription;
    @FXML
    private TextField TFadresse;
    @FXML
    private Button Aupdate;
    @FXML
    private Button Adelete;
    @FXML
    private Button pdf;
    @FXML
    private Button btn_commande;
    @FXML
    private Button btn_resrep;
    @FXML
    private Button btn_rec;
    @FXML
    private Button btn_resact;
    @FXML
    private Button btn_resmais;

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
    private void maison(ActionEvent event) {
    }

    @FXML
    private void repas(ActionEvent event) {
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
    
    
  private void afficherEvent() {
        EventService aS = new EventService();
        ObservableList<Event> liste = aS.showEvent();
        colID.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Event, String>("nom"));
        colDate.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        colCapacite.setCellValueFactory(new PropertyValueFactory<Event, Integer>("capacite"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Event, String>("adresse"));
        tabAgence.setItems(liste);
    }
    @FXML
    private void ajouterEvent(ActionEvent event) throws IOException {
             
        
LocalDate d = DPdate.getValue();


    //    String date = d.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
   
        String date = d.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        
        String nom = TFnom.getText();
        String description = TFdescription.getText();
        int capacite = Integer.parseInt(TFcapacite.getText());
        String adresse = TFadresse.getText();
       

        Event A = new Event(nom,date,capacite,description,adresse);
        EventService aS = new EventService();
         String path =aS.QR(nom);
         aS.sendEmail(path);
        if (aS.addEvent(A)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'un new event a été effectué avec succées");
        alert.showAndWait();
        afficherEvent();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout d'un new event n'a pas été effectué!");
        alert.showAndWait();   
        afficherEvent();
        }
    }

    @FXML
    private void selectEvent(MouseEvent event) {
          Event A= tabAgence.getSelectionModel().getSelectedItem();
    TFnom.setText(A.getNom());
    TFdescription.setText(A.getDescription());
    TFcapacite.setText(String.valueOf(A.getCapacite()));
    TFadresse.setText(A.getAdresse());
    
    }

    @FXML
    private void modifierEvent(ActionEvent event) {
          Event A= tabAgence.getSelectionModel().getSelectedItem();
           
LocalDate d = DPdate.getValue();
        String date = d.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
         String nom = TFnom.getText();
        String description = TFdescription.getText();
        int capacite = Integer.parseInt(TFcapacite.getText());
        String adresse = TFadresse.getText();
       
        A.setNom(nom);
        A.setDate(date);
        A.setAdresse(adresse);
        A.setCapacite(capacite);
        A.setDescription(description);
        EventService aS = new EventService();
        if (aS.updateEvent(A)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La modification d'event a été effectué avec succées");
        alert.showAndWait();
        afficherEvent();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La modufication d'event n'a pas été effectué!");
        alert.showAndWait();   
        afficherEvent();
        }
    }

    @FXML
    private void supprimerEvent(ActionEvent event) {
    Event A= tabAgence.getSelectionModel().getSelectedItem();
        EventService aS = new EventService();
        if (aS.deleteEvent(A)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La suppression d'event a été effectué avec succées");
        alert.showAndWait();
        afficherEvent();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La supression d'event n'a pas été effectué!");
        alert.showAndWait();   
        afficherEvent();
        }
    }

    @FXML
    private void Pdf(ActionEvent event) {
           String path="";
        
        JFileChooser j=new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       int x=j.showSaveDialog(j);
        if(x==JFileChooser.APPROVE_OPTION)
        {
            path=j.getSelectedFile().getPath();
        
        }
       
        Document doc =new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path+"/EVENT.pdf"));
            doc.open();
            
            PdfPTable table= new PdfPTable(6);
            table.addCell("ID");
            table.addCell("NOM");
            table.addCell("DATE");
            table.addCell("CAPACITE");
            table.addCell("DESCRIPTION");
            table.addCell("ADRESSE");

           

           EventService s= new EventService();
            for( int i=0 ; i < s.rowEvent();i++)
            {
           
                String ID =tabAgence.getColumns().get(0).getCellObservableValue(i).getValue().toString();
                 String Nom =tabAgence.getColumns().get(1).getCellObservableValue(i).getValue().toString();
                  String Adresse =tabAgence.getColumns().get(5).getCellObservableValue(i).getValue().toString();
  //                 String Sujet =tabPlan.getColumns().get(4).getCellObservableValue(i).getValue().toString();
                    String Description =tabAgence.getColumns().get(4).getCellObservableValue(i).getValue().toString();
                     String Date =tabAgence.getColumns().get(2).getCellObservableValue(i).getValue().toString();
                      String Capacite =tabAgence.getColumns().get(3).getCellObservableValue(i).getValue().toString();
                                       
             table.addCell(ID);
            table.addCell(Nom);
            table.addCell(Adresse);
//            table.addCell(Sujet);
            table.addCell(Description);
            table.addCell(Date);
            table.addCell(Capacite);
            
            }
            
            doc.add(table);
            
            
            
        } catch (FileNotFoundException ex) {
            
        } catch (DocumentException ex) {
            
        }
        
        doc.close();
    }

    @FXML
    private void commande(ActionEvent event) {
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
    private void resact(ActionEvent event) {
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
    private void resmais(ActionEvent event) {
    }

    }
    

