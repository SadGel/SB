package com.sadgel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Гель on 15.06.2017.
 */
public class SB_enemy implements Runnable {
    MainWindow bw;
    Thread th;
    volatile boolean isStop;

    public SB_enemy(MainWindow bw) {
        this.bw = bw;
    }

    public void setStop() {
        isStop = true;
        if (th != null) {
            th.interrupt();
        }
    }


    @Override
    public void run() {

        th = Thread.currentThread();
        while (!isStop) {

            if (!bw.isOurTern()) {
                SB_battle.enemyTurn(bw);
            } else if (bw.compVsComp) {
                SB_battle.enemyTurn2(bw);
            }
        }

    }

    private static Bat_cell[] corectInjured(Bat_cell[] cor) {
        Bat_cell a, b, c;
        Bat_cell[] rez = new Bat_cell[2];
        if (cor.length != 3) return cor;
        else {
            a = cor[0];
            b = cor[1];
            c = cor[2];

            if ((a.getX() == b.getX()) | (a.getY() == b.getY())) {
                rez[0] = a;
                rez[1] = b;
                //rez[0] = c;
                return rez;
            }

            if ((a.getX() == c.getX()) | (a.getY() == c.getY())) {
                rez[0] = a;
                rez[1] = c;
                //rez[0] = b;
                return rez;
            }

            //if ((b.getX() == c.getX())|(b.getY() == c.getY())) {
            rez[0] = b;
            rez[1] = c;
            //rez[0] = a;
            return rez;
            //}

        }

    }

    private static Bat_cell[] getInjured(Bat_Field bf) {

        Bat_cell[] rez = new Bat_cell[0];
        boolean inj;
        Set BitDecks;

        Iterator iterator = bf.ships.iterator();
        while (iterator.hasNext()) {
            BitDecks = new HashSet();
            Set ship = (Set) iterator.next();
            inj = false;
            Bat_cell[] shipAr = (Bat_cell[]) ship.toArray(new Bat_cell[ship.size()]);


            boolean tempPressed = shipAr[0].pressed;
            for (Bat_cell elem : shipAr) {

                if (elem.pressed != tempPressed) {
                    inj = true;
                }

                if (elem.pressed) {
                    BitDecks.add(elem);
                }

            }

            if (inj) { //Есть раненый
                int x, y, dx, dy;
                Bat_cell[] BitDecksAr = (Bat_cell[]) BitDecks.toArray(new Bat_cell[BitDecks.size()]);
                Set CellForBit = new HashSet();

                if (BitDecksAr.length == 1) { // ранена одна палуба

                    x = BitDecksAr[0].getX();
                    y = BitDecksAr[0].getY();


                    dx = x;
                    dy = y - 1; // клетка 1

                    if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)) {
                        if (!bf.arOur[dx][dy].pressed) {
                            CellForBit.add(bf.arOur[dx][dy]);
                        }
                    }

                    dx = x + 1;
                    dy = y; // клетка 2

                    if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)) {
                        if (!bf.arOur[dx][dy].pressed) {
                            CellForBit.add(bf.arOur[dx][dy]);
                        }
                    }

                    dx = x;
                    dy = y + 1; // клетка 3

                    if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)) {
                        if (!bf.arOur[dx][dy].pressed) {
                            CellForBit.add(bf.arOur[dx][dy]);
                        }
                    }

                    dx = x - 1;
                    dy = y; // клетка 4

                    if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)) {
                        if (!bf.arOur[dx][dy].pressed) {
                            CellForBit.add(bf.arOur[dx][dy]);
                        }
                    }

                    Bat_cell[] CellForBitAr = (Bat_cell[]) CellForBit.toArray(new Bat_cell[CellForBit.size()]);

                    return CellForBitAr;


                } else { //Ранено больше одной палубы

                    int a1 = BitDecksAr[0].getX();
                    int a2 = BitDecksAr[1].getX();
                    boolean vert = (a1 == a2);

                    if (vert) { //Карабль вертикальный
                        dx = BitDecksAr[0].getX();
                        int dymax = 0;
                        int dymin = 11;

                        for (Bat_cell elem : BitDecksAr) {
                            int dyTemp = elem.getY();
                            if (dyTemp >= dymax) dymax = dyTemp;
                            if (dyTemp <= dymin) dymin = dyTemp;
                        }

                        dy = dymin - 1; // клетка 1

                        if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)) {
                            if (!bf.arOur[dx][dy].pressed) {
                                CellForBit.add(bf.arOur[dx][dy]);
                            }
                        }

                        dy = dymax + 1; // клетка 2

                        if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)) {
                            if (!bf.arOur[dx][dy].pressed) {
                                CellForBit.add(bf.arOur[dx][dy]);
                            }
                        }

                        Bat_cell[] CellForBitAr = (Bat_cell[]) CellForBit.toArray(new Bat_cell[CellForBit.size()]);

                        return CellForBitAr;

                    } else { //Карабль горизонтальный
                        dy = BitDecksAr[0].getY();
                        int dxmax = 0;
                        int dxmin = 11;

                        for (Bat_cell elem : BitDecksAr) {
                            int dxTemp = elem.getX();
                            if (dxTemp >= dxmax) dxmax = dxTemp;
                            if (dxTemp <= dxmin) dxmin = dxTemp;
                        }

                        dx = dxmin - 1; // клетка 1

                        if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)) {
                            if (!bf.arOur[dx][dy].pressed) {
                                CellForBit.add(bf.arOur[dx][dy]);
                            }
                        }

                        dx = dxmax + 1; // клетка 2

                        if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)) {
                            if (!bf.arOur[dx][dy].pressed) {
                                CellForBit.add(bf.arOur[dx][dy]);
                            }
                        }

                        Bat_cell[] CellForBitAr = (Bat_cell[]) CellForBit.toArray(new Bat_cell[CellForBit.size()]);

                        return CellForBitAr;

                    }


                }


            }


        }


        return rez;

    }

    private static int getSummARound(int[][] raiting, int x, int y) {
        int rez = 0, dx, dy;

        dx = x + 1;
        dy = y;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            rez = rez + raiting[dx][dy]; //1
        }

        dx = x - 1;
        dy = y;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            rez = rez + raiting[dx][dy];//2
        }

        dx = x;
        dy = y + 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            rez = rez + raiting[dx][dy];//3
        }

        dx = x;
        dy = y - 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            rez = rez + raiting[dx][dy];//4
        }

        dx = x + 1;
        dy = y - 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            rez = rez + raiting[dx][dy];//5
        }

        dx = x + 1;
        dy = y + 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            rez = rez + raiting[dx][dy];//6
        }

        dx = x - 1;
        dy = y + 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            rez = rez + raiting[dx][dy];//7
        }

        dx = x - 1;
        dy = y - 1;

        if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
            rez = rez + raiting[dx][dy];//8
        }



        return rez;
    }

    public static Bat_cell[] getTactic(Bat_Field bf, int d) {
        Set possibilShips = new HashSet();
        Set possibilShipG;
        Set possibilShipV;
        int[][] raiting = new int[11][11];


        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {

                //добавляем возможные горизонтальные корабли
                boolean flag = true;
                possibilShipG = new HashSet();
                for (int i = 0; i < d; i++) {
                    if ((x + i <= 10) & (y <= 10) & (y >= 1) & (x + i >= 1)) {
                        if (bf.arOur[x + i][y].pressed) {
                            flag = false;
                            break;
                        } else {
                            possibilShipG.add(bf.arOur[x + i][y]);
                        }
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    possibilShips.add(possibilShipG);
                }
                //добавляем возможные горизонтальные корабли


                //добавляем возможные вертикальные корабли
                flag = true;
                possibilShipV = new HashSet();
                for (int i = 0; i < d; i++) {
                    if ((x <= 10) & (y + i <= 10) & (y + i >= 1) & (x >= 1)) {
                        if (bf.arOur[x][y + i].pressed) {
                            flag = false;
                            break;
                        } else {
                            possibilShipV.add(bf.arOur[x][y + i]);
                        }
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    possibilShips.add(possibilShipV);
                }
                //добавляем возможные вертикальные корабли
            }
        }

        //Сформирован список возможных кораблей

        //формируем рейтинг нахождения кораблей
        System.out.println("Вариантов установки: " + possibilShips.size());
        int kmax = 0;
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                int k = 0;
                Iterator iterator = possibilShips.iterator();
                while (iterator.hasNext()) {
                    Set ship = (Set) iterator.next();
                    if (ship.contains(bf.arOur[x][y])) k++;
                }
                if (k >= kmax) kmax = k;
                raiting[x][y] = k;
            }
        }
        //формируем рейтинг нахождения кораблей


        //формируем список клеток с максимальным рейтингом

        Set CellForBit = new HashSet();
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {

                if (raiting[x][y] == kmax) {
                    CellForBit.add(bf.arOur[x][y]);
                }

            }
        }

        Bat_cell[] CellForBitAr = (Bat_cell[]) CellForBit.toArray(new Bat_cell[CellForBit.size()]);

        //формируем список клеток с максимальным рейтингом


        for (int y = 1; y <= 10; y++) {
            for (int x = 1; x <= 10; x++) {
                System.out.print(raiting[x][y] + " ");
            }
            System.out.println();
        }


        return CellForBitAr;
    }

    public static Bat_cell[] getTactic3(Bat_Field bf, int d) {
        Set possibilShips = new HashSet();
        Set possibilShipG;
        Set possibilShipV;
        int[][] raiting = new int[11][11];
        int[][] raiting2 = new int[11][11];


        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {

                //добавляем возможные горизонтальные корабли
                boolean flag = true;
                possibilShipG = new HashSet();
                for (int i = 0; i < d; i++) {
                    if ((x + i <= 10) & (y <= 10) & (y >= 1) & (x + i >= 1)) {
                        if (bf.arOur[x + i][y].pressed) {
                            flag = false;
                            break;
                        } else {
                            possibilShipG.add(bf.arOur[x + i][y]);
                        }
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    possibilShips.add(possibilShipG);
                }
                //добавляем возможные горизонтальные корабли


                //добавляем возможные вертикальные корабли
                flag = true;
                possibilShipV = new HashSet();
                for (int i = 0; i < d; i++) {
                    if ((x <= 10) & (y + i <= 10) & (y + i >= 1) & (x >= 1)) {
                        if (bf.arOur[x][y + i].pressed) {
                            flag = false;
                            break;
                        } else {
                            possibilShipV.add(bf.arOur[x][y + i]);
                        }
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    possibilShips.add(possibilShipV);
                }
                //добавляем возможные вертикальные корабли
            }
        }

        //Сформирован список возможных кораблей

        //формируем рейтинг нахождения кораблей
        System.out.println("Вариантов установки: " + possibilShips.size());
        int kmax = 0;
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                int k = 0;
                Iterator iterator = possibilShips.iterator();
                while (iterator.hasNext()) {
                    Set ship = (Set) iterator.next();
                    if (ship.contains(bf.arOur[x][y])) k++;
                }
                if (k >= kmax) kmax = k;
                raiting[x][y] = k;
            }
        }
        //формируем рейтинг нахождения кораблей

        //Формируем массив с суммами окружающих рейтингов
        int kmin = 100;
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {

                if (raiting[x][y] == kmax) {
                    raiting2[x][y] = getSummARound(raiting,x,y);
                    if (raiting2[x][y] < kmin) kmin = raiting2[x][y];
                }
                else {
                    raiting2[x][y] = -1;
                }

            }
        }
        //Формируем массив с суммами окружающих рейтингов


        //формируем список клеток с минимальным окружающим рейтингом

        Set CellForBit = new HashSet();
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {

                if (raiting2[x][y] == kmin) {
                    CellForBit.add(bf.arOur[x][y]);
                }

            }
        }

        Bat_cell[] CellForBitAr = (Bat_cell[]) CellForBit.toArray(new Bat_cell[CellForBit.size()]);

        //формируем список клеток с минимальным окружающим рейтингом


        for (int y = 1; y <= 10; y++) {
            for (int x = 1; x <= 10; x++) {
                System.out.print(raiting2[x][y] + " ");
            }
            System.out.println();
        }


        return CellForBitAr;
    }


    public static Bat_cell[] getTactic2(Bat_Field bf, int d) {
        Set possibilShips = new HashSet();
        Set possibilShipG;
        Set possibilShipV;
        int[][] raiting = new int[11][11];


        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {

                //добавляем возможные горизонтальные корабли
                boolean flag = true;
                possibilShipG = new HashSet();
                for (int i = 0; i < d; i++) {
                    if ((x + i <= 10) & (y <= 10) & (y >= 1) & (x + i >= 1)) {
                        if (bf.arOur[x + i][y].pressed) {
                            flag = false;
                            break;
                        } else {
                            possibilShipG.add(bf.arOur[x + i][y]);
                        }
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    possibilShips.add(possibilShipG);
                }
                //добавляем возможные горизонтальные корабли


                //добавляем возможные вертикальные корабли
                flag = true;
                possibilShipV = new HashSet();
                for (int i = 0; i < d; i++) {
                    if ((x <= 10) & (y + i <= 10) & (y + i >= 1) & (x >= 1)) {
                        if (bf.arOur[x][y + i].pressed) {
                            flag = false;
                            break;
                        } else {
                            possibilShipV.add(bf.arOur[x][y + i]);
                        }
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    possibilShips.add(possibilShipV);
                }
                //добавляем возможные вертикальные корабли
            }
        }

        //Сформирован список возможных кораблей

        //формируем рейтинг нахождения кораблей
        System.out.println("Вариантов установки: " + possibilShips.size());
        int kmax = 0;
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                int k = 0;
                Iterator iterator = possibilShips.iterator();
                while (iterator.hasNext()) {
                    Set ship = (Set) iterator.next();
                    if (ship.contains(bf.arOur[x][y])) k++;
                }
                if (k >= kmax) kmax = k;
                raiting[x][y] = k;
            }
        }
        //формируем рейтинг нахождения кораблей


        //формируем список клеток с максимальным рейтингом

        Set CellForBit = new HashSet();
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {

                if (raiting[x][y] != 0) { //Бить в возможную а не максимальновозможную точку
                    CellForBit.add(bf.arOur[x][y]);
                }

            }
        }

        Bat_cell[] CellForBitAr = (Bat_cell[]) CellForBit.toArray(new Bat_cell[CellForBit.size()]);

        //формируем список клеток с максимальным рейтингом


        for (int y = 1; y <= 10; y++) {
            for (int x = 1; x <= 10; x++) {
                System.out.print(raiting[x][y] + " ");
            }
            System.out.println();
        }


        return CellForBitAr;
    }

    private static int getNumberOfLiveShips(Bat_Field bf, int d) {
        int rez = 0;

        Iterator iterator = bf.ships.iterator();
        while (iterator.hasNext()) {
            Set ship = (Set) iterator.next();

            if (ship.size() != d) continue;

            boolean live = false;

            Bat_cell[] shipAr = (Bat_cell[]) ship.toArray(new Bat_cell[ship.size()]);

            for (Bat_cell elem : shipAr) {

                if (elem.pressed == false) {
                    live = true;
                    //break;
                }
            }

            if (live) rez++;

        }


        return rez;
    }

    private static Bat_cell[] getOneDeckers(Bat_Field bf) {
        int[][] raiting = new int[11][11];
        int kmax = 0;

        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                int dx, dy, k = 0;

                if (bf.arOur[x][y].pressed) {

                    raiting[x][y] = -1;
                    continue;

                } else k++;

                dx = x + 1;
                dy = y;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bf.arOur[dx][dy].pressed) k++;
                }

                dx = x - 1;
                dy = y;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bf.arOur[dx][dy].pressed) k++;
                }

                dx = x;
                dy = y + 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bf.arOur[dx][dy].pressed) k++;
                }

                dx = x;
                dy = y - 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bf.arOur[dx][dy].pressed) k++;
                }

                dx = x + 1;
                dy = y - 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bf.arOur[dx][dy].pressed) k++;
                }

                dx = x + 1;
                dy = y + 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bf.arOur[dx][dy].pressed) k++;
                }

                dx = x - 1;
                dy = y + 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bf.arOur[dx][dy].pressed) k++;
                }

                dx = x - 1;
                dy = y - 1;

                if (!((dx > 10) | (dy > 10) | (dy < 1) | (dx < 1))) {
                    if (!bf.arOur[dx][dy].pressed) k++;
                }

                if (k >= kmax) kmax = k;
                raiting[x][y] = k;

            }
        }

        //формируем список клеток с максимальным рейтингом

        Set CellForBit = new HashSet();
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {

                if (raiting[x][y] == kmax) {
                    CellForBit.add(bf.arOur[x][y]);
                }

            }
        }

        Bat_cell[] CellForBitAr = (Bat_cell[]) CellForBit.toArray(new Bat_cell[CellForBit.size()]);

        //формируем список клеток с максимальным рейтингом

        return CellForBitAr;
    }


    private static Bat_cell returnCellForBit(Bat_cell[] CellsForBitAr) {
        Bat_cell rez;

        Random random = new Random();

        rez = CellsForBitAr[random.nextInt(CellsForBitAr.length)];

        return rez;

    }

    public static int[] AI_enemy(Bat_Field bf) {

        Bat_cell[] CellsForBitAr;
        Bat_cell CellForBit;
        int[] rez = new int[2];

        // поиск и удар по раненым
        CellsForBitAr = getInjured(bf);


        if (CellsForBitAr.length > 0) {

            CellsForBitAr = corectInjured(CellsForBitAr);
            CellForBit = returnCellForBit(CellsForBitAr);
            rez[0] = CellForBit.getX();
            rez[1] = CellForBit.getY();
            return rez;

        }
        // поиск и удар по раненым


        //тактика поиска 4х 3х и 2х палубников

        int d = SB_battle.getMaxDeckLiveShip(bf);
        if (d > 1) {

            System.out.println("Ищем " + d + "х палубники");

            CellsForBitAr = getTactic3(bf, d);

            if (CellsForBitAr.length > 0) {

                CellForBit = returnCellForBit(CellsForBitAr);
                rez[0] = CellForBit.getX();
                rez[1] = CellForBit.getY();
                return rez;
            }
        }


        //тактика поиска 4х 3х и 2х палубников


        //Тактика поиска однопалубников
        //System.out.println(getNumberOfLiveShips(bf,1));
        if (getNumberOfLiveShips(bf,1) > 1) { //Если однопалубников больше 1

            CellsForBitAr = getOneDeckers(bf);


            if (CellsForBitAr.length > 0) {

                CellForBit = returnCellForBit(CellsForBitAr);
                rez[0] = CellForBit.getX();
                rez[1] = CellForBit.getY();
                return rez;
            }
        }

        //остался 1 однопалубник

        System.out.println("Ищем 1о палубники");

        Random random = new Random();


        rez[0] = random.nextInt(10) + 1;
        rez[1] = random.nextInt(10) + 1;

        while (bf.arOur[rez[0]][rez[1]].pressed) {
            rez[0] = random.nextInt(10) + 1;
            rez[1] = random.nextInt(10) + 1;
        }

        //остался 1 однопалубник

        return rez;
    }

    public static int[] AI_enemy2(Bat_Field bf) {

        Bat_cell[] CellsForBitAr;
        Bat_cell CellForBit;
        int[] rez = new int[2];

        // поиск и удар по раненым
        CellsForBitAr = getInjured(bf);


        if (CellsForBitAr.length > 0) {
            //if (CellsForBitAr.length < 0) { //Убрать добивание раненых
            CellsForBitAr = corectInjured(CellsForBitAr);
            CellForBit = returnCellForBit(CellsForBitAr);
            rez[0] = CellForBit.getX();
            rez[1] = CellForBit.getY();
            return rez;
        }
        // поиск и удар по раненым


        //тактика поиска 4х 3х и 2х палубников

        int d = SB_battle.getMaxDeckLiveShip(bf);
        //d=1; // отключить поиск возможных установок
        if (d > 1) {
            //if (d == 4) d = 5; //Выставить начальный поиск на нужное количество палуб
            System.out.println("Ищем " + d + "х палубники");

            CellsForBitAr = getTactic(bf, d);

            if (CellsForBitAr.length > 0) {

                CellForBit = returnCellForBit(CellsForBitAr);
                rez[0] = CellForBit.getX();
                rez[1] = CellForBit.getY();
                return rez;
            }
        }


        //тактика поиска 4х 3х и 2х палубников

        //Тактика поиска однопалубников
        if (getNumberOfLiveShips(bf,1) > 1) { //Если однопалубников больше 1

            CellsForBitAr = getOneDeckers(bf);


            if (CellsForBitAr.length > 0) {

                CellForBit = returnCellForBit(CellsForBitAr);
                rez[0] = CellForBit.getX();
                rez[1] = CellForBit.getY();
                return rez;
            }

       }


        //Тактика поиска однопалубников


        //остался 1 однопалубник

        System.out.println("Ищем 1о палубники");

        Random random = new Random();


        rez[0] = random.nextInt(10) + 1;
        rez[1] = random.nextInt(10) + 1;

        while (bf.arOur[rez[0]][rez[1]].pressed) {
            rez[0] = random.nextInt(10) + 1;
            rez[1] = random.nextInt(10) + 1;
        }

        //остался 1 однопалубник

        return rez;
    }

}
