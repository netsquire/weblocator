package up;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoService implements Holdable {

	String dbName = "weblocator";
	static String collectionName = "grid";
	MongoClient mongoClient = null;
	MongoCollection<Document> collection;
	MongoDatabase db = null;
	String uriString;

	/* (non-Javadoc)
	 * @see up.Holdable#getUriString()
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see up.Holdable#putIp(java.lang.String, java.lang.String)
	 */
	@Override
	public void putIp(String id, String ip) {
		Document ipDoc = new Document();
		ipDoc.put("id", id);
		ipDoc.put("ip", ip);
		collection.insertOne(ipDoc);
	}
	
	/* (non-Javadoc)
	 * @see up.Holdable#list()
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see up.Holdable#getIp(java.lang.String)
	 */
	@Override
	public String getIp(String id) {
		Document query = new Document();
		query.put("id", id);
		Document res = collection.find(query).first();
		return res.get("ip").toString();
	}
}
