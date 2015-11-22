import javax.swing.*;  

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class WelcomeScreen {
	
	private JFrame frame;
	private JPanel mainPanel;
	
	//JDBC stuff
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private String s;
	
	public WelcomeScreen(){
		
		//initialize jdbc variables
		 con = null;
		 st = null;
		 rs = null;
		 s = "";
		 
		 try {
		       Class.forName("com.mysql.jdbc.Driver").newInstance();
		 } 
		 catch (Exception ex) {
		       // handle the error
		   	System.out.println("JDBC driver not found");
		 }
		   
		//connect to mysql database
		 try{
		       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtest", "root", "Gohardorgohome1!");
		       
		 }
		 catch (SQLException ex) {
	            // handle any errors
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	     }	
	      
		//initialize GUI
		 
		//most outer layouts
		//order of gui layouts. frame, mainPanel (BorderLayout), addPanel/deletePanel/updatePanel (BoxLayouts)
		frame = new JFrame();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		
		//welcome text on top 
		JLabel welcomeLabel = new JLabel("Welcome Screen", SwingConstants.CENTER);
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(welcomeLabel, BorderLayout.PAGE_START);
		
		
		//calls the methods that adds the other gui
		displayAddPanel();
		displayDeletePanel();
		displayUpdatePanel();
		displayTabel();

		
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}
	
	public void displayAddPanel(){
		//Add panel and buttons
		JPanel addPanel = new JPanel();
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.PAGE_AXIS));
		JLabel addLabel = new JLabel("Add");
		JButton addWebsite = new JButton("Add Website");
		JButton addUser = new JButton("Add User");
		JButton addLogin = new JButton("Add Login");
		
		addPanel.add(addLabel);
		addPanel.add(addWebsite);
		addPanel.add(addUser);
		addPanel.add(addLogin);
		
		addLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		addWebsite.setAlignmentX(Component.CENTER_ALIGNMENT);
		addUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		addLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainPanel.add(addPanel, BorderLayout.LINE_START);	
	}
	
	public void displayDeletePanel(){
		//Delete panel and buttons
		JPanel deletePanel = new JPanel();
		deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.PAGE_AXIS));
		JLabel deleteLabel = new JLabel("Delete");
		JButton deleteWebsite = new JButton("Delete Website");
		JButton deleteUser = new JButton("Delete User");
		JButton deleteLogin = new JButton("Delete Login");
		
		deletePanel.add(deleteLabel);
		deletePanel.add(deleteWebsite);
		deletePanel.add(deleteUser);
		deletePanel.add(deleteLogin);
		
		deleteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteWebsite.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainPanel.add(deletePanel, BorderLayout.CENTER);
	}

	public void displayUpdatePanel(){
		//update panel and buttons
		JPanel updatePanel = new JPanel();
		updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.PAGE_AXIS));
		JLabel updateLabel = new JLabel("Update");
		JButton updateWebsite = new JButton("Update Website");
		JButton updateUser = new JButton("Update User");
		JButton updateLogin = new JButton("Update Login");
		
		updatePanel.add(updateLabel);
		updatePanel.add(updateWebsite);
		updatePanel.add(updateUser);
		updatePanel.add(updateLogin);
		
		updateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		updateWebsite.setAlignmentX(Component.CENTER_ALIGNMENT);
		updateUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		updateLogin.setAlignmentX(Component.CENTER_ALIGNMENT);	
		
		mainPanel.add(updatePanel, BorderLayout.LINE_END);
	}
	
	public void displayTabel(){
		//connect to mysql database
		   try{
	
		       st = con.createStatement();
		       s = "select * from Users";
		       rs = st.executeQuery(s);
		       ResultSetMetaData rsmt = rs.getMetaData();
		       int c = rsmt.getColumnCount();
		       Vector column = new Vector(c);
		       for(int i = 1; i <= c; i++)
		       {
		           column.add(rsmt.getColumnName(i));
		       }
		       Vector data = new Vector();
		       Vector row = new Vector();
		       while(rs.next())
		       {
		           row = new Vector(c);
		           for(int i = 1; i <= c; i++){
		               row.add(rs.getString(i));
		           }
		           data.add(row);
		       }
		       
		JPanel bottomPanel = new JPanel(); //holds the table and search button
		JTable table = new JTable(data,column); //creates the table with data
		JScrollPane jsp = new JScrollPane(table); //scroll for table
		JButton search = new JButton("Search");
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
	    bottomPanel.add(jsp);
	  	bottomPanel.add(search);
	  	
	  	
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		   
		}
	    catch(Exception e){
	        JOptionPane.showMessageDialog(null, "method error");
	    }finally{
	        try{
	        st.close();
	        rs.close();
	        con.close();
	        }catch(Exception e){
	            JOptionPane.showMessageDialog(null, "method  error2");
	        }
	    }   
		//
	}

}
