package com.sadgel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.Font;

/**
 * Created by User15 on 30.05.2017.
 */
public class SB_menu {

    public static MainWindow bw;
    //public static JButton[] menu_but;

    public static void set_butt(MainWindow baseWin, int x, int y) {

        bw = baseWin;

        bw.menu_but = new JButton[4];


        JButton setShipsButton = new JButton();
        baseWin.setLayout(null);
        setShipsButton.setSize(100, 20);
        setShipsButton.setLocation(x, y);
        setShipsButton.setText("Set random");
        ActionListener actionListenerSet = new SetActionListener();
        setShipsButton.addActionListener(actionListenerSet);
        baseWin.getContentPane().add(setShipsButton);
        bw.menu_but[1] = setShipsButton;

        JButton StartButton = new JButton();
        baseWin.setLayout(null);
        StartButton.setSize(100, 20);
        StartButton.setLocation(x, y + 20);
        StartButton.setText("Start H vs C");
        ActionListener actionListenerStart = new StartActionListener();
        StartButton.addActionListener(actionListenerStart);
        baseWin.getContentPane().add(StartButton);
        bw.menu_but[2] = StartButton;

        JButton StartButtonCvC = new JButton();
        baseWin.setLayout(null);
        StartButtonCvC.setSize(100, 20);
        StartButtonCvC.setLocation(x, y + 40);
        StartButtonCvC.setText("Start C vs C");
        ActionListener actionListenerStartCvC = new StartActionListenerCvC();
        StartButtonCvC.addActionListener(actionListenerStartCvC);
        baseWin.getContentPane().add(StartButtonCvC);
        bw.menu_but[3] = StartButtonCvC;

        JButton newGameButton = new JButton();
        baseWin.setLayout(null);
        newGameButton.setSize(100, 20);
        newGameButton.setLocation(x, y + 60);
        newGameButton.setText("Multiplayer");
        ActionListener actionListener = new NewGameActionListener();
        newGameButton.addActionListener(actionListener);
        baseWin.getContentPane().add(newGameButton);
        bw.menu_but[0] = newGameButton;


        JLabel label = new JLabel();
        label.setLocation(150, 5);
        label.setSize(200, 45);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setText("Установите корабли..");
        bw.tablo = label;
        baseWin.getContentPane().add(label);


        JLabel labelScore = new JLabel();
        labelScore.setLocation(250, 300);
        labelScore.setSize(200, 45);
        labelScore.setFont(new Font("Serif", Font.PLAIN, 40));
        labelScore.setText("0 : 0");
        bw.tabloScore = labelScore;
        baseWin.getContentPane().add(labelScore);


    }

    public static class SetActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Set_ships.setAllShips(bw.bf1);
            Set_ships.setAllShips(bw.bf2);

        }
    }

    public static class NewGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try {
                SB_web.newGame(bw);

            } catch (MalformedURLException e1) {
                System.out.println(e1);
            }

        }
    }


    public static class StartActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            bw.compVsComp = false;
            bw.setGameBegin(true);
            bw.t1 = new SB_enemy(bw);
            Thread t1 = new Thread(bw.t1);
            t1.start();
            Set_buttHide(bw, true);


        }
    }

    public static class StartActionListenerCvC implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            bw.compVsComp = true;
            bw.setGameBegin(true);
            bw.t1 = new SB_enemy(bw);
            Thread t1 = new Thread(bw.t1);
            t1.start();
            Set_buttHide(bw, true);
        }
    }

    public static void Set_buttHide(MainWindow baseWin, boolean hide) {
        for (int i = 0; i <= 3; i++) {
            baseWin.menu_but[i].setEnabled(!hide);
        }

    }
}
