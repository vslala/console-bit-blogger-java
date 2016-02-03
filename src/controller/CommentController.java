package controller;

import model.Comment;
import model.User;
import util.Utility;
import util.Validation;

public class CommentController extends Comment{
	/*
	 * ------------------------------------------------------------------------------------
	 * Add Comment
	 * ------------------------------------------------------------------------------------
	 */
	public void addCommentToBlog(){
		while (true){
			try {
				Utility.instructions();
				System.out.print("Comment: ");
				String comment = Utility.multilineInput();
				
				if (Validation.length(comment, 1000)){
					super.addComment(BlogController.temp_id, User.user_id, comment);
					break;
				} else{
					System.out.println("Only Alphabets are allowed!");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
