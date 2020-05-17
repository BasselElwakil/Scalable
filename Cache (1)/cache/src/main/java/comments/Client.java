package comments;

import org.json.JSONArray;
import org.json.JSONObject;

public class Client {

	static String user;
	static String text;
	static String postId;
	
	
	public Client(String user, String text, String postId) {
		this.user = user;
		this.text = text;
		this.postId = postId;
	}

	


	public Client(String user2, String postId2) {
		// TODO Auto-generated constructor stub
		this.user = user2;
		this.postId = postId2;
	}


	public Client(String postId2) {
		// TODO Auto-generated constructor stub
		this.postId = postId2;
	}



	public static void main(String[] args) {
		
//		Invoker invoker = new Invoker();
//		
//		Comment comment = new Comment();
//		
//		Command likeC = new LikeCommentCommand(comment, user);
//		Command unlikeC = new unlikeCommentCommand(comment, user);
//		
//		Command getCom = new GetPostComments(comment, postId);
//		
//		Command setT = new setText(comment,user,text,postId);
//		
//		invoker.setCommand(setT);
//		invoker.executeAction();
		
		//invoker.setCommand(setT);
		
		//invoker.executeAction();
		
		//invoker.setCommand(likeC);
		
		//invoker.executeAction();
		
		
	//	invoker.setCommand(unlikeC);
		
	//	invoker.executeAction();
		
	}
	
	public void runInsert(){
		Invoker invoker = new Invoker();
		
		Comment comment = new Comment();
	
		
		Command setT = new setText(comment,user,text,postId);
		
		invoker.setCommand(setT);
		invoker.executeAction();
	}


	public void runLike() {
		
		Invoker invoker = new Invoker();
		
		Comment comment = new Comment();
		
		Command likeC = new LikeCommentCommand(comment, user, postId);
		
		invoker.setCommand(likeC);
		invoker.executeAction();
	}


	public void runUnlike() {
	
		Invoker invoker = new Invoker();
		
		Comment comment = new Comment();
		
		
		Command unlikeC = new unlikeCommentCommand(comment, user, postId);

		invoker.setCommand(unlikeC);
		invoker.executeAction();
	}
	
	public void runGetCom(){
		
		Invoker invoker = new Invoker();
		
		Comment comment = new Comment();
		
		
		
		Command getCom = new GetPostComments(comment, postId);
		
		
		invoker.setCommand(getCom);
		invoker.executeAction();
		
	}
	
	public JSONArray runGetComJ(){
		
	
		
		Comment comment = new Comment();
		
		
		
		Command getCom = new GetPostComments(comment, postId);
		
		GetPostComments getJ = new GetPostComments(comment, postId);
		
		
		
		return getJ.getComments();

		
	}
	
	

}
