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
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Customer {
	
	public static String customerLoggedIn; 
	 
	public Customer(){ 
	}
 

	 public static Map<String, String> customerHash() 
	            throws FileNotFoundException {
			Map<String, String> customerLogin = new HashMap<>(); 
	        Scanner scanner = new Scanner(new FileInputStream("data/CustomerCredentials.txt"));
	        String[] record;
	        while(scanner.hasNextLine()) {
	            record = scanner.nextLine().split(",");
	            customerLogin.put(record[2], record[3]);
	        }
	        scanner.close();
	        
	        return customerLogin;
	    }
	      
	 /*
	  * Returns the email of thec customer currently logged in
	  * Value needs to be reset when customer chooses to logout
	  */
	 public static String signedInCustomer(){
		 return customerLoggedIn;
		}
	
	 public static String getCustomerID() throws IOException {
		 return specificCustomer(customerLoggedIn).get(0);
	 }
	 
	 
	 public static String getCustomerName() throws IOException {
         return specificCustomer(Customer.customerLoggedIn).get(1);
     }
	 
	 public static String getCustomerEmail() throws IOException {
         return specificCustomer(Customer.customerLoggedIn).get(2);
     }
	  
	 public static String getCustomerPass() throws IOException {
         return specificCustomer(Customer.customerLoggedIn).get(3);
     }
	 
	 public static ArrayList<String> specificCustomer(String ID) throws IOException{
  	   FileReader fr = new FileReader("data/CustomerCredentials.txt");
  	   BufferedReader br = new BufferedReader(fr);
  	   String line;
  	   String name;
  	   String email;
  	   String pass;
  	   ArrayList<String> customerAttributes = new ArrayList<>();
  	   
  	   while((line =  br.readLine()) != null){
  	       if (line.contains(ID))
  	       {
  	           ID = line.split(",")[0];
  	           name = line.split(",")[1];
  	           email = line.split(",")[2];
  	           pass = line.split(",")[3];
  	           customerAttributes.add(ID);
  	           customerAttributes.add(name);
  	           customerAttributes.add(email);
  	           customerAttributes.add(pass);
  	           break;
  	       }
  	   }
  	   br.close();
  	   fr.close();
		return customerAttributes;
     }
	 
	 public ArrayList<String> customerStoreArray() 
	            throws IOException {
			ArrayList<String> customerFavorites = new ArrayList<>();
	        Scanner scanner = new Scanner(new FileInputStream("data/favoriteStores/"+Customer.getCustomerID()+".txt"));
	        String[] record;
	        while(scanner.hasNextLine()) {
	            record = scanner.nextLine().split(",");
	            customerFavorites.add(record[0]);
	        }
	        scanner.close();
	        
	        return customerFavorites;
	    }
	  
	 //return true if the store has already been favorited by the customer
	 public boolean alreadyFavorite(String name) throws IOException {
		 boolean result = false;
		 if (customerStoreArray().contains(Store.getStoreID(name, "data/Stores.txt"))) {
			result = true;
		}
		return result;
	 }
	    
	  
	 public void saveFavoriteStoreFile(String id, String storeInfo) throws IOException {
		 	String filePathString = "data/favoriteStores/"+id+".txt";
	        File file = new File(filePathString);

	        if (!file.exists()) {
	            file.createNewFile();
	             FileWriter fileWriter = new FileWriter(filePathString);
			     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			     bufferedWriter.write(Store.getStoreID(storeInfo, "data/Stores.txt")+ "\n");
			      bufferedWriter.close();
			      fileWriter.close();
			      }
	        else if (!alreadyFavorite(storeInfo)){
	        	FileWriter fileWriter = new FileWriter(filePathString, true);
			    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			    bufferedWriter.write(Store.getStoreID(storeInfo, "data/Stores.txt")+ "\n");
			    bufferedWriter.close();
			    fileWriter.close();
	        }
	   
	 }
	  
	 public static String getCustomerStringInfo() throws IOException {
			String text = getCustomerID() + "," + getCustomerName() + "," + getCustomerEmail() + "," + getCustomerPass();
			return text;   
	       }
	 
	 //Use this to UPDATE information - to DELETE information include the method removeTextSpace
	 public void removeFavoriteStore(String id, String storeinfo) throws IOException {
		 String filePathString = "data/favoriteStores/"+id+".txt";
		        // input the file content to the StringBuffer "input"
		        BufferedReader file = new BufferedReader(new FileReader(filePathString));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;

		        while ((line = file.readLine()) != null) {
		        	if (!line.isEmpty()) {
			        	if (line.equals(Store.getStoreID(storeinfo, "data/Stores.txt"))) {
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
		        
		       
		        
				 File tempFile = new File("data/favoriteStores/temp.txt");

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
				 
		        removeTextSpace(id);
		    } 
		 	
	
	 
	 //Use this method when you have deleted a line of data and want to get rid of empty spaces
	 public void removeTextSpace(String id) throws IOException {
		 
		 String filePathString = "data/favoriteStores/"+id+".txt";
		 File tempFile = new File("data/favoriteStores/temp.txt");
		 
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
	  
	 //update customer information, only the customer ID remains the same and unaffected by user changes
	 public static void updateUserInfo(String updatedInfo) throws IOException {
		 String filePathString = "data/CustomerCredentials.txt";
		
 
		        // input the file content to the StringBuffer "input"
		        BufferedReader file = new BufferedReader(new FileReader(filePathString));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;

		        while ((line = file.readLine()) != null) {
		        	if (!line.isEmpty()) {
		        		if (line.equals(Customer.getCustomerStringInfo())) {
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
	 
	 public static void createShoppingList(String product) throws IOException{
		    String PATH = "data/CustomerShoppingLists/"+Customer.getCustomerID();
		    String directoryName = PATH;
		    String fileName = Products.currentStoreID + ".txt";

		    File directory = new File(directoryName);
		    if (!directory.exists()){
		        directory.mkdir();
		        // If you require it to make the entire directory path including parents,
		        // use directory.mkdirs(); here instead.
		    }
		    	File file = new File(directoryName + "/" + fileName);
		    	
		    	if (!file.exists()) {
		    		file.createNewFile();
		    		FileWriter fw = new FileWriter(file, true);
		    		BufferedWriter bw = new BufferedWriter(fw);
				    bw.write(product + "\n");
				    bw.close();
				    fw.close();
		    	}
		    	else if (!alreadyInList(Products.productViewedID)){
		    	    FileWriter fw = new FileWriter(file, true);
		    		BufferedWriter bw = new BufferedWriter(fw);
				    bw.write(product + "\n");
				    bw.close();
				    fw.close();
				}    
	 }
	 
	 //Creates an array of the IDs of products in a customer's shopping list and checks if the product is there already
	 public static ArrayList<String> customerShoppingListArray() throws IOException {
			ArrayList<String> customerList = new ArrayList<>();
	        Scanner scanner = new Scanner(new FileInputStream("data/CustomerShoppingLists/"+Customer.getCustomerID()+"/" + Products.currentStoreID +".txt"));
	        String[] record;
	        while(scanner.hasNextLine()) {
	            record = scanner.nextLine().split(",");
	            customerList.add(record[0]);
	        }
	        scanner.close();
	        
	        return customerList;
	    }
	 
	 //Checks if a customer already has this product in their shopping list
	 public static boolean alreadyInList(String product) throws IOException {
		 boolean result = false;
		 if (customerShoppingListArray().contains(Products.getProductID(product))) {
			result = true;
		}
		return result;
	 }
	  
	 //sorts the hashmap by values i.e. by the aisle numbers to order the shopping list for customer's convenience
	 public static LinkedHashMap<String, Integer> sortShoppingList(HashMap<String, Integer> map) {
		  LinkedHashMap<String, Integer> linkedMap = new LinkedHashMap<>();
		  ArrayList<Map.Entry<String, Integer>> arr = new ArrayList<>();

   for(Map.Entry<String, Integer> e: map.entrySet()) {
			arr.add(e);
		}

		Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
           
           	public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
               Integer v1 = e1.getValue();
               Integer v2 = e2.getValue();
               return v1.compareTo(v2);
           	}
			};

		Collections.sort(arr, valueComparator);

		for(Map.Entry<String, Integer> e: arr) {
			linkedMap.put(e.getKey(), e.getValue());
		}
		return linkedMap;
	}
	  
	  
	 //gets product ID's and aisle # of that product
	 public static ArrayList<String> orderedShoppingList() throws IOException{
		 HashMap<String, Integer> listMap = new HashMap<>();
		 for (int i=0; i < customerShoppingListArray().size(); i++) {
			 listMap.put(customerShoppingListArray().get(i), Products.getProductAisle(customerShoppingListArray().get(i)));
		 }		 	
		  ArrayList<String> listOfVKeys = new ArrayList<>(sortShoppingList(listMap).keySet());
          
		  return listOfVKeys;
	 }
	 
	   
	  
	 public static ArrayList<String> suggestedProducts() throws IOException{
		 ArrayList<String> suggProducts = new ArrayList<>();
		 ArrayList<String> randomSuggProducts = new ArrayList<>();
		 for (int i=0; i < Products.numberOfProducts(); i++) {
			  if (!orderedShoppingList().contains(Products.searchProductDatabase().get(i).get(0))) {
				  suggProducts.add(Products.searchProductDatabase().get(i).get(0));
			  }
		 }		 	
		 if (suggProducts.size() >= 3) {
		 randomSuggProducts.add(suggProducts.get(new Random().nextInt(suggProducts.size())));
		 randomSuggProducts.add(suggProducts.get(new Random().nextInt(suggProducts.size())));
		 randomSuggProducts.add(suggProducts.get(new Random().nextInt(suggProducts.size())));
		 }
		  return randomSuggProducts;
	 }
	  
	  
	 public void removeProductFromList(String id) throws IOException {
		 String filePathString = "data/CustomerShoppingLists/"+Customer.getCustomerID()+"/" + Products.currentStoreID +".txt";
		

		        // input the file content to the StringBuffer "input"
		        BufferedReader file = new BufferedReader(new FileReader(filePathString));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;

		        while ((line = file.readLine()) != null) {
		        	if (!line.isEmpty()) {
			        	if (line.equals(Products.getProductID(id))) {
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
		        
		       
		        
				 File tempFile = new File("data/CustomerShoppingLists/"+Customer.getCustomerID()+"/temp.txt");

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
				 
				 removeTextSpaceFromProduct(id);
		    } 
		 	
	
	 
	 //Use this method when you have deleted a line of data and want to get rid of empty spaces
	 public void removeTextSpaceFromProduct(String id) throws IOException {
		 
		 String filePathString = "data/CustomerShoppingLists/"+Customer.getCustomerID()+"/" + Products.currentStoreID +".txt";
		 File tempFile = new File("data/CustomerShoppingLists/"+Customer.getCustomerID()+"/temp.txt");

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
	 
	 
	 public void deleteAccount() throws IOException {
		 String filePathString = "data/CustomerCredentials.txt";
			 

	        // input the file content to the StringBuffer "input"
	        BufferedReader file = new BufferedReader(new FileReader(filePathString));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;

	        while ((line = file.readLine()) != null) {
	        	if (!line.isEmpty()) {
		        	if (line.equals(Customer.getCustomerStringInfo())) {
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
			 
			 removeTxtSpaceCredentials();
	 }
	 
	 public void removeTxtSpaceCredentials() throws IOException {
		 
		 String filePathString = "data/CustomerCredentials.txt";
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
	   
	 public static Map<Integer, ArrayList<String>> searchCustomerDatabase() 
	            throws IOException {
			Map<Integer, ArrayList<String>> customerHMap = new HashMap<>(); 
	        // FileInputStream can be used for reading raw bytes, like an image. 
	        Scanner scanner = new Scanner(new FileInputStream("data/CustomerCredentials.txt"));
	        String[] record;
	        BufferedReader br = new BufferedReader(new FileReader("data/CustomerCredentials.txt"));
	        //String line = scanner.nextLine(); 
	        int i = 0;
	        
	        while(scanner.hasNextLine()) { //is line empty check trial scanner.hasNextLine(
	       	 	//if (!(line.length() == 0)) continue;

						ArrayList<String> customerInfo = new ArrayList<>();
			            record = scanner.nextLine().split(",");
			            customerInfo.add(record[0]);//Customer ID
			            customerInfo.add(record[1]);//Customer name
			            customerInfo.add(record[2]);//Customer email
			            customerInfo.add(record[3]);//Customer password
			            customerHMap.put(i++, customerInfo);
	           } 
	        scanner.close();
	        return customerHMap;
	    }
	  

	 public static ArrayList<String> allCustomerIDs() throws IOException {
		 ArrayList<String> allNameStrings = new ArrayList<String>();
		 for (int i =0; i < searchCustomerDatabase().size(); i++) {
			  allNameStrings.add(searchCustomerDatabase().get(i).get(0));
		 }
		 return allNameStrings;
	 }
	 
	 
}