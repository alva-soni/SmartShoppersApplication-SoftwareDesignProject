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

import normalClasses.Manager;
import normalClasses.Store;

import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class GUIManagerUpdatesStore extends JPanel implements ActionListener{
//User can update their name, email, password, and opt in for 2FA via a checkbox
	
	private JLabel addressLabel, hoursLabel, resultLabel, headingLabel, introLabel, nameLabel;
	private JTextField txtAddress, nameStore, fldHours;
	//private JPasswordField fldPassword;
	private JButton btnSubmit; //confirm info is correct and what database it belongs to + what page to open
	private JButton btnGoBack;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox = new JComboBox();
	private JTextField currentManager;
	JCheckBox boxChngeManager = new JCheckBox();
	private JCheckBox bxDelete = new JCheckBox();

	 
	public GUIManagerUpdatesStore() throws IOException {
		setBackground(new Color(210, 105, 30));
		setForeground(Color.PINK);

		headingLabel = new JLabel(Store.getStoreName(Manager.getManagerStoreID(Manager.managerLoggedIn), "data/Stores.txt")+ " Information Update");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 21));
		introLabel = new JLabel("Update Store Information");
		introLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel = new JLabel("Store Name:");
		addressLabel = new JLabel("Store Address:");
		hoursLabel = new JLabel("Store Hours:");
		resultLabel = new JLabel();
		resultLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		nameStore = new JTextField();
		nameStore.setEditable(false);
		fldHours = new JTextField();

		btnSubmit = new JButton("Update Info");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit.setBackground(new Color(173, 255, 47));
		btnGoBack = new JButton("Go Back");
		

		addComponents();
		addActionEvent();
		setLocationAndSize();
		
		this.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Store Manager:");
		lblNewLabel_1.setBounds(55, 265, 90, 14);
		add(lblNewLabel_1);
		
		currentManager = new JTextField();
		currentManager.setEditable(false);
		currentManager.setBounds(150, 261, 200, 20);
		add(currentManager);
		currentManager.setColumns(10);
		
	
		currentInfo();
		


		}


		public void addComponents() {	
		this.add(headingLabel);
		this.add(introLabel);
		this.add(nameLabel);
		this.add(nameStore);
		this.add(addressLabel);
		this.add(txtAddress);
		this.add(hoursLabel);
		this.add(fldHours);
		this.add(btnSubmit);
		this.add(btnGoBack);
		this.add(resultLabel);
		}

		public void addActionEvent() {
		btnSubmit.addActionListener(this);
		btnGoBack.addActionListener(this);
		}

		public void setLocationAndSize() {
		headingLabel.setBounds(16,11,467, 30);
		introLabel.setBounds(142, 80, 176, 30);
		nameLabel.setBounds(55, 140, 100, 30);
		nameStore.setBounds(150, 140, 200, 30);

		addressLabel.setBounds(55, 181, 100, 30);
		hoursLabel.setBounds(55, 220, 100, 30);
		txtAddress.setBounds(150, 180, 200, 30);
		fldHours.setBounds(150, 220, 200, 30);
		btnSubmit.setBounds(169, 313, 131, 42);
		btnGoBack.setBounds(169, 424, 123, 27);
		resultLabel.setBounds(74, 365, 336, 30);

		}

	
		public void currentInfo() throws IOException {
			nameStore.setText(Store.getStoreName(Manager.getManagerStoreID(Manager.managerLoggedIn), "data/Stores.txt"));
			txtAddress.setText(Store.getStoreAddress(Manager.getManagerStoreID(Manager.managerLoggedIn), "data/Stores.txt"));
			fldHours.setText(Store.getStoreHours(Manager.getManagerStoreID(Manager.managerLoggedIn), "data/Stores.txt"));
			currentManager.setText(Manager.getManagerName(Manager.managerLoggedIn));
		}
	
	 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnGoBack) {
			mainFrame.frame.getContentPane().add(new GUIManagerMenu());
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnSubmit) {
			if (!txtAddress.getText().equals("") && !nameStore.getText().equals("") && !fldHours.getText().equals("")) {
				try {
					String text = Store.getStoreID(Manager.getManagerStoreID(Manager.managerLoggedIn), "data/Stores.txt") + "," + nameStore.getText() + "," + txtAddress.getText() + "," + fldHours.getText();
					Manager.updateStoreInfo(text,Manager.getManagerStoreID(Manager.managerLoggedIn) );
					resultLabel.setText("Store Information Updated!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	
	}
}


