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

import normalClasses.Customer;
import normalClasses.Products;
import normalClasses.Store;

import javax.swing.JButton;

public class GUIViewProductInfo extends JPanel implements ActionListener{

	JLabel storeName = new JLabel();
	JLabel nameLabel = new JLabel();
	JLabel priceLabel = new JLabel();
	JLabel stockLabel = new JLabel();
	JButton btnAddProduct = new JButton();
	JButton btnGoBack = new JButton();
	JLabel sizeLabel = new JLabel();
	JTextPane productDescrip = new JTextPane();
	JLabel categoryLabel = new JLabel();
	JLabel aisleLabel = new JLabel();
	JButton btnMenu = new JButton();
	JLabel resultLabel = new JLabel();
	
	public GUIViewProductInfo() throws IOException {
		this.setBackground(Color.PINK);
		setLayout(null);
		
		storeName = new JLabel(Store.getStoreName(Products.currentStoreID, "data/Stores.txt"));
		storeName.setFont(new Font("Tahoma", Font.BOLD, 15));
		storeName.setBounds(10, 0, 183, 23);
		add(storeName);
		
		nameLabel = new JLabel(Products.getProductName(Products.productViewedID));
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		nameLabel.setBounds(170, 34, 231, 29);
		add(nameLabel);
		
		priceLabel = new JLabel("Price: " + Products.getProductPrice(Products.productViewedID));
		priceLabel.setBounds(76, 74, 129, 14);
		add(priceLabel);
		
		stockLabel = new JLabel("Stock: " + Products.getProductStock(Products.productViewedID));
		stockLabel.setBounds(276, 124, 114, 14);
		add(stockLabel);
		
		btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(this);
			
		btnAddProduct.setBounds(171, 299, 129, 46);
		add(btnAddProduct);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(0, 311, 106, 23);
		btnGoBack.addActionListener(this);
		add(btnGoBack);
		
		sizeLabel = new JLabel("Size: " + Products.getProductSize(Products.productViewedID));
		sizeLabel.setBounds(76, 124, 129, 14);
		add(sizeLabel);
		
		productDescrip = new JTextPane();
		productDescrip.setText(Products.getProductDescription(Products.productViewedID));
		productDescrip.setBounds(76, 149, 303, 106);
		add(productDescrip);
		productDescrip.setEditable(false);
		
		categoryLabel = new JLabel("Category: " +Products.getProductCategory(Products.productViewedID));
		categoryLabel.setBounds(276, 74, 300, 14);
		add(categoryLabel);
		
		aisleLabel = new JLabel("Aisle: " + Products.getProductAisle(Products.productViewedID));
		aisleLabel.setBounds(355, 11, 46, 14);
		add(aisleLabel);
		
		btnMenu = new JButton("Menu");
		btnMenu.setBounds(375, 311, 89, 23);
		btnMenu.addActionListener(this);
		add(btnMenu);
		
		resultLabel.setBounds(148, 255, 231, 31);
		add(resultLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGoBack) {	
			try {
				if (Products.suggestedProduct == false) {
				mainFrame.frame.getContentPane().add(new GUIStoreProducts());
				this.setVisible(false);
				}
				else {
					mainFrame.frame.getContentPane().add(new GUIShoppingList());
					this.setVisible(false);
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if (e.getSource() == btnMenu) {
			try {
				mainFrame.frame.getContentPane().add(new GUICustomerIntroMenu());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		 
		else if (e.getSource() == btnAddProduct) {
			try {
				Customer.createShoppingList(Products.productViewedID); //get ID of the product and place in shopping list
				//Customer.createShoppingList(Products.getProductStringInfo(Products.productViewedID));
				resultLabel.setText("Product Added to Shopping List");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		 
	}
}


