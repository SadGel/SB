package com.sadgel;

import javax.swing.*;
import java.util.Random;

/**
 * Created by Гель on 21.02.2017.
 */
public class MainWindow extends JFrame {

    public Bat_Field bf1;
    public Bat_Field bf2;
    public JButton [] menu_but;
    public JLabel tablo;
    public Thread t1;
    public int score1=0,score2=0;
    volatile private boolean isGameBegin = false;
    volatile private boolean ourTern;
    volatile public boolean compVsComp;

    public void setOurTern(boolean ourTern) {

        this.ourTern = ourTern;
        if (compVsComp){

            tablo.setText(score1+" : "+score2);

        }else {
            if (ourTern) {
                tablo.setText("ВАШ ХОД");
            } else {
                tablo.setText("ХОД СОПРЕНИКА");
            }
        }




    }

     public boolean isOurTern() {
        return ourTern;
    }

    public boolean isGameBegin() {
        return isGameBegin;
    }

    public void setGameBegin(boolean gameBegin) {
        isGameBegin = gameBegin;
        if (gameBegin==false&compVsComp) {
            //t1.stop();
            isGameBegin = true;
            Set_ships.setAllShips(this.bf1);
            Set_ships.setAllShips(this.bf2);
            SB_enemy.startGameAgain(this);
            //SB_menu.startGame();
            //t1.start();
        }
        if (compVsComp) {
            Random random = new Random();
            setOurTern(random.nextBoolean());
        }else setOurTern(false);

    }





    public MainWindow() {

        super("Морской бой 2.4");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);

        SB_menu.set_butt(this, 20, 300);

    }


}
