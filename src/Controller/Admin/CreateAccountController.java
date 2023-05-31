/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Account;
import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CreateAccountController implements Initializable {

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button saveNewAccountBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label userName;
    @FXML
    private TextField accountNumberTF;
    @FXML
    private TextField userNameTF;
    @FXML
    private Label accountNumber;
    @FXML
    private TextField emailTF;
    @FXML
    private Label email;
    @FXML
    private Label currency;
    @FXML
    private TextField currencyTF;
    @FXML
    private Label balance;
    @FXML
    private TextField balanceTF;
    @FXML
    private Label creationDate;
    @FXML
    private TextField creationDateTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
         ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
          ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void saveNewAccount(ActionEvent event) throws SQLException, ClassNotFoundException {
      // get data from all text fields
        String accountNumber=accountNumberTF.getText();
        String username = userNameTF.getText();
        String currency=currencyTF.getText();
        String balance=balanceTF.getText();
        String creationDate = creationDateTF.getText();

        
        // make an user object having this data
        Account account = new Account(accountNumber,username,currency,balance,creationDate);
        // save the user in database by save method
        account.save();
        accountNumberTF.setText("");
        userNameTF.setText("");
        currencyTF.setText("");
        balanceTF.setText("");
        creationDateTF.setText("");
        

        //after saving should return to the back scene and show an alert
        ViewManager.adminPage.changeSceneToAccountsManagment();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account inserted");
        alert.setContentText("Account inserted");
        alert.showAndWait();
    }

    @FXML
    private void cancelAccountCreation(ActionEvent event) {
         ViewManager.adminPage.changeSceneToAccountsManagment();
    }
    
}
