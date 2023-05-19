package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessLayerTest {
    private Beheer beheer;
    private Klant klant;
    private Medewerker medewerker;
    private Sneaker sneaker;

    @BeforeEach
    public void init() {
        medewerker = new Medewerker(1, "medewerker1@test.nl", "wachtwoord");
        beheer = new Beheer(medewerker);
        klant = new Klant(2, "klant@test.nl", "wachtwoord");
        sneaker = new Sneaker(12345, "Nike", "Zwart", 44, "Dit is een test omschrijving", 100, "sneaker1.jpg");
    }

    @Test
    public void testVoegSneakerToe() {
        beheer.voegSneakerToe(sneaker);
        ArrayList<Sneaker> sneakers = beheer.getSneakers();
        assertEquals(1, sneakers.size());
        assertEquals(sneaker, sneakers.get(0));
    }

    @Test
    public void testVerwijderSneaker() {
        beheer.voegSneakerToe(sneaker);
        beheer.verwijderSneaker(sneaker);
        ArrayList<Sneaker> sneakers = beheer.getSneakers();
        assertEquals(0, sneakers.size());
    }

    @Test
    public void testVoegBestellingToe() {
        Date datum = new Date();
        Bestelling bestelling = new Bestelling(1, datum, 100, false, klant, sneaker);
        klant.voegBestellingToe(bestelling);
        ArrayList<Bestelling> bestellingen = klant.getBestellingen();
        assertEquals(1, bestellingen.size());
        assertEquals(bestelling, bestellingen.get(0));
    }

    @Test
    public void testVerwijderBestelling() {
        Date datum = new Date();
        Bestelling bestelling = new Bestelling(1, datum, 100, false, klant, sneaker);
        klant.voegBestellingToe(bestelling);
        klant.verwijderBestelling(bestelling);
        ArrayList<Bestelling> bestellingen = klant.getBestellingen();
        assertEquals(0, bestellingen.size());
    }
}