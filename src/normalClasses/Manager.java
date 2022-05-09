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

public class Manager {
	
	public static String managerLoggedIn; 
	public static Boolean isManagerLoggedIn;


	 public static Map<String, String> managerHash() 
	            throws FileNotFoundException {
			Map<String, String> managerLogin = new HashMap<>(); 
	        // FileInputStream can be used for reading raw bytes, like an image. 
	        Scanner scanner = new Scanner(new FileInputStream("data/ManagerCredentials.txt"));
	        String[] record;
	        //User user;
	        while(scanner.hasNextLine()) {
	            record = scanner.nextLine().split(",");
	            managerLogin.put(record[2], record[3]);
	        }
	        scanner.close();
	        
	        return managerLogin;
	    }
	 
	 
	 public static ArrayList<String> specificManager(String ID) throws IOException{
	  	   FileReader fr = new FileReader("data/ManagerCredentials.txt");
	  	   BufferedReader br = new BufferedReader(fr);
	  	   String line;
	  	   String name;
	  	   String email;
	  	   String pass;
	  	   String storeID;
	  	   ArrayList<String> managerAttributes = new ArrayList<>();
	  	   
	  	   while((line =  br.readLine()) != null){
	  	       if (line.contains(ID))
	  	       {
	  	           ID = line.split(",")[0];
	  	           name = line.split(",")[1];
	  	           email = line.split(",")[2];
	  	           pass = line.split(",")[3];
	  	           storeID = line.split(",")[4];
	  	         managerAttributes.add(ID);
	  	         managerAttributes.add(name);
	  	         managerAttributes.add(email);
	  	         managerAttributes.add(pass);
	  	         managerAttributes.add(storeID);
	  	           break;
	  	       }
	  	   }
	  	   br.close();
	  	   fr.close();
			return managerAttributes;
	     }
	 
	 //email of manager signed in
	 public static String signedInManager(){
		 return managerLoggedIn;
		}
	
	 public static String getManagerID(String ID) throws IOException {
		 return specificManager(ID).get(0);
	 }
	 
	 public static String getManagerName(String ID) throws IOException {
         return specificManager(ID).get(1);
     }
	 
	 public static String getManagerEmail(String ID) throws IOException {
         return specificManager(ID).get(2);
     }
	 
	 public static String getManagerPass(String ID) throws IOException {
         return specificManager(ID).get(3);
     }
	
	 public static String getManagerStoreID(String ID) throws IOException {
         return specificManager(ID).get(4);
     }
	 
	 public static Boolean hasStoreBoolean() {
		 Boolean store = false;
		 try {
			if (!getManagerStoreID(managerLoggedIn).equals("N/A")) {
				store = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return store;
	 }
	 
	 
	 public static String getManagerStringInfo(String ID) throws IOException {
			String text = getManagerID(ID) + "," + getManagerName(ID) + "," + getManagerEmail(ID) + "," + getManagerPass(ID) + "," + getManagerStoreID(ID);
			return text;   
	       }
	 
	 public static Map<Integer, ArrayList<String>> searchManagerDatabase() 
	            throws IOException {
			Map<Integer, ArrayList<String>> managerHMap = new HashMap<>(); 
	        // FileInputStream can be used for reading raw bytes, like an image. 
	        Scanner scanner = new Scanner(new FileInputStream("data/ManagerCredentials.txt"));
	        String[] record;
	        BufferedReader br = new BufferedReader(new FileReader("data/ManagerCredentials.txt"));
	        //String line = scanner.nextLine(); 
	        int i = 0;
	        
	        while(scanner.hasNextLine()) { //is line empty check trial scanner.hasNextLine(
	       	 	//if (!(line.length() == 0)) continue;

						ArrayList<String> managerInfo = new ArrayList<>();
			            record = scanner.nextLine().split(",");
			            managerInfo.add(record[0]);//manager ID
			            managerInfo.add(record[1]);//manager name
			            managerInfo.add(record[2]);//manager email
			            managerInfo.add(record[3]);//manager pass
			            managerInfo.add(record[4]);//manager storeID
			            managerHMap.put(i++, managerInfo);
	           } 
	        scanner.close();
	        return managerHMap;
	    }
	 
	 
	 public static Map<String, String> managerStoreHash() 
	            throws FileNotFoundException {
			Map<String, String> managerStore = new HashMap<>(); 
	        // FileInputStream can be used for reading raw bytes, like an image. 
	        Scanner scanner = new Scanner(new FileInputStream("data/ManagerCredentials.txt"));
	        String[] record;
	        //User user;
	        while(scanner.hasNextLine()) {
	            record = scanner.nextLine().split(",");
	            managerStore.put(record[0], record[4]);
	        }
	        scanner.close();
	        
	        return managerStore;
	    }
	 
	 public static ArrayList<String> allManagerNames() throws IOException {
		 ArrayList<String> allNameStrings = new ArrayList<String>();
		 for (int i =0; i < searchManagerDatabase().size(); i++) {
			  allNameStrings.add(searchManagerDatabase().get(i).get(1));
		 }
		 return allNameStrings;
	 }
	 
	 public static ArrayList<String> allManagerIDs() throws IOException {
		 ArrayList<String> allNameStrings = new ArrayList<String>();
		 for (int i =0; i < searchManagerDatabase().size(); i++) {
			  allNameStrings.add(searchManagerDatabase().get(i).get(0));
		 }
		 return allNameStrings;
	 }
	 
	 
	 public static ArrayList<String> allManagerStoreIDs() throws IOException {
		 ArrayList<String> allNameStrings = new ArrayList<String>();
		 for (int i =0; i < searchManagerDatabase().size(); i++) {
			  allNameStrings.add(searchManagerDatabase().get(i).get(4));
		 }
		 return allNameStrings;
	 }
	 
	 public static void deleteManager(String id) throws IOException {
		 String filePathString = "data/ManagerCredentials.txt";
		

		        // input the file content to the StringBuffer "input"
		        BufferedReader file = new BufferedReader(new FileReader(filePathString));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;

		        while ((line = file.readLine()) != null) {
		        	if (!line.isEmpty()) {
			        	if (line.equals(Manager.getManagerStringInfo(id))) {
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
				 
				 removeTextSpaceFromManager();
		    } 
		 	
	
	 
	 //Use this method when you have deleted a line of data and want to get rid of empty spaces
	 public static void removeTextSpaceFromManager() throws IOException {
		 
		 String filePathString = "data/ManagerCredentials.txt";
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
	 
	 
	 public static void addManager(String manager) {
			try{
			    FileWriter fileWriter = new FileWriter("data/ManagerCredentials.txt", true);

			    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			    bufferedWriter.write(manager + "\n");
			    bufferedWriter.close();
			    
			  }catch (Exception e){
				 System.err.println("Error while writing to file: " +
			          e.getMessage());
			  }
		}
	 
	//update customer information, only the customer ID remains the same and unaffected by user changes
	 public static void updateStoreInfo(String updatedInfo, String storeID) throws IOException {
		 String filePathString = "data/Stores.txt";
		 
		        BufferedReader file = new BufferedReader(new FileReader(filePathString));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;

		        while ((line = file.readLine()) != null) {
		        	if (!line.isEmpty()) {
		        		if (line.equals(Store.getStoreStringInfo(storeID, filePathString))) {
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
	 
}
