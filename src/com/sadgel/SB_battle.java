package com.sadgel;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by User15 on 09.06.2017.
 */
public class SB_battle {


    private static void shooting(Bat_cell bc, boolean izVne) {

        bc.pressed = true;

        if (izVne) {
            Image img = new ImageIcon(SB_battle.class.getClass().getResource("/res/aim20.png")).getImage();
            ImageIcon icon = new ImageIcon(img);

            for (int i = 1; i <= 2; i++) {
                int timeOut = 200;
                if (bc.bf.bw.compVsComp) timeOut = 0;
                bc.Butt.setIcon(icon);
                bc.Butt.setDisabledIcon(icon);
                try {
                    TimeUnit.MILLISECONDS.sleep(timeOut);
                } catch (InterruptedException e) {
                }
                bc.Butt.setIcon(null);
                bc.Butt.setDisabledIcon(null);

                try {
                    TimeUnit.MILLISECONDS.sleep(timeOut);
                } catch (InterruptedException e) {
                }
            }

        }

        if (bc.isDeck()) {

            Image img = new ImageIcon(SB_battle.class.getClass().getResource("/res/cross20.png")).getImage();
            ImageIcon icon = new ImageIcon(img);
            //bc.Butt.setOpaque(false);
            bc.Butt.setIcon(icon);
            bc.Butt.setDisabledIcon(icon);

            bc.Butt.setEnabled(false);
            bc.Butt.repaint();


        } else {

            Image img = new ImageIcon(SB_battle.class.getClass().getResource("/res/dot20.png")).getImage();
            ImageIcon icon = new ImageIcon(img);
            bc.Butt.setIcon(icon);
            bc.Butt.setDisabledIcon(icon);


            bc.Butt.setEnabled(false);
            bc.Butt.repaint();

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

            if (isShipDown(bw.bf1.arOur[x][y])) {  //проверка на уничтожение карабля
                shipDown(bw.bf1.arOur[x][y]); //уничтожение карабля
            }

            bw.setOurTern(!bw.bf1.arOur[x][y].isDeck()); //проверка на передачу хода

        }


    }

    public static void enemyTurn(MainWindow bw) {

        int[] rez = SB_enemy.AI_enemy(bw.bf1);

        setShoot(bw, rez[0], rez[1]);


    }

    public static void enemyTurn2(MainWindow bw) {

        int[] rez = SB_enemy.AI_enemy2(bw.bf2);
        setShoot(bw, rez[0], rez[1]);

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

        Image img = new ImageIcon(SB_battle.class.getClass().getResource("/res/crossDeth20.png")).getImage();
        ImageIcon icon = new ImageIcon(img);

        while (iterator.hasNext()) {

            Bat_cell bt = iterator.next();

            bt.Butt.setIcon(icon);
            bt.Butt.setDisabledIcon(icon);

            bt.Butt.repaint();

            x = bt.getX();
            y = bt.getY();

            //Убрать установку битых клеток при "убит"
            //if (bc.bf.bw.isOurTern()) {
            //   x = 200;
            //    y = 200;
            //}
            //Убрать установку битых клеток при "убит"

            dx = x + 1;
            dy = y;

            if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
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
                if (!bt.bf.arOur[dx][dy].pressed)
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

        isBattleEnds(bc);


    }

    public static boolean isBattleEnds(Bat_cell bc) {
        boolean rez = false;
        if (isHasALiveDecks(bc.bf) == 0) {


            if (bc.bf.isMy) {
                System.out.println("Вы проиграли!");
                bc.bf.bw.score1++;
                bc.bf.bw.tabloScore.setText(bc.bf.bw.score1 + " : " + bc.bf.bw.score2);
                if (!bc.bf.bw.compVsComp) {
                    SB_menu.Set_buttHide(bc.bf.bw, false);
                    showALive(bc.bf.bw);
                    JOptionPane.showMessageDialog(bc.bf.bw, "Вы проиграли!");
                    Set_ships.setAllShips(bc.bf.bw.bf1);
                    Set_ships.setAllShips(bc.bf.bw.bf2);
                }
            } else {
                //bc.bf.bw.
                System.out.println("Вы выиграли!");
                bc.bf.bw.score2++;
                bc.bf.bw.tabloScore.setText(bc.bf.bw.score1 + " : " + bc.bf.bw.score2);
                if (!bc.bf.bw.compVsComp) {
                    SB_menu.Set_buttHide(bc.bf.bw, false);
                    JOptionPane.showMessageDialog(bc.bf.bw, "Вы выиграли!");
                    Set_ships.setAllShips(bc.bf.bw.bf1);
                    Set_ships.setAllShips(bc.bf.bw.bf2);
                }
            }

            bc.bf.bw.setGameBegin(false);

            rez = true;

        }

        return rez;
    }

    public static int getMaxDeckLiveShip(Bat_Field bf) {
        int rez = 0;

        Iterator iterator = bf.ships.iterator();
        while (iterator.hasNext()) {
            Set ship = (Set) iterator.next();
            Bat_cell[] shipAr = (Bat_cell[]) ship.toArray(new Bat_cell[ship.size()]);
            if (!shipAr[0].pressed) {
                if (rez < ship.size()) rez = ship.size();
            }
        }

        return rez;
    }

    public static int isHasALiveDecks(Bat_Field bf) {
        int rez = 0;

        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                if (bf.arOur[x][y].isDeck() & !bf.arOur[x][y].pressed) rez++;
            }
        }

        return rez;
    }

    public static void showALive(MainWindow bw) {

        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                if (bw.bf2.arOur[x][y].isDeck() & !bw.bf2.arOur[x][y].pressed) {
                    bw.bf2.arOur[x][y].Butt.setBackground(new Color(255, 0, 0));
                }
            }
        }


    }
}

