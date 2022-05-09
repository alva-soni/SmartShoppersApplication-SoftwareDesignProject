package testcases;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import normalClasses.Admin;
import normalClasses.Customer;
import normalClasses.CustomerRegistration;
import normalClasses.CustomerStoreSearch;
import normalClasses.DamageControl;
import normalClasses.Manager;
import normalClasses.Products;
import normalClasses.Store;
import normalClasses.StoreSearch;


public class RequirementsTesting {

	@Test //Register a new customer and then deletes their account
	//Requirement 4.1 - Test 1
	public void authenticationTest1() throws FileNotFoundException {
		Customer customer = new Customer();
		
		String uniqueID = UUID.randomUUID().toString();
		String[] parts = uniqueID.split("-");
		String iD = parts[0];
		String IdString = "CUSTOMER-"+iD;
		String customerIDString = IdString;
		String nameString = "Sherlock Holmes";
		String emailString = "detective@email.com";
		String passString = "violin";
		
		CustomerRegistration cr = new CustomerRegistration(IdString, nameString, emailString, passString);
		cr.writeToTextFile();
		
		assert(customer.customerHash().containsKey(emailString));
		assert(customer.customerHash().containsValue(passString));

		assertEquals(0, 0);
		
		customer.customerLoggedIn = emailString;
		 
		try {
			customer.deleteAccount();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert(!customer.customerHash().containsKey(emailString));
		assert(!customer.customerHash().containsValue(passString));
		}	
	 
	@Test //Register a new customer and then deletes their account
	//Requirement 4.1 - Test 2
	public void authenticationTest2() throws FileNotFoundException {
		Customer customer = new Customer();
		
		String uniqueID = UUID.randomUUID().toString();
		String[] parts = uniqueID.split("-");
		String iD = parts[0];
		String IdString = "CUSTOMER-"+iD;
		String customerIDString = IdString;
		String nameString = "Mr. Beans";
		String emailString = "teddy@email.com";
		String passString = "english";
		
		CustomerRegistration cr = new CustomerRegistration(IdString, nameString, emailString, passString);
		cr.writeToTextFile();
		
		assert(customer.customerHash().containsKey(emailString));
		assert(customer.customerHash().containsValue(passString));

		assertEquals(0, 0);
		
		customer.customerLoggedIn = emailString;
		 
		try {
			customer.deleteAccount();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert(!customer.customerHash().containsKey(emailString));
		assert(!customer.customerHash().containsValue(passString));
		}
	
	
	@Test //Check that admin is logged in and exists in database
		  //Requirement 4.1 - Test 3
	public void authenticationTest3() throws IOException {
		Admin admin = new Admin(); 
		
		admin.adminLoggedIn = "parrots@hotmail.ca";
		String adminNameString = admin.getAdminName(admin.adminLoggedIn);
		String adminPasswordString = admin.getAdminPass(admin.adminLoggedIn);
		String adminIDString = admin.getAdminID(admin.adminLoggedIn);
		String adminInfoString = adminIDString + "," + adminNameString + ","  + admin.adminLoggedIn
				+ "," + adminPasswordString;
		assert(admin.adminHash().containsKey(admin.adminLoggedIn)); 
		assertEquals(admin.getAdminEmail(admin.adminLoggedIn), "parrots@hotmail.ca");
		assertEquals(adminNameString, "Alessandra Soni");
		assertEquals(adminPasswordString, "turkey");
		assertEquals(adminIDString, "ADMIN-12345");
		assertEquals(admin.getAdminStringInfo(adminIDString), adminInfoString);
	}
	
	
	
	@Test //Confirms that current users do already have an account
	     //Requirement 4.2 - Test 1
	public void userProfileTest1() throws FileNotFoundException {
		Customer customer = new Customer();
		
		try {;
			customer.customerHash();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email = "greenleaf@gmail.com";
		String password = "mirkwood";
		
		assert(customer.customerHash().containsKey(email) && customer.customerHash().get(email).equals(password));
	}	
	
	 
	

	@Test /*Confirms that information of user already in system matches when asked 
		  *to be retrieved from database
		  *Requirement 4.2 - Test 2
		  */
	public void userProfileTest2() throws IOException {
		Customer customer = new Customer();
		customer.customerLoggedIn = "greenleaf@gmail.com";
		
		String signedInString = "greenleaf@gmail.com";
		String idString = "CUSTOMER-92a02834";
		String nameString = "Legolas Greenleaf";
		String passString = "mirkwood";
		String infoString = idString+","+nameString+","+signedInString+","+passString;
		
		//expected vs. actual
		assertEquals(customer.signedInCustomer(), signedInString);
		assertEquals(customer.getCustomerID(), idString);
		assertEquals(customer.getCustomerName(), nameString);
		assertEquals(customer.getCustomerEmail(), signedInString);
		assertEquals(customer.getCustomerPass(), passString);
		assertEquals(customer.signedInCustomer(), customer.customerLoggedIn);
		assertEquals(customer.getCustomerStringInfo(), infoString);


	}	
	
	@Test //Confirms all current user's information when they sign into the system
		  //Requirement 4.2 - Test 3
	public void userProfileTest3() throws IOException {
		Customer customer = new Customer();
		customer.customerLoggedIn = "gryffindor@hogwarts.ca";
		customer.specificCustomer(customer.customerLoggedIn);
		
		//expected vs. actual
		assertEquals(customer.specificCustomer(customer.customerLoggedIn).get(0), customer.getCustomerID());
		assertEquals(customer.specificCustomer(customer.customerLoggedIn).get(1), customer.getCustomerName());
		assertEquals(customer.specificCustomer(customer.customerLoggedIn).get(2), customer.getCustomerEmail());
		assertEquals(customer.specificCustomer(customer.customerLoggedIn).get(3), customer.getCustomerPass());
	}	
	 
	
	@Test //Check that user information has been updated
	//Requirement 4.2 - Test 4
	public void userProfileTest4() throws IOException {
		
		Customer customer = new Customer(); 
		Customer.customerLoggedIn = "123@email.com";
	;
		String updatedInfoString = customer.getCustomerID() + "," + "Edna Mode" + "," + customer.getCustomerEmail() + "," + customer.getCustomerPass();
		customer.updateUserInfo(updatedInfoString);

		assertEquals(customer.getCustomerStringInfo(),updatedInfoString);
	}
	
	
	@Test //Check that manager information has been retrieved and successfully updated with new information
	//Requirement 4.2 - Test 5
	public void userProfileTest5() throws IOException {
		Manager manager = new Manager(); 
		Admin admin = new Admin();
		manager.managerLoggedIn = "acorns@email.ca";
		
		manager.signedInManager();
		assertEquals(manager.signedInManager(), manager.managerLoggedIn);
		
		assert(manager.managerHash().containsKey(manager.signedInManager()));
		
		String updatedInfoString = manager.getManagerID(manager.managerLoggedIn) + "," + "Pink Piglet" + "," + manager.getManagerEmail(manager.managerLoggedIn) + "," + manager.getManagerPass(manager.managerLoggedIn) 
		 + "," + manager.getManagerStoreID(manager.managerLoggedIn);
		admin.updateManagerInfo(updatedInfoString, manager.managerLoggedIn);

		assertEquals(manager.getManagerStringInfo(manager.managerLoggedIn),updatedInfoString);
		
		assert(manager.hasStoreBoolean());
	}
	
	
	@Test //Let an admin or manager create a product, modify the product information, 
		 //and then delete the product
		//Requirement 4.3.1 - Test 1
	public void itemManagementTest1() throws IOException {
		Admin admin = new Admin(); 
		Store store = new Store();
		Products products = new Products();
		  
		String uniqueID = UUID.randomUUID().toString();
		String[] parts = uniqueID.split("-");
		String iD = parts[0];
		String IdString = "PRODUCT-"+iD;
		String productId = IdString;
		String productInfo = productId +  "," + "Mushroom" + "," + "$2.99" + "," + "Food" + "," 
		+ "An edible and healthy fungi." + "," + "80" + "," + "20 grams" + "," + "3";	
		
		products.currentStoreID = "STORE-GG1122";
		
		Products.addProduct(productInfo, products.currentStoreID);
		 
		assert(products.allProductNames().contains("Mushroom"));
		assertEquals(products.getProductID(productId), productId);
		assertEquals(products.getProductName(productId), "Mushroom");
		assertEquals(products.getProductPrice(productId), "$2.99");
		assertEquals(products.getProductCategory(productId), "Food");
		assertEquals(products.getProductDescription(productId), "An edible and healthy fungi.");
		assertEquals(products.getProductStock(productId), Integer.valueOf("80"));
		assertEquals(products.getProductSize(productId), "20 grams");
		assertEquals(products.getProductAisle(productId), Integer.valueOf("3"));
		assertEquals(products.getProductStringInfo(productId), productInfo);

		String productInfoUpdated = productId +  "," + "Mushroom Salad" + "," + "$2.99" + "," + "Food" + "," 
				+ "An edible and healthy fungi." + "," + "80" + "," + "200 grams" + "," + "3";	
		
		products.updateProductInfo(productInfoUpdated, productId, products.currentStoreID);
		assertEquals(products.getProductStringInfo(productId), productInfoUpdated);
		products.deleteProduct(productId, products.currentStoreID);
	}
	
	
	@Test //Let an admin or manager create a product, modify the product information, 
	 //and then delete the product
	//Requirement 4.3.1 - Test 2
public void itemManagementTest2() throws IOException {
	Admin admin = new Admin(); 
	Store store = new Store();
	Products products = new Products();
	  
	String uniqueID = UUID.randomUUID().toString();
	String[] parts = uniqueID.split("-");
	String iD = parts[0];
	String IdString = "PRODUCT-"+iD;
	String productId = IdString;
	String productInfo = productId +  "," + "Strawberry Jam" + "," + "$8.99" + "," + "Food" + "," 
	+ "Delicious and organic Strawberry Jam" + "," + "16" + "," + "1 kg" + "," + "3";	
	
	products.currentStoreID = "STORE-EE3123";
	
	Products.addProduct(productInfo, products.currentStoreID);
	 
	assert(products.allProductNames().contains("Strawberry Jam"));
	assertEquals(products.getProductID(productId), productId);
	assertEquals(products.getProductName(productId), "Strawberry Jam");
	assertEquals(products.getProductPrice(productId), "$8.99");
	assertEquals(products.getProductCategory(productId), "Food");
	assertEquals(products.getProductDescription(productId), "Delicious and organic Strawberry Jam");
	assertEquals(products.getProductStock(productId), Integer.valueOf("16"));
	assertEquals(products.getProductSize(productId), "1 kg");
	assertEquals(products.getProductAisle(productId), Integer.valueOf("3"));
	assertEquals(products.getProductStringInfo(productId), productInfo);

	String productInfoUpdated = productId +  "," + "Strawberry Jam" + "," + "SALE $8.99" + "," + "Food" + "," 
			+ "Delicious and organic Strawberry Jam" + "," + "16" + "," + "1 kg" + "," + "3";	
	
	products.updateProductInfo(productInfoUpdated, productId, products.currentStoreID);
	assertEquals(products.getProductStringInfo(productId), productInfoUpdated);
	products.deleteProduct(productId, products.currentStoreID);
}
	
	
	@Test //Check that manager has successfully updated store hours for their store
	//Requirement 4.3.2 - Test 1
	public void storeManagementTest1() throws IOException {
		Manager manager = new Manager(); 
		Admin admin = new Admin();
		manager.managerLoggedIn = "acorns@email.ca";
		String updatedHourString = "Open 24 hours daily";
		String text = Store.getStoreID(Manager.getManagerStoreID(Manager.managerLoggedIn), "data/Stores.txt") + "," + Store.getStoreName(Manager.getManagerStoreID(Manager.managerLoggedIn), "data/Stores.txt")+ "," 
		+ Store.getStoreAddress(Manager.getManagerStoreID(Manager.managerLoggedIn),  "data/Stores.txt") + "," + updatedHourString;

		manager.updateStoreInfo(text,Manager.getManagerStoreID(Manager.managerLoggedIn));
		
		Store store = new Store();
		assertEquals(store.getStoreHours(Manager.getManagerStoreID(Manager.managerLoggedIn),"data/Stores.txt"), updatedHourString);
	}
	
	
	@Test 
	  /* Let an admin add i.e. create a store, update the store info, check that it is in store registry, 
	  *	check it has no manager, and then delete the store (and make sure it doesn't exist)
	  * Requirement 4.3.2 - Test 2
	  */
public void storeManagementTest2() throws IOException {
	Admin admin = new Admin(); 
	Store store = new Store();
	  
	String uniqueID = UUID.randomUUID().toString();
	String[] parts = uniqueID.split("-");
	String iD = parts[0];
	String IdString = "STORE-"+iD;
	String storeId = IdString;
	String storeString = IdString + "," + "Hell Store" + "," + "Planet of Tartarus"
	+ "," + "Open 24/7 - Hell never rests";	
	
	store.createStore(storeString, IdString);
	assert(store.allStoreIDs().contains(IdString));
	admin.currentStore = IdString;
	String storeStringUpdated = IdString + "," + "Hell Planet" + "," + "Planet of Tartarus"
			+ "," + "Open 24/7 - Hell never rests";	
	admin.updateStoreInfo(storeStringUpdated);
	assertEquals(store.getStoreName(IdString, "data/Stores.txt"), "Hell Planet");
	
	admin.currentStore = IdString;
	assertEquals(admin.currentManager(), "No Manager Assigned");
	
	String filePathString = "data/Stores.txt";
	assert(StoreSearch.allStoreIDs(filePathString).contains(Store.getStoreID(storeId, filePathString)));
	assert(StoreSearch.allStoreNames(filePathString).contains(Store.getStoreName(storeId, filePathString)));
	assert(StoreSearch.allStoreAddresses(filePathString).contains(Store.getStoreAddress(storeId, filePathString)));
	assert(StoreSearch.allStoreHours(filePathString).contains(Store.getStoreHours(storeId, filePathString)));
	
	store.deleteStore(IdString);
	
	assert(!store.allStoreIDs().contains(IdString));
}
	
	@Test 
	 /*Let an admin add i.e. create a store, a customer will add that store to their favorite store lists, then admin will delete the store
	  *the customer should no longer have that store saved if the admin deletes the store
	  *Requirement 4.3.2 - Test 3
	  */
	public void storeManagementTest3() throws IOException {
		Store store = new Store();
		Customer customer = new Customer();
		DamageControl dmControl = new DamageControl();
		CustomerStoreSearch cSearch = new CustomerStoreSearch();
		customer.customerLoggedIn = "CUSTOMER-92a02834"; //customer Legolas Greenleaf
		String favStoreFilePath = "data/favoriteStores/" + customer.customerLoggedIn + ".txt";


		String uniqueID = UUID.randomUUID().toString();
		String[] parts = uniqueID.split("-");
		String iD = parts[0];
		String IdString = "STORE-"+iD;
		String storeId = IdString;
		String storeString = IdString + "," + "Hell Store" + "," + "Planet of Tartarus"
		+ "," + "Open 24/7 - Hell never rests";	
		
		store.createStore(storeString, storeId);
		assert(store.allStoreIDs().contains(storeId));
				
		String filePathString = "data/Stores.txt";
		assert(StoreSearch.allStoreIDs(filePathString).contains(Store.getStoreID(storeId, filePathString)));
		assert(StoreSearch.allStoreNames(filePathString).contains(Store.getStoreName(storeId, filePathString)));
		assert(StoreSearch.allStoreAddresses(filePathString).contains(Store.getStoreAddress(storeId, filePathString)));
		assert(StoreSearch.allStoreHours(filePathString).contains(Store.getStoreHours(storeId, filePathString)));
		
		  
		customer.saveFavoriteStoreFile(customer.getCustomerID(), IdString);
		assert(cSearch.allStoreNames(favStoreFilePath).contains(store.getStoreName(storeId, filePathString)));
		
		dmControl.removeStoreFromCustomer(storeId);
		store.deleteStore(storeId);
		assert(!store.allStoreIDs().contains(storeId));
		assert(!cSearch.allStoreIDs(favStoreFilePath).contains(storeId)); 
	}	
	
	@Test //Check that a new manager has been successfully added to manager database i.e. created (done by the admin) and then deleted (done by admin)
	//4.3.3 - Test 1
	public void humanResourcesTest1() throws IOException {
		Manager manager = new Manager(); 
		
		String uniqueID = UUID.randomUUID().toString();
		String[] parts = uniqueID.split("-");
		String iD = parts[0];
		String managerIdString = "MANAGER-"+iD;
		String managerString = managerIdString + "," + "Arwen Evenstar" + "," + "LOTR@email.com"
		+ "," + "aragorn" + "," + "N/A";
		
		manager.addManager(managerString);

		assert(manager.managerStoreHash().containsKey(manager.getManagerID(managerIdString)));
		assert(manager.allManagerNames().contains(manager.getManagerName(managerIdString)));
		assert(manager.allManagerIDs().contains(managerIdString));
		assert(manager.allManagerStoreIDs().contains(manager.getManagerStoreID(managerIdString))); //store ID should be "N/A"

		assertEquals(manager.getManagerStringInfo(managerString), managerString);
		 
		manager.deleteManager(managerIdString);
	}
	
	@Test //Check that a new manager has been successfully added to manager database i.e. created (done by the admin) and then deleted (done by admin)
	//4.3.3 - Test 2
	public void humanResourcesTest2() throws IOException {
		Manager manager = new Manager(); 
		
		String uniqueID = UUID.randomUUID().toString();
		String[] parts = uniqueID.split("-");
		String iD = parts[0];
		String managerIdString = "MANAGER-"+iD;
		String managerString = managerIdString + "," + "Woder Woman" + "," + "DianaPrince@email.com"
		+ "," + "truth" + "," + "N/A";
		
		manager.addManager(managerString);

		assert(manager.managerStoreHash().containsKey(manager.getManagerID(managerIdString)));
		assert(manager.allManagerNames().contains(manager.getManagerName(managerIdString)));
		assert(manager.allManagerIDs().contains(managerIdString));
		assert(manager.allManagerStoreIDs().contains(manager.getManagerStoreID(managerIdString)));

		assertEquals(manager.getManagerStringInfo(managerString), managerString);
		 
		manager.deleteManager(managerIdString);
	}
	
	

	@Test //Confirm that the stores saved by this particular customer exist 
		  //and have valid addresses and hours
		  //Requirement 4.4.1 - Test 1
	public void storeServicesTest1() throws IOException {
		CustomerStoreSearch cStoreSearch = new CustomerStoreSearch();
		String filePathString = "data/favoriteStores/CUSTOMER-133431434.txt";
		
		Store store = new Store();
		
       assertEquals(cStoreSearch.searchStoreDatabase(filePathString).size(), cStoreSearch.numberOfStores(filePathString) );
       
       ArrayList<String> storeAddressList = new ArrayList<>();
       storeAddressList.add(store.getStoreAddress("STORE-GG1122", "data/Stores.txt"));
       storeAddressList.add(store.getStoreAddress("STORE-APM7878", "data/Stores.txt"));
       storeAddressList.add(store.getStoreAddress("STORE-EE3123", "data/Stores.txt"));
       
       ArrayList<String> storeHoursList = new ArrayList<>();
       storeHoursList.add(store.getStoreHours("STORE-GG1122", "data/Stores.txt"));
       storeHoursList.add(store.getStoreHours("STORE-APM7878", "data/Stores.txt"));
       storeHoursList.add(store.getStoreHours("STORE-EE3123", "data/Stores.txt"));
       
      assertEquals(cStoreSearch.allStoreAddresses(filePathString), storeAddressList);
      assertEquals(cStoreSearch.allStoreHours(filePathString), storeHoursList);
	}
	
	@Test //Check that a saved store has been removed from the customer's favorites
		  //Requirement 4.4.1 - Test 2
	public void storeServicesTest2() throws IOException {
		
		Customer customer = new Customer(); 
		Customer.customerLoggedIn = "123@email.com";
		Products.currentStoreID = "STORE-EE3123";
		String customerID = customer.getCustomerID();
		String store = "STORE-EE3123";
		customer.removeFavoriteStore(customerID,store);
		//assert that the store has been removed from the customer's favorite store array
		assert(!customer.customerStoreArray().contains(store));
	}
	
	@Test //Check that a store has been added to the customer's favorite store list
		  //Requirement 4.4.1 - Test 3
	public void storeServicesTest3() throws IOException {
		
		Customer customer = new Customer(); 
		Customer.customerLoggedIn = "123@email.com";
		String customerID = customer.getCustomerID();
		String store = "STORE-EE3123";
		customer.saveFavoriteStoreFile(customerID,store);
		//assert that the store has been removed from the customer's favorite store array
		assert(customer.customerStoreArray().contains(store));
	}
	
	

	@Test //Check how many stores a certain customer has in their 
		  //saved Store's list (return their name, address, hours)
		  //Requirement 4.4.1 - Test 4
	public void storeServicesTest4() throws IOException {
		CustomerStoreSearch cStoreSearch = new CustomerStoreSearch();
		String filePathString = "data/favoriteStores/CUSTOMER-133431434.txt";
		
		Store store = new Store();
		
       assertEquals(cStoreSearch.searchStoreDatabase(filePathString).size(), cStoreSearch.numberOfStores(filePathString) );
       
       ArrayList<String> storeNameAList = new ArrayList<>();
       storeNameAList.add(store.getStoreName("STORE-GG1122", "data/Stores.txt"));
       storeNameAList.add(store.getStoreName("STORE-APM7878", "data/Stores.txt"));
       storeNameAList.add(store.getStoreName("STORE-EE3123", "data/Stores.txt"));
       
      assertEquals(cStoreSearch.allStoreNames(filePathString), storeNameAList);
	}
	
	@Test //Confirms that a store is already favorited i.e. saved by a customer
		  //Requirement 4.4.1 - Test 5
	public void storeServicesTest5() throws IOException {
		Customer customer = new Customer();
		customer.customerLoggedIn = "greenleaf@gmail.com";
		customer.getCustomerID();

		String store = "STORE-GG1122";

		//expected vs. actual
		assertEquals(customer.alreadyFavorite(store),customer.customerStoreArray().contains(store) );
	}	
	
	 
	@Test 
	/*Let an admin or manager create a product, customer adds the product to their shopping list, and checks it is there,
	 * confirms that the it is the same product searched using the name, category, etc. 
	 *then admin or manager deletes the product and the customer should no longer have this item in their shopping list
	 *Checks if when an admin or manager deletes a product, it is deleted everywhere.
	 *Requirement 4.4.2 - Test 1
	 */ 
	public void productTest1() throws IOException {
		Admin admin = new Admin(); 
		Store store = new Store();
		Products products = new Products();
		DamageControl dmControl = new DamageControl();
		Customer customer = new Customer();
		  
		String uniqueID = UUID.randomUUID().toString();
		String[] parts = uniqueID.split("-");
		String iD = parts[0];
		String IdString = "PRODUCT-"+iD;
		String productId = IdString;
		String productInfo = productId +  "," + "Spoons set of 12" + "," + "$16.99" + "," + "Kitchen" + "," 
		+ "Concave object great for eating soup or cereal with." + "," + "56" + "," + "256 grams" + "," + "4";	
		
		products.currentStoreID = "STORE-GG1122";
		products.productViewedID = productId;
		customer.customerLoggedIn = "123@email.com";
				
		Products.addProduct(productInfo, products.currentStoreID);
		 
		assert(products.allProductNames().contains("Spoons set of 12"));
		assertEquals(products.getProductID(productId), productId);
		assertEquals(products.getProductName(productId), "Spoons set of 12");
		assertEquals(products.getProductPrice(productId), "$16.99");
		assertEquals(products.getProductCategory(productId), "Kitchen");
		assertEquals(products.getProductDescription(productId), "Concave object great for eating soup or cereal with.");
		assertEquals(products.getProductStock(productId), Integer.valueOf("56"));
		assertEquals(products.getProductSize(productId), "256 grams");
		assertEquals(products.getProductAisle(productId), Integer.valueOf("4"));
		assertEquals(products.getProductStringInfo(productId), productInfo);

		customer.createShoppingList(productId);
		assert(customer.customerShoppingListArray().contains(productId));
		
		dmControl.removeProductFromShoppingList(productId, products.currentStoreID);
		products.deleteProduct(productId, products.currentStoreID);
		
		assert(!products.allProductNames().contains("Spoons set of 12"));
		assert(!customer.customerShoppingListArray().contains(productId));
	}
	 
	@Test 
	/*Let an admin or manager create a product, customer adds the product to their shopping list, and checks it is there,
	 * confirms that the it is the same product searched using the name, category, etc. 
	 *then admin or manager deletes the product and the customer should no longer have this item in their shopping list
	 *Checks if when an admin or manager deletes a product, it is deleted everywhere.
	 *Requirement 4.4.2 - Test 2
	 */ 
	public void productTest2() throws IOException {
		Admin admin = new Admin(); 
		Store store = new Store();
		Products products = new Products();
		DamageControl dmControl = new DamageControl();
		Customer customer = new Customer();
		  
		String uniqueID = UUID.randomUUID().toString();
		String[] parts = uniqueID.split("-");
		String iD = parts[0];
		String IdString = "PRODUCT-"+iD;
		String productId = IdString;
		String productInfo = productId +  "," + "Toy Cow" + "," + "$16.99" + "," + "Decor" + "," 
		+ "Very soft and cuddly." + "," + "12" + "," + "800 grams" + "," + "6";	
		
		products.currentStoreID = "STORE-GG1122";
		products.productViewedID = productId;
		customer.customerLoggedIn = "123@email.com";
				
		Products.addProduct(productInfo, products.currentStoreID);
		 
		assert(products.allProductNames().contains("Toy Cow"));
		assertEquals(products.getProductID(productId), productId);
		assertEquals(products.getProductName(productId), "Toy Cow");
		assertEquals(products.getProductPrice(productId), "$16.99");
		assertEquals(products.getProductCategory(productId), "Decor");
		assertEquals(products.getProductDescription(productId), "Very soft and cuddly.");
		assertEquals(products.getProductStock(productId), Integer.valueOf("12"));
		assertEquals(products.getProductSize(productId), "800 grams");
		assertEquals(products.getProductAisle(productId), Integer.valueOf("6"));
		assertEquals(products.getProductStringInfo(productId), productInfo);

		customer.createShoppingList(productId);
		assert(customer.customerShoppingListArray().contains(productId));
		
		dmControl.removeProductFromShoppingList(productId, products.currentStoreID);
		products.deleteProduct(productId, products.currentStoreID);
		
		assert(!products.allProductNames().contains("Toy Cow"));
		assert(!customer.customerShoppingListArray().contains(productId));
	}
	 
	
	@Test //Check that a product has been removed from a specific customer's shopping list
		  //Requirement 4.4.3 - Test 1
	public void shoppingListTest1() throws IOException {
		
		Customer customer = new Customer(); 
		Customer.customerLoggedIn = "123@email.com";
		customer.getCustomerID();
		Products.currentStoreID = "STORE-GG1122";
		String productString = "d00d85a5";
		customer.removeProductFromList(productString);
		//expected vs. actual
		assert(!customer.customerShoppingListArray().contains(productString));
	}
	
	@Test //Check if product is already in customer's shopping list
		  //Requirement 4.4.3 - Test 2
	public void shoppingListTest2() throws IOException {
		
		Customer customer = new Customer();
		customer.customerLoggedIn = "greenleaf@gmail.com";
		customer.getCustomerID();
		String store = "STORE-GG1122";

		Products.currentStoreID = store;

		String product = Products.getProductID("PRODUCT-3788a21d");
		assertEquals(customer.customerShoppingListArray().contains(product),customer.alreadyInList(product));
	}	
	
	@Test //Best order for Customer's shopping list based on product aisle
		  //Requirement 4.4.4 - Test 1
	public void bestOrderTest1() throws IOException {
		
		Customer customer = new Customer();
		customer.customerLoggedIn = "greenleaf@gmail.com";
		customer.getCustomerID();
		Products.currentStoreID = "STORE-GG1122";		

		customer.customerShoppingListArray();
		
		ArrayList<String> products = new ArrayList<>();
		products.add("PRODUCT-1b6f4e9c"); //aisle 2
		products.add("PRODUCT-76d37f7e"); //aisle 2
		products.add("PRODUCT-3788a21d"); //aisle 3
		products.add("PRODUCT-e7e9b2da"); //aisle 3
		//expected vs. actual
		assertEquals(products,customer.orderedShoppingList());
	}	
	
	@Test //Adds products to customer's shopping list and order should be as follows 
		  //base on product aisle
	      //Requirement 4.4.4 - Test 2
	public void bestOrderTest2() throws IOException {
	
	Customer customer = new Customer();
	customer.customerLoggedIn = "gryffindor@hogwarts.ca";
	customer.getCustomerID();
	Products.currentStoreID = "STORE-GG1122";		

	customer.customerShoppingListArray();
	
	ArrayList<String> products = new ArrayList<>(); //expe
	products.add("PRODUCT-8uf2vb9h"); //aisle 1
	products.add("PRODUCT-76d37f7e"); //aisle 2
	products.add("PRODUCT-3788a21d"); //aisle 3
	products.add("PRODUCT-76981jk6"); //aisle 6
	//expected vs. actual
	assertEquals(products,customer.orderedShoppingList());
}	
	
	
	@Test //Make sure that suggested/recommended products are not already in customer's shopping list
		  //Requirement 4.5 - Test 1
	public void suggestedProductsTest1() throws IOException {
		
		Customer customer = new Customer();
		customer.customerLoggedIn = "greenleaf@gmail.com";
		customer.getCustomerID();
		Products.currentStoreID = "STORE-GG1122";		
		assert(!customer.customerShoppingListArray().contains(customer.suggestedProducts()));
	}
	
	@Test //Make sure that suggested products are not already in customer's shopping list
	  //Requirement 4.5 - Test 2
	public void suggestedProductsTest2() throws IOException {
	
	Customer customer = new Customer();
	customer.customerLoggedIn = "gryffindor@hogwarts.ca";
	customer.getCustomerID();
	Products.currentStoreID = "STORE-GG1122";		
	assert(!customer.customerShoppingListArray().contains(customer.suggestedProducts()));
	}
	
	@Test //Confirms that this particular customer's saved stores are as follows
	public void extraTest1() throws IOException {
		Customer customer = new Customer();
		customer.customerLoggedIn = "greenleaf@gmail.com";
		customer.getCustomerID();

		ArrayList<String> testArr = new ArrayList<>();
		testArr.add("STORE-GG1122");
		testArr.add("STORE-EE3123");
		assertEquals(customer.customerStoreArray(), testArr);
	}	
	
	@Test //Compare this customer's shopping list to array of products to check if they match
	public void extraTest2() throws IOException {
		
		Customer customer = new Customer();
		customer.customerLoggedIn = "greenleaf@gmail.com";
		customer.getCustomerID();
		String store = "STORE-GG1122";

		Products.currentStoreID = store;

		ArrayList<String> shopList = new ArrayList<>();
		shopList.add("PRODUCT-e7e9b2da");
		shopList.add("PRODUCT-1b6f4e9c");
		shopList.add("PRODUCT-76d37f7e");
		shopList.add("PRODUCT-3788a21d");
		assertEquals(customer.customerShoppingListArray(),shopList);
	}	
	
	@Test //Check that the ID of first customer listed in txt file is same for both methods that obtain the ID
	public void extraTest3() throws IOException {
		
		Customer customer = new Customer();
		//expected vs. actual
		assertEquals(customer.searchCustomerDatabase().get(0).get(0), customer.allCustomerIDs().get(0));
	}
	
	
	@Test //Let an admin add i.e. create another admin, update their information, and then delete them from the database
	public void extraTest4() throws IOException {
		Admin admin = new Admin(); 
		
		String uniqueID = UUID.randomUUID().toString();
		String[] parts = uniqueID.split("-");
		String iD = parts[0];
		String  IdString = "ADMIN-"+iD;
		String adminIdString = IdString;
		String adminString = adminIdString + "," + "Sauron" + "," + "oneRingtoRuleThem@email.com"
		+ "," + "inTheDarknessBindThem";	
	
		admin.addAdmin(adminString);
		
		assert(admin.adminHash().containsValue("inTheDarknessBindThem"));	

		String adminStringUpdated = adminIdString + "," + "Sauron of Middle Earth" + "," + "oneRingtoRuleThem@email.com"
				+ "," + "inTheDarknessBindThem";
		
		admin.updateAdminInfo(adminStringUpdated, adminIdString);
		
		admin.deleteAdmin(adminIdString);
	}
	
	
	
}
