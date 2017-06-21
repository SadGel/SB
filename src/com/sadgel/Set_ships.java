package com.sadgel;

import java.util.*;

/**
 * Created by User15 on 02.05.2017.
 */
public class Set_ships {

    // Первоначальная установка караблей
    private static int findEdge(boolean vert, int dd, Set decks) {
        int rezmax = 0, rezmin = 0;
        Iterator iterator = decks.iterator();

        if (vert) {

            while (iterator.hasNext()) {
                Bat_cell deck = (Bat_cell) iterator.next();
                rezmax = deck.getY() > rezmax ? deck.getY() : rezmax;
                rezmin = deck.getY() < rezmin ? deck.getY() : rezmin;
            }

            return dd == 1 ? rezmax + 1 : rezmin - 1;

        } else {

            while (iterator.hasNext()) {
                Bat_cell deck = (Bat_cell) iterator.next();
                rezmax = deck.getX() > rezmax ? deck.getX() : rezmax;
                rezmin = deck.getX() < rezmin ? deck.getX() : rezmin;
            }
            return dd == 1 ? rezmin - 1 : rezmax + 1;
        }


    }

    public static boolean setShipRandom(int d, Bat_Field bf) {

        boolean rez, vert = true;
        int dx = 0, dy = 0;

        Set decks = new HashSet();

        Random random = new Random();

        int x = random.nextInt(10) + 1;
        int y = random.nextInt(10) + 1;

        if (bf.arOur[x][y].isFull()) {
            return false;
        } else {
            decks.add(bf.arOur[x][y]);
            rez = true;
        }

        if (d > 1) {

            int dd = random.nextInt(4) + 1;

            switch (dd) {
                case 1:
                    dx = 0;
                    dy = 1;
                    vert = true;
                    break;
                case 2:
                    dx = 1;
                    dy = 0;
                    vert = false;
                    break;
                case 3:
                    dx = 0;
                    dy = -1;
                    vert = true;
                    break;
                case 4:
                    dx = -1;
                    dy = 0;
                    vert = false;
                    break;
            }

            if ((x + dx > 10) | (y + dy > 10) | (y + dy < 1) | (x + dx < 1)) {
                return false;
            }

            if (bf.arOur[x + dx][y + dy].isFull()) {
                return false;
            } else {
                decks.add(bf.arOur[x + dx][y + dy]);
                rez = true;
            }

        }

        if (d > 2) {

            int dd = random.nextInt(2) + 1;

            if (vert) {
                dx = x;
                dy = findEdge(vert, dd, decks);
            } else {

                dx = findEdge(vert, dd, decks);
                dy = y;

            }

            if ((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1)) {
                return false;
            }

            if (bf.arOur[dx][dy].isFull()) {
                return false;
            } else {
                decks.add(bf.arOur[dx][dy]);
                rez = true;
            }

        }

        if (d > 3) {

            int dd = random.nextInt(2) + 1;

            if (vert) {
                dx = x;
                dy = findEdge(vert, dd, decks);
            } else {

                dx = findEdge(vert, dd, decks);
                dy = y;

            }

            if ((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1)) {
                return false;
            }

            if (bf.arOur[dx][dy].isFull()) {
                return false;
            } else {
                decks.add(bf.arOur[dx][dy]);
                rez = true;
            }

        }

        bf.ships.add(decks);
        refreshAll(bf);

        return rez;
    }

    private static void unSetShips(Bat_Field bf) {

        bf.ships.clear();
        bf.shipIsTaken = false;
        bf.takenShip.clear();


        refreshAll(bf);
    }

    public static void setAllShips(Bat_Field bf) {

        unSetShips(bf);

        while (!setShipRandom(4, bf)) ;


        while (!setShipRandom(3, bf)) ;
        while (!setShipRandom(3, bf)) ;

        while (!setShipRandom(2, bf)) ;
        while (!setShipRandom(2, bf)) ;
        while (!setShipRandom(2, bf)) ;

        while (!setShipRandom(1, bf)) ;
        while (!setShipRandom(1, bf)) ;
        while (!setShipRandom(1, bf)) ;
        while (!setShipRandom(1, bf)) ;

    }
    // Первоначальная установка караблей


    public static void setShieldOnCell(Bat_cell bt) {
        int dx, dy, x, y;

        x = bt.getX();
        y = bt.getY();

        dx = x + 1;
        dy = y;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            bt.bf.arOur[dx][dy].setShield(true);
        }

        dx = x - 1;
        dy = y;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            bt.bf.arOur[dx][dy].setShield(true);
        }

        dx = x;
        dy = y + 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            bt.bf.arOur[dx][dy].setShield(true);
        }

        dx = x;
        dy = y - 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            bt.bf.arOur[dx][dy].setShield(true);
        }

        dx = x + 1;
        dy = y - 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            bt.bf.arOur[dx][dy].setShield(true);
        }

        dx = x + 1;
        dy = y + 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            bt.bf.arOur[dx][dy].setShield(true);
        }

        dx = x - 1;
        dy = y + 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            bt.bf.arOur[dx][dy].setShield(true);
        }

        dx = x - 1;
        dy = y - 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            bt.bf.arOur[dx][dy].setShield(true);
        }

    }

    public static void refreshShields(Bat_Field bf) {

        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {

                bf.arOur[x][y].setShield(false);

            }
        }

        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {

                if (bf.arOur[x][y].isDeck())
                    setShieldOnCell(bf.arOur[x][y]);

            }
        }


    }

    public static void refreshShips(Bat_Field bf) {

        Iterator iterator = bf.ships.iterator();
        while (iterator.hasNext()) {
            Set ship = (Set) iterator.next();

            Iterator iteratorShip = ship.iterator();
            while (iteratorShip.hasNext()) {

                Bat_cell bt = (Bat_cell) iteratorShip.next();
                bt.setDeck(1);
                bt.ship.addAll(ship);

            }
        }
    }

    public static void refreshAll(Bat_Field bf) {

        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                bf.arOur[x][y].setDeck(0);
                bf.arOur[x][y].setShield(false);
                bf.arOur[x][y].ship.clear();
            }
        }

        refreshShips(bf);
        refreshShields(bf);

    }


    public static void unSetShip(Set decks, Bat_Field bf) {
        bf.ships.remove(decks);
        refreshAll(bf);
    }

    public static void setShip(Set decks, Bat_Field bf) {
        bf.ships.add(decks);
        refreshAll(bf);
    }

    //Вращение караблей
    public static boolean setShipOnCell(int d, boolean vert, Bat_cell bt) {

        boolean rez = false;
        int dx, dy;

        Set decks = new HashSet();
        int x = bt.getX();
        int y = bt.getY();

        //Однопалубник
        if (d == 1) {
            if (bt.bf.arOur[x][y].isFull()) {
                return false;
            } else {
                decks.add(bt.bf.arOur[x][y]);
                setShip(decks, bt.bf);
                return true;
            }
        }
        //Однопалубник

        if (vert) {

            for (int s = 0; s < d; s++) {

                for (int i = 0; i < d; i++) {

                    dx = x;
                    dy = y - s + i;

                    if ((dy > 10) | (dy < 1)) {
                        decks.clear();
                        rez = false;
                        break;
                    }

                    if (bt.bf.arOur[dx][dy].isFull()) {
                        decks.clear();
                        rez = false;
                        break;
                    } else {
                        decks.add(bt.bf.arOur[dx][dy]);
                        rez = true;
                    }

                }

                if (rez) {
                    setShip(decks, bt.bf);
                    return true;
                }
            }

        } else {
            for (int s = 0; s < d; s++) {

                for (int i = 0; i < d; i++) {

                    dx = x - s + i;
                    dy = y;

                    if ((dx > 10) | (dx < 1)) {
                        decks.clear();
                        rez = false;
                        break;
                    }

                    if (bt.bf.arOur[dx][dy].isFull()) {
                        decks.clear();
                        rez = false;
                        break;
                    } else {
                        decks.add(bt.bf.arOur[dx][dy]);
                        rez = true;
                    }

                }

                if (rez) {
                    setShip(decks, bt.bf);
                    return true;
                }
            }


        }
        return false;
    }

    public static void rotateShip(Bat_cell bt) {
        Set tempShip = new HashSet();
        boolean vert;

        Set tempDecks = new HashSet();
        boolean rez = false;


        tempShip.addAll(bt.ship);

        int i = tempShip.size();

        if (i <= 1) return;

        ArrayList<Bat_cell> arrList = new ArrayList<Bat_cell>();
        arrList.addAll(tempShip);
        tempDecks.addAll(bt.ship);

        unSetShip(bt.ship, bt.bf);

        int a1 = arrList.get(0).getX();
        int a2 = arrList.get(1).getX();
        vert = (a1 == a2);

        for (Bat_cell dbt : arrList) {

            rez = setShipOnCell(arrList.size(), !vert, dbt);

            if (rez) {
                return;
            }

        }
        //Вернуть корабль на место если не удалось повернуть его
        setShip(tempDecks, bt.bf);
        //Вернуть корабль на место если не удалось повернуть его
    }
    //перетаскивание караблей

    public static void moveShip(Bat_cell bt) {

        boolean vert;

        Set tempDecks = new HashSet();
        boolean rez = false;

        if (bt.bf.shipIsTaken) {
            tempDecks.addAll(bt.bf.takenShip);
        } else {
            tempDecks.addAll(bt.ship);
        }


        if (bt.bf.shipIsTaken & !bt.isFull()) {

            ArrayList<Bat_cell> arrList = new ArrayList<Bat_cell>();
            arrList.addAll(bt.bf.takenShip);


            int i = tempDecks.size();
            if (i > 1) {
                int a1 = arrList.get(0).getX();
                int a2 = arrList.get(1).getX();
                vert = (a1 == a2);
            } else vert = true;

            rez = setShipOnCell(i, vert, bt);

            if (rez) {
                bt.bf.shipIsTaken = false;
                bt.bf.takenShip.clear();
                return;
            }


        } else if (!bt.bf.shipIsTaken & bt.isDeck()) {

            unSetShip(bt.ship, bt.bf);

            bt.bf.shipIsTaken = true;
            bt.bf.takenShip.clear();
            bt.bf.takenShip.addAll(tempDecks);

            return;

        }


        //Вернуть корабль на место если не удалось поставить его
        bt.bf.shipIsTaken = false;
        bt.bf.takenShip.clear();
        setShip(tempDecks, bt.bf);

        //Вернуть корабль на место если не удалось поставить его
    }
    //перетаскивание караблей


}
