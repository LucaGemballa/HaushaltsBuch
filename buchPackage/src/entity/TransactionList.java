package entity;

import java.util.LinkedList;
public class TransactionList {
    public LinkedList<Transaction> list;
    public LinkedList<LinkedList<Transaction>> monthList;

    public TransactionList(){

        list = new LinkedList<Transaction>();
        monthList = new LinkedList<LinkedList<Transaction>>();
    }

    public void add(Transaction transaction){
        boolean monthFound = false;
        list.add(transaction);
        for ( LinkedList<Transaction> month: monthList ) {
            if(transaction.transactionDate.getMonth().equals(month.getFirst().transactionDate.getMonth()) ){
                month.add(transaction);
                monthFound= true;
            }
        }

        /*
        create new entry for monthList if no Transaction existed for a given month before
         */
        if(!monthFound ){
            LinkedList<Transaction> newMonth = new LinkedList<Transaction>();
            newMonth.add(transaction);
            monthList.add(newMonth);
        }
    }

}
