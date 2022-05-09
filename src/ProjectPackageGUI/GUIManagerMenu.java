package ProjectPackageGUI;

import javax.swing.JPanel;

import normalClasses.Manager;
import normalClasses.Products;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import java.awt.SystemColor;

public class GUIManagerMenu extends JPanel implements ActionListener {

	JButton btnManageStores = new JButton();
	private JButton btnManageStore;
	JButton btnLogOut = new JButton();
	JButton btnAddStores = new JButton();
	JButton btnManagers = new JButton();
	private JButton btnProducts;
	JButton btnAdminInfo = new JButton();
	private JButton btnManagerInfo;
	JLabel adminName = new JLabel();
	private JLabel managerName;
	
	public GUIManagerMenu() {
		setBackground(new Color(205, 133, 63));
		setLayout(null);
		
		JLabel lblNewLabel;
		lblNewLabel = new JLabel("SmartShoppers Manager Menu");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(95, 11, 298, 34);
		add(lblNewLabel);

		
		btnManageStore = new JButton("Manage Store");
		btnManageStore.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnManageStore.setBackground(new Color(173, 255, 47));
		btnManageStore.setBounds(157, 138, 139, 39);
		add(btnManageStore);
		
		btnLogOut = new JButton("Logout");
		btnLogOut.setBackground(Color.ORANGE);
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOut.setBounds(351, 399, 89, 23);
		add(btnLogOut);
		
		btnProducts = new JButton("Products");
		btnProducts.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnProducts.setBackground(new Color(173, 255, 47));
		btnProducts.setBounds(157, 221, 139, 39);
		add(btnProducts);
		
		JLabel lblNewLabel_1 = new JLabel("Update Store Hours");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(167, 113, 155, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Manage Store Products");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(160, 203, 150, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Manager Info");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(178, 291, 106, 14);
		add(lblNewLabel_4);
		
		btnManagerInfo = new JButton("View or Update");
		btnManagerInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnManagerInfo.setBackground(new Color(173, 255, 47));
		btnManagerInfo.setBounds(157, 316, 139, 39);
		add(btnManagerInfo);
		
		try {
			managerName = new JLabel("Manager: " + Manager.getManagerName(Manager.managerLoggedIn));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		managerName.setForeground(new Color(47, 79, 79));
		managerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		managerName.setBounds(145, 51, 295, 14);
		add(managerName);
		
		JLabel storeNamelabel;
		try {
			storeNamelabel = new JLabel("Store: " + Manager.getManagerStoreID(Manager.managerLoggedIn));
			storeNamelabel.setForeground(new Color(47, 79, 79));
			storeNamelabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			storeNamelabel.setBounds(145, 76, 295, 14);
			add(storeNamelabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addActionEvent();

	}
	
	public void addActionEvent() {
		btnLogOut.addActionListener(this);
		btnProducts.addActionListener(this);
		btnManageStore.addActionListener(this);
		btnManagerInfo.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
 
		if (e.getSource() == btnLogOut) {
			Manager.isManagerLoggedIn = false;
			mainFrame.frame.getContentPane().add(new GUILogin());
			this.setVisible(false);
		}
		
		else if (e.getSource() == btnManageStore) {
			if (!Manager.hasStoreBoolean()) { //If a manager doesn't have a store assigned
				JOptionPane.showMessageDialog(this, "No Store Assigned!");
			}
			else {
				try {
					mainFrame.frame.getContentPane().add(new GUIManagerUpdatesStore());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.setVisible(false);
			}
		}
		
		else if (e.getSource() == btnProducts) {
			
			if (!Manager.hasStoreBoolean()) {//If a manager doesn't have a store assigned
				JOptionPane.showMessageDialog(this, "No Store Assigned!");
			}
			else {
				try {
					Products.currentStoreID = Manager.getManagerStoreID(Manager.managerLoggedIn);
					mainFrame.frame.getContentPane().add(new GUIProductManagement2());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.setVisible(false);
			}
		}
			
		else if (e.getSource() == btnManagerInfo) {
			try {
				mainFrame.frame.getContentPane().add(new GUIManagerProfile());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		
	}

}
