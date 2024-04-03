package asm03;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getDivider(){
        String divider = "+---------*--------------*-----------+";
        return  divider;
    }

    public static String formatBalance(double amount){
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(amount);
    }

    //    show thoi gian giao dich cua tung bien
    public static String getDateTime(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String strDate = formatter.format(date);
        return strDate;

    }
}
