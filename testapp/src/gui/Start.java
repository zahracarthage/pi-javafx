/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author malek
 */

package gui;

import entite.Users;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author malek
 */
public class Start  extends Application {

    static Users Userconnected = new Users();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginUser.fxml"));
        
        Scene scene = new Scene(root);
        
     
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
