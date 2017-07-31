package up;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoService {

	String dbName = "weblocator";
	String collectionName = "grid";
	MongoClient mongoClient = null;
	DBCollection collection = null;
	DB db = null;
	
	public MongoService() throws Exception{
		dbName = System.getProperty("OPENSHIFT_APP_NAME");
		String username = System.getProperty("OPENSHIFT_MONGODB_DB_USERNAME");
		String password = System.getProperty("OPENSHIFT_MONGODB_DB_PASSWORD");
		try {
			mongoClient = new MongoClient("127.0.0.1", 27017);
			db = mongoClient.getDB(dbName);
			collection = db.getCollection(collectionName);
			if(db.authenticate(username, password.toCharArray())){
				System.out.println("Auth is OK.");
				}
			else {
				System.out.println("AUTH IS NOT OK.");
			}
		} catch (UnknownHostException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	public void putIp(String id, String ip){
		BasicDBObject ipDoc = new BasicDBObject();
		ipDoc.put("id", id);
		ipDoc.put("ip", ip);		
		collection.insert(ipDoc);
	}
	
	public void put(String info){}
	
	public void put(BasicDBObject info){}
}
