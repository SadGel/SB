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
    private boolean isGameBegin = false;
    private boolean ourTern;

    public void setOurTern(boolean ourTern) {
        this.ourTern = ourTern;
    }

    public void setGameBegin(boolean gameBegin) {
        isGameBegin = gameBegin;

    }

    public MainWindow() {

        super("Морской бой 2.1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);


       // super.addMouseWheelListener(new EventLis());

        SB_menu.set_butt(this, 20, 300);


    }


}
