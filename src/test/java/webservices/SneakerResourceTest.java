package webservices;

import helpers.SneakerRequest;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.PersistenceSneaker;

import javax.ws.rs.core.Response;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SneakerResourceTest {
    @BeforeEach
    public void init() {
        Sneaker sneaker1 = new Sneaker(99, "TestMerk", "TestKleur", 42, "TestBeschrijving", 99.99, "TestImage.png", 1);
        Sneaker sneaker2 = new Sneaker(999, "TestMerk", "TestKleur", 42, "TestBeschrijving", 99.99, "TestImage.png", 1);
        Sneaker sneaker3 = new Sneaker(9999, "TestMerk", "TestKleur", 42, "TestBeschrijving", 99.99, "TestImage.png", 1);

        PersistenceSneaker.saveSneaker(sneaker1);
        PersistenceSneaker.saveSneaker(sneaker2);
        PersistenceSneaker.saveSneaker(sneaker3);
    }
    @Test
    public void testGetAllSneakers() {
        SneakerResource sneakerResource = new SneakerResource();

        Response response = sneakerResource.getAllSneakers();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        ArrayList<Sneaker> sneakers = (ArrayList<Sneaker>) response.getEntity();
        assertNotNull(sneakers);
        assertEquals(4, sneakers.size()); // Los staand moet dit 3 zijn, maar wanneer de hele klasse runt moet het 4 zijn omdat je een sneaker maakt
    }

    @Test
    public void testGetSneaker_WhenSneakerExists() {
        int artikelnummer = 99;

        Sneaker sneaker = new Sneaker(99, "TestMerk", "TestKleur", 42, "TestBeschrijving", 99.99, "TestImage.png", 1);
        PersistenceSneaker.saveSneaker(sneaker);

        SneakerResource sneakerResource = new SneakerResource();

        Response response = sneakerResource.getSneaker(artikelnummer);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        Sneaker loadedSneaker = (Sneaker) response.getEntity();
        assertNotNull(loadedSneaker);
        assertEquals(artikelnummer, loadedSneaker.getArtikelnummer());
    }

    @Test
    public void testGetSneaker_WhenSneakerDoesNotExists() {
        int nonExistingArtikelnummer = 123456789;

        SneakerResource sneakerResource = new SneakerResource();

        Response response = sneakerResource.getSneaker(nonExistingArtikelnummer);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    public void testCreateSneaker() {
        SneakerRequest sneakerRequest = new SneakerRequest();

        SneakerResource sneakerResource = new SneakerResource();

        Response response = sneakerResource.createSneaker(sneakerRequest);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }
}