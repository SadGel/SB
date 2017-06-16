package com.sadgel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by User15 on 09.06.2017.
 */
public class SB_battle {


    private static void shooting(Bat_cell bc, boolean izVne) {

        if (izVne) {
            Color Bg = new Color(255, 0, 0);
            Color BgSt = bc.colorBg;


            for (int i = 1; i < 3; i++) {

                bc.Butt.setEnabled(false);
                bc.Butt.setBackground(Bg);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bc.Butt.setEnabled(true);
                bc.Butt.setBackground(BgSt);
            }

            //bc.bf.bw.setOurTern(true);

        } else {

            if (bc.isDeck()) {
                //bc.Butt.set("X");


                //ImageIcon icon = new ImageIcon("res/cross20.png");
                Image img = new ImageIcon(SB_battle.class.getClass().getResource("/res/cross20.png")).getImage();
                ImageIcon icon = new ImageIcon(img);
                //bc.Butt.setOpaque(false);
                bc.Butt.setIcon(icon);
                bc.Butt.setDisabledIcon(icon);

                bc.Butt.setEnabled(false);
                bc.Butt.repaint();


            } else {
                //ImageIcon icon = new ImageIcon("res/dot20.png");

                Image img = new ImageIcon(SB_battle.class.getClass().getResource("/res/dot20.png")).getImage();
                ImageIcon icon = new ImageIcon(img);
                bc.Butt.setIcon(icon);
                bc.Butt.setDisabledIcon(icon);


                bc.Butt.setEnabled(false);

                //bc.Butt.setOpaque(false);
                bc.Butt.repaint();

                //bc.bf.bw.setOurTern(false);

            }

        }
    }

    public static void setShoot(MainWindow bw, int x, int y) {

        if (bw.isOurTern()) {

            shooting(bw.bf2.arOur[x][y], false); //установка хода

            if (isShipDown(bw.bf2.arOur[x][y])) {  //проверка на уничтожение карабля
               shipDown(bw.bf2.arOur[x][y]); //уничтожение карабля
            }

            bw.setOurTern(bw.bf2.arOur[x][y].isDeck()); //проверка на передачу хода

            

        } else {

            shooting(bw.bf1.arOur[x][y], true);

        }


    }

    public static void enemyTurn(MainWindow bw) {

        setShoot(bw, 5, 5);

        bw.setOurTern(true);


    }

    public static boolean isShipDown(Bat_cell bc) {

        Iterator<Bat_cell> iterator = bc.ship.iterator();

        while (iterator.hasNext()) {
            if (!iterator.next().pressed) {
                return false;
            }
        }
        return true;

    }

    public static void shipDown(Bat_cell bc) {
        int dx, dy, x, y;
        Iterator<Bat_cell> iterator = bc.ship.iterator();

        while (iterator.hasNext()) {
            Bat_cell bt = iterator.next();

                x = bt.getX();
                y = bt.getY();

                dx = x + 1;
                dy = y;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    //bt.bf.arOur[dx][dy].setShield(true);
                    if (!bt.bf.arOur[dx][dy].pressed)
                    shooting(bt.bf.arOur[dx][dy], false);
                }

                dx = x - 1;
                dy = y;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bt.bf.arOur[dx][dy].pressed)
                    shooting(bt.bf.arOur[dx][dy], false);
                }

                dx = x;
                dy = y + 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bt.bf.arOur[dx][dy].pressed)
                    shooting(bt.bf.arOur[dx][dy], false);
                }

                dx = x;
                dy = y - 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    shooting(bt.bf.arOur[dx][dy], false);
                }

                dx = x + 1;
                dy = y - 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bt.bf.arOur[dx][dy].pressed)
                    shooting(bt.bf.arOur[dx][dy], false);
                }

                dx = x + 1;
                dy = y + 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bt.bf.arOur[dx][dy].pressed)
                    shooting(bt.bf.arOur[dx][dy], false);
                }

                dx = x - 1;
                dy = y + 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bt.bf.arOur[dx][dy].pressed)
                    shooting(bt.bf.arOur[dx][dy], false);
                }

                dx = x - 1;
                dy = y - 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bt.bf.arOur[dx][dy].pressed)
                    shooting(bt.bf.arOur[dx][dy], false);
                }










        }


    }


}

