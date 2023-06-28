package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Bestelling implements Serializable {
    private int bestellingId;
    private Date datum;
    private double totaalPrijs;
    private boolean betalingStatus;
    private String voornaam;
    private String achternaam;
    private String adres;
    private String postcode;
    private String woonplaats;
    private String email;
    private int klantId;
    private Klant klant;
    private Sneaker sneaker;

    public static ArrayList<Bestelling> alleBestellingen = new ArrayList<>();

    public Bestelling(int bestellingId, Date datum, int klantId, String voornaam, String achternaam, String adres, String postcode, String woonplaats, Sneaker sneaker) {
        this.bestellingId = bestellingId;
        this.datum = datum;
        this.klantId = klantId;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.sneaker = sneaker;
        alleBestellingen.add(this);
    }


    public int getBestellingId() {
        return bestellingId;
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

    public static int generateId() {
        int highestId = 0;
        for (Bestelling bestelling : alleBestellingen) {
            if (bestelling.getBestellingId() > highestId) {
                highestId = bestelling.getBestellingId();
            }
        }
        return highestId + 1;
    }
}
