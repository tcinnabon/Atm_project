package view;

import controller.AtmOperation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String numberCard;
        String pinCodCard;
        boolean flagRun = true;
        AtmOperation atmOperation = null;

        try {
            atmOperation = new AtmOperation();
            flagRun = true;

        }catch (Exception e){
            System.out.println("Невозможно получить данные, проверьте файл. Сеанс завершён!");
            flagRun = false;
        }
       if(flagRun) {
           Scanner in = new Scanner(System.in);
           do{
               System.out.println("    Введите номер карты ");
               numberCard = in.next();
           }while (!DataChecking.checkCardNumber(numberCard) || !atmOperation.searchBackAccount(numberCard));

           do{
               System.out.println("    Введите пинкод карты ");
               try {
                   pinCodCard = in.next();
               } catch (Exception e) {
                   pinCodCard = "0";
               }
           }while (!DataChecking.checkCardPinCod(pinCodCard) || !atmOperation.setCurrentBackAccount(pinCodCard));

           String menuSelection = "";
           do {
               System.out.println("    1 - просмотреть баланс");
               System.out.println("    2 - пополнить баланс");
               System.out.println("    3 - снять деньги с карты ");
               menuSelection = in.next();
               switch (menuSelection) {
                   case "1":
                       atmOperation.checkCardBalance();
                       break;
                   case "2":
                       try {
                           String money = "";
                           System.out.print(" Введите сумму  ");
                           money = in.next();
                           if (Integer.parseInt(money) < 1_000_000) atmOperation.topUpAccount(Integer.parseInt(money));
                           else System.out.println("Сумма не должна первышать 1000000 ");
                       } catch (Exception e) {
                           System.out.println("Вы введели некорректную сумму, попробуйте ещё раз ");
                       }
                       break;
                   case "3":
                       try {
                           String money = "";
                           System.out.print(" Введите сумму  ");
                           money = in.next();
                           atmOperation.withdrawMoney(Integer.parseInt(money));
                       } catch (Exception e) {
                           System.out.println("Вы введели некорректную сумму, попробуйте ещё раз ");
                       }
                       break;
                   default:
                       System.out.println("Данной команды не существует, попробуйте ещё раз ");
                       break;


               }
               atmOperation.fileUpdate();
           } while (true);


       }
    }
}