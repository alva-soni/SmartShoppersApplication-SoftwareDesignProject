package normalClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DamageControl {
	 
	//Takes the ID of the current store for which the product is removed
	//Goes through all the shopping lists of the customers and removes that product (product ID is in shopping list)
	//Also need to follow up by removing the space left in their shopping list
	public static void removeProductFromShoppingList(String productID, String productStoreId) throws IOException {
		
		for (int i=0; i < Customer.allCustomerIDs().size(); i++) {
		    //Path path = Paths.get("data/CustomerShoppingLists/"+Customer.allCustomerIDs().get(i)+ "/"+productStoreId+".txt");
 
		    File f = new File("data/CustomerShoppingLists/"+Customer.allCustomerIDs().get(i)+ "/");
		    File storeFile = new File("data/CustomerShoppingLists/"+Customer.allCustomerIDs().get(i)+ "/"+productStoreId+".txt");
		    
		    if (f.isDirectory() && storeFile.exists()) {

		    		deleteProduct(productID, productStoreId, Customer.allCustomerIDs().get(i));
		    }
		}	
	}
	 
	public static void deleteProduct(String productID, String storeID, String customerID) throws IOException {
		 String filePathString = "data/CustomerShoppingLists/"+customerID+ "/"+storeID+".txt";
		Boolean yesBoolean = false;

		        // input the file content to the StringBuffer "input"
		        BufferedReader file = new BufferedReader(new FileReader(filePathString));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;

		        while ((line = file.readLine()) != null) {
		        	if (!line.isEmpty()) {
			        	if (line.equals(Products.getProductID(productID))) {
		        			line = "";
		        			yesBoolean = true;
		        		} 
		        	
		        	inputBuffer.append(line);
		            inputBuffer.append('\n');
		        }
		        }
		        file.close();
		        
		        FileOutputStream fileOut = new FileOutputStream(filePathString);
		        fileOut.write(inputBuffer.toString().getBytes());
		        fileOut.close();
		        
		       
		        
				 File tempFile = new File("data/CustomerShoppingLists/"+customerID+"/"+"temp.txt");

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
				 
				 if (yesBoolean) {
					 removeTextSpaceFromProduct(storeID, customerID);

				}
		    } 
		 	
	
	 
	 //Use this method when you have deleted a line of data and want to get rid of empty spaces
	 public static void removeTextSpaceFromProduct(String storeID, String customerID) throws IOException {
		 
		 String filePathString = "data/CustomerShoppingLists/"+ customerID + "/" +storeID+".txt";
		 File tempFile = new File("data/CustomerShoppingLists/"+ customerID +"/"+"temp.txt");

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
	
	 
	 
	 //delete Customer's store shopping list
	 //remove the store ID from Customer's favoriteStores txt file
	 public static void removeStoreFromCustomer(String storeID) throws IOException {
			
			for (int i=0; i < Customer.allCustomerIDs().size(); i++) {

			    File storeDir = new File("data/CustomerShoppingLists/"+Customer.allCustomerIDs().get(i)+ "/");
			    File storeFile = new File("data/CustomerShoppingLists/"+Customer.allCustomerIDs().get(i)+ "/"+storeID+".txt");
			    File favStoreFile = new File("data/favoriteStores/" + Customer.allCustomerIDs().get(i) + ".txt");
			    			    
			    if (storeDir.isDirectory() && storeFile.exists()) {
			    	//Customer customer = new Customer();
					//customer.removeFavoriteStore(Customer.allCustomerIDs().get(i), storeID);
					storeFile.delete();
			    }
			    if (favStoreFile.exists()) { 
			    	Customer customer = new Customer();
					customer.removeFavoriteStore(Customer.allCustomerIDs().get(i), storeID);
				}
			}	
		}	

}
