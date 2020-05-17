package comments;



public class LikeCommentCommand implements Command{

	
	Comment comment;
	String user;
	String postId;

	public LikeCommentCommand(Comment comment, String user, String postId) {
		this.comment = comment;
		this.user = user;
		this.postId = postId;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		comment.likeComment(user, postId);
		
		
    	
    	
    	
		System.out.println("Liked Comment");
		
		
	}
	
	
	
	
}
