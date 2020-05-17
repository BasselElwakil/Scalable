package comments;

public class unlikeCommentCommand implements Command {

	Comment comment;
	String user;
	String postId;

	public unlikeCommentCommand(Comment comment,String user, String postId) {
		this.comment = comment;
		this.user = user;
		this.postId = postId;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		comment.unlikeComment(user, postId);
		System.out.println("UnLiked Comment");
	}
	
	
}
