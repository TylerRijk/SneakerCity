package model;

public class Sneaker {
    private int artikelnummer;
    private String merk;
    private String kleur;
    private int maat;
    private String beschrijving;
    private double prijs;
    private String image;
    private int voorraad;

    public Sneaker(int artikelnummer, String merk, String kleur, int maat, String beschrijving, double prijs, String image, int voorraad) {
        this.artikelnummer = artikelnummer;
        this.merk = merk;
        this.kleur = kleur;
        this.maat = maat;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.image = image;
        this.voorraad = voorraad;
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

    public void setPrijs(double prijs) {
        if (prijs <= 0) {
            throw new IllegalArgumentException("De prijs moet positief zijn");
        }
        this.prijs = prijs;
    }

    public String getImage() {
        return image;
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

    public boolean isOpVoorraad() {
        return voorraad > 0;
    }

}
