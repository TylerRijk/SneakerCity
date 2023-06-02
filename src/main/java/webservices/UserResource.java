package webservices;

import security.User;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

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
}
