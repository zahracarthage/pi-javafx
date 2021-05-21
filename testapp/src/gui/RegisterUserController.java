/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Users;
import utils.Mailing;
import service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class RegisterUserController implements Initializable {
    String path;
    private String Imguser;
    @FXML
    private TextField NomField;
    @FXML
    private TextField EmailField;
    @FXML
    private TextField PasswordField;
    @FXML
    private Label ImageName;
    @FXML
    private Button upload;
    @FXML
    private Button RegisterButton;
    
     Random r = new Random();
    static int nb_valider;
    UserService su = new UserService();
    @FXML
    private ImageView UserImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    public void register(ActionEvent event) throws IOException, Exception {
        Users u = new Users();
        u.setUsername(NomField.getText());
        u.setEmail(EmailField.getText());
        u.setPassword(PasswordField.getText());
        u.setImage(Imguser);
        
        nb_valider = r.nextInt(10000);
        Mailing.mailingValider(EmailField.getText(), nb_valider);
        
        
        JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
        String txt_CodeConfirmation = jop.showInputDialog(null, "Merci de saisir le code de verification !", "Verification Adresse Mail", JOptionPane.QUESTION_MESSAGE);
            
            if (Integer.parseInt(txt_CodeConfirmation) == nb_valider) {
  
                try {
                     su.register(u);
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bienvenue Mr(s) "+ NomField.getText() , ButtonType.CLOSE);
                      
            alert.show();
            
         FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      LoginUserController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
                    // redirection vers la page d'accueil
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
//                lblResultat.setText("Inscription valide !!");
            }else {
              Alert alert = new Alert(Alert.AlertType.ERROR, "Code incorrect", ButtonType.CLOSE);
                alert.show();
            }
        
    }
    
    public void uploadImage(ActionEvent event) throws FileNotFoundException, IOException {
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
                os = new FileOutputStream(new File("C:/wamp64/Image_Pi/" + f.getName()));
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

            File file = new File("C:/wamp64/Image_Pi/" + f.getName());
//            System.out.println(file.toURI().toString());
            UserImg.setImage(new Image(file.toURI().toString()));
            Imguser = f.getName();
            System.out.println(Imguser);
            ImageName.setText(Imguser);
        } else if (f == null) {
            //Commentaire.setText("Erreur chargement de l'image");
            System.out.println("Erreur !");
        }

    }
    
    public void GoToLogin(MouseEvent event) throws IOException, Exception {
         FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      LoginUserController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
     }
    
}
