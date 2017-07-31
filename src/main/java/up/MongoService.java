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

	public MongoService() {
		dbName = System.getProperty("OPENSHIFT_APP_NAME");
		String username = System.getProperty("OPENSHIFT_MONGODB_DB_USERNAME");
		String password = System.getProperty("OPENSHIFT_MONGODB_DB_PASSWORD");
		String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
		String port = System.getenv("OPENSHIFT_MONGODB_DB_PORT");
		String dbName = System.getenv("OPENSHIFT_APP_NAME");

		try {
			mongoClient = new MongoClient("mongodb://admin:xcY_Iv7IZzRw@" + host + "27017/" + dbName);
			db = mongoClient.getDB(dbName);
			collection = db.getCollection(collectionName);
		} catch (UnknownHostException e) {
			// System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void putIp(String id, String ip) {
		BasicDBObject ipDoc = new BasicDBObject();
		ipDoc.put("id", id);
		ipDoc.put("ip", ip);
		collection.insert(ipDoc);
	}

	public void put(String info) {
	}

	public void put(BasicDBObject info) {
	}
}
