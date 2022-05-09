package ProjectPackageGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import normalClasses.Admin;
import normalClasses.Manager;
import normalClasses.Store;

import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.SystemColor;

public class GUIManagerUpdateInfo extends JPanel implements ActionListener{
//User can update their name, email, password, and opt in for 2FA via a checkbox
	
	private JLabel emailLabel, passwordLabel, headingLabel, introLabel, nameLabel;
	private JLabel resultLabel = new JLabel();
	private JTextField txtUser, nameUser, fldPassword;
	private JButton btnSubmit = new JButton(); //confirm info is correct and what database it belongs to + what page to open
	private JButton btnGoBack = new JButton();
;	private JButton btnAccDeletion = new JButton();
	private JLabel lblNewLabel;
	private JCheckBox boxDelete = new JCheckBox(); 
	private JLabel adminlabel;
	private JTextField txtStore;
	private JCheckBox bxRemoveStore;
	private JLabel storeLabel;
	private JButton btnGiveStore = new JButton();
	
	public GUIManagerUpdateInfo() throws IOException {
		setBackground(new Color(135, 206, 250));

		headingLabel = new JLabel("Manager Information Update");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 23));
		introLabel = new JLabel("Update Manager Information Below.");
		nameLabel = new JLabel("Full Name:");
		emailLabel = new JLabel("Email:");
		passwordLabel = new JLabel("Password:");
		txtUser = new JTextField();
		nameUser = new JTextField();
		fldPassword = new JTextField();

		btnSubmit = new JButton("Update Info");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit.setBackground(new Color(173, 255, 47));
		btnGoBack = new JButton("Go Back");
		btnAccDeletion = new JButton("Delete Manager");
		btnAccDeletion.setBackground(Color.RED);
		

		
		
		this.setLayout(null);
		
		lblNewLabel = new JLabel("Click and Check Box to Delete Manager");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(10, 321, 208, 14);
		add(lblNewLabel);

		adminlabel = new JLabel("Admin Privileges");
		adminlabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		adminlabel.setBounds(168, 52, 132, 14);
		add(adminlabel);
		
		txtStore = new JTextField();
		txtStore.setEditable(false);
		txtStore.setBounds(150, 221, 150, 23);
		add(txtStore);
		txtStore.setColumns(10);
		
		bxRemoveStore = new JCheckBox("Remove Store");
		bxRemoveStore.setBounds(309, 221, 108, 23);
		add(bxRemoveStore);
		
		storeLabel = new JLabel("Store:");
		storeLabel.setBounds(55, 214, 100, 30);
		add(storeLabel);
		
		boxDelete = new JCheckBox("");
		boxDelete.setForeground(Color.RED);
		boxDelete.setBounds(150, 336, 21, 26);
		add(boxDelete);
		
		btnGiveStore = new JButton("Assign Store");
		btnGiveStore.setBounds(150, 400, 150, 30);
		add(btnGiveStore);
		
	
		currentInfo();
		addComponents();
		addActionEvent();
		setLocationAndSize();

		}


		public void addComponents() {	
		this.add(headingLabel);
		this.add(introLabel);
		this.add(nameLabel);
		this.add(nameUser);
		this.add(emailLabel);
		this.add(txtUser);
		this.add(passwordLabel);
		this.add(fldPassword);
		this.add(btnSubmit);
		this.add(btnGoBack);
		resultLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.add(resultLabel);
		this.add(btnAccDeletion);
		}

		public void addActionEvent() {
		btnSubmit.addActionListener(this);
		btnGoBack.addActionListener(this);
		btnAccDeletion.addActionListener(this);
		btnGiveStore.addActionListener(this);
		}

		public void setLocationAndSize() {
		headingLabel.setBounds(67,11,339, 30);
		introLabel.setBounds(128, 69, 300, 30);
		nameLabel.setBounds(55, 98, 100, 30);
		nameUser.setBounds(150, 98, 150, 30);

		emailLabel.setBounds(55, 139, 100, 30);
		passwordLabel.setBounds(55, 180, 100, 30);
		txtUser.setBounds(150, 139, 150, 30);
		fldPassword.setBounds(150, 180, 150, 30);
		btnSubmit.setBounds(161, 267, 115, 42);
		btnGoBack.setBounds(306, 332, 100, 30);
		resultLabel.setBounds(105, 369, 251, 30);
		btnAccDeletion.setBounds(10, 336, 132, 26);

		}

	
		public void currentInfo() throws IOException {
			nameUser.setText(Manager.getManagerName(Admin.viewedManager));
			txtUser.setText(Manager.getManagerEmail(Admin.viewedManager));
			fldPassword.setText(Manager.getManagerPass(Admin.viewedManager));
			String setStoreString = "No Assigned Store";
			if (!Manager.getManagerStoreID(Admin.viewedManager).equals("N/A")) {
				setStoreString = Store.getStoreName(Manager.getManagerStoreID(Admin.viewedManager), "data/Stores.txt");
			}
			txtStore.setText(setStoreString);
			
		}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnGoBack) {
			try {
				mainFrame.frame.getContentPane().add(new GUIManageManagers());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		
		else if (e.getSource() == btnGiveStore) {
			try {
				mainFrame.frame.getContentPane().add(new GUIManageStores());
				this.setVisible(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if (e.getSource() == btnAccDeletion && boxDelete.isSelected()) {
				
			try {
				Manager.deleteManager(Admin.viewedManager);
				mainFrame.frame.getContentPane().add(new GUIManageManagers());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
			
			
		}
		
		else if (e.getSource() == btnSubmit) {
			
			if (!txtUser.getText().equals("") && !nameUser.getText().equals("") && !fldPassword.getText().equals("")) {

				try {
				if (bxRemoveStore.isSelected()) {
					String managerString = Manager.getManagerID(Admin.viewedManager) + "," + nameUser.getText() + "," + txtUser.getText()
					+ "," + fldPassword.getText() + "," + "N/A";

					
					Admin.updateManagerInfo(managerString, Admin.viewedManager);
					mainFrame.frame.getContentPane().add(new GUIManagerUpdateInfo());
					//resultLabel.setText("Manager Information Updated!");
					this.setVisible(false);
				}
				
				else {

					String managerString = Manager.getManagerID(Admin.viewedManager) + "," + nameUser.getText() + "," + txtUser.getText()
					+ "," + fldPassword.getText() + "," + Manager.getManagerStoreID(Admin.viewedManager);

					Admin.updateManagerInfo(managerString, Admin.viewedManager);
					Manager.managerLoggedIn = txtUser.getText();
					//mainFrame.frame.getContentPane().add(new GUIManagerUpdateInfo());
					resultLabel.setText("Manager Information Updated!");
					//this.setVisible(false);

				}
				
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}
	
}


