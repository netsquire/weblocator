package up;

import com.mongodb.DB;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;

public class MongoService {

	String dbName = "grid";
	MongoService mongo = new MongoService();
	MongoClient client;
	
	public MongoService(){
		
		try {
			client = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void putIp(String id, String ip){
		BasicDBObject ipDoc = new BasicDBObject();
		ipDoc.put("id", id);
		ipDoc.put("ip", ip);
		mongo.client.getDB(dbName).getCollection("user").insert(ipDoc);
	}
	
	public void put(String info){}
	
	public void put(BasicDBObject info){}
}
