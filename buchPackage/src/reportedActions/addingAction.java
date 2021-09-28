package reportedActions;

import java.util.LinkedList;

public class addingAction extends moneyAction{

    public addingAction (float sum, String cathegory, String description){
        transactionSum = sum;
        transactionCathegory = cathegory;
        transactionDescription = description;
    }

    public static void registerAction (float sum,String cathegory, String description, LinkedList<moneyAction> list) {

        list.add(new addingAction(sum, cathegory, description));
    }

    public static String actionToText(int i, LinkedList<moneyAction> list){
        System.out.println("Umwandlung in Textform 1");
        System.out.println(Float.toString(list.get(i).getTransactionSum()));
        System.out.println(list.get(i).getTransactionCathegory());
        String retVal = Float.toString(list.get(i).getTransactionSum()) + " " + list.get(i).getTransactionCathegory();
        System.out.println("Umwandlung in Textform 2");
        return retVal;
    }
}
