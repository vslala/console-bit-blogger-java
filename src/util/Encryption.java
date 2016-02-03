package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	// this method crypt the password with md5 encryption
	public static String cryptWithMD5(String pass){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] passBytes = pass.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<digested.length;i++){
				sb.append(Integer.toHexString(0xff & digested[i]));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			System.out.println("No such Algorithm found!");
		}
		return null;

	}
	
	// if password matches then return true
	public static boolean checkPassword(String password, String encryptedPassword){
		password = Encryption.cryptWithMD5(password);
		if (password.equals(encryptedPassword))
			return true;
		return false;
	}

}
