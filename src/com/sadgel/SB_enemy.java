package com.sadgel;

import java.util.Random;
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
        while (bw.isGameBegin()) {
            //try {
            //    TimeUnit.SECONDS.sleep(5);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}

            if (!bw.isOurTern()) {
                System.out.println("work1");
                SB_battle.enemyTurn(bw);
                System.out.println("work2");
            }
        }
    }

    public static int[] AI_enemy(Bat_Field bf) {

        int[] rez = new int[2];
        Random random = new Random();

        rez[0] = random.nextInt(9)+1;
        rez[1] = random.nextInt(9)+1;
        System.out.println("x= " + rez[0]);
        System.out.println("y= " + rez[1]);
        System.out.println(bf.arOur[rez[0]][rez[1]].pressed);
        while (bf.arOur[rez[0]][rez[1]].pressed) {
            rez[0] = random.nextInt(9)+1;
            rez[1] = random.nextInt(9)+1;
        }

        return rez;
    }
}
