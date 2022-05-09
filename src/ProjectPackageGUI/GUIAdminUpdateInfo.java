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
import normalClasses.Customer;

import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.SystemColor;

public class GUIAdminUpdateInfo extends JPanel implements ActionListener{
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
	private JButton btnGiveStore = new JButton();
	private JButton btnAddAdmin;
	private JCheckBox chckbxNewCheckBox;
	private JLabel lblNewLabel_1;
	
	public GUIAdminUpdateInfo() throws IOException {
		setBackground(new Color(135, 206, 250));
		setForeground(new Color(135, 206, 250));

		headingLabel = new JLabel("Admin Information Update");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 23));
		introLabel = new JLabel("Update Admin Information Below.");
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
		btnAccDeletion = new JButton("Delete Admin");
		btnAccDeletion.setBackground(Color.RED);
		

		
		
		this.setLayout(null);
		
		lblNewLabel = new JLabel("Click and Check Box to Delete Current Admin");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(10, 321, 373, 14);
		add(lblNewLabel);

		adminlabel = new JLabel("Admin Privileges");
		adminlabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		adminlabel.setBounds(168, 52, 202, 14);
		add(adminlabel);
		
		boxDelete = new JCheckBox("");
		boxDelete.setForeground(Color.RED);
		boxDelete.setBounds(150, 336, 21, 26);
		add(boxDelete);
		
	
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
		
		btnAddAdmin = new JButton("Add Admin");
		btnAddAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddAdmin.setBounds(168, 388, 117, 23);
		add(btnAddAdmin);
		
		chckbxNewCheckBox = new JCheckBox("Enable 2FA");
		chckbxNewCheckBox.setBounds(21, 231, 97, 23);
		add(chckbxNewCheckBox);
		
		lblNewLabel_1 = new JLabel("(An email will be sent if enabled)");
		lblNewLabel_1.setBounds(10, 261, 290, 14);
		add(lblNewLabel_1);
		}

		public void addActionEvent() {
		btnSubmit.addActionListener(this);
		btnGoBack.addActionListener(this);
		btnAccDeletion.addActionListener(this);
		btnAddAdmin.addActionListener(this);
		}

		public void setLocationAndSize() {
		headingLabel.setBounds(67,11,373, 30);
		introLabel.setBounds(128, 69, 300, 30);
		nameLabel.setBounds(55, 98, 100, 30);
		nameUser.setBounds(150, 98, 150, 30);

		emailLabel.setBounds(55, 139, 100, 30);
		passwordLabel.setBounds(55, 180, 100, 30);
		txtUser.setBounds(150, 139, 150, 30);
		fldPassword.setBounds(150, 180, 150, 30);
		btnSubmit.setBounds(170, 221, 115, 42);
		btnGoBack.setBounds(306, 332, 100, 30);
		resultLabel.setBounds(107, 274, 251, 30);
		btnAccDeletion.setBounds(10, 336, 132, 26);

		}

	
		public void currentInfo() throws IOException {
			nameUser.setText(Admin.getAdminName(Admin.adminLoggedIn));
			txtUser.setText(Admin.getAdminEmail(Admin.adminLoggedIn));
			fldPassword.setText(Admin.getAdminPass(Admin.adminLoggedIn));
	
		}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnGoBack) {
			mainFrame.frame.getContentPane().add(new GUIAdminMenu());
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnAccDeletion && boxDelete.isSelected()) {
				
			try {
				//log out and make Admin.logged in false
				Admin.IsAdminLoggedIn = false;
				Admin.deleteAdmin(Admin.adminLoggedIn); //change to method for deleting admin
				mainFrame.frame.getContentPane().add(new GUILogin());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
			
			
		}
		
		else if (e.getSource() == btnSubmit) {
			
			if (!txtUser.getText().equals("") && !nameUser.getText().equals("") && !fldPassword.getText().equals("")) {
		try {
						
						String adminString = Admin.getAdminID(Admin.adminLoggedIn) + "," + nameUser.getText() + "," + txtUser.getText()
						+ "," + fldPassword.getText();

						Admin.updateAdminInfo(adminString, Admin.adminLoggedIn);
						Admin.adminLoggedIn = txtUser.getText();
						resultLabel.setText("Admin Information Updated!");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		else if (e.getSource() == btnAddAdmin) {
			try {
				mainFrame.frame.getContentPane().add(new GUIAddAdmin());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);

		}
		}

	}
	



