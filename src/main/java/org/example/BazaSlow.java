package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.example.Stale.DATA_PATH;

public class BazaSlow {

    private HashMap<String, String[]> listaSlow;

    private ArrayList<String> kategorie;


    public BazaSlow() {
        try {//po wykonaniu mam zapisane wszystkie kategorie w tablicy i wyrazy odpowiadajace kategoriom w hashmapie
            listaSlow=new HashMap<>();
            kategorie=new ArrayList<>();
            String filePath = Main.class.getClassLoader().getResource(DATA_PATH).getPath();

            BufferedReader czytnik = new BufferedReader(new FileReader(filePath));
            String linia;
            while ((linia = czytnik.readLine()) != null) {
                    String[] linia_wyrazow=linia.split(",");

                    String kategoria=linia_wyrazow[0];
                    kategorie.add(kategoria);

                    String wartosci[] = Arrays.copyOfRange(linia_wyrazow, 1, linia_wyrazow.length);
                    listaSlow.put(kategoria, wartosci);
                }
            } catch (IOException e) {
                System.out.println("Error: "+e);
            }
        }


        public String[] losowanie() {//losujemy kategorie potem dobieramy slowa tylko z danej kategorii i losujemy jedno
        Random rand =new Random();

        String kategoria=kategorie.get(rand.nextInt(kategorie.size()));
        String[] wartosci_kategorii=listaSlow.get(kategoria);
        String slowo=wartosci_kategorii[rand.nextInt(wartosci_kategorii.length)];

        return new String[] {kategoria.toUpperCase(), slowo.toUpperCase()};
        }

    }


