package service;

import entity.SavingsAccount;

import java.util.LinkedList;

public class SavingsAccountService {

    private RootService rootService;

    public int numberOfAccounts;

    public SavingsAccountService(RootService rS){
        rootService = rS;
        numberOfAccounts = 0;
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
        int returnID = -1;
        for (SavingsAccount s: rootService.accountList) {
            if(s.name.equals(accName)){
                System.out.println("Found Account!");
                returnID = s.getAccountID();
            }
        }
        return returnID;
    }

    public void createNewAccount(String accName){

    }


}
