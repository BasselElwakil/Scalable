package com.mongodb.quickstart;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class HelloMongoDB {

    public static void main(String[] args) {
    		
    	MongoClient mongoClient = new MongoClient("localhost", 27017);
    	DB database = mongoClient.getDB("Facebook");
    	DBCollection collection = database.getCollection("Comments");
    //	mongoClient.getDatabaseNames().forEach(System.out::println);
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.put("User", "Bassel");
    	DBCursor cursor = collection.find(searchQuery);
    	System.out.println((long)cursor.next().get("likes")+1);
    	//System.out.println(cursor.next());
    	while (cursor.hasNext()) {
    	    //System.out.println(cursor.next());
    	}
    }
}