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
			System.out.println("1) create account\n2) log in\n3) exit");
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
				case "LOG IN":
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
					System.exit(0);
					break;
				case "TEST":
					proxy.testQuery();
					break;
				default:
					System.out.println("Invalid selection");
					break;
				}
				
				System.out.println("What would you like to do?");
				System.out.println("1) create account\n2) log in\n3) exit");
				selection = scan.nextLine();
			}
		}
		catch (Exception E) {
			System.err.println("Error connecting: " + E);
			E.printStackTrace();
		}
		System.out.println("-----------------------------------UNEXPECTED END OF PROGRAM (---------------------------------------");
	}
	
	static void loggedIn(SQLProxy proxy) {	
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Select an option:\n1) view favorites\n2) add song to favorites\n3) discussions\n4) log out");
			String option = scan.nextLine();
			
			switch (option.toUpperCase()) {
			case "1":
			case "VIEW FAVORITES":
				try {
					proxy.viewFavorites();
				}
				catch (Exception E) {
					System.err.println(E);
					E.printStackTrace();
				}
				break;
			case "2":
			case "ADD SONG TO FAVORITES":
				try {
					proxy.addToFavorites();
				}
				catch (Exception E) {
					System.err.println(E);
					E.printStackTrace();
				}
				break;
			case "3":
			case "DISCUSSIONS":
				//scan.close();//why does this cause an issue in the discussions function? why can i not close the scanner and reinitialize it?
				discussions(proxy);
				break;
			case "4":
			case "LOG OUT":
				return;
			default:
				System.out.println("Invalid selection");
				break;
			}
		}		
	}
	
	static void discussions(SQLProxy proxy) {
		
		while (true) {
			Scanner scan = new Scanner(System.in);	
			System.out.println("Select an option:\n1) view threads\n2) post to thread\n3) go back");
			String option = scan.nextLine();
			
			switch (option.toUpperCase()) {
			case "1":
			case "VIEW THREADS":
				try {
					proxy.viewThreads();
				}
				catch (Exception E) {
					System.err.println(E);
					E.printStackTrace();
				}
				break;
			case "2":
			case "POST TO THREAD":
				break;
			case "3":
			case "GO BACK":
				return;
			default:
				System.out.println("Invalid selection");
				break;
			}
		}
	}
}
