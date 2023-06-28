package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessLayerTest {
    private Medewerker medewerker;
    private Sneaker sneaker;
    private Klant klant;
    private Bestelling bestelling;

    @BeforeEach
    public void init() {
        medewerker = new Medewerker("AdminTest", "AdminTest", "admin", 0, "Test@admin.nl");
        klant = new Klant("KlantTest", "KlantTest", "klant", 1, "klant@test.nl");
        sneaker = new Sneaker(1, "TestMerk", "TestKleur", 42, "TestBeschrijving", 100.00, "TestImage.png", 1);
        bestelling = new Bestelling(100, new Date(), 34567, "Test", "Test", "Test", "0000AA", "Test", sneaker);
    }

    @Test
    public void testUniekMedewerkerID() {
        Medewerker medewerker2 = new Medewerker("Medewerker", "Medewerker", "admin", 1, "test@test.nl");

        assertNotEquals(medewerker.getId(), medewerker2.getId());
    }

    @Test
    public void testUniekMedewerkerEmail() {
        Medewerker medewerker2 = new Medewerker("Medewerker", "Medewerker", "admin", 1, "test@test.nl");

        assertNotEquals(medewerker.getEmail(), medewerker2.getEmail());
    }

    @Test
    public void testUniekMedewerkerWachtwoord() {
        Medewerker medewerker2 = new Medewerker("Medewerker", "Medewerker", "admin", 1, "test@test.nl");

        assertNotEquals(medewerker.getPassword(), medewerker2.getPassword());
    }

    @Test
    public void testUniekSneakerArtikelnummer() {
        Sneaker sneaker2 = new Sneaker(2, "Merk", "Kleur", 42, "Beschrijving", 99.99, "image.jpg", 1);

        assertNotEquals(sneaker.getArtikelnummer(), sneaker2.getArtikelnummer());
    }

    @Test
    public void testPositieveSneakerPrijs() {
        double positievePrijs = 50.00;

        assertDoesNotThrow(() -> sneaker.setPrijs(positievePrijs));
    }

    @Test
    public void testNegatieveSneakerPrijs() {
        double negatievePrijs = -50.00;

        assertThrows(IllegalArgumentException.class, () -> sneaker.setPrijs(negatievePrijs));
    }

    @Test
    public void testNulSneakerPrijs() {
        double nulPrijs = 0.0;

        assertThrows(IllegalArgumentException.class, () -> sneaker.setPrijs(nulPrijs));
    }

    @Test
    public void testPositieveVoorraad() {
        int positieveVoorraad = 1;

        assertDoesNotThrow(() -> sneaker.setVoorraad(positieveVoorraad));
    }

    @Test
    public void testNegatieveVoorraad() {
        int negatieveVoorraad = -1;

        assertThrows(IllegalArgumentException.class, () -> sneaker.setVoorraad(negatieveVoorraad));
    }

    @Test
    public void testUniekeBestellingID() {
        Bestelling bestelling2 = new Bestelling(42, new Date(), 367, "Test", "Test", "Test", "0000AA", "Test", sneaker);

        assertNotEquals(bestelling.getBestellingId(), bestelling2.getBestellingId());
    }

    @Test
    public void testPositieveTotaalPrijsBestelling() {
        double positievePrijs = 50.00;

        assertDoesNotThrow(() -> bestelling.setTotaalPrijs(positievePrijs));
    }

    @Test
    public void testNegatieveTotaalPrijsBestelling() {
        double negatievePrijs = -50.00;

        assertThrows(IllegalArgumentException.class, () -> bestelling.setTotaalPrijs(negatievePrijs));
    }

    @Test
    public void testGeldigeStatusBestelling() {
        boolean betalingStatus = true;

        bestelling.setBetalingStatus(betalingStatus);
        assertEquals(betalingStatus, bestelling.getBetalingStatus());
    }

    @Test
    public void testUniekKlantID() {
        Klant klant2 = new Klant("TestKlant", "TestKlant", "klant", 2, "klant@test.nl");

        assertNotEquals(klant.getId(), klant2.getId());
    }

    @Test
    public void testUniekKlantEmail() {
        Klant klant2 = new Klant("TestKlant", "TestKlant", "klant", 2, "test@klant.nl");

        assertNotEquals(klant.getEmail(), klant2.getEmail());
    }

}
