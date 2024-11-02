package sashtgnv.plannerLogic.dates;

import sashtgnv.plannerLogic.database.DBConnection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class DateMethods {
    private static final Calendar DATE = new GregorianCalendar();
    public static final Calendar DEFAULT_DATE = new GregorianCalendar(DATE.get(Calendar.YEAR),
            DATE.get(Calendar.MONTH),
            DATE.get(Calendar.DATE));

    public static Calendar CURRENT_DATE = (GregorianCalendar) DEFAULT_DATE.clone();

    public static String getMonthYear(){
        String fullDate = CURRENT_DATE.getTime().toString();
        String[] splitDate = fullDate.split(" ");
        return russifierMonth(splitDate[1]) + " " + splitDate[5];
    }

    private static String russifierMonth(String month){
        return switch (month) {
            case "Jan" -> "Январь";
            case "Feb" -> "Февраль";
            case "Mar" -> "Март";
            case "Apr" -> "Апрель";
            case "May" -> "Май";
            case "Jun" -> "Июнь";
            case "Jul" -> "Июль";
            case "Aug" -> "Август";
            case "Sep" -> "Сентябрь";
            case "Oct" -> "Октябрь";
            case "Nov" -> "Ноябрь";
            case "Dec" -> "Декабрь";
            default -> "";
        };
    }

    public static int getFirstDowInMonth(){
        Calendar FirstDay = CURRENT_DATE;
        FirstDay.set(Calendar.DAY_OF_MONTH,1);
        if (FirstDay.get(Calendar.DAY_OF_WEEK)-2>=0) {
            return FirstDay.get(Calendar.DAY_OF_WEEK)-2;
        }
        else {return 6;}
    }

    public static int getDaysInMonth(){
        int res = CURRENT_DATE.getActualMaximum(Calendar.DAY_OF_MONTH);
        return res;
    }

    public static String dateToString(){
        return CURRENT_DATE.get(Calendar.YEAR)+"-"+(CURRENT_DATE.get(Calendar.MONTH)+1)+
                "-" +CURRENT_DATE.get(Calendar.DATE);
    }

    public static String dateToString(Calendar date){
        return date.get(Calendar.YEAR)+"-"+(date.get(Calendar.MONTH)+1)+
                "-" +date.get(Calendar.DATE);
    }

    public static void main(String[] args) {
        System.out.println(DEFAULT_DATE.get(Calendar.YEAR));
        System.out.println(DEFAULT_DATE.get(Calendar.MONTH));
        System.out.println(DEFAULT_DATE.get(Calendar.DATE));

    }
}
