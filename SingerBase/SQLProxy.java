import java.sql.*;
import java.util.*;

public class SQLProxy
{
	private Connection C;
	private String user_id;
	
	public SQLProxy(Connection C) {
		this.C = C;
		user_id = null;
	}
	
	public boolean createAccount() throws SQLException {
		String username, gender, email, phone, country;
		Scanner scan = new Scanner(System.in);//currently set as if the user will interact through terminal, will need to change
		System.out.print("Enter username: ");
		username = scan.nextLine();
		System.out.print("Enter gender: ");
		gender = scan.nextLine();
		System.out.print("Enter email: ");
		email = scan.nextLine();
		System.out.print("Enter phone number: ");
		phone = scan.nextLine();
		System.out.print("Enter country: ");
		country = scan.nextLine();
		
		//code to make sure username, email, phone doesn't already exist. check if country exists, if not, set to 'Not listed'
		
		return true;//temp
	}
	
	public boolean login() throws SQLException {
		String username;
//		String password;//currently no password system yet
		Scanner scan = new Scanner(System.in);//currently set as if the user will interact through terminal, will need to change
		System.out.print("Enter username: ");
		username = scan.nextLine();
//		System.out.print("Enter password: ");
//		password = scan.nextLine();
		
		Statement s = C.createStatement();
		String sql = "SELECT user_id FROM SB_User WHERE username = '" + username + "'";
		s.executeQuery(sql);

		ResultSet result = s.getResultSet();
		ResultSetMetaData meta = result.getMetaData();
		
		System.out.println(meta.getColumnCount());
		System.out.println("getting user info");
		
		if (result != null && result.next()) {
			user_id = result.getString(1);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void testQuery() throws SQLException {
		System.out.println("testing");
		String table;
		Scanner scan = new Scanner(System.in);//currently set as if the user will interact through terminal, will need to change
		table = scan.nextLine();
		
		Statement s = C.createStatement();
		String sql="SELECT * FROM " + table;
		s.executeQuery(sql);
//		System.out.println(s.execute(sql));
		
		ResultSet result = s.getResultSet();
		ResultSetMetaData meta = result.getMetaData();
		
		System.out.println(meta.getColumnCount());
		System.out.println("getting user info");
		if (result != null) {
			System.out.println("result is not null");
			while(result.next()) {	
				System.out.println("\n" + result.getString(1) + "\t" + result.getString(2));
			}
		}
	}
}
