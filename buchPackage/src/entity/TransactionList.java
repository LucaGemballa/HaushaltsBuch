package entity;

import java.util.LinkedList;
public class TransactionList {
    public LinkedList<Transaction> list;

    public LinkedList<TransactionList> monthList;

    public LinkedList<TransactionList> yearList;

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
}
