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
        if (sc != null) {
            if (sc.getUserPrincipal() instanceof User) {
                User user = (User) sc.getUserPrincipal();

                JsonObjectBuilder job = Json.createObjectBuilder()
                        .add("role", user.getRole());

                return job.build().toString();
            }
        }

        return Json.createObjectBuilder().add("error", "role not found").build().toString();
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(RegisterRequest registerRequest) {
        String username = registerRequest.username;
        String password = registerRequest.password;
        String email = registerRequest.email;

        if (User.getUserByName(username) != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Gebruikersnaam al in gebruik").build();
        }

        Klant klant = new Klant(username, password, "klant", User.generateId(), email);
        PersistenceKlant.saveKlant(klant);

        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/klanten")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllKlanten() {
        ArrayList<Klant> klanten = PersistenceKlant.loadAllKlanten();
        return Response.status(Response.Status.OK).entity(klanten).build();
    }

    @GET
    @Path("/profile")
    @RolesAllowed({"klant", "admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public String getProfile(@Context SecurityContext sc) {
        if (sc.getUserPrincipal() instanceof User) {
            User current = (User) sc.getUserPrincipal();
            return Json.createObjectBuilder()
                    .add("username", current.getName())
                    .add("password", current.getPassword())
                    .build()
                    .toString();
        }
        return Json.createObjectBuilder()
                .add("error", "Something went wrong")
                .build()
                .toString();
    }
}
