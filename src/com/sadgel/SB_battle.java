package com.sadgel;

import javax.swing.*;
import java.awt.*;
import java.security.PublicKey;
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

            bc.bf.bw.setOurTern(true);

        }
        else {

            if (bc.isDeck()) {
                //bc.Butt.set("X");


                ImageIcon icon = new ImageIcon("cross20.png");


               //bc.Butt.setOpaque(false);
                bc.Butt.setIcon(icon);
                bc.Butt.setDisabledIcon(icon);

                bc.Butt.setEnabled(false);
                bc.Butt.repaint();

            }
            else {
                ImageIcon icon = new ImageIcon("dot20.png");
                bc.Butt.setIcon(icon);
                bc.Butt.setDisabledIcon(icon);

                bc.Butt.setEnabled(false);

                //bc.Butt.setOpaque(false);
               bc.Butt.repaint();

                bc.bf.bw.setOurTern(false);

            }

        }
    }

    public static void setShoot(MainWindow bw, int x, int y) {

        if (bw.isOurTern()) {

            shooting(bw.bf2.arOur[x][y], false);

        }
        else {

            shooting(bw.bf1.arOur[x][y], true);

        }


    }

    public static void enemyTurn(MainWindow bw) {

        setShoot(bw, 5, 5);

        bw.setOurTern(true);


    }


}

