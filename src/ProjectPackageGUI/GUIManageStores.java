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

import normalClasses.Admin;
import normalClasses.Manager;
import normalClasses.Products;
import normalClasses.Store;
import normalClasses.StoreSearch;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class GUIManageStores extends JPanel implements ActionListener{

	List<JButton> updateStore = new ArrayList<>();
	List<JButton> storeProducts = new ArrayList<>();
	JButton btnGoBack = new JButton();
	/**
	 * Create the panel. 
	 * @throws IOException 
	 */
	public GUIManageStores() throws IOException {
		setBackground(new Color(135, 206, 250));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		add(panel);
				
		btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(this);
		add(btnGoBack);
		
		JLabel lblNewLabel = new JLabel("All SmartShopper Stores");
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
		List<JLabel> storeManagers = new ArrayList<>();
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
		    
		    //List all store managers
		    String managerString = "No Manager Assigned";
		    if (Manager.allManagerStoreIDs().contains(Store.getStoreID(StoreSearch.allStoreNames("data/Stores.txt").get(i), "data/Stores.txt"))) {
		    	managerString = Manager.getManagerName(Store.getStoreID(StoreSearch.allStoreNames("data/Stores.txt").get(i), "data/Stores.txt"));
		    }
		    
		    storeManagers.add(new JLabel("Manager: " + managerString));
		    storeManagers.get(i).setFont(new Font("Serif", Font.BOLD, 15));
		    storeListPane.add(storeManagers.get(i));
		    
		    updateStore.add(new JButton("Update " + StoreSearch.allStoreNames("data/Stores.txt").get(i)));
		    updateStore.get(i).setBounds(90, 170+i*100, 200, 30);
		    updateStore.get(i).addActionListener(this);
		    updateStore.get(i).setBackground(Color.GREEN);
			storeListPane.add(updateStore.get(i)); 
		    
			storeProducts.add(new JButton("View " + StoreSearch.allStoreNames("data/Stores.txt").get(i) + " Products"));
			storeProducts.get(i).setBounds(90, 170+i*100, 200, 30);
			storeProducts.get(i).addActionListener(this);
			storeProducts.get(i).setBackground(Color.CYAN);
			storeListPane.add(storeProducts.get(i));
			
			space.add(new JLabel("                                                    "));
			storeListPane.add(space.get(i));
		} 
		return storeListPane;
}
	 
	//Edit this to show suggested products
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnGoBack) {
			mainFrame.frame.getContentPane().add(new GUIAdminMenu());
			this.setVisible(false);
		}
 
		//go to page that displays products using the store ID of chosen store
		for (int i = 0; i < updateStore.size(); i++ ) {
			if (e.getSource() == updateStore.get(i)) {
	
				String[] parts = updateStore.get(i).getText().split(" ");
				String storeName = parts[parts.length-2];
				try {
					Store.justCreated = false;
					Admin.currentStore = Store.getStoreID(storeName, "data/Stores.txt"); //get ID of store being viewed
					mainFrame.frame.getContentPane().add(new GUIStoreUpdateInfo()); //change to update or delete store info
					this.setVisible(false);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
		 
			}
		}
 
		for (int i = 0; i < storeProducts.size(); i++ ) {
			if (e.getSource() == storeProducts.get(i)) {
	
				String[] parts = storeProducts.get(i).getText().split(" ");
				String storeName = parts[parts.length-2];
				try {
					Store.justCreated = false;
					Admin.currentStore = Store.getStoreID(storeName, "data/Stores.txt"); //get ID of store being viewed
					Products.currentStoreID = Admin.currentStore;
					mainFrame.frame.getContentPane().add(new GUIProductManagement()); //change to page that displays all store's products
					this.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}  
		}
	}
	
	
}
