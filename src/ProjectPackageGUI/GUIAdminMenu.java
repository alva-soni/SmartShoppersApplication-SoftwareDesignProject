package ProjectPackageGUI;

import javax.swing.JPanel;

import normalClasses.Admin;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import java.awt.SystemColor;

public class GUIAdminMenu extends JPanel implements ActionListener {

	JButton btnManageStores = new JButton();
	JButton btnLogOut = new JButton();
	JButton btnAddStores = new JButton();
	JButton btnManagers = new JButton();
	JButton btnAdminInfo = new JButton();
	JLabel adminName = new JLabel();
	
	public GUIAdminMenu() {
		setBackground(new Color(135, 206, 250));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SmartShoppers Admin Menu");
		lblNewLabel.setForeground(new Color(173, 255, 47));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(94, 11, 274, 34);
		add(lblNewLabel);
		
		btnManageStores = new JButton("Manage Stores");
		btnManageStores.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnManageStores.setBackground(new Color(173, 255, 47));
		btnManageStores.setBounds(157, 87, 139, 34);
		add(btnManageStores);
		
		btnLogOut = new JButton("Logout");
		btnLogOut.setBackground(Color.ORANGE);
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOut.setBounds(351, 399, 89, 23);
		add(btnLogOut);
		
		btnAddStores = new JButton("Add Stores");
		btnAddStores.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddStores.setBackground(new Color(173, 255, 47));
		btnAddStores.setBounds(157, 164, 139, 34);
		add(btnAddStores);
		
		btnManagers = new JButton("Managers");
		btnManagers.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnManagers.setBackground(new Color(173, 255, 47));
		btnManagers.setBounds(157, 240, 139, 39);
		add(btnManagers);
		
		JLabel lblNewLabel_1 = new JLabel("Update SmartShoppers Stores Products and Information");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(55, 74, 357, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Add New SmartShoppers Stores");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(139, 148, 200, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Manage Managers");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(168, 225, 150, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Admin Info");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(193, 319, 72, 14);
		add(lblNewLabel_4);
		
		btnAdminInfo = new JButton("View or Update");
		btnAdminInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdminInfo.setBackground(new Color(173, 255, 47));
		btnAdminInfo.setBounds(149, 336, 147, 39);
		add(btnAdminInfo);
		
		try {
			adminName = new JLabel(Admin.getAdminName(Admin.adminLoggedIn));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adminName.setForeground(Color.BLUE);
		adminName.setFont(new Font("Tahoma", Font.BOLD, 12));
		adminName.setBounds(168, 49, 272, 14);
		add(adminName);
		
		addActionEvent();

	}
	
	public void addActionEvent() {
		btnAddStores.addActionListener(this);
		btnLogOut.addActionListener(this);
		btnManagers.addActionListener(this);
		btnManageStores.addActionListener(this);
		btnAdminInfo.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
 
		if (e.getSource() == btnLogOut) {
			Admin.IsAdminLoggedIn = false;
			mainFrame.frame.getContentPane().add(new GUILogin());
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnManageStores) {
			//display all the stores with buttons that give the options to update store info, update product info, and add products
			try {
				mainFrame.frame.getContentPane().add(new GUIManageStores());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnAddStores) {
			try {
				mainFrame.frame.getContentPane().add(new GUICreateStore());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnManagers) {
			try {
				mainFrame.frame.getContentPane().add(new GUIManageManagers());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
			
		else if (e.getSource() == btnAdminInfo) {
			try {
				mainFrame.frame.getContentPane().add(new GUIAdminUpdateInfo());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
	}

}
