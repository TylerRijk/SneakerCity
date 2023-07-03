package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Sneaker implements Serializable {
    private int artikelnummer;
    private String merk;
    private String kleur;
    private int maat;
    private String beschrijving;
    private double prijs;
    private String image;
    private int voorraad;

    private static ArrayList<Sneaker> sneakers = new ArrayList<>();

    public Sneaker(int artikelnummer, String merk, String kleur, int maat, String beschrijving, double prijs, String image, int voorraad) {
        this.artikelnummer = artikelnummer;
        this.merk = merk;
        this.kleur = kleur;
        this.maat = maat;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.image = image;
        this.voorraad = voorraad;
        sneakers.add(this);
    }


    public static ArrayList<Sneaker> getSneakers() {
        return sneakers;
    }

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public int getMaat() {
        return maat;
    }

    public void setMaat(int maat) {
        this.maat = maat;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        if (prijs <= 0) {
            throw new IllegalArgumentException("De prijs moet positief zijn");
        }
        this.prijs = prijs;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(int voorraad) {
        if (voorraad < 0) {
            throw new IllegalArgumentException("De voorraad moet 0 of meer zijn");
        }
        this.voorraad = voorraad;
    }

//    @Override
//    public boolean equals(Object andereObject) {
//        boolean gelijkeObjecten = false;
//        if (andereObject instanceof Sneaker) {
//            Sneaker andereSneaker = (Sneaker) andereObject;
//            if (andereSneaker.getArtikelnummer() == this.artikelnummer) {
//                gelijkeObjecten = true;
//            }
//        }
//        return gelijkeObjecten;
//    }
}
