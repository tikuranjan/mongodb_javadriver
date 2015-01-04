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

	public  MongoDB( String dbname, String collectioName, String userName, String password, int port ) {
		try {
			
			// Getting mongoClient object for the specified port on localhost
			this.mongoClient 	  = new MongoClient( "localhost", port);
			
			// Getting database object 
			this.db 			  = mongoClient.getDB( dbname );
			
			// Getting authentication for the perticula user on specified password
			boolean auth 		  = db.authenticate( userName, password.toCharArray() );
			
			// Getting collection 
			this.collection 	  = db.getCollection( collectioName );

			// getting total number of document in collection
			this.totalRecordCount = this.collection.count();
			
			System.out.println("MongoDb instace created successfull");
		} catch (Exception e) {

		}
	}

	
	public  MongoDB( String dbname, String collectioName, String userName, String password ) {
		try {
			
			// Getting mongoClient object for the specified port on localhost
			this.mongoClient 	  = new MongoClient( "localhost" );
			
			// Getting database object 
			this.db 			  = mongoClient.getDB( dbname );
			
			// Getting authentication for the perticula user on specified password
			boolean auth 		  = db.authenticate( userName, password.toCharArray() );
			
			// Getting collection 
			this.collection 	  = db.getCollection( collectioName );

			// getting total number of document in collection
			this.totalRecordCount = this.collection.count();
			
			System.out.println("MongoDb instace created successfull");
		} catch (Exception e) {

		}
	}
	
	//INSERT RECORD
	public ObjectId insertRecordToACollection( BasicDBObject document, String primaryKeyField ) {
		ObjectId id = null;
		try {
			if( primaryKeyField!=null ){
				DBObject criteria = new QueryBuilder().put( primaryKeyField ).is( document.getString(primaryKeyField ) ).get();
				// If the data is already found we will not insert it again
				if (collection.findOne( criteria ) == null) {
					collection.insert( document );
					id = ( ObjectId ) document.get( "_id" );
					System.out.println("row inserted " + id);
					this.totalRecordCount=this.totalRecordCount+1;
					return id;
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
		while( cursor.hasNext() ) {
            DBObject o = cursor.next();
            System.out.println(i+":"+o);    
		}
		return cursor;
	}
	
	
	//Get collection based on criteria
	public static DBCursor getCollection( DBObject criteria )
	{
		DBCursor cursor =null;
		if(criteria!=null){
			cursor = collection.find( criteria );
		}
		return cursor;
				
	}
	
	
	// Select first record frm collection and return DBObject
	public DBObject selectFirstRecordsFromACollection () {
		System.out.println(collection.findOne());
		return collection.findOne();
	}

	// Update a Collection and return object Id 
	public ObjectId updateACollection( BasicDBObject document,BasicDBObject criteriaDocument, boolean upsert, boolean multi ) {
		try {
			ObjectId objId = null;
			System.out.println("criteriaDocument>>"+criteriaDocument);
//			BSONObject docObject = collection.findOne( criteriaDocument );
			collection.update( criteriaDocument, document, upsert, multi );
			return null;

		} catch (Exception e) {
			System.out.println(" updateACollection Exception ocuured >>>>>" + e);
		}
		return null;
	}
	public void updateACollectionFieled( BasicDBObject criteriaDocument,BasicDBObject setDBObject, boolean upsert, boolean multi){ 
		BasicDBObject _setBObject = new BasicDBObject("$set", setDBObject);
		this.collection.update(criteriaDocument, _setBObject,false,false);
	}
	// Create a collection and return it . 
	public DBCollection createCollection( String collectionName ) {
		DBObject options = BasicDBObjectBuilder.start().add( "capped", true ).add( "size", 2000000000l ).get();
		this.collection = db.createCollection( collectionName, options );
		return this.collection;
	}
	
	//Delete Document 
	public void deleteDocument( BasicDBObject document ){
	 this.collection.remove(document);
	}

}
