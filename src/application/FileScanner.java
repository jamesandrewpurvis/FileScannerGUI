package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

/**
 * This class contains methods used to parse our Poem.html file.
 * @author James Purvis
 * @version 1.0
 */
public class FileScanner 
{
	
	/**
	 * A LinkedHashmap that stores words in keys with their corresponding counts as values.
	 * 
	 */
	
	private Map<String, Integer> mWordDictionary = null;
	
	
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
	 * Returns our Word Dictionary, used for accessing outside of our class.
	 * @return Map
	 */
	
	public Map<String, Integer> returnWordDictionary()
	{
		return mWordDictionary;
	}
	
	/**
	 * Constructs a new FileScanner object, filters the poem for redundancy, and then parses the poem.html file.
	 * @param path
	 * The file path of the poem.html file
	 */
	public FileScanner(String path)
	{
		try
		{
			mFilePath = path;
			mReader = new BufferedReader(new FileReader(path));
			mPoem = new StringBuilder();
			String mLine = mReader.readLine();
			mWordDictionary = new LinkedHashMap<String, Integer>();
			
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
	   //PrintWords();
		
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
	 */
	
	private void SaveWords()
	{
		StringTokenizer mTokenizer = new StringTokenizer(mPoem.toString());
		
		String mWord; 
		
		while(mTokenizer.hasMoreTokens())
		{
			mWord = mTokenizer.nextToken().toLowerCase();
			
			if (mWordDictionary.containsKey(mWord) == true)
			{
				int currentCount = mWordDictionary.get(mWord);
				mWordDictionary.replace(mWord, currentCount = currentCount + 1);
			}
			else
			{
				mWordDictionary.put(mWord, 1);
			}
		}
		
	}
	
	/**
	 * This method prints the words from our Dictionary to the end-user and alerts them the process is complete.
	 */
	private void PrintWords()
	{
		Set<String> mKeys = mWordDictionary.keySet();
		
		
		mWordDictionary.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
        .limit(20)
        .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
        )).forEach((s, integer) -> MainController.mControllerInstance.updateList(String.format("The word %s occured %s times", s, integer)));
		
		new Alert(Alert.AlertType.INFORMATION, "Your file has been processed successfully!").show();
	}
	
	
	
	
}

