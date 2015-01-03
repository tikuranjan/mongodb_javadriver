import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoURI;


public class MongoDriverClass {
	
	//mongodb://<dbuser>:<dbpassword>@ds043210.mongolab.com:43210/mingodblab-tiku
	public static void main( String args[] ){
		try{
			
			
			
			
			
			String textUri = "mongodb://tiku8nitrr:rajushau1210@ds043210.mongolab.com:43210/mingodblab-tiku";
			MongoURI uri = new MongoURI(textUri);
			Mongo mongo  = new Mongo(uri);
			DB db 		 = mongo.getDB("mingodblab-tiku");
			//boolean auth = 	db.authenticate("tiku8nitrr","rajushau1210".toCharArray());
			BasicDBObject document = new BasicDBObject();
			document.put("name", "Raju");
			db.getCollection("blood_bank").insert(document);
			System.out.println(" Login is successful!"+(ObjectId)document.get( "_id" ));
				
			
//				DBCollection collection = db.getCollection("blood_bank");
//				BasicDBObject document = new BasicDBObject();
//				document.put("name", "Raju");
//				collection.insert(document);
//				System.out.println("Login is successful!"+(ObjectId)document.get( "_id" ));
//				//System.out.println("Login is successful!"+collection.count());
//				
//				System.out.println("auth >>>>"+auth);
//				DBObject dbObject = collection.findOne();
			
	}
		catch(Exception e){
			System.out.println(e);
			
		}
	}

}
