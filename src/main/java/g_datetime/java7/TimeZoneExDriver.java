package g_datetime.java7;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeZoneExDriver {

    public static void main(String[] args) {
        //getTimeZoneFromCalendar();
        //createTimeZone();
        //timeZoneNamesIDOffsets();
        calculateTimeZoneDifference();
    }

    public static void getTimeZoneFromCalendar(){
        Calendar calendar = new GregorianCalendar();
        TimeZone systemTz = calendar.getTimeZone();
        System.out.println("System: TZ ID: " + systemTz.getID() +
                ", TZ Name: " + systemTz.getDisplayName());
    }

    public static void createTimeZone() {
        // Default TimeZone
        TimeZone defaultTz = TimeZone.getDefault();
        System.out.println("Default: TZ ID: " + defaultTz.getID() +
                ", TZ Name: " + defaultTz.getDisplayName());

        // Specific TimeZone
        TimeZone specificTz = TimeZone.getTimeZone("Asia/Kathmandu");
        System.out.println("Specific: TZ ID: " + specificTz.getID() +
                ", TZ Name: " + specificTz.getDisplayName());
    }

    public static void timeZoneNamesIDOffsets() {
        TimeZone tz = TimeZone.getDefault();
        System.out.println("TimeZone ID: " + tz.getID() +
                "\nDisplay name: " + tz.getDisplayName() +
                "\nOffset: " + tz.getOffset(System.currentTimeMillis()));
    }

    public static void calculateTimeZoneDifference(){
        Calendar calendar = new GregorianCalendar();

        // TimeZone1
        TimeZone london = TimeZone.getTimeZone("Europe/London");
        calendar.setTimeZone(london);
        long londonTimeInMillis = calendar.getTimeInMillis();
        int londonHour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("London time in millis: " + londonTimeInMillis +
                ", hour: " + londonHour);

        // TimeZone2
        TimeZone tokyo = TimeZone.getTimeZone("Asia/Tokyo");
        calendar.setTimeZone(tokyo);
        long tokyoTimeInMillis = calendar.getTimeInMillis();
        int tokyoHour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("Tokyo time in millis: " + tokyoTimeInMillis +
                ", hour: " + tokyoHour);

        // Difference between TimeZone1 and TimeZone2
        System.out.println("Hour difference: " + (tokyoHour - londonHour));
    }
}
