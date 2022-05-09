package ProjectPackageGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.PanelUI;

public class GUIStoreSearch extends JPanel implements ActionListener{

	private JLabel locationLabel, headingLabel, introJLabel, errorLabel;
	private JButton btnSearch, btnBacktoMenu;
	private JTextField userLocationField;
	 
	public GUIStoreSearch() {
		this.setBackground(Color.PINK);

		headingLabel = new JLabel("SmartShoppers Store Search");
		headingLabel.setFont(new Font("Serif", Font.BOLD, 22));
		locationLabel = new JLabel("Input your Location");
		locationLabel.setFont(new Font("Serif", Font.BOLD, 17));
		btnSearch = new JButton("Search");
		btnBacktoMenu = new JButton("Back to Menu");
		userLocationField = new JTextField();
		introJLabel = new JLabel("Search for Stores");
		errorLabel = new JLabel();

		addComponents();
		addActionEvent();
		setLocationAndSize();
		
		this.setLayout(null);

	}
	 
	public void addComponents() {	
		this.add(headingLabel);
		this.add(locationLabel);
		this.add(btnSearch);
		this.add(btnBacktoMenu);
		this.add(userLocationField);
		this.add(introJLabel);
		this.add(errorLabel);
	}
	
	public void addActionEvent() {
		btnSearch.addActionListener(this);
		btnBacktoMenu.addActionListener(this);
	}
	
	public void setLocationAndSize() {
		headingLabel.setBounds(100, 50, 350, 30);
		introJLabel.setBounds(175, 80, 350, 30);
		locationLabel.setBounds(160, 150, 200, 30);
		userLocationField.setBounds(80, 190, 300, 30);
		btnSearch.setBounds(130, 230, 200, 30);
		btnBacktoMenu.setBounds(145, 400, 170, 30);
		errorLabel.setBounds(140, 300, 400, 30);
		
	}
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnBacktoMenu) {
			try {
				mainFrame.frame.add(new GUICustomerIntroMenu());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		if (e.getSource() == btnSearch && !userLocationField.getText().equals("")) {
			try {
				mainFrame.frame.add(new GUISearchStoreList()); //change to GUISearchStoreList()
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);   
		}
		  
		else {
			this.errorLabel.setText("Input an Address in the Textfield");
		}
		
		
	}

}
