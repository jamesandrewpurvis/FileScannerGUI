package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.MainController;

/**
 * This class contains methods for testing the MainController class.
 * @author James Purvis
 * @version 1.0
 */
class MainControllerTest {

	/**
	 * Verifies that the FilePath doese exist, should return true.
	 */
	void verifyFileTest() 
	{
		assertEquals(MainController.verifyFile("/Users/andy/Desktop/poem.html"), true);

	}
	
	/**
	 * Verifies that the FilePath doesn't exist, should return false.
	 */
	void verifyFileInvalidTest() 
	{
		assertEquals(MainController.verifyFile("/Users/andy/desktop/Nothing.html"), false);

	}
	

}
