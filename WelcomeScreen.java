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
import java.util.ArrayList;
import java.util.HashMap;
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
	 
	private Vector column;
	private Vector data;
	private Vector row;
	
	private int i;
	
	public WelcomeScreen(){
	
		 JDBC = new MyJDBC(); //creates a MyJDBC instance
	
		//initialize jdbc variables
		 con = null;
		 st = null;
		 rs = null;
		 s = "";
		 
		 //initialize table variables
		 data = new Vector();
		 row = new Vector();
		 
		 i = 0;
		 
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
		mainPanel.setLayout(new BorderLayout(0, 20));
		
		//welcome text on top 
		JLabel welcomeLabel = new JLabel("Welcome Screen", SwingConstants.CENTER);
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(welcomeLabel, BorderLayout.PAGE_START);
		
		
		//calls the methods that adds the other gui
		displayAddPanel();
		displayDeletePanel();
		displayUpdatePanel();
		displayTabel("Login");

		
		frame.add(mainPanel);
		frame.pack();
		frame.setResizable(false);
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
		
		deleteWebsite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField field1 = new JTextField();
				Object[] message = {
					    "Website:", field1, 
				};
				
				int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
				
				if (option == JOptionPane.OK_OPTION)
				{
				    String value1 = field1.getText();
				    JDBC.deleteWebsite(value1);
				}
			}
		});
		
		deleteUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField field1 = new JTextField();
				Object[] message = {
					    "Email:", field1, 
				};
				
				int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
				
				if (option == JOptionPane.OK_OPTION)
				{
				    String value1 = field1.getText();
				    JDBC.deleteUser(value1);
				}
			}
		});
		
		deleteLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField field1 = new JTextField();
				JTextField field2 = new JTextField();
				Object[] message = {
					    "Email:", field1, 
					    "Website:", field2, 
				};
				
				int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
				
				if (option == JOptionPane.OK_OPTION)
				{
				    String value1 = field1.getText();
				    String value2 = field2.getText();
				    JDBC.deleteLogin(value1, value2);
				   
				}
			}
		});
		
		
		
		
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
		
		addUpdateActionListener(updateLogin);
		addUpdateActionListener(updateWebsite);
		addUpdateActionListener(updateUser);
		
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
		       column = new Vector(columnCount);
		       for(int i = 1; i <= columnCount; i++)
		       {
		           column.add(rsmt.getColumnName(i)); //adds the name of each attribute to column
		       }
		       data = new Vector();
		       row = new Vector();
		       while(rs.next())
		       {
		           row = new Vector(columnCount);
		           for(int i = 1; i <= columnCount; i++){
		               row.add(rs.getString(i));
		           }
		           data.add(row);
		       }   
		          
		JPanel bottomPanel = new JPanel(); //holds the table and searchShowButtonPanel 
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
		JLabel tableLabel = new JLabel(tableName);
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
		searchActionListener(search);
	
		
		bottomPanel.add(tableLabel);
	    bottomPanel.add(jsp);
	    bottomPanel.add(searchShowButtonPanel);
	  	
	  	
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		}
	    catch(Exception e){
	        JOptionPane.showMessageDialog(null, "Can't display table");
	    }
		
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
				else if(addButton.getText().equals("Add Website")){
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
				else if(addButton.getText().equals("Add Login")){
					Object[] message = {
					    "Email:", field1, 
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
	
	public void addUpdateActionListener(final JButton addButton){
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField field1 = new JTextField();
				JTextField field2 = new JTextField();
				JTextField field3 = new JTextField();
				JTextField field4 = new JTextField();
				JTextField field5 = new JTextField();
				JTextField field6 = new JTextField();
				JTextField field7 = new JTextField();
				
				//popup for Login
				if(addButton.getText().equals("Update Login")){
					Object[] message = {
						"Email:", field1,
						"Website:", field2,
					    "New Username:", field3,
					    "New Password:", field4,
					};
					int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
					
					if (option == JOptionPane.OK_OPTION)
					{
					    String email = field1.getText();
					    String website = field2.getText();
					    String username = field3.getText();
					    String password = field4.getText();
					    JDBC.updateLogin(email, website, username, password);
					}
				}
				
				//popup for updateUser
				if(addButton.getText().equals("Update User")){
					Object[] message = {
						"Email:", field1,
					    "New Name:", field2, 
					    "New Age:", field3,
					    "New State:", field4,
					    "New Email:", field5,
					};
					int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
					
					if (option == JOptionPane.OK_OPTION)
					{
					    String value1 = field1.getText();
					    String value2 = field2.getText();
					    int value3 = Integer.parseInt(field3.getText());
					    String value4 = field4.getText();
					    String value5 = field5.getText();
					    JDBC.updateUser(value1, value2, value3, value4, value5);
					}
				}
				
				//popup for updateWebsite
				else if(addButton.getText().equals("Update Website")){
					Object[] message = {
						"Current Website Name:", field1,	
					    "New Website Name:", field2, 
					    "New Domain Name:", field3,
					    "New IP Address:", field4,
					    "New Server Location (state):", field5,
					};
					int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
					
					if (option == JOptionPane.OK_OPTION)
					{
						String currentSiteName = field1.getText();
					    String siteName = field2.getText();
					    String domainName = field3.getText();
					    String IPAddress = field4.getText();
					    String serverLocation = field5.getText();
					    JDBC.updateWebsite(currentSiteName, siteName, domainName, IPAddress, serverLocation);
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
				frame.revalidate();  
			}
		});
	}
	
	public void searchActionListener(JButton searchButton){
		searchButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame searchFrame = new JFrame();
				JPanel searchPanel = new JPanel();
				
				JLabel searchLabel = new JLabel("Table Summaries");
				JButton left = new JButton("<");
				JButton right = new JButton(">");

				//Name of Query
				ArrayList<String> nameOfQuery = new ArrayList<>();
				nameOfQuery.add("Average User Age");
				nameOfQuery.add("Average Signup Date");
				nameOfQuery.add("Number of Users");
				nameOfQuery.add("Number of Websites");
				
				//actual query
				ArrayList<String> actualQuery = new ArrayList<>();
				actualQuery.add(JDBC.averageUserAge());
				actualQuery.add(JDBC.averageSignupDate());
				actualQuery.add(JDBC.numberOfUsers());
				actualQuery.add(JDBC.numberOfSites());
				
				
				//listeners for buttons
				left.addActionListener(new ActionListener(){
	
						@Override
						public void actionPerformed(ActionEvent e) {
							
							displaySearchTables(nameOfQuery.get(i), actualQuery.get(i));
							frame.revalidate();
							if(i <= 0){
								i = 0;
							}
							else{
							i--;
							}
						}
					
				});
				
				right.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
				
						displaySearchTables(nameOfQuery.get(i), actualQuery.get(i));
						frame.revalidate();
						if(i == nameOfQuery.size() -1){
							i = nameOfQuery.size() -1;
						}
						else{
						i++;
						}
					}
				});
				
				searchPanel.add(left);
				searchPanel.add(searchLabel);
				searchPanel.add(right);
				searchFrame.add(searchPanel);
				searchFrame.pack();
				searchFrame.setVisible(true);
			}
		});
	}
	
	
	public void displaySearchTables(String tableName, String actualQuery){
		
		
		//connect to mysql database
		   try{
	
		       st = con.createStatement();
		       s = actualQuery;
		       rs = st.executeQuery(s);
		       ResultSetMetaData rsmt = rs.getMetaData();
		       int columnCount = rsmt.getColumnCount();
		       column = new Vector(columnCount);
		       for(int i = 1; i <= columnCount; i++)
		       {
		           column.add(rsmt.getColumnName(i)); //adds the name of each attribute to column
		       }
		       data = new Vector();
		       row = new Vector();
		       while(rs.next())
		       {
		           row = new Vector(columnCount);
		           for(int i = 1; i <= columnCount; i++){
		               row.add(rs.getString(i));
		           }
		           data.add(row);
		       }   
		          
		JPanel bottomPanel = new JPanel(); //holds the table and searchShowButtonPanel 
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
		JLabel tableLabel = new JLabel(tableName);
		JTable table = new JTable(data,column); //creates the table with data
		JScrollPane jsp = new JScrollPane(table); //scroll for table
		tableLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
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
		searchActionListener(search);
	
		
		bottomPanel.add(tableLabel);
	    bottomPanel.add(jsp);
	    bottomPanel.add(searchShowButtonPanel);
	  	
	  	
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		}
		   
		   catch (SQLException ex) {
				// handle any errors
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}	
	}
}
