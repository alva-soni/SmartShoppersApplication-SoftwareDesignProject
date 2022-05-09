package ProjectPackageGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.synth.ColorType;
import javax.swing.text.AttributeSet.ColorAttribute;

import normalClasses.Customer;
import normalClasses.CustomerStoreSearch;
import normalClasses.Products;
import normalClasses.Store;

import java.awt.font.TextAttribute;
import javax.swing.JScrollBar;
import javax.swing.JList;


public class GUICustomerSavedStores extends JPanel implements ActionListener {

	private JLabel headingLabel;
	JButton btnGoBack = new JButton();
	JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JSplitPane splitPane = new JSplitPane();
	List<JButton> viewStore = new ArrayList<>();
	List<JButton> removeStore = new ArrayList<>();
 

 
  
	
	public GUICustomerSavedStores() throws IOException{
		this.setBackground(Color.PINK);
 
		headingLabel = new JLabel("<html><u>" + "Saved Stores" + "</html></u>");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 18));
		btnGoBack = new JButton("Go Back");

		String filePathString = "data/favoriteStores/"+Customer.getCustomerID()+".txt";
		File file = new File(filePathString);

		if (!file.isFile() || (file.exists() && CustomerStoreSearch.numberOfStores(filePathString) == 0 )) {
			emptyStoreList();
		}
		else {
			 
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.PINK);
			add(panel);
					
			btnGoBack = new JButton("Go Back");
			btnGoBack.addActionListener(this);
			add(btnGoBack);
			
			JLabel lblNewLabel = new JLabel("<html><u>" + "Saved Stores" + "</html></u>");
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

	}
	
	
	public void addComponents() throws FileNotFoundException {
		setLayout(new GridLayout(0, 1, 0, 0));
		this.add(headingLabel);
		this.add(btnGoBack);
	} 
	
	
	public void addActionEvent() {
		btnGoBack.addActionListener(this);

	}

	public JPanel showStoreList() throws IOException {
		
			List<JLabel> storeNames = new ArrayList<>();
			List<JLabel> storeHours = new ArrayList<>();
			List<JLabel> storeLocations = new ArrayList<>();
			List<JLabel> space = new ArrayList<>();

			
			JPanel savedStoresPane = new JPanel();
			savedStoresPane.setLayout(new BoxLayout(savedStoresPane, BoxLayout.PAGE_AXIS)); 
			
			String id = Customer.getCustomerID();
		        
			for (int i = 0; i < CustomerStoreSearch.numberOfStores("data/favoriteStores/"+id+".txt"); i++) {
		        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
		        //List all store names
				storeNames.add(new JLabel(CustomerStoreSearch.allStoreNames("data/favoriteStores/"+id+".txt").get(i)));
			    storeNames.get(i).setFont(new Font("Serif", Font.BOLD, 16));
			    storeNames.get(i).setBorder(border);
			    savedStoresPane.add(storeNames.get(i));
 

			    //List all store hours
			    storeHours.add(new JLabel(CustomerStoreSearch.allStoreHours("data/favoriteStores/"+id+".txt").get(i)));
			    storeHours.get(i).setFont(new Font("Serif", Font.BOLD, 15));
			    savedStoresPane.add(storeHours.get(i));

			    
			    //List all store locations
			    storeLocations.add(new JLabel("<html><u>" + CustomerStoreSearch.allStoreAddresses("data/favoriteStores/"+id+".txt").get(i) + "</u></html>" ));
			    storeLocations.get(i).setFont(new Font("Serif", Font.BOLD, 15));
			    savedStoresPane.add(storeLocations.get(i));
			    
			    //viewStore.add(new JButton("View " + StoreSearch.allStoreNames("data/favoriteStores/"+id+".txt").get(i)));
			    viewStore.add(new JButton("View " + CustomerStoreSearch.allStoreNames("data/favoriteStores/"+id+".txt").get(i)));
				viewStore.get(i).addActionListener(this);
				viewStore.get(i).setBackground(Color.GREEN);
				savedStoresPane.add(viewStore.get(i)); 
			    
			    //removeStore.add(new JButton("Remove " + StoreSearch.allStoreNames("data/favoriteStores/"+id+".txt").get(i)));
			    removeStore.add(new JButton("Remove " + CustomerStoreSearch.allStoreNames("data/favoriteStores/"+id+".txt").get(i)));
				removeStore.get(i).addActionListener(this);
				removeStore.get(i).setBackground(Color.RED);

				savedStoresPane.add(removeStore.get(i));
				
				space.add(new JLabel("                                                    "));
				savedStoresPane.add(space.get(i));
			} 
			
			return savedStoresPane;
	}
	
	
	
	
	public void emptyStoreList() {
		btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(this);
		add(btnGoBack);
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
		this.setLayout(null);
		this.add(headingLabel);
		this.add(btnGoBack);
		JLabel nothing = new JLabel("No Saved Stores");
		nothing.setBounds(180, 200, 150, 30); 
		this.add(nothing);
		headingLabel.setBounds(180, 40, 400, 30);
		headingLabel.setFont(new Font("Serif", Font.BOLD, 25));
		nothing.setFont(new Font("Serif", Font.BOLD, 20));
		nothing.setBorder(border);
		btnGoBack.setBounds(150, 400, 200, 30);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnGoBack) {
			try {
				mainFrame.frame.getContentPane().add(new GUICustomerIntroMenu());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}

		for (int i = 0; i < viewStore.size(); i++ ) {
			if (e.getSource() == viewStore.get(i)) {
	
				String[] parts = viewStore.get(i).getText().split(" ");
				String storeName = parts[parts.length-2];
				try {
					Products.currentStoreID = Store.getStoreID(storeName, "data/Stores.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//mainFrame.frame.getContentPane().add(new GUIStoreProducts());
				try {
					Store.savedStore = true;
					mainFrame.frame.getContentPane().add(new GUISearchProductMethod());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.setVisible(false);
//	System.out.print(Store.getStoreStringInfo(storeName));
		 
			}
		}
		//removing a saved store removes it from the shopping list and from the saved stores list
		for (int i = 0; i < removeStore.size(); i++ ) {
			if (e.getSource() == removeStore.get(i)) {
	
				String[] parts = removeStore.get(i).getText().split(" ");
				String storeName = parts[parts.length-2];
				try {
					
						Customer customer = new Customer();
						//method that takes store name and entire store info and replaces it with empty string
						customer.removeFavoriteStore(Customer.getCustomerID(), storeName);
						File removeShoppingList = new File("data/CustomerShoppingLists/"+Customer.getCustomerID()+"/" + Products.currentStoreID +".txt");
						removeShoppingList.delete();
						removeStore.get(i).setText("Removed");
						removeStore.get(i).setEnabled(false);
						viewStore.get(i).setEnabled(false);

				//System.out.println(Customer.getCustomerID());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
	}
 
}
