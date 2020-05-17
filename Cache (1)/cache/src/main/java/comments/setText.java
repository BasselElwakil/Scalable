package comments;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
import com.mongodb.MongoClient;


public class setText implements Command{

	Comment comment;
	String user;
	String text;
	String postId;
	
	
	
	public setText(Comment comment,String user,String text, String postId) {
		this.comment = comment;
		this.user = user;
		this.text = text;
		this.postId = postId;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

		comment.setText(user, text, postId);
		
		//System.out.println("in");
	}

}
