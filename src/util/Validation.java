package util;

import model.User;

public class Validation {
	
	// validates an input for its length
	public static boolean length(String term, int size){
		char [] nameArray = term.toCharArray();
		
		if (nameArray.length > size)
			return false;
		else
			return true;
	}
	
	// validation for username, etc
	public static boolean alphabetsOnly(String term){
		if (term.matches("^([a-zA-Z]+\\s+)*[a-zA-Z]+$")) {
			// str consists entirely of letters
			return true;
		}
		
		return false;
	}
	
	// email validation
	public static boolean email(String email){
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (email.matches(EMAIL_REGEX))
			return true;
		else
			return false;		     
	}
	
	// checks username against database for validation
	public static boolean checkDuplicateUsername(String username){
		User user = new User();
		boolean flag = user.checkDuplicateUsername(username); // if present returns true
		
		if (flag)
			return false;
		else 
			return true;
	}
}
