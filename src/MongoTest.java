
import org.bson.types.ObjectId;

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
	    	
	    	long dobDate = new java.util.Date("12 Oct 1985").getTime();
	    	long regDate = new java.util.Date().getTime();
	    	long lastDonationDate = new java.util.Date("1 Dec 2011").getTime();
	    	System.out.println(dobDate+" >> "+regDate);
	    	
	     	BasicDBObject documentDetail = new BasicDBObject();
	     	documentDetail.put("NAME", "Tikeshwar");
	     	documentDetail.put("SNAME", "Sahu");
	     	documentDetail.put("DOB", dobDate);
	     	documentDetail.put("SEX", "MALE");
	     	documentDetail.put("REG_DATE", regDate);
	     	documentDetail.put("LAST_DONATION_DATE", lastDonationDate);
	     	documentDetail.put("BLOOD_GROUP", "A+");
	     	documentDetail.put("EMAIL_ID", "tiku8nitrrraju@gmail.com");
	     	documentDetail.put("MOBILE_NO", "7871565242");
	     	documentDetail.put("STATE", "Chhattisgarh");
	     	documentDetail.put("DISTRICT", "Raipur");
	     	documentDetail.put("CITY", "Raipur");
	     	documentDetail.put("PIN_CODE", 492001);


	     	//Get data from document
	     	mdb.selectAllRecordsFromACollection();
	     	
	     	// Find first record form document
	     	//mdb.selectFirstRecordsFromACollection();
	     	
	     	//Insert into db collection
	        //mdb.insertRecordToACollection(documentDetail,"EMAIL_ID");
	     	
	     	//Update Data 
	     	BasicDBObject criteriaDocument = new BasicDBObject();
	     	criteriaDocument.put("EMAIL_ID", "tiku8nitrrraju@gmail.com");
	     	
	     	BasicDBObject setDBObject = new BasicDBObject();
	     	setDBObject.put("SNAME", "Ranjan");
	     	mdb.updateACollectionFieled(criteriaDocument, setDBObject,false,false);
	     	
	     	BasicDBObject updatedocumentDetail = new BasicDBObject();
	     	//updatedocumentDetail.put("SEX", "MALE");
	     	//Update Data and allow multiple update
	     	//mdb.updateACollection(updatedocumentDetail,documentDetail,true,false);
	     	
//	     	DBCursor dbcrsr = mdb.getCollection(documentDetail);
//	     	if(dbcrsr!=null){
//	     		while(dbcrsr.hasNext()){
//	     			System.out.println(">>"+dbcrsr.next());
//	     		}
//	     	}
	     	
	      
			//mdb.updateACollectionFieled(documentDetail, updatedocumentDetail, false, true);


//	     	Delete document based on if you know document id 
//	     	
	     	BasicDBObject query = new BasicDBObject();
	        query.put("_id", new ObjectId("54a954610364663d3debb1cc"));
	    	//mdb.deleteDocument(query);
	     	
	     	// Get total record in collection
	     	System.out.println("Total Record found in db :=>"+mdb.totalRecordCount);
	         
	      }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	   }
	
	
}
