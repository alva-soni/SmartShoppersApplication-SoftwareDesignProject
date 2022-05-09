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
import normalClasses.StoreSearch;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class GUISearchStoreList extends JPanel implements ActionListener{

	List<JButton> viewStore = new ArrayList<>();
	List<JButton> saveStore = new ArrayList<>();
	JButton btnGoBack = new JButton();
	/**
	 * Create the panel. 
	 * @throws IOException 
	 */
	public GUISearchStoreList() throws IOException {
		setBackground(Color.PINK);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		add(panel);
				
		btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(this);
		add(btnGoBack);
		
		JLabel lblNewLabel = new JLabel("SmartShopper Stores Near You");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

		JScrollPane suggestProductsArea = new JScrollPane(showStoreList()); //HERE FOR SHOPPING LIST PRODUCTS
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnGoBack))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(suggestProductsArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
							)))));
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGoBack)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(suggestProductsArea, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
		));
		panel.setLayout(gl_panel);

	}

	public JPanel showStoreList() throws IOException {

		List<JLabel> storeNames = new ArrayList<>();
		List<JLabel> storeHours = new ArrayList<>();
		List<JLabel> storeLocations = new ArrayList<>();
		List<JLabel> space = new ArrayList<>();


		JPanel storeListPane = new JPanel();
		storeListPane.setLayout(new BoxLayout(storeListPane, BoxLayout.PAGE_AXIS)); 
	        // add list to frame
		for (int i = 0; i < StoreSearch.numberOfStores("data/Stores.txt"); i++) {
	        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
	        //List all store names
			storeNames.add(new JLabel(StoreSearch.allStoreNames("data/Stores.txt").get(i)));
		    storeNames.get(i).setFont(new Font("Serif", Font.BOLD, 16));
		    storeNames.get(i).setBorder(border);
		    storeNames.get(i).setBounds(50, 110+i*100, 190, 30);
		    storeListPane.add(storeNames.get(i));
 
		    //List all store hours
		    storeHours.add(new JLabel(StoreSearch.allStoreHours("data/Stores.txt").get(i)));
		    storeHours.get(i).setFont(new Font("Serif", Font.BOLD, 15));
		    storeHours.get(i).setBounds(50, 150+i*100, 200, 30);
		    storeListPane.add(storeHours.get(i));
		    
		    //List all store locations
		    storeLocations.add(new JLabel("<html><u>" + StoreSearch.allStoreAddresses("data/Stores.txt").get(i) + "</u></html>" ));
		    storeLocations.get(i).setFont(new Font("Serif", Font.BOLD, 15));
		    storeLocations.get(i).setBounds(50, 170+i*100, 350, 30);
		    storeListPane.add(storeLocations.get(i));
		    
		    viewStore.add(new JButton("View " + StoreSearch.allStoreNames("data/Stores.txt").get(i)));
		    viewStore.get(i).setBounds(90, 170+i*100, 200, 30);
			viewStore.get(i).addActionListener(this);
			viewStore.get(i).setBackground(Color.GREEN);
			storeListPane.add(viewStore.get(i)); 
		    
		    saveStore.add(new JButton("Save " + StoreSearch.allStoreNames("data/Stores.txt").get(i)));
		    saveStore.get(i).setBounds(90, 170+i*100, 200, 30);
			saveStore.get(i).addActionListener(this);
			saveStore.get(i).setBackground(Color.CYAN);
			storeListPane.add(saveStore.get(i));
			
			space.add(new JLabel("                                                    "));
			storeListPane.add(space.get(i));
		} 
		return storeListPane;
}
	 
	//Edit this to show suggested products
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnGoBack) {
			mainFrame.frame.getContentPane().add(new GUIStoreSearch());
			this.setVisible(false);
		}
 
		//go to page that displays products using the store ID of chosen store
		for (int i = 0; i < viewStore.size(); i++ ) {
			if (e.getSource() == viewStore.get(i)) {
	
				String[] parts = viewStore.get(i).getText().split(" ");
				String storeName = parts[parts.length-2];
				try {
					Products.currentStoreID = Store.getStoreID(storeName, "data/Stores.txt");
					//mainFrame.frame.getContentPane().add(new GUIStoreProducts());
					Store.savedStore = false;
					mainFrame.frame.getContentPane().add(new GUISearchProductMethod());
					this.setVisible(false);
					//System.out.println(Products.currentStoreID);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//	System.out.print(Store.getStoreStringInfo(storeName));
		 
			} 
		}
 
		for (int i = 0; i < saveStore.size(); i++ ) {
			if (e.getSource() == saveStore.get(i)) {
	
				String[] parts = saveStore.get(i).getText().split(" ");
				String storeName = parts[parts.length-2];
				try {
						Customer customer = new Customer();
						customer.saveFavoriteStoreFile(Customer.getCustomerID(), storeName);
						//System.out.println(storeName);
					
				//System.out.println(Customer.getCustomerID());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				saveStore.get(i).setText("Saved");
				saveStore.get(i).setEnabled(false);
			}  
		}
	}
	
	
}
