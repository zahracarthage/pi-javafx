/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Users;
import Service.UserService;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class AdminBackController implements Initializable {
    @FXML
    private Button btn_boutique;
    @FXML
    private Button btn_event;
    @FXML
    private Button btn_maison;
    @FXML
    private Button btn_repas;
    @FXML
    private Button btn_activite;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_user1;
    @FXML
    private Button btn_user11;
    @FXML
    private Button btn_user111;
    @FXML
    private Button btn_user1111;
    @FXML
    private Button btn_user1112;
    @FXML
    private Button btn_user1113;
    @FXML
    private Button btn_user11131;
    @FXML
    private Button btn_user11132;
     @FXML
    private TableView<Users> UsersViewss;
    @FXML
    private TableColumn<Users, String> IdCol;
    @FXML
    private TableColumn<Users, String> UserNameCol;
    @FXML
    private TableColumn<Users, String> EmailCol;
    @FXML
    private TableColumn<Users, String> RoleCol;
    @FXML
    private TableColumn<Users, String> ActionCol;
    
     ObservableList<Users> UserList = FXCollections.observableArrayList();
    String query = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Users User = null;
    Connection connection;

    UserService su = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        // TODO
    }    

    @FXML
    private void boutique(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) {
    }

    @FXML
    private void maison(ActionEvent event) {
    }

    @FXML
    private void repas(ActionEvent event) {
    }

    @FXML
    private void activite(ActionEvent event) {
    }

    @FXML
    private void user(ActionEvent event) {
    }

    @FXML
    private void GoToProfile(ActionEvent event) throws IOException, Exception {

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
    
    public void refresh() {

        try {
            UserList.clear();

            query = "SELECT * FROM `users`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserList.add(new Users(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("role")));
                UsersViewss.setItems(UserList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminInterfaceaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadData() {

        connection = DataSource.getInstance().getCnx();
        refresh();
        IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        UserNameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        RoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        UsersViewss.setItems(UserList);

        Callback<TableColumn<Users, String>, TableCell<Users, String>> cellfactory = (param) -> {
            final TableCell<Users, String> cell = new TableCell<Users, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        final Button editButton = new Button("DELETE");
                        editButton.setOnAction(event -> {

                            Users u1 = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Suppression ");
                            alert.setContentText("Voulez-vous vraiment supprimer ce compte ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                try {
                                    if (su.delete(u1)) {
                                        
                       refresh();
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(AdminInterfaceaController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        ActionCol.setCellFactory(cellfactory);

    }
    
    
    
}
