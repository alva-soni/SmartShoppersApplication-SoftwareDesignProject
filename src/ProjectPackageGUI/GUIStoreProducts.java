package ProjectPackageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import normalClasses.Products;
import normalClasses.Store;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import java.awt.Choice;

public class GUIStoreProducts extends JPanel  implements ActionListener {
	private JTextField searchField = new JTextField(); //
	private JButton btnBack; //
    JPanel btnPanel = new JPanel();
	JPanel btnPanel_1 = new JPanel();
    JPanel middlePanel = new JPanel();
    public static String searched;

  
	public GUIStoreProducts() throws IOException {
		 
        List<JButton> storeName = new ArrayList<>();
        btnBack = new JButton("Go Back");
        middlePanel.setBackground(Color.PINK);
        FlowLayout fl_middlePanel = new FlowLayout();
        fl_middlePanel.setAlignment(FlowLayout.LEFT);
        middlePanel.setLayout(fl_middlePanel);
		//middlePanel.add(showProductNames()); //this is what displays the product names
		this.setLayout(new BorderLayout());
		this.add(btnBack, BorderLayout.SOUTH);
		btnPanel.setLayout(new GridLayout(0,2,10,60));
		JScrollPane scroll = new JScrollPane(middlePanel);
		 
		middlePanel.add(btnPanel_1);
		btnPanel_1.setLayout(new GridLayout(0, 2, 40, 60));
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	  
	    this.add(scroll);
	    
	    JPanel panel = new JPanel();
	    FlowLayout flowLayout = (FlowLayout) panel.getLayout();
	    flowLayout.setAlignment(FlowLayout.LEADING);
	    add(panel, BorderLayout.NORTH);
	    
	    JLabel lblNewLabel = new JLabel(Store.getStoreName(Products.currentStoreID, "data/Stores.txt"));
	    panel.add(lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("•");
	    panel.add(lblNewLabel_1);
	    
	      
	    
		btnBack.addActionListener(this);
	    
	   if (Products.categoryChosen.equals("")) {
		    //This pops up ONLY when a category is choen
		    JLabel categoryName = new JLabel("Results for: "+ searched);
		    categoryName.setVerticalAlignment(SwingConstants.TOP);
		    categoryName.setHorizontalAlignment(SwingConstants.RIGHT);
		    panel.add(categoryName);
		   middlePanel.add(showProductNames(Products.inputArr));
	   } 
	   else {
		    //This pops up ONLY when a category is choen
		    JLabel categoryName = new JLabel(Products.categoryChosen);
		    categoryName.setVerticalAlignment(SwingConstants.TOP);
		    categoryName.setHorizontalAlignment(SwingConstants.RIGHT);
		    panel.add(categoryName);
		   middlePanel.add(showProductNamesforCategory());
	   }
		
	}
	
	public JPanel showProductNames(ArrayList<String> input) throws IOException {

		List<JButton> productName = new ArrayList<>();
		 
		for (int i = 0; i < input.size(); i++) {		    
		    productName.add(new JButton(input.get(i)));
			productName.get(i).addActionListener(this);
		    btnPanel.add(productName.get(i)); 
		}
		return btnPanel;
	}
	
	// backup while I make edits to one above
	public JPanel showProductNamesforCategory() throws IOException {

		List<JButton> productName = new ArrayList<>();
		 
		for (int i = 0; i < categoryChosen().size(); i++) {		    
		    productName.add(new JButton(categoryChosen().get(i)));
			productName.get(i).addActionListener(this);
		    btnPanel.add(productName.get(i)); 
		}
		return btnPanel;
	}
	
	
	
	
	public static ArrayList<String> categoryChosen() throws IOException{
		ArrayList<String> productName = new ArrayList<>();
		
		for (int i = 0; i < Products.numberOfProducts(); i++) {	
			if (Products.categoryChosen.equals(Products.getProductCategory(Products.allProductNames().get(i)))) {
				productName.add(Products.allProductNames().get(i));
			}
		}
		return productName;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		
		if (e.getSource() == btnBack) {
			try {
				Products.suggestedProduct = false;
				Products.categoryChosen = "";
				Products.productViewedID = "";
				mainFrame.frame.getContentPane().add(new GUISearchProductMethod());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		else {
			try {
				Products.productViewedID = Products.getProductID(e.getActionCommand().toString());
				Products.suggestedProduct = false;
				mainFrame.frame.getContentPane().add(new GUIViewProductInfo());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
	}
 
}
 