
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
		
			///website categories Search engine, Gaming, Public Forum, Public Repository, Social Network
			System.out.print("Website Insert \n");
			insertWebsite("Gmail", "Gmail.com", "156.39.127.199", "CA", "Search Engine");
			insertWebsite("localhost", "Localhost.com", "261.38.561.211", "CA", "Search Engine");
			insertWebsite("Olympia", "Olympia.com", "122.23.244.168", "CA", "Search Engine");
			
			//newly added
			insertWebsite("Mozilla", "Mozilla.com","144.84.326.147", "CA", "Search Engine");
			insertWebsite("Yahoo", "Yahoo.com", "268.14.165.249", "CA", "Search Engine");
			insertWebsite("Bing", "Bing.com", "167.54.159.567", "CA", "Search Engine");
			
			insertWebsite("League of Legends", "Leagueoflegends.com", "134.26.871.149", "CA", "Gaming");
			insertWebsite("Hearthstone", "http://us.battle.net/hearthstone/en/", "162.24.952.654", "CA", "Gaming");
			insertWebsite("Maple Story", "http://maplestory.nexon.net/landing/", "234.29.762.632", "CA", "Gaming");
			
			insertWebsite("Quora", "https://www.quora.com/", "357.58.624.232", "CA","Public Forum");
			insertWebsite("Reddit", "Reddit.com", "146.24.489.484", "CA", "Public Forum");
			insertWebsite("9Gag", "http://9gag.com/", "147.26.465.653", "CA", "Public Forum");
			insertWebsite("Healthboards", "http://www.healthboards.com/", "325.14.168.653", "CA", "Public Forum");
			
			insertWebsite("Facebook", "Facebook.com", "415.26.243.645", "CA","Social Network");
			insertWebsite("Instagram", "https://www.instagram.com/", "712.65.146.382", "CA", "Social Network");
			insertWebsite("Vine", "https://vine.co/", "178.53.342.782", "CA", "Social Network");
			insertWebsite("Twitter", "https://twitter.com/", "264.26.487.345", "CA", "Social Network");
			
			insertWebsite("Github", "Github.com", "843.43.146.245", "CA", "Public Repository");
			insertWebsite("Bitbucket", "Bitbucket.com", "142.24.645.213", "CA", "Public Repository");
			
			
			System.out.print("Website Insert passed \n");
			
			insertUser("Bryan Nguyen", 21, "CA", "bryan.nguyen@sjsu.edu");
			insertUser("Andy Phung", 13, "AZ", "aphung@gmail.com");
			insertUser("Joachim Lerman", 19, "NY", "joachim@lerman.com");
			insertUser("Kevin Yan", 24, "CO", "kevyan@gmail.com");
			insertUser("Jackon Torrents", 17, "MA", "dabestjackboy56@gmail.com");
			insertUser("Benjamin Lee", 20, "NY", "benemail@yahoo.com");
			insertUser("Alex Tran", 13, "TX", "texastran@yahoo.com");
			insertUser("Michael James", 60, "WY", "mikeyjames@aol.com");
			insertUser("Robert Almet", 9, "IL", "bobbymet@gmail.com");
			insertUser("Alfred Roosvelt", 61, "KS", "aroos543@gmail.com");
			insertUser("Joshua Malter", 32, "AL", "work.josh.malter@gmail.com");
			insertUser("Clark Washington", 58, "PA", "clarkwashington@gmail.com");
			insertUser("Jordan Smith", 14, "CA", "ellohello123@gmail.com");
			insertUser("Zoey Karrigan", 63, "MA", "sc.karrigan@zerg.com");
			insertUser("Calvin Broadus jr", 44, "CA", "herecomestheking@gmail.com");
			insertUser("I. Jones", 50, "IN", "indyjones@gmail.com");
			insertUser("Robert Sponge", 5, "CA", "bobbysquared@yahoo.com");
			insertUser("Andre Young", 50, "CA", "rhymeswithpoop@aftermath.com");
			insertUser("Greg Aizik", 36, "NY", "g.aizik@aol.com");
			insertUser("Ron Tran", 45, "FL", "gohardorgohomw1@gmail.com");

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
