package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.example.Stale.DATA_PATH;

public class Narzedzia {

    public static Font stworzCzcionke(String zrodlo) {
        String sciezka = Main.class.getClassLoader().getResource(zrodlo).getPath();

        if (sciezka.contains("%20")) {
            sciezka=sciezka.replaceAll("%20", " ");
        }

        try {
            File plik_czcionka= new File(sciezka);
            Font czcionka=Font.createFont(Font.TRUETYPE_FONT,plik_czcionka);
            return czcionka;
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
        return null;
    }

    public static String ukryjSlowa(String slowo) {
        String ukryteSlowo="";
        for (int i=0;i<slowo.length();i++) {
            if (!(slowo.charAt(i)==' ')) {
                ukryteSlowo+="*";
            }
            else {
                ukryteSlowo+=" ";
            }
        }
        return ukryteSlowo;
    }

    public static JLabel wczytajObraz(String zrodlo) {
        BufferedImage obraz;
        try {
            InputStream input=Main.class.getClassLoader().getResourceAsStream(zrodlo);
            obraz= ImageIO.read(input);
            return new JLabel(new ImageIcon(obraz));
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
        return null;
    }

    public static void aktualizacja_obrazu(JLabel kontener_obrazu, String zrodlo) {
        BufferedImage obraz;
        try {
            InputStream input=Main.class.getClassLoader().getResourceAsStream(zrodlo);
            obraz= ImageIO.read(input);
            kontener_obrazu.setIcon(new ImageIcon(obraz));
        } catch (IOException e) {
            System.out.println("Error: "+e);
        }
    }

}
