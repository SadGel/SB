package com.sadgel;

import java.awt.event.*;

public class SB_gen {
    static boolean vertal = false;

    public static void main(String[] args) {

        MainWindow baseWin = new MainWindow();


        Bat_Field bf1 = new Bat_Field(0, 40, baseWin, true);

        Bat_Field bf2 = new Bat_Field(230, 40, baseWin, false);

        baseWin.bf1 = bf1;
        baseWin.bf2 = bf2;


        baseWin.addMouseWheelListener(new EventLis());
        baseWin.setVisible(true);


    }

    public static class EventLis implements MouseWheelListener {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            SB_gen.vertal = !SB_gen.vertal;
        }
    }
}
