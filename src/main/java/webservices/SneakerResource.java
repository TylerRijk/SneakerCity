package webservices;

import helpers.SneakerRequest;
import model.Sneaker;
import persistence.PersistenceSneaker;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/sneakers")
public class SneakerResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSneakers() {
        // Alle sneakers ophalen uit systeem
        ArrayList<Sneaker> sneakers = PersistenceSneaker.loadAllSneakers();

        // Controleren of er sneakers aanwezig zijn
        if (sneakers.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Geen sneakers gevonden").build();
        } else {
            return Response.status(Response.Status.OK).entity(sneakers).build();
        }
    }

    @GET
    @Path("/{artikelnummer}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSneaker(@PathParam("artikelnummer") int artikelnummer) {
        // Sneaker ophalen uit systeem met opgegeven artikelnummer
        Sneaker sneaker = PersistenceSneaker.loadSneaker(artikelnummer);

        // Controleren of de sneaker is gevonden
        if (sneaker != null) {
            return Response.status(Response.Status.OK).entity(sneaker).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSneaker(SneakerRequest sneakerRequest) {
        // Controleren of alle velden zijn ingevuld
        if (sneakerRequest.artikelnummer == 0
                || sneakerRequest.merk == null || sneakerRequest.merk.isEmpty()
                || sneakerRequest.kleur == null || sneakerRequest.kleur.isEmpty()
                || sneakerRequest.maat == 0
                || sneakerRequest.beschrijving == null || sneakerRequest.beschrijving.isEmpty()
                || sneakerRequest.prijs == 0
                || sneakerRequest.image == null || sneakerRequest.image.isEmpty()
                || sneakerRequest.voorraad == 0 ) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        // Alle sneakers ophalen uit systeem
        ArrayList<Sneaker> sneakers = PersistenceSneaker.loadAllSneakers();

        // Controleren of er een sneaker al bestaat met opgegeven artikelnummer
        for (Sneaker sneaker : sneakers) {
            if (sneaker.getArtikelnummer() == sneakerRequest.artikelnummer) {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }

        // De nieuwe sneaker opslaan in het systeem
        PersistenceSneaker.saveSneaker(new Sneaker(sneakerRequest.artikelnummer, sneakerRequest.merk, sneakerRequest.kleur, sneakerRequest.maat, sneakerRequest.beschrijving, sneakerRequest.prijs, sneakerRequest.image, sneakerRequest.voorraad));
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{artikelnummer}")
    public Response updateSneaker(@PathParam("artikelnummer") int artikelnummer, SneakerRequest sneakerRequest) {
        // Controleren of alle velden zijn ingevuld
        if (sneakerRequest.artikelnummer == 0
                || sneakerRequest.merk == null || sneakerRequest.merk.isEmpty()
                || sneakerRequest.kleur == null || sneakerRequest.kleur.isEmpty()
                || sneakerRequest.maat == 0
                || sneakerRequest.beschrijving == null || sneakerRequest.beschrijving.isEmpty()
                || sneakerRequest.prijs == 0
                || sneakerRequest.image == null || sneakerRequest.image.isEmpty()
                || sneakerRequest.voorraad == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        // Sneaker ophalen uit systeem met opgegeven artikelnummer
        Sneaker sneaker = PersistenceSneaker.loadSneaker(artikelnummer);

        // Als sneaker gevonden is, eigenschappen bijwerken
        if (sneaker != null) {
            sneaker.setMerk(sneakerRequest.merk);
            sneaker.setKleur(sneakerRequest.kleur);
            sneaker.setMaat(sneakerRequest.maat);
            sneaker.setBeschrijving(sneakerRequest.beschrijving);
            sneaker.setPrijs(sneakerRequest.prijs);
            sneaker.setImage(sneakerRequest.image);
            sneaker.setVoorraad(sneakerRequest.voorraad);

            // Bijgewerkte sneaker opslaan in systeem
            PersistenceSneaker.saveSneaker(sneaker);

            return Response.status(Response.Status.OK).build();
        }

        // Als er geen sneaker gevonden is met opgegeven artikelnummer, stuur foutmelding
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{artikelnummer}")
    public Response deleteSneaker(@PathParam("artikelnummer") int artikelnummer) {
        // Sneaker ophalen uit systeem met opgegeven artikelnummer
        Sneaker sneaker = PersistenceSneaker.loadSneaker(artikelnummer);

        // Controleren of sneaker gevonden is
        if (sneaker != null) {
            // Bestaande sneaker verwijderen uit systeem
            PersistenceSneaker.deleteSneaker(artikelnummer);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
