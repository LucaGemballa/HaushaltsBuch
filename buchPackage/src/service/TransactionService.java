package service;

import entity.Transaction;
import entity.TransactionList;
import entity.TransactionWeight;
import java.text.DecimalFormat;

import java.time.*;

import java.util.LinkedList;

import java.math.BigDecimal;

public class TransactionService {

    private DecimalFormat df;

    private RootService rootService;

    public TransactionService(RootService rS){
        rootService = rS;
        df = new DecimalFormat("0.00");
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

        for (TransactionList yL: rootService.transactionList.yearList) {
            if(date.getYear() == yL.year){
                for(TransactionList mL : yL.monthList){
                    if(date.getMonthValue() == mL.month){
                        for(Transaction tr : mL.list){
                            sum += tr.getTransactionSum();
                        }
                    }
                }
            }
        }
        return sum;
    }

    /*
    Use this method to calculate balance of spending and earning in a given month and get it in String format

    need to rework so it doesnt include the same month for every year!!!!
     */
    public String calculateMonthlyBalanceString(LocalDate date){
        float sum = 0;
        for (TransactionList yL: rootService.transactionList.yearList) {
            System.out.println("Y"  + yL.year);
            if(date.getYear() == yL.year){
                for(TransactionList mL : yL.monthList){
                    System.out.println("M" + mL.month);
                    if(date.getMonthValue() == mL.month){
                        for(Transaction tr : mL.list){
                            System.out.println("Hey");
                            sum += tr.getTransactionSum();
                        }
                    }
                }
            }
        }
        System.out.println(sum);
        return df.format(sum);
    }

    public float round2(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;
    }





}
