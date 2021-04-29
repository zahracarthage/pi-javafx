/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.repas;
import static entite.repas.filename;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import service.repasService;
import javafx.scene.image.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import static java.sql.DriverManager.println;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileOutputStream;

import java.io.OutputStream;
import static java.util.Collections.list;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 *
 * @author zcart
 */
public class FrontController implements Initializable {
    
    
    public String imagecomp; 
               Integer id, idd; 

    
    @FXML
    private TextField repasnom;
    @FXML
    private TextField repasdescription;
    @FXML
    private TextField repasprice;
    @FXML
    private TextField repasadresse;
    private TextField repasimg;
    @FXML
    private Button ajoutbtn;
    @FXML
    private ComboBox<String> repascategory;
    
    ObservableList<String> options = FXCollections.observableArrayList(
                "sans-gluten",
                "vegetarien",
                "Halal",
                "Diabetique",
                "Vegan"
        );
    @FXML
    private Label imagepath;
    @FXML
    private ImageView imagefield;
    @FXML
    private Button upload;
    @FXML
    private TableView<repas> affichagerepas;
    @FXML
    private TableColumn<repas, String> idrepascol;
    @FXML
    private TableColumn<repas, String> nomrepascol;
    @FXML
    private TableColumn<repas, String> descrepascol;
    @FXML
    private TableColumn<repas, String> categoryrepascol;
    @FXML
    private TableColumn<repas, String> pricerepascol;
    @FXML
    private TableColumn<repas, String> adresserepascol;
    @FXML
    private Button modifierrepasbtn;
    @FXML
    private Button supprimerrepasbtn;
    @FXML
    private TextField reclamationnom;
    @FXML
    private TextField reclamationprenom;
    @FXML
    private TextField reclamationemail;
    @FXML
    private ComboBox<?> reclamationsujet;
    @FXML
    private TextArea reclamationmessage;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    

              repascategory.setItems(options);
        
        repasService rs = new repasService();
        

        
                     
              affichagerepas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                id = rs.liste2()
                        .get(affichagerepas.getSelectionModel().getSelectedIndex())
                        .getId();
                //System.out.println(iddd);
                repasnom.setText(rs.liste2()
                        .get(affichagerepas.getSelectionModel().getSelectedIndex())
                        .getNom());
                
                repasdescription.setText(rs.liste2()
                        .get(affichagerepas.getSelectionModel()
                                .getSelectedIndex())
                        .getDescription());
                
                repascategory.setValue(rs.liste2()
                        .get(affichagerepas.getSelectionModel()
                                .getSelectedIndex())
                        .getCategory()
                );
                
  
                repasadresse.setText(rs.liste2()
                        .get(affichagerepas.getSelectionModel()
                                .getSelectedIndex())
                        .getAdresse()
                ); 
                repasprice.setText(String.valueOf(rs.liste2()
                        .get(affichagerepas.getSelectionModel()
                            .getSelectedIndex()
                        ).getPrice()
                          )
                );
                };
            
               
         }); 
       
        
         ObservableList<repas> list;
         
        try {
            list = rs.getRepasList();
            
            
            idrepascol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomrepascol.setCellValueFactory(new PropertyValueFactory<>("nom"));
            descrepascol.setCellValueFactory(new PropertyValueFactory<>("description"));
            categoryrepascol.setCellValueFactory(new PropertyValueFactory<>("category"));
            pricerepascol.setCellValueFactory(new PropertyValueFactory<>("price"));

            adresserepascol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            
            affichagerepas.setItems(list);

            
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }



         
    }    


    @FXML
    private void ajouterrepas(ActionEvent event) throws SQLException {
        
      //  repas r = new repas(nom, description, price, category, adresse, img );
          String rp = repasprice.getText();
          int repasp = Integer.parseInt(rp);
          
          
          
    

        repas r = new repas(repasnom.getText(),repasdescription.getText(),repasp,repascategory.getValue().toString(),repasadresse.getText(),repas.filename );
        
        repasService rs = new repasService();
        
        rs.ajouterrepas(r);
        
         try {
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Addeded!");
                alert.show();
                
              
                
                affichagerepas.refresh();
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
            affichagerepas.setItems(rs.getRepasList());
        
     }
    
    @FXML
    private void uploadimg(ActionEvent event) throws FileNotFoundException, IOException {
    
        FileChooser f = new FileChooser();
        String img;

        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imagefield.setImage(i);
            imagecomp = fc.toString();
            System.out.println(imagecomp);
            int index = imagecomp.lastIndexOf('\\');
            if (index > 0) {
                filename = imagecomp.substring(index + 1);
            }

            //source = new File(pathimage);
            //dest = new File(System.getProperty("user.dir") + "\\src\\com\\esprit\\img\\" + filename);
            repas.filename = "C:\\Users\\zcart\\Desktop\\kiftrip-website\\public\\uploads" + filename;
            //se.sendphp(fc.getAbsolutePath());
        }
        imagefield.setFitHeight(215);
        imagefield.setFitWidth(265);
        //..\img\google.png

        //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img
        repas.pathfile = fc.getAbsolutePath();
       
    }

    @FXML
    private void modifierrepas(ActionEvent event) throws SQLException {
        
        repas r = affichagerepas.getSelectionModel().getSelectedItem();
        
        String rp = repasprice.getText();
         int repasp = Integer.parseInt(rp);

       // r.setNom(repasnom.getText());
     //  String memid = select1.getValue();
       // int id = Integer.parseInt(idd);
        r.setNom(repasnom.getText());

        r.setDescription(repasdescription.getText());
        r.setCategory(repascategory.getValue()); 
        r.setPrice(repasp);
        r.setAdresse(repasadresse.getText());
        
        
        
        //a.setDate_eve(datee);
        r.setImg(imagecomp);
        System.out.println(imagecomp);
        repasService rs = new repasService();
        
        
          try {
                       
        rs.updaterepas (id, r.getNom(),r.getDescription(),repasp,r.getCategory(),r.getAdresse(), repas.filename );
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.show();
                        alert.setTitle("updated !");
                        alert.setContentText("updated succesfully");

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed succesfully");

                    }
       // int id, String nom, String description, int price, String category, String adresse, String img
       affichagerepas.setItems(rs.getRepaslistnew());
        
    }

    @FXML
    private void supprimerrepas(ActionEvent event) {
        
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer cet annonce repas  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            repasService rs = new repasService();
            try {
                rs.deleterepas(id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                affichagerepas.setItems(rs.getRepasList());
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
