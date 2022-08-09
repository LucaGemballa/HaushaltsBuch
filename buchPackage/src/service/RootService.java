package service;

import entity.SavingsAccount;
import entity.TransactionList;

import java.util.LinkedList;

public class RootService {
    /*
    service attributes are defined here
     */
    public TransactionService transactionService;
    public FileService fileService;
    public CategoryService categoryService;
    public SavingsAccountService savingsAccountService;


    /*
    other attributes concerning data are defined here
     */
    public TransactionList transactionList;
    public LinkedList<SavingsAccount> accountList;

    public RootService(){
        transactionService = new TransactionService(this);
        fileService = new FileService(this);
        categoryService = new CategoryService(this);
        savingsAccountService = new SavingsAccountService(this);

        transactionList = new TransactionList();
        accountList = new LinkedList<SavingsAccount>();
    }

}
