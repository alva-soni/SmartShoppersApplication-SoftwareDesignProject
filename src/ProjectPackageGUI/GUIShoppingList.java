package ProjectPackageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Component;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

import normalClasses.Customer;
import normalClasses.Products;
import normalClasses.Store;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class GUIShoppingList extends JPanel implements ActionListener{

	List<JButton> viewStore = new ArrayList<>();
	List<JButton> saveStore = new ArrayList<>();
	JButton btnGoBack = new JButton();
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public GUIShoppingList() throws IOException {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		add(panel);
		
		JScrollPane shoppingListArea = new JScrollPane(showStoreList()); 
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(this);
		add(btnGoBack);
		
		JLabel lblNewLabel = new JLabel(Store.getStoreName(Products.currentStoreID, "data/Stores.txt") + " Suggested Products");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		
		JLabel suggestedProductsLabel = new JLabel("Your " + Store.getStoreName(Products.currentStoreID, "data/Stores.txt") + " Shopping List");
		suggestedProductsLabel.setForeground(Color.BLACK);
		suggestedProductsLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		suggestedProductsLabel.setBackground(Color.CYAN);
		
		JScrollPane suggestProductsArea = new JScrollPane(showSuggestedProducts());

		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(suggestedProductsLabel)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
							.addComponent(btnGoBack))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(suggestProductsArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
								.addComponent(shoppingListArea, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
							.addContainerGap())))
		); 
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGoBack)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(suggestProductsArea, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(suggestedProductsLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(shoppingListArea, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					)
		);
		panel.setLayout(gl_panel);

	}
 
	public JPanel showStoreList() throws IOException {

		List<JLabel> productNames = new ArrayList<>();
		List<JLabel> productCategory = new ArrayList<>();
		List<JLabel> productAisle = new ArrayList<>();
		List<JLabel> productPrice = new ArrayList<>();
		List<JButton> removeProduct = new ArrayList<>();
		List<JLabel> space = new ArrayList<>();

		
		JPanel shoppingListScrollPane = new JPanel();
		shoppingListScrollPane.setLayout(new BoxLayout(shoppingListScrollPane, BoxLayout.PAGE_AXIS));
		
	        // add list to frame
		for (int i = 0; i < Customer.orderedShoppingList().size(); i++) {
	        Border border = BorderFactory.createLineBorder(Color.PINK, 5);
	        //List all store names
	        productNames.add(new JLabel(Products.getProductName(Customer.orderedShoppingList().get(i))));
	        productNames.get(i).setFont(new Font("Serif", Font.BOLD, 16));
	        productNames.get(i).setBorder(border);
			shoppingListScrollPane.add(productNames.get(i));

			 
		    //List all product prices
			productPrice.add(new JLabel(Products.getProductPrice(Customer.orderedShoppingList().get(i))));
			productPrice.get(i).setFont(new Font("Serif", Font.BOLD, 14));
		   shoppingListScrollPane.add(productPrice.get(i));
		   
		   //List all product categories
			productCategory.add(new JLabel(Products.getProductCategory(Customer.orderedShoppingList().get(i))));
			productCategory.get(i).setFont(new Font("Serif", Font.BOLD, 14));
		   shoppingListScrollPane.add(productCategory.get(i));
		    
		    //List all product locations by aisle
		    productAisle.add(new JLabel("Aisle: " + Products.getProductAisle(Customer.orderedShoppingList().get(i))));
		    productAisle.get(i).setFont(new Font("Serif", Font.BOLD, 14));
		    shoppingListScrollPane.add(productAisle.get(i));
		    
		    //remove product from Shopping List
		    removeProduct.add(new JButton("Remove " + Products.getProductName(Customer.orderedShoppingList().get(i))));
		    removeProduct.get(i).addActionListener(this);
		    shoppingListScrollPane.add(removeProduct.get(i));
		    
		    space.add(new JLabel("                                                    "));
		    shoppingListScrollPane.add(space.get(i));
		}
		return shoppingListScrollPane; 
}
	 
	//Edit this to show suggested products
	public JPanel showSuggestedProducts() throws IOException {

		List<JLabel> productNames = new ArrayList<>();
		List<JLabel> productCategory = new ArrayList<>();
		List<JLabel> productAisle = new ArrayList<>();
		List<JButton> addProduct = new ArrayList<>();
		List<JLabel> productPrice = new ArrayList<>();
		List<JLabel> space = new ArrayList<>();

		
		JPanel suggestProductsPane = new JPanel();
		suggestProductsPane.setLayout(new BoxLayout(suggestProductsPane, BoxLayout.PAGE_AXIS));

		 
	        // add list to frame
	    // add list to frame
		if (!(Customer.suggestedProducts().size() < 3)) {
			for (int i = 0; i < Customer.suggestedProducts().size(); i++) {
		        Border border = BorderFactory.createLineBorder(Color.GRAY, 5);
		        //List all store names
		        String sameProduct = Customer.suggestedProducts().get(i);
		        productNames.add(new JLabel(Products.getProductName(sameProduct)));
		        productNames.get(i).setFont(new Font("Serif", Font.BOLD, 16));
		        productNames.get(i).setBorder(border);
		        suggestProductsPane.add(productNames.get(i));

				
			    //List all product prices
		        productPrice.add(new JLabel(Products.getProductPrice(sameProduct)));
		        productPrice.get(i).setFont(new Font("Serif", Font.BOLD, 14));
				suggestProductsPane.add(productPrice.get(i));
			   
			   //List all product categories
				productCategory.add(new JLabel(Products.getProductCategory(sameProduct)));
				productCategory.get(i).setFont(new Font("Serif", Font.BOLD, 14));
				suggestProductsPane.add(productCategory.get(i));
			    
			    //List all product locations by aisle
			    productAisle.add(new JLabel("Aisle: " + Products.getProductAisle(sameProduct)));
			    productAisle.get(i).setFont(new Font("Serif", Font.BOLD, 14));
			    suggestProductsPane.add(productAisle.get(i));
			    
			    //remove product from Shopping List
			    addProduct.add(new JButton("View " + Products.getProductName(sameProduct)));
			    addProduct.get(i).addActionListener(this);
			    suggestProductsPane.add(addProduct.get(i));
			    
			    space.add(new JLabel("                                                    "));
			    suggestProductsPane.add(space.get(i));
			}
		}
		
		else {
			suggestProductsPane.add(new JLabel("Not Enough Products to Suggest!"));
		}
		return suggestProductsPane; 
}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		if (e.getSource() == btnGoBack) {
			try {
				Products.categoryChosen = "";
				Products.productViewedID = "";
				mainFrame.frame.getContentPane().add(new GUICustomerStoreLists());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		else {
			try {
				//Products.productViewedID = Products.getProductID(e.getActionCommand().toString());
				String[] parts = e.getActionCommand().toString().split(" ",2);
				
				if (parts[0].equals("Remove")) {
					String productName = parts[1];
					Customer customer = new Customer();
					customer.removeProductFromList(Products.getProductID(productName));
					mainFrame.frame.getContentPane().add(new GUIShoppingList());
					showStoreList().revalidate();
				}
				
				else if (parts[0].equals("View")) {
					Products.suggestedProduct = true;
					String productName = parts[1];
					Products.productViewedID = Products.getProductID(productName);
					mainFrame.frame.getContentPane().add(new GUIViewProductInfo());
					this.setVisible(false);
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
	}
	
	
	
}
