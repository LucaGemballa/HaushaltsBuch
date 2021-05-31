package reportedActions;

public class spendingAction extends moneyAction{
    public spendingAction (long sum, String cathegory, String description){
        transactionSum = sum;
        transactionCathegory = cathegory;
        transactionDescription = description;
    }
}
