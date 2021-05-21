/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entite.repas;
import static entite.repas.filename;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.SystemTray;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import service.repasService;

/**
 * FXML Controller class
 *
 * @author zcart
 */
public class GestiondesrepasController implements Initializable {

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
    private Button btn_resmais;
    @FXML
    private Button btn_resevenement;
    @FXML
    private Button btn_evenements;
    @FXML
    private Button ajoutbtn;
    @FXML
    private TextField repasprice;
    @FXML
    private TextField repasnom;
    @FXML
    private Label imagepath;
    @FXML
    private TextField repasdescription;
    @FXML
    private TextField repasadresse;
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
    private Button pdfrepasbtn;
    @FXML
    private Button pdfrepas;
    @FXML
    private TextField searchrepas;
    @FXML
    private Button qr;
    @FXML
    private ImageView imagefield;
    @FXML
    private Button stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                               repasService rs = new repasService();
                                repascategory.setItems(options);

                                
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
       
        
         ObservableList<repas> list2;      
            try {
            list2 = rs.getRepasList();
            
            
            idrepascol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomrepascol.setCellValueFactory(new PropertyValueFactory<>("nom"));
            descrepascol.setCellValueFactory(new PropertyValueFactory<>("description"));
            categoryrepascol.setCellValueFactory(new PropertyValueFactory<>("category"));
            pricerepascol.setCellValueFactory(new PropertyValueFactory<>("price"));
            adresserepascol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            
            affichagerepas.setItems(list2);

            
        } catch (SQLException ex) {
            Logger.getLogger(GestiondesrepasController.class.getName()).log(Level.SEVERE, null, ex);
        }
          

        // TODO
        
        //sort wela recherche
        
        
        
        ObservableList<repas> liste;
         try {
             liste = rs.getRepasList();
            
        FilteredList<repas> filteredData = new FilteredList<>(liste, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchrepas.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(repas -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (repas.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (repas.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(repas.getCategory()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<repas> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(affichagerepas.comparatorProperty());
                
		
		// 5. Add sorted (and filtered) data to the table.
		affichagerepas.setItems(sortedData);
        } catch (SQLException ex) {
             Logger.getLogger(repasService.class.getName()).log(Level.SEVERE, null, ex);
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
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/gestiondesrepas.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
         
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
    private void ajouterrepas(ActionEvent event) throws SQLException {
         String rp = repasprice.getText();
          int repasp = Integer.parseInt(rp);
          
          
          
    

        repas r = new repas(repasnom.getText(),repasdescription.getText(),repasp,repascategory.getValue().toString(),repasadresse.getText(),repas.filename );
        
        repasService rs = new repasService();
        
        
        
        
         try {
              
             
             rs.ajouterrepas(r);
             if (SystemTray.isSupported()) {
                    //TrayIconDemo td = new TrayIconDemo();
                    rs.displayTray();
                    //System.out.println("aaaaa");
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Addeded!");
                alert.show();
                
             }
                   else {
                    System.err.println("System tray not supported!");
                }
                
              
                
                affichagerepas.refresh();
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
            affichagerepas.setItems(rs.getRepasList());
    }

    
    
    @FXML
    private void uploadimg(ActionEvent event) throws FileNotFoundException, MalformedURLException, IOException {
        {
            
         FileChooser fc = new FileChooser();
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", listFichier));
        File f = fc.showOpenDialog(null);
        if (f != null) {

            //Commentaire.setText("Image selectionnée" + f.getAbsolutePath());
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(new File(f.getAbsolutePath()));
//             
                os = new FileOutputStream(new File("D:\\kiftrip-website\\public\\uploads\\" + f.getName()));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                System.out.println("Malek");

            } finally {
                is.close();
                os.close();

            }

            File file = new File("D:\\kiftrip-website\\public\\uploads\\" + f.getName());
//            System.out.println(file.toURI().toString());
            imagefield.setImage(new Image(file.toURI().toString()));
            imagecomp = f.getName();
            System.out.println(imagecomp);
            imagepath.setText(imagecomp);
        } else if (f == null) {
            //Commentaire.setText("Erreur chargement de l'image");
            System.out.println("Erreur !");
        }
        
          
    }
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

    @FXML
    private void exportpdfrepasbtn(ActionEvent event) {
           PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.affichagerepas;
        
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

    @FXML
    private void pdfrepas(ActionEvent event) throws SQLException {
        repasService rs = new repasService();
              
         ObservableList<repas> list = rs.getRepasList();
        try {
            OutputStream file = new FileOutputStream(new File("D:\\pdf\\exportfront.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();

            Font font = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Paragraph pdfTitle = new Paragraph("Repas list", font);
            pdfTitle.setAlignment(Element.ALIGN_CENTER);

            document.add(pdfTitle);
            document.add(new Chunk("\n"));
            PdfPTable table = new PdfPTable(3);
            table.setHeaderRows(1);

            table.addCell("Name");
            table.addCell("Date");
            table.addCell("Description");

            list.forEach((_item) -> {
                table.addCell(_item.getNom());
                table.addCell(_item.getDescription());
                table.addCell(_item.getAdresse());
            });

            document.add(table);

            document.close();

            file.close();

        } catch (DocumentException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Cannot export data!");
            alert.show();
        }
    }

    @FXML
    private void searchrepas(ActionEvent event) {
    }

    @FXML
    private void generateqr(ActionEvent event) {
         repasService rs = new repasService();

                if (affichagerepas.getSelectionModel().getSelectedItem() != null) {
            repas e = new repas();
            e.setDescription(rs.liste2().get(affichagerepas.getSelectionModel().getSelectedIndex()).getDescription());
            e.setNom(rs.liste2().get(affichagerepas.getSelectionModel().getSelectedIndex()).getNom());
            e.setAdresse(rs.liste2().get(affichagerepas.getSelectionModel().getSelectedIndex()).getAdresse());
            
            Map hints = new HashMap();
            hints.put(com.google.zxing.EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.H);
            com.google.zxing.qrcode.QRCodeWriter writer = new com.google.zxing.qrcode.QRCodeWriter();
            com.google.zxing.common.BitMatrix bitMatrix = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                // Create a qr code with the url as content and a size of 250x250 px
                bitMatrix = writer.encode("The plate's name= "+e.getNom()+"  "+"The plate's description= "+e.getDescription()+"  "+"The plate's adress=  "+e.getAdresse(), BarcodeFormat.QR_CODE, 250, 250, hints);
                MatrixToImageConfig config = new MatrixToImageConfig(MatrixToImageConfig.BLACK, MatrixToImageConfig.WHITE);
                // Load QR image
                BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
                // Load logo image
                File file = new File("C:\\ahmed.png");
                BufferedImage logoImage = ImageIO.read(file);
                // Calculate the delta height and width between QR code and logo
                int deltaHeight = qrImage.getHeight() - logoImage.getHeight();
                int deltaWidth = qrImage.getWidth() - logoImage.getWidth();
                // Initialize combined image
                BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = (Graphics2D) combined.getGraphics();
                // Write QR code to new image at position 0/0
                g.drawImage(qrImage, 0, 0, null);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                // Write logo into combine image at position (deltaWidth / 2) and
                // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
                // the same space for the logo to be centered
                g.drawImage(logoImage, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);
                // Write combined image as PNG to OutputStream
                ImageIO.write(combined, "png", new File("C:\\QR.png"));
                //System.out.println("done");
            } catch (Exception ea) {
                System.out.println(ea);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Choose a row !");
            alert.show();
        }
    }
/*
    @FXML
    private void stat(ActionEvent event) {
               
      try {
           
          String query = "SELECT COUNT(*),category FROM repas GROUP BY category" ;
       
             PreparedStatement PreparedStatement = conn.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("categorie"),rs.getInt("COUNT(*)")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        piechart.setTitle("**Statistiques nombres des catégories**");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
        
    }*/

    @FXML
    private void stat(ActionEvent event) {
         try {
           Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/gchart.fxml"));
           Scene ex_section_scene = new Scene(exercices_parent);
           Stage second_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
           
           second_stage.setScene(ex_section_scene);
           second_stage.show();
                   
                   
                   } catch (IOException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
