package com.sadgel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Created by Гель on 21.02.2017.
 */
public class MainWindow extends JFrame {

    public Bat_Field bf1;
    public Bat_Field bf2;
    public JButton [] menu_but;
    public JLabel tablo;
    volatile private boolean isGameBegin = false;
    volatile private boolean ourTern;

    public void setOurTern(boolean ourTern) {

        this.ourTern = ourTern;

        if (ourTern) {
            tablo.setText("ВАШ ХОД");
        }else {
            tablo.setText("ХОД СОПРЕНИКА");
        }




    }

     public boolean isOurTern() {
        return ourTern;
    }

    public boolean isGameBegin() {
        return isGameBegin;
    }

    public void setGameBegin(boolean gameBegin) {
        isGameBegin = gameBegin;
        setOurTern(true);

    }





    public MainWindow() {

        super("Морской бой 2.2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);


       // super.addMouseWheelListener(new EventLis());

        SB_menu.set_butt(this, 20, 300);


    }


}
