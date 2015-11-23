import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {
	public static void main(String[] args) throws SQLException {
	
		MyJDBC test = new MyJDBC();
		//test.createDB();
		test.loadData();
		test.averageUserAge();
		
		
		test.updateLogin("jo@gmail.com", "Olympia", "jo11", "12666");
		test.updateWebsite("Google", "Pandora", "Pandora.com", "21", "BK");
		test.updateUser("kyan@gmail.com", "Olympus", 22, "NV", "brolly@gmail.com");
		WelcomeScreen welcomeScreen = new WelcomeScreen();
		
	}

}
