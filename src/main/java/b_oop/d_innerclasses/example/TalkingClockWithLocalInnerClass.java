package b_oop.d_innerclasses.example;

import b_oop.d_innerclasses.example.TalkingClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class TalkingClockWithLocalInnerClass {
    private int interval;
    private boolean beep;

    public TalkingClockWithLocalInnerClass(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    // Start the clock
    public void start(String prefix){
        // Local inner class (i.e. class inside a method)
        class TimePrinterLocal implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(prefix + ":" + LocalDateTime.now());
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        }
        ActionListener listener = new TimePrinterLocal();
        Timer timer = new Timer(interval, listener);
        timer.start();
    }
}
