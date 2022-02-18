package service;

import entity.Transaction;

import java.util.LinkedList;

public class TransactionService {

    private RootService rootService;

    public TransactionService(RootService rS){
        rootService = rS;
    }

    public void registerTransaction (float sum,String cathegory, String description) {
        rootService.transactionList.add(new Transaction(sum, cathegory, description));
    }

}
