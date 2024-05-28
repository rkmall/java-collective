package b_oop.c_interfaces.callback_ex.timer_ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class TimerExDriver {

    public static void main(String[] args) {
        // Timer class is a Caller whose constructor takes interface type as argument
        // ActionListener is the Callback and provided as implementation on callsite to
        // Caller Timer.
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Time: " + LocalDate.now());
                Toolkit.getDefaultToolkit().beep();
            }
        });
        timer.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
