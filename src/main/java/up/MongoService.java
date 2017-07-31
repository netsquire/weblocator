package up;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoService {

	String dbName = "weblocator";
	String collectionName = "grid";
	MongoClient mongoClient = null;
	DBCollection collection = null;
	
	public MongoService(){
		
		try {
			mongoClient = new MongoClient("127.0.0.1", 27017);
			collection = mongoClient.getDB(dbName).getCollection("collectionName");
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
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
