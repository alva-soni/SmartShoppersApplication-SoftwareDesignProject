package ProjectPackageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Component;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

import normalClasses.Admin;
import normalClasses.Manager;
import normalClasses.Store;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class GUIManageManagers extends JPanel implements ActionListener{

	List<JButton> updateManager = new ArrayList<>();
	List<JLabel> managerIDs = new ArrayList<>();
	JButton btnGoBack = new JButton();
	private JButton btnAddManager = new JButton();
	/**
	 * Create the panel. 
	 * @throws IOException 
	 */
	public GUIManageManagers() throws IOException {
		setBackground(new Color(135, 206, 250));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		add(panel);
				
		btnAddManager = new JButton("Add Manager");
		btnAddManager.addActionListener(this);
		add(btnAddManager);
		
		JLabel lblNewLabel = new JLabel("All SmartShopper Managers");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

		JScrollPane suggestProductsArea = new JScrollPane(showManagerList()); //HERE FOR SHOPPING LIST PRODUCTS
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAddManager))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(suggestProductsArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
							)))));
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddManager)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(suggestProductsArea, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
		));
		panel.setLayout(gl_panel);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(this);
		btnGoBack.setVerticalAlignment(SwingConstants.TOP);
		btnGoBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(btnGoBack);

	}

	public JPanel showManagerList() throws IOException {

		List<JLabel> managerNames = new ArrayList<>();
		List<JLabel> managerStoreIds = new ArrayList<>();
		List<JLabel> space = new ArrayList<>();


		JPanel storeListPane = new JPanel();
		storeListPane.setLayout(new BoxLayout(storeListPane, BoxLayout.PAGE_AXIS));
	        // add list to frame
		for (int i = 0; i < Manager.searchManagerDatabase().size(); i++) {
	        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
	        //List all store names
	        managerNames.add(new JLabel(Manager.allManagerNames().get(i)));
	        managerNames.get(i).setFont(new Font("Serif", Font.BOLD, 16));
	        managerNames.get(i).setBorder(border);
		    storeListPane.add(managerNames.get(i));
		    
		    managerIDs.add(new JLabel("ID: " + Manager.allManagerIDs().get(i)));
		    managerIDs.get(i).setFont(new Font("Serif", Font.BOLD, 13));
		    storeListPane.add(managerIDs.get(i));
 
		    //List all manager Stores
		    String storeString = "No Store Assigned";
		    if (!Manager.allManagerStoreIDs().get(i).equals("N/A")) {
		    	storeString = Store.getStoreName(Manager.allManagerStoreIDs().get(i), "data/Stores.txt");
		    }
		    managerStoreIds.add(new JLabel("Store: " + storeString)); 
		    managerStoreIds.get(i).setFont(new Font("Serif", Font.BOLD, 15));
		    storeListPane.add(managerStoreIds.get(i));
		    
		    updateManager.add(new JButton("View/Update " + Manager.allManagerNames().get(i)));
		    updateManager.get(i).addActionListener(this);
		    updateManager.get(i).setBackground(Color.GREEN);
			storeListPane.add(updateManager.get(i)); 
			
			space.add(new JLabel("                                                    "));
			storeListPane.add(space.get(i));
		} 
		return storeListPane;
}
	 
	//Edit this to show suggested products
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnGoBack) {
			
			mainFrame.frame.getContentPane().add(new GUIAdminMenu());
			this.setVisible(false);
		}

		//go to page that displays products using the store ID of chosen store
		for (int i = 0; i < updateManager.size(); i++ ) {
			if (e.getSource() == updateManager.get(i)) {
	
				String[] parts = managerIDs.get(i).getText().split(" ",2);
				String managerID = parts[1];
				Admin.viewedManager = managerID;
				try {
					mainFrame.frame.getContentPane().add(new GUIManagerUpdateInfo());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.setVisible(false);
			
			}
		}
 
		if (e.getSource() == btnAddManager) {
			try {
				mainFrame.frame.getContentPane().add(new GUIAddManager());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);

		}
		
	}
	
	
}
