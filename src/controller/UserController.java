/*
 * Author: Varun Shrivastava
 * Description: This class is responsible for all the user inputs related to user.
 * Operations such as-
 * -Registration
 * -Login
 */

package controller;

import java.util.Scanner;

import model.User;
import util.Encryption;
import util.Utility;
import util.Validation;

public class UserController extends User{
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private int status = 1;
	private Scanner in2;
	
	// Sets the user data to temp variables
	public void setUserData(String firstName, String lastName, String username, String password, int status){
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.status = status; 
	}
	
	/*
	 *| ---------------------------------------------------------------------------------
	 *| GETTING USER DATA
	 *| --------------------------------------------------------------------------------- 
	 */
	// reads data from the user and validates it
	public void getUserData(){
		Scanner in = new Scanner(System.in);
		
		// ask user for his FIRST NAME again and again if until its not a valid data
		while (true){
			System.out.println("Enter First Name");
			String firstName = in.nextLine();
			if (Validation.length(firstName, 20)){
				if (Validation.alphabetsOnly(firstName)){
					this.firstName = firstName;
					this.firstName = Utility.capitalizeFirstLetter(this.firstName);
					break;
				}
			}
		}
		
		// ask user for his LAST NAME again and again if until its not a valid data
		while (true){
			System.out.println("Enter Last Name");
			String lastName = in.nextLine();
			if (Validation.length(lastName, 30)){
				if (Validation.alphabetsOnly(lastName)){
					this.lastName = lastName;
					this.lastName = Utility.capitalizeFirstLetter(this.lastName);
					break;
				}
			}
		}
		
		// ask user for username 
		while (true){
			System.out.println("Choose Your Desired Username");
			String username = in.nextLine();
			
			// validate input
			if (Validation.length(username, 12)){
				if (Validation.alphabetsOnly(username)){
					if (Validation.checkDuplicateUsername(username)){ // if no username exists
						this.username = username;
						break;
					}else{
						System.out.println("Username already exists! Choose another one!");
					}
				}else{
					System.out.println("Only Alphabets are allowed!!!");
				}
			}
		}
		
		while (true){
//			Console console = null;
			System.out.println("Choose your desired password!");
//			char[] pass = console.readPassword("Choose your desired Password: "); // read password
			String password = in.nextLine(); // convert password to string
			
			if (Validation.length(password, 10)){
				this.password = Encryption.cryptWithMD5(password);
				break;
			} else{
				System.out.println("Password length can't exceed more than 10");
			}
		}
	}
	
	// this function will create user in the database
	public boolean registerUser(){
		this.getUserData();
		User user = new User();
		boolean isCreated = user.createUser(this.username, this.password, this.firstName, this.lastName, this.status);
		return isCreated; // true if user has been created successfully!
	}
	
	// Checks where the user is valid or not
	public void loginUser(){
		in2 = new Scanner(System.in);
		
		System.out.print("Enter Username: ");
		String username = in2.nextLine();
		
		System.out.print("Enter Password: ");
		String password = in2.nextLine();
		
		User user = new User();
		boolean flag = user.auth(username, password);
		
		// if user is authentic
		if (flag){
			System.out.println("You have been logged in successfully!");
		}else{
			System.out.println("Invalid Credentials!");
		}
	}
	
	/*
	 *|--------------------------------------------------------------------------------------------------
	 *| ALL UPDATE METHODS FOR USER IS LISTED BELOW
	 *|--------------------------------------------------------------------------------------------------
	 */
	
	// update first name
	public void updateFirstName(){
		Scanner in = new Scanner(System.in);
		while(true){
			System.out.print("Enter your First Name: ");
			this.firstName = in.nextLine();
			
			if (Validation.alphabetsOnly(this.firstName)){
				if (Validation.length(this.firstName, 20)){
					super.updateUserFirstName(User.user_id, this.firstName);
					break;
				}else{
					System.out.println("Name should be less than 20 charecters");
				}
			}else{
				System.out.println("Only alphabets are allowed!");
			}
		}
	}
	
	// update last name
	public void updateLastName(){
		Scanner in = new Scanner(System.in);
		while(true){
			System.out.print("Enter your Last Name: ");
			this.lastName = in.nextLine();
			
			if (Validation.alphabetsOnly(this.lastName)){
				if (Validation.length(this.lastName, 30)){
					super.updateUserLastName(User.user_id, this.lastName);
					break;
				}else{
					System.out.println("Last Name should be less than 30 charecters");
				}
			}else{
				System.out.println("Only alphabets are allowed!");
			}
		}
	}
	
	// update user's password
	public void updatePassword(){
		Scanner in = new Scanner(System.in);
		while (true){
//			Console console = null;
			System.out.println("Choose your desired password!");
//			char[] pass = console.readPassword("Choose your desired Password: "); // read password
			this.password = in.nextLine(); // convert password to string
			
			if (Validation.length(this.password, 10)){
				this.password = Encryption.cryptWithMD5(this.password);
				super.updateUserPassword(User.user_id, this.password);
				break;
			} else{
				System.out.println("Password length can't exceed more than 10");
			}
		}
	}
	
	public void logout(){
		User.isLogged = false;
		User.first_name = null;
		User.last_name = null;
		User.user_id = 0;
		User.username = null;
	}
	

}
