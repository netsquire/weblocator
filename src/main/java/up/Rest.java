package up;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Rest {

	MongoService mongoService = new MongoService();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String index() {
		String dbName = System.getenv("OPENSHIFT_APP_NAME");
		String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
		return "request: /info/{id} " + dbName + "@" + host;
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
		return "Information about:- " + id;
	}

	/**
	 * Stores IP of client with ID
	 * 
	 * @param id - ID of client
	 * @param ip - IP to be stored
	 * @return
	 */
	@GET
	@Path("/{id}/ip/{ip}")
	@Produces(MediaType.TEXT_PLAIN)
	public String putIp(@PathParam("id") String id, @PathParam("ip") String ip) {
		mongoService.putIp(id, ip);
		return "OK";
	}
	
	@GET
	@Path("/{id}/ip")
	@Produces(MediaType.TEXT_PLAIN)
	public String getIp(@PathParam("id") String id) {
		return mongoService.getIp(id);
	}
	
	@GET
	@Path("/dump")
	@Produces(MediaType.TEXT_PLAIN)
	public String dump() {
		return "Got it - " + System.getProperty("line.separator") 
				+ mongoService.list();
	}
}
