package model;

import security.User;

import java.util.ArrayList;

public class Klant {
    private int id;
    private String email;
    private String naam;
    private User user;
    private ArrayList<Bestelling> bestellingen;

    public Klant(int id, String email, String naam, User user) {
        this.id = id;
        this.email = email;
        this.naam = naam;
        this.user = user;
        this.bestellingen = new ArrayList<>();
    }

    public int getID() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNaam() {
        return naam;
    }

    public User getUser() {
        return user;
    }

    public void voegBestellingToe(Bestelling bestelling) {
        bestellingen.add(bestelling);
    }

    public ArrayList<Bestelling> getBestellingen() {
        return bestellingen;
    }
}
