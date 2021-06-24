package application;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class contains logic for our MainController. The MainController is the first GUI seen when the application is launched.
 * @author James Purvis
 * @version 1.0
 */
public class MainController {

	
   /**
    * This member is the TextField that contains the file-path to the Poem.html file.
    */
    private TextField mProcessField;
    
    
    /**
     * This member contains the button used to process the Poem.html file.
     */
    
    private Button mProcessButton;
    
    
    /**
     * This member contains an instance of our ProcessPaneController. (The second GUI shown)
     */
    
    public static ProcessPaneController mControllerInstance = null;

    /**
     * This method is where our ActionEvent button press takes place. Our method grabs the filePath that the user provided, and then verifies if it's valid. If so, the application continues.
     * @param event 
     * @throws IOException 
     */
    void buttonPress(ActionEvent event) throws IOException {
    	
    	String mFilePath = mProcessField.getText();
    	
    	if (verifyFile(mFilePath) == false)
    	{
    		new Alert(Alert.AlertType.ERROR, "Please enter a valid file path! FileNotFound").show();
    		return;
    	}
    
    	FXMLLoader mLoader = new FXMLLoader(getClass().getResource("/ProcessFilePane.fxml"));
    	
    	Parent mProcessXML = mLoader.load();
    	
    	mControllerInstance = (ProcessPaneController)mLoader.getController();
    	
    	Scene mProcessScene = new Scene(mProcessXML);
    	
    	
    	Stage mWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	mWindow.setScene(mProcessScene);
    	
    	mWindow.show();
    	
    	FileScanner mScanner = new FileScanner(mFilePath);
    	
    	
    	
    }
    
    /**
     * This method verifies if a file path is valid or not. 
     * @param mFilePath
     * The specified FilePath
     * @return boolean
     */
    public static boolean verifyFile(String mFilePath)
    {
    	try {
			BufferedReader mReader = new BufferedReader(new FileReader(mFilePath));
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
    }
 
    

}