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

public class MainController {

    @FXML
    private TextField mProcessField;

    @FXML
    private Button mProcessButton;
    
    public static ProcessPaneController mControllerInstance = null;

    @FXML
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
    
    public boolean verifyFile(String mFilePath)
    {
    	try {
			BufferedReader mReader = new BufferedReader(new FileReader(mFilePath));
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
    }
 
    

}