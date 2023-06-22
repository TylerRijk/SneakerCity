package webservices;

import helpers.RegisterRequest;
import model.Klant;
import persistence.PersistenceKlant;
import model.User;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;

@Path("/user")
public class UserResource {

    @GET
    @RolesAllowed({"admin", "klant"})
    @Path("/role")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRole(@Context SecurityContext sc) {
        // Controleren of de SecurityContext niet leeg is
        if (sc != null) {
            // Controleren of huidige gebruiker een User object is
            if (sc.getUserPrincipal() instanceof User) {
                // Cast de huidige gebruiker naar een User object
                User user = (User) sc.getUserPrincipal();

                // Json terugsturen met daarin de rol van huidige gebruiker
                JsonObjectBuilder job = Json.createObjectBuilder()
                        .add("role", user.getRole());

                return job.build().toString();
            }
        }

        // Als er geen rol gevonden is, verzend een foutmelding
        return Json.createObjectBuilder().add("error", "role not found").build().toString();
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(RegisterRequest registerRequest) {
        // Gegevens ophalen uit request
        String username = registerRequest.username;
        String password = registerRequest.password;
        String email = registerRequest.email;

        // Controleren of username al bestaat
        if (User.getUserByName(username) != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Gebruikersnaam al in gebruik").build();
        }

        // Nieuwe klant aanmaken en opslaan in systeem
        Klant klant = new Klant(username, password, "klant", User.generateId(), email);
        PersistenceKlant.saveKlant(klant);

        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/klanten")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllKlanten() {
        // Alle klanten ophalen uit systeem
        ArrayList<Klant> klanten = PersistenceKlant.loadAllKlanten();
        return Response.status(Response.Status.OK).entity(klanten).build();
    }

    @GET
    @Path("/profile")
    @RolesAllowed({"klant", "admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public String getProfile(@Context SecurityContext sc) {
        // Controleren of huidige gebruiker een User object is
        if (sc.getUserPrincipal() instanceof User) {
            // Cast de huidige gebruiker naar een User object
            User current = (User) sc.getUserPrincipal();

            // Json terugsturen met daarin username en password van huidige gebruiker
            return Json.createObjectBuilder()
                    .add("username", current.getName())
                    .add("password", current.getPassword())
                    .build()
                    .toString();
        }
        // Als er iets misgaat, verzend een foutmelding
        return Json.createObjectBuilder()
                .add("error", "Something went wrong")
                .build()
                .toString();
    }
}
