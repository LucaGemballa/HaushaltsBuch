package reportedActions;

import java.util.LinkedList;

public class addingAction extends moneyAction{

    public addingAction (long sum, String cathegory, String description){
        transactionSum = sum;
        transactionCathegory = cathegory;
        transactionDescription = description;
    }

    public void registerAction (long sum,String cathegory, String description, LinkedList<moneyAction> list) {

        list.add(new addingAction(sum, cathegory, description));
    }
}
