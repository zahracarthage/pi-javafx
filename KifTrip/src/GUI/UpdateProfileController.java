/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Users;
import static GUI.Start.Userconnected;
import Service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class UpdateProfileController implements Initializable {
    private String Imguser;
    @FXML
    private ImageView ImageUserLogedIn;
    @FXML
    private Label UserName;
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField EmailField;
    @FXML
    private Label ImageName;
    UserService su = new UserService();
    @FXML
    private ImageView imageUpdate;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EmailField.setText(Userconnected.getEmail());
        UsernameField.setText(Userconnected.getUsername());
        ImageName.setText(Userconnected.getImage());
        File file1 = new File("C:/wamp64/Image_Pi/" + Userconnected.getImage());
        ImageUserLogedIn.setImage(new Image(file1.toURI().toString()));

        File file = new File("C:/wamp64/Image_Pi/" + Userconnected.getImage());
        imageUpdate.setImage(new Image(file.toURI().toString()));

        UserName.setText(Userconnected.getUsername());
    }    

    @FXML
    public void UpdateProfile(MouseEvent event) throws IOException, Exception {
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("UpdateProfile.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            UpdateProfileController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }

    @FXML
   public void ResetPassword(MouseEvent event) throws IOException, Exception {
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            ResetPasswordController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    public void DeleteAccount(MouseEvent event) throws IOException, Exception {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression ");
        alert.setContentText("Voulez-vous vraiment supprimer Votre compte ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (su.delete(Userconnected)) {
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                    LoginUserController cntr = LOADER.getController();
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {

                }
            }

        }

    }

    @FXML
    public void Logout(MouseEvent event) throws IOException, Exception {
        Userconnected.setId(0);
        Userconnected.setUsername("");
        Userconnected.setEmail("");
        Userconnected.setPassword("");
        Userconnected.setRole("");
        Userconnected.setImage("");
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            LoginUserController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {

        }
    }

    @FXML
   public void Modifier(ActionEvent event) throws IOException, Exception {

        Users u = new Users(Userconnected.getId(), UsernameField.getText(), EmailField.getText(), Userconnected.getPassword(), "User", ImageName.getText());
        if (su.update(u, Userconnected.getId())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Mr/Mme"+ UsernameField.getText()+ " "+ " Vos donnés personelles sont modifiés !", ButtonType.CLOSE);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, " il ya un petit probleme ressayer plus tard !", ButtonType.CLOSE);
                alert.show();
        }
    }

    @FXML
    public void fileChooser(ActionEvent event) throws IOException, Exception {
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
            imageUpdate.setImage(new Image(file.toURI().toString()));
            Imguser = f.getName();
            System.out.println(Imguser);
            ImageName.setText(Imguser);
        } else if (f == null) {
            //Commentaire.setText("Erreur chargement de l'image");
            System.out.println("Erreur !");
        }

    }

    @FXML
    private void BackToInterface(ActionEvent event) throws IOException, Exception {
       if(Userconnected.getRole().equals("Admin"))
        {
     
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("AdminBack.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      AdminInterfaceaController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
            
        }
        if(Userconnected.getRole().equals("User"))
        {
     
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("UserInterface.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      UserInterfaceController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
            
        }
      

    
    }

    
}
