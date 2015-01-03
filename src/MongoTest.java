

import com.mongodb.BasicDBObject;





public class MongoTest {
	static String userName=null;
	static String password = null;
	
	public static void main( String args[] ){
	    try{   
	    	MongoDB mdb = new MongoDB("javauser_mdb",userName,password);
	     	BasicDBObject documentDetail = new BasicDBObject();
	     	documentDetail.put("Name", "Shankar");
	     	documentDetail.put("SName", "Sahu");
	     	documentDetail.put("Age", 33);
	     	documentDetail.put("Desig", "Doctor");
	     	documentDetail.put("Education", "MA");

	     	//Get data from document
	     	mdb.selectAllRecordsFromACollection();
	     	
	     	// Find first record form document
	     	mdb.selectFirstRecordsFromACollection();
	     	
	     	//Insert into db collection
	     	mdb.insertRecordToACollection(documentDetail);
	     	
	     	//Update Data 
	     	mdb.updateACollection(documentDetail);
	         
	      }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	   }
	
	
}
