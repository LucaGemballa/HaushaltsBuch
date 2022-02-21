package entity;

public class SavingsAccount {
    public float balance;
    public String name;

    public SavingsAccount(String n, float b){
        balance = b;
        name = n;
    }

    public void bookTransaction(float sum){
        balance = balance+sum;
    }

    public String toText(){
        return name + " " + Float.toString(balance);
    }
}
