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

	public MongoDB(String dbname, String collection, String userName,String password,int port) {
		try {
			this.mongoClient = new MongoClient("localhost", port);
			this.db = mongoClient.getDB(dbname);
			boolean auth = db.authenticate(userName, password.toCharArray());
			this.collection = db.createCollection(collection, null);
			this.totalRecordCount = this.collection.count();
			System.out.println("MongoDb instace created successfull");
		} catch (Exception e) {

		}
	}

	
	//INSERT RECORD
	public ObjectId insertRecordToACollection(BasicDBObject document, String primaryKeyField) {
		ObjectId id = null;
		try {
			if(primaryKeyField!=null){
				DBObject criteria = new QueryBuilder().put(primaryKeyField).is(document.getString(primaryKeyField)).get();
				// If the data is already found we will not insert it again
				if (collection.findOne(criteria) == null) {
					collection.insert(document);
					id = (ObjectId) document.get("_id");
					System.out.println("row inserted " + id);
					return id;
				} else {
					this.updateACollection(document, primaryKeyField, true, false);
					System.out.println(collection.findOne(criteria).get("_id")+ " Found duplicate :" + collection.findOne());
				}
			}else{
				System.out.println("Please provide a primary key filed to avoid duplicate");
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
		
		int i = 0;
		while(cursor.hasNext()) {
            DBObject o = cursor.next();
            System.out.println(i+":"+o);    
		}
		
		return cursor;
	}
	
	
	
	// Select first record frm collection and return DBObject
	public DBObject selectFirstRecordsFromACollection() {
		System.out.println(collection.findOne());
		return collection.findOne();
	}

	// Update a Collection and return object Id 
	public ObjectId updateACollection(BasicDBObject document,String criteriaFiled, boolean upsert, boolean multi) {
		try {
			ObjectId objId = null;
			DBObject criteria = new QueryBuilder().put(criteriaFiled).is(document.getString(criteriaFiled)).get();
			BSONObject docObject = collection.findOne(criteria);
			if (docObject != null) {

				objId = new ObjectId(docObject.get("_id").toString());
				criteria.put("_id", objId);
			}
			collection.update(criteria, document, upsert, multi);
			return new ObjectId(docObject.get("_id").toString());

		} catch (Exception e) {
			System.out.println("Exception ocuured >>>>>" + e);
		}
		return null;
	}
	// Create a collection and return it . 
	public DBCollection createCollection(String name) {
		DBObject options = BasicDBObjectBuilder.start().add("capped", true).add("size", 2000000000l).get();
		this.collection = db.createCollection(name, options);
		return this.collection;
	}
	
	//

}
