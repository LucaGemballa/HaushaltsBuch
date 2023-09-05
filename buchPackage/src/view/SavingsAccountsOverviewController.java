package view;

import entity.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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


}
