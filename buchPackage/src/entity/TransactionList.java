package entity;

import view.Main;

import java.util.LinkedList;
public class TransactionList {
    public LinkedList<Transaction> list;

    public LinkedList<TransactionList> monthList;

    public LinkedList<TransactionList> yearList;
    /*
     use the IDCounter to generate individual identification for each transaction
     IDs will be generated from scratch after the program start!
     */
    public int transactionIDCounter ;

    public int month;

    public int year;

    public Integer freeYear;
    private Integer lowestYear;

    /*
    use this for initializing upper level of list, for saving years
     */
    public TransactionList(){

        yearList = new LinkedList<TransactionList>();
        freeYear = 0;
        month = 0;
        year = 0;
        transactionIDCounter = 0;
    }

    /*
    use this for lower ranking transaction lists, as data structure
     */
    public TransactionList(int m, int y){

        list = new LinkedList<Transaction>();
        monthList = new LinkedList<TransactionList>();

        month = m;
        year = y;
    }


    /*
    call this only on upper level with year = 0 and month = 0
     */
    public void add(Transaction transaction) {
        assert ((year == 0)  && (month == 0)) : "Adding Transaction with this function is not possible";

        boolean monthFound = false;
        boolean yearFound = false;
        //list.add(transaction);
        int transMonth = transaction.getTransactionDate().getMonthValue();
        int transYear = transaction.getTransactionDate().getYear();

        for (TransactionList yL : yearList){

            if (yL.year == transYear) {

                yearFound = true;

                for (TransactionList mL : yL.monthList){
                    if (mL.month == transMonth){
                        monthFound = true;
                        mL.list.add(transaction);
                    }
                }
                if(!monthFound){
                    TransactionList tempList = new TransactionList(transMonth, yL.year);
                    tempList.list.add(transaction);
                    yL.monthList.add(tempList);
                }
            }
        }
        if(!yearFound ){
            TransactionList tempListY = new TransactionList(0, transYear);
            TransactionList tempListM = new TransactionList(transMonth, transYear);
            tempListM.list.add(transaction);
            tempListY.monthList.add(tempListM);
            yearList.add(tempListY);
        }
    }
    public void delete(Transaction t){
        int transMonth = t.getTransactionDate().getMonthValue();
        int transYear = t.getTransactionDate().getYear();
        String transSource = t.transactionSource;
        String transDestination = t.transactionDestination;

        if(!transSource.equals("Einnahme")){
            Main.rootService.accountList.get(Main.rootService.savingsAccountService.getAccountID(transSource)).bookTransaction(t.transactionSum);
        }
        if(!transDestination.equals("Ausgabe")){
            Main.rootService.accountList.get(Main.rootService.savingsAccountService.getAccountID(transDestination)).bookTransaction(- t.transactionSum);
        }


        for (TransactionList yL : yearList){

            if (yL.year == transYear) {

                for (TransactionList mL : yL.monthList){
                    if (mL.month == transMonth){
                        mL.list.remove(t);
                    }
                }
            }
        }
        System.out.println("Transaktion gelöscht");
    }

    /*
    Use this function to get a single list (not ordered I assume) of all transactions
     */
    public LinkedList<Transaction> getAllTransactions(){
        LinkedList<Transaction> returnList = new LinkedList<Transaction>();

        for (TransactionList yL : yearList){
            for (TransactionList mL : yL.monthList){
                returnList.addAll(mL.list);
            }
        }
        return returnList;
    }


    public String getTransactionString(){
        String transactionString = "";
        for (TransactionList yL : yearList){
            for (TransactionList mL : yL.monthList){
                for (Transaction t: mL.list){
                    transactionString = transactionString + t.toText() + "\n";
                }
            }
        }
        return transactionString;
    }
}
