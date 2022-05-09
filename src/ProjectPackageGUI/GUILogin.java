package ProjectPackageGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import normalClasses.Admin;
import normalClasses.Customer;
import normalClasses.Manager;

public class GUILogin  extends JPanel implements ActionListener{
	
	private JLabel emailLabel, passwordLabel, resultLabel, introLabel, headingLabel;
	private JTextField txtUser; 
	private JPasswordField fldPassword;
	private JButton btnSubmit; //confirm info is correct and what database it belongs to + what page to open
	private JButton btnRegister;

  
	 
	public GUILogin() {
		this.setBackground(Color.PINK);

		headingLabel = new JLabel("SmartShoppers Login");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 25));
		introLabel = new JLabel("New? Register Below Today!");
		emailLabel = new JLabel("Email:");
		passwordLabel = new JLabel("Password:");
		resultLabel = new JLabel();
		txtUser = new JTextField();
		fldPassword = new JPasswordField();
		
		
		btnSubmit = new JButton("Submit");
		
		btnRegister = new JButton("Register");
		
		addComponentsToContainer();
		addActionEvent();
		setLocationAndSize();

		this.setLayout(null);
	}
	   
	  public void addComponentsToContainer() {	
		  	this.add(headingLabel);
		  	this.add(introLabel);
	        this.add(emailLabel);
	        this.add(txtUser);
	        this.add(passwordLabel);
	        this.add(fldPassword);
	        this.add(btnSubmit);
	        this.add(btnRegister);
	        this.add(resultLabel);
	    }
   
	  public void addActionEvent() {
	        btnSubmit.addActionListener(this);
	        btnRegister.addActionListener(this);
	    }
	  
	  public void setLocationAndSize() {
		  	headingLabel.setBounds(100,45, 250, 30);
		  	introLabel.setBounds(130, 90, 180, 30);
	        emailLabel.setBounds(80, 150, 100, 30);
	        passwordLabel.setBounds(50, 220, 100, 30);
	        txtUser.setBounds(150, 150, 150, 30);
	        fldPassword.setBounds(150, 220, 150, 30);
	        btnSubmit.setBounds(50, 300, 100, 30);
	        btnRegister.setBounds(250, 300, 100, 30);
	        resultLabel.setBounds(105, 350, 300, 30);
	  }
	  

	  
	  @Override
		public void actionPerformed(ActionEvent e) {
		  if (e.getSource() == btnRegister) {
			  mainFrame.frame.add(new GUIRegister());
			  this.setVisible(false);
		  }
		  
		  if (e.getSource() == btnSubmit) {
			  if (!txtUser.getText().equals("") && !fldPassword.getPassword().equals("")) {
				  try {
					if (Customer.customerHash().containsKey(txtUser.getText()) && Customer.customerHash().get(txtUser.getText()).equals(new String(fldPassword.getPassword())) ) {
						//this.resultLabel.setText("Succesful Login!");
						Customer.customerLoggedIn = txtUser.getText();
						//System.out.println(Customer.signedInCustomer());
						mainFrame.frame.add(new GUICustomerIntroMenu());
						this.setVisible(false);
					}
					
					else if (Admin.adminHash().containsKey(txtUser.getText()) && Admin.adminHash().get(txtUser.getText()).equals(new String(fldPassword.getPassword())) ) {
						Admin.IsAdminLoggedIn = true;
						Admin.adminLoggedIn = Admin.getAdminID(txtUser.getText());
						mainFrame.frame.add(new GUIAdminMenu());
						this.setVisible(false); 
					}
				
					else if (Manager.managerHash().containsKey(txtUser.getText()) && Manager.managerHash().get(txtUser.getText()).equals(new String(fldPassword.getPassword())) ) {
						Admin.IsAdminLoggedIn = false;
						Manager.isManagerLoggedIn = true;
						Manager.managerLoggedIn = Manager.getManagerID(txtUser.getText());
						mainFrame.frame.add(new GUIManagerMenu());
						this.setVisible(false); 
					}
					
					
					else {
						this.resultLabel.setText("Incorrect Login or Nonexistent Account!");
						this.resultLabel.setFont(new Font("Serif", Font.BOLD, 17));
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  
			  }
			  
			  else {
				 this.resultLabel.setText("Complete your login details!");

			}
			  
		  }

		}
}
