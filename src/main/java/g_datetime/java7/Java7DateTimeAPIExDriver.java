package g_datetime.java7;

import java.awt.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.stream.IntStream;

public class Java7DateTimeAPIExDriver {

    public static void main(String[] args) {

        System.out.println(new Date());

        //systemCurrentTimeMillis();
        //utilDateClassEx();
        //sqlDateClassEx();
        //sqlTimeStampClassEx();

        //formatDate();
        //simpleDateFormatParseDateString();
        //formatPatternsExample();
    }

    public static void systemCurrentTimeMillis() {
        long start = System.currentTimeMillis();
        sumUp();
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start) + " ms");
    }

    public static void utilDateClassEx() {
        Date date = new Date();
        System.out.println(date);

        // Date from now
        long now = System.currentTimeMillis();
        Date date1 = new Date(now);
        System.out.println(date1);

        System.out.println("Compare dates: " + date.compareTo(date1));
    }

    public static void sqlDateClassEx() {
        long now = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(now);
        System.out.println("Sql date: " + date);
    }

    public static void sqlTimeStampClassEx() {
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        System.out.println("Sql timestamp: " + timestamp);

        timestamp.setNanos(300);
        int nanos = timestamp.getNanos();
        System.out.println("Sql timestamp nanos: " + nanos);
        System.out.println("Sql timestamp: " + timestamp);
    }

    public static void formatDate() {
        System.out.println("Default format: " + new Date());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS ", Locale.ENGLISH);
        String formattedDate = formatter.format(new Date());
        System.out.println("Formatted date: " + formattedDate);
    }

    public static void simpleDateFormatParseDateString() {
        String dateString = "01-01-1970";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            Date date = formatter.parse(dateString);
            System.out.println(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void formatPatternsExample() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd u/E HH:mm:ss.SSS z");
        String formattedDate = formatter.format(date);
        System.out.println("Formatted date: " + formattedDate);
    }

    public static void sumUp() {
        int sum = IntStream.range(1, 1_000_000).sum();
        System.out.println("Sum: " + sum);
    }
}
