package webservices;

import helpers.BestellingRequest;
import model.*;
import model.Klant;
import persistence.PersistenceBestelling;
import persistence.PersistenceSneaker;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Path("/bestelling")
public class BestellingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBestellingen() {
        // Alle bestellingen ophalen uit systeem
        ArrayList<Bestelling> bestellingen = PersistenceBestelling.loadAllBestellingen();

        // Controleren of er bestellingen aanwezig zijn
        if (bestellingen.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "Geen bestelling gevonden")).build();
        } else {
            return Response.ok(bestellingen).build();
        }
    }

    @GET
    @Path("/{bestellingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBestelling(@PathParam("bestellingId") int bestellingId) {
        // Bestelling ophalen uit systeem met opgegeven bestelling-id
        Bestelling bestelling = PersistenceBestelling.loadBestelling(bestellingId);

        // Controleren of de bestelling is gevonden
        if (bestelling != null) {
            return Response.status(Response.Status.OK).entity(bestelling).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBestelling(BestellingRequest bestellingRequest) {
        // Klant-id ophalen aan de hand van email
        int klantId = Klant.getKlantIdByEmail(bestellingRequest.email);

        // Controleren of klant klopt
        if (klantId == -1) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Sneaker ophalen uit systeem met opgegeven artikelnummer
        Sneaker sneaker = PersistenceSneaker.loadSneaker(bestellingRequest.artikelnummer);

        // Nieuwe bestelling aanmaken
        Bestelling bestelling = new Bestelling(Bestelling.generateId(),
                new Date(),
                klantId,
                bestellingRequest.voornaam,
                bestellingRequest.achternaam,
                bestellingRequest.adres,
                bestellingRequest.postcode,
                bestellingRequest.woonplaats,
                sneaker
        );

        // Bestelling opslaan in systeem
        PersistenceBestelling.saveBestelling(bestelling);

        return Response.ok().build();
    }
}
