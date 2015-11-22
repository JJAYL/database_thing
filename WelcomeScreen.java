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
	private MyJDBC JDBC;
	
	public WelcomeScreen(){
		
		
		 JDBC = new MyJDBC(); //creates a MyJDBC instance
		
		
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
		displayTabel("Website");

		
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
		
		//adds action listener for each add button
		addUserActionListener(addUser);
		addUserActionListener(addWebsite);
		addUserActionListener(addLogin);
		
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
	
	public void displayTabel(String tableName){
		//connect to mysql database
		   try{
	
		       st = con.createStatement();
		       s = "select * from " + tableName;
		       rs = st.executeQuery(s);
		       ResultSetMetaData rsmt = rs.getMetaData();
		       int columnCount = rsmt.getColumnCount();
		       Vector column = new Vector(columnCount);
		       for(int i = 1; i <= columnCount; i++)
		       {
		           column.add(rsmt.getColumnName(i)); //adds the name of each attribute to column
		       }
		       Vector data = new Vector();
		       Vector row = new Vector();
		       while(rs.next())
		       {
		           row = new Vector(columnCount);
		           for(int i = 1; i <= columnCount; i++){
		               row.add(rs.getString(i));
		           }
		           data.add(row);
		           //System.out.println(data);
		       }
		       
		JPanel bottomPanel = new JPanel(); //holds the table and searchShowButtonPanel 
		JTable table = new JTable(data,column); //creates the table with data
		JScrollPane jsp = new JScrollPane(table); //scroll for table
		
		
		
		/***********************/
		//search, and show buttons
		JButton search = new JButton("Search");
		JButton showWebsites = new JButton("Show Websites");
		JButton showUsers = new JButton("Show Users");
		JButton showLogins = new JButton("Show Logins");
		
		//adds each button to panel
		JPanel searchShowButtonPanel = new JPanel();
		searchShowButtonPanel.setLayout(new FlowLayout());
		searchShowButtonPanel.add(search);
		searchShowButtonPanel.add(showWebsites);
		searchShowButtonPanel.add(showUsers);
		searchShowButtonPanel.add(showLogins);
		
		//adds action listeners to each button
		showTableActionListener(showWebsites, "Website");
		showTableActionListener(showLogins, "Login");
		showTableActionListener(showUsers, "Users");
		
	
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
	    bottomPanel.add(jsp);
	    bottomPanel.add(searchShowButtonPanel);
	  	//bottomPanel.add(search);
	  	
	  	
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		frame.revalidate();  
		}
	    catch(Exception e){
	        JOptionPane.showMessageDialog(null, "Can't display table");
	    }
		//
	}

	public void addUserActionListener(final JButton addButton){
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField field1 = new JTextField();
				JTextField field2 = new JTextField();
				JTextField field3 = new JTextField();
				JTextField field4 = new JTextField();
				JTextField field5 = new JTextField();
				
				//popup for addUser
				if(addButton.getText().equals("Add User")){
					Object[] message = {
					    "Name:", field1, 
					    "Age:", field2,
					    "State:", field3,
					    "Email:", field4,
					};
					int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
					
					if (option == JOptionPane.OK_OPTION)
					{
					    String value1 = field1.getText();
					    int value2 = Integer.parseInt(field2.getText());
					    String value3 = field3.getText();
					    String value4 = field4.getText();
					    JDBC.insertUser(value1, value2, value3, value4);
					}
				}
				
				//popup for addWebsite
				if(addButton.getText().equals("Add Website")){
					Object[] message = {
					    "Website Name:", field1, 
					    "Domain Name:", field2,
					    "IP Address:", field3,
					    "Server Location (state):", field4,
					    "reason:", field5,
					};
					int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
					
					if (option == JOptionPane.OK_OPTION)
					{
					    String siteName = field1.getText();
					    String domainName = field2.getText();
					    String IPAddress = field3.getText();
					    String serverLocation = field4.getText();
					    String reason = field5.getText();
					    JDBC.insertWebsite(siteName, domainName, IPAddress, serverLocation, reason);
					}
				}
				
				//popup for Login
				if(addButton.getText().equals("Add Login")){
					Object[] message = {
					    "Name:", field1, 
					    "Website:", field2,
					    "Username:", field3,
					    "Password:", field4,
					    "Date (YYYY/MM/DD):", field5,
					};
					int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
					
					if (option == JOptionPane.OK_OPTION)
					{
					    String name = field1.getText();
					    String website = field2.getText();
					    String username = field3.getText();
					    String password = field4.getText();
					    String dateCreated = field5.getText();
					    JDBC.insertLogin(name, website, username, password, dateCreated);
					}
				}
				
				
				
				
			}
		});
	}
	public void showTableActionListener(JButton showButton, final String name){
		showButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				displayTabel(name);	
			}
		});
	}
}
