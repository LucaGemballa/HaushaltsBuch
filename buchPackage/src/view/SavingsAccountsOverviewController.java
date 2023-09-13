package view;

import entity.SavingsAccount;
import entity.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Screen;

public class SavingsAccountsOverviewController {

    @FXML
    private Label accountNameLabel;
    @FXML
    private Label accountBalanceLabel;

    @FXML
    public TableView<Transaction> tableViewTransactions;

    @FXML
    public TableColumn<Transaction, String> tableColumnSource;
    @FXML
    public TableColumn<Transaction, String> tableColumnTransactionSum;
    @FXML
    public TableColumn<Transaction, String> tableColumnTransactionDate;
    @FXML
    private Button deleteAccountButton;


    public void initParameters(){
        System.out.println(accountNameLabel.getAccessibleText().toString());
        int accountID = Main.rootService.savingsAccountService.getAccountID(accountNameLabel.getAccessibleText().toString());
        SavingsAccount currentAccount = Main.rootService.accountList.get(accountID);
        accountNameLabel.setText(currentAccount.name);
        // need to set text first
        accountBalanceLabel.setText(currentAccount.getBalanceString());
        currentAccount.balanceObserve.setValue(currentAccount.getBalanceString());
        accountBalanceLabel.textProperty().bind(Main.rootService.accountList.get(accountID).balanceObserve);
    }


    public void showDeleteAccountWindow(){

    }


}
