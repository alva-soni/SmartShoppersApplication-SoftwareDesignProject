package ProjectPackageGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import normalClasses.Customer;

import javax.swing.JCheckBox;
import java.awt.Color;

public class GUIUserPreferences extends JPanel implements ActionListener{
//User can update their name, email, password, and opt in for 2FA via a checkbox
	
	private JLabel emailLabel, passwordLabel, resultLabel, headingLabel, introLabel, nameLabel;
	private JTextField txtUser, nameUser, fldPassword;
	//private JPasswordField fldPassword;
	private JButton btnSubmit; //confirm info is correct and what database it belongs to + what page to open
	private JButton btnGoBack, btnAccDeletion;
	JLabel lbl2fa = new JLabel();
	JCheckBox twoFABox = new JCheckBox();
	 
	public GUIUserPreferences() throws IOException {
		setBackground(Color.PINK);
		setForeground(Color.PINK);

		headingLabel = new JLabel("User Preferences ");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 25));
		introLabel = new JLabel("Change your current user information below.");
		nameLabel = new JLabel("Full Name:");
		emailLabel = new JLabel("Email:");
		passwordLabel = new JLabel("Password:");
		resultLabel = new JLabel();
		txtUser = new JTextField();
		nameUser = new JTextField();
		fldPassword = new JTextField();

		btnSubmit = new JButton("Update Info");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit.setBackground(Color.GREEN);
		btnGoBack = new JButton("Go Back");
		btnAccDeletion = new JButton("Click to Delete Account");
		btnAccDeletion.setBackground(Color.RED);
		
 
		addComponents();
		addActionEvent();
		setLocationAndSize();
		
		this.setLayout(null);
		
		twoFABox = new JCheckBox("Enable 2FA");
		twoFABox.setBackground(Color.YELLOW);
		twoFABox.setBounds(80, 257, 97, 23);
		add(twoFABox);
		
		JLabel lblNewLabel = new JLabel("(You will also be logged out)");
		lblNewLabel.setBounds(245, 288, 166, 14);
		add(lblNewLabel);
				
	
		currentInfo();

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
		this.add(resultLabel);
		this.add(btnAccDeletion);
		this.add(lbl2fa);
		}

		public void addActionEvent() {
		btnSubmit.addActionListener(this);
		btnGoBack.addActionListener(this);
		btnAccDeletion.addActionListener(this);
		}

		public void setLocationAndSize() {
		headingLabel.setBounds(130,45,300, 30);
		introLabel.setBounds(106, 86, 300, 30);
		nameLabel.setBounds(55, 140, 100, 30);
		nameUser.setBounds(150, 140, 150, 30);

		emailLabel.setBounds(80, 180, 100, 30);
		passwordLabel.setBounds(50, 220, 100, 30);
		txtUser.setBounds(150, 180, 150, 30);
		fldPassword.setBounds(150, 220, 150, 30);
		btnSubmit.setBounds(55, 332, 122, 30);
		btnGoBack.setBounds(258, 332, 100, 30);
		resultLabel.setBounds(10, 373, 440, 30);
		btnAccDeletion.setBounds(225, 261, 180, 23);
		lbl2fa.setBounds(10, 307, 326, 14);

		}

	
		public void currentInfo() throws IOException {
			nameUser.setText(Customer.getCustomerName());
			txtUser.setText(Customer.getCustomerEmail());
			fldPassword.setText(Customer.getCustomerPass());
		}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnGoBack) {
			try {
				mainFrame.frame.getContentPane().add(new GUICustomerIntroMenu());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnAccDeletion) {
			
			
			
			Customer customer = new Customer();

			//This deletes the directory which contains this customer's shopping lists
			try {
				File index = new File("data/CustomerShoppingLists/"+ Customer.getCustomerID() + "/");
				
				if (index.isDirectory()) {
					String[]entries = index.list();
					for(String s: entries){
					    File currentFile = new File(index.getPath(),s);
					    currentFile.delete();
					}
					index.delete();
				}
			
				File favoriteStores = new File("data/favoriteStores/"+ Customer.getCustomerID() + ".txt");
				if (favoriteStores.exists()) {
					favoriteStores.delete();
				}
				customer.deleteAccount();
				mainFrame.frame.getContentPane().add(new GUILogin());
				this.setVisible(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
 
			 
			
		}
		
		else if (e.getSource() == btnSubmit) {
			if (!txtUser.getText().equals("") && !nameUser.getText().equals("") && !fldPassword.getText().equals("")) {
				try {
					String text = Customer.getCustomerID() + "," + nameUser.getText() + "," + txtUser.getText() + "," + fldPassword.getText();
					Customer.updateUserInfo(text);
					if (twoFABox.isSelected()) {
						lbl2fa.setText("(An email with furthur instructions will be sent)");
					}
					Customer.customerLoggedIn = txtUser.getText();
					resultLabel.setText("User Information Updated! Please wait a moment before going back to Menu.");
					//mainFrame.frame.getContentPane().add(new GUIUserPreferences());
					//this.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
		}
	}
}


