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
               DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtest", "root", "");
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
							"Name VARCHAR(32) ," +
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
							"EmailAddress VARCHAR(32), " +
							"primary key(UserID)" +
							")"
					);
		
			stmt.executeUpdate(
					"CREATE TABLE Login ( " +
							"UserID INTEGER, " +
							"WebsiteID INTEGER, " +
							"Username VARCHAR(32), " +
							"Password VARCHAR(32), " +
							"DateCreated DATE, " + 
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
		try {
			
			System.out.print("Website Insert \n");
			stmt.executeUpdate(
					"INSERT INTO Website (Name, DomainName, IPAddress, ServerLocation, reason) VALUES " +
							"('Google', 'google.com', '8.8.8.8', 'CA', 'search engine'), " +
							"('localhost', 'localhost/', '127.0.0.1', 'this computer', 'personal website'), " +
							"('Olympia', 'olympia.com', '123.465.789', 'Olympus', 'dating'); "
					);
			System.out.print("Website Insert passed \n");
			
			
			
			
			System.out.print("Login Insert \n");
			stmt.executeUpdate(
					"INSERT INTO Login(Username, Password, DateCreated) VALUES " +
							"('joachimlerman', 'somepassword', '2000-12-29'), " +
							"('kevin.yan', 'S0mePaSsWord1', '2000-3-12'), " +
							"('benjamin', 'hi', '2000-5-12') "
					);
			System.out.print("Login Insert passed \n");
			
			
			System.out.print("Users Insert \n");
			stmt.executeUpdate(
	    			"INSERT INTO Users(Name, Age, State, EmailAddress) VALUES " +
	    				"('KevinY', 19, 'California', 'joachimlerman@gmail.com'), " +
	    	           	"('Joachim', 19, 'California', 'kevinyan@gmail.com'), " +
	    	           	"('Benjamin', 19, 'California', 'benjamin.hi@gmail.com')"
	            	);
			System.out.print("Users Insert passed\n");
			
			
 	     } catch (SQLException ex) {
 	            // handle any errors
 	            System.out.println("SQLException: " + ex.getMessage());
 	            System.out.println("SQLState: " + ex.getSQLState());
 	            System.out.println("VendorError: " + ex.getErrorCode());
 	            }	

		
	}

	public void execQuery(String query) throws SQLException
	{
		//rs = stmt.executeQuery("select * from States");
		rs = stmt.executeQuery(query);
        while (rs.next()) {
            String stateName = rs.getString("name");
            int population = rs.getInt("population");
            String capital = rs.getString("capital");
            System.out.println(stateName + "\t" + capital + "\t" + population);
            }

	}
}
