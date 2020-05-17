package comments;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetPostComments implements Command{

	Comment comment;
	String postId;

	public GetPostComments(Comment comment, String postId) {
		this.comment = comment;
		this.postId = postId;
	}

	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		comment.getComments(postId);
	}
	
	public JSONArray getComments(){
		
		
		
		return comment.getCommentsJ(postId);
		
	}
	
	
}
