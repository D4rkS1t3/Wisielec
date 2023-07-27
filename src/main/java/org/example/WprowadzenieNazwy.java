package org.example;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WprowadzenieNazwy extends JFrame implements ActionListener {

    private Font czcionka;
    private JTextField poleTekstowe;
    private JButton przyciskZatwierdz, przyciskZatwierdz2;
    private JLabel nazwa;
    private MenadzerOkien menadzerOkien;
    private Gracz gracz;

    public WprowadzenieNazwy(MenadzerOkien menadzerOkien) {
        super("OknoWprowadzaniaNazwy");
        this.menadzerOkien=menadzerOkien;
        setSize(Stale.FRAME_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Stale.BACKGROUND_COLOR);
        getContentPane().setForeground(Color.WHITE);

        czcionka = new Font("Arial", Font.PLAIN, 12);

        stworzWprowadzanieNazwy();
    }

    public void stworzWprowadzanieNazwy() {
        nazwa = new JLabel("Wprowadź nazwę użytkownika:");
        nazwa.setHorizontalAlignment(SwingConstants.CENTER);
        nazwa.setForeground(Color.WHITE);
        nazwa.setBackground(Stale.BACKGROUND_COLOR);
        nazwa.setFont(czcionka.deriveFont(33f));
        nazwa.setBounds(0, 125, Stale.FRAME_SIZE.width, 50);

        poleTekstowe = new JTextField(18);
        poleTekstowe.setFont(czcionka.deriveFont(50f));
        poleTekstowe.setBounds(0, 225, Stale.FRAME_SIZE.width - 14, 200);
        poleTekstowe.setHorizontalAlignment(SwingConstants.CENTER);
        poleTekstowe.setBackground(Stale.SECONDARY_COLOR);
        poleTekstowe.setForeground(Color.WHITE);

        ((AbstractDocument) poleTekstowe.getDocument()).setDocumentFilter(new MaxLengthFilter(18)); // Ustaw maksymalną długość na 10 znaków

        przyciskZatwierdz = new JButton("Zatwierdź");
        przyciskZatwierdz.setBounds(0, 475, Stale.FRAME_SIZE.width - 14, 100);
        przyciskZatwierdz.setBackground(Stale.SECONDARY_COLOR);
        przyciskZatwierdz.setForeground(Color.WHITE);
        przyciskZatwierdz.setFont(czcionka.deriveFont(30f));
        przyciskZatwierdz.addActionListener(this);

        przyciskZatwierdz2 = new JButton("Powrót do menu");
        przyciskZatwierdz2.setBounds(0, 580, Stale.FRAME_SIZE.width - 14, 100);
        przyciskZatwierdz2.setBackground(Stale.SECONDARY_COLOR);
        przyciskZatwierdz2.setForeground(Color.WHITE);
        przyciskZatwierdz2.setFont(czcionka.deriveFont(30f));
        przyciskZatwierdz2.addActionListener(this);

        JPanel panelGlowny = new JPanel();
        panelGlowny.setBackground(Stale.BACKGROUND_COLOR);
        panelGlowny.setLayout(null);
        setContentPane(panelGlowny);

        panelGlowny.add(nazwa);
        panelGlowny.add(poleTekstowe);
        panelGlowny.add(przyciskZatwierdz);
        panelGlowny.add(przyciskZatwierdz2);
        panelGlowny.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String komenda = e.getActionCommand();
        if (komenda.equals("Zatwierdź")) {
            String wprowadzonyTekst = poleTekstowe.getText().trim(); // Usuń ewentualne spacje na początku i końcu
            if (wprowadzonyTekst.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wprowadź nazwę użytkownika przed zatwierdzeniem!");
            } else {
                Gracz gracz = new Gracz(wprowadzonyTekst);
                menadzerOkien.ustawGracz(gracz);
                JOptionPane.showMessageDialog(null, "Wprowadzona nazwa użytkownika: " + wprowadzonyTekst);
                menadzerOkien.usunWprowadzanieNazwyIutworzWisielca();
            }
        } else if (komenda.equals("Powrót do menu")) {
            menadzerOkien.usunWprowadzanieNazwyITworzMenu();
        }
    }

    class MaxLengthFilter extends DocumentFilter {
        private int maxLength;

        public MaxLengthFilter(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String str, AttributeSet attr)
                throws BadLocationException {
            if ((fb.getDocument().getLength() + str.length()) <= maxLength) {
                super.insertString(fb, offset, str, attr);
            } else {
                // Można zastosować tutaj dowolne działanie w przypadku przekroczenia maksymalnej długości
                // Na przykład wyświetlić komunikat lub zignorować wpisywany tekst
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet attr)
                throws BadLocationException {
            if ((fb.getDocument().getLength() - length + str.length()) <= maxLength) {
                super.replace(fb, offset, length, str, attr);
            } else {
                // Można zastosować tutaj dowolne działanie w przypadku przekroczenia maksymalnej długości
                // Na przykład wyświetlić komunikat lub zignorować wpisywany tekst
            }
        }
    }
}
