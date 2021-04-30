/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import controller.AjouterActiviteController;
import service.ActivitesService;
import service.ResactivitesService;
import entite.Activites;
import entite.Resactivites;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 * FXML Controller class
 *
 * @author Zannou
 */
public class FrontController implements Initializable {

    @FXML
    private TableView<Activites> listeActivites;
    @FXML
    private TableColumn<Activites, String> idactcol;
    @FXML
    private TableColumn<Activites, String> nomactcol;
    @FXML
    private TableColumn<Activites, String> descactcol;
    @FXML
    private TableColumn<Activites, String> categorieactcol;
    @FXML
    private TableColumn<Activites, String> dateactcol;
    @FXML
    private TableColumn<Activites, String> prixactcol;
    @FXML
    private TableColumn<Activites, Image> img;
    @FXML
    private Button btn_user11;
    @FXML
    private TextField prix;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField image;
    @FXML
    private TextField categorie;
    @FXML
    private TextField date;
    @FXML
    private TextField id;
    private ComboBox<String> idetranger;
    @FXML
    private Button btn_mais;
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ActivitesService as = new ActivitesService();
        
        ResactivitesService rs = new ResactivitesService();

        
                     
              listeActivites.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                id .setText(String.valueOf(as.liste2()
                        .get(listeActivites.getSelectionModel().getSelectedIndex()
                        ).getIdact()
                          ));
                //System.out.println(iddd);
                nom.setText(as.liste2()
                        .get(listeActivites.getSelectionModel().getSelectedIndex())
                        .getNom());
                
                description.setText(as.liste2()
                        .get(listeActivites.getSelectionModel().getSelectedIndex())
                        .getDescription());
                
                categorie.setText(as.liste2()
                        .get(listeActivites.getSelectionModel().getSelectedIndex())
                        .getCategorie()
                );
                
  
               date.setText(as.liste2()
                        .get(listeActivites.getSelectionModel().getSelectedIndex())
                        .getCategorie()
                );
                
                
                prix.setText(String.valueOf(as.liste2()
                        .get(listeActivites.getSelectionModel().getSelectedIndex()
                        ).getPrix()
                          )
                );
               image.setText(String.valueOf(as.liste2()
                        .get(listeActivites.getSelectionModel().getSelectedIndex()
                        ).getImage()
                          )
                );
                };
          
              
         }); 
     
      

         ObservableList<Activites> list;
         
        try {
            list = as.getActivitesList();
            
            
            img.setPrefWidth(80);
            idactcol.setCellValueFactory(new PropertyValueFactory<>("idact"));
            nomactcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
            descactcol.setCellValueFactory(new PropertyValueFactory<>("description"));
            categorieactcol.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            dateactcol.setCellValueFactory(new PropertyValueFactory("date"));
            prixactcol.setCellValueFactory(new PropertyValueFactory<>("prix"));
            img.setCellValueFactory(new PropertyValueFactory<>("image"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
            
           listeActivites.setItems(list);

          
            
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
   @FXML
    private void OnClickedréserver(ActionEvent event) {
        try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/Formulairereservation.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setTitle("Réserver");
            stage.setScene(scene);
            stage.getIcons().add(new Image("/Images/logo.png"));
            stage.initStyle(StageStyle.UTILITY);
          
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FormulairereservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void Map(ActionEvent event) {
        Stage stage = new Stage ();
         
        final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/gui/googleMaps.html").toString());
       
        // create scene
        stage.getIcons().add(new Image("/Images/logo.png"));
        stage.setTitle("localisation");
        Scene scene = new Scene(webView,1000,700, Color.web("#666970"));
        stage.setScene(scene);
        // show stage
        stage.show();
    }
    static { // use system proxy settings when standalone application
        System.setProperty("java.net.useSystemProxies", "true");
    }

    @FXML
    private void maison(ActionEvent event) {
          try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/gui/FrontMaison.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

  
}
    

