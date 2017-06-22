package com.sadgel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Гель on 21.02.2017.
 */
public class Bat_cell {

    public Color colorBg;
    public JButton Butt = new JButton();
    private int x, y;
    public boolean pressed = false;


    private boolean isShield = false;
    private boolean deck = false;

    public Set ship;
    public Set shield;


    public Bat_cell[][] arOur2 = new Bat_cell[11][11];

    public Bat_Field bf;


    public Bat_cell(JFrame MainWindow, int x, int y, int xp, int yp, Bat_cell[][] arOur, Bat_Field bf) {
        Color Bg;

        MainWindow.setLayout(null);
        Butt.setSize(20, 20);
        Butt.setLocation(x * 20 + xp, y * 20 + yp);
        Butt.addMouseListener(new CustomListener());
        //newButton.addMouseListener(new EventLis());
        Bg = new Color(255, 255, 255);
        this.colorBg = Bg;
        Butt.setBackground(colorBg);
        this.x = x;
        this.y = y;
        arOur[x][y] = this;
        arOur2 = arOur;
        this.bf = bf;
        ship = new HashSet();

        Butt.setFocusPainted(false);

        MainWindow.getContentPane().add(Butt);


    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public boolean isDeck() {
        return deck;
    }

    public boolean isFull() {

        if (isShield | deck)
            return true;
        else
            return false;
    }

    public void setShield(boolean isShield) {

        if (!this.deck & isShield)
            this.isShield = true;
        else
            this.isShield = false;

    }


    public void setDeck(int c) {
        Color Bg;
        if (c == 1) {
            if (bf.isMy) {
                Bg = new Color(0, 250, 200);
            } else {
                int enemyColor = 255;
                if (bf.bw.compVsComp) enemyColor = 0;
                Bg = new Color(255, enemyColor, 255); //цвет врага
            }

            this.deck = true;
        } else {
            Bg = new Color(255, 255, 255);
            this.deck = false;
        }
        this.colorBg = Bg;

        Butt.setBackground(colorBg);
    }


    public class CustomListener implements MouseListener {


        public void mouseClicked(MouseEvent e) {


            if (bf.isMy) {
                if (bf.bw.isGameBegin()) {

                    if (pressed) return;
                    if (!bf.bw.isOurTern()) return;

                } else {

                    if (!isDeck() & !bf.shipIsTaken) return; //бага

                    JButton button = (JButton) e.getSource();
                    if (e.getButton() == 3) {
                        if (isDeck()) {

                            Set_ships.rotateShip(Bat_cell.this);
                        }

                    }

                    if (e.getButton() == 1) {

                        Set_ships.moveShip(Bat_cell.this);

                    }

                }

            } else {
                if (bf.bw.isGameBegin()) {

                    if (pressed) return;
                    if (!bf.bw.isOurTern()) return;

                    if (e.getButton() == 1) {
                        SB_battle.setShoot(bf.bw, getX(), getY());
                    }

                } else {


                }

            }

        }

        public void mouseEntered(MouseEvent e) {
            bf.setX(x);
            bf.setY(y);
            bf.setTextLabel("X: " + x + " Y: " + y);
        }

        public void mouseExited(MouseEvent e) {
            bf.setTextLabel("");

        }

        public void mousePressed(MouseEvent e) {
            JButton button = (JButton) e.getSource();
        }

        public void mouseReleased(MouseEvent e) {
            JButton button = (JButton) e.getSource();
        }
    }


}
