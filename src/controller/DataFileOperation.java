package controller;

import model.Atm;
import model.BankCard;
import model.DataFile;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataFileOperation {
    private static String fileName = "data.txt";

    private DataFile dataFile;

    public DataFileOperation() {


        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String dateFile = reader.readLine();
             dataFile = new DataFile(dateFile.split(" "));
        }
        catch (Exception e){
            System.out.println("Возникла ошибка чтения из файла");
        }

    }

    public void fileUpdate(Atm atm, ArrayList<BankCard> bankCards) {
        try (FileOutputStream fos = new FileOutputStream(fileName, false)) { }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(fileName, true);
            int a = atm.getAmountMoney();
            writer.write(String.valueOf(a));
            for (int i =0; i < bankCards.size(); i++){
                writer.write(" " + bankCards.get(i).getNumberCard());
                writer.write(" " + bankCards.get(i).getPinCod());
                writer.write(" " + bankCards.get(i).getBalanceCardFromBankAccaount());
                writer.write(" " + bankCards.get(i).getDateLocked());
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи в файл");
        }
    }
    public String returnBalanceAtm(){
        return dataFile.returnBalanceAtm();
    }

    public int sizeData(){
        return dataFile.sizeData();
    }

    public String returnElement(int index){
        return dataFile.returnElement(index);
    }
}
