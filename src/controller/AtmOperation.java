package controller;

import model.Atm;
import model.BankCard;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AtmOperation {
    private ArrayList<BankCard> bankCards ;
    private BankCard currentBackCard;
    private Atm atm;
    private DataFileOperation dataFile;


    public AtmOperation() {
        dataFile = new DataFileOperation();
        bankCards = new ArrayList<>();
        atm = new Atm(Integer.parseInt(dataFile.returnBalanceAtm()));
        for (int i = 1; i < dataFile.sizeData(); ) {
            bankCards.add(new BankCard(dataFile.returnElement(i),
                    dataFile.returnElement(i + 1),
                    Integer.parseInt(String.valueOf(dataFile.returnElement(i + 2))),
                    Long.parseLong(dataFile.returnElement(i+3))));
            i += 4;
        }
    }

    public boolean setCurrentBackAccount(String pinCod){

        if(currentBackCard.getDateLocked() == 0) {
            if (Objects.equals(currentBackCard.getPinCod(), pinCod)) {
                System.out.println("Данные были введены успешно");
                return true;
            } else {
                System.out.println("Неверный пинкод");
                currentBackCard.increaseNumberFailed();
                if(currentBackCard.getNumberFailed() == 3) {
                    Date date = new Date();
                    currentBackCard.setDateLocked(date.getTime());
                    System.out.println("Карта заблокирована ");
                   fileUpdate();
                }
                return false;
            }
        }else {
            System.out.println("Карта заблокирована");
        }
        return false;
    }

    public boolean searchBackAccount(String numberCard){
        for(int i =0; i <bankCards.size();i++){
            if(Objects.equals(bankCards.get(i).getNumberCard(), numberCard)){
                currentBackCard = bankCards.get(i);
                if(currentBackCard.getDateLocked()!=0){
                    unlockTimer();
                }
                return true;
            }
        }
        System.out.println("Неверный номер карты ");
        return false;
    }
    public void checkCardBalance(){
        System.out.println("Текущий бананс : " + currentBackCard.getBalanceCardFromBankAccaount());
    }
    public void topUpAccount(int money){
        currentBackCard.topUpBankAccount(money);
        atm.topUp(money);
        System.out.println("Деньги были внесены");
    }

    public void withdrawMoney(int money){
        if(money> currentBackCard.getBalanceCardFromBankAccaount()){
            System.out.println("На вашем счёте недостаточно средств");
        }else if (money> atm.getAmountMoney()){
            System.out.println("Средств в банкомате не хватает");
        }else {
            currentBackCard.withdrawMoneyBankAccount(money);
            atm.takeOff(money);
            System.out.println("Деньги сняты");
        }
    }


    public void unlockTimer(){
        long oneDayMls = 86_400_000;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flagRunTread = true;
                while (flagRunTread) {
                    Date date = new Date();
                    if (date.getTime() - currentBackCard.getDateLocked() >= oneDayMls) {
                        currentBackCard.resetDataLocked();
                        System.out.println("карта разблокирована");
                        fileUpdate();
                        flagRunTread = false;

                    }
                }
            }
        });
        thread.start();
    }

    public void fileUpdate() {
        dataFile.fileUpdate(atm, bankCards);
    }

}
