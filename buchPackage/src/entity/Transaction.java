package entity;

import java.time.*;

public class Transaction implements Comparable<Transaction>{

    float transactionSum;
    String transactionCategory;
    String transactionDescription;
    LocalDate transactionDate;
    String transactionSource;
    String transactionDestination;
    TransactionWeight transactionWeight;
    int transactionID;


    public Transaction (float sum, String category,LocalDate date,String source, String destination, TransactionWeight weight, String description, int id){
        transactionSum = sum;
        transactionCategory = category;
        transactionDescription = description;
        transactionDate = date;
        transactionSource = source;
        transactionDestination = destination;
        transactionWeight = weight;
        transactionID = id;
    }

    public String toText(){
        int maxLength = 0;
        if (transactionSum < 0.0) maxLength = 7;
        else maxLength = 6;

        int s = Float.toString(transactionSum).length();

        String tSum = Float.toString(transactionSum);
        if(s > maxLength) tSum = tSum.substring(0, maxLength);

        // do not really know what this was intended to do!
        /*
        while(tSum.length() < 7){
            tSum = " " + tSum;
        }

         */

        String retVal = tSum + " " + transactionCategory + " " + transactionDate + " " + transactionSource + " "+ transactionDestination + " " + transactionDescription;
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

    public String getTransactionDescription() {return transactionDescription;}

    public String getTransactionCategory(){
        return transactionCategory;
    }

    public LocalDate getTransactionDate(){return transactionDate; }

    public int getTransactionID(){return transactionID; }

    public void setTransactionSource(String s){
        transactionSource = s;
    }

    @Override
    public int compareTo(Transaction o){
        int i = transactionDate.compareTo(o.getTransactionDate());
        return i;
    }
}
