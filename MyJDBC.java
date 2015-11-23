
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MyJDBC
{
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

	public MyJDBC()
	{
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        	System.out.println("JDBC driver not found");
            }
		try {
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtest", "root", "Gohardorgohome1!");
    	    stmt = conn.createStatement();
	     } catch (SQLException ex) {
	            // handle any errors
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	            }		
	    System.out.println("completed connection");   
	}
	
	public void createDB() throws SQLException
	{
		try {
			stmt.executeUpdate(
					"CREATE TABLE Website ( " +
							"WebsiteID INTEGER NOT null auto_increment, " +
							"Name VARCHAR(32) UNIQUE ," +
							"DomainName VARCHAR(32), " +
							"IPAddress VARCHAR(32), " +
							"Serverlocation VARCHAR(32), " +
							"reason VARCHAR(32), " +
							"primary key(WebsiteID)" +
							")"
					);
			
			stmt.executeUpdate(
					"CREATE TABLE Users (" +
							"UserID INTEGER NOT null auto_increment, " +
							"Name VARCHAR(32)," +
							"Age INTEGER, " +
							"State VARCHAR(32), " +
							"EmailAddress VARCHAR(32) UNIQUE, " +
							"primary key(UserID)" +
							")"
					);
		
			stmt.executeUpdate(
					"CREATE TABLE Login ( " +
							"LoginID INTEGER NOT null auto_increment, " +
							"UserID INTEGER, " +
							"WebsiteID INTEGER, " +
							"Username VARCHAR(32), " +
							"Password VARCHAR(32), " +
							"DateCreated DATE, " + 
							"primary key(LoginID)," +
							"CONSTRAINT fk1 FOREIGN KEY (UserID) REFERENCES Users(UserID), " +
							"CONSTRAINT fk2 FOREIGN KEY (websiteID) REFERENCES Website(WebsiteID) " +						
							")"
					);
		
			
		
 	     } catch (SQLException ex) {
 	            // handle any errors
 	            System.out.println("SQLException: " + ex.getMessage());
 	            System.out.println("SQLState: " + ex.getSQLState());
 	            System.out.println("VendorError: " + ex.getErrorCode());
 	      }	
		
	}

	
	public void loadData() throws SQLException
	{
		
			
			System.out.print("Website Insert \n");
			/*
			stmt.executeUpdate(
					"INSERT INTO Website (Name, DomainName, IPAddress, ServerLocation, reason) VALUES " +
							"('Google', 'google.com', '8.8.8.8', 'CA', 'search engine'), " +
							"('localhost', 'localhost/', '127.0.0.1', 'this computer', 'personal website'), " +
							"('Olympia', 'olympia.com', '123.465.789', 'Olympus', 'dating'); "
					);
			*/
			insertWebsite("Google", "google.com", "8.8.8.8", "CA", "search engine");
			insertWebsite("localhost", "localhost.com", "8.8.8.8", "CA", "search engine");
			insertWebsite("Olympia", "olympia.com", "8.8.8.8", "CA", "search engine");
			
			System.out.print("Website Insert passed \n");
			
			
			System.out.print("Users Insert \n");
			/*
			stmt.executeUpdate(
	    			"INSERT INTO Users(Name, Age, State, EmailAddress) VALUES " +
	    				"('KevinY', 19, 'California', 'joachimlerman@gmail.com'), " +
	    	           	"('Joachim', 19, 'California', 'kevinyan@gmail.com'), " +
	    	           	"('Benjamin', 19, 'California', 'benjamin.hi@gmail.com')"
	            	);
	         */
			insertUser("KevinY", 21, "CA", "kyan@gmail.com");
			insertUser("Joachim", 21, "CA", "jo@gmail.com");
			insertUser("Benjamin", 21, "CA", "ben@gmail.com");
			System.out.print("Users Insert passed\n");
			

			
			System.out.print("Login Insert \n");
			/*
			stmt.executeUpdate(
					"INSERT INTO Login(Username, Password, DateCreated) VALUES " +
							"('joachimlerman', 'somepassword', '2000-12-29'), " +
							"('kevin.yan', 'S0mePaSsWord1', '2000-3-12'), " +
							"('benjamin', 'hi', '2000-5-12') "
					);
			*/
			insertLogin("KevinY", "Google", "kyan", "fd", "2005-12-2");
			insertLogin("Joachim", "Olympia", "kyan", "fd", "2005-12-2");
			insertLogin("Benjamin", "localhost", "kyan", "fd", "2005-12-2");
			System.out.print("Login Insert passed \n");
		
	}

	/*
	public void showWebsite()
	{
		try {
		rs = stmt.executeQuery("SELECT DISTINCT * FROM Website");
        while (rs.next()) {
            String name = rs.getString("name");
            int websiteID = rs.getInt("WebsiteID");
            String reason = rs.getString("reason"); //Username Password
            System.out.println(name + "\t\t" + websiteID + "\t\t" + reason);
            }
        
		} catch (SQLException ex) {
	            // handle any errors
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	            }	


	}
	
	
	public void showUser()
	{
		try {
		rs = stmt.executeQuery("SELECT DISTINCT * FROM Users");
        while (rs.next()) {
            String name = rs.getString("Name");
            int userID = rs.getInt("UserID");
            int age = rs.getInt("Age");
            String state = rs.getString("State");
            String email = rs.getString("EmailAddress");
            System.out.println(userID+ "\t\t" +name + "\t\t" + age + "\t\t" + state + "\t\t" + email);
            }
      
		} catch (SQLException ex) {
	            // handle any errors
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	            }	


	}

	public void showLogin()
	{
		try {
		rs = stmt.executeQuery("SELECT DISTINCT * FROM Login");
        while (rs.next()) {
            int userID = rs.getInt("UserID");
            int websiteID = rs.getInt("WebsiteID");
            String username = rs.getString("Username");
            String password = rs.getString("Password"); //Username Password
            String dateCreated = rs.getString("DateCreated");
            System.out.println(userID + "\t\t" + websiteID + "\t\t" + username +"\t\t"+ password+"\t\t"+dateCreated);
            }
        
		} catch (SQLException ex) {
	            // handle any errors
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	            }	
	}
	
	*/
	
	public void insertLogin(String name, String website, String username, String password, String dateCreated)
	{
		int userID;
		int websiteID;
		try {
			rs = stmt.executeQuery("SELECT DISTINCT userID FROM Users WHERE name = '"+name+"'");
	        rs.next();
	        userID = rs.getInt("UserID");
	        
	        System.out.println("userID exists "+userID);
	        rs = stmt.executeQuery("SELECT DISTINCT WebsiteID FROM Website WHERE name = '"+website+"'");
	        rs.next();
	        
	        websiteID = rs.getInt("WebsiteID");
	        
	        System.out.println("websiteID exists "+websiteID);
	        stmt.executeUpdate(
					"INSERT INTO Login(UserID, WebsiteID, Username, Password, DateCreated) VALUES " +
							"("+ "'" +userID + "'" +","+ "'" + websiteID +"'" +","+ "'" + username + "'" +","+ "'" + password + "'"+ ","+ "'"+ dateCreated+"'"+")"
					);
	        //INSERT INTO Login VALUES (1, 1, 'imdabest', 'aaa', '2015-11-18')
			} catch (SQLException ex) {
		            // handle any errors
		            System.out.println("SQLException: " + ex.getMessage());
		            System.out.println("SQLState: " + ex.getSQLState());
		            System.out.println("VendorError: " + ex.getErrorCode());
		            }	
	}

	public void insertUser(String name, int age, String state, String email)
	{
		
		try {
			
			stmt.executeUpdate(
					"INSERT INTO Users(Name, Age, State, EmailAddress) VALUES " +
							"("+ "'" +name + "'" +","+ "'" + age +"'" +","+ "'" + state + "'" +","+ "'" + email + "'"+ ")"
					);
	        
	       
			} catch (SQLException ex) {
		            // handle any errors
		            System.out.println("SQLException: " + ex.getMessage());
		            System.out.println("SQLState: " + ex.getSQLState());
		            System.out.println("VendorError: " + ex.getErrorCode());
		            }	
	}
	
	public void insertWebsite(String siteName, String domainName, String IPAddress, String serverLocation, String reason)
	{
		
		try {
			
			stmt.executeUpdate(
					"INSERT INTO Website(Name, DomainName, IPAddress, Serverlocation, reason) VALUES " +
							"("+ "'" +siteName + "'" +","+ "'" + domainName +"'" +","+ "'" + IPAddress + "'" +","+ "'" + serverLocation + "'"+ ","+ "'" + reason + "'"+ ")"
					);
	        
	       
			} catch (SQLException ex) {
		            // handle any errors
		            System.out.println("SQLException: " + ex.getMessage());
		            System.out.println("SQLState: " + ex.getSQLState());
		            System.out.println("VendorError: " + ex.getErrorCode());
		            }	
	}
	
	public void deleteWebsite(String websiteName){
			try {
			
			stmt.executeUpdate(
					"DELETE FROM Website WHERE Name = " + "'" + websiteName + "'" 
			);
	        
	       
			}
			catch (SQLException ex) {
		            // handle any errors
		            System.out.println("SQLException: " + ex.getMessage());
		            System.out.println("SQLState: " + ex.getSQLState());
		            System.out.println("VendorError: " + ex.getErrorCode());
		            }	
	}
	
	public void deleteUser(String email){
		try {
		
		stmt.executeUpdate(
				"DELETE FROM Users WHERE EmailAddress = " + "'" + email + "'" 
		);
        
       
		}
		catch (SQLException ex) {
	            // handle any errors
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	    }	
	}
	
	public void deleteLogin(String email, String website){
		try {
			stmt.executeUpdate(
					// "delete from login where loginID = " + "'" + loginID + "'"
					"delete from login where loginID in( " + 
					
					"SELECT loginID FROM users,website, (select * from login) as originalLogin " +
					"where users.UserID = login.UserID and website.websiteID = login.websiteID and " +
					"website.Name = " + "'" + website + "'" + "and users.EmailAddress = " + "'" + email + "'" + ")"		
			);
		}
			
		catch (SQLException ex) {
		            // handle any errors
		            System.out.println("SQLException: " + ex.getMessage());
		            System.out.println("SQLState: " + ex.getSQLState());
		            System.out.println("VendorError: " + ex.getErrorCode());
		}	
	}
	
	
	public void execQuery(String query) throws SQLException
	{
		try {
		//rs = stmt.executeQuery("select * from States");
		rs = stmt.executeQuery(query);
        while (rs.next()) {
            String name = rs.getString("name");
            int websiteID = rs.getInt("WebsiteID");
            String reason = rs.getString("reason"); //Username Password
            System.out.println(name + "\t\t" + websiteID + "\t\t" + reason);
            }
        
		} catch (SQLException ex) {
	            // handle any errors
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	           }	
	}
}
