package webservices;

import helpers.SneakerRequest;
import model.Sneaker;
import persistence.PersistenceSneaker;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

@Path("/sneakers")
public class SneakerResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSneakers() {
        ArrayList<Sneaker> sneakers = PersistenceSneaker.loadAllSneakers();

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
        Sneaker sneaker = PersistenceSneaker.loadSneaker(artikelnummer);
        if (sneaker != null) {
            return Response.status(Response.Status.OK).entity(sneaker).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSneaker(SneakerRequest sneakerRequest) {
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

        ArrayList<Sneaker> sneakers = PersistenceSneaker.loadAllSneakers();

        for (Sneaker sneaker : sneakers) {
            if (sneaker.getArtikelnummer() == sneakerRequest.artikelnummer) {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }

        PersistenceSneaker.saveSneaker(new Sneaker(sneakerRequest.artikelnummer, sneakerRequest.merk, sneakerRequest.kleur, sneakerRequest.maat, sneakerRequest.beschrijving, sneakerRequest.prijs, sneakerRequest.image, sneakerRequest.voorraad));
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{artikelnummer}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSneaker(@PathParam("artikelnummer") int artikelnummer, Sneaker updatedSneaker) {
        Sneaker sneaker = PersistenceSneaker.loadSneaker(artikelnummer);
        if (sneaker != null) {
            sneaker.setMerk(updatedSneaker.getMerk());
            sneaker.setKleur(updatedSneaker.getKleur());
            sneaker.setMaat(updatedSneaker.getMaat());
            sneaker.setBeschrijving(updatedSneaker.getBeschrijving());
            sneaker.setPrijs(updatedSneaker.getPrijs());
            sneaker.setImage(updatedSneaker.getImage());
            sneaker.setVoorraad(updatedSneaker.getVoorraad());

            PersistenceSneaker.saveSneaker(sneaker);

            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // DELETE EN PUT WERKT NOG NIET
    @DELETE
    @Path("/{artikelnummer}")
    public Response deleteSneaker(@PathParam("artikelnummer") int artikelnummer) {
        Sneaker sneaker = PersistenceSneaker.loadSneaker(artikelnummer);
        if (sneaker != null) {
            File file = new File(PersistenceSneaker.getPersistenceDirectory(artikelnummer));
            if (file.exists()) {
                file.delete();
            }

            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
