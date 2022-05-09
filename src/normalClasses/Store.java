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
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {
	   String ID;
	   String name;
       String hours;
       String address;
  
       public static boolean justCreated;
       
       public static boolean savedStore;
       
       public Store() {
    	   
       }

       
     public static String getStoreID(String ID, String filePath) throws IOException {
         return specificStore(ID, filePath).get(0);
     } 
       public static String getStoreName(String ID, String filePath) throws IOException {
           return specificStore(ID, filePath).get(1);
       }

       
       public static String getStoreAddress(String ID, String filePath) throws IOException {
           return specificStore(ID, filePath).get(2);
       }

       
       public static String getStoreHours(String ID, String filePath) throws IOException {
           return specificStore(ID, filePath).get(3);
       }
       
       public static String getStoreStringInfo(String ID, String filePath) throws IOException {
		String text = getStoreID(ID, filePath) + "," + getStoreName(ID, filePath) + "," + getStoreAddress(ID,filePath) + "," + getStoreHours(ID, filePath);
		return text;
       }
       
       //Get all the ID's of the stores and put them into an arraylist
       public static ArrayList<String> allStoreIDs() throws IOException{
      	 ArrayList<String> storeIDs = new ArrayList<>();
      	 String filePath ="data/Stores.txt"; 
      	Scanner scanner = new Scanner(new FileInputStream(filePath));
        String[] record;
        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            storeIDs.add(record[0]);
        }
        scanner.close();
        return storeIDs;    	   
       }
       
       
  	 public static ArrayList<String> specificStore(String ID, String filePath) throws IOException{
    	   //FileReader fr = new FileReader("data/Stores.txt");
    	   FileReader fr = new FileReader(filePath);
    	   BufferedReader br = new BufferedReader(fr);
    	   String line;
    	   String name;
    	   String address;
    	   String hours;
    	  // String manager;
    	   ArrayList<String> storeAttributes = new ArrayList<>();
    	   
    	   while((line =  br.readLine()) != null){
    	       if (line.contains(ID))
    	       {
    	           ID = line.split(",")[0];
    	           name = line.split(",")[1];
    	           address = line.split(",")[2];
    	           hours = line.split(",")[3];
    	           storeAttributes.add(ID);
    	           storeAttributes.add(name);
    	           storeAttributes.add(address);
    	           storeAttributes.add(hours);
    	           break;
    	       }
    	   }
  		return storeAttributes;
       }

  	  
  	public static void createStore(String store, String storeID) {
		try{
		    FileWriter fileWriter = new FileWriter("data/Stores.txt", true);

		    Path textFilePath = Paths.get("data/StoreProductsDB/" + storeID + ".txt"); //create file with storeID
		    Files.createFile(textFilePath);
		    
		    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    bufferedWriter.write(store + "\n");
		    bufferedWriter.close();
		    
		  }catch (Exception e){
			 System.err.println("Error while writing to file: " +
		          e.getMessage());
		  }
	}
  	 
  	public static void deleteStore(String storeID) throws IOException {
		 String filePathString = "data/Stores.txt";
		

		        // input the file content to the StringBuffer "input"
		        BufferedReader file = new BufferedReader(new FileReader(filePathString));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;

		        while ((line = file.readLine()) != null) {
		        	if (!line.isEmpty()) {
			        	if (line.equals(Store.getStoreStringInfo(storeID, "data/Stores.txt"))) {
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
				 
				 removeTextSpace(storeID);
		    } 
		 	
	
	 
	 //Use this method when you have deleted a line of data and want to get rid of empty spaces
	 public static void removeTextSpace(String id) throws IOException {
		 
		 String filePathString = "data/Stores.txt";
		 File tempFile = new File("data/temp.txt");
		 File storeDBfile = new File("data/StoreProductsDB/" + id +".txt");

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
		 storeDBfile.delete();
	 }
  	
 
}
