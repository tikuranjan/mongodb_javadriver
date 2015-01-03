import org.bson.BSONObject;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class MongoDB {
	public static MongoClient mongoClient = null;
	public static DB db = null;
	public static DBCollection collection = null;
	public static long totalRecordCount = 0;

	public MongoDB(String dbname, String collection, String userName,
			String password) {
		try {
			this.mongoClient = new MongoClient("localhost", 27017);
			this.db = mongoClient.getDB(dbname);
			boolean auth = db.authenticate(userName, password.toCharArray());
			this.collection = db.createCollection(collection, null);
			this.totalRecordCount = this.collection.count();
			System.out.println("MongoDb instace created successfull");
		} catch (Exception e) {

		}
	}

	
	//INSERT RECORD
	public ObjectId insertRecordToACollection(BasicDBObject document) {
		ObjectId id = null;
		try {
			DBObject criteria = new QueryBuilder().put("Name")
					.is(document.getString("Name")).get();
			// If the data is already found we will not insert it again
			if (collection.findOne(criteria) == null) {
				collection.insert(document);
				id = (ObjectId) document.get("_id");
				System.out.println("row inserted " + id);
				return id;
			} else {
				System.out.println(collection.findOne(criteria).get("_id")
						+ " Found duplicate :" + collection.findOne());
			}
		} catch (Exception e) {
			System.out.println("Exception while inserting data  " + e);
			return id;
		}
		return id;

	}
	
	
	
	// Select All Record and retrun Cursor
	public DBCursor selectAllRecordsFromACollection() {
		DBCursor cursor = this.collection.find();
		return cursor;
	}
	
	
	
	// Select first record frm collection and return DBObject
	public DBObject selectFirstRecordsFromACollection() {
		return collection.findOne();
	}

	// Update a Collection and return object Id 
	public ObjectId updateACollection(BasicDBObject document) {
		try {
			DBObject criteria = new QueryBuilder().put("Name")
					.is(document.getString("Name")).get();
			// collection.findOne(criteria).get("_id");
			BSONObject docObject = collection.findOne(criteria);
			if (docObject != null) {
				ObjectId objId = new ObjectId(docObject.get("_id").toString());
				criteria.put("_id", objId);
				collection.update(criteria, document);
				return objId;

			} else {
				System.out.println("No entry found so can be neew >>>>>");
				collection.insert(document);
				return (ObjectId) document.get("_id");
			}
		} catch (Exception e) {
			System.out.println("Exception ocuured >>>>>" + e);
		}
		return null;
	}
	// Create a collection and return it . 
	public DBCollection createCollection(String name) {
		DBObject options = BasicDBObjectBuilder.start().add("capped", true)
				.add("size", 2000000000l).get();
		this.collection = db.createCollection(name, options);
		return this.collection;
	}

}
