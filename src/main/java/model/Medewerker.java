package model;

public class Medewerker {
    private int medewerkerID;
    private String email;
    private String wachtwoord;

    public Medewerker(int medewerkerID, String email, String wachtwoord) {
        this.medewerkerID = medewerkerID;
        this.email = email;
        this.wachtwoord = wachtwoord;
    }
}
