package normalClasses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Saves customer registration information to the text file
 * Check if the email already exists in the 'email' section when user has typed info in GUI
 */
public class CustomerRegistration {
	
	protected String name;
	protected String email;
	protected String password;
	protected String type;
	
	public CustomerRegistration(String type, String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
	}
 
	public void writeToTextFile() {
		try{
		    FileWriter fileWriter = new FileWriter("data/CustomerCredentials.txt", true);

		    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    bufferedWriter.write(type + "," + name + "," + email + "," + password + "\n");
		    bufferedWriter.close();
		    
		  }catch (Exception e){
			 System.err.println("Error while writing to file: " +
		          e.getMessage());
		  }
	} 
     
	 
	  
}
 