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
	
	protected boolean viewThreads() throws SQLException {
		Statement s = C.createStatement();
		String sql = "SELECT * FROM Thread";
		s.executeQuery(sql);
		ResultSet result = s.getResultSet();
		
		boolean noThreads = true;
		while (result.next()) {
			System.out.println("Thread ID: " + result.getString("TID") + "\n    Topic: " + result.getString("topic"));
			noThreads = false;
		}
		if (noThreads) {
			System.out.println("Nobody has created any threads. It's quiet in here...");
			return false;
		}
		else {
			return true;
		}
	}
	
	/**Allows a user to see what songs they have added to their favorites list
	 * @return true if the list of songs was not empty, false otherwise
	 * @throws SQLException
	 */
	protected boolean viewFavorites() throws SQLException {
		String favoritesId = initializeFavorites();
				
		Statement s = C.createStatement();
		String sql = "SELECT * FROM Song S WHERE S.song_id IN (SELECT J.song_id FROM Song_Song_List_Junction J WHERE song_list_id = '" + favoritesId + "')";
		s.executeQuery(sql);
		ResultSet result = s.getResultSet();
		boolean favoritesEmpty = true;
		while (result.next()) {
			System.out.println("Song title: " + result.getString("title"));
			favoritesEmpty = false;
		}
		if (favoritesEmpty) {
			System.out.println("You do not have any favorited songs.");
			return false;
		}
		else {
			return true;
		}
	}
		
	/**Allows users to add a song to their list of favorites
	 * @return
	 * @throws SQLException
	 */
	protected boolean addToFavorites() throws SQLException {
		String favoritesId = initializeFavorites();
		//System.out.println("id for your list of favorite songs: " + favoritesId);
		String songName;
		System.out.print("Enter name of the song to favorite: ");
		Scanner scan = new Scanner(System.in);
		songName = scan.nextLine();
		
		Statement s = C.createStatement();
		String sql = "SELECT song_id FROM Song WHERE title = '" + songName + "'";
		s.executeQuery(sql);
		ResultSet result = s.getResultSet();
		
		if (result.next()) {//song is found
			String songId = result.getString("song_id");
			
			sql = "SELECT * FROM Song_Song_List_Junction WHERE song_id = '" + songId + "' AND song_list_id = '" + favoritesId + "'";
			s.executeQuery(sql);
			result = s.getResultSet();
			if (result.next()) {//song already exists in user's favorited song list
				System.out.println("Song already exists in your favorites.");
				return false;
			}
			else {
				System.out.println("song id of \"" + songName + "\": " + songId);
				sql = "INSERT INTO Song_Song_List_Junction VALUES ('" + favoritesId + "', '" + songId + "')";
				s.executeUpdate(sql);
				return true;
			}
		}
		else {
			System.out.println("Song not found.");
			return false;
		}
	}
	
	/**Checks to see if the user already has a favorited songs list and creates a list if one doesn't exist for that user
	 * @throws SQLException
	 */
	private String initializeFavorites() throws SQLException {
		//System.out.println("user id: " + user_id);
		Statement s = C.createStatement();
		String sql = "SELECT * FROM Song_List WHERE user_id = '" + user_id + "'";
		s.executeQuery(sql);
		ResultSet result = s.getResultSet();
		if (result.next()) { //will return empty set if the user doesn't have a favorited song list initialized
			return result.getString("song_list_id");//returns the user's song list id if it exists
		}
		else {
			sql = "SELECT COUNT(*) FROM Song_List";
			s.executeQuery(sql);
			result = s.getResultSet();
			result.next();
			int newKeyNo = result.getInt(1) + 1;
			
			return "SL" + newKeyNo;
		}	
	}
	
	/**Allows users to create an account. Does very minimal data validation
	 * @return true if account successfully created, false otherwise
	 * @throws SQLException
	 */
	protected boolean createAccount() throws SQLException {
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
		
		Statement s = C.createStatement();
		String sql = "SELECT * FROM SB_User WHERE username = '" + username + "'";//checks to make sure username isn't already taken, ideally should
																				//be done for email and phone too, but the only way i can think of
																				//that is having a similar query for each. idk if that's the right way
		s.executeQuery(sql);
		ResultSet result = s.getResultSet();
		
		if (result != null && result.next()) {
			boolean exists = false;
			if (username.equals(result.getString("username"))) {
				System.out.println("Username already taken.");
				exists = true;
			}
//			if (email.equals(result.getString("email"))) {
//				System.out.println("Email already in use.");
//				exists = true;
//			}
//			if (phone.equals(result.getString("mobile_no"))) {
//				System.out.println("Phone number already in use.");
//				exists = true;
//			}
			
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
				int newKeyNo = result.getInt(1) + 1;
				//System.out.println(existingUser + " users already exist");
				
				//THIS ASSUMES ALL THE INFORMATION ENTERED IS VALID. CHECKING INFORMATION VALIDITY IS REQUIRED FOR THE FUTURE BUT NOT A CURRENT PRIORITY
				sql = "INSERT INTO SB_User VALUES ('U" + newKeyNo + "', '" + username + "', '" + password + "', '" + gender + "', '"
						+ email + "', '" + phone + "', '" + country + "')";
				s.executeUpdate(sql);
				return true;
			}
		}
		return false;//temp
	}
	
	/**Allows the user to log in and use the application.
	 * @return true if logged in successfully, false otherwise
	 * @throws SQLException
	 */
	protected boolean login() throws SQLException {
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
	
	/**Takes the name of a table as user input and returns the contents of that table.
	 * @throws SQLException
	 */
	protected void testQuery() throws SQLException {
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
