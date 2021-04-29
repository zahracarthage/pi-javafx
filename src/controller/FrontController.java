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
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Document;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import utils.mail;
import entite.reclamation;
import entite.repas;
import static entite.repas.filename;
import entite.reservationrepas;
import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
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
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
import static java.nio.file.Files.list;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import service.reclamationService;
import service.reservationrepasService;


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
    private Button ajouterreclamationbtn;
    @FXML
    private TableView<reclamation> affichagereclamation;
    @FXML
    private TableColumn<reclamation, String> idreccol;
    @FXML
    private TableColumn<reclamation, String> nomrec;
    @FXML
    private TableColumn<reclamation, String> emailcolrec;
    
    @FXML
    private TableColumn<reclamation, String> sujetcolrec;
    
    @FXML
    private TableColumn<reclamation, String> etatcolrec;
    @FXML
    private Button modifierreclamationbtn;
    @FXML
    private Button supprimerreclamationbtn;
               reclamationService recs = new reclamationService();
    @FXML
    private TableColumn<reclamation, String> prencolrec;
    @FXML
    private TableColumn<reclamation,String > msgcolrec;
    @FXML
    private Button pdfrepasbtn;
    @FXML
    private Button pdfrepas;
    @FXML
    private Button trirepas;
    @FXML
    private TextField searchrepas;
    @FXML
    private Button qr;
    @FXML
    private DatePicker dateres;
    @FXML
    private TableView<?> affichageresrep;
    @FXML
    private TextField nbrpersonnes;
    @FXML
    private TextArea messageresrep;
    @FXML
    private TextField emailresrep;
    @FXML
    private TextField nomresrep;
    @FXML
    private TextField phoneresrep;
    @FXML
    private TextField nomrepas;
    @FXML
    private Button btn_ajoutreservation;
    @FXML
    private Button btn_supprimerreservation;
    @FXML
    private TableColumn<reservationrepas, String> idtabres;
    @FXML
    private TableColumn<reservationrepas, String> idrtabres;
    @FXML
    private TableColumn<reservationrepas, String> datetabres;
    @FXML
    private TableColumn<reservationrepas, String> nbrtabres;
    @FXML
    private TableColumn<reservationrepas, String> msgtabres;
    @FXML
    private TableColumn<reservationrepas, String> nomtabres;
    @FXML
    private TableColumn<reservationrepas, String> emailtabres;
    @FXML
    private TableColumn<reservationrepas, String> phonetabres;
    @FXML
    private ComboBox<String> idr;

   // repas r = (repas) idr.getSelectionModel().getSelectedItem();

    /**
     * Initializes the controller class
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
       //repas r = new repas();
        
     //   int id_utilisateur= user.getId_utilisateur();
     
                       repasService rs = new repasService();
                       reservationrepasService rrs= new reservationrepasService();
                       
                       
        try {
            idr.setItems(FXCollections.observableArrayList(rrs.listidr()));
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
                           System.out.println(idr);
                       

     
      /*  try {
            idr.setItems(FXCollections.observableArrayList(rrs.getAllrepas()));
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } */


        
        
             repascategory.setItems(options);
            reclamationsujet.setItems(op);
              
              
        
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
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
            
            
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
         
         ObservableList<reclamation> list;

         
        try {
            list = recs.getReclamationList();
            
            idreccol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomrec.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prencolrec.setCellValueFactory(new PropertyValueFactory<>("prenom"));

            
            emailcolrec.setCellValueFactory(new PropertyValueFactory<>("email"));
            
       sujetcolrec.setCellValueFactory(new PropertyValueFactory<>("subject"));

            
            msgcolrec.setCellValueFactory(new PropertyValueFactory<>("message"));

            etatcolrec.setCellValueFactory(new PropertyValueFactory<>("etat"));
            
            affichagereclamation.setItems(list);

            
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //well 
        
        
        
        
        
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
    private void ajouterrepas(ActionEvent event) throws SQLException, AWTException, InterruptedException {
        
      //  repas r = new repas(nom, description, price, category, adresse, img );
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
          //  String memid = select.getValue();
           //nt idd = Integer.parseInt(memid);

        
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

    @FXML
    private void ajouterreclamation(ActionEvent event) throws SQLException, MessagingException, Exception {
        
        
            

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
        
         reclamation r = affichagereclamation.getSelectionModel().getSelectedItem();
        
      

       
    }
    
    @FXML
    private void trirepasnombtn(ActionEvent event) throws SQLException {
        repasService rs = new repasService();
        rs.tri();
        affichagerepas.setItems((ObservableList<repas>) rs.tri());
        
        
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
   

    

/*

    @FXML
    private void modifierrecbtn(ActionEvent event) {
    }

    @FXML
    private void supprimerrecbtn(ActionEvent event) {
    }
*/

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

   // @FXML
    //private void dateres(ActionEvent event) {

    @FXML
    private void searchrepas(ActionEvent event) {
    }

    @FXML
    private void dateres(ActionEvent event) {
    }

    @FXML
    private void ajoutreservation(ActionEvent event) {
        
          String rp = nomrepas.getText();
          int repasp = Integer.parseInt(rp);
          
          
          String nbr= nbrpersonnes.getText();
          int nbrpersonnes = Integer.parseInt(nbr);
                  
                  
          String datee = dateres.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

          
 reservationrepas rr = new reservationrepas(repasp,datee,nbrpersonnes, messageresrep.getText(),nomresrep.getText(),emailresrep.getText(),phoneresrep.getText());
        
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
                
              
                
                affichagerepas.refresh();
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
        //affichageresrep.setItems(rrs.getresrepasliste());
        

        
    }

    @FXML
    private void supprimerreservation(ActionEvent event) {
        
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
            
          //  affichageresrep.setItems(rs.getresrepasliste());

        } else {
            alert2.close();
        }
        
        
    }
/*
    @FXML
    private void idr(ActionEvent event) throws SQLException {
        
        reservationrepasService rp = new reservationrepasService();
    
        List<String> repa=  rp.listidr();
 
    ObservableList<repas> utili = FXCollections.observableArrayList();
 
       
        for(repas e : repas)

        {

        utili.add(new repas(e.getId(),e.getNom()));

        idr.setItems(utili);
        }

           // como2.getItems().addAll(e.getId_utilisateur(),e.getNom()); 
        //Utilisateur user = como2.getSelectionModel().getSelectedItem();



    }*/
}
//
    
    
    /*
    @FXML
    private void combodis(ActionEvent event) {
        
   
    reservationrepasService rp = new reservationrepasService();
    
        List<repas> repa=  rp.getAllrepas();
 
    ObservableList<repas> utili = FXCollections.observableArrayList();
 
       
        for(repas e : repa)

        {

        utili.add(new repas(e.getId(),e.getNom()));

        idr.setItems(utili);
        }

           // como2.getItems().addAll(e.getId_utilisateur(),e.getNom()); 
        //Utilisateur user = como2.getSelectionModel().getSelectedItem();


else{
idr.setVisible(false);
}
 // como2.getSelectionModel().getSelectedItem();
//    System.out.print(u.getId_utilisateur());

}*/
    

    
    

