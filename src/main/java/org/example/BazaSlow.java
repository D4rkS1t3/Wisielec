package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class BazaSlow {

    private HashMap<String, String[]> listaSlow;
    private ArrayList<String> kategorie;

    public BazaSlow() {
        try {
            listaSlow = new HashMap<>();
            kategorie = new ArrayList<>();

            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(Stale.DATA_PATH);
            if (inputStream != null) {
                try (BufferedReader czytnik = new BufferedReader(new InputStreamReader(inputStream))) {
                    String linia;
                    while ((linia = czytnik.readLine()) != null) {
                        String[] linia_wyrazow = linia.split(",");

                        String kategoria = linia_wyrazow[0];
                        kategorie.add(kategoria);

                        String[] wartosci = Arrays.copyOfRange(linia_wyrazow, 1, linia_wyrazow.length);
                        listaSlow.put(kategoria, wartosci);
                    }
                }
            } else {
                System.out.println("Nie można wczytać pliku data.txt");
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku data.txt: " + e.getMessage());
        }
    }

    public String[] losowanie() {
        Random rand = new Random();

        String kategoria = kategorie.get(rand.nextInt(kategorie.size()));
        String[] wartosci_kategorii = listaSlow.get(kategoria);
        String slowo = wartosci_kategorii[rand.nextInt(wartosci_kategorii.length)];

        return new String[]{kategoria.toUpperCase(), slowo.toUpperCase()};
    }
}
