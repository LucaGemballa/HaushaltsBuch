package service;

import entity.SavingsAccount;
import view.MainScreenController;

import java.util.LinkedList;

public class SavingsAccountService {

    private RootService rootService;

    private MainScreenController mainController;

    public int numberOfAccounts;

    public SavingsAccountService(RootService rS){
        rootService = rS;
        numberOfAccounts = 0;
    }

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public MainScreenController getMainController(){
        return mainController;
    }

    public String accountListToText(){
        String returnList = "";

        for (SavingsAccount s: rootService.accountList) {
            returnList += s.toText() + "\n";
        }

        return  returnList;
    }

    public int getAccountID(String accName){
        System.out.println(accName);
        int returnIDHelper = -1;
        int returnID = 0;
        for (SavingsAccount s: rootService.accountList) {
            returnIDHelper++;
            if(s.name.equals(accName)){
                System.out.println("Found Account!");
                returnID = returnIDHelper;
            }
        }
        return returnID;
    }

    public void createNewAccount(String accName){

    }


}
