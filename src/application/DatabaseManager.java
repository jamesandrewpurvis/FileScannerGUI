package application;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.control.Alert;


/**
 * This class contains an instance of a databaseManager and allows us to connect to a mySQL database using JDBC.
 * @author James Purvis
 * @version 1.0
 */


public class DatabaseManager {
	
	/**
	 * Contains the IP address for our mySQL server.
	 */
	private String mIP = null;
	
	/**
	 * Contains the Port for our mySQL server.
	 */
	private int mPort = 0;
	
	/**
	 * Contains the connection string for our mySQL server
	 */
	private String mConnectionString = null;
	
	/**
	 * Contains the actual connection for our mySQL server.
	 */
	private Connection mConnection = null;
	/**
	 * Contains the userName for our mySQL server.
	 */
	private String mUsername = null;
	
	/**
	 * Contains the Password for our mySQL server.
	 */
	private String mPassword = null;

	/**
	 * Used to return the IP address for our mySQL server.
	 * @return string
	 */
	public String getIP() {
		return mIP;
	}

	/**
	 * Used to return the port for our mySQL server.
	 * @return int
	 */
	public int getPort() {
		return mPort;
	}
	
	/**
	 * Used to reutrn the connection string for our mySQL server.
	 * @return string
	 */

	public String getConnectionString() {
		return mConnectionString;
	}

	/**
	 * Used to return the userName for our mySQL server.
	 * @return string
	 */
	public String getUsername() {
		return mUsername;
	}

	/**
	 * Used to return the password for our mySQL server.
	 * @return string
	 */
	public String getPassowrd() {
		return mPassword;
	}

	/**
	 * Class constructor, used to create a new instance of our databaseManager
	 * @param IP
	 * @param port
	 * @param username
	 * @param password
	 * @throws ClassNotFoundException
	 */
	public DatabaseManager(String IP, int port, String username, String password) throws ClassNotFoundException {
		//a little bit of input checking
		if (IP.isBlank() == false && username.isBlank() == false && password.isBlank() == false && port > 0) {
			this.mIP = IP;
			this.mPort = port;
			this.mUsername = username;
			this.mPassword = password;
		}

		//our connection string
		mConnectionString = "jdbc:mysql://" + mIP + ":" + mPort + "/word_occurences?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

		//this method returns a connection object for us to use 
		mConnection = createDatabaseConnection();

	}

	/**
	 * Returns a fresh mySQL connection for us to use.
	 * @return connection
	 */
	private Connection createDatabaseConnection() {
		//defining our connection var
		Connection testConnection = null;

		//error handling
		try {
			//use the driveManager get connection method to grab a connection using our connection string.
			testConnection = DriverManager.getConnection(mConnectionString, mUsername, mPassword);
			return testConnection; //return our connection and let the user know!
		} catch (SQLException e) //darn! an error occurred let the user know!
		{
			System.out.println(e.getMessage());
		}

		//return null, we couldn't get a connection
		System.out.println("Unable to connect to mySQL[" + mConnectionString + "]");
		return null;
	}

/**
 * Used to create a new mySQL databased
 * @param query 
 */
	public void createDatabase(String query) {
		//error handling
		try {
			//our statement variable, we use this and our connection to create a statement
			Statement mStatement = mConnection.createStatement();
			mStatement.executeUpdate(query);
			//lets execute our query

			//it worked! let the user know
			System.out.println("A new databased named the_world has been created!");
		}
		//darn a error!
		catch (SQLException e) {
			//print the error
			System.out.println(e.getMessage());
		}
	}

	/**
	 * used to create a new mySQL table
	 * @param query
	 */
	public void createTable(String query) {
		//error handling
		try {
			//use our connection and statement var to create a statement
			Statement mStatement = mConnection.createStatement();
			//execute our statement using our query.
			mStatement.executeUpdate(query);

			//it worked! let the user know!
			System.out.println("A new table named player_scores has been created!");
		} catch (SQLException e) //darn a error!
		{
			//print the error!
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Used to run a select query on a database, returns a result set.
	 * @param query
	 * @return resultset
	 */
	public ResultSet SELECT(String query) {
		try {
			Statement mStatement = mConnection.createStatement();

			ResultSet mResult = mStatement.executeQuery(query);

			return mResult;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	/**
	 * Allows us to run a update query on a database
	 * @param query
	 */
	public void UPDATE(String query) {
		try {
			Statement mStatement = mConnection.createStatement();

			mStatement.executeUpdate(query);


		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Allows us to run a insert query on the database
	 */
	public void INSERT(String query) {
		try {
			Statement mStatement = mConnection.createStatement();

			mStatement.executeUpdate(query);


		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Allows us to run a deletequery on the database
	 * @param query
	 */
	public void DELETE(String query) {
		try {
			Statement mStatement = mConnection.createStatement();

			mStatement.executeUpdate(query);

			System.out.println(query);


		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Allows us to check if a row inside of a table exists. 
	 * @param query
	 * @return boolean
	 */
	public boolean EXISTS(String query)
	{
		try
		{
			ResultSet mResult = SELECT(query);
			
			if (mResult.next() == true)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	/**
	 * Allows us to return an integer from a query inside the database easily without running through a resultset.
	 * @param query
	 * @return int
	 */
	
	public int SELECT_INT(String query) {
		try {
			Statement mStatement = mConnection.createStatement();

			ResultSet mResult = mStatement.executeQuery(query);

			if (mResult.next())
			{
				int mSelect = mResult.getInt(1);
				return mSelect;
			}
			
			return 0;
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return 0;
		}
	}
	
	/**
	 * allows us to update word counts easily within the database.
	 * @param word
	 */
	public void UPDATE_WORDS(String word)
	{
		int mCount = SELECT_INT("SELECT word_count FROM word WHERE word_name = '" + word + "'");
		mCount++;
		UPDATE("UPDATE word SET word_count = '" + mCount + "' WHERE word_name = '" + word + "'");
		new Alert(Alert.AlertType.INFORMATION, "The word " + " " + word + " " + "has been inserted successfully into the database with a value of " + mCount).show();
	}

}