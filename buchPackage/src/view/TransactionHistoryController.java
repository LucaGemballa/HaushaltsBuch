package view;

import java.lang.*;

import entity.Transaction;
import entity.TransactionList;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.TextField;
import javafx.beans.property.*;

import java.nio.file.*;
import java.util.Collections;

import java.util.Comparator;
/*
public class DateComparator implements Comparator<LocalDate>{
    @Override
    public int compareTo(Transaction o, Transaction){
        int i = transactionDate.compareTo(o.getTransactionDate());
        return i;
    }

}

 */

public class TransactionHistoryController {

    @FXML
    public  TableView<Transaction> tableViewTransactions;

    @FXML
    public TableColumn<Transaction, String> tableColumnCategory;
    @FXML
    public TableColumn<Transaction, String> tableColumnTransactionSum;
    @FXML
    public TableColumn<Transaction, String> tableColumnTransactionDate;
    @FXML
    public TableColumn<Transaction, String> tableColumnDescription;

    @FXML
    public void initialize(){
        tableViewTransactions.getItems().addAll(Main.rootService.transactionList.list);
        tableColumnCategory.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getTransactionCategory()));
        tableColumnTransactionSum.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getTransactionSumString()));

        // Need to give LocalDates a suitable compare method
        tableColumnTransactionDate.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getTransactionDate().format(DateTimeFormatter.ofPattern("dd.LL.yyyy"))));

        tableColumnDescription.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getTransactionDescription()));
    }
}
