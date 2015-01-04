
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class MongoTest {
	
    
	//Please provide following details to work on your mongo db 
	static String userName		="null";
	static String password 		= "null";
	static String databaseName 	= "null";
	static String collectioName = "null";
	static int port				= 27017;
	public static void main( String args[] ){
	    try{   
	    	MongoDB mdb = new MongoDB(databaseName,collectioName,userName,password);
	     	BasicDBObject documentDetail = new BasicDBObject();
//	     	documentDetail.put("Name", "Kalyan Kumar");
//	     	documentDetail.put("SName", "Sahu");
//	     	documentDetail.put("Age", 27);
//	     	documentDetail.put("Desig", "Agriculture");
//	     	documentDetail.put("Education", "Middle");


	     	//Get data from document
	     	//mdb.selectAllRecordsFromACollection();
	     	
	     	// Find first record form document
	     	//mdb.selectFirstRecordsFromACollection();
	     	
	     	//Insert into db collection
	     	//mdb.insertRecordToACollection(documentDetail,"Name");
	     	
	     	//Update Data 
	     	//mdb.updateACollection(documentDetail,"name",false,false);
	     	
	     	//Update Data and allow multiple update
	     	//mdb.updateACollection(documentDetail,"Name",true,false);
	     	
	     	DBCursor dbcrsr = mdb.getCollection(documentDetail);
	     	if(dbcrsr!=null){
	     		while(dbcrsr.hasNext()){
	     			System.out.println(">>"+dbcrsr.next());
	     		}
	     	}

//	     	Delete document based on criteria
//	     	BasicDBObject document = new BasicDBObject();
//	    	document.put("Name", "Kalyan Kumar");
//	    	mdb.deleteDocument(document);
//	    	mdb.selectAllRecordsFromACollection();
	     	
	     	// Get total record in collection
	     	System.out.println("Total Record found in db :=>"+mdb.totalRecordCount);
	         
	      }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	   }
	
	
}
