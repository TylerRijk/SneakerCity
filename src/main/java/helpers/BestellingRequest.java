package helpers;

import java.io.Serializable;

public class BestellingRequest implements Serializable {
    public String voornaam;
    public String achternaam;
    public String email;
    public String adres;
    public String postcode;
    public String woonplaats;
    public int artikelnummer;
}
