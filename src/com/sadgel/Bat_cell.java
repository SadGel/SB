package com.sadgel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.PublicKey;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Гель on 21.02.2017.
 */
public class Bat_cell {

    private Color colorBg;
    private JButton newButton = new JButton();
    private int x, y;
    public boolean vertal = false;


    private boolean isShield = false;
    private boolean deck = false;

    public Set ship;
    public Set shield;



    public Bat_cell[][] arOur2 = new Bat_cell[11][11];

    public Bat_Field bf;


    public Bat_cell(JFrame MainWindow, int x, int y, int xp, int yp, Bat_cell[][] arOur,Bat_Field bf) {
        Color Bg;

        MainWindow.setLayout(null);
        newButton.setSize(20, 20);
        newButton.setLocation(x * 20 + xp, y * 20 + yp);
        newButton.addMouseListener(new CustomListener());
        //newButton.addMouseListener(new EventLis());
        Bg = new Color(255, 255, 255);
        this.colorBg = Bg;
        newButton.setBackground(colorBg);
        this.x = x;
        this.y = y;
        arOur[x][y] = this;
        arOur2 = arOur;
        this.bf = bf;
        ship = new HashSet();

        MainWindow.getContentPane().add(newButton);


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
                Bg = new Color(255, 0, 0);
            }
            else{
                Bg = new Color(255, 0, 255);
            }

            //this.full = true;
            this.deck = true;
            }
            else {
            Bg = new Color(255, 255, 255);
            //this.full = false;
            this.deck = false;
            //this.ship.clear();
        }
        this.colorBg = Bg;

        newButton.setBackground(colorBg);
    }

    public void setColorBgOver2(int c, int p, boolean vert, int x, int y) {

        Color Bg;
        if (c == 1) Bg = new Color(255, 0, 0);
        else Bg = new Color(255, 255, 255);

        if (vert) {
            for (int i = 1; i < p; i++) {
                int resy = y + i;
                if (resy > 10) resy = resy - p;
                if (resy < 1) resy = resy + p;
                arOur2[x][resy].setDeck(c);
            }
        } else {

            for (int i = 1; i < p; i++) {
                int resy = x + i;
                if (resy > 10) resy = resy - p;
                if (resy < 1) resy = resy + p;
                arOur2[resy][y].setDeck(c);
            }

        }


    }


    public class CustomListener implements MouseListener {


        public void mouseClicked(MouseEvent e) {

            if (bf.isMy) {

                JButton button = (JButton) e.getSource();
                if (e.getButton() == 3) {
                    if (isDeck()) {

                        //System.out.println(Bat_cell.this.ship.size());

                        Set_ships.rotateShip(Bat_cell.this);
                    }

                }

                if (e.getButton() == 1) {

                    Set_ships.moveShip(Bat_cell.this);

                }

            }

            //String text = "<html><b>" + button.getText()
            //        + " mouseReleased() <br>" + button.getText()
            //        + " mouseClicked() </b><html>";
            //eventLabel.setText(text);
        }

        public void mouseEntered(MouseEvent e) {
            //JButton button = (JButton) e.getSource();
           //setDeck(1);
           bf.setX(x);
            bf.setY(y);
           bf.setTextLabel("X: "+x+" Y: "+y);
            //setColorBgOver(1, 4, SB_gen.vertal, x, y);
            //eventLabel.setText(button.getText() + " mouseEntered()");
        }

        public void mouseExited(MouseEvent e) {
            //JButton button = (JButton) e.getSource();
           //setDeck(0);
            bf.setTextLabel("");

           //setColorBgOver(2, 4, SB_gen.vertal, x, y);
            //eventLabel.setText(button.getText() + " mouseExited()");
        }

        public void mousePressed(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            //System.out.println(getX() + " " + getY());
            //eventLabel.setText(button.getText() + " mousePressed()");
        }

        public void mouseReleased(MouseEvent e) {
            JButton button = (JButton) e.getSource();
           // System.out.println(getX() + " " + getY());
            //eventLabel.setText(button.getText() + " mouseReleased()");
        }
    }


}
