package entity;

import java.util.*;
import java.time.*;

public class Transaction implements Comparable<Transaction>{

    float transactionSum;
    String transactionCathegory;
    String transactionDescription;
    LocalDate transactionDate;

    public Transaction (float sum, String cathegory,LocalDate date,String description){
        transactionSum = sum;
        transactionCathegory = cathegory;
        transactionDescription = description;
        transactionDate = date;
    }

    public String toText(){
        //System.out.println(Float.toString(transactionSum));
        //System.out.println(transactionCathegory);
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
        System.out.println("Date Comparison");
        System.out.println(transactionDate);
        System.out.println(o.getTransactionDate());
        int i = transactionDate.compareTo(o.getTransactionDate());
        System.out.println(i);
        return i;
    }
}
