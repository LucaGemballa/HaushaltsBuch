package reportedActions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class spendingAction extends moneyAction{
    public spendingAction (float sum, String cathegory, String description){
        transactionSum = sum;
        transactionCathegory = cathegory;
        transactionDescription = description;
    }

    public void registerAction (float sum,String cathegory, String description, LinkedList<moneyAction> list){

        list.add(new spendingAction(sum,cathegory,description));

        /*try(BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();


        }
        catch(Exception e) {
        }

         */

    }
}
