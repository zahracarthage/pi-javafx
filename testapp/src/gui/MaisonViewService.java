/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.AjouterActiviteController;
import entite.Maison;
import static entite.Maison.filename;
import service.MaisonService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.M;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javax.swing.text.html.parser.DTDConstants.MS;



/**
 *
 * @author Asus
 */
public class MaisonViewService {
    @FXML
    private Button TFAjouter;
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
    private TextField TFnom;
    @FXML
    private TextField TFadresse;
    @FXML
    private TextField TFnum;
    @FXML
    private TextField TFprix;
    @FXML
    private TextField TFnote;
    @FXML
    private Button TFimage;
    @FXML
    private Button Mupdate;
    @FXML
    private Button Mdelete;
    @FXML
    private Button Mrecherche;
    @FXML
    
    private ImageView imagefield;
    public String imagecomp; 
               Integer id;
    @FXML
    private Button imprimer;
    @FXML
    private TextField TFrecherche;
    @FXML
    private Button btn_boutique;
    @FXML
    private Button btn_maison;
    @FXML
    private Button btn_repas;
    @FXML
    private Button btn_user1;
    @FXML
    private Button btn_commande;
    @FXML
    private Button btn_resrepas;
    @FXML
    private Button btn_ac;
    @FXML
    private Button btn_resact;
    @FXML
    private Button btn_maos;
    @FXML
    private Button btn_resmais;
    @FXML
    private Button btn_even;
    @FXML
    private Button btn_rec;
               
    @FXML
    private void OnClickedPrint(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.tabMaison;
       
       
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
    public void initialize(URL url, ResourceBundle rb) {
        MaisonService ms = new MaisonService();
     
     
        
    }     

    @FXML
    private void ajouterMaison(ActionEvent event) throws Exception {
             
        
        String nom = TFnom.getText();
        String adresse = TFadresse.getText();
        int num= Integer.parseInt(TFnum.getText());
        int prix= Integer.parseInt(TFprix.getText());
        int note= Integer.parseInt(TFnote.getText());
        String image = Maison.filename;
        
       

        Maison m = new Maison(nom,adresse,num,prix,note,image);
        MaisonService mS = new MaisonService();
        if (mS.addMaison(m)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succès");
        alert.setHeaderText(null);
        alert.setContentText("L'ajout a été effectué avec succès ");
        alert.showAndWait();
        afficherMaison();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Oups! un echec d'ajout");
        alert.showAndWait();   
        afficherMaison();
        }
        
    }
        
    @FXML
        public void modifierMaison(ActionEvent event) throws SQLException {
        Maison M= tabMaison.getSelectionModel().getSelectedItem();
        
        String p = TFprix.getText();
         int prix = Integer.parseInt(p);
         String n = TFnum.getText();
         int num = Integer.parseInt(n);
         String no = TFnote.getText();
         int note = Integer.parseInt(no);
         
       
        M.setNom(TFnom.getText());

        M.setAdresse(TFadresse.getText());
        
        M.setPrix(prix);
        M.setNum(num);
        M.setNote(note);
        M.setImage(imagecomp);
        
        MaisonService ms = new MaisonService();
        
        
          try {
                       
        ms.updateMaison(M);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.show();
                        alert.setTitle("updated !");
                        alert.setContentText("updated succesfully");
                        tabMaison.refresh();

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed succesfully");

                    }
       
       tabMaison.setItems(ms.getMaisonlistnew());
    }

    @FXML
    private void selectMaison(MouseEvent event) {
    Maison M= tabMaison.getSelectionModel().getSelectedItem();
     
    TFnom.setText(M.getNom());
    TFadresse.setText(M.getAdresse());
    TFnum.setText(String.valueOf(M.getNum()));
    TFprix.setText(String.valueOf(M.getPrix()));
    TFnote.setText(String.valueOf(M.getNote()));
     
   
    }



    @FXML
    private void supprimerMaison(ActionEvent event) {
         Maison M= tabMaison.getSelectionModel().getSelectedItem();
        MaisonService mS = new MaisonService();
        if (mS.deleteMaison(M)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succès");
        alert.setHeaderText(null);
        alert.setContentText("La suppression a été effectuée avec succès");
        alert.showAndWait();
        afficherMaison();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Oups! un echec de suppression");
        alert.showAndWait();   
        afficherMaison();
        }
    }
    private void afficherMaison() {
        MaisonService mS = new MaisonService();
        ObservableList<Maison> liste = mS.showMaison();
        colID.setCellValueFactory(new PropertyValueFactory<Maison, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Maison, String>("nom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Maison, String>("adresse"));
        colNum.setCellValueFactory(new PropertyValueFactory<Maison, Integer>("num"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Maison, Integer>("prix"));
        colNote.setCellValueFactory(new PropertyValueFactory<Maison, Integer>("note"));
        colImage.setCellValueFactory(new PropertyValueFactory<Maison, String>("image"));
      
       
        tabMaison.setItems(liste);
    }

    @FXML
    private void uploadimg(ActionEvent event) {
FileChooser f = new FileChooser();
        String img;

        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img) {};
            
            imagefield.setImage(i);
            imagecomp = fc.toString();
            System.out.println(imagecomp);
            int index = imagecomp.lastIndexOf('\\');
            if (index > 0) {
                filename = imagecomp.substring(index + 1);
            }

            Maison.filename = "C:\\Users\\Asus\\Pictures\\test pro" + filename;
            //se.sendphp(fc.getAbsolutePath());
        }
        imagefield.setFitHeight(215);
        imagefield.setFitWidth(265);
        //..\img\google.png

        //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img
        Maison.pathfile = fc.getAbsolutePath();
    }
    
    @FXML
    private void rechercherMaison(ActionEvent event) throws Exception {
        MaisonService maison = new MaisonService();
        ObservableList<Maison> liste = maison.findMaisonByName(TFrecherche.getText());
        colID.setCellValueFactory(new PropertyValueFactory<Maison, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Maison, String>("nom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Maison, String>("adresse"));
        colNum.setCellValueFactory(new PropertyValueFactory<Maison, Integer>("num"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Maison, Integer>("prix"));
        colNote.setCellValueFactory(new PropertyValueFactory<Maison, Integer>("note"));
        colImage.setCellValueFactory(new PropertyValueFactory<Maison, String>("image"));
        tabMaison.setItems(liste);
    }
    
     @FXML
    private void OnClickedconsulterfront(Event event) {
        try {
                   
          Parent parent = FXMLLoader.load(getClass().getResource("/Maison/Views/FrontMaison.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MaisonViewService.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
    
    
  /*    @FXML
    private void OnClickedstat(Event event) {
        try {
                   
          Parent parent = FXMLLoader.load(getClass().getResource("/Maison/Views/Stat.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
*/
    
    @FXML
    private void resmaison(ActionEvent event) {
         try {
                   
          Parent parent = FXMLLoader.load(getClass().getResource("ReservationView.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MaisonViewService.class.getName()).log(Level.SEVERE, null, ex);
        
        }
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
    private void resrepas(ActionEvent event) {
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
    private void btn_event(ActionEvent event) {
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

