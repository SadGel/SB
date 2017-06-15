package com.sadgel;

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
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!bw.isOurTern()) {
                SB_battle.enemyTurn(bw);
                System.out.println("work");
            }
        }
    }
}
