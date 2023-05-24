package webservices;

import model.Sneaker;

import javax.ws.rs.*;
import javax.json.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/sneakers")
public class SneakerResource {
    private List<Sneaker> sneakers = new ArrayList<>();

    // Test data
    public SneakerResource() {
        sneakers.add(new Sneaker(1, "Nike", "Zwart", 42, "Coole sneaker", 99.99, "sneaker1.jpg", 1));
        sneakers.add(new Sneaker(2, "Adidas", "Wit", 44, "Sportieve sneaker", 89.99, "sneaker2.jpg", 1));
    }

    @GET
    @Produces("application/json")
    public Response getAllSneakers() {
        return Response.ok(sneakers).build();
    }

    @POST
    @Consumes("application/json")
    public Response addSneaker(Sneaker sneaker) {
        sneakers.add(sneaker);
        return Response.status(Response.Status.CREATED).build();
    }
}
