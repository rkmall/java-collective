package g_datetime.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeExDriver {

    public static void main(String[] args) {

        //getLocalDateTime();
        //parseLocalDateTime();
        formatLocalDateTime();
        //localDateTimeArithmetic();

        //getLocalTime();
        //parseLocalTime();
        //formatLocalTime();
        //localTimeArithmetic();

        //getLocalDate();
        //parseLocalDate();
        //formatLocalDate();
        //localDateArithmetic();
    }

    //-------------------------------LocalDateTime---------------------------------------
    public static void getLocalDateTime() {
        // Get from now
        LocalDateTime dt1 = LocalDateTime.now();
        System.out.println("From now: " + dt1);

        Clock clock = Clock.system(ZoneId.systemDefault());
        LocalDateTime dt2 = LocalDateTime.now(clock);
        System.out.println("From now and clock: " + dt2);

        // Get from year, month and day
        LocalDateTime dt3 = LocalDateTime.of(2021, 3, 5, 21, 30, 50);
        System.out.println("From date and time: " + dt3);

        // Get from Temporal object
        LocalDateTime dt4 = LocalDateTime.from(ZonedDateTime.now());
        System.out.println("From temporal object: " + dt4);
    }

    public static void parseLocalDateTime() {
        LocalDateTime dt1 = LocalDateTime.parse("2007-12-10T21:30:45");
        System.out.println("Parsed LocalDateTime: " + dt1);

        LocalDateTime dt2 = LocalDateTime.parse("2012-10-10T05:05:01", DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("Parsed LocalDateTime: " + dt2);
    }

    public static void formatLocalDateTime() {
        LocalDateTime dt1 = LocalDateTime.now();
        String formattedDateTimeStr = dt1.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("Formatted DateTime String: " + formattedDateTimeStr);

        LocalDateTime dt2 = LocalDateTime.now();
        formattedDateTimeStr = dt2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("Formatted DateTime String: " + formattedDateTimeStr);
    }

    public static void localDateTimeArithmetic() {
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime future = time
                .plusNanos(3000)
                .plusSeconds(30)
                .plusMinutes(10)
                .plusHours(12)
                .plusDays(1)
                .plusWeeks(3)
                .plusMonths(2)
                .plusYears(1);
        System.out.println("LocalDateTime arithmetic: " + future);
    }

    //-------------------------------LocalTime---------------------------------------
    public static void getLocalTime() {
        // Get from now
        LocalTime time1 = LocalTime.now();
        System.out.println("From now: " + time1);

        Clock clock = Clock.system(ZoneId.systemDefault());
        LocalTime time2 = LocalTime.now(clock);
        System.out.println("From now and clock: " + time2);

        // Get from year, month and day
        LocalTime time3 = LocalTime.of(21, 30);
        System.out.println("From hour and minutes: " + time3);

        // Get from Temporal object
        LocalTime time4 = LocalTime.from(ZonedDateTime.now());
        System.out.println("From temporal object: " + time4);
    }

    public static void parseLocalTime() {
        LocalTime time1 = LocalTime.parse("21:30:45");
        System.out.println("Parsed LocalTime: " + time1);

        LocalTime time2 = LocalTime.parse("21:45:50", DateTimeFormatter.ISO_TIME);
        System.out.println("Parsed LocalTime: " + time2);
    }

    public static void formatLocalTime() {
        LocalTime time1 = LocalTime.now();
        String formattedTimeStr = time1.format(DateTimeFormatter.ISO_TIME);
        System.out.println("Formatted Time String: " + formattedTimeStr);

        LocalTime time2 = LocalTime.now();
        formattedTimeStr = time2.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("Formatted Time String: " + formattedTimeStr);
    }

    public static void localTimeArithmetic() {
        LocalTime time = LocalTime.now();
        LocalTime future = time
                .plusNanos(3000)
                .plusSeconds(30)
                .plusMinutes(10)
                .plusHours(12);
        System.out.println("LocalTime arithmetic: " + future);
    }

    //-------------------------------LocalDate---------------------------------------
    public static void getLocalDate() {
        // Get from now
        LocalDate date1 = LocalDate.now();
        System.out.println("From now: " + date1);

        Clock clock = Clock.system(ZoneId.systemDefault());
        LocalDate date2 = LocalDate.now(clock);
        System.out.println("From now and clock: " + date2);

        // Get from year, month and day
        LocalDate date3 = LocalDate.of(2050, 1,1);
        System.out.println("From year, month and day: " + date3);

        // Get from Temporal object
        LocalDate date4 = LocalDate.from(ZonedDateTime.now());
        System.out.println("From temporal object: " + date4);
    }

    public static void parseLocalDate() {
        LocalDate date1 = LocalDate.parse("2023-12-23");
        System.out.println("Parsed LocalDate: " + date1);

        LocalDate date2 = LocalDate.parse("2023-12-23", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("Parsed LocalDate: " + date2);
    }

    public static void formatLocalDate() {
        LocalDate date1 = LocalDate.now();
        String formattedDateStr = date1.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("Formatted Date String: " + formattedDateStr);

        LocalDate date2 = LocalDate.now();
        formattedDateStr = date2.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println("Formatted Date String: " + formattedDateStr);
    }

    public static void localDateArithmetic() {
        LocalDate date = LocalDate.now();
        LocalDate future = date
                .plusDays(5)
                .plusWeeks(3)
                .plusMonths(1)
                .plusYears(1);
        System.out.println("LocalDate arithmetic: " + future);
    }
}
