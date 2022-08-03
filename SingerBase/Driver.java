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
			System.out.println("What would you like to do?");
			System.out.println("1) create account\n2) login\n3) exit");
			selection = scan.nextLine();
			
			while (!selection.equals("exit")) {
				switch (selection.toUpperCase()) {
				case "1":
				case "CREATE ACCOUNT":
					System.out.println("creating account");
					if (proxy.createAccount()) {
						
					}
					break;
				case "2":
				case "LOGIN":
					System.out.println("logging in");
					if (proxy.login()) {
						
						loggedIn(proxy);
					}
					break;
				case "3":
				case "EXIT":
					System.out.println("exiting");
					scan.close();
					C.close();
					break;
				case "TEST":
					proxy.testQuery();
					break;
				default:
					System.out.println("Invalid selection");
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
		System.out.println("Select an option:\n1) add song to favorites\n2) view favorites");
		Scanner scan = new Scanner(System.in);
		String option = scan.nextLine();
		switch (option.toUpperCase()) {
		case "1":
		case "ADD SONG TO FAVORITES":
			try {
				proxy.addToFavorites();
			}
			catch (Exception E) {
				System.err.println(E);
				E.printStackTrace();
			}
			break;
		case "2":
		case "view favorites":
			try {
				proxy.viewFavorites();
			}
			catch (Exception E) {
				System.err.println(E);
				E.printStackTrace();
			}
			break;
		default:
			System.out.println("Invalid selection");
			break;
		}	
	}
}
