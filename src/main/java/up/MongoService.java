package up;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoService {

	String dbName = "weblocator";
	static String collectionName = "grid";
	MongoClient mongoClient = null;
	MongoCollection<Document> collection;
	//static DB db = null;

	public MongoService() {
		dbName = System.getProperty("OPENSHIFT_APP_NAME");
		String username = System.getProperty("OPENSHIFT_MONGODB_DB_USERNAME");
		String password = System.getProperty("OPENSHIFT_MONGODB_DB_PASSWORD");
		String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
		String port = System.getenv("OPENSHIFT_MONGODB_DB_PORT");
		String dbName = System.getenv("OPENSHIFT_APP_NAME");

		MongoCredential credential = MongoCredential.createCredential(username, dbName, password.toCharArray());
		mongoClient = new MongoClient(new ServerAddress(host, 27017), Arrays.asList(credential));
		MongoDatabase db = mongoClient.getDatabase(dbName);
		collection = db.getCollection(collectionName);
	}

	public void putIp(String id, String ip) {
		Document ipDoc = new Document();
		ipDoc.put("id", id);
		ipDoc.put("ip", ip);
		collection.insertOne(ipDoc);
	}

	public static void main(String[] args) throws UnknownHostException {
		String mongoServer = "192.168.0.101";
		MongoClient ms = new MongoClient(mongoServer, 27017);
		
	/*	BasicDBObject query = new BasicDBObject();
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
		
		*/
	}
}
