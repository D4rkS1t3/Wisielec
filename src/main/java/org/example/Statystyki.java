package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Statystyki extends JFrame implements ActionListener {

    private Font czcionka;
    private List<List<Object>> ranking;
    private MenadzerOkien menadzerOkien;

    public Statystyki(MenadzerOkien menadzerOkien) {
        // Ustawienia okna
        super("Statystyki");
        setSize(Stale.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Stale.BACKGROUND_COLOR);

        wczytajStatystyki();
        stworzStatystyki();

        this.menadzerOkien=menadzerOkien;
    }

    public void stworzStatystyki() {
        czcionka = new Font("Arial", Font.PLAIN, 12);

        JPanel panelGlowny = new JPanel();
        panelGlowny.setBackground(Stale.BACKGROUND_COLOR);
        panelGlowny.setLayout(new BorderLayout());
        setContentPane(panelGlowny);

        JPanel panelNaglowka = new JPanel();
        panelNaglowka.setBackground(Stale.PRIMARY_COLOR);

        JLabel statystyka = new JLabel("STATYSTYKI");
        statystyka.setForeground(Color.WHITE);
        statystyka.setHorizontalAlignment(SwingConstants.CENTER);
        statystyka.setFont(czcionka.deriveFont(70f));
        panelNaglowka.add(statystyka);

        JPanel panelStatystyk = new JPanel();
        panelStatystyk.setBackground(Stale.PRIMARY_COLOR);
        panelStatystyk.setLayout(new BoxLayout(panelStatystyk, BoxLayout.Y_AXIS)); // Zmiana na BoxLayout

        // Tworzenie etykiety dla statystyk - jedna pętla na pola [i][0] i [i][1]
        for (int i = 0; i < 20 && i < ranking.size(); i++) {
            List<Object> gracz = ranking.get(i);
            JLabel etykietaStatystyk = new JLabel((i + 1) + ". " + gracz.get(0) + ", Punkty: " + gracz.get(1));
            etykietaStatystyk.setForeground(Color.WHITE);
            etykietaStatystyk.setFont(czcionka.deriveFont(25f));
            panelStatystyk.add(etykietaStatystyk);
        }

        JPanel panelPrzyciskow = new JPanel();
        panelPrzyciskow.setBackground(Stale.BACKGROUND_COLOR);
        panelPrzyciskow.setLayout(new GridLayout(1, 2));
        panelPrzyciskow.setBounds(0, 660,
                Stale.FRAME_SIZE.width,100);

        JButton przyciskPowrotuG = new JButton("Powrót do gry");
        przyciskPowrotuG.setFont(czcionka.deriveFont(22f));
        przyciskPowrotuG.setForeground(Color.WHITE);
        przyciskPowrotuG.setBackground(Stale.SECONDARY_COLOR);
        przyciskPowrotuG.addActionListener(this);

        JButton przyciskPowrotuM = new JButton("Powrót do menu");
        przyciskPowrotuM.setFont(czcionka.deriveFont(22f));
        przyciskPowrotuM.setForeground(Color.WHITE);
        przyciskPowrotuM.setBackground(Stale.SECONDARY_COLOR);
        przyciskPowrotuM.addActionListener(this);

        panelPrzyciskow.add(przyciskPowrotuG);
        panelPrzyciskow.add(przyciskPowrotuM);

        panelPrzyciskow.setPreferredSize(new Dimension(panelPrzyciskow.getPreferredSize().width, 87));

        panelGlowny.add(panelNaglowka, BorderLayout.NORTH);
        panelGlowny.add(panelStatystyk, BorderLayout.CENTER);
        panelGlowny.add(panelPrzyciskow, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String komenda = e.getActionCommand();
        if (komenda.equals("Powrót do gry")) {
            menadzerOkien.usunStatystykiITworzWprowadzenieNazwy();
        } else if (komenda.equals("Powrót do menu")) {
            menadzerOkien.usunStatystykiITworzMenu();
        }
    }

    public void wczytajStatystyki() {
        try {
            ranking = new ArrayList<>();
            String filePath = Stale.STATISTIC_PATH;
            BufferedReader czytnik = new BufferedReader(new FileReader(filePath));
            String linia;
            while ((linia = czytnik.readLine()) != null) {
                String[] linia_wyrazow = linia.split(",");
                if (linia_wyrazow.length >= 2) {
                    List<Object> gracz = new ArrayList<>();
                    gracz.add(linia_wyrazow[0]);
                    gracz.add(linia_wyrazow[1]);
                    ranking.add(gracz);
                }
            }
            czytnik.close();
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku statystyki.txt: " + e.getMessage());
        }
    }

}
