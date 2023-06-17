package entity;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SavingsAccount {
    public float balance;
    public String name;
    private DecimalFormat df;

    public SavingsAccount(String n, float b){
        balance = b;
        name = n;
        df = new DecimalFormat("0.00");
    }

    public void bookTransaction(float sum){
        balance = balance+sum;
    }

    public String toText(){
        return name + " " + Float.toString(balance);
    }

    public String getBalanceString(){
        return df.format(balance);
    }
}
