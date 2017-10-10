package up;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Rest {

	Holdable storageService = new MemoryStore();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String index() {
		return "Head request";
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
		return "Information about: " + id + storageService.getIp(id);
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
		storageService.putIp(id, ip);
		return "OK";
	}
	
	@GET
	@Path("/{id}/ip")
	@Produces(MediaType.TEXT_PLAIN)
	public String getIp(@PathParam("id") String id) {
		return storageService.getIp(id);
	}
	
	@GET
	@Path("/dump")
	@Produces(MediaType.TEXT_PLAIN)
	public String dump() {
		return "Got it - " + System.getProperty("line.separator") 
				+ storageService.list();
	}
}
