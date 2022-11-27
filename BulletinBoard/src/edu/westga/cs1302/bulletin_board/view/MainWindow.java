package edu.westga.cs1302.bulletin_board.view;


import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.westga.cs1302.bulletin_board.model.EarliestFirstEventComparator;
import edu.westga.cs1302.bulletin_board.model.Event;
import edu.westga.cs1302.bulletin_board.model.Event.TypeOfEvent;
import edu.westga.cs1302.bulletin_board.model.LastestFirstEventComparator;
import edu.westga.cs1302.bulletin_board.viewmodel.BulletinBoardViewModel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * The Class MainWindow.
 * @author Jeffrey Gaines
 */
public class MainWindow {
	
	private BulletinBoardViewModel viewmodel;
	private AlertProperty alertProperty;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane guiPane;

    @FXML
    private Button addButton;

    @FXML
    private DatePicker eventDatePicker;

    @FXML
    private TextField eventDescriptionTextField;

    @FXML
    private TextField eventTitleTextField;

    @FXML
    private Button removeButton;
    
    @FXML
    private MenuItem removeListViewContextMenu;
    
    @FXML
    private MenuItem displayListViewMenu;

    @FXML
    private Button sortButton;

    @FXML
    private ComboBox<TypeOfEvent> eventTypeComboBox;

    @FXML
    private ComboBox<Comparator<Event>> listOrderComboBox;

    @FXML
    private ComboBox<TypeOfEvent> listTypeComboBox;
    
    @FXML
    private ListView<Event> eventListProperty;
    
    @FXML
    private Label titleFormatLabel;


    /**
	 * Instantiates a new main window.
	 */
	public MainWindow() {
		this.alertProperty = new AlertProperty();
		this.viewmodel = new BulletinBoardViewModel();
		
	}


    @FXML
	void initialize() {
    	
		this.bindToViewModel();
		this.setUpAddButtonUsability();
		this.setUpRemoveButtonUsability();
		this.setUpRemoveMenuItemUsability();
		this.setUpSortButtonUsability();
		this.setUpDisplayMenuItemUsability();
		this.setUpListenerForAddButton();
		this.setUpEventTypeComboBox();
		this.setUpListTypeComboBox();
		this.setUpListOrderComboBox();
		this.setUpListenerForAlerts();
		this.setUpChangeListenerListView();
		this.setUpListenerForSortButton();
		this.setUpDetailWindowMenu();
	}
    
	private void setUpListenerForAddButton() {
		this.addButton.setOnAction(event -> {
			try {
				if (!this.viewmodel.addEvent()) {
					this.alertProperty.set(AlertProperty.ERROR, "Add error", "ERROR: event was not added");
				}
			} catch (IllegalArgumentException | NullPointerException ex) {
				this.alertProperty.set(AlertProperty.ERROR, "Add error",
						"ERROR: couldn't add the event due to " + ex.getMessage());
			}
		});
	}
	
	private void setUpListenerForSortButton() {
		this.sortButton.setOnAction(event -> {
			this.viewmodel.filterByTypeAndComparator();
		});
	}
	
	private void setUpChangeListenerListView() {
		this.eventListProperty.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldEvent, newEvent) -> {
					if (newEvent != null) {
						this.eventTitleTextField.setText(newEvent.getTitle());
						this.eventDescriptionTextField.setText(newEvent.getDescription());
						this.eventDatePicker.setAccessibleText(newEvent.getDate().toString());
						this.eventTypeComboBox.setPromptText(newEvent.getType().toString());
					}
				});
	}
	
	@FXML 
	private void handleRemoveEvent() {
		try {
			if (!this.viewmodel.removeEvent()) {
				this.alertProperty.set(AlertProperty.ERROR, "Remove error", "ERROR: No such a event");
			}
		} catch (IllegalArgumentException | NullPointerException ex) {
			this.alertProperty.set(AlertProperty.ERROR, "Remove error",
					"ERROR: couldn't find the event due to " + ex.getMessage());
		}
	}
	
	private void openDetailWindow() {
		Stage newStage = new Stage();
		DetailWindow detailCodeBehind;
		Parent parent;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(edu.westga.cs1302.bulletin_board.Main.DETAIL_WINDOW));
			parent = loader.load();
			detailCodeBehind = (DetailWindow) loader.getController();
		} catch (IOException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Opening Window");
			alert.setContentText("Unable to open window: " + e.getMessage());
			alert.showAndWait();
			return;
		}
		Scene scene = new Scene(parent);
		newStage.initModality(Modality.WINDOW_MODAL);
		newStage.initOwner(((Stage) (this.guiPane.getScene().getWindow())));
		newStage.setTitle(edu.westga.cs1302.bulletin_board.Main.WINDOW_TITLE);
		newStage.setScene(scene);
		detailCodeBehind.bindToViewModel(this.viewmodel);
		newStage.show();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void setUpEventTypeComboBox() {
		this.eventTypeComboBox.getItems().add(Event.TypeOfEvent.Musical);
		this.eventTypeComboBox.getItems().add(Event.TypeOfEvent.Political);
		this.eventTypeComboBox.getItems().add(Event.TypeOfEvent.Theatrical);
	}
	
	private void setUpListTypeComboBox() {
		this.listTypeComboBox.getItems().add(Event.TypeOfEvent.Musical);
		this.listTypeComboBox.getItems().add(Event.TypeOfEvent.Political);
		this.listTypeComboBox.getItems().add(Event.TypeOfEvent.Theatrical);
		this.listTypeComboBox.getItems().add(null);
	}
	
	private void setUpListOrderComboBox() {
		this.listOrderComboBox.getItems().add(new EarliestFirstEventComparator());
		this.listOrderComboBox.getItems().add(new LastestFirstEventComparator());
		this.listOrderComboBox.getItems().add(null);
	}
    
    private void bindToViewModel() {
    	this.eventTitleTextField.textProperty().bindBidirectional(this.viewmodel.getEventTitleProperty());
    	this.eventDescriptionTextField.textProperty().bindBidirectional(this.viewmodel.getEventDescriptionProperty());
    	this.eventDatePicker.valueProperty().bindBidirectional(this.viewmodel.getEventDateProperty());
    	this.eventTypeComboBox.valueProperty().bindBidirectional(this.viewmodel.getEventTypeProperty());
    	this.eventListProperty.itemsProperty().bindBidirectional(this.viewmodel.getEventListProperty());
    	
    	this.listOrderComboBox.valueProperty().bindBidirectional(this.viewmodel.getListOrderObjectProperty());
    	this.listTypeComboBox.valueProperty().bindBidirectional(this.viewmodel.getListTypeObjectProperty());
    
    	
		this.viewmodel.getSelectedEventProperty()
				.bind(this.eventListProperty.getSelectionModel().selectedItemProperty());

    }
    
    private void showAlert(Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		Window owner = this.guiPane.getScene().getWindow();
		alert.initOwner(owner);
		if (!this.alertProperty.getTitle().isEmpty()) {
			alert.setTitle(this.alertProperty.getTitle());
		}
		alert.setHeaderText(this.alertProperty.getHeader());
		alert.setContentText(this.alertProperty.getContent());
		Optional<ButtonType> alertResult = alert.showAndWait();
		if (alertType == AlertType.CONFIRMATION && alertResult.get() == ButtonType.CANCEL) {
			this.alertProperty.setResult("cancel");
		} else {
			this.alertProperty.setResult("ok");
		}
		this.alertProperty.setType(AlertProperty.NO_ALERT);
	}
    
    private void setUpListenerForAlerts() {
    	this.alertProperty.addListener(new ChangeListener<Number>() {
    		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
    			if (newValue.intValue() == AlertProperty.ERROR) {
    				MainWindow.this.showAlert(AlertType.ERROR);
    			}
    		}
    	});
    }
    
    private void setUpAddButtonUsability() {
    	BooleanBinding addButtonCriteria = Bindings.or(
    			this.eventTitleTextField.textProperty().isEmpty(), 
    			this.eventTypeComboBox.valueProperty().isNull()
    			);
    	
    	this.addButton.disableProperty().bind(addButtonCriteria.or(this.eventDatePicker.valueProperty().isNull()));
    }
    private void setUpRemoveButtonUsability() {
    	this.removeButton.disableProperty().bind(this.eventListProperty.getSelectionModel().selectedItemProperty().isNull());
    }
    
    private void setUpRemoveMenuItemUsability() {
    	this.removeListViewContextMenu.disableProperty().bind(this.eventListProperty.getSelectionModel().selectedItemProperty().isNull());
    }
    
    private void setUpDisplayMenuItemUsability() {
    	this.displayListViewMenu.disableProperty().bind(this.eventListProperty.getSelectionModel().selectedItemProperty().isNull());
    }
    
    private void setUpSortButtonUsability() {
    	this.sortButton.disableProperty().bind(this.viewmodel.getEventListEmpty());
    }

	private void setUpDetailWindowMenu() {
		this.displayListViewMenu.setOnAction((event) -> {
			this.openDetailWindow();
		});

	}
    
    
    @FXML 
    void handleRemoveSelectedListViewItem(ActionEvent event) {
    	Event selectedEvent = this.eventListProperty.getSelectionModel().getSelectedItem();
    	if (selectedEvent != null) {
    		this.handleRemoveEvent();
    	}
    }   
   

}
