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
	    test.insertLogin("joachim", "Google", "username", "password", "2000-4-12");
	    test.insertUser("Andy", 22, "CA", "aphun@gmail.com");
	    test.insertWebsite("Apple", "apple.com", "1.1", "CA", "buying");
		
		WelcomeScreen welcomeScreen = new WelcomeScreen();
		//test.deleteWebsite("Apple");
		//test.deleteUser("Andy");
		//test.deleteLogin("kyan@gmail.com", "Google");
	}

}
