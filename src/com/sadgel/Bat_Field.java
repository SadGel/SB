package com.sadgel;


import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Bat_Field {

    public int xp, yp;

    public Bat_cell[][] arOur = new Bat_cell[11][11];

    private int x,y;

    private JLabel label;

    public Set ships = new HashSet();

    public boolean shipIsTaken;

    public ArrayList<Bat_cell> takenShip;

    public Boolean isMy;

    public MainWindow bw;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTextLabel (String str) {

        this.label.setText(str);

    }

    public Bat_Field(int xp, int yp, MainWindow baseWin, boolean isMy) {

        takenShip = new ArrayList<Bat_cell>();


        this.xp = xp;
        this.yp = yp;
        this.isMy = isMy;
        this.bw = baseWin;


        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                Bat_cell bt = new Bat_cell(baseWin, x, y, xp, yp, arOur, this);
            }


        }


        JLabel label = new JLabel();
        label.setLocation(xp + 100, yp + 2);
        label.setSize(100, 20);

        this.label = label;

        baseWin.getContentPane().add(label);


    }


}
