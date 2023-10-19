package entity;
import javafx.beans.property.*;
import view.Main;


import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.LinkedList;

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


    /*
    1. Delete all transactions going to and out of the deleted account -> transfer money back/set sender as main account
    2. Delete the account itself
    3. Save the information in respective files
     */
    public void deleteAccount(){
        // 1.
        LinkedList<Transaction> deleteList = new LinkedList<Transaction>();
        System.out.println("Account wird gelöscht");
        for (TransactionList years: Main.rootService.transactionList.yearList){
            System.out.println("B");
            for (TransactionList month: years.monthList){
                System.out.println("C");
                for (Transaction t: month.list){
                    System.out.println("D");
                    System.out.println(t.transactionDestination + " " + this.name);
                    if(t.transactionDestination.equals(this.name)){
                        System.out.println("F");
                        Main.rootService.accountList.get(Main.rootService.savingsAccountService.getAccountID(t.transactionSource)).bookTransaction(t.transactionSum);
                        deleteList.add(t);
                    }
                    if(t.transactionSource.equals(this.name)){
                        System.out.println("E");
                        t.setTransactionSource("Hauptkonto");
                    }
                }
            }
        }

        // Have to delete out of prior loop to avoid java.util.ConcurrentModificationException
        for(Transaction d: deleteList){
            Main.rootService.transactionList.delete(d);
        }
        deleteList.clear();

        System.out.println("Account wird gelöscht");



        // 2.  delete the account object from the accountlist
        Main.rootService.accountList.remove(accountID);
        System.out.println(Main.rootService.accountList.size());

        for(int i = 0; i< Main.rootService.accountList.size();i++ ) {
            System.out.println("A " +Main.rootService.accountList.get(i).name);
        }

        Main.rootService.savingsAccountService.getMainController().savingsAccountsTilePane.getChildren().clear();
        Main.rootService.savingsAccountService.getMainController().refreshMainTab();
        //Main.rootService.savingsAccountService.getMainController().savingsAccounts.requestLayout();

        // Speichern der geänderten Informationen
        Main.printToData = Main.rootService.transactionList.getTransactionString();
        Path p = Path.of(Main.transactionPath);
        try{
            Path filePath = Files.writeString(p, Main.printToData);
        }
        catch (Exception e){
        }

        Main.printToDataAccs = Main.rootService.savingsAccountService.accountListToText();
        Path c = Path.of(Main.accountPath);
        try{
            Path filePath = Files.writeString(c, Main.printToDataAccs);
        }
        catch (Exception e){
        }

    }
}
