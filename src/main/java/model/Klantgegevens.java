package model;

public class Klantgegevens {
    private String voornaam;
    private String achternaam;
    private String adres;
    private String woonplaats;
    private String telefoonnummer;

    public Klantgegevens(String voornaam, String achternaam, String adres, String woonplaats, String telefoonnummer) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.adres = adres;
        this.woonplaats = woonplaats;
        this.telefoonnummer = telefoonnummer;
    }
}
