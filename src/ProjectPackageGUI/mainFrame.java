package ProjectPackageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class mainFrame extends JFrame{

	public static JFrame frame;
	 
	 public mainFrame() throws FileNotFoundException {
	  frame = new JFrame();
	  frame.setSize(500,500);
	  frame.setTitle("SmartShoppers");
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setLocationRelativeTo(null);
	  frame.add(new GUILogin());  
	  frame.setResizable(false);
	  frame.setVisible(true);  


	   

	 }
	 
	 

}
