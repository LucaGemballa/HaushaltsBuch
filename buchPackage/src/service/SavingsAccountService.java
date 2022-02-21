package service;

import entity.SavingsAccount;

import java.util.LinkedList;

public class SavingsAccountService {

    private RootService rootService;

    public SavingsAccountService(RootService rS){
        rootService = rS;
    }

    public String accountListToText(){

        String returnList = "";

        for (SavingsAccount s: rootService.accountList) {
            returnList += s.toText() + "\n";
        }

        return  returnList;
    }


}
