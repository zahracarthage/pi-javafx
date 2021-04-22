/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.article;
import static entite.article.filename;
import entite.commande;
import entite.repas;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.util.Collections.list;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import service.articleService;
import service.commandeService;

/**
 * FXML Controller class
 *
 * @author zcart
 */
public class BackController implements Initializable {
    
    Integer id; 

    
    String imagecomp;
    @FXML
    private TextField articlenom;
    @FXML
    private TextField descriptionarticle;
    @FXML
    private TextField categoriearticle;
    @FXML
    private TextField prixarticle;
    @FXML
    private ImageView imgview;
    @FXML
    private Button uploadbtn;
    @FXML
    private TableView<article> affichagearticle;
    @FXML
    private Label imagepath;
    @FXML
    private TableColumn<article, String> idarticlecol;
    @FXML
    private TableColumn<article, String> nomarticlecol;
    @FXML
    private TableColumn<article, String> descriptionarticlecol;
    @FXML
    private TableColumn<article, String> categoriarticlecol;
    @FXML
    private TableColumn<article, String> prixarticlecol;
    @FXML
    private Button modifierarticlebtn;
    @FXML
    private Button supprimerarticlebtn;
    @FXML
    private Button ajoutercommandebtn;
    @FXML
    private Button modifiercommandebtn;
    @FXML
    private Button supprimercommandebtn;
    @FXML
    private DatePicker datecommande;
    @FXML
    private TextField quantitecommande;
    @FXML
    private TextField prixtcommande;
    @FXML
    private TableView<commande> affichagecommande;
    @FXML
    private TableColumn<commande, String> idcommandecol;
    @FXML
    private TableColumn<commande, String> quantitecommandecol;
    @FXML
    private TableColumn<commande, String> prixtcol;
    @FXML
    private TableColumn<commande, String> datecommandecol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         articleService as = new articleService();
        commandeService cs = new commandeService(); 
        
        
        affichagecommande.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                id = cs.liste2()
                        .get(affichagecommande.getSelectionModel().getSelectedIndex())
                        .getId();
                //System.out.println(iddd);
                datecommande.setValue(LocalDate.parse(cs.liste2()
                        .get(affichagecommande.getSelectionModel().getSelectedIndex())
                        .getDate()));
                
              
                
                prixtcommande.setText(String.valueOf(cs.liste2()
                        .get(affichagecommande.getSelectionModel()
                                .getSelectedIndex())
                        .getPrixT()
                ));
                
                  quantitecommande.setText(String.valueOf(cs.liste2()
                        .get(affichagecommande.getSelectionModel()
                                .getSelectedIndex())
                        .getQuantite()));
                };
            
               
         }); 
            
            
                
                  ObservableList<commande>list;

        try {
            list = cs.getCommandeList();
            
            
            idcommandecol.setCellValueFactory(new PropertyValueFactory<>("id"));
            datecommandecol.setCellValueFactory(new PropertyValueFactory<>("date"));
            quantitecommandecol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prixtcol.setCellValueFactory(new PropertyValueFactory<>("prix_total"));

            
           affichagecommande.setItems(list);
                    
        } catch (SQLException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
                ///////article
                
        
                     
              affichagearticle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                id = as.liste2()
                        .get(affichagearticle.getSelectionModel().getSelectedIndex())
                        .getId();
                //System.out.println(iddd);
                articlenom.setText(as.liste2()
                        .get(affichagearticle.getSelectionModel().getSelectedIndex())
                        .getNom());
                
                descriptionarticle.setText(as.liste2()
                        .get(affichagearticle.getSelectionModel()
                                .getSelectedIndex())
                        .getDescription());
                
                categoriearticle.setText(as.liste2()
                        .get(affichagearticle.getSelectionModel()
                                .getSelectedIndex())
                        .getCategorie()
                );
                
  
              
                prixarticle.setText(String.valueOf(as.liste2()
                        .get(affichagearticle.getSelectionModel()
                            .getSelectedIndex()
                        ).getPrix()
                          )
                );
                };
            
               
         }); 
       
        
         
              ObservableList<article>list2;

        try {
            list2 = as.getArticleListe();
            
            
            idarticlecol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomarticlecol.setCellValueFactory(new PropertyValueFactory<>("nom"));
            descriptionarticlecol.setCellValueFactory(new PropertyValueFactory<>("description"));
            categoriarticlecol.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            prixarticlecol.setCellValueFactory(new PropertyValueFactory<>("prix"));

            
           affichagearticle.setItems(list2);
                    
        } catch (SQLException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }

            
        

        
        
    };

    @FXML
    private void ajouterarticle(ActionEvent event) throws SQLException {
        
          String rp = prixarticle.getText();
          int pa = Integer.parseInt(rp);
          
         

        article a = new article(articlenom.getText(),descriptionarticle.getText(),categoriearticle.getText(),pa,article.filename );
        
        articleService as = new articleService();
        
        as.ajouterarticle(a);
        
         try {
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Addeded!");
                alert.show();
                
              
                
                affichagearticle.refresh();
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
            affichagearticle.setItems(as.getArticleListe());
        
    }

    
    
    
    
    @FXML
    private void uploadimagearticle(ActionEvent event) {
        
          FileChooser f = new FileChooser();
        String img;

        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imgview.setImage(i);
            imagecomp = fc.toString();
            System.out.println(imagecomp);
            int index = imagecomp.lastIndexOf('\\');
            if (index > 0) {
                filename = imagecomp.substring(index + 1);
            }

            //source = new File(pathimage);
            //dest = new File(System.getProperty("user.dir") + "\\src\\com\\esprit\\img\\" + filename);
            article.filename = "C:\\Users\\zcart\\Desktop\\kiftrip-website\\public\\uploads" + filename;
            //se.sendphp(fc.getAbsolutePath());
        }
        imgview.setFitHeight(215);
        imgview.setFitWidth(265);
        //..\img\google.png

        //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img
        article.pathfile = fc.getAbsolutePath();
       
    }

    @FXML
    private void modifierarticle(ActionEvent event) throws SQLException {
        
        
         article a = affichagearticle.getSelectionModel().getSelectedItem();
        
        String rp = prixarticle.getText();
         int prixrepas = Integer.parseInt(rp);

       // r.setNom(repasnom.getText());
     //  String memid = select1.getValue();
       // int id = Integer.parseInt(idd);
        a.setNom(articlenom.getText());

        a.setDescription(descriptionarticle.getText());
        a.setCategorie(categoriearticle.getText()); 
        a.setPrix(prixrepas);
        
        //a.setDate_eve(datee);
        a.setImg(imagecomp);
        System.out.println(imagecomp);
        articleService rs = new articleService();
        
        
          try {
                       
        rs.modifierarticle(id, a.getNom(),a.getDescription(),a.getCategorie(),prixrepas, article.filename );
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
       affichagearticle.setItems(rs.getArticleListenew());
    }

    @FXML
    private void supprimerarticle(ActionEvent event) {
        
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer cet article  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            articleService as = new articleService();
            try {
                as.supprimerarticle(id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                affichagearticle.setItems(as.getArticleListe());
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
    private void ajoutercommande(ActionEvent event) throws SQLException {
    
    

         String qc = quantitecommande.getText();
          int quantitec = Integer.parseInt(qc);
          
          
           String pt = prixtcommande.getText();
          int prixt = Integer.parseInt(pt);
          
          
          
          
     String datee = datecommande.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
System.out.println(datee);
    
        commande c = new commande(datee,quantitec,prixt);
        
        commandeService cs = new commandeService();
        
            cs.ajoutercommande(c);        
        
         try {
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Addeded!");
                alert.show();
                
              
                
                affichagearticle.refresh();
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
            affichagecommande.setItems(cs.getCommandeList());
            
    }

    @FXML
    private void modifiercommande(ActionEvent event) throws SQLException {
        
        commande c = affichagecommande.getSelectionModel().getSelectedItem();
       
        
         String qc = quantitecommande.getText();
          int quantitec = Integer.parseInt(qc);
          
          
           String pt = prixtcommande.getText();
          int prixt = Integer.parseInt(pt);
          
          
          
          
     String datee = datecommande.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(datee);
    
        
        commandeService cs = new commandeService();

       // r.setNom(repasnom.getText());
     //  String memid = select1.getValue();
       // int id = Integer.parseInt(idd);
       c.setDate(datee);
        c.setQuantite(quantitec);

        c.setPrixT(prixt);
       
              
        
                     try {
                       
        cs.modifiercommande(id,datee,quantitec,prixt);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.show();
                        alert.setTitle("updated !");
                        alert.setContentText("updated succesfully");

                    } 
                     catch (Exception e) 
                     {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed succesfully");

                    }
          affichagecommande.setItems(cs.getCommandeListnew());

    }

    @FXML
    private void supprimercommande(ActionEvent event) {
        
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer dis  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            commandeService cs = new commandeService();
            try {
                cs.supprimercommande(id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                affichagecommande.setItems(cs.getCommandeList());
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
