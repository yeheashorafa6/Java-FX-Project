/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Account;
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
public class UpdataAccountController implements Initializable {
    private Account oldAccounts;

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
       this.oldAccounts = Controller.Admin.AccountsManagmentController.selectedAccountToUpdate;
        
        //set text field's data the same as updatedUser data
        accountNumberTF.setText(oldAccounts.getAccountNumber());
        userNameTF.setText(oldAccounts.getUserName());
        currencyTF.setText(oldAccounts.getCurreny());
        balanceTF.setText(oldAccounts.getBalance());
        creationDateTF.setText(oldAccounts.getCreation_date());
      
    }    

    @FXML
    private void saveNewAccount(ActionEvent event) throws SQLException, ClassNotFoundException {
          //get the new data from text field's and store it in new Account object
        String accountNumber= this.accountNumberTF.getText();
        String username = userNameTF.getText();
        String curreny = currencyTF.getText();
        String balance = balanceTF.getText();
        String creationDate = creationDateTF.getText();
      
       
        
        //make an new Account object having this data
        Account newUser = new Account(accountNumber,username,curreny,balance,creationDate);
        
        //set the new Account id the same as the old Account
        newUser.setId(oldAccounts.getId());
        
        // update the Account in database by update method
        newUser.update();
        
        //close the update stage using our global stage var in AccountManagmentController and show an alert
        Controller.Admin.AccountsManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account updated");
        alert.setContentText("Account updated");
        alert.showAndWait();
    }

    @FXML
    private void cancelAccountCreation(ActionEvent event) {
        Controller.Admin.AccountsManagmentController.updateStage.close();
    }
    
}
