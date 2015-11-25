
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
		insertWebsite("Yahoo", "https://www.yahoo.com/", "268.14.165.249", "CA", "Search Engine");
		insertWebsite("Bing", "https://www.bing.com/", "167.54.159.567", "WA", "Search Engine");
		insertWebsite("DuckDuckGo", "https://duckduckgo.com/", "212.54.40.25", "PA", "Search Engine");
		insertWebsite("Google", "https://google.com/", "74.125.239.37", "CA", "Search Engine");
		
		insertWebsite("League of Legends", "http://na.leagueoflegends.com/", "134.26.871.149", "CA", "Gaming");
		insertWebsite("Hearthstone", "us.battle.net/hearthstone/en/", "162.24.952.654", "NY", "Gaming");
		insertWebsite("Maple Story", "maplestory.nexon.net/landing/", "234.29.762.632", "CA", "Gaming");
		insertWebsite("Dota 2", "www.dota2.com", "23.79.148.37", "WA", "Gaming");
		insertWebsite("Agar.io", "http://agar.io/", "45.83.246.43", "NY", "Gaming");
		
		insertWebsite("Quora", "https://quora.com/", "357.58.624.232", "CA","Public Forum");
		insertWebsite("Reddit", "https://reddit.com/", "146.24.489.484", "NY", "Public Forum");
		insertWebsite("9Gag", "9gag.com/", "147.26.465.653", "WA", "Public Forum");
		insertWebsite("Healthboards", "www.healthboards.com/", "325.14.168.653", "MA", "Public Forum");
		insertWebsite("StackOverflow", "www.stackoverflow.com/", "104.16.33.249", "NC", "Public Forum");
		
		insertWebsite("Facebook", "www.Facebook.com", "415.26.243.645", "CA","Social Network");
		insertWebsite("Instagram", "https://www.instagram.com/", "712.65.146.382", "CA", "Social Network");
		insertWebsite("Vine", "https://vine.co/", "178.53.342.782", "CA", "Social Network");
		insertWebsite("Twitter", "https://twitter.com/", "264.26.487.345", "CA", "Social Network");
		
		insertWebsite("Github", "www.Github.com", "843.43.146.245", "CA", "Public Repository");
		insertWebsite("Bitbucket", "www.Bitbucket.com", "142.24.645.213", "CA", "Public Repository");
		insertWebsite("GitLab", "www.Gitlab.com", "104.210.2.228", "MA", "Public Repository");
			

		
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

			
		insertLogin("bryan.nguyen@sjsu.edu", "Google", "bnguyen", "myb123", "2005-06-2");
		insertLogin("bryan.nguyen@sjsu.edu", "Facebook", "bnguyen", "myb123", "2015-10-9");
		insertLogin("bryan.nguyen@sjsu.edu", "Github", "bnguyen", "myb123", "2008-12-4");
		
		insertLogin("aphung@gmail.com", "Github", "funguy", "password", "2008-12-4");
		insertLogin("aphung@gmail.com", "League of Legends", "funguy", "password", "2011-12-20");
		insertLogin("aphung@gmail.com", "Instagram", "funguy12", "password", "2013-6-15");
		insertLogin("aphung@gmail.com", "Facebook", "Andy", "password", "2010-7-1");
		
		insertLogin("joachim@lerman.com", "Gitlab", "jlerman","elaborate", "2013-6-15");
		insertLogin("joachim@lerman.com", "Github", "jlerman","adjective1", "2013-6-15");
		insertLogin("joachim@lerman.com", "Dota 2", "jlerman","Difficult6", "2013-6-15");
		insertLogin("joachim@lerman.com", "StackOverflow", "jlerman","Injection4", "2013-6-15");
		insertLogin("joachim@lerman.com", "Instagram", "jlerman","Vanity6", "2013-6-15");

		insertLogin("kevyan@gmail.com", "Vine", "KevDaBes","GohardGoHam1", "2013-1-10");
		insertLogin("kevyan@gmail.com", "Vine", "KevDaBes","GohardGoHam1", "2000-6-12");
			
		insertLogin("dabestjackboy56@gmail.com", "Facebook", "dabestjackboy56","imdabest32", "2011-5-20");
		insertLogin("dabestjackboy56@gmail.com", "Google", "dabestjackboy56","imdabest32", "2012-6-15");
		insertLogin("dabestjackboy56@gmail.com", "Reddit", "dabestjackboy56", "imdabest32", "2013-7-10");

		insertLogin("benemail@yahoo.com", "Google", "bbakyd514", "bbakyd514", "2010-3-16");
		insertLogin("benemail@yahoo.com", "Reddit", "bbakyd514", "bbakyd514", "2013-11-29");

		insertLogin("texastran@yahoo.com", "Facebook", "texasrules", "g0texas", "2009-11-29");
		insertLogin("texastran@yahoo.com", "Dota 2", "texasnumber1", "g0texas", "2009-12-1");

		insertLogin("mikeyjames@aol.com", "Github", "michael_james", "githUBpaSs12", "2006-10-30");
		insertLogin("mikeyjames@aol.com", "Google", "michael_james_WY", "g00Gl_PaSs", "2010-12-7");
		insertLogin("mikeyjames@aol.com", "Facebook", "micky_james", "faceb00kPasS", "2009-6-13");

		insertLogin("bobbymet@gmail.com", "Facebook", "Robert Met", "youmetme", "2008-8-12");
		insertLogin("bobbymet@gmail.com", "Instagram", "bobbymeet12", "YouMetME1", "2010-4-23");
	
		insertLogin("aroos543@gmail.com", "Twitter", "@realroosevelt", "realdeal", "2014-8-20");

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
		return "select Login.Website, ROUND(AVG(Users.age),0) AS Age from Login, Users Where Users.EmailAddress = Login.EmailAddress group by Login.website order by AVG(Users.age)";
	}
	
	//query 2
	public String averageSignupDate()
	{
		return "select Login.Website, TRUNCATE(AVG(Login.DateCreated),0) AS Date from Login group by Login.website ";	
	}
	
	//query 3
	public String numberOfUsers()
	{
		return "select Website, COUNT(username) as Users from Login GROUP BY Website order by COUNT(username)";
	}
	
	//query 4
	public String numberOfSites(){
		return "select Username, COUNT(Website) as Websites from Login GROUP BY Username order by COUNT(Website)";
	}
	
	//query 5. # Of Users Per Website Age 10-25
	public String usersPerWebsiteByAge10_25(){
		return "select  Login.Website, count(Users.EmailAddress) as Users " +
				"from Login, Users  "+
				"Where Login.EmailAddress = Users.EmailAddress and Age >= 10 and Age <= 25 "+
				"group by Login.website " +
				"order by count(Users.EmailAddress)";
	}
	
	//query 6
	public String usersPerWebsiteByAge26_45(){
		return "select  Login.Website, count(Users.EmailAddress) as Users " +
				"from Login, Users  "+
				"Where Login.EmailAddress = Users.EmailAddress and Age >= 26 and Age <= 45 "+
				"group by Login.website " +
				"order by count(Users.EmailAddress)";
	}
	
	//query 7
	public String usersPerWebsiteByAge46_65(){
		return "select  Login.Website, count(Users.EmailAddress) as Users " +
				"from Login, Users  "+
				"Where Login.EmailAddress = Users.EmailAddress and Age >= 46 and Age <= 65 "+
				"group by Login.website " +
				"order by count(Users.EmailAddress)";
	}
	//query 8
	public String usersPerWebsiteByAge65plus(){
		return "select  Login.Website, count(Users.EmailAddress) as Users " +
				"from Login, Users  "+
				"Where Login.EmailAddress = Users.EmailAddress and Age >= 66 "+
				"group by Login.website " +
				"order by count(Users.EmailAddress)";
	}
		
	
	//query 9
	public String mostCommonUsername(){
		return "select username, COUNT(Username) as Users from Login GROUP BY Username order by COUNT(Username);";
	}
	
	//10
	public String userWebSameState(){
		return "select Website.Website, COUNT(Users.name) as Users "+
				"from Website, Users, Login "+
				"where Website.Serverlocation = Users.State and Website.Website = Login.Website and Users.EmailAddress = Login.EmailAddress "+
				"GROUP BY Website.DomainName " +
				"order by COUNT(Users.name)";


	}
}
