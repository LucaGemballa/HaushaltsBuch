package view;

import entity.SavingsAccount;
import entity.Transaction;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

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
        System.out.println(accountNameLabel.getAccessibleText());
        int accountID = Main.rootService.savingsAccountService.getAccountID(accountNameLabel.getAccessibleText().toString());
        SavingsAccount currentAccount = Main.rootService.accountList.get(accountID);
        accountNameLabel.setText(currentAccount.name);
        // need to set text first
        accountBalanceLabel.setText(currentAccount.getBalanceString());
        currentAccount.balanceObserve.setValue(currentAccount.getBalanceString());
        accountBalanceLabel.textProperty().bind(Main.rootService.accountList.get(accountID).balanceObserve);

        BooleanProperty x = new SimpleBooleanProperty(false);
        deleteAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/deleteAccountRequest.fxml"));
                    root = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("My New Stage Title");
                    stage.setScene(new Scene(root, 450, 450));
                    DeleteAccountRequestController d = loader.getController();

                    x.bind(d.ret);
                    stage.setOnHidden(event1 -> {
                        try {
                            System.out.println("Attempt Deletion");
                            System.out.println(x.getValue());
                            if(x.getValue()){
                                currentAccount.deleteAccount();
                            }
                        }
                        catch (Exception e) {
                            System.out.println(e.toString());
                            System.out.println("HEEEEEE");

                        }
                    });
                    stage.show();
                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void showDeleteAccountWindow(){

    }


}
