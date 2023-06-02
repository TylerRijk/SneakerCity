//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class BusinessLayerTest {
//    private Medewerker medewerker;
//    private Sneaker sneaker;
//    private Klant klant;
//    private Bestelling bestelling;
//    private Klantgegevens klantgegevens;
//
//    @BeforeEach
//    public void init() {
//        medewerker = new Medewerker(1, "medewerker@test.nl", "wachtwoord");
//        sneaker = new Sneaker(1, "Merk", "Kleur", 42, "Beschrijving", 99.99, "image.jpg", 1);
//        klant = new Klant(1, "klant@test.nl", "wachtwoord");
//        bestelling = new Bestelling(1, new Date(), 99.99, true, null, null);
//        klantgegevens = new Klantgegevens("Voornaam", "Achternaam", "Adres", "Woonplaats", "123456789");
//    }
//
//    @Test
//    public void testUniekMedewerkerID() {
//        Medewerker medewerker2 = new Medewerker(2, "medewerker2@test.nl", "wachtwoord2");
//
//        assertNotEquals(medewerker.getMedewerkerID(), medewerker2.getMedewerkerID());
//    }
//
//    @Test
//    public void testUniekMedewerkerEmail() {
//        Medewerker medewerker2 = new Medewerker(2, "medewerker2@test.nl", "wachtwoord2");
//
//        assertNotEquals(medewerker.getEmail(), medewerker2.getEmail());
//    }
//
//    @Test
//    public void testUniekMedewerkerWachtwoord() {
//        Medewerker medewerker2 = new Medewerker(2, "medewerker2@test.nl", "wachtwoord2");
//
//        assertNotEquals(medewerker.getWachtwoord(), medewerker2.getWachtwoord());
//    }
//
//    @Test
//    public void testUniekSneakerArtikelnummer() {
//        Sneaker sneaker2 = new Sneaker(2, "Merk", "Kleur", 42, "Beschrijving", 99.99, "image.jpg", 1);
//
//        assertNotEquals(sneaker.getArtikelnummer(), sneaker2.getArtikelnummer());
//    }
//
//    @Test
//    public void testPositieveSneakerPrijs() {
//        double positievePrijs = 50.00;
//
//        assertDoesNotThrow(() -> sneaker.setPrijs(positievePrijs));
//    }
//
//    @Test
//    public void testNegatieveSneakerPrijs() {
//        double negatievePrijs = -50.00;
//
//        assertThrows(IllegalArgumentException.class, () -> sneaker.setPrijs(negatievePrijs));
//    }
//
//    @Test
//    public void testNulSneakerPrijs() {
//        double nulPrijs = 0.0;
//
//        assertThrows(IllegalArgumentException.class, () -> sneaker.setPrijs(nulPrijs));
//    }
//
//    @Test
//    public void testPositieveVoorraad() {
//        int positieveVoorraad = 1;
//
//        assertDoesNotThrow(() -> sneaker.setVoorraad(positieveVoorraad));
//    }
//
//    @Test
//    public void testNegatieveVoorraad() {
//        int negatieveVoorraad = -1;
//
//        assertThrows(IllegalArgumentException.class, () -> sneaker.setVoorraad(negatieveVoorraad));
//    }
//
//    @Test
//    public void testUniekeBestellingID() {
//        Bestelling bestelling2 = new Bestelling(2, new Date(), 99.99, true, null, null);
//
//        assertNotEquals(bestelling.getBestellingID(), bestelling2.getBestellingID());
//    }
//
//    @Test
//    public void testPositieveTotaalPrijsBestelling() {
//        double positievePrijs = 50.00;
//
//        assertDoesNotThrow(() -> bestelling.setTotaalPrijs(positievePrijs));
//    }
//
//    @Test
//    public void testNegatieveTotaalPrijsBestelling() {
//        double negatievePrijs = -50.00;
//
//        assertThrows(IllegalArgumentException.class, () -> bestelling.setTotaalPrijs(negatievePrijs));
//    }
//
//    @Test
//    public void testGeldigeStatusBestelling() {
//        boolean betalingStatus = true;
//
//        bestelling.setBetalingStatus(betalingStatus);
//        assertEquals(betalingStatus, bestelling.getBetalingStatus());
//    }
//
//    @Test
//    public void testUniekKlantID() {
//        Klant klant2 = new Klant(2, "klant2@test.nl", "wachtwoord2");
//
//        assertNotEquals(klant.getId(), klant2.getId());
//    }
//
//    @Test
//    public void testUniekKlantEmail() {
//        Klant klant2 = new Klant(2, "klant2@test.nl", "wachtwoord2");
//
//        assertNotEquals(klant.getEmail(), klant2.getEmail());
//    }
//
//    @Test
//    public void testCompleetKlantGegevens() {
//        String voornaam = klantgegevens.getVoornaam();
//        String achternaam = klantgegevens.getAchternaam();
//        String adres = klantgegevens.getAdres();
//        String woonplaats = klantgegevens.getWoonplaats();
//        String telefoonnummer = klantgegevens.getTelefoonnummer();
//
//        assertNotNull(voornaam);
//        assertNotNull(achternaam);
//        assertNotNull(adres);
//        assertNotNull(woonplaats);
//        assertNotNull(telefoonnummer);
//    }
//}
