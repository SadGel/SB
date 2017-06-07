package com.sadgel;


import webServiceEx.WebSB;
import webServiceEx.WebSBPortType;


import javax.swing.*;
import java.net.*;


/**
 * Created by User15 on 30.05.2017.
 */
public class SB_web {



    public static String newGame(MainWindow bw) throws MalformedURLException{
        String rez="none";
        String username = "gel";
        String password = "258456";

        Authenticator myAuth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        };

        Authenticator.setDefault(myAuth);



        String pin = (String)JOptionPane.showInputDialog(
                bw,
                "Введите pin:",
                "Ввод pin-кода",
                JOptionPane.PLAIN_MESSAGE,null
                ,
                null,
                "0000");

        if (pin == null) return rez;



        WebSB webSB = new WebSB();
        WebSBPortType webSBSoap = webSB.getWebSBSoap();
        rez = webSBSoap.newGame(pin);

        //System.out.println(pin);




        return rez;

    }



}
