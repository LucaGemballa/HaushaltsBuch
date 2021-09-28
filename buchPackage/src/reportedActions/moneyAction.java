package reportedActions;

public abstract class moneyAction {
    private int transactionNumber;
    public float transactionSum;
    //private String priority;
    public String transactionCathegory;
    public String transactionDescription;

    public float getTransactionSum(){
        return transactionSum;
    }
    public String getTransactionCathegory(){
        return transactionCathegory;
    }
}