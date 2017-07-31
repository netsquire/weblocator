package up;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/")
public class Rest {
	
    @GET
    @Path("/empty")
    @Produces(MediaType.TEXT_PLAIN)
    public String emptyId() {
        return "Empty request";
    }

    @GET
    @Path("/info/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("id") String id) {
        return "Got it - " + id;
    }
}
