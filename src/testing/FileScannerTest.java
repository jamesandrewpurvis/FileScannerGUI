package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import application.FileScanner;

class FileScannerTest {

	@Test
	void theTest() {
		
		FileScanner mScanner = new FileScanner("/Users/andy/Desktop/poem.html");
		
		int mTheWordCount = mScanner.returnWordDictionary().get("the");
		
		assertEquals(57, mTheWordCount);
	}
	
	
	@Test
	void andTest()
	{
        FileScanner mScanner = new FileScanner("/Users/andy/Desktop/poem.html");
		
		int mAndWordCount = mScanner.returnWordDictionary().get("and");
		
		assertEquals(38, mAndWordCount);
	}
	
	
	@Test
	void iTest()
	{
        FileScanner mScanner = new FileScanner("/Users/andy/Desktop/poem.html");
		
		int mIWordCount = mScanner.returnWordDictionary().get("i");
		
		assertEquals(32, mIWordCount);
	}
	
	@Test
	void myTest()
	{
        FileScanner mScanner = new FileScanner("/Users/andy/Desktop/poem.html");
		
		int mMywordCount = mScanner.returnWordDictionary().get("my");
		
		assertEquals(24, mMywordCount);
	}
	
	@Test
	void ofest()
	{
        FileScanner mScanner = new FileScanner("/Users/andy/Desktop/poem.html");
		
		int mOfWordCount = mScanner.returnWordDictionary().get("of");
		
		assertEquals(22, mOfWordCount);
	}
	
}
