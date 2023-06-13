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
        int maxLength = 0;
        if (transactionSum < 0.0) maxLength = 7;
        else maxLength = 6;

        int s = Float.toString(transactionSum).length();

        String tSum = Float.toString(transactionSum);
        if(s > maxLength) tSum = tSum.substring(0, maxLength);

        while(tSum.length() < 7){
            tSum = " " + tSum;
        }

        String retVal = tSum + " " + transactionCathegory + " " + transactionDate;
        return retVal;
    }

    /*
    for use in calculations etc
     */
    public float getTransactionSum(){
        return transactionSum;
    }

    /*
    for output in tableview
     */
    public String getTransactionSumString(){
        int maxLength = 0;

        if (transactionSum < 0.0) maxLength = 7;
        else maxLength = 6;

        int s = Float.toString(transactionSum).length();

        String tSum = Float.toString(transactionSum);
        if(s > maxLength) tSum = tSum.substring(0, maxLength);

        while(tSum.length() < 7){
            tSum = " " + tSum;
        }
        return tSum;
    }

    public String getTransactionCategory(){
        return transactionCathegory;
    }

    public LocalDate getTransactionDate(){return transactionDate; }

    @Override
    public int compareTo(Transaction o){
        int i = transactionDate.compareTo(o.getTransactionDate());
        return i;
    }
}
