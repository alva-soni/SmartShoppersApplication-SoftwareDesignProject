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

import normalClasses.Manager;

import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.SystemColor;

public class GUIAddManager extends JPanel implements ActionListener{
//User can update their name, email, password, and opt in for 2FA via a checkbox
	
	private JLabel emailLabel, passwordLabel, headingLabel, introLabel, nameLabel;
	private JLabel resultLabel = new JLabel();
	private JTextField txtUser, nameUser, fldPassword;
	private JButton btnSubmit = new JButton(); //confirm info is correct and what database it belongs to + what page to open
	private JButton btnGoBack = new JButton();
;	private JButton btnAccDeletion = new JButton();
	private JButton btnGiveStore;
	private JCheckBox boxDelete = new JCheckBox(); 
	private JLabel adminlabel;
	 
	public GUIAddManager() throws IOException {
		setBackground(new Color(135, 206, 250));
		setForeground(new Color(173, 216, 230));

		headingLabel = new JLabel("Manager Creation");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 23));
		introLabel = new JLabel("Register a Manager Below.");
		nameLabel = new JLabel("Full Name:");
		emailLabel = new JLabel("Email:");
		passwordLabel = new JLabel("Password:");
		txtUser = new JTextField();
		nameUser = new JTextField();
		fldPassword = new JTextField();

		btnSubmit = new JButton("Add Manager");
		btnGoBack = new JButton("Go Back");
		btnGiveStore = new JButton("Assign Store");
		

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
		this.add(btnGoBack);
		resultLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.add(resultLabel);
		this.add(btnGiveStore);
		}

		public void addActionEvent() {
		btnSubmit.addActionListener(this);
		btnGoBack.addActionListener(this);
		btnGiveStore.addActionListener(this);
		}

		public void setLocationAndSize() {
		headingLabel.setBounds(111,11,339, 30);
		introLabel.setBounds(150, 70, 300, 30);
		nameLabel.setBounds(55, 98, 100, 30);
		nameUser.setBounds(150, 98, 150, 30);

		emailLabel.setBounds(55, 139, 100, 30);
		passwordLabel.setBounds(55, 180, 100, 30);
		txtUser.setBounds(150, 139, 150, 30);
		fldPassword.setBounds(150, 180, 150, 30);
		btnSubmit.setBounds(166, 242, 115, 42);
		btnGoBack.setBounds(306, 332, 100, 30);
		resultLabel.setBounds(166, 373, 227, 30);
		btnGiveStore.setBounds(10, 336, 132, 26);

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
		
		
		else if (e.getSource() == btnSubmit) {
			
			if (!txtUser.getText().equals("") && !nameUser.getText().equals("") && !fldPassword.getText().equals("")) {

				String uniqueID = UUID.randomUUID().toString();
				String[] parts = uniqueID.split("-");
				String iD = parts[0];
				String managerIdString = "MANAGER-"+iD;
				String managerString = managerIdString + "," + nameUser.getText() + "," + txtUser.getText()
				+ "," + fldPassword.getText() + "," + "N/A";

				Manager.addManager(managerString);
				//mainFrame.frame.getContentPane().add(new GUIAddManager());
				resultLabel.setText("Manager Added!");
				//this.setVisible(false);
			}
		}
		 
		else if (e.getSource() == btnGiveStore) {
			try {
				mainFrame.frame.getContentPane().add(new GUIManageStores());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);

		}
	
	}
}


