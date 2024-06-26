package b_oop.d_innerclasses.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    // Start the clock
    public void start(){
        ActionListener listener = new TimePrinter(); // new TimePrinter(this); internally auto generated
        Timer timer = new Timer(interval, listener);
        timer.start();
    }

    public class TimePrinter implements ActionListener {
        // Internally automatically generated by the compiler:
        /* private TalkingClock outer;
        public TimePrinter(TalkingClock clock) {
            outer = clock;
        }*/

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("Time: " + LocalDateTime.now());
            if (beep) Toolkit.getDefaultToolkit().beep(); // inner class can access outer class fields
        }                                                 // incl. private fields
    }
}
