package entity;

import java.util.LinkedList;

public class TransactionList {
    public LinkedList<Transaction> list;

    public TransactionList(){
        list = new LinkedList<Transaction>();
    }

    public void add(Transaction transaction){
        list.add(transaction);
    }

}
