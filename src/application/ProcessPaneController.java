package application;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
/**
 * Contains logic for the ProcessPaneController (The second GUI seen in our application).
 * @author James Purvis
 * @version 1.0
 */

public class ProcessPaneController {

    /**
     * This member contains our ListView control which contains the words and how many times they occurred.
     */
    private ListView<String> mListBox;
    
    /**
     * This method allows us to update our ListView and add items.
     * @param item
     * The item that is being added to the ListView control.
     */

    public void updateList(String item)
    {
    	mListBox.getItems().add(item);
    }
}