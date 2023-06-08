package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceKlantTest {
    @Test
    public void testSaveKlant() {
        Klant klant = new Klant("TestKlant", "TestKlant", "klant", 99, "Testklant@test.nl");

        PersistenceKlant.saveKlant(klant);

        Klant loadedKlant = PersistenceKlant.loadKlant(klant.getId());
        assertNotNull(loadedKlant);
        assertEquals(klant, loadedKlant);
    }

    @Test
    public void testLoadKlant_WhenKlantDoesNotExists() {
        int nonExistingKlantId = 123456789;

        Klant loadedKlant = PersistenceKlant.loadKlant(nonExistingKlantId);

        assertNull(loadedKlant);
    }

    @Test
    public void testLoadAllKlanten() {
        Klant klant1 = new Klant("TestKlant1", "TestKlant", "klant", 99, "Testklant@test.nl");
        Klant klant2 = new Klant("TestKlant2", "TestKlant", "klant", 999, "Testklant@test.nl");
        Klant klant3 = new Klant("TestKlant3", "TestKlant", "klant", 9999, "Testklant@test.nl");

        PersistenceKlant.saveKlant(klant1);
        PersistenceKlant.saveKlant(klant2);
        PersistenceKlant.saveKlant(klant3);

        ArrayList<Klant> klanten = PersistenceKlant.loadAllKlanten();

        assertEquals(3, klanten.size());

        assertTrue(klanten.contains(klant1));
        assertTrue(klanten.contains(klant2));
        assertTrue(klanten.contains(klant3));
    }
}