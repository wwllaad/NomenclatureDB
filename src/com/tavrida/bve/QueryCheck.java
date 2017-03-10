package com.tavrida.bve;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryCheck {
//класс методов проверки регулярных выражений в строке поиска приложения

    public static boolean isID(String data) {
        Pattern p = Pattern.compile("[a-zA-Z0-9]+");
        Matcher m = p.matcher(data);
        return m.matches();
    }

    public static boolean isPartCode(String data) {
        Pattern p = Pattern.compile("[a-zA-Z0-9_.,%()-]+");
        Matcher m = p.matcher(data);
        return m.matches();
    }

    public static boolean isInt(String data) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(data);
        return m.matches();
    }


}
