package model;

import java.util.ArrayList;

public class Klant {
    private int id;
    private String email;
    private String wachtwoord;
    private ArrayList<Bestelling> bestellingen;

    public Klant(int id, String email, String wachtwoord) {
        this.id = id;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.bestellingen = new ArrayList<>();
    }

    public void voegBestellingToe(Bestelling bestelling) {
        bestellingen.add(bestelling);
    }

    public void verwijderBestelling(Bestelling bestelling) {
        bestellingen.remove(bestelling);
    }

    public ArrayList<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }
}
