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

    private static HashSet isInjured(Bat_Field bf) {
        HashSet rez = new HashSet();
        boolean inj = false;
        //HashSet[] ships = {};

        //Iterator<Set> iterator = new bf.ships.iterator();
        System.out.println(bf.ships.size());

        Iterator iterator = bf.ships.iterator();
        while (iterator.hasNext()) {
            Set ship = (Set) iterator.next();
            inj = false;
            Bat_cell[] shipAr =  (Bat_cell[]) ship.toArray(new Bat_cell[ship.size()]);
            boolean tempPressed = shipAr[0].pressed;

            for (Bat_cell elem :shipAr) {

                if (elem.pressed != tempPressed) {
                    inj = true;
                }

            }

            if (inj) {
                System.out.println(shipAr.length);
            }

            //System.out.println(shipAr.length);

            //Iterator iteratorShip = ship.iterator();
            //while (iteratorShip.hasNext()) {

            //    Bat_cell bt = (Bat_cell) iteratorShip.next();


            //}
        }


        return rez;

    }


    public static int[] AI_enemy(Bat_Field bf) {

        isInjured(bf);

        int[] rez = new int[2];
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
