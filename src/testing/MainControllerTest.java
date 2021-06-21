package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.MainController;

class MainControllerTest {

	@Test
	void verifyFileTest() 
	{
		assertEquals(MainController.verifyFile("/Users/andy/Desktop/poem.html"), true);

	}
	
	@Test
	void verifyFileInvalidTest() 
	{
		assertEquals(MainController.verifyFile("/Users/andy/desktop/Nothing.html"), false);

	}
	

}
