package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.control.Alert;
import javafx.scene.shape.Line;

/**
 * This class contains methods used to parse our Poem.html file.
 * @author James Purvis
 * @version 1.0
 */
public class FileScanner 
{
	
	
	/**
	 * Contains our BufferedReader, allows us to read the Poem.html file
	 */
	private BufferedReader mReader = null;
	
	/**
	 * Boolean that let's us know when the poem starts in our poem.html file.
	 */
	private boolean mPoemStart = false;
	
	/**
	 * StringBuilder that contains the lines of our poem, used for parsing the file.
	 */
	
	private StringBuilder mPoem = null;
	
	/**
	 * Contains the filePath of the Poem.html file.
	 */
	private String mFilePath = null;
	
	
	/**
	 * Returns a instance of our JDBC databaseManager
	 */
	private DatabaseManager mDatabaseManager = null;
	
	/**
	 * Constructs a new FileScanner object, filters the poem for redundancy, and then parses the poem.html file.
	 * @param path
	 * The file path of the poem.html file
	 */
	public FileScanner(String path)
	{
		try
		{
			mDatabaseManager = new DatabaseManager("localhost", 3306, "root", "Newnew99");
			mFilePath = path;
			mReader = new BufferedReader(new FileReader(path));
			mPoem = new StringBuilder();
			String mLine = mReader.readLine();
			
			while(mLine != null)
			{
				mLine = mLine.replaceAll("\\<.*?>","");
				mLine = mLine.replaceAll("[?,!;'.]", "");
				mLine = mLine.replaceAll("\"", "");
				mLine = mLine.replace("&mdash", " ");
				mLine = mLine.trim();
				
				if (mLine.equals("The Raven"))
				{
					mPoemStart = true;
					
				}
				
				if (mLine.contains("*** END"))
				{
					mPoemStart = false;
				}
				
				   StorePoem(mLine);
				
					mLine = mReader.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		SaveWords();
	   PrintWords();
		
	}
	
	/**
	 * Used to store the lines of our poem, so we can use them later.
	 * @param line
	 * The individual line of the poem.
	 */
	private void StorePoem(String line)
	{
		if (mPoemStart == true && line.isBlank() != true)
		{
			mPoem.append(line);
			mPoem.append(System.lineSeparator());
		}
	}
	
	/**
	 * This method uses a Tokenizer to parse through the poem, the tokenizer allows us to get every word. Words are entered into a dictionary.
	 * @throws SQLException 
	 */
	
	private void SaveWords() 
	{
		StringTokenizer mTokenizer = new StringTokenizer(mPoem.toString());
		
		String mWord; 
		
		while(mTokenizer.hasMoreTokens())
		{
			mWord = mTokenizer.nextToken().toLowerCase();
			mWord = mWord.replaceAll("“", "");
			mWord = mWord.replaceAll("‘", "");
			mWord = mWord.replaceAll("’", "");
			mWord = mWord.replaceAll("”", "");
			
			if (mDatabaseManager.EXISTS("SELECT word_name FROM word WHERE word_name = '" + mWord + "'") == false)
			{
				mDatabaseManager.INSERT("INSERT INTO word(word_name, word_count) " + " VALUES('" + mWord+ "'," + "'" + "1" + "')");
			}
			else
			{
				int mCount = mDatabaseManager.SELECT_INT("SELECT word_count FROM word WHERE word_name = '" + mWord + "'");
				mCount++;
				mDatabaseManager.UPDATE("UPDATE word SET word_count = '" + mCount + "' WHERE word_name = '" + mWord + "'");
			}
		}
		
	}
	
	/**
	 * This method prints the words from our Dictionary to the end-user and alerts them the process is complete.
	 */
	private void PrintWords()
	{
		
		ResultSet mResult = mDatabaseManager.SELECT("SELECT word_name, word_count FROM word ORDER BY word_count DESC LIMIT 20");
		
		try {
			while(mResult.next())
			{
				MainController.mControllerInstance.updateList("The word " + mResult.getString("word_name") + " " + "occurred " + mResult.getInt("word_count") + " times");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Alert(Alert.AlertType.INFORMATION, "Your file has been processed successfully!").show();
		
		mDatabaseManager.UPDATE_WORDS("the");
		mDatabaseManager.UPDATE_WORDS("test");
		mDatabaseManager.UPDATE_WORDS("this");
		mDatabaseManager.UPDATE_WORDS("a");
	
		ResultSet mTable = mDatabaseManager.SELECT("SELECT * FROM word");
		StringBuilder mFullTableToString = new StringBuilder();
		
		try {
			while(mTable.next())
			{
				int mCount = mTable.getInt("word_count");
				String mWord = mTable.getString("word_name");
				mFullTableToString.append("WORD: " + mWord + " " + "COUNT: " + mCount + " ");
				mFullTableToString.append(System.lineSeparator());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("##############");
		System.out.println("SELECT QUERY");
		System.out.println("##############");
		System.out.println("\n");
	    System.out.println(mFullTableToString.toString());
	}
	
}

