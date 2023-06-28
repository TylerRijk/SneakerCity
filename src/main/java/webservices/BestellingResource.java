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

@Path("/bestelling")
public class BestellingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBestellingen() {
        ArrayList<Bestelling> bestellingen = PersistenceBestelling.loadAllBestellingen();

        if (bestellingen.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Geen bestellingen gevonden").build();
        } else {
            return Response.ok(bestellingen).build();
        }
    }

    @GET
    @Path("/{bestellingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBestelling(@PathParam("bestellingId") int bestellingId) {
        Bestelling bestelling = PersistenceBestelling.loadBestelling(bestellingId);

        if (bestelling != null) {
            return Response.status(Response.Status.OK).entity(bestelling).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBestelling(BestellingRequest bestellingRequest) {
        System.out.println("appel");
        int klantId = Klant.getKlantIdByEmail(bestellingRequest.email);

        System.out.println(klantId);

        if (klantId == -1) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Sneaker sneaker = PersistenceSneaker.loadSneaker(bestellingRequest.artikelnummer);

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

        System.out.println(bestelling);

        PersistenceBestelling.saveBestelling(bestelling);

        System.out.println(PersistenceBestelling.loadBestelling(1));

        return Response.ok().build();
    }
}
