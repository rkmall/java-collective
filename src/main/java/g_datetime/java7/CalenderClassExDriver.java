package g_datetime.java7;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class CalenderClassExDriver {

    public static void main(String[] args) {

        //calendarGetters();
        //calendarSetters();
        //calendarArithmetic();
    }

    public static void calendarGetters() {
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Jan=0, Feb=1, March=2 ...
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // SUNDAY=1, MONDAY=2 ...
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("Year: " + year +
                "\nMonth: " + month +
                "\nDay of week: " + dayOfWeek +
                "\nDay of month: " + dayOfMonth +
                "\nWeek of month: " + weekOfMonth +
                "\nWeek of year: " + weekOfYear);
    }

    public static void calendarSetters() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 2070);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Day: " + calendar.get(Calendar.DAY_OF_MONTH) +
                ", Month: " + calendar.get(Calendar.MONTH) +
                ", Year: " + calendar.get(Calendar.YEAR));
    }

    public static void calendarArithmetic(){
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        System.out.println("Day: " + calendar.get(Calendar.DAY_OF_MONTH) +
                ", Month: " + calendar.get(Calendar.MONTH) +
                ", Year: " + calendar.get(Calendar.YEAR));  // Day: 31, Month: 11, Year 2020

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Day: " + calendar.get(Calendar.DAY_OF_MONTH) +
                ", Month: " + calendar.get(Calendar.MONTH) +
                ", Year: " + calendar.get(Calendar.YEAR));  // Day: 1, Month: 0, Year 2021


        calendar.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println("Day: " + calendar.get(Calendar.DAY_OF_MONTH) +
                ", Month: " + calendar.get(Calendar.MONTH) +
                ", Year: " + calendar.get(Calendar.YEAR));  // Day: 31, Month: 11, Year 2020
    }
}
