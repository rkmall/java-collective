package b_oop.d_innerclasses.example;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class TalkingClockWithLambda {
    private int interval;
    private boolean beep;

    public TalkingClockWithLambda(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start(String prefix){
        Timer timer = new Timer(interval, e -> {        // passing lambda call site
            System.out.println(prefix + ":" + LocalDateTime.now());
            if (beep) Toolkit.getDefaultToolkit().beep();
        });
        timer.start();
    }
}
