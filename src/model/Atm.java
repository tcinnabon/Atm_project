package model;

public class Atm {
    private int amountMoney;

    public Atm(int amountMoney) {
        this.amountMoney = amountMoney;
    }

    public void topUp(int amountMoney){
        this.amountMoney += amountMoney;
    }

    public void takeOff(int amountMoney){
        this.amountMoney -=amountMoney;
    }

    public  int getAmountMoney(){
        return this.amountMoney;
    }
}
