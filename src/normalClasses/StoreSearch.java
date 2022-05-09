package normalClasses;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class StoreSearch { 
	//Replace this class for customer store search
	
	 public static Map<Integer, ArrayList<String>> searchStoreDatabase(String filePath) 
	            throws IOException {
			Map<Integer, ArrayList<String>> storesHMap = new HashMap<>(); 
	        // FileInputStream can be used for reading raw bytes, like an image. 
	        Scanner scanner = new Scanner(new FileInputStream(filePath));
	        String[] record;
	        BufferedReader br = new BufferedReader(new FileReader(filePath));
	        //String line = scanner.nextLine(); 
	        int i = 0;
	        
	        while(scanner.hasNextLine()) { //is line empty check trial scanner.hasNextLine(
	       	 	//if (!(line.length() == 0)) continue;

						ArrayList<String> storeInfo = new ArrayList<>();
			            record = scanner.nextLine().split(",");
			            storeInfo.add(record[0]);//store ID
			            storeInfo.add(record[1]);//store name
			            storeInfo.add(record[2]);//store location
			            storeInfo.add(record[3]);//store hours
			            storesHMap.put(i++, storeInfo);
	           } 
	        scanner.close();
	        return storesHMap;
	    }
	  
	   
	 
	 
	 public static int numberOfStores(String filePath) throws IOException {
		 return searchStoreDatabase(filePath).size();
	 }
	 
	 public static ArrayList<String> allStoreIDs(String filePath) throws IOException {
		 ArrayList<String> allNameStrings = new ArrayList<String>();
		 for (int i =0; i < numberOfStores(filePath); i++) {
			  allNameStrings.add(searchStoreDatabase(filePath).get(i).get(0));
		 }
		 return allNameStrings;
	 }
	 
	 public static ArrayList<String> allStoreNames(String filePath) throws IOException {
		 ArrayList<String> allNameStrings = new ArrayList<String>();
		 for (int i =0; i < numberOfStores(filePath); i++) {
			  allNameStrings.add(searchStoreDatabase(filePath).get(i).get(1));
		 }
		 return allNameStrings;
	 }
	
	 public static ArrayList<String> allStoreAddresses(String filePath) throws IOException {
		 ArrayList<String> allAddressStrings = new ArrayList<String>();
		 for (int i =0; i < numberOfStores(filePath); i++) {
			  allAddressStrings.add(searchStoreDatabase(filePath).get(i).get(2));
		 }
		 return allAddressStrings;
	 }
	 
	 public static ArrayList<String> allStoreHours(String filePath) throws IOException {
		 ArrayList<String> allHoursStrings = new ArrayList<String>();
		 for (int i =0; i < numberOfStores(filePath); i++) {
			  allHoursStrings.add(searchStoreDatabase(filePath).get(i).get(3));
		 }
		 return allHoursStrings;
	 }
	 
	   
	 
	//public static void main(String[] args) throws FileNotFoundException {
	 //  		System.out.print(allStoreProductDir());
	 //  	}
	
	
	
	
	
	
	
	
	
}
