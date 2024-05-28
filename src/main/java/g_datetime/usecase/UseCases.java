package g_datetime.usecase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class UseCases {

    public static void main(String[] args) {

        List<String> dateOptions = dateOptionsJava7();
        dateOptions = datOptionsJava8();
        dateOptions.forEach(System.out::println);
    }


    public static List<String> datOptionsJava8() {
        ArrayList<String> dateOptions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MM dd", Locale.ENGLISH);

        LocalDate ldt = LocalDate.now();
        for (int i = 0; i < 4; i++) {
            dateOptions.add(formatter.format(ldt));
            ldt = ldt.plusDays(1);
        }

        return dateOptions;
    }

    public static List<String> dateOptionsJava7() {
        ArrayList<String> dateOptions = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("E MM dd", Locale.getDefault());

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 4; i++) {
            dateOptions.add(formatter.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }

        return dateOptions;
    }
}
