package org.example;

public class Gracz {
    private String nazwa;
    private int punkty;

    public Gracz(String nazwa) {
        this.nazwa = nazwa;
        this.punkty = 0;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }
}