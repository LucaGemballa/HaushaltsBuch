package entity;
import javafx.beans.property.*;
import view.Main;


import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;

public class SavingsAccount {
    public float balance;
    public String name;
    private DecimalFormat df;
    private int accountID;

    public StringProperty balanceObserve;

    public SavingsAccount(String n, float b,int id){
        balance = b;
        name = n;
        df = new DecimalFormat("0.00");
        accountID = id;
        balanceObserve = new SimpleStringProperty("");

    }

    public void bookTransaction(float sum){
        balance = balance+sum;
        balanceObserve.setValue(String.valueOf(balance));
    }

    public String toText(){
        return name + " " + Float.toString(balance);
    }

    public String getBalanceString(){
        return df.format(balance);
    }

    public int getAccountID(){
        return accountID;
    }

    public void deleteAccount(){
        for (TransactionList years: Main.rootService.transactionList.yearList){
            for (TransactionList month: years.monthList){
                for (Transaction t: month.list){
                    if(t.transactionDestination.equals(this.name)){
                        Main.rootService.accountList.get(Main.rootService.savingsAccountService.getAccountID(t.transactionSource)).bookTransaction(t.transactionSum);
                        Main.rootService.transactionList.delete(t);
                    }
                    if(t.transactionSource.equals(this.name)){
                        t.setTransactionSource("Hauptkonto");
                    }
                }
            }
        }

        // Speichern der geänderten Informationen
        Main.printToData = Main.rootService.transactionList.getTransactionString();
        Path p = Path.of(Main.transactionPath);
        try{
            Path filePath = Files.writeString(p, Main.printToData);
        }
        catch (Exception e){
        }

    }
}
