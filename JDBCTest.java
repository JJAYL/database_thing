import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {
	public static void main(String[] args) throws SQLException {
	
		MyJDBC test = new MyJDBC();
		test.createDB();
		//System.out.println("Database successfully created. ");
		test.loadData();
		//test.showWebsite();
		//test.showLogin();
		//test.showUser();
	    
		WelcomeScreen welcomeScreen = new WelcomeScreen();
		/*
	    test.insertUser("Andy", 22, "CA", "aphun@gmail.com");
	   test.insertWebsite("Apple", "apple.com", "1.1", "CA", "buying");
	  test.insertLogin("aphun@gmail.com", "Apple", "username", "password", "2000-4-12");
	   
		
	
		test.deleteLogin("aphun@gmail.com", "Apple");
		test.deleteWebsite("Apple");
		test.deleteUser("aphun@gmail.com");
		*/
	}

}
