package service;

import entity.Transaction;
import entity.TransactionWeight;

import java.time.*;

import java.util.LinkedList;

public class TransactionService {

    private RootService rootService;

    public TransactionService(RootService rS){
        rootService = rS;
    }

    public void registerTransaction (float sum, String cathegory, LocalDate date, String source, TransactionWeight weight, String description) {
        rootService.transactionList.add(new Transaction(sum, cathegory,date,source,weight,description));
    }

}
