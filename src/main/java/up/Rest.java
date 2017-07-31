package up;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Rest {

	MongoService mongoService;
	public Rest() {
		mongoService = new MongoService();
	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String index() {
		String dbName = System.getenv("OPENSHIFT_APP_NAME");
		return "request: /info/{id} " + dbName;
	}

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

	@GET
	@Path("/{id}/ip/{ip}")
	@Produces(MediaType.TEXT_PLAIN)
	public String ipAnnounce(@PathParam("id") String id, @PathParam("ip") String ip) {
		mongoService.putIp(id, ip);
		return "Got it - " + id;
	}
}
