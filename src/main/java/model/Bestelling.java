package model;

import java.util.Date;

public class Bestelling {
    private int bestellingID;
    private Date datum;
    private double totaalPrijs;
    private boolean betalingStatus;
    private Klant klant;
    private Sneaker sneaker;

    public Bestelling(int bestellingID, Date datum, double totaalPrijs, boolean betalingStatus) {
        this.bestellingID = bestellingID;
        this.datum = datum;
        this.totaalPrijs = totaalPrijs;
        this.betalingStatus = betalingStatus;
    }
}
