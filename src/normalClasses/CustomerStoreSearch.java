package normalClasses;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerStoreSearch{

	 public static Map<Integer, String> searchStoreDatabase(String filePath) 
	            throws IOException {
			Map<Integer, String> storesHMap = new HashMap<>(); 
	        // FileInputStream can be used for reading raw bytes, like an image. 
	        Scanner scanner = new Scanner(new FileInputStream(filePath));
	        String[] record;
	        BufferedReader br = new BufferedReader(new FileReader(filePath));
	        //String line = scanner.nextLine(); 
	        int i = 0;
	         
	        while(scanner.hasNextLine()) { //is line empty check trial scanner.hasNextLine(
			            storesHMap.put(i++, scanner.nextLine());
	           } 
	        scanner.close();
	        return storesHMap;
	    }
	   
	  
	 public static int numberOfStores(String filePath) throws IOException {
		 return searchStoreDatabase(filePath).size();
	 }
	
	 public static ArrayList<String> allStoreNames(String filePath) throws IOException {
		 ArrayList<String> allNameStrings = new ArrayList<String>();
		 for (int i =0; i < numberOfStores(filePath); i++) {
			 allNameStrings.add(Store.getStoreName(searchStoreDatabase(filePath).get(i), "data/Stores.txt"));
		 }
		 return allNameStrings;
	 }
	 
	 public static ArrayList<String> allStoreAddresses(String filePath) throws IOException {
		 ArrayList<String> allAddressStrings = new ArrayList<String>();
		 for (int i =0; i < numberOfStores(filePath); i++) {
			  allAddressStrings.add(Store.getStoreAddress(searchStoreDatabase(filePath).get(i), "data/Stores.txt"));
		 }
		 return allAddressStrings;
	 }
	 
	 public static ArrayList<String> allStoreHours(String filePath) throws IOException {
		 ArrayList<String> allHoursStrings = new ArrayList<String>();
		 for (int i =0; i < numberOfStores(filePath); i++) {
			  allHoursStrings.add(Store.getStoreHours(searchStoreDatabase(filePath).get(i), "data/Stores.txt"));
		 }
		 return allHoursStrings;
	 }
	 
	 public static ArrayList<String> allStoreIDs(String filePath) throws IOException {
		 ArrayList<String> allHoursStrings = new ArrayList<String>();
		 for (int i =0; i < numberOfStores(filePath); i++) {
			  allHoursStrings.add(Store.getStoreID(searchStoreDatabase(filePath).get(i), "data/Stores.txt"));
		 }
		 return allHoursStrings;
	 }
	 
	   
}
