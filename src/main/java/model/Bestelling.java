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

    public boolean getBetalingStatus() {
        return betalingStatus;
    }

    public Klant getKlant() {
        return klant;
    }

    public Sneaker getSneaker() {
        return sneaker;
    }
}
