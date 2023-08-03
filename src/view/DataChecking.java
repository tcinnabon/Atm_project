package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataChecking {

    public static boolean checkCardNumber(String numberCard){
        String templateCard = "^[0-9]{4}[\\-][0-9]{4}[\\-][0-9]{4}[\\-][0-9]{4}";
        Matcher m = Pattern.compile(templateCard).matcher(numberCard);
        if (m.find( )) {
            return true;
        }else {
            System.out.println("Некорректный номер карты");
            return false;
        }


    }

    public static boolean checkCardPinCod(String pinCodCard){
        String templatePinCod = "^[0-9]{4}$";
        Matcher m = Pattern.compile(templatePinCod).matcher(pinCodCard);
        if (m.find( )) {
            return true;
        }else {
            System.out.println("Некорректный пинкод карты");
            return false;
        }

    }

}
