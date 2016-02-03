package view;

import model.User;
import util.Utility;

public class SubMenu {
	public void welcome(){
		// <!-- Welcome Box -->
		Utility.indent(20);
		Utility.hr(30, "*");
		Utility.indent(30);
		System.out.println("WELCOME");
		Utility.indent(33);
		System.out.println("TO");
		Utility.indent(28);
		System.out.println("BIT BLOGGER");
		Utility.indent(20);
		Utility.hr(30, "*");
		// <-- / -->
		
		Utility.indent(25);
		System.out.println("Stay Hungry, Stay Foolish");
		Utility.indent(20);
		Utility.hr(30, "-");
		Utility.vIndent(5);
	}
	
	public void IHome(){
		if (! User.isLogged){
			System.out.println("1. Login");
			System.out.println("2. Register");
		} else{
			this._IAfterLogin();
		}
		System.out.println("3. Show All Blogs");
		System.out.println("4. Search");
		if (! User.isLogged)
			System.out.println("5. Exit");
	}
	
	public void _IAfterLogin(){
		System.out.println("11. Create a Blog");
		System.out.println("12. Show My Blogs");
		System.out.println("13. Update Profile");
		System.out.println("10. Logout");
	}
	
	public void __IUpdateProfile(){
		System.out.println("131. Update First Name");
		System.out.println("132. Update Last Name");
		System.out.println("133. Change Password");	
		System.out.println("6. Go Back");
	}
	
	public void _ISearch(){
		System.out.println("41. Search By Blog Author");
		System.out.println("42. Search By Keyword");
		System.out.println("43. Search By Category");
		System.out.println("6. Go Back");
	}
	
	public void __IUserEdit(){
		System.out.println("121. Update");
		System.out.println("122. Delete");
		System.out.println("123. Comment");
		System.out.println("6. Go Back");
	}
	
	public void ___IUserUpdate(){
		System.out.println("1111. Update Blog Title");
		System.out.println("1112. Update Blog Summary");
		System.out.println("1113. Update Blog Content");
		System.out.println("6. Go Back");
	}
	
	public void __IComment(){
		System.out.println("31. Comment");
		System.out.println("6. Go Back");
	}
	
	public void _ISelectBlogMessage(){
		
	}
}
