package ProjectPackageGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import normalClasses.Customer;

public class GUICustomerIntroMenu extends JPanel implements ActionListener{

	private JLabel headingLabel, savedStoresLabel, introLabel, yourListLabel, uiLabel, storeSearchLabel;
	private JLabel nameLabel;
	private JButton btnSearch;
	private JButton btnViewFavorites;
	private JButton btnViewInfo;
	private JButton btnShoppingList;
	private JButton btnLogout;
	
	public GUICustomerIntroMenu() throws IOException { 
		this.setBackground(Color.PINK);

		headingLabel = new JLabel("Welcome to SmartShoppers");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 25));
		nameLabel = new JLabel("<html><u>"+ Customer.getCustomerName() + "</u></html>");
		nameLabel.setFont(new Font("Serif", Font.BOLD, 20));
		introLabel = new JLabel("Customer Menu Options");
		storeSearchLabel = new JLabel("Search for Stores");
		storeSearchLabel.setFont(new Font("Serif", Font.BOLD, 18));
		savedStoresLabel = new JLabel("Saved Stores");
		savedStoresLabel.setFont(new Font("Serif", Font.BOLD, 18));
		yourListLabel = new JLabel("Your List");
		yourListLabel.setFont(new Font("Serif", Font.BOLD, 18));
		uiLabel = new JLabel("User Information"); 
		uiLabel.setFont(new Font("Serif", Font.BOLD, 18));

		btnSearch = new JButton("Start Search");
		btnViewFavorites = new JButton("View Saved");
		btnShoppingList = new JButton("Shopping Lists");
		btnViewInfo = new JButton("View or Update");
		btnLogout = new JButton("Logout");

		addComponents();
		addActionEvent();
		setLocationAndSize(); 
		
		this.setLayout(null);

		}
	
	public void addComponents() {	
		this.add(headingLabel);
		this.add(nameLabel);
		this.add(introLabel);
		this.add(storeSearchLabel);
		this.add(savedStoresLabel);
		this.add(yourListLabel);
		this.add(uiLabel);
		this.add(btnSearch);
		this.add(btnViewFavorites);
		this.add(btnShoppingList);
		this.add(btnViewInfo);
		this.add(btnLogout);
		}
	
	public void addActionEvent() {
		btnSearch.addActionListener(this);
		btnViewFavorites.addActionListener(this);
		btnShoppingList.addActionListener(this);
		btnViewInfo.addActionListener(this);
		btnLogout.addActionListener(this);
		}
	
	public void setLocationAndSize() {
		headingLabel.setBounds(100, 40, 400, 30);
		nameLabel.setBounds(175, 65, 350, 30);
		introLabel.setBounds(175, 10, 350, 30);
		storeSearchLabel.setBounds(175, 110, 200, 30);
		btnSearch.setBounds(150, 140, 190, 30);
		savedStoresLabel.setBounds(195, 190, 200, 30);
		btnViewFavorites.setBounds(150, 220, 200, 30);
		yourListLabel.setBounds(210, 260, 200, 30);
		btnShoppingList.setBounds(150, 290, 200, 30);
		uiLabel.setBounds(185, 340, 200,30);
		btnViewInfo.setBounds(150, 370, 200, 30);
		btnLogout.setBounds(360, 420, 100, 30);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnLogout) {
			mainFrame.frame.add(new GUILogin());
			this.setVisible(false);
			Customer.customerLoggedIn = "";
		}
		
		else if (e.getSource() == btnSearch) {
			mainFrame.frame.add(new GUIStoreSearch());
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnViewFavorites) {
			try {
				mainFrame.frame.add(new GUICustomerSavedStores());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnViewInfo) {
			try {
				mainFrame.frame.add(new GUIUserPreferences());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnShoppingList) {
			try {
				mainFrame.frame.add(new GUICustomerStoreLists());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);

		}
		
		
	}
	
	
	
	
}
