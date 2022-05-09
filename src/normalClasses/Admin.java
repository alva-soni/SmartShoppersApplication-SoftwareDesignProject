package normalClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin {
	
	public static Boolean IsAdminLoggedIn; //gives ID of the admin logged in 
	public static String adminLoggedIn; 
	public static String currentStore; //get the ID of the current store Admin is viewing
	public static String viewedManager;
	
	public static Map<String, String> adminHash() 
            throws FileNotFoundException {
		Map<String, String> adminLogin = new HashMap<>(); 
        Scanner scanner = new Scanner(new FileInputStream("data/AdminCredentials.txt")); 
        String[] record;
        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            adminLogin.put(record[2], record[3]);
        }
        scanner.close();
        
        return adminLogin;
    }  
	
	public static ArrayList<String> specificAdmin(String ID) throws IOException{
	  	   FileReader fr = new FileReader("data/AdminCredentials.txt");
	  	   BufferedReader br = new BufferedReader(fr);
	  	   String line;
	  	   String name;
	  	   String email;
	  	   String pass;
	  	   ArrayList<String> adminAttributes = new ArrayList<>();
	  	   
	  	   while((line =  br.readLine()) != null){
	  	       if (line.contains(ID))
	  	       {
	  	           ID = line.split(",")[0];
	  	           name = line.split(",")[1];
	  	           email = line.split(",")[2];
	  	           pass = line.split(",")[3];
	  	           adminAttributes.add(ID);
	  	           adminAttributes.add(name);
	  	           adminAttributes.add(email);
	  	       	   adminAttributes.add(pass);
	  	           break;
	  	       }
	  	   }
	  	   br.close();
	  	   fr.close();
			return adminAttributes;
	     }
	
	public static String getAdminID(String ID) throws IOException {
		 return specificAdmin(ID).get(0);
	 } 
	  
	 public static String getAdminName(String ID) throws IOException {
        return specificAdmin(ID).get(1);
    }
	 
	 public static String getAdminEmail(String ID) throws IOException {
        return specificAdmin(ID).get(2);
    }
	 
	 public static String getAdminPass(String ID) throws IOException {
        return specificAdmin(ID).get(3);
    }

	 
	 public static String getAdminStringInfo(String ID) throws IOException {
			String text = getAdminID(ID) + "," + getAdminName(ID) + "," + getAdminEmail(ID) + "," + getAdminPass(ID);
			return text;   
	       }
	 
	 
	//update customer information, only the customer ID remains the same and unaffected by user changes
		 public static void updateStoreInfo(String updatedInfo) throws IOException {
			 String filePathString = "data/Stores.txt";
			 
			        BufferedReader file = new BufferedReader(new FileReader(filePathString));
			        StringBuffer inputBuffer = new StringBuffer();
			        String line;

			        while ((line = file.readLine()) != null) {
			        	if (!line.isEmpty()) {
			        		if (line.equals(Store.getStoreStringInfo(currentStore, filePathString))) {
			        			line = updatedInfo;
			        		} 
			        	
			        	inputBuffer.append(line);
			            inputBuffer.append('\n');
			        }
			        }
			        file.close();
			        
			        FileOutputStream fileOut = new FileOutputStream(filePathString);
			        fileOut.write(inputBuffer.toString().getBytes());
			        fileOut.close();
		 }

		 
	
		 
		 public static ArrayList<String> availableManagers() throws IOException{
			 ArrayList<String> avaiList = new ArrayList<>();
			 
			 for(String i: Manager.managerStoreHash().keySet()){
			        if(Manager.managerStoreHash().get(i).equals("N/A")) {
			            avaiList.add(i);
			        }
			    }
			 
			 ArrayList<String> namesList = new ArrayList<>();

			 for (int j = 0; j < avaiList.size(); j++) {
				 namesList.add(Manager.getManagerName(avaiList.get(j)));
			 }
			 
		 return namesList;
		 }
	
		 
		 public static String currentManager() throws IOException{
			 String currManager = "No Manager Assigned";
			 
			 for(String i: Manager.managerStoreHash().keySet()){
			        if(Manager.managerStoreHash().get(i).equals(Store.getStoreID(Admin.currentStore, "data/Stores.txt")))  {
			            currManager = Manager.getManagerName(i);
			        }
			    }

			 
		 return currManager;
		 }
	
		//update manager information, only the customer ID remains the same and unaffected by user changes
		 public static void updateManagerInfo(String updatedInfo, String ID) throws IOException {
			 String filePathString = "data/ManagerCredentials.txt";
			 
			        BufferedReader file = new BufferedReader(new FileReader(filePathString));
			        StringBuffer inputBuffer = new StringBuffer();
			        String line;

			        while ((line = file.readLine()) != null) {
			        	if (!line.isEmpty()) {
			        		if (line.equals(Manager.getManagerStringInfo(ID))) {
			        			line = updatedInfo;
			        		} 
			        	inputBuffer.append(line);
			            inputBuffer.append('\n');
			        }
			        }
			        file.close();
			        
			        FileOutputStream fileOut = new FileOutputStream(filePathString);
			        fileOut.write(inputBuffer.toString().getBytes());
			        fileOut.close();
		 }
		 
		 public static void updateAdminInfo(String updatedInfo, String ID) throws IOException {
			 String filePathString = "data/AdminCredentials.txt";
			 
			        BufferedReader file = new BufferedReader(new FileReader(filePathString));
			        StringBuffer inputBuffer = new StringBuffer();
			        String line;

			        while ((line = file.readLine()) != null) {
			        	if (!line.isEmpty()) {
			        		if (line.equals(Admin.getAdminStringInfo(ID))) {
			        			line = updatedInfo;
			        		} 
			        	inputBuffer.append(line);
			            inputBuffer.append('\n');
			        }
			        }
			        file.close();
			        
			        FileOutputStream fileOut = new FileOutputStream(filePathString);
			        fileOut.write(inputBuffer.toString().getBytes());
			        fileOut.close();
		 }
		 
		 public static void deleteAdmin(String id) throws IOException {
			 String filePathString = "data/AdminCredentials.txt";
			

			        // input the file content to the StringBuffer "input"
			        BufferedReader file = new BufferedReader(new FileReader(filePathString));
			        StringBuffer inputBuffer = new StringBuffer();
			        String line;

			        while ((line = file.readLine()) != null) {
			        	if (!line.isEmpty()) {
				        	if (line.equals(Admin.getAdminStringInfo(id))) {
			        			line = "";
			        		} 
			        	
			        	inputBuffer.append(line);
			            inputBuffer.append('\n');
			        }
			        }
			        file.close();
			        
			        FileOutputStream fileOut = new FileOutputStream(filePathString);
			        fileOut.write(inputBuffer.toString().getBytes());
			        fileOut.close();
			        
			       
			        
					 File tempFile = new File("data/temp.txt");

					 Scanner file1 = new Scanner(new FileReader(filePathString));
					 PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

					 while (file1.hasNext()) {
			             String line1 = file1.nextLine();
			             if (!line1.isEmpty()) {
			                 writer.write(line1);
			                 writer.write("\n");
			             }
			         }
					 writer.close(); 
					 file1.close(); 
					 
					 removeTextSpaceFromAdmin();
			    } 
			 	
		
		 
		 //Use this method when you have deleted a line of data and want to get rid of empty spaces
		 public static void removeTextSpaceFromAdmin() throws IOException {
			 
			 String filePathString = "data/AdminCredentials.txt";
			 File tempFile = new File("data/temp.txt");

			 Scanner file1 = new Scanner(new FileReader(tempFile));
			 PrintWriter writer = new PrintWriter(new FileWriter(filePathString));

			 while (file1.hasNext()) {
	            String line1 = file1.nextLine();
	            if (!line1.isEmpty()) {
	                writer.write(line1);
	                writer.write("\n");
	            }
	        }
			 writer.close(); 
			 file1.close(); 
			 
			 tempFile.delete();
		 }
		 
		 public static void addAdmin(String admin) {
				try{
				    FileWriter fileWriter = new FileWriter("data/AdminCredentials.txt", true);

				    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				    bufferedWriter.write(admin + "\n");
				    bufferedWriter.close();
				    
				  }catch (Exception e){
					 System.err.println("Error while writing to file: " +
				          e.getMessage());
				  }
			}
		 
		 
}
