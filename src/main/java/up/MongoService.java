package up;

import java.net.UnknownHostException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoService {

	String dbName = "weblocator";
	static String collectionName = "grid";
	MongoClient mongoClient = null;
	static DBCollection collection = null;
	static DB db = null;

	public MongoService() {
		dbName = System.getProperty("OPENSHIFT_APP_NAME");
		String username = System.getProperty("OPENSHIFT_MONGODB_DB_USERNAME");
		String password = System.getProperty("OPENSHIFT_MONGODB_DB_PASSWORD");
		String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
		String port = System.getenv("OPENSHIFT_MONGODB_DB_PORT");
		String dbName = System.getenv("OPENSHIFT_APP_NAME");
		try {
			mongoClient = new MongoClient(host, 27017);
			db = mongoClient.getDB(dbName);
			collection = db.getCollection(collectionName);
		} catch (UnknownHostException e) { e.printStackTrace();}
	}

	public void putIp(String id, String ip) {
		BasicDBObject ipDoc = new BasicDBObject();
		ipDoc.put("id", id);
		ipDoc.put("ip", ip);
		collection.insert(ipDoc);
	}

	public static void main(String[] args) throws UnknownHostException {
		String mongoServer = "192.168.0.101";
		MongoClient ms = new MongoClient(mongoServer, 27017);
		db = ms.getDB("weblocator");
		collection = db.getCollection(collectionName);
		BasicDBObject query = new BasicDBObject();
		query.put("", "");
		DBCursor cursor = collection.find(null);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		//putIp("id", "ip");
		//putIp("ip", "127.0.0.1");
		System.out.println(collection.getFullName() + " has " + collection.count() + " documents inside.");
		DBObject dbo = collection.findOne();
		System.out.println(dbo);
		
		
	}
}
