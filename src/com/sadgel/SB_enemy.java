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
    public SB_enemy(MainWindow bw) {
        this.bw = bw;
        {

       }
    }

    @Override
    public void run() {


       // System.out.println(bw.isGameBegin());
        //while (bw.isGameBegin()) {
        //
        while (bw.isGameBegin()) {
        //System.out.println(bw.isGameBegin());
            //try {TimeUnit.SECONDS.sleep(1);
             //} catch (InterruptedException e) {
             //   e.printStackTrace();
           // }

            if (!bw.isOurTern()) {
                //System.out.println("work1");
                SB_battle.enemyTurn(bw);
                //System.out.println("work2");
            }
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
            Bat_cell[] shipAr =  (Bat_cell[]) ship.toArray(new Bat_cell[ship.size()]);
            boolean tempPressed = shipAr[0].pressed;
            for (Bat_cell elem :shipAr) {

                if (elem.pressed != tempPressed) {
                    inj = true;
                }

                if (elem.pressed) {
                    BitDecks.add(elem);
                }

            }

            if (inj) { //Есть раненый
                int x,y,dx,dy;
                Bat_cell[] BitDecksAr =  (Bat_cell[]) BitDecks.toArray(new Bat_cell[BitDecks.size()]);
                System.out.println(BitDecksAr.length);
                Set CellForBit = new HashSet();

                if (BitDecksAr.length == 1) { // ранена одна палуба

                    x = BitDecksAr[0].getX();
                    y = BitDecksAr[0].getY();


                    dx = x;dy=y-1; // клетка 1

                    if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)){
                        if (!bf.arOur[dx][dy].pressed) {
                            CellForBit.add(bf.arOur[dx][dy]);
                        }
                    }

                    dx = x+1;dy=y; // клетка 2

                    if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)){
                        if (!bf.arOur[dx][dy].pressed) {
                            CellForBit.add(bf.arOur[dx][dy]);
                        }
                    }

                    dx = x;dy=y+1; // клетка 3

                    if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)){
                        if (!bf.arOur[dx][dy].pressed) {
                            CellForBit.add(bf.arOur[dx][dy]);
                        }
                    }

                    dx = x-1;dy=y; // клетка 4

                    if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)){
                        if (!bf.arOur[dx][dy].pressed) {
                            CellForBit.add(bf.arOur[dx][dy]);
                        }
                    }

                    Bat_cell[] CellForBitAr =  (Bat_cell[]) CellForBit.toArray(new Bat_cell[CellForBit.size()]);

                    return CellForBitAr;


                }else { //Ранено больше одной палубы

                    int a1 = BitDecksAr[0].getX();
                    int a2 = BitDecksAr[1].getX();
                    boolean vert = (a1 == a2);

                    if (vert) { //Карабль вертикальный
                        dx = BitDecksAr[0].getX();
                        int dymax = 0;
                        int dymin = 11;

                        for (Bat_cell elem:BitDecksAr) {
                           int dyTemp = elem.getY();
                           if (dyTemp >= dymax) dymax = dyTemp;
                           if (dyTemp <= dymin) dymin = dyTemp;
                        }

                        dy=dymin-1; // клетка 1

                        if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)){
                            if (!bf.arOur[dx][dy].pressed) {
                                CellForBit.add(bf.arOur[dx][dy]);
                            }
                        }

                        dy=dymax+1; // клетка 2

                        if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)){
                            if (!bf.arOur[dx][dy].pressed) {
                                CellForBit.add(bf.arOur[dx][dy]);
                            }
                        }

                        Bat_cell[] CellForBitAr =  (Bat_cell[]) CellForBit.toArray(new Bat_cell[CellForBit.size()]);

                        return CellForBitAr;

                    }else{ //Карабль горизонтальный
                        dy = BitDecksAr[0].getY();
                        int dxmax = 0;
                        int dxmin = 11;

                        for (Bat_cell elem:BitDecksAr) {
                            int dxTemp = elem.getX();
                            if (dxTemp >= dxmax) dxmax = dxTemp;
                            if (dxTemp <= dxmin) dxmin = dxTemp;
                        }

                        dx=dxmin-1; // клетка 1

                        if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)){
                            if (!bf.arOur[dx][dy].pressed) {
                                CellForBit.add(bf.arOur[dx][dy]);
                            }
                        }

                        dx=dxmax+1; // клетка 2

                        if ((dx <= 10) & (dy <= 10) & (dy >= 1) & (dx >= 1)){
                            if (!bf.arOur[dx][dy].pressed) {
                                CellForBit.add(bf.arOur[dx][dy]);
                            }
                        }

                        Bat_cell[] CellForBitAr =  (Bat_cell[]) CellForBit.toArray(new Bat_cell[CellForBit.size()]);

                        return CellForBitAr;

                    }



                }


            }


        }


        return rez;

    }

    private static  Bat_cell returnCellForBit(Bat_cell[] CellsForBitAr) {
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

            CellForBit = returnCellForBit(CellsForBitAr);
            rez[0] = CellForBit.getX();
            rez[1] = CellForBit.getY();
            return rez;
        }
        // поиск и удар по раненым


        Random random = new Random();



        rez[0] = random.nextInt(10)+1;
        rez[1] = random.nextInt(10)+1;
        //System.out.println("x= " + rez[0]);
        //System.out.println("y= " + rez[1]);
        //System.out.println(bf.arOur[rez[0]][rez[1]].pressed);
        while (bf.arOur[rez[0]][rez[1]].pressed) {
            rez[0] = random.nextInt(10)+1;
            rez[1] = random.nextInt(10)+1;
        }

        return rez;
    }
}
