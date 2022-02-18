package entity;

public class Transaction {

    float transactionSum;
    String transactionCathegory;
    String transactionDescription;

    public Transaction (float sum, String cathegory, String description){
        transactionSum = sum;
        transactionCathegory = cathegory;
        transactionDescription = description;
    }

    public String toText(){
        System.out.println("Umwandlung in Textform 1" );
        System.out.println(Float.toString(transactionSum));
        System.out.println(transactionCathegory);
        String retVal = Float.toString(transactionSum) + " " + transactionCathegory;
        System.out.println("Umwandlung in Textform 2");
        return retVal;
    }

    public float getTransactionSum(){
        return transactionSum;
    }

    public String getTransactionCathegory(){
        return transactionCathegory;
    }
}
