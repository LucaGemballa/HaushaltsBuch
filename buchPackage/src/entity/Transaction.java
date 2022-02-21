package entity;

import java.util.*;
import java.time.*;

public class Transaction implements Comparable<Transaction>{

    float transactionSum;
    String transactionCathegory;
    String transactionDescription;
    LocalDate transactionDate;
    String transactionSource;
    TransactionWeight transactionWeight;


    public Transaction (float sum, String cathegory,LocalDate date,String source,TransactionWeight weight, String description){
        transactionSum = sum;
        transactionCathegory = cathegory;
        transactionDescription = description;
        transactionDate = date;
        transactionSource = source;
        transactionWeight = weight;
    }

    public String toText(){
        String retVal = Float.toString(transactionSum) + " " + transactionCathegory + " " + transactionDate;
        return retVal;
    }

    public float getTransactionSum(){
        return transactionSum;
    }

    public String getTransactionCathegory(){
        return transactionCathegory;
    }

    public LocalDate getTransactionDate(){return  transactionDate; }

    @Override
    public int compareTo(Transaction o){
        int i = transactionDate.compareTo(o.getTransactionDate());
        return i;
    }
}
