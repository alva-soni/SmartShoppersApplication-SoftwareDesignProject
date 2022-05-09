package ProjectPackageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.UUID;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import normalClasses.Products;
import normalClasses.Store;

import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class GUIAddProduct extends JPanel implements ActionListener{

	JLabel storeName = new JLabel();
	JTextField nametxt = new JTextField();
	JLabel priceLabel = new JLabel();
	JLabel stockLabel = new JLabel();
	JButton btnUpdateProduct = new JButton();
	private JButton btnAddProduct;
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
	 JLabel lblNewLabel = new JLabel(); 
	 JCheckBox bxChangeCat = new JCheckBox(); 
	 private JCheckBox bxSale = new JCheckBox(); 
	 private JLabel resultLabel = new JLabel(); 
	 private JLabel nameLabel = new JLabel();
	
	@SuppressWarnings("unchecked")
	public GUIAddProduct() throws IOException {
		setBackground(new Color(135, 206, 250));
		setLayout(null);
		
		storeName = new JLabel(Store.getStoreName(Products.currentStoreID, "data/Stores.txt") + " - Add a Product");
		storeName.setFont(new Font("Tahoma", Font.BOLD, 15));
		storeName.setBounds(10, 0, 290, 23);
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
		
		btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(this);
			
		btnAddProduct.setBounds(171, 299, 129, 46);
		add(btnAddProduct);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(0, 311, 106, 23);
		btnGoBack.addActionListener(this);
		add(btnGoBack);
		
		sizeLabel = new JLabel("Size: ");
		sizeLabel.setBounds(21, 140, 50, 14);
		add(sizeLabel);
		
		productDescrip = new JTextPane();
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
		bxChangeCat.setBounds(416, 105, 28, 23);
		add(bxChangeCat);
		
		bxSale = new JCheckBox("Sale");
		bxSale.setFont(new Font("Tahoma", Font.PLAIN, 9));
		bxSale.setBounds(176, 70, 44, 23);
		add(bxSale);
		
		resultLabel = new JLabel("Click to Add Product");
		resultLabel.setBounds(181, 354, 129, 14);
		add(resultLabel);
		
		nameLabel = new JLabel("Product Name:");
		nameLabel.setBounds(34, 44, 89, 14);
		add(nameLabel);

	}

	
	 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGoBack) {	
			
			if (Store.justCreated) {
				try {
					mainFrame.frame.getContentPane().add(new GUICreateStore());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.setVisible(false);
			}
			else {
				try {
					mainFrame.frame.getContentPane().add(new GUIProductManagement());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.setVisible(false);
			}
		}
		
		else if (e.getSource() == btnMenu) {
			mainFrame.frame.getContentPane().add(new GUIAdminMenu()); //change to make it depend on if Admin or Manager is signed in
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnAddProduct) {
				
			try { //make sure no textfields are empty
				if (!productDescrip.getText().equals("") &&  !nametxt.getText().equals("") && !txtAisle.getText().equals("") && !txtSize.getText().equals("") && !txtPrice.getText().equals("")) {
					String uniqueID = UUID.randomUUID().toString();
					String[] parts = uniqueID.split("-");
					String iD = parts[0];
					if (!bxSale.isSelected()) { //product no longer on sale
						if (bxChangeCat.isSelected()) {
							String catPicked = String.valueOf(comboBox.getSelectedItem());
							String updatedInfo = "PRODUCT"+"-"+ iD + "," + nametxt.getText() + "," +  "$" + txtPrice.getText() + "," + catPicked + "," + productDescrip.getText() + "," + txtStock.getText() + "," + txtSize.getText() + "," + txtAisle.getText();
							Products.addProduct(updatedInfo, Products.currentStoreID);
						}
						else if (!bxChangeCat.isSelected() && !txtCategory.getText().equals("")) {
							String updatedInfo = "PRODUCT"+"-"+ iD + "," + nametxt.getText() + "," + "$" + txtPrice.getText() + "," + txtCategory.getText() + "," + productDescrip.getText() + "," + txtStock.getText() + "," + txtSize.getText() + "," + txtAisle.getText();
							Products.addProduct(updatedInfo, Products.currentStoreID);
						}
					}
					else if (bxSale.isSelected()) { //put product on sale
						
						if (bxChangeCat.isSelected()) {
							String catPicked = String.valueOf(comboBox.getSelectedItem());
							String updatedInfo = "PRODUCT"+"-"+ iD + "," + nametxt.getText() + "," + "SALE " + "$" + txtPrice.getText() + "," + catPicked + "," + productDescrip.getText() + "," + txtStock.getText() + "," + txtSize.getText() + "," + txtAisle.getText();
							Products.addProduct(updatedInfo, Products.currentStoreID);
						}
						else if (!bxChangeCat.isSelected() && !txtCategory.getText().equals("")) {
							String updatedInfo = "PRODUCT"+"-"+ iD + "," + nametxt.getText() + "," + "SALE " +  "$" +txtPrice.getText() + "," + txtCategory.getText() + "," + productDescrip.getText() + "," + txtStock.getText() + "," + txtSize.getText() + "," + txtAisle.getText();
							Products.addProduct(updatedInfo, Products.currentStoreID);
						}

					}
					mainFrame.frame.getContentPane().add(new GUIAddProduct()); 
					this.setVisible(false);
				}
				
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			
		}
}
	
}



