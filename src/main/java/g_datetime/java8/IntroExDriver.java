package g_datetime.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class IntroExDriver {

    public static void main(String[] args) {

        //instantMethods();
        parseInstant();
        //formatInstant();
        //measureTime();
        //instantToLocalDateTime();
        //dateToLocalDateTime();
    }

    public static void instantMethods(){
        // Get instant
        Instant now = Instant.now(); // current point on the time-line
        long epochSec = now.getEpochSecond(); // seconds since EPOCH
        int nano = now.getNano();             // nano - part of the Instant second
        System.out.println("Now: " + now +
                "\nNow nano: " + nano +
                "\nNow epoch: " + epochSec);

        // Equality and Comparison
        Instant now1 = Instant.now();
        System.out.println("Equals: " + now.equals(now1));
        System.out.println("Compare: " + now.compareTo(now1));

        // Get instant from Epoch
        Instant now2 = Instant.ofEpochSecond(epochSec);
        System.out.println("Instant of epoch second: " + now2);

        Instant now3 = Instant.ofEpochSecond(epochSec, 300);
        System.out.println("Instant of epoch second, nano adjusted: " + now3);

        Instant now4 = Instant.ofEpochMilli(epochSec * 1000);   // ms = s * 1000
        System.out.println("Instant of epoch milli: " + now4);

        // Get instant from TemporalAccessor
        Instant now5 = Instant.from(ZonedDateTime.now());
        System.out.println("Instant from temporal accessor: " + now5);

        // Get Instant from parsing text
        Instant now6 = Instant.parse("2023-12-21T11:29:06.000000300Z");
        System.out.println("Instant from parsing text: " + now6);
    }

    public static void parseInstant() {
        Instant parsedInstant = Instant.parse("2023-12-21T11:29:06.000000300Z");
        System.out.println("Parsed Instant: " + parsedInstant);
    }

    public static void formatInstant() {
        Instant now = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        String formattedInstant = formatter.format(now);
        System.out.println("Formatted Instant: " + formattedInstant);
    }

    public static void measureTime(){
        // Duration between Instants
        Instant start = Instant.now();
        printNum();
        Instant end = Instant.now();
        Duration elapsedTime = Duration.between(start, end);
        System.out.println("Elapsed time in ns (int): " + elapsedTime.getNano());
        System.out.println("Elapsed time in s: " + elapsedTime.getSeconds());

        System.out.println("Elapsed time in ns (long): " + elapsedTime.toNanos());
        System.out.println("Elapsed time in ms: " + elapsedTime.toMillis());
        System.out.println("Elapsed time in minutes: " + elapsedTime.toMinutes());
        System.out.println("Elapsed time in hours: " + elapsedTime.toHours());
        System.out.println("Elapsed time in days: " + elapsedTime.toDays());

        // Duration between LocalDateTime
        LocalDateTime start1 = LocalDateTime.of(2021, 11, 21, 20, 0, 0 );
        LocalDateTime end1 = LocalDateTime.of(2021, 11, 22, 20, 0, 0 );
        elapsedTime = Duration.between(start1, end1);
        System.out.println("Time difference in hours: " + elapsedTime.toMinutes());
        System.out.println("Time difference in minutes: " + elapsedTime.toHours());
        System.out.println("Time difference in days: " + elapsedTime.toDays());
    }

    public static void instantToLocalDateTime() {
        Instant instant = Instant.now();
        System.out.println("Instant: " + instant);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        System.out.println("LocalDateTime: " + localDateTime);
    }

    public static void dateToLocalDateTime() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        System.out.println("System Default TimeZone : " + defaultZoneId);
        Date date = new Date();
        System.out.println("date : " + date);

        //1. Convert Date -> Instant
        Instant instant = date.toInstant();
        System.out.println("instant : " + instant);

        //2. Instant + system default time zone + toLocalDate() = LocalDate
        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
        System.out.println("localDate : " + localDate);

        //3. Instant + system default time zone + toLocalDateTime() = LocalDateTime
        LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        System.out.println("localDateTime : " + localDateTime);

        //4. Instant + system default time zone = ZonedDateTime
        ZonedDateTime zonedDateTime = instant.atZone(defaultZoneId);
        System.out.println("zonedDateTime : " + zonedDateTime);
    }

    private static void printNum(){
        for (int i = 0; i < 1_000_000; i++){
            System.out.println(i);
        }
    }
}
