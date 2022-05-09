package ProjectPackageGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import normalClasses.Admin;
import normalClasses.Customer;
import normalClasses.CustomerRegistration;
import normalClasses.Manager;

public class GUIRegister extends JPanel implements ActionListener{
	
	private JLabel emailLabel, passwordLabel, resultLabel, headingLabel, introLabel, nameLabel;
	private JTextField txtUser, nameUser;
	private JPasswordField fldPassword;
	private JButton btnSubmit; //confirm info is correct and what database it belongs to + what page to open
	private JButton btnGoBack;
	 
	public GUIRegister() {
		this.setBackground(Color.PINK);

		headingLabel = new JLabel("Register Here ");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 25));
		introLabel = new JLabel("Enter your details below.");
		nameLabel = new JLabel("Full Name:");
		emailLabel = new JLabel("Email:");
		passwordLabel = new JLabel("Password:");
		resultLabel = new JLabel();
		txtUser = new JTextField();
		nameUser = new JTextField();
		fldPassword = new JPasswordField();

		btnSubmit = new JButton("Submit");
		btnGoBack = new JButton("Go Back");

		addComponents();
		addActionEvent();
		setLocationAndSize();
		
		this.setLayout(null);

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
		}

		public void addActionEvent() {
		btnSubmit.addActionListener(this);
		btnGoBack.addActionListener(this);
		}

		public void setLocationAndSize() {
		headingLabel.setBounds(130,45, 180, 30);
		introLabel.setBounds(130, 90, 180, 30);
		nameLabel.setBounds(55, 140, 100, 30);
		nameUser.setBounds(150, 140, 150, 30);

		emailLabel.setBounds(80, 180, 100, 30);
		passwordLabel.setBounds(50, 220, 100, 30);
		txtUser.setBounds(150, 180, 150, 30);
		fldPassword.setBounds(150, 220, 150, 30);
		btnSubmit.setBounds(50, 300, 100, 30);
		btnGoBack.setBounds(250, 300, 100, 30);
		resultLabel.setBounds(150, 330, 150, 30);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnGoBack) {
				  mainFrame.frame.add(new GUILogin());
				  this.setVisible(false);
			  }
			//also check if the email already exists!
			if (!txtUser.getText().equals("") && !nameUser.getText().equals("") && !fldPassword.getPassword().equals("")) {
					if (e.getSource() == btnSubmit) {
						
						try {
							 if(!(Customer.customerHash().size() == 0) && Customer.customerHash().containsKey(txtUser.getText()) 
									 || Admin.adminHash().containsKey(txtUser.getText()) || Manager.managerHash().containsKey(txtUser.getText()) ) {
										this.resultLabel.setText("Email already exists!");
										txtUser.setText("");
								 }
			 	
							else {								
								this.resultLabel.setText("Registration successful!");
								String passText = new String(fldPassword.getPassword());
								String uniqueID = UUID.randomUUID().toString();
								String[] parts = uniqueID.split("-");
								String iD = parts[0];
								new CustomerRegistration("CUSTOMER"+"-"+iD, nameUser.getText(), txtUser.getText() , passText).writeToTextFile();
								txtUser.setText("");
								nameUser.setText("");
								fldPassword.setText("");
							}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			}  
			else {
				if (e.getSource() == btnSubmit) {
				this.resultLabel.setText("Registration Incomplete!");
				}

			}
		}
	}
   
 
