package application;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ProcessPaneController {

    @FXML
    private ListView<String> mListBox;
    

    public void updateList(String item)
    {
    	mListBox.getItems().add(item);
    }
}