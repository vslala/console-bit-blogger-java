package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.jdbc.Util;

import controller.BlogController;
import controller.CommentController;
import controller.UserController;
import model.User;
import util.Utility;
import util.Validation;

public class Menu {
	private static int ch;
	private int blogId = 0;
	UserController uc = new UserController();
	BlogController bc = new BlogController();
	CommentController cc = new CommentController();
	SubMenu submenu = new SubMenu();
	Scanner in = new Scanner(System.in);
	/*
	 *|-----------------------------------------------------------------------------------|
	 *| All the view based callings
	 *|-----------------------------------------------------------------------------------|
	 */
	Menu(){
		this.home();
	}

	public void home(){
		submenu.welcome();
		while (true){
			submenu.IHome();
			try{
				this.getChoice();

				switch (ch){
				case 1:
					if(User.isLogged)
					{
						System.out.println("enter valid choice");
						break;
					}
					// check login
					uc.loginUser();
					break;
					/* 
					 * after login sub-menu
					 */
				case 11:
					// compose and save blog
					bc.saveBlog(); 
					break;
				// Show User's Blog
				case 12:
					int flag = bc.showBlogsByAuthorId(); // show all blogs of the logged in user
					// if no blogs found then break
					if (flag == 0)
						break;
					blogId = bc.selectBlogIdDisplay();
					bc.showSingleBlog(blogId);
					submenu.__IUserEdit();
					
					this.getChoice();
					/*
					 * Update Sub-sub-menu
					 */
					switch (ch){
					case 121:
						// Update Blog Sub-menu
						submenu.___IUserUpdate();
						this.getChoice();

						switch (ch){
						case 1111:
							if (! User.isLogged)
								break;
							// Update Blog Title
							Utility.instructions();
							bc.updateBlogByTitle();
							break;
						case 1112:
							if (! User.isLogged)
								break;
							// Update Blog Summary
							Utility.instructions();
							bc.updateBlogBySummary();
							break;
						case 1113:
							if (! User.isLogged)
								break;
							// Update Blog Content
							Utility.instructions();
							bc.updateBlogByContent();
							break;
						default :
							System.out.println("Invalid Input!");
							break;
						}
						break;
					case 122:
						if (! User.isLogged)
							return;
						// delete blog
						bc.deleteBlog();
						break;
					case 123:
						// comment
						
						break;
					}
					break;
				// Update user profile
				case 13:
					if (! User.isLogged)
						return;
					// update profile sub menu
					submenu.__IUpdateProfile();
					this.getChoice();
					
					switch (ch){
					case 131:
						// update first Name
						uc.updateFirstName();
						break;
					case 132:
						//update last name
						uc.updateLastName();
						break;
					case 133:
						// update password
						uc.updatePassword();
						break;
					default :
						System.out.println("Invalid Choice!");
						break;
					}
					break;
				case 2:
					if (User.isLogged)
						uc.logout();
					else
						uc.registerUser();
					break;
				case 3: 
					// display all blogs
					bc.showAllBlogs();
					blogId = bc.selectBlogIdDisplay();
					bc.showSingleBlog(blogId);
					
					// if user is logged ask for comment choice4
					
					if (User.isLogged){
						submenu.__IComment();
						this.getChoice();
						
						switch (ch){
						case 31:
							// comment
							cc.addCommentToBlog();
							break;
						case 32:
							break;
						}
					}
					break;
				case 4:
					// search sub-menu
					submenu._ISearch();
					this.getChoice();

					switch(ch){
					//all  search
					case 41:
						bc.searchByAuthor();
						break;
					case 42:
						bc.searchByKeyword();
						break;
					case 43:
						bc.searchByCategory();
						break;
					}
					break;
				case 5:
					if (User.isLogged){
						System.out.println("Sorry! No such choice available!");
						break;
					}						
					Utility.showBoxMessage("Thanks for Visiting.");
					System.exit(0);
					break;
				case 6:
					
					break;
				case 10:
					Utility.showBoxMessage("You have been logged out successfully!");
					uc.logout();
					break;
				default:
					System.out.println("Please provide valid Input!");
					break;
				}
			}catch (Exception e){
				e.printStackTrace();
			}

		}
	}

	public void getChoice(){

		while (true){
			try {
				String ch = in.nextLine();
				if (! Validation.alphabetsOnly(ch)) //validation of choices
				{       
					Menu.ch = Integer.parseInt(ch);
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please Enter a Valid Choice!");
				Menu.ch = in.nextInt();
				break;
			} catch(Exception e){

			}
		}
	}

	public static void main (String [] args){
		new Menu();
	}
}
