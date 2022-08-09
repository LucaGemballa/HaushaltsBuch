package service;

import entity.Transaction;
import entity.TransactionWeight;

import java.time.*;

import java.util.LinkedList;

import java.math.BigDecimal;

public class TransactionService {

    private RootService rootService;

    public TransactionService(RootService rS){
        rootService = rS;
    }

    public void registerTransaction (Transaction t) {
        rootService.transactionList.add(t);
        //rootService.accountList.getFirst().bookTransaction(sum);
    }

    /*
    calculate monthly balance for a date in any given month
     */
    public float calculateMonthlyBalance(LocalDate date){
        float sum = 0;
        for ( LinkedList<Transaction> month: rootService.transactionList.monthList) {
            if(date.getMonth().equals(month.getFirst().getTransactionDate().getMonth()) ){
                for (Transaction tr : month){
                    sum += tr.getTransactionSum();
                }
            }
        }
        return sum;
    }

    public float round2(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;
    }





}
