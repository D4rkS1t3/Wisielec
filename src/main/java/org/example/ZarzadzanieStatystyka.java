package org.example;

import java.io.*;
import java.util.*;

import static org.example.Stale.STATISTIC_PATH;

public class ZarzadzanieStatystyka {
    private LinkedList<String> nazwyGraczy;
    private LinkedList<Integer> punkty;


    public ZarzadzanieStatystyka() {
        try {
            nazwyGraczy = new LinkedList<>();
            punkty = new LinkedList<>();

            String filePath = Stale.STATISTIC_PATH;
            BufferedReader czytnik = new BufferedReader(new FileReader(filePath));
            String linia;

            while ((linia = czytnik.readLine()) != null) {
                String[] linia_wyrazow = linia.split(",");
                if (linia_wyrazow.length == 2) { // Sprawdzamy, czy linia zawiera dwie wartości oddzielone przecinkiem
                    String nazwaGracza = linia_wyrazow[0].trim(); // Pobieramy nazwę gracza, usuwając ewentualne białe znaki na początku i końcu
                    int punkt = Integer.parseInt(linia_wyrazow[1].trim()); // Pobieramy punkt jako liczbę całkowitą
                    nazwyGraczy.add(nazwaGracza);
                    punkty.add(punkt);
                } else {
                    System.out.println("Niepoprawny format linii w pliku statystyki.txt: " + linia);
                }
            }
            czytnik.close();
        } catch (FileNotFoundException e) {
            System.out.println("Błąd: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku statystyki.txt: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Błąd przetwarzania liczby w pliku statystyki.txt: " + e.getMessage());
        }
    }


    public boolean graczMiesciSieWRankingu(int punktyGracza) {
        if (nazwyGraczy.size()<20) {
            return true;
        }
        for (int punkt : punkty) {
            if (punktyGracza > punkt) {
                return true;
            }
        }
        return false;
    }



        public int miejsceGracza(int punktyGracza) {
        int k=0;
        if (nazwyGraczy.size()<1) {
            return k;
        }
        for (int punkt:punkty) {
            if (punktyGracza>punkt) {
                return k;
            }
            k++;
        }
        return k;
    }

    public int znajdzGracza(String nazwaGracza) {
        int indeks = 0;
        for (String nazwa : nazwyGraczy) {
            if (nazwa.equalsIgnoreCase(nazwaGracza)) {
                return indeks;
            }
            indeks++;
        }
        return -1;
    }

    public void aktualizujPlikStatystyki(String nazwaGracza, int punktyGracza) {
        int miejsce = miejsceGracza(punktyGracza);
        nazwyGraczy.add(miejsce, nazwaGracza);
        punkty.add(miejsce, punktyGracza);
        if (nazwyGraczy.size() > 20) {
            nazwyGraczy.removeLast();
            punkty.removeLast();
        }

        // Zapis do pliku
        String filePath = STATISTIC_PATH;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < nazwyGraczy.size(); i++) {
                String nazwa = nazwyGraczy.get(i);
                int punkty = this.punkty.get(i);
                writer.write(nazwa + "," + punkty);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Błąd przy zapisie do pliku statystyki.txt: " + e);
        }

    }


    private void usunNajnizszaLiczbePunktow(RandomAccessFile raf) throws IOException {
        int liczbaGraczy = 0;
        int indeksNajnizszejLiczby = -1;
        int najnizszaLiczbaPunktow = Integer.MAX_VALUE;

        // Szukamy linii z najniższą liczbą punktów
        raf.seek(0);
        String line;
        while ((line = raf.readLine()) != null) {
            String[] linia_wyrazow = line.split(",");
            int punkty = Integer.parseInt(linia_wyrazow[1].trim());
            if (punkty < najnizszaLiczbaPunktow) {
                najnizszaLiczbaPunktow = punkty;
                indeksNajnizszejLiczby = liczbaGraczy;
            }
            liczbaGraczy++;
        }

        // Jeśli liczba graczy przekracza 20, usuwamy gracza z najniższą liczbą punktów
        if (liczbaGraczy >= 20 && indeksNajnizszejLiczby != -1) {
            List<String> lines = new ArrayList<>();
            raf.seek(0);
            int indeks = 0;
            while ((line = raf.readLine()) != null) {
                if (indeks != indeksNajnizszejLiczby) {
                    lines.add(line);
                }
                indeks++;
            }

            // Zapisujemy nową zawartość pliku bez gracza z najniższą liczbą punktów
            raf.setLength(0); // Czyścimy plik
            for (String newLine : lines) {
                raf.writeBytes(newLine + System.lineSeparator());
            }
        }
    }


}
