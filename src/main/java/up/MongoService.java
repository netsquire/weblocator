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
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoService {

	String dbName = "weblocator";
	static String collectionName = "grid";
	MongoClient mongoClient = null;
	MongoCollection<Document> collection;
	MongoDatabase db = null;
	String uriString;

	public String getUriString(){ return uriString;}
	
	public MongoService() {
		dbName = System.getProperty("OPENSHIFT_APP_NAME");
		String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
		String dbName = System.getenv("OPENSHIFT_APP_NAME");

		uriString = "mongodb://admin:xcY_Iv7IZzRw@" + host + ":27017/" + dbName;
		MongoClientURI uri = new MongoClientURI(uriString);
		mongoClient = new MongoClient(uri);
		db = mongoClient.getDatabase(dbName);
		collection = db.getCollection(collectionName);
	}

	public void putIp(String id, String ip) {
		Document ipDoc = new Document();
		ipDoc.put("id", id);
		ipDoc.put("ip", ip);
		collection.insertOne(ipDoc);
	}
	
	public String list(){
		StringBuffer sb = new StringBuffer();
		FindIterable<Document> cursor = collection.find();
		MongoCursor<Document> it = cursor.iterator();
		while (it.hasNext()) {
			sb.append(it.next());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
	}

	public String getIp(String id) {
		Document query = new Document();
		query.put("id", id);
		Document res = collection.find(query).first();
		return res.get("ip").toString();
	}
}
