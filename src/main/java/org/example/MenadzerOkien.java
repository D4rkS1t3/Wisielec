package org.example;

public class MenadzerOkien {
    private Menu oknoMenu;
    private Statystyki oknoStatystyk;
    private Wisielec oknoWisielca;
    private WprowadzenieNazwy oknoWprowadzeniaNazwy;
    private Gracz gracz;

    private ZarzadzanieStatystyka zarzadzanieStatystyka;


    //menu
    public void utworzMenu() {
        if (oknoMenu == null) {
            this.oknoMenu = new Menu(this);
        }
        gracz = null;
        oknoMenu.setVisible(true);
    }

    public void usunMenuIWprowadzanieNazwy() {
        oknoMenu.dispose();
        oknoMenu = null;
        utworzWprowadzenieNazwy();
    }

    public void usunWprowadzanieNazwyIutworzWisielca() {
        oknoWprowadzeniaNazwy.dispose();
        oknoWprowadzeniaNazwy = null;
        utworzWisielca();
    }

    public void utworzStatystki() {
        if (oknoStatystyk == null) {
            this.oknoStatystyk = new Statystyki(this);
        }
        oknoStatystyk.setVisible(true);
    }

    public void usunMenuITworzStatystyki() {
        oknoMenu.dispose();
        oknoMenu = null;
        utworzStatystki();
    }

    public void usunWisielcaITworzMenu() {
        oknoWisielca.dispose();
        oknoWisielca = null;
        utworzMenu();
    }

    public void usunWprowadzanieNazwyITworzMenu() {
        oknoWprowadzeniaNazwy.dispose();
        oknoWprowadzeniaNazwy = null;
        utworzMenu();
    }

    public void utworzWisielca() {
        if (oknoWisielca == null) {
            this.oknoWisielca = new Wisielec(this);
        }
        oknoWisielca.setVisible(true);
    }

    public void usunStatystykiITworzMenu() {
        oknoStatystyk.dispose();
        oknoStatystyk = null;
        utworzMenu();
    }

    public void utworzWprowadzenieNazwy() {
        if (oknoWprowadzeniaNazwy == null) {
            this.oknoWprowadzeniaNazwy = new WprowadzenieNazwy(this);
        }
        oknoWprowadzeniaNazwy.setVisible(true);
    }

    public void usunStatystykiITworzWprowadzenieNazwy() {
        oknoStatystyk.dispose();
        oknoStatystyk = null;
        utworzWprowadzenieNazwy();
    }

    public Gracz getGracz() {
        return gracz;
    }

    public void setGracz(Gracz gracz) {
        this.gracz = gracz;
    }

    public void ustawGracz(Gracz gracz) {
        this.gracz = gracz;
    }

    public ZarzadzanieStatystyka getZarzadzanieStatystyka() {
        return zarzadzanieStatystyka;
    }
    public void utworzZarzadzanieStatystyka() {
        this.zarzadzanieStatystyka=new ZarzadzanieStatystyka();
    }

    public void setZarzadzanieStatystyka(ZarzadzanieStatystyka zarzadzanieStatystyka) {
        this.zarzadzanieStatystyka = zarzadzanieStatystyka;
    }


}
