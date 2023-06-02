package model;

import security.User;

public class Medewerker {
    private int medewerkerID;
    private String email;
    private String naam;
    private User user;

    public Medewerker(int medewerkerID, String email, String naam, User user) {
        this.medewerkerID = medewerkerID;
        this.email = email;
        this.naam = naam;
        this.user = user;
    }

    public int getMedewerkerID() {
        return medewerkerID;
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
}
