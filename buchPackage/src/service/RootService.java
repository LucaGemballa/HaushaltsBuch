package service;

import entity.TransactionList;

public class RootService {
    /*
    service attributes are defined here
     */
    public TransactionService transactionService;
    public FileService fileService;


    /*
    other attributes concerning data are defined here
     */
    public TransactionList transactionList;



    public RootService(){
        transactionService = new TransactionService(this);
        fileService = new FileService(this);

        transactionList = new TransactionList();
    }

}
