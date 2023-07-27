package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    private Wisielec wisielec;
    private Font czcionka;
    private JLabel obrazWisielca, etykietaMenu;
    private MenadzerOkien menadzerOkien;


    public Menu(MenadzerOkien menadzerOkien) {
        super("Menu");
        this.menadzerOkien=menadzerOkien;
        setSize(Stale.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Stale.BACKGROUND_COLOR);
        czcionka = Narzedzia.stworzCzcionke(Stale.FONT_PATH);

        etykietaMenu=new JLabel("WISIELEC");
        etykietaMenu.setFont(czcionka.deriveFont(100f));
        etykietaMenu.setHorizontalAlignment(SwingConstants.CENTER);
        etykietaMenu.setOpaque(true);
        etykietaMenu.setForeground(Color.WHITE);
        etykietaMenu.setBackground(Stale.PRIMARY_COLOR);
        etykietaMenu.setBounds(0, 0,
                Stale.FRAME_SIZE.width,etykietaMenu.getPreferredSize().height);

        obrazWisielca = Narzedzia.wczytajObraz("7.png");
        obrazWisielca.setBounds(0, etykietaMenu.getY()+100, obrazWisielca.getPreferredSize().width, obrazWisielca.getPreferredSize().height);
        GridLayout siec = new GridLayout(3, 1);

        JPanel panelPrzyciskow = new JPanel();
        panelPrzyciskow.setBounds(-5, obrazWisielca.getY()+250,
                Stale.BUTTON_PANEL_SIZE.width, Stale.BUTTON_PANEL_SIZE.height+55);
        panelPrzyciskow.setLayout(siec);

        JButton przycisk1 = new JButton("Rozpocznij gre");
        przycisk1.setBackground(Stale.PRIMARY_COLOR);
        przycisk1.setFont(czcionka.deriveFont(31f));
        przycisk1.setForeground(Color.WHITE);
        przycisk1.addActionListener(this);

        JButton przycisk2 = new JButton("Statystyki");
        przycisk2.setBackground(Stale.PRIMARY_COLOR);
        przycisk2.setFont(czcionka.deriveFont(31f));
        przycisk2.setForeground(Color.WHITE);
        przycisk2.addActionListener(this);

        JButton przycisk3 = new JButton("Zakoncz gre");
        przycisk3.setBackground(Stale.PRIMARY_COLOR);
        przycisk3.setFont(czcionka.deriveFont(31f));
        przycisk3.setForeground(Color.WHITE);
        przycisk3.addActionListener(this);

        panelPrzyciskow.add(przycisk1);
        panelPrzyciskow.add(przycisk2);
        panelPrzyciskow.add(przycisk3);

        getContentPane().add(etykietaMenu);
        getContentPane().add(obrazWisielca);
        getContentPane().add(panelPrzyciskow);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String komenda = e.getActionCommand();

        if (komenda.equals("Rozpocznij gre")) {
            menadzerOkien.usunMenuIWprowadzanieNazwy();
        } else if (komenda.equals("Zakoncz gre")) {
            dispose();
        }else if (komenda.equals("Statystyki")) {
            menadzerOkien.usunMenuITworzStatystyki();
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
