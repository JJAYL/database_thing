
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
							//"WebsiteID INTEGER NOT null auto_increment, " +
							"Website VARCHAR(32) ," +
							"DomainName VARCHAR(32), " +
							"IPAddress VARCHAR(32), " +
							"Serverlocation VARCHAR(32), " +
							"reason VARCHAR(32), " +
							"primary key(Website)" +
							")"
					);
			
			stmt.executeUpdate(
					"CREATE TABLE Users (" +
							//"UserID INTEGER NOT null auto_increment, " +
							"Name VARCHAR(32)," +
							"Age INTEGER, " +
							"State VARCHAR(32), " +
							"EmailAddress VARCHAR(32), " +
							"primary key(EmailAddress)" +
							")"
					);
		
			stmt.executeUpdate(
					"CREATE TABLE Login ( " +
							"LoginID INTEGER NOT null auto_increment, " +
							//"UserID INTEGER, " +
							//"WebsiteID INTEGER, " +
							"EmailAddress VARCHAR(32), " +
							"Website VARCHAR(32), " +
							"Username VARCHAR(32), " +
							"Password VARCHAR(32), " +
							"DateCreated DATE, " + 
							"primary key(LoginID)," +
							"CONSTRAINT fk1 FOREIGN KEY (EmailAddress) REFERENCES Users(EmailAddress) ON DELETE CASCADE ON UPDATE CASCADE, " +
							"CONSTRAINT fk2 FOREIGN KEY (Website) REFERENCES Website(Website) ON DELETE CASCADE ON UPDATE CASCADE" +						
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
			insertWebsite("Google", "Google.com", "8.8.8.8", "CA", "search engine");
			insertWebsite("localhost", "Localhost.com", "8.8.8.8", "CA", "search engine");
			insertWebsite("Olympia", "Olympia.com", "8.8.8.8", "CA", "search engine");
			
			System.out.print("Website Insert passed \n");
			
			
			System.out.print("Users Insert \n");
			insertUser("KevinY", 21, "CA", "kyan@gmail.com");
			insertUser("Joachim", 21, "CA", "jo@gmail.com");
			insertUser("Benjamin", 21, "CA", "ben@gmail.com");
			System.out.print("Users Insert passed\n");
			

			
			System.out.print("Login Insert \n");
			insertLogin("kyan@gmail.com", "Google", "kyan", "fd", "2005-12-2");
			insertLogin("jo@gmail.com", "Olympia", "kyan", "fd", "2005-12-2");
			insertLogin("ben@gmail.com", "localhost", "kyan", "fd", "2005-12-2");
			System.out.print("Login Insert passed \n");
		
	}

	
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
	
	
	public void insertLogin(String EmailAddress, String Website, String username, String password, String dateCreated)
	{
		//int userID;
		//int websiteID;
		try {
	        stmt.executeUpdate(
					"INSERT INTO Login(EmailAddress, Website, Username, Password, DateCreated) VALUES " +
							"("+ "'" +EmailAddress + "'" +","+ "'" + Website +"'" +","+ "'" + username + "'" +","+ "'" + password + "'"+ ","+ "'"+ dateCreated+"'"+")"
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
					"INSERT INTO Website(Website, DomainName, IPAddress, Serverlocation, reason) VALUES " +
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
					"DELETE FROM Website WHERE Website = " + "'" + websiteName + "'" 
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
					"delete from login "+
					"where EmailAddress = " + "'" + email + "'" + "and " + "Website = " + "'" + website + "'" 
					/*
					// "delete from login where loginID = " + "'" + loginID + "'"
					"delete from login where loginID in( " + 
					
					"SELECT loginID FROM users,website, (select * from login) as originalLogin " +
					"where users.UserID = login.UserID and website.websiteID = login.websiteID and " +
					"website.Name = " + "'" + website + "'" + "and users.EmailAddress = " + "'" + email + "'" + ")"
					*/		
			);
		}
			
		catch (SQLException ex) {
		            // handle any errors
		            System.out.println("SQLException: " + ex.getMessage());
		            System.out.println("SQLState: " + ex.getSQLState());
		            System.out.println("VendorError: " + ex.getErrorCode());
		}	
	}
	
	public void updateLogin(String currentEmail, String currentWebsite, String username, String password){
		try {
			stmt.executeUpdate(
					"UPDATE `login` SET "
					+ " Username= " + "'" + username + "'," + "Password= " + "'" + password + "'" + " WHERE EmailAddress = " + "'" + currentEmail + "'" +" and Website= " + "'" + currentWebsite + "'" 
					);
		} catch (SQLException ex) {
			// handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	public void updateUser(String currentEmail, String name, int age, String state, String email){
		try {
			stmt.executeUpdate( "UPDATE `Users` SET " +
					"`Name`= " + "'" + name + "', " +  "`Age`= " + "'" + age + "', " + "`State`= " + "'" + state + "', " + "`EmailAddress`= " + "'" + email + "'" +  "WHERE `EmailAddress`= " + "'" + currentEmail + "'"
					);
		}
		catch (SQLException ex) {
			// handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public void updateWebsite(String currentSiteName, String siteName, String domainName, String IPAddress, String serverLocation){
		try {
			stmt.executeUpdate( "UPDATE `website` SET " +
					"`Website`= " + "'" + siteName + "', " +  "`DomainName`= " + "'" + domainName + "', " + "`IPAddress`= " + "'" + IPAddress + "', " + "`Serverlocation`= " + "'" + serverLocation + "'" +  "WHERE `Website`= " + "'" + currentSiteName + "'"

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
	
	//query 1
	public String averageUserAge()
	{
		return "select Login.Website, AVG(Users.age) from Login, Users Where Users.EmailAddress = Login.EmailAddress group by Login.website";
	}
	
	//query 2
	public String averageSignupDate()
	{
		return "select Login.Website, AVG(Login.DateCreated) from Login group by Login.website ";	
	}
	
	//query 3
	public String numberOfUsers()
	{
		return "select Website, COUNT(username) from Login GROUP BY Website";
	}
	
	//query 4
	public String numberOfSites(){
		return "select Username, COUNT(Website) from Login GROUP BY Username";
	}
	
	//query 5. # Of Users Per Website Age 10-25
	public String usersPerWebsiteByAge10_25(){
		return "select  Login.Website, count(Users.EmailAddress) " +
				"from Login, Users  "+
				"Where Login.EmailAddress = Users.EmailAddress and Age >= 10 and Age <= 25 "+
				"group by Login.website ";
	}
	
	//query 6
	public String usersPerWebsiteByAge26_45(){
		return "select  Login.Website, count(Users.EmailAddress) " +
				"from Login, Users  "+
				"Where Login.EmailAddress = Users.EmailAddress and Age >= 26 and Age <= 45 "+
				"group by Login.website ";
	}
	
	//query 7
	public String usersPerWebsiteByAge46_65(){
		return "select  Login.Website, count(Users.EmailAddress) " +
				"from Login, Users  "+
				"Where Login.EmailAddress = Users.EmailAddress and Age >= 46 and Age <= 65 "+
				"group by Login.website ";
	}
	//query 8
	public String usersPerWebsiteByAge65plus(){
		return "select  Login.Website, count(Users.EmailAddress) " +
				"from Login, Users  "+
				"Where Login.EmailAddress = Users.EmailAddress and Age >= 66 "+
				"group by Login.website ";
	}
		
	
	//query 9
	public String mostCommonUsername(){
		return "select username, COUNT(Username) from Login GROUP BY Username;";
	}
	
	//10
	public String userWebSameState(){
		return "select Website.DomainName, COUNT(Users.name) "+
				"from Website, Users "+
				"where Website.Serverlocation = Users.State and Users.State = 'CA' "+
				"GROUP BY Website.DomainName ";


	}
}
