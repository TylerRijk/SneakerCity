package model;

import java.util.Date;

public class Bestelling {
    private int bestellingID;
    private Date datum;
    private double totaalPrijs;
    private boolean betalingStatus;
    private Klant klant;
    private Sneaker sneaker;

    public Bestelling(int bestellingID, Date datum, double totaalPrijs, boolean betalingStatus, Klant klant, Sneaker sneaker) {
        this.bestellingID = bestellingID;
        this.datum = datum;
        this.totaalPrijs = totaalPrijs;
        this.betalingStatus = betalingStatus;
        this.klant = klant;
        this.sneaker = sneaker;
    }

    public int getBestellingID() {
        return bestellingID;
    }

    public Date getDatum() {
        return datum;
    }

    public double getTotaalPrijs() {
        return totaalPrijs;
    }

    public void setTotaalPrijs(double totaalPrijs) {
        if (totaalPrijs < 0) {
            throw new IllegalArgumentException("De totaalprijs moet positief zijn");
        }
        this.totaalPrijs = totaalPrijs;
    }

    public boolean getBetalingStatus() {
        return betalingStatus;
    }

    public void setBetalingStatus(boolean betalingStatus) {
        this.betalingStatus = betalingStatus;
    }

    public Klant getKlant() {
        return klant;
    }

    public Sneaker getSneaker() {
        return sneaker;
    }
}
