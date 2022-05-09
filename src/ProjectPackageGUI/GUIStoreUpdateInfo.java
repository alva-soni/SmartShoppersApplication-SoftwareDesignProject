package ProjectPackageGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import normalClasses.Admin;
import normalClasses.DamageControl;
import normalClasses.Manager;
import normalClasses.Store;

import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class GUIStoreUpdateInfo extends JPanel implements ActionListener{
//User can update their name, email, password, and opt in for 2FA via a checkbox
	
	private JLabel emailLabel, passwordLabel, resultLabel, headingLabel, introLabel, nameLabel;
	private JTextField txtAddress, nameStore, fldHours;
	//private JPasswordField fldPassword;
	private JButton btnSubmit; //confirm info is correct and what database it belongs to + what page to open
	private JButton btnGoBack, btnAccDeletion;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox = new JComboBox();
	private JTextField currentManager;
	private JLabel lblNewLabel_2;
	JCheckBox boxChngeManager = new JCheckBox();
	private JCheckBox bxDelete = new JCheckBox();

	 
	public GUIStoreUpdateInfo() throws IOException {
		setBackground(new Color(135, 206, 250));
		setForeground(Color.PINK);

		headingLabel = new JLabel(Store.getStoreName(Admin.currentStore, "data/Stores.txt")+ " Information Update");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 23));
		introLabel = new JLabel("Update Store Information");
		introLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel = new JLabel("Store Name:");
		emailLabel = new JLabel("Store Address:");
		passwordLabel = new JLabel("Store Hours:");
		resultLabel = new JLabel();
		resultLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAddress = new JTextField();
		nameStore = new JTextField();
		fldHours = new JTextField();

		btnSubmit = new JButton("Update Info");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit.setBackground(new Color(173, 255, 47));
		btnGoBack = new JButton("Go Back");
		btnAccDeletion = new JButton(" Delete Store");
		btnAccDeletion.setBackground(Color.RED);
		btnAccDeletion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		

		addComponents();
		addActionEvent();
		setLocationAndSize();
		
		this.setLayout(null);
		
		lblNewLabel = new JLabel("Check Box and Click to Delete Store");
		lblNewLabel.setBounds(10, 403, 207, 14);
		add(lblNewLabel);
		
		JLabel adminLabel = new JLabel("Admin Privileges ");
		adminLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		adminLabel.setBounds(136, 52, 145, 14);
		add(adminLabel);
		
		lblNewLabel_1 = new JLabel("Store Manager:");
		lblNewLabel_1.setBounds(55, 265, 90, 14);
		add(lblNewLabel_1);
		
		comboBox = new JComboBox(Admin.availableManagers().toArray());
		comboBox.setBounds(183, 292, 137, 22);
		add(comboBox);
		
		currentManager = new JTextField();
		currentManager.setEditable(false);
		currentManager.setBounds(150, 261, 200, 20);
		add(currentManager);
		currentManager.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Unassigned Managers:");
		lblNewLabel_2.setBounds(55, 296, 137, 14);
		add(lblNewLabel_2);
		
		boxChngeManager = new JCheckBox("Change Manager");
		boxChngeManager.setBounds(326, 292, 137, 23);
		add(boxChngeManager);
		
		bxDelete = new JCheckBox("");
		bxDelete.setBackground(Color.RED);
		bxDelete.setBounds(134, 423, 21, 23);
		add(bxDelete);
		
	
		currentInfo();
		


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
		this.add(btnAccDeletion);
		}

		public void addActionEvent() {
		btnSubmit.addActionListener(this);
		btnGoBack.addActionListener(this);
		btnAccDeletion.addActionListener(this);
		}

		public void setLocationAndSize() {
		headingLabel.setBounds(10,11,430, 30);
		introLabel.setBounds(112, 84, 200, 30);
		nameLabel.setBounds(55, 140, 100, 30);
		nameStore.setBounds(150, 140, 200, 30);

		emailLabel.setBounds(55, 181, 100, 30);
		passwordLabel.setBounds(55, 220, 100, 30);
		txtAddress.setBounds(150, 180, 200, 30);
		fldHours.setBounds(150, 220, 200, 30);
		btnSubmit.setBounds(172, 350, 131, 42);
		btnGoBack.setBounds(336, 419, 123, 27);
		resultLabel.setBounds(16, 325, 453, 30);
		btnAccDeletion.setBounds(10, 421, 118, 23);

		}

	
		public void currentInfo() throws IOException {
			nameStore.setText(Store.getStoreName(Admin.currentStore, "data/Stores.txt"));
			txtAddress.setText(Store.getStoreAddress(Admin.currentStore, "data/Stores.txt"));
			fldHours.setText(Store.getStoreHours(Admin.currentStore, "data/Stores.txt"));
			currentManager.setText(Admin.currentManager());
		}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnGoBack) {
			try {
				mainFrame.frame.getContentPane().add(new GUIManageStores());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnSubmit) {
			if (!txtAddress.getText().equals("") && !nameStore.getText().equals("") && !fldHours.getText().equals("") && !boxChngeManager.isSelected()) {
				try {
					String text = Admin.currentStore + "," + nameStore.getText() + "," + txtAddress.getText() + "," + fldHours.getText();
					Admin.updateStoreInfo(text);
					resultLabel.setText("Store Information Updated! Please wait a moment before going back.");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				} else
				try {
					if (!txtAddress.getText().equals("") && !nameStore.getText().equals("") && !fldHours.getText().equals("") && boxChngeManager.isSelected() && (Admin.availableManagers().size()>0) ){
						//get the checked combobox option for that manager and edit their txt file so that they have the store ID and the current manager has the store ID as "N/A"
						String picked = String.valueOf(comboBox.getSelectedItem());
					
						if (Admin.currentManager().equals("No Manager Assigned")) {
							String newManager = Manager.getManagerID(picked) + "," + Manager.getManagerName(picked) + "," + Manager.getManagerEmail(picked) 
							+ "," + Manager.getManagerPass(picked) + "," + Store.getStoreID(Admin.currentStore, "data/Stores.txt");
							
							Admin.updateManagerInfo(newManager, picked);
							Admin.currentManager();
							resultLabel.setText("Store Information Updated!");
							
							mainFrame.frame.getContentPane().add(new GUIStoreUpdateInfo()); //this is done to refresh the page
							this.setVisible(false);
						}
						
						else {
							String oldManager = Manager.getManagerID(Admin.currentManager()) + "," + Manager.getManagerName(Admin.currentManager()) + "," + Manager.getManagerEmail(Admin.currentManager()) 
							+ "," + Manager.getManagerPass(Admin.currentManager()) + "," + "N/A";
							
							
							String newManager = Manager.getManagerID(picked) + "," + Manager.getManagerName(picked) + "," + Manager.getManagerEmail(picked) 
							+ "," + Manager.getManagerPass(picked) + "," + Store.getStoreID(Admin.currentStore, "data/Stores.txt");
							
							Admin.updateManagerInfo(oldManager, Admin.currentManager());
							Admin.updateManagerInfo(newManager, picked);
							Admin.currentManager();
							//System.out.println(Manager.getManagerStringInfo(picked));
						
							resultLabel.setText("Store Information Updated! Please wait a moment before going back.");
							
							mainFrame.frame.getContentPane().add(new GUIStoreUpdateInfo()); //this is done to refresh the page
							this.setVisible(false);
						}
					} 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
		}
		
		else if (e.getSource() == btnAccDeletion && bxDelete.isSelected()){
			
			try { 
				//do same as above for 'no manager selected'
				if (Admin.currentManager().equals("No Manager Assigned")) {
					
					DamageControl.removeStoreFromCustomer(Store.getStoreID(Admin.currentStore, "data/Stores.txt"));

					Store.deleteStore(Store.getStoreID(Admin.currentStore, "data/Stores.txt"));
										
					mainFrame.frame.getContentPane().add(new GUIManageStores());
					this.setVisible(false);
				}
				else {
				String oldManager = Manager.getManagerID(Admin.currentManager()) + "," + Manager.getManagerName(Admin.currentManager()) + "," + Manager.getManagerEmail(Admin.currentManager()) 
				+ "," + Manager.getManagerPass(Admin.currentManager()) + "," + "N/A";
				Admin.updateManagerInfo(oldManager, Admin.currentManager());
				
				DamageControl.removeStoreFromCustomer(Store.getStoreID(Admin.currentStore, "data/Stores.txt")); //trial
				
				Store.deleteStore(Store.getStoreID(Admin.currentStore, "data/Stores.txt"));
				
				mainFrame.frame.getContentPane().add(new GUIManageStores());
				this.setVisible(false);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 

		}
	}
}


