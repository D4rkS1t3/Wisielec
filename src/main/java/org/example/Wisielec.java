package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Wisielec extends JFrame implements ActionListener {
    private String[] kategoria_haslo;
    private int niepoprawneOdp;
    private int punkty;
    private final BazaSlow bazaSlow;
    private JButton[] przyciski;
    private Font czcionka;
    private JLabel obrazWisielca, etykietaKategorii, etykietaUkrytegoSlowa, etykietaRezultatu, etykietaSlowa,etykietaPunktow;

    private JDialog rezultatKoncowy;
    private boolean zgadles;
    private String nazwaGracza;
    private final MenadzerOkien menadzerOkien;
    private ZarzadzanieStatystyka zarzadzanieStatystyka;
    private Gracz gracz;

    private int najwyzszePunkty;

    public Wisielec(MenadzerOkien menadzerOkien) {
        super("Gra Wisielec (Java Ed.)");
        this.menadzerOkien=menadzerOkien;
        zarzadzanieStatystyka=menadzerOkien.getZarzadzanieStatystyka();
        setSize(Stale.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Stale.BACKGROUND_COLOR);

        bazaSlow=new BazaSlow();
        gracz=menadzerOkien.getGracz();
        przyciski=new JButton[35];
        kategoria_haslo=bazaSlow.losowanie();
        czcionka=Narzedzia.stworzCzcionke(Stale.FONT_PATH);
        najwyzszePunkty = 0;
        utworzRezultatKoncowy();
        stworzInterfejs();


    }


    private void stworzInterfejs() {
        //punkty
        punkty=0;
        zgadles=false;
        etykietaPunktow=new JLabel("Punkty: "+punkty);
        etykietaPunktow.setFont(czcionka.deriveFont(22f));
        etykietaPunktow.setOpaque(true);
        etykietaPunktow.setForeground(Color.WHITE);
        etykietaPunktow.setBackground(Stale.BACKGROUND_COLOR);
        etykietaPunktow.setBounds(0,0,etykietaPunktow.getPreferredSize().width, etykietaPunktow.getPreferredSize().height);

        obrazWisielca=Narzedzia.wczytajObraz(Stale.IMAGE_PATH);
        obrazWisielca.setBounds(0,0, obrazWisielca.getPreferredSize().width, obrazWisielca.getPreferredSize().height);

        //kategorie
        etykietaKategorii=new JLabel(kategoria_haslo[0]);
        etykietaKategorii.setFont(czcionka.deriveFont(30f));
        etykietaKategorii.setHorizontalAlignment(SwingConstants.CENTER);
        etykietaKategorii.setOpaque(true);
        etykietaKategorii.setForeground(Color.WHITE);
        etykietaKategorii.setBackground(Stale.SECONDARY_COLOR);
        etykietaKategorii.setBorder(BorderFactory.createLineBorder(Stale.SECONDARY_COLOR));
        etykietaKategorii.setBounds(0, obrazWisielca.getPreferredSize().height-28,
                Stale.FRAME_SIZE.width,etykietaKategorii.getPreferredSize().height);

        //ukrywanie slowa

        etykietaUkrytegoSlowa=new JLabel(Narzedzia.ukryjSlowa(kategoria_haslo[1]));
        czcionka=etykietaUkrytegoSlowa.getFont();
        etykietaUkrytegoSlowa.setFont(czcionka.deriveFont(56f));
        etykietaUkrytegoSlowa.setForeground(Color.WHITE);
        etykietaUkrytegoSlowa.setHorizontalAlignment(SwingConstants.CENTER);
        etykietaUkrytegoSlowa.setBounds(0, etykietaKategorii.getY()+etykietaKategorii.getPreferredSize().height+50,
                Stale.FRAME_SIZE.width, etykietaUkrytegoSlowa.getPreferredSize().height);

        //przyciski


        JPanel panelPrzyciskow=new JPanel();
        panelPrzyciskow.setBounds(-3, etykietaUkrytegoSlowa.getY()+etykietaUkrytegoSlowa.getPreferredSize().height,
                Stale.BUTTON_PANEL_SIZE.width, Stale.BUTTON_PANEL_SIZE.height);
        panelPrzyciskow.setLayout(null);
        panelPrzyciskow.setBackground(Stale.PRIMARY_COLOR);


        char[] litery = {'A', 'Ą', 'B', 'C', 'Ć', 'D', 'E', 'Ę', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'Ł', 'M', 'N', 'Ń',
                'O', 'Ó', 'P', 'Q', 'R', 'S', 'Ś', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Ź', 'Ż'};
        int button_x=0;
        int button_y=0;
        for (int i = 0; i < litery.length; i++) {
            JButton przycisk = new JButton(Character.toString(litery[i]));
            przycisk.setBackground(Stale.PRIMARY_COLOR);
            Font czcionka_przyciskow=przycisk.getFont();
            przycisk.setFont(czcionka_przyciskow.deriveFont(22f));
            przycisk.setForeground(Color.WHITE);
            przycisk.addActionListener(this);
            przycisk.setBounds(button_x,button_y,66,63);

            przyciski[i] = przycisk;
            panelPrzyciskow.add(przyciski[i]);
            button_x+=66;
            if (button_x>=528) {
                button_x=0;
                button_y+=63;
            }
        }

        // przycisk resetu
        JButton przyciskResetu = new JButton("Reset");
        Font czcionka_przyciskow=przyciskResetu.getFont();
        przyciskResetu.setFont(czcionka_przyciskow.deriveFont(22f));
        przyciskResetu.setForeground(Color.WHITE);
        przyciskResetu.setBackground(Stale.SECONDARY_COLOR);
        przyciskResetu.addActionListener(this);
        przyciskResetu.setBounds(198,252,165,63);
        panelPrzyciskow.add(przyciskResetu);

        // przycisk zakończenia
        JButton przyciskZakonczenia = new JButton("Zakończ");
        przyciskZakonczenia.setFont(czcionka_przyciskow.deriveFont(22f));
        przyciskZakonczenia.setForeground(Color.WHITE);
        przyciskZakonczenia.setBackground(Stale.SECONDARY_COLOR);
        przyciskZakonczenia.addActionListener(this);
        przyciskZakonczenia.setBounds(363,252,165,63);
        panelPrzyciskow.add(przyciskZakonczenia);

        getContentPane().add(etykietaPunktow);
        getContentPane().add(etykietaKategorii);
        getContentPane().add(obrazWisielca);
        getContentPane().add(etykietaUkrytegoSlowa);
        getContentPane().add(panelPrzyciskow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String komenda = e.getActionCommand();
        if (komenda.equals("Reset") || komenda.equals("Restart")) {
            if (gracz != null) {
                gracz.setPunkty(punkty);
                if (gracz.getPunkty()>najwyzszePunkty) {
                    najwyzszePunkty=gracz.getPunkty();
                }
            }
            resetGry();
            zgadles = false;
            if (komenda.equals("Restart")) {
                rezultatKoncowy.setVisible(false);
            }

        } else if (komenda.equals("Zakończ")) {
            if (gracz != null) {
                punkty=najwyzszePunkty;
                gracz.setPunkty(punkty);
                if (zarzadzanieStatystyka.graczMiesciSieWRankingu(gracz.getPunkty())) {
                    zarzadzanieStatystyka.aktualizujPlikStatystyki(gracz.getNazwa(), gracz.getPunkty());
                }
            }
            menadzerOkien.usunWisielcaITworzMenu();
        } else {
            JButton przycisk = (JButton) e.getSource();
            przycisk.setEnabled(false);

            if (kategoria_haslo[1].contains(komenda)) {
                przycisk.setBackground(Color.GREEN);

                char[] ukryteSlowo = etykietaUkrytegoSlowa.getText().toCharArray();

                for (int i = 0; i < kategoria_haslo[1].length(); i++) {
                    if (kategoria_haslo[1].charAt(i) == komenda.charAt(0)) {
                        ukryteSlowo[i] = komenda.charAt(0);
                    }
                }
                etykietaUkrytegoSlowa.setText(String.valueOf(ukryteSlowo));

                if (!etykietaUkrytegoSlowa.getText().contains("*")) {
                    etykietaRezultatu.setText("Zgadłeś!");
                    punkty++;
                    zgadles=true;
                    etykietaPunktow.setText("Punkty: " + punkty);
                    if (punkty > najwyzszePunkty) {
                        najwyzszePunkty = punkty;
                    }

                    rezultatKoncowy.setVisible(true);

                }
            } else {
                przycisk.setBackground(Color.RED);

                niepoprawneOdp++;

                Narzedzia.aktualizacja_obrazu(obrazWisielca, (niepoprawneOdp + 1) + ".png");

                if (niepoprawneOdp >= 6) {
                    gracz.setPunkty(punkty);
                    etykietaRezultatu.setText("Nie udało się, chcesz spróbować ponownie?");
                    rezultatKoncowy.setVisible(true);

                }
            }
            etykietaSlowa.setText("Słowo: " + kategoria_haslo[1]);
        }
    }

    private void utworzRezultatKoncowy() {
        rezultatKoncowy = new JDialog();
        rezultatKoncowy.setTitle("Rezultat");
        rezultatKoncowy.setSize(Stale.RESULT_DIALOG_SIZE);
        rezultatKoncowy.getContentPane().setBackground(Stale.BACKGROUND_COLOR);
        rezultatKoncowy.setResizable(false);
        rezultatKoncowy.setLocationRelativeTo(this);
        rezultatKoncowy.setModal(true);
        rezultatKoncowy.setLayout(new GridLayout(3, 1));
        rezultatKoncowy.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                resetGry();
            }
        });

        etykietaRezultatu = new JLabel();
        etykietaRezultatu.setForeground(Color.WHITE);
        etykietaRezultatu.setHorizontalAlignment(SwingConstants.CENTER);

        etykietaSlowa = new JLabel();
        etykietaSlowa.setForeground(Color.WHITE);
        etykietaSlowa.setHorizontalAlignment(SwingConstants.CENTER);

        JButton przyciskRestartu = new JButton("Restart");
        przyciskRestartu.setForeground(Color.WHITE);
        przyciskRestartu.setBackground(Stale.SECONDARY_COLOR);
        przyciskRestartu.addActionListener(this);

        rezultatKoncowy.add(etykietaRezultatu);
        rezultatKoncowy.add(etykietaSlowa);
        rezultatKoncowy.add(przyciskRestartu);
    }

    private void resetGry() {
        kategoria_haslo = bazaSlow.losowanie();

        // Aktualizacja najwyższej liczby punktów gracza
        if (gracz != null && punkty > najwyzszePunkty) {
            najwyzszePunkty = punkty;
        }

        // Reset punktów i niepoprawnych odpowiedzi tylko w przypadku niepowodzenia
        if (niepoprawneOdp >= 6 || !zgadles) {
            punkty = 0;
            niepoprawneOdp = 0;
            Narzedzia.aktualizacja_obrazu(obrazWisielca, Stale.IMAGE_PATH);
        }

        etykietaPunktow.setText("Punkty: " + punkty);
        etykietaKategorii.setText(kategoria_haslo[0]);
        String ukryteSlowo = Narzedzia.ukryjSlowa(kategoria_haslo[1]);
        etykietaUkrytegoSlowa.setText(ukryteSlowo);

        for (int i = 0; i < przyciski.length; i++) {
            przyciski[i].setEnabled(true);
            przyciski[i].setBackground(Stale.PRIMARY_COLOR);
        }
    }
    private void zwolnijZasoby() {
        obrazWisielca.setIcon(null); // Usuń ikonę z etykiety obrazu
    }

    @Override
    public void dispose() {
        zwolnijZasoby(); // Zwalnianie zasobów przed zamknięciem okna
        super.dispose();
    }

}
