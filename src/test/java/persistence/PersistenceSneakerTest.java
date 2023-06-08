package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceSneakerTest {
    @Test
    public void testSaveSneaker() {
        Sneaker sneaker = new Sneaker(99, "TestMerk", "TestKleur", 42, "TestBeschrijving", 99.99, "TestImage.png", 1);

        PersistenceSneaker.saveSneaker(sneaker);

        Sneaker loadedSneaker = PersistenceSneaker.loadSneaker(sneaker.getArtikelnummer());
        assertNotNull(loadedSneaker);
        assertEquals(sneaker, loadedSneaker);
    }

    @Test
    public void testLoadSneaker_WhenSneakerDoesNotExists() {
        int nonExistingArtikelnummer = 123456789;

        Sneaker loadedSneaker = PersistenceSneaker.loadSneaker(nonExistingArtikelnummer);

        assertNull(loadedSneaker);
    }

    @Test
    public void testLoadAllSneakers() {
        Sneaker sneaker1 = new Sneaker(99, "TestMerk", "TestKleur", 42, "TestBeschrijving", 99.99, "TestImage.png", 1);
        Sneaker sneaker2 = new Sneaker(999, "TestMerk", "TestKleur", 42, "TestBeschrijving", 99.99, "TestImage.png", 1);
        Sneaker sneaker3 = new Sneaker(9999, "TestMerk", "TestKleur", 42, "TestBeschrijving", 99.99, "TestImage.png", 1);

        PersistenceSneaker.saveSneaker(sneaker1);
        PersistenceSneaker.saveSneaker(sneaker2);
        PersistenceSneaker.saveSneaker(sneaker3);

        ArrayList<Sneaker> sneakers = PersistenceSneaker.loadAllSneakers();

        assertEquals(4, sneakers.size()); // Los staand moet dit 3 zijn, maar wanneer de hele klasse runt moet het 4 zijn omdat je een sneaker maakt

        assertTrue(sneakers.contains(sneaker1));
        assertTrue(sneakers.contains(sneaker2));
        assertTrue(sneakers.contains(sneaker3));
    }
}