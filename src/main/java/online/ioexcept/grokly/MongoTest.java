package online.ioexcept.grokly;


import com.mongodb.BasicDBObject;
import com.mongodb.DB; 
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;

public class MongoTest {

	public static void main(String[] args) {
		MongoClient mongoClient = null;
		try {
//			mongoClient = new MongoClient();
//			mongoClient = new MongoClient("gorkly", 27017);
//			mongoClient = new MongoClient("gorkly");
			mongoClient = new MongoClient("144.229.218.198");
			// or, to connect to a replica set, supply a seed list of members
			//MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
			//                                    new ServerAddress("localhost", 27018),
			//                                  new ServerAddress("localhost", 27019)));
	
			DB db = mongoClient.getDB("grokly");
			
			
			
			DBCollection coll = db.getCollection("links");
			List<BasicDBObject> tags = new ArrayList<BasicDBObject>();
			Multiset<String> hashTags = TreeMultiset.create();
			hashTags.add("#fake");
			hashTags.add("#tags");			
//			tags.add(new DB)
			BasicDBObject newURL = new BasicDBObject("ip", "127.0.0.1").append("user", "fake-user").append("timestamp", new Date());
			newURL.append("title","fake-title").append("description","fake-description").append("url","fake-url").append("tags",hashTags);

			//System.out.println("Data Display");
			coll.insert(newURL);
			DBCursor cursor = coll.find();
			try {
				while (cursor.hasNext()) {
					System.out.println(cursor.next());
				}
			} finally {
				//mongoClient.dropDatabase("test");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}

}
