package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Static class that exists as an entry point for our application.
 * @author James Purvis
 * @version 1.0
 *
 */

public class Main extends Application {
	/**
	 * @param primaryStage
	 * This method helps us setup the "stage" for our application, so that we can show the GUI to end-user.
	 */
	
	
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("FileScanner GUI");
			Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is our application's entry point.
	 * @param args Arguments used for intialization of our application.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
