package util;

import java.util.StringTokenizer;

public class Utility {
	
	private static java.util.Scanner in;
	
	public String encrypt(String password){
		return password;
	}
	
	// this method will take multi-line input from the user
	public static String multilineInput(){
		String TERMINATOR_STRING = ".TERMINATE";
        in = new java.util.Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String strLine;
        final String lineSeperator = System.lineSeparator(); // caching lineSeperator
        
        while (true){
            strLine = in.nextLine();
            if (strLine.isEmpty()){
                sb.append(lineSeperator);
            }else if (strLine.equals(TERMINATOR_STRING)){
                break;
            }else{
                sb.append(strLine);
            }
        }
        
        return sb.toString();
	}
	
	// Instructions to write content
	public static void instructions(){
		System.out.println("*Type hash (#) to change paragraph whenever required!");
		System.out.println("*Type (.TERMINATE) [without parethesis] to end!");
	}
	
	// create a horizontal line of particular patter
	public static void hr(int width){
		System.out.println();
		for (int i=0; i < width; i++){
			System.out.print("-");
		}
		System.out.println();
	}
	
	public static void hr(int width, String symbol){
		for (int i=0; i < width; i++){
			System.out.print(symbol);
		}
		System.out.println();
	}
	
	// tokenize the string based on certain symbol and print
	public static void explode(String content, String token){
		StringTokenizer st = new StringTokenizer(content, token);
		while (st.hasMoreTokens()){
			System.out.println(st.nextToken());
		}
	}
	
	// required indentation 
	public static void indent(int spaces){
		for (int i=0; i < spaces; i++){
			System.out.print(" ");
		}
	}
	
	// required vertical indentation
	public static void vIndent(int spaces){
		for (int i=0; i < spaces; i++){
			System.out.println();
		}
	}
	
	// makes first letter of the string capital
	public static String capitalizeFirstLetter(String original) {
	    if (original == null || original.length() == 0) {
	        return original;
	    }
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	
	// will create a box and show message
	public static void showBoxMessage(String str){
		Utility.hr(50, "*");
		System.out.print("*");
		Utility.indent(10);
		System.out.print(str);
		Utility.indent(18);
		System.out.print("*");
		System.out.println();			
		Utility.hr(50, "*");
	}
	
	
}
