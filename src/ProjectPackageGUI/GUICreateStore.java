package ProjectPackageGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import normalClasses.Admin;
import normalClasses.Manager;
import normalClasses.Products;
import normalClasses.Store;

import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class GUICreateStore extends JPanel implements ActionListener{
//User can update their name, email, password, and opt in for 2FA via a checkbox
	
	private JLabel emailLabel, passwordLabel, resultLabel, headingLabel, introLabel, nameLabel;
	private JTextField txtAddress, nameStore, fldHours;
	//private JPasswordField fldPassword;
	private JButton btnSubmit = new JButton(); //confirm info is correct and what database it belongs to + what page to open
	private JButton btnGoBack = new JButton();
	private JComboBox comboBox = new JComboBox();
	private JLabel lblNewLabel_2;
	JCheckBox boxChngeManager = new JCheckBox();
	private JButton btnCreateManager = new JButton();

	 
	public GUICreateStore() throws IOException {
		setBackground(new Color(135, 206, 250));
		setForeground(Color.PINK);

		headingLabel = new JLabel("Add a SmartShoppers Store");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 23));
		introLabel = new JLabel("Add Store Information");
		introLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel = new JLabel("Store Name:");
		emailLabel = new JLabel("Store Address:");
		passwordLabel = new JLabel("Store Hours:");
		resultLabel = new JLabel();
		resultLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtAddress = new JTextField();
		nameStore = new JTextField();
		fldHours = new JTextField();

		btnSubmit = new JButton("Add Store");
		btnGoBack = new JButton("Go Back");

		
		this.setLayout(null);
		
		JLabel adminLabel = new JLabel("Admin Privileges ");
		adminLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		adminLabel.setBounds(166, 52, 145, 14);
		add(adminLabel);
		
		comboBox = new JComboBox(Admin.availableManagers().toArray());
		comboBox.setBounds(150, 268, 123, 22);
		add(comboBox);
		
		lblNewLabel_2 = new JLabel("Assign Manager:");
		lblNewLabel_2.setBounds(48, 272, 100, 14);
		add(lblNewLabel_2);
		
		boxChngeManager = new JCheckBox("Add Manager");
		boxChngeManager.setBounds(291, 268, 137, 23);
		add(boxChngeManager);
		
		btnCreateManager = new JButton("Create Manager");
		btnCreateManager.setBounds(0, 433, 139, 23);
		add(btnCreateManager);

		addComponents();
		addActionEvent();
		setLocationAndSize();
		}


		public void addComponents() {	
		this.add(headingLabel);
		this.add(introLabel);
		this.add(nameLabel);
		this.add(nameStore);
		this.add(emailLabel);
		this.add(txtAddress);
		this.add(passwordLabel);
		this.add(fldHours);
		this.add(btnSubmit);
		this.add(btnGoBack);
		this.add(resultLabel);
		}

		public void addActionEvent() {
		btnSubmit.addActionListener(this);
		btnGoBack.addActionListener(this);
		btnCreateManager.addActionListener(this);
		}

		public void setLocationAndSize() {
		headingLabel.setBounds(10,11,430, 30);
		introLabel.setBounds(150, 77, 200, 30);
		nameLabel.setBounds(55, 140, 100, 30);
		nameStore.setBounds(150, 140, 200, 30);

		emailLabel.setBounds(55, 181, 100, 30);
		passwordLabel.setBounds(55, 220, 100, 30);
		txtAddress.setBounds(150, 180, 200, 30);
		fldHours.setBounds(150, 220, 200, 30);
		btnSubmit.setBounds(159, 367, 152, 30);
		btnGoBack.setBounds(350, 429, 100, 30);
		resultLabel.setBounds(142, 324, 223, 30);

		}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnGoBack) {
			mainFrame.frame.getContentPane().add(new GUIAdminMenu());
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnCreateManager) {
			try {
				mainFrame.frame.getContentPane().add(new GUIAddManager());
				this.setVisible(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if (e.getSource() == btnSubmit) {
				try {
					if (!txtAddress.getText().equals("") && !nameStore.getText().equals("") && !fldHours.getText().equals("") && boxChngeManager.isSelected() && (Admin.availableManagers().size()>0) ){
						//get the checked combobox option for that manager and edit their txt file so that they have the store ID and the current manager has the store ID as "N/A"
						String picked = String.valueOf(comboBox.getSelectedItem());
						String uniqueID = UUID.randomUUID().toString();
						String[] parts = uniqueID.split("-");
						String StoreiD = "STORE"+"-"+parts[0];
			
						String newStore = StoreiD +"," +nameStore.getText() + "," + txtAddress.getText() + "," + "Open " + fldHours.getText() + " daily";
						
						String manager = Manager.getManagerID(picked) + "," + Manager.getManagerName(picked) + "," + Manager.getManagerEmail(picked) 
						+ "," + Manager.getManagerPass(picked) + "," + StoreiD;
						
						Admin.updateManagerInfo(manager, Manager.getManagerID(picked));
						
						Store.createStore(newStore, StoreiD);
						
						Store.justCreated = true;
						Products.currentStoreID = StoreiD;
						mainFrame.frame.getContentPane().add(new GUIAddProduct()); //this is done to refresh the page
						this.setVisible(false);
					}
					
					else {
						resultLabel.setText("Create more Managers!");

					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}
	}
}


