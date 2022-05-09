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

public class GUIManagerProfile extends JPanel implements ActionListener{
//User can update their name, email, password, and opt in for 2FA via a checkbox
	
	private JLabel emailLabel, passwordLabel, headingLabel, introLabel, nameLabel;
	private JLabel resultLabel = new JLabel();
	private JTextField txtUser, nameUser, fldPassword;
	private JButton btnSubmit = new JButton(); //confirm info is correct and what database it belongs to + what page to open
	private JButton btnGoBack = new JButton();
;	private JButton btnAccDeletion = new JButton();
	private JCheckBox boxDelete = new JCheckBox(); 
	private JLabel managerlabel;
	private JTextField txtStore;
	private JLabel storeLabel;
	private JButton btnGiveStore = new JButton();
	
	public GUIManagerProfile() throws IOException {
		setBackground(new Color(210, 105, 30));
		setForeground(SystemColor.activeCaption);

		headingLabel = new JLabel("Manager Profile");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 23));
		introLabel = new JLabel("Update Your Information Below.");
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
		

		
		
		this.setLayout(null);

		managerlabel = new JLabel(Manager.getManagerID(Manager.managerLoggedIn));
		managerlabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		managerlabel.setBounds(168, 52, 132, 14);
		add(managerlabel);
		
		txtStore = new JTextField();
		txtStore.setEditable(false);
		txtStore.setBounds(150, 221, 150, 23);
		add(txtStore);
		txtStore.setColumns(10);
		
		storeLabel = new JLabel("Store:");
		storeLabel.setBounds(55, 214, 100, 30);
		add(storeLabel);
		
	
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
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Enable 2FA");
		chckbxNewCheckBox.setBounds(33, 277, 97, 23);
		add(chckbxNewCheckBox);
		
		JLabel lblNewLabel = new JLabel("(Email will be sent if enabled)");
		lblNewLabel.setBounds(10, 307, 183, 14);
		add(lblNewLabel);
		}

		public void addActionEvent() {
		btnSubmit.addActionListener(this);
		btnGoBack.addActionListener(this);
		}

		public void setLocationAndSize() {
		headingLabel.setBounds(136,11,200, 30);
		introLabel.setBounds(150, 64, 300, 30);
		nameLabel.setBounds(55, 98, 100, 30);
		nameUser.setBounds(150, 98, 150, 30);

		emailLabel.setBounds(55, 139, 100, 30);
		passwordLabel.setBounds(55, 180, 100, 30);
		txtUser.setBounds(150, 139, 150, 30);
		fldPassword.setBounds(150, 180, 150, 30);
		btnSubmit.setBounds(161, 267, 115, 42);
		btnGoBack.setBounds(168, 336, 100, 30);
		resultLabel.setBounds(105, 369, 251, 30);

		}

	
		public void currentInfo() throws IOException {
			nameUser.setText(Manager.getManagerName(Manager.managerLoggedIn));
			txtUser.setText(Manager.getManagerEmail(Manager.managerLoggedIn));
			fldPassword.setText(Manager.getManagerPass(Manager.managerLoggedIn));
			String setStoreString = "No Assigned Store";
			if (!Manager.getManagerStoreID(Manager.managerLoggedIn).equals("N/A")) {
				setStoreString = Store.getStoreName(Manager.getManagerStoreID(Manager.managerLoggedIn), "data/Stores.txt");
			}
			txtStore.setText(setStoreString);
			
		}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnGoBack) {
			mainFrame.frame.getContentPane().add(new GUIManagerMenu());
			this.setVisible(false);
		}

		else if (e.getSource() == btnSubmit) {
			
			if (!txtUser.getText().equals("") && !nameUser.getText().equals("") && !fldPassword.getText().equals("")) {

				try {
					String managerString = Manager.getManagerID(Manager.managerLoggedIn) + "," + nameUser.getText() + "," + txtUser.getText()
					+ "," + fldPassword.getText() + "," + Manager.getManagerStoreID(Manager.managerLoggedIn);

					Admin.updateManagerInfo(managerString, Manager.managerLoggedIn);
					//mainFrame.frame.getContentPane().add(new GUIManagerUpdateInfo());
					resultLabel.setText("Your Manager Information Updated!");
					//this.setVisible(false);

				
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}
}


