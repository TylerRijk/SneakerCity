package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceMedewerkerTest {
    @Test
    public void testSaveMedewerker() {
        Medewerker medewerker = new Medewerker("TestAdmin", "TestAdmin", "admin", 99, "Testadmin@admin.nl");

        PersistenceMedewerker.saveMedewerker(medewerker);

        Medewerker loadedMedewerker = PersistenceMedewerker.loadMedewerker(medewerker.getId());
        assertNotNull(loadedMedewerker);
        assertEquals(medewerker, loadedMedewerker);
    }

    @Test
    public void testLoadMedewerker_WhenMedewerkerDoesNotExists() {
        int nonExistingMedewerkerId = 123456789;

        Medewerker loadedMedewerker = PersistenceMedewerker.loadMedewerker(nonExistingMedewerkerId);

        assertNull(loadedMedewerker);
    }

    @Test
    public void testLoadAllMedewerkers() {
        Medewerker medewerker1 = new Medewerker("TestAdmin1", "TestAdmin", "admin", 99, "Testadmin@admin.nl");
        Medewerker medewerker2 = new Medewerker("TestAdmin2", "TestAdmin", "admin", 999, "Testadmin@admin.nl");
        Medewerker medewerker3 = new Medewerker("TestAdmin3", "TestAdmin", "admin", 9999, "Testadmin@admin.nl");

        PersistenceMedewerker.saveMedewerker(medewerker1);
        PersistenceMedewerker.saveMedewerker(medewerker2);
        PersistenceMedewerker.saveMedewerker(medewerker3);

        ArrayList<Medewerker> medewerkers = PersistenceMedewerker.loadAllMedewerkers();

        assertEquals(3, medewerkers.size());

        assertTrue(medewerkers.contains(medewerker1));
        assertTrue(medewerkers.contains(medewerker2));
        assertTrue(medewerkers.contains(medewerker3));
    }
}