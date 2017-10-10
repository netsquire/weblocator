package up;

public interface Holdable {

	/**
	 * 
	 * @return
	 */
	String getUriString();

	/**
	 * 
	 * @param id
	 * @param ip
	 */
	void putIp(String id, String ip);

	/**
	 * 
	 * @return
	 */
	String list();

	/**
	 * 
	 * @param id
	 * @return
	 */
	String getIp(String id);

}