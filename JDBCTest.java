import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {
	public static void main(String[] args) throws SQLException {
	
		MyJDBC test = new MyJDBC();
		//test.createDB();
		//System.out.println("Database successfully created. ");
		test.loadData();
	}

}