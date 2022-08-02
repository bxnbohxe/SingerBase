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
		String username, password, gender, email, phone, country;
		Scanner scan = new Scanner(System.in);//currently set as if the user will interact through terminal, will need to change
		System.out.print("Enter username: ");
		username = scan.nextLine();
		System.out.print("Enter password: ");
		password = scan.nextLine();
		System.out.print("Enter gender: ");
		gender = scan.nextLine();
		System.out.print("Enter email: ");
		email = scan.nextLine();
		System.out.print("Enter phone number (10 digits with area code, unformatted): ");
		phone = scan.nextLine();
		System.out.print("Enter country: ");
		country = scan.nextLine();
		
		//code to make sure username, email, phone doesn't already exist. check if country exists, if not, set to 'Not listed'
		Statement s = C.createStatement();
		String sql = "SELECT * FROM SB_User";
		s.executeQuery(sql);
		ResultSet result = s.getResultSet();
		
		if (result != null && result.next()) {
			boolean exists = false;
			if (username.equals(result.getString("username"))) {
				System.out.println("Username already taken.");
				exists = true;
			}
			if (email.equals(result.getString("email"))) {
				System.out.println("Email already in use.");
				exists = true;
			}
			if (phone.equals(result.getString("mobile_no"))) {
				System.out.println("Phone number already in use.");
				exists = true;
			}
			
			if (exists) {
				System.out.println("Failed to create account.");
				return false;
			}
			else {
				sql = "SELECT country_name FROM Country WHERE country_name = '" + country + "'";
				s.executeQuery(sql);
				result = s.getResultSet();
				if (result != null && result.next() == false) {//country not found in db, returns empty set
					System.out.println("Country not found in database, defaulting to \"Not listed\".");
					country = "Not listed";
				}
				
				sql = "SELECT COUNT(*) FROM SB_User";
				s.executeQuery(sql);
				result = s.getResultSet();
				result.next();
				int existingUser = result.getInt(1) + 1;
				//System.out.println(existingUser + " users already exist");
				
				//THIS ASSUMES ALL THE INFORMATION ENTERED IS VALID. CHECKING INFORMATION VALIDITY IS REQUIRED FOR THE FUTURE BUT NOT A CURRENT PRIORITY
				sql = "INSERT INTO SB_User VALUES ('U" + existingUser + "', '" + username + "', '" + password + "', '" + gender + "', '"
						+ email + "', '" + phone + "', '" + country + "')";
				s.executeUpdate(sql);
				return true;
			}
		}
		return false;//temp
	}
	
	public boolean login() throws SQLException {
		String username;
		String password;//currently no password system yet
		Scanner scan = new Scanner(System.in);//currently set as if the user will interact through terminal, will need to change
		System.out.print("Enter username: ");
		username = scan.nextLine();
		System.out.print("Enter password: ");
		password = scan.nextLine();
		
		Statement s = C.createStatement();
		String sql = "SELECT user_id FROM SB_User WHERE username = '" + username + "' AND password = '" + password + "'";
		s.executeQuery(sql);
		ResultSet result = s.getResultSet();

		
		if (result != null && result.next()) {
			user_id = result.getString(1);
			System.out.println("logged in");
			return true;
		}
		else {
			System.out.println("invalid username and password combination");
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
