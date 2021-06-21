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

public class FileScanner 
{
	private Map<String, Integer> mWordDictionary = null;
	private BufferedReader mReader = null;
	private boolean mPoemStart = false;
	private StringBuilder mPoem = null;
	private String mFilePath = null;
	
	public Map<String, Integer> returnWordDictionary()
	{
		return mWordDictionary;
	}
	
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
	
	private void StorePoem(String line)
	{
		if (mPoemStart == true && line.isBlank() != true)
		{
			mPoem.append(line);
			mPoem.append(System.lineSeparator());
		}
	}
	
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

