package ProjectPackageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import normalClasses.Products;
import normalClasses.Store;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;

public class GUISearchProductMethod extends JPanel implements ActionListener {
	private JTextField textField = new JTextField();
	private JLabel storeName, lblNewLabel_1, lblNewLabel, lblNewLabel_2, lblNewLabel_3;
	private JButton btnSearch = new JButton();
	private JComboBox comboBox = new JComboBox();
	private JButton btnSaleItems = new JButton(); 
	private JButton btnBack = new JButton();
	String cbOption;
	private JLabel resultLabel;
	private JButton btnRefresh = new JButton();
	private JButton btnMenu;
	
	@SuppressWarnings("unchecked")
	public GUISearchProductMethod() throws IOException {
		this.setBackground(Color.PINK);
		this.setLayout(null);
		
		storeName = new JLabel("<html><u>" + Store.getStoreName(Products.currentStoreID, "data/Stores.txt") + "</html></u>");
		storeName.setFont(new Font("Tahoma", Font.BOLD, 18));
		storeName.setBounds(10, 11, 286, 20);
		
		textField = new JTextField();
		textField.setBounds(10, 114, 197, 20);
		textField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(351, 113, 89, 23);
		
		comboBox = new JComboBox(productCategories().toArray());
		comboBox.setBounds(228, 113, 113, 22);
		
		btnSaleItems = new JButton("Sale Items");
		btnSaleItems.addActionListener(this);
		btnSaleItems.setBounds(150, 197, 132, 23);
		this.add(btnSaleItems);
		
		btnBack = new JButton("Go Back");
		btnBack.setBounds(333, 277, 107, 23);
		
		lblNewLabel_1 = new JLabel("View On-Sale Items");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(164, 171, 143, 14);
		this.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("Search for Products By Name or by Category");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(86, 52, 328, 14);
		this.add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel("Search by Name or Category");
		lblNewLabel_2.setBounds(39, 88, 214, 14);
		this.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Choose a Category");
		lblNewLabel_3.setBounds(239, 88, 124, 14);
		this.add(lblNewLabel_3);

		resultLabel = new JLabel();
		resultLabel.setBounds(105, 240, 250, 14);
		this.add(resultLabel);
		
		btnMenu = new JButton("Menu");
		btnMenu.setBounds(0, 277, 89, 23);
		add(btnMenu);
		
		addComponents();
		addActionEvent(); 
	}
	
	public void addComponents() {	
		this.add(storeName);
		this.add(textField);
		this.add(btnSearch);
		this.add(comboBox);
		this.add(btnBack);
		
		

		;

		//this.add(introJLabel);
		//this.add(errorLabel);
	}
	
	public void addActionEvent() {
		btnSearch.addActionListener(this);
		btnBack.addActionListener(this);
		btnMenu.addActionListener(this);

	}

	
	public static ArrayList<String> productCategories() throws IOException {
		ArrayList<String> cbList = new ArrayList<String>();
		
		for (int i = 0; i < Products.numberOfProducts(); i++) {		    
			if (!cbList.contains(Products.getProductCategory(Products.allProductNames().get(i)))) {
		    cbList.add(Products.getProductCategory(Products.allProductNames().get(i)));
			}
		}
		return cbList;
	}
	
	public static ArrayList<String> productNames(String ID) throws IOException {
		ArrayList<String> allNameStrings = new ArrayList<String>();
		
		for (int i =0; i<Products.numberOfProducts(); i++) {
			if (Products.allProductNames().get(i).toUpperCase().contains(ID.toUpperCase())) {
				allNameStrings.add(Products.allProductNames().get(i));
			}
		}
		return allNameStrings;
	}

	
	public static String productCategoriesTextInput(String ID) throws IOException {
			String answerString = "";
		for (int i = 0; i < productCategories().size(); i++) {		    
			if (productCategories().get(i).toUpperCase().equals(ID.toUpperCase())) {
		    	answerString = productCategories().get(i);
			}
		}
		return answerString;
	}
	 
	
	public static ArrayList<String> productSale() throws IOException {
		ArrayList<String> allNameStrings = new ArrayList<String>();
		
		 for (int i =0; i < Products.numberOfProducts(); i++) {
			 if (Products.searchProductDatabase().get(i).get(2).contains("SALE")) {
				  allNameStrings.add(Products.searchProductDatabase().get(i).get(1));
			 }
		 }
		 return allNameStrings;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnBack) {
			
				if (Store.savedStore) {
					Products.categoryChosen = "";
					try {
						mainFrame.frame.getContentPane().add(new GUICustomerSavedStores());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.setVisible(false);
				}
				else {
					Products.categoryChosen = "";
					try {
						mainFrame.frame.getContentPane().add(new GUISearchStoreList());
						this.setVisible(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		}
		
		else if (e.getSource() == btnMenu) {
			Products.categoryChosen = "";
			try {
				mainFrame.frame.getContentPane().add(new GUICustomerIntroMenu());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);

		}
		
		//shop by category
		else if (e.getSource() == btnSearch && textField.getText().isBlank()) {
			try {
				Products.categoryChosen = String.valueOf(comboBox.getSelectedItem());
				Products.inputArr = GUIStoreProducts.categoryChosen(); //because customer is searching via category, array of products to choose is from here
				mainFrame.frame.getContentPane().add(new GUIStoreProducts());
				
			} catch (IOException e1) {
			}
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnSearch && !textField.getText().isBlank()) {
			
		{
			Products.categoryChosen = "";

					try {
							if (!productCategoriesTextInput(textField.getText()).equals("")) {
									Products.categoryChosen = productCategoriesTextInput(textField.getText());
									Products.inputArr = GUIStoreProducts.categoryChosen();
									mainFrame.frame.getContentPane().add(new GUIStoreProducts());
									this.setVisible(false);
									resultLabel.setText("");
								}
								
								else if(productNames(textField.getText()).size() > 0){
									GUIStoreProducts.searched = textField.getText();
									Products.categoryChosen = "";
									Products.inputArr = productNames(textField.getText());
									mainFrame.frame.getContentPane().add(new GUIStoreProducts());
									this.setVisible(false);
									resultLabel.setText("");

								}
								else {
									resultLabel.setText("Enter a valid product name or category.");
								}
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
				
				
		}
		
		else if(e.getSource() == btnSaleItems) {
			Products.categoryChosen = "";
			GUIStoreProducts.searched = "PRODUCTS ON SALE";
			try {
				Products.inputArr = productSale();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				mainFrame.frame.getContentPane().add(new GUIStoreProducts());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
			resultLabel.setText("");	
			
		}
		
		
		
		
		//search by category
		/*
		if (e.getSource() == btnSearch && textField.getText().isBlank()) {
			try {
				Products.categoryChosen = String.valueOf(comboBox.getSelectedItem());
				Products.inputArr = GUIStoreProducts.categoryChosen(); //because customer is searching via category, array of products to choose is from here
				mainFrame.frame.getContentPane().add(new GUIStoreProducts());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		*/
	
		
	}
}
