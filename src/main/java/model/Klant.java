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
    }
}
