package g_datetime.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeExDriver {

    public static void main(String[] args) {

        //getZonedDateTime();
        //calculateDifference();
        //parseZonedDateTime();
        //formatZonedDateTime();
        zonedDateTimeArithmetic();
    }

    public static void getZonedDateTime() {
        // Get from now
        ZonedDateTime zone1 = ZonedDateTime.now();
        System.out.println("From now: " + zone1);

        Clock clock = Clock.system(ZoneId.systemDefault());
        ZonedDateTime zone2 = ZonedDateTime.now(clock);
        System.out.println("From now and clock: " + zone2);

        // Get from LocalDateTime and ZoneId
        ZonedDateTime zone3 = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Europe/London"));
        System.out.println("From local date-time and zone id: " + zone3);

        // Get from Temporal object
        ZonedDateTime paris = ZonedDateTime.of(
                LocalDateTime.now(ZoneId.of("Europe/Paris")),
                ZoneId.of("Europe/Paris"));
        ZonedDateTime zone4 = ZonedDateTime.from(paris);
        System.out.println("From temporal object: " + zone4);

        // Convert to OffsetDateTime
        OffsetDateTime offset = paris.toOffsetDateTime();
        System.out.println(offset);
    }

    public static void calculateDifference() {
        // Calculate difference using LocalDateTime
        LocalDateTime london = LocalDateTime.now(ZoneId.of("Europe/London"));
        LocalDateTime paris = LocalDateTime.now(ZoneId.of("Europe/Paris"));
        System.out.println(london);
        System.out.println(paris);
        System.out.println("Time difference: " + (london.getHour() - paris.getHour()));

        // Calculate difference using ZonedDateTime
        ZonedDateTime zone1 = ZonedDateTime.of(london, ZoneId.of("Europe/London"));
        ZonedDateTime zone2 = ZonedDateTime.of(paris, ZoneId.of("Europe/Paris"));
        System.out.println(zone1);
        System.out.println(zone2);
        System.out.println("Time difference: " + (zone1.getHour() - zone2.getHour()));
    }

    public static void parseZonedDateTime() {
        ZonedDateTime zone1 = ZonedDateTime.parse("2023-12-27T12:56:46.597276Z[Europe/London]");
        System.out.println("Parsed ZonedDateTime: " + zone1);

        ZonedDateTime zone2 = ZonedDateTime.parse("2023-12-27T12:56:46.597276Z[Europe/London]",
                DateTimeFormatter.ISO_ZONED_DATE_TIME);
        System.out.println("Parsed ZonedDateTime: " + zone2);
    }

    public static void formatZonedDateTime() {
        ZonedDateTime zone1 = ZonedDateTime.now();
        String formattedDateTimeStr = zone1.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        System.out.println("Formatted DateTime String: " + formattedDateTimeStr);

        ZonedDateTime zone2 = ZonedDateTime.now();
        formattedDateTimeStr = zone2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS z"));
        System.out.println("Formatted DateTime String: " + formattedDateTimeStr);
    }

    public static void zonedDateTimeArithmetic() {
        ZonedDateTime time = ZonedDateTime.now();
        ZonedDateTime future = time
                .plusNanos(3000)
                .plusSeconds(30)
                .plusMinutes(10)
                .plusHours(12)
                .plusDays(1)
                .plusWeeks(3)
                .plusMonths(2)
                .plusYears(1);
        System.out.println("ZonedDateTime arithmetic: " + future);
    }
}
