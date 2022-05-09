package ProjectPackageGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import normalClasses.Customer;
import normalClasses.Products;
import normalClasses.Store;

public class GUICustomerStoreLists extends JPanel implements ActionListener {

	private JTextField searchField = new JTextField(); //
	private JButton btnBack; //
    JPanel btnPanel = new JPanel();
	JPanel btnPanel_1 = new JPanel();
    JPanel middlePanel = new JPanel();
    public static String searched;
  
  
	public GUICustomerStoreLists() throws IOException {
		 
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
	    
	    JLabel lblNewLabel = new JLabel(Customer.getCustomerName()+ "'s Stores with Shopping Lists");
	    panel.add(lblNewLabel);
	      
	    
		btnBack.addActionListener(this);
	
		//used to display buttons of stores to view shopping lists
	   middlePanel.add(showStoreNameButtons(storeNames()));
	   
	
		
	}
	
	public JPanel showStoreNameButtons(ArrayList<String> input) throws IOException {

		List<JButton> storeName = new ArrayList<>();
		 
		for (int i = 0; i < input.size(); i++) {		    
			storeName.add(new JButton(input.get(i)));
			storeName.get(i).addActionListener(this);
		    btnPanel.add(storeName.get(i)); 
		}
		return btnPanel;
	}
	
	
	
	public static ArrayList<String> storeNames() throws IOException{
		ArrayList<String> storeName = new ArrayList<>();

		for (int i = 0; i < Store.allStoreIDs().size(); i++) {	
	        File file = new File("data/CustomerShoppingLists/"+Customer.getCustomerID()+"/"+Store.allStoreIDs().get(i)+".txt");
	        if (file.exists()) {
	        	storeName.add(Store.getStoreName(Store.allStoreIDs().get(i), "data/Stores.txt"));
	        }
		}
		return storeName;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		
		if (e.getSource() == btnBack) {
			try {
				mainFrame.frame.getContentPane().add(new GUICustomerIntroMenu());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		else{
			//Products.productViewedID = Products.getProductID(e.getActionCommand().toString());
			try {
				Products.currentStoreID = Store.getStoreID(e.getActionCommand().toString(), "data/Stores.txt");
				mainFrame.frame.getContentPane().add(new GUIShoppingList());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		
	}

}
