package comments;

import java.util.Iterator;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.netty.util.CharsetUtil;

public class Comment {

	private boolean liked;
	private String text;
	private int likes;
	// private String postId;

	public void likeComment(String user, String postId) {

//		MongoClient mongoClient = new MongoClient("localhost", 27017);
//		DB database = mongoClient.getDB("Facebook");
//		DBCollection collection = database.getCollection("Comments");
//		// mongoClient.getDatabaseNames().forEach(System.out::println);
//		BasicDBObject searchQuery = new BasicDBObject();
//		searchQuery.put("User", user);
//		searchQuery.put("PostId", postId);
//		DBCursor cursor = collection.find(searchQuery);
//		long x = (long) cursor.next().get("likes") + 1;
//
//		BasicDBObject newDocument = new BasicDBObject();
//		newDocument.put("likes", x);
//
//		BasicDBObject updateObject = new BasicDBObject();
//		updateObject.put("$set", newDocument);
//
//		collection.update(searchQuery, updateObject);

		
		MongoClientURI uri = new MongoClientURI(
				"mongodb://dbUser:db12345678@cluster0-shard-00-00-xyoi4.mongodb.net:27017,cluster0-shard-00-01-xyoi4.mongodb.net:27017,cluster0-shard-00-02-xyoi4.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("Facebook");
		MongoCollection<Document> collection = database.getCollection("Comments");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("User", user);
		searchQuery.put("PostId", postId);
		FindIterable<Document> cursor = collection.find(searchQuery);
//		System.out.println(cursor.iterator().next());
		long x = (long)cursor.first().get("likes")+1;
		long y = x;
		Document doc = new Document("likes", x);
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", doc);
		collection.updateOne(searchQuery, updateObject);
		
		System.out.println("done");
		
	}

	public int getLikes() {
		return likes;
	}

	public void unlikeComment(String user, String postId) {

		liked = false;

//		MongoClient mongoClient = new MongoClient("localhost", 27017);
//		DB database = mongoClient.getDB("Facebook");
//		DBCollection collection = database.getCollection("Comments");
//		// mongoClient.getDatabaseNames().forEach(System.out::println);
//		BasicDBObject searchQuery = new BasicDBObject();
//		searchQuery.put("User", user);
//		searchQuery.put("PostId", postId);
//		DBCursor cursor = collection.find(searchQuery);
//		// long x =(long)cursor.next().get("likes");
//		long x = (long) cursor.next().get("likes");
		MongoClientURI uri = new MongoClientURI(
				"mongodb://dbUser:db12345678@cluster0-shard-00-00-xyoi4.mongodb.net:27017,cluster0-shard-00-01-xyoi4.mongodb.net:27017,cluster0-shard-00-02-xyoi4.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("Facebook");
		MongoCollection<Document> collection = database.getCollection("Comments");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("User", user);
		searchQuery.put("PostId", postId);
		FindIterable<Document> cursor = collection.find(searchQuery);
//		System.out.println(cursor.iterator().next());
		long x = (long)cursor.first().get("likes");
		
		if (x > 0) {
			// likes--;
			x--;
			Document doc = new Document("likes", x);
			BasicDBObject updateObject = new BasicDBObject();
			updateObject.put("$set", doc);
			collection.updateOne(searchQuery, updateObject);
		} else {
			System.out.println("likes = 0");
		}

	}

	public void setText(String user, String text, String postId) {

//		MongoClient mongoClient = new MongoClient("localhost", 27017);
//		DB database = mongoClient.getDB("Facebook");
//		DBCollection collection = database.getCollection("Comments");
//		BasicDBObject document = new BasicDBObject();
//		document.put("User", user);
//		document.put("Comment", text);
//		long x = 0;
//		document.put("likes", x);
//		document.put("PostId", postId);
//		collection.insert(document);
		
		MongoClientURI uri = new MongoClientURI(
				"mongodb://dbUser:db12345678@cluster0-shard-00-00-xyoi4.mongodb.net:27017,cluster0-shard-00-01-xyoi4.mongodb.net:27017,cluster0-shard-00-02-xyoi4.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("Facebook");
		MongoCollection<Document> collection = database.getCollection("Comments");
		
	//	BasicDBObject document = new BasicDBObject();
		long x = 0;
		Document doc = new Document("User", user)
				.append("Comment", text)
				.append("likes", x)
				.append("PostId", postId);
		collection.insertOne(doc);
//		document.put("User", user);
//		document.put("Comment", text);
//		document.put("likes", x);
//		document.put("PostId", postId);
	//	collection.insertOne(document);

	}

	public void getComments(String postId) {

		//MongoClient mongoClient = new MongoClient("localhost", 27017);
		//DB database = mongoClient.getDB("Facebook");
		
		System.out.println("in");
		MongoClientURI uri = new MongoClientURI(
				"mongodb://dbUser:db12345678@cluster0-shard-00-00-xyoi4.mongodb.net:27017,cluster0-shard-00-01-xyoi4.mongodb.net:27017,cluster0-shard-00-02-xyoi4.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("Facebook");
		MongoCollection<Document> collection = database.getCollection("Comments");

		// mongoClient.getDatabaseNames().forEach(System.out::println);
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("PostId", postId);
		FindIterable<Document> cursor = collection.find(searchQuery);
		
		for(Document doc : cursor){
			System.out.println(doc.get("User"));
			System.out.println(doc.toString());
		}
		
		// System.out.println(cursor.next());
	//	System.out.println(cursor.iterator().next());	
		//System.out.println(cursor.iterator());
//		while (cursor.iterator().hasNext()) {
//			System.out.println(cursor.iterator().next());
//		}
	}

	
	public JSONArray getCommentsJ(String postId) {

		//MongoClient mongoClient = new MongoClient("localhost", 27017);
		//DB database = mongoClient.getDB("Facebook");
		
		System.out.println("in");
		MongoClientURI uri = new MongoClientURI(
				"mongodb://dbUser:db12345678@cluster0-shard-00-00-xyoi4.mongodb.net:27017,cluster0-shard-00-01-xyoi4.mongodb.net:27017,cluster0-shard-00-02-xyoi4.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("Facebook");
		MongoCollection<Document> collection = database.getCollection("Comments");

		// mongoClient.getDatabaseNames().forEach(System.out::println);
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("PostId", postId);
		FindIterable<Document> cursor = collection.find(searchQuery);
		
	
		
		JSONArray ja = new JSONArray();
		
		for(Document doc : cursor){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("User",doc.get("User"));
			jsonObject.put("Comment",doc.get("Comment"));
			jsonObject.put("likes",doc.get("likes"));
			jsonObject.put("PostId",doc.get("PostId"));
			
			ja.put(jsonObject);
			System.out.println(doc.get("User"));
			System.out.println(doc.toString());
		}
		
		return ja;
		
		// System.out.println(cursor.next());
	//	System.out.println(cursor.iterator().next());	
		//System.out.println(cursor.iterator());
//		while (cursor.iterator().hasNext()) {
//			System.out.println(cursor.iterator().next());
//		}
	}
	
}
