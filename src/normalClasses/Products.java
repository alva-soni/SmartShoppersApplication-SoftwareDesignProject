package normalClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Products {
	
	public static String currentStoreID; 
	public static String categoryChosen;
	public static ArrayList<String> inputArr;
	public static String productViewedID;
	public static Boolean suggestedProduct;
    
	
	//Uses storeID to search the product database
	public static Map<Integer, ArrayList<String>> searchProductDatabase() 
            throws IOException {
		//String filePathString = "data/StoreProductsDB/"+"STORE-GG1122"+".txt";
		String filePathString = "data/StoreProductsDB/"+currentStoreID+".txt";
		Map<Integer, ArrayList<String>> productHMap = new HashMap<>(); 
        Scanner scanner = new Scanner(new FileInputStream(filePathString)); 
        String[] record; 
        BufferedReader br = new BufferedReader(new FileReader(filePathString));
        //String line = scanner.nextLine(); 
        int i = 0;
         
        while(scanner.hasNextLine()) { //is line empty check trial scanner.hasNextLine(
       	 	//if (!(line.length() == 0)) continue;
					ArrayList<String> productInfo = new ArrayList<>();
		            record = scanner.nextLine().split(",");
		            productInfo.add(record[0]);//product ID
		            productInfo.add(record[1]);//product name
		            productInfo.add(record[2]);//product price
		            productInfo.add(record[3]);//product category
		            productInfo.add(record[4]);//product description
		            productInfo.add(record[5]);//product stock
		            productInfo.add(record[6]);//product size/weight
		            productInfo.add(record[7]);//product aisle
		            productHMap.put(i++, productInfo);
           } 
        scanner.close();
        return productHMap;
    }
	
	//returns number of products from a store
	 public static int numberOfProducts() throws IOException  {
		 return searchProductDatabase().size();
	 }
	  
	 public static ArrayList<String> allProductNames() throws IOException {
		 ArrayList<String> allNameStrings = new ArrayList<String>();
		 for (int i =0; i < numberOfProducts(); i++) {
			  allNameStrings.add(searchProductDatabase().get(i).get(1));
		 }
		 return allNameStrings;
	 }
	 
	
	 public static ArrayList<String> specificProduct(String itemInfo) throws IOException{
	  	   //FileReader fr = new FileReader("data/StoreProductsDB/"+"STORE-GG1122"+".txt");  
	  	   FileReader fr = new FileReader("data/StoreProductsDB/"+currentStoreID+".txt");  
	  	   BufferedReader br = new BufferedReader(fr);
	  	   String line;
	  	   String name;
	  	   String price;
	  	   String category;
	  	   String descrip;
	  	   String stock;
	  	   String size;
	  	   String aisle;
	  	   ArrayList<String> productInfo = new ArrayList<>();
	  	   
	  	   while((line =  br.readLine()) != null){
	  	       if (line.contains(itemInfo))
	  	       {
	  	           itemInfo = line.split(",")[0];
	  	           name = line.split(",")[1];
	  	           price = line.split(",")[2];
	  	           category = line.split(",")[3];
	  	           descrip = line.split(",")[4];
	  	           stock = line.split(",")[5];
	  	           size = line.split(",")[6];
	  	           aisle = line.split(",")[7];
	  	           productInfo.add(itemInfo);
	  	           productInfo.add(name);
	  	           productInfo.add(price);
	  	           productInfo.add(category);
	  	           productInfo.add(descrip);
	  	          productInfo.add(stock);
	  	          productInfo.add(size);
	  	          productInfo.add(aisle);
	  	           break;
	  	       }
	  	   }
	  	   br.close();
	  	   fr.close();
			return productInfo;
	     }
	
	 
	   public static String getProductID(String ID) throws IOException {
	         return specificProduct(ID).get(0);
	   } 
	 
       public static String getProductName(String ID) throws IOException {
           return specificProduct(ID).get(1);
       }

       
       public static String getProductPrice(String ID) throws IOException {
           return specificProduct(ID).get(2);
       }
       
       public static String getProductCategory(String ID) throws IOException {
           return specificProduct(ID).get(3);
       }

       //return product description
       public static String getProductDescription(String ID) throws IOException {
           return specificProduct(ID).get(4);
       }

       //return the # of products available
       public static Integer getProductStock(String ID) throws IOException {
           return Integer.parseInt(specificProduct(ID).get(5));
       }
       
       //return product size in lb as a string
       public static String getProductSize(String ID) throws IOException {
           return specificProduct(ID).get(6);
       }
       
       //return aisle number of the product
       public static Integer getProductAisle(String ID) throws IOException {
           return Integer.parseInt(specificProduct(ID).get(7));
       }
       
       public static String getProductStringInfo(String ID) throws IOException {
   		String text = getProductID(ID) + "," + getProductName(ID) + "," + getProductPrice(ID) + "," + getProductCategory(ID) + "," + getProductDescription(ID) + "," + getProductStock(ID) + "," + getProductSize(ID)+ ","+getProductAisle(ID);
   		return text;
          }
       
       public static void updateProductInfo(String updatedInfo, String ID, String storeID) throws IOException {
			 String filePathString = "data/StoreProductsDB/" + storeID + ".txt";
			 
			        BufferedReader file = new BufferedReader(new FileReader(filePathString));
			        StringBuffer inputBuffer = new StringBuffer();
			        String line;

			        while ((line = file.readLine()) != null) {
			        	if (!line.isEmpty()) {
			        		if (line.equals(Products.getProductStringInfo(ID))) {
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
       
   	public static void addProduct(String product, String storeID) {
		try{
		    FileWriter fileWriter = new FileWriter("data/StoreProductsDB/" + storeID + ".txt", true);

		    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    bufferedWriter.write(product + "\n");
		    bufferedWriter.close();
		    
		  }catch (Exception e){
			 System.err.println("Error while writing to file: " +
		          e.getMessage());
		  }
	}
   	
   	
   	public void deleteProduct(String id, String storeID) throws IOException {
		 String filePathString = "data/StoreProductsDB/"+storeID+".txt";
		

		        // input the file content to the StringBuffer "input"
		        BufferedReader file = new BufferedReader(new FileReader(filePathString));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;

		        while ((line = file.readLine()) != null) {
		        	if (!line.isEmpty()) {
			        	if (line.equals(Products.getProductStringInfo(id))) {
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
		        
		       
		        
				 File tempFile = new File("data/StoreProductsDB/temp.txt");

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
				 
				 removeTextSpaceFromProduct(storeID);
		    } 
		 	
	
	 
	 //Use this method when you have deleted a line of data and want to get rid of empty spaces
	 public void removeTextSpaceFromProduct(String storeID) throws IOException {
		 
		 String filePathString = "data/StoreProductsDB/"+storeID+".txt";
		 File tempFile = new File("data/StoreProductsDB/temp.txt");

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
	
       
}
