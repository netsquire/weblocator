package up;

import java.util.HashMap;
import java.util.Map;

public class MemoryStore implements Holdable {


	private String uriString;
	private Map<String, String> locations;

	@Override
	public String getUriString(){ return uriString;}

	public MemoryStore() {	
		locations = new HashMap<String, String>();
	}


	@Override
	public void putIp(String id, String ip) {
		locations.put(id, ip);
	}
	
	@Override
	public String list(){
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}

	@Override
	public String getIp(String id) {
		return locations.get(id);
	}
}
