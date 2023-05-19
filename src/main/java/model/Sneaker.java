package model;

public class Sneaker {
    private int artikelnummer;
    private String merk;
    private String kleur;
    private int maat;
    private String beschrijving;
    private double prijs;
    private String image;

    public Sneaker(int artikelnummer, String merk, String kleur, int maat, String beschrijving, double prijs, String image) {
        this.artikelnummer = artikelnummer;
        this.merk = merk;
        this.kleur = kleur;
        this.maat = maat;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.image = image;
    }

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public String getMerk() {
        return merk;
    }

    public String getKleur() {
        return kleur;
    }

    public int getMaat() {
        return maat;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public String getImage() {
        return image;
    }
}
