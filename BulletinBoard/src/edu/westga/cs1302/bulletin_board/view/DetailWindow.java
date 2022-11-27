package edu.westga.cs1302.bulletin_board.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs1302.bulletin_board.viewmodel.BulletinBoardViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * The Class DetailWindow.
 * @author Jeffrey Gaines
 */
public class DetailWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea displayEventTextArea;
    

    /**
     * Bind to view model.
     *
     * @param vm the vm
     */
    public void bindToViewModel(BulletinBoardViewModel vm) {
    	String summary = vm.getSelectedEventProperty().getValue().getEventSummary();
    	this.displayEventTextArea.setText(summary);
    	this.displayEventTextArea.setEditable(false);
    }
    
    
}

