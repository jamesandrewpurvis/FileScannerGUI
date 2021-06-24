package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import application.FileScanner;

/**
 * This class contains logic for testing our FileScanner
 * @author James Purvis
 * @version 1.0
 * Date: June 24, 2021
 *
 */
class FileScannerTest {
	
	

	/**
	 * Tests for occurrence of the word "the" to ensure the count is correct.
	 */
	
	void theTest() {
		
		FileScanner mScanner = new FileScanner("/Users/andy/Desktop/poem.html");
		
		int mTheWordCount = mScanner.returnWordDictionary().get("the");
		
		assertEquals(57, mTheWordCount);
	}
	
	
	/**
	 * Tests for the occurrence of the word "and" to ensure the count is correct.
	 */
	
	void andTest()
	{
        FileScanner mScanner = new FileScanner("/Users/andy/Desktop/poem.html");
		
		int mAndWordCount = mScanner.returnWordDictionary().get("and");
		
		assertEquals(38, mAndWordCount);
	}
	
	
	/**
	 * Tests for the occurrence of the word "i" to ensure the count is correct.
	 */
	void iTest()
	{
        FileScanner mScanner = new FileScanner("/Users/andy/Desktop/poem.html");
		
		int mIWordCount = mScanner.returnWordDictionary().get("i");
		
		assertEquals(32, mIWordCount);
	}
	
	/**
	 * Tests for the occurrence of the word "my" to ensure the count is correct.
	 */
	void myTest()
	{
        FileScanner mScanner = new FileScanner("/Users/andy/Desktop/poem.html");
		
		int mMywordCount = mScanner.returnWordDictionary().get("my");
		
		assertEquals(24, mMywordCount);
	}
	
	/**
	 * Tests for the occurrence of the word "of" to ensure the count is correct.
	 */
	void ofest()
	{
        FileScanner mScanner = new FileScanner("/Users/andy/Desktop/poem.html");
		
		int mOfWordCount = mScanner.returnWordDictionary().get("of");
		
		assertEquals(22, mOfWordCount);
	}
	
}
