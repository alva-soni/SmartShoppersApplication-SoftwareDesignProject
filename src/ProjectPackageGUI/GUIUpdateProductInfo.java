package ProjectPackageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import normalClasses.DamageControl;
import normalClasses.Products;
import normalClasses.Store;

import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class GUIUpdateProductInfo extends JPanel implements ActionListener{

	JLabel storeName = new JLabel();
	JTextField nametxt = new JTextField();
	JLabel priceLabel = new JLabel();
	JLabel stockLabel = new JLabel();
	JButton btnUpdateProduct = new JButton();
	JButton btnGoBack = new JButton();
	JLabel sizeLabel = new JLabel();
	JTextPane productDescrip = new JTextPane();
	JLabel categoryLabel = new JLabel();
	JLabel aisleLabel = new JLabel();
	JButton btnMenu = new JButton();
	JTextField txtSize = new JTextField();
	 JTextField txtPrice = new JTextField();
	 JTextField txtAisle  = new JTextField();
	 JTextField txtStock  = new JTextField();
	 JTextField txtCategory = new JTextField();
	 JComboBox comboBox = new JComboBox();
	 JLabel lblNewLabel;
	 JCheckBox bxChangeCat;
	 private JCheckBox bxSale;
	 private JLabel resultLabel;
	 private JButton btnDeleteProduct;
	 private JLabel ifLabel;
	
	@SuppressWarnings("unchecked")
	public GUIUpdateProductInfo() throws IOException {
		setBackground(new Color(135, 206, 250));
		setLayout(null);
		
		storeName = new JLabel(Store.getStoreName(Products.currentStoreID, "data/Stores.txt"));
		storeName.setFont(new Font("Tahoma", Font.BOLD, 15));
		storeName.setBounds(10, 0, 183, 23);
		add(storeName);
		
		nametxt = new JTextField();
		nametxt.setFont(new Font("Tahoma", Font.BOLD, 17));
		nametxt.setBounds(129, 34, 231, 29);
		add(nametxt);
		
		priceLabel = new JLabel("Price: ");
		priceLabel.setBounds(21, 74, 50, 14);
		add(priceLabel);
		
		stockLabel = new JLabel("Stock: ");
		stockLabel.setBounds(244, 140, 56, 14);
		add(stockLabel);
		
		btnUpdateProduct = new JButton("Update Product");
		btnUpdateProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdateProduct.setBackground(new Color(173, 255, 47));
		btnUpdateProduct.addActionListener(this);
			
		btnUpdateProduct.setBounds(171, 299, 129, 46);
		add(btnUpdateProduct);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(0, 311, 106, 23);
		btnGoBack.addActionListener(this);
		add(btnGoBack);
		
		sizeLabel = new JLabel("Size: ");
		sizeLabel.setBounds(21, 140, 50, 14);
		add(sizeLabel);
		
		productDescrip = new JTextPane();
		productDescrip.setText(Products.getProductDescription(Products.productViewedID));
		productDescrip.setBounds(76, 182, 303, 106);
		add(productDescrip);
		
		categoryLabel = new JLabel("Category: ");
		categoryLabel.setBounds(226, 74, 74, 14);
		add(categoryLabel);
		
		aisleLabel = new JLabel("Aisle: ");
		aisleLabel.setBounds(21, 112, 50, 14);
		add(aisleLabel);
		
		btnMenu = new JButton("Menu");
		btnMenu.setBounds(375, 311, 89, 23);
		btnMenu.addActionListener(this);
		add(btnMenu);
		
		txtSize = new JTextField();
		txtSize.setBounds(76, 137, 97, 20);
		add(txtSize);
		txtSize.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(76, 71, 97, 20);
		add(txtPrice);
		txtPrice.setColumns(10);
		
		txtAisle = new JTextField();
		txtAisle.setBounds(76, 109, 98, 20);
		add(txtAisle);
		txtAisle.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(289, 137, 108, 20);
		add(txtStock);
		txtStock.setColumns(10);
		
		txtCategory = new JTextField();
		txtCategory.setBounds(289, 71, 112, 20);
		add(txtCategory);
		txtCategory.setColumns(10);
		
		comboBox = new JComboBox(GUIProductManagement.productCategories().toArray());
		comboBox.setBounds(289, 105, 112, 22);
		add(comboBox);
		
		lblNewLabel = new JLabel("Change Category:");
		lblNewLabel.setBounds(184, 109, 106, 14);
		add(lblNewLabel);
		
		bxChangeCat = new JCheckBox("");
		bxChangeCat.setBounds(423, 105, 28, 23);
		add(bxChangeCat);
		
		bxSale = new JCheckBox("Sale");
		bxSale.setFont(new Font("Tahoma", Font.PLAIN, 9));
		bxSale.setBounds(176, 70, 44, 23);
		add(bxSale);
		
		resultLabel = new JLabel("");
		resultLabel.setBounds(211, 349, 46, 14);
		add(resultLabel);
		
		btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.setBackground(Color.RED);
		btnDeleteProduct.setBounds(171, 395, 129, 23);
		add(btnDeleteProduct);
		btnDeleteProduct.addActionListener(this);
		
		ifLabel = new JLabel("If deleting a product, you will be redirected to the previous page.");
		ifLabel.setBounds(59, 380, 405, 14);
		add(ifLabel);
		

		currentInfo();

	}

	public void currentInfo() throws IOException {
		txtSize.setText(Products.getProductSize(Products.productViewedID));
		txtPrice.setText(Products.getProductPrice(Products.productViewedID));
		txtAisle.setText(String.valueOf(Products.getProductAisle(Products.productViewedID)));
		txtStock.setText(String.valueOf(Products.getProductStock(Products.productViewedID)));
		txtCategory.setText(Products.getProductCategory(Products.productViewedID));
		nametxt.setText(Products.getProductName(Products.productViewedID));
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGoBack) {	
			try {
				mainFrame.frame.getContentPane().add(new GUIProductListings());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnMenu) {
			mainFrame.frame.getContentPane().add(new GUIAdminMenu()); //change to make it depend on if Admin or Manager is signed in
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnUpdateProduct) {
				
			try { //make sure no textfields are empty
				if (!productDescrip.getText().equals("") &&  !nametxt.getText().equals("") && !txtAisle.getText().equals("") && !txtSize.getText().equals("") && !txtPrice.getText().equals("") && !txtCategory.getText().equals("")) {
					
					if (!bxSale.isSelected() && Products.getProductPrice(Products.productViewedID).contains("SALE")) { //product no longer on sale
						if (bxChangeCat.isSelected()) {
							String catPicked = String.valueOf(comboBox.getSelectedItem());
							String [] arr = Products.getProductPrice(Products.productViewedID).split(" ", 2);
							String noSalePrice = arr[1];
							String updatedInfo = Products.productViewedID + "," + nametxt.getText() + "," + noSalePrice + "," + catPicked + "," + productDescrip.getText() + "," + txtStock.getText() + "," + txtSize.getText() + "," + txtAisle.getText();
							Products.updateProductInfo(updatedInfo, Products.productViewedID, Products.currentStoreID);
						}
						else if (!bxChangeCat.isSelected()) {
							String [] arr = Products.getProductPrice(Products.productViewedID).split(" ", 2);
							String noSalePrice = arr[1];
							String updatedInfo = Products.productViewedID + "," + nametxt.getText() + "," + noSalePrice + "," + txtCategory.getText() + "," + productDescrip.getText() + "," + txtStock.getText() + "," + txtSize.getText() + "," + txtAisle.getText();
							Products.updateProductInfo(updatedInfo, Products.productViewedID, Products.currentStoreID);
						}
					}
					else if (bxSale.isSelected() && !Products.getProductPrice(Products.productViewedID).contains("SALE")) { //put product on sale
						
						if (bxChangeCat.isSelected()) {
							String catPicked = String.valueOf(comboBox.getSelectedItem());
							String updatedInfo = Products.productViewedID + "," + nametxt.getText() + "," + "SALE " + txtPrice.getText() + "," + catPicked + "," + productDescrip.getText() + "," + txtStock.getText() + "," + txtSize.getText() + "," + txtAisle.getText();
							Products.updateProductInfo(updatedInfo, Products.productViewedID, Products.currentStoreID);
						}
						else if (!bxChangeCat.isSelected()) {
							String updatedInfo = Products.productViewedID + "," + nametxt.getText() + "," + "SALE " + txtPrice.getText() + "," + txtCategory.getText() + "," + productDescrip.getText() + "," + txtStock.getText() + "," + txtSize.getText() + "," + txtAisle.getText();
							Products.updateProductInfo(updatedInfo, Products.productViewedID, Products.currentStoreID);
						}
					}
					else if (!bxSale.isSelected() && !Products.getProductPrice(Products.productViewedID).contains("SALE")) {
						
						if (bxChangeCat.isSelected()) {
							String catPicked = String.valueOf(comboBox.getSelectedItem());
							String updatedInfo = Products.productViewedID + "," + nametxt.getText() + "," + txtPrice.getText() + "," + catPicked + "," + productDescrip.getText() + "," + txtStock.getText() + "," + txtSize.getText() + "," + txtAisle.getText();
							Products.updateProductInfo(updatedInfo, Products.productViewedID, Products.currentStoreID);
						}
						else if (!bxChangeCat.isSelected()) {
							String updatedInfo = Products.productViewedID + "," + nametxt.getText() + "," + txtPrice.getText() + "," + txtCategory.getText() + "," + productDescrip.getText() + "," + txtStock.getText() + "," + txtSize.getText() + "," + txtAisle.getText();
							Products.updateProductInfo(updatedInfo, Products.productViewedID, Products.currentStoreID);
						}
					}
					mainFrame.frame.getContentPane().add(new GUIUpdateProductInfo()); 
					this.setVisible(false);
					resultLabel.setText("Product Information Updated!");
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if (e.getSource() == btnDeleteProduct) {
			
			try {
				DamageControl.removeProductFromShoppingList(Products.productViewedID,Products.currentStoreID );
				Products products = new Products();
				products.deleteProduct(Products.productViewedID, Products.currentStoreID);
				//testing below method with admin
				
				mainFrame.frame.getContentPane().add(new GUIProductManagement());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //change to make it depend on if Admin or Manager is signed in
			this.setVisible(false);
		}
}
	
}



