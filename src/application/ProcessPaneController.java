package application;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ProcessPaneController {

    @FXML
    private ListView<String> mListBox;
    

    public void updateList(String test)
    {
    	mListBox.getItems().add(test);
    }
}