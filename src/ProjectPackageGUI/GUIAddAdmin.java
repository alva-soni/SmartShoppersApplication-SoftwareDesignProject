package ProjectPackageGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

import normalClasses.Admin;

public class GUIAddAdmin extends JPanel implements ActionListener{
//User can update their name, email, password, and opt in for 2FA via a checkbox
	
	private JLabel emailLabel, passwordLabel, headingLabel, introLabel, nameLabel;
	private JLabel resultLabel = new JLabel();
	private JTextField txtUser, nameUser, fldPassword;
	private JButton btnSubmit = new JButton(); //confirm info is correct and what database it belongs to + what page to open
	private JButton btnGoBack = new JButton();
	private JButton btnMenu;
;	private JButton btnAccDeletion = new JButton();
	private JCheckBox boxDelete = new JCheckBox(); 
	private JLabel adminlabel;
	 
	public GUIAddAdmin() throws IOException {
		setBackground(new Color(135, 206, 250));
		setForeground(new Color(173, 216, 230));

		headingLabel = new JLabel("Admin Creation");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 23));
		introLabel = new JLabel("Register a new Admin.");
		nameLabel = new JLabel("Full Name:");
		emailLabel = new JLabel("Email:");
		passwordLabel = new JLabel("Password:");
		txtUser = new JTextField();
		nameUser = new JTextField();
		fldPassword = new JTextField();

		btnSubmit = new JButton("Add Admin");
		btnMenu = new JButton("Menu");
		

		addComponents();
		addActionEvent();
		setLocationAndSize();
		
		this.setLayout(null);

		adminlabel = new JLabel("Admin Privileges");
		adminlabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		adminlabel.setBounds(168, 52, 132, 14);
		add(adminlabel);
		
	
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
		this.add(btnMenu);
		resultLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.add(resultLabel);
		}

		public void addActionEvent() {
		btnSubmit.addActionListener(this);
		btnMenu.addActionListener(this);
		}

		public void setLocationAndSize() {
		headingLabel.setBounds(141,11,216, 30);
		introLabel.setBounds(161, 71, 243, 30);
		nameLabel.setBounds(55, 98, 100, 30);
		nameUser.setBounds(150, 98, 150, 30);

		emailLabel.setBounds(55, 139, 100, 30);
		passwordLabel.setBounds(55, 180, 100, 30);
		txtUser.setBounds(150, 139, 150, 30);
		fldPassword.setBounds(150, 180, 150, 30);
		btnSubmit.setBounds(166, 242, 115, 42);
		btnMenu.setBounds(170, 325, 100, 30);
		resultLabel.setBounds(166, 373, 227, 30);

		}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnMenu) {
			mainFrame.frame.getContentPane().add(new GUIAdminMenu());
			this.setVisible(false);
		}
		
		
		else if (e.getSource() == btnSubmit) {
			
			if (!txtUser.getText().equals("") && !nameUser.getText().equals("") && !fldPassword.getText().equals("")) {

				String uniqueID = UUID.randomUUID().toString();
				String[] parts = uniqueID.split("-");
				String iD = parts[0];
				String adminIdString = "ADMIN-"+iD;
				String adminString = adminIdString + "," + nameUser.getText() + "," + txtUser.getText()
				+ "," + fldPassword.getText();

				Admin.addAdmin(adminString);
				//mainFrame.frame.getContentPane().add(new GUIAddManager());
				resultLabel.setText("Admin Added!");
				//this.setVisible(false);
			}
		}
		
	
	}
}


