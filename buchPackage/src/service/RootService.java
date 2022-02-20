package service;

import entity.TransactionList;

public class RootService {
    /*
    service attributes are defined here
     */
    public TransactionService transactionService;
    public FileService fileService;
    public CategoryService categoryService;


    /*
    other attributes concerning data are defined here
     */
    public TransactionList transactionList;



    public RootService(){
        transactionService = new TransactionService(this);
        fileService = new FileService(this);
        categoryService = new CategoryService(this);

        transactionList = new TransactionList();
    }

}
