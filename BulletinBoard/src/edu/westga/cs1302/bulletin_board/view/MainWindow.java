package edu.westga.cs1302.bulletin_board.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * The Class MainWindow.
 * @author Jeffrey Gaines
 */
public class MainWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker eventDatePicker;

    @FXML
    private TextField eventDescriptionTextField;

    @FXML
    private TextField eventTitleTextField;

    @FXML
    private ComboBox<?> eventTypeComboBox;

    @FXML
    private ComboBox<?> listOrderComboBox;

    @FXML
    private ComboBox<?> listTypeComboBox;

    

}
