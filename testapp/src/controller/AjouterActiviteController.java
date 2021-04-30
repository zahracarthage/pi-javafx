/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.Activites;
import static entite.Activites.filename;
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
import service.ActivitesService;

import static entite.Activites.pathfile;
import entite.Resactivites;
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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.FormatStyle;
import static java.util.Collections.list;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.DataSource;


/**
 * FXML Controller class
 *
 * @author Zannou
 */
public class AjouterActiviteController implements Initializable {

     public String imagecomp; 
               Integer idact; 
               int idresactivites;
    @FXML
    private TextField txtn;
    @FXML
    private TextField txtp;
     @FXML
    private DatePicker txtda;
    @FXML
    private ImageView imagefield;
 
    @FXML
    private Button btnajouter;
    @FXML
    private TextField activiteDescription;
 
    @FXML
    private ComboBox<String> activiteCategorie;
             ObservableList<String> options = FXCollections.observableArrayList(
                "quad",
                "randonné",
                "camping",
                "kayak",
                "escalade"
        );
          ;
    @FXML
    private Button upload;
    
    @FXML
    private TableView<Activites> affichageActivites;
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
    private Button modifieractbtn;
    @FXML
    private Button supprimeractbtn;
    @FXML
    private TextField filterField;
    @FXML
    private Button stat;
    @FXML
    private TableColumn<?, ?> img;
    
       ObservableList<Activites> listea = FXCollections.observableArrayList();
    
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;
    @FXML
    private Button btn_user11;
    @FXML
    private Button btn_reserveract;
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
    private Button btn_resmais;
    @FXML
    private Button btn_evenements;
   
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        activiteCategorie.setItems(options);
        
        ActivitesService as = new ActivitesService();
        

        
                     
              affichageActivites.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                idact = as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getIdact();
                //System.out.println(iddd);
                txtn.setText(as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getNom());
                
                activiteDescription.setText(as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getDescription());
                
                activiteCategorie.setValue(as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getCategorie()
                );
                
  
               /* txtda.Date(as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getDate()
                ); */
                
                
                txtp.setText(String.valueOf(as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex()
                        ).getPrix()
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
            
           affichageActivites.setItems(list);

            
        } catch (SQLException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }

     /*ObservableList<Activites> liste;
         try {
             liste = as.getActivitesList();
             DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
            
        FilteredList<Activites> filteredData = new FilteredList<>(liste, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Activites -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Activites.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Activites.getCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Activites.getPrix()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Activites> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(affichageActivites.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		affichageActivites.setItems(sortedData);
        } catch (SQLException ex) {
             Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
         
         /*ResactivitesService ras = new ResactivitesService();
      
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
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);

     /*ObservableList<Activites> liste;
         try {
             liste = as.getActivitesList();
             DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
            
        FilteredList<Activites> filteredData = new FilteredList<>(liste, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Activites -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Activites.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Activites.getCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Activites.getPrix()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Activites> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(affichageActivites.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		affichageActivites.setItems(sortedData);
        } catch (SQLException ex) {
             Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
         
         /*ResactivitesService ras = new ResactivitesService();
      
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
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

               
     
    }    
     

    @FXML
    private void AjouterActivite(ActionEvent event) throws SQLException {
        
      
          String p = txtp.getText();
          int prix = Integer.parseInt(p);
          
        
          

        Activites a = new Activites(txtn.getText(),activiteDescription.getText(),activiteCategorie.getValue().toString(),Date.valueOf(txtda.getValue().toString()),Activites.filename,prix );
        
        ActivitesService as = new ActivitesService();
        
        as.ajouterActivitePst(a);
        
         try {
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Ajouté!");
                alert.show();
                
              
                
                affichageActivites.refresh();
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
            affichageActivites.setItems(as.getActivitesList());
        
     }
    
    

    

   @FXML
  private void ModifierActivite(ActionEvent event) throws SQLException {
        
        Activites a = affichageActivites.getSelectionModel().getSelectedItem();
        
        String p = txtp.getText();
         int prix = Integer.parseInt(p);
         
       
        a.setNom(txtn.getText());

        a.setDescription(activiteDescription.getText());
        a.setCategorie(activiteCategorie.getValue()); 
        a.setPrix(prix);
        a.setDate(Date.valueOf(txtda.getValue().toString()));
        a.setImage(imagecomp);
        System.out.println(imagecomp);
        ActivitesService as = new ActivitesService();
        
        
          try {
                       
        as.updateactivite (a);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.show();
                        alert.setTitle("updated !");
                        alert.setContentText("updated succesfully");
                        affichageActivites.refresh();

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed succesfully");

                    }
       
       affichageActivites.setItems(as.getActiviteslistnew());
    
  
  }
    @FXML
    private void SupprimerActivite(ActionEvent event) {
                
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous vraiment supprimer cette activité  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ActivitesService as = new ActivitesService();
            try {
                as.deleteactivite(idact);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
               affichageActivites.setItems(as.getActivitesList());
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
    private void uploadimg(ActionEvent event)throws FileNotFoundException, IOException {
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

            Activites.filename = "C:\\Users\\ellil\\Desktop\\activités" + filename;
            //se.sendphp(fc.getAbsolutePath());
        }
        imagefield.setFitHeight(215);
        imagefield.setFitWidth(265);
        //..\img\google.png

        //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img
        Activites.pathfile = fc.getAbsolutePath();
    }

    @FXML
    private void OnClickedStatistique(ActionEvent event) {
        try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/Piechart.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/images/logo.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OnClickedPrint(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.affichageActivites;
        
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

    
    
   /* @FXML
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

    /*@FXML
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
    }*/

     @FXML
    private void front(ActionEvent event) {
         try { 
             Parent parent = FXMLLoader.load(getClass().getResource("/gui/Front.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/images/logo.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
   @FXML
    private void on_on(KeyEvent event) {
        conn=DataSource.getInstance().getCnx();
        try {
            listea.clear();
            
            String requette = " SELECT * FROM `activites` WHERE Prix LIKE  '%"+ filterField.getText()+"%' OR nom LIKE  '%"+ filterField.getText()+"%' OR date LIKE  '%"+ filterField.getText()+"%' OR categorie LIKE  '%"+ filterField.getText()+"%' ";
            pst = conn.prepareStatement(requette);;
            rs = pst.executeQuery();
            
            while (rs.next()){
                listea.add(new Activites(
                        

                        rs.getInt("idact"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getString("categorie"),
                        rs.getDate("date"),
                        rs.getString("image"),
                        rs.getInt("prix")
                      ));
                affichageActivites.setItems(listea);
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reservation(ActionEvent event) {
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
    private void boutique(ActionEvent event) {
    }

    @FXML
    private void commandes(ActionEvent event) {
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
    private void resmaison(ActionEvent event) {
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
    }

     

    


   

    
    

    
    

