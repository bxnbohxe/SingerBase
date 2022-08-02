import java.sql.*;
import java.util.*;

public class Driver
{
	public static void main(String[] args) {
		final String DB_URL = "jdbc:oracle:thin:@//localhost:1522/XE";
		final String USERNAME = "system";
		final String PASSWORD = "oracle";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception E) {
			System.err.println("Unable to load driver.");
			E.printStackTrace();
		}
		
		try {
//			Connection C = DriverManager.getConnection("jdbc:mysql://test.sjsu.edu:3307/testDB", "system", "oracle"); //?user=root&password=xyz");
			Connection C = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); //?user=root&password=xyz");
			System.out.println("Connection established");
			
			SQLProxy proxy = new SQLProxy(C);
			
			String selection;
			Scanner scan = new Scanner(System.in);//currently set as if the user will interact through terminal, will need to change
			System.out.println("what would you like to do?");
			selection = scan.nextLine();
			
			while (!selection.equals("exit")) {
				switch (selection) {
				case "create account":
					System.out.println("creating account");
					if (proxy.createAccount()) {
						
					}
					break;
				case "login":
					System.out.println("logging in");
					if (proxy.login()) {
						loggedIn(proxy);
					}
					break;
				case "exit":
					System.out.println("exiting");
					C.close();
					break;
				case "test":
					proxy.testQuery();
					break;
				default:
					break;
				}
				
				System.out.println("what would you like to do?");
				selection = scan.nextLine();
			}
		}
		catch (Exception E) {
			System.err.println("Error connecting: " + E);
			E.printStackTrace();
		}
		System.out.println("-----------------------------------END OF PROGRAM---------------------------------------");
	}
	
	static void loggedIn(SQLProxy proxy) {
		try {
			proxy.addToFavorites();
		}
		catch (Exception E) {
			System.err.println(E);
			E.printStackTrace();
		}
	}
}
