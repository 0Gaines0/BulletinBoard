package edu.westga.cs1302.bulletin_board.view;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

/**
 * The Class AlertProperty.
 * 
 * @author CS 1302
 * @version Fall 2022
 */
public class AlertProperty {
	private String title;
	private String header;
	private String content;
	private IntegerProperty typeProperty;
	private String result;

	public static final int NO_ALERT = 0;
	public static final int ERROR = 1;
	public static final int INFORMATION = 2;
	public static final int CONFIRMATION = 3;
	
	/**
	 * Instantiates a new alert property.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public AlertProperty() {
		this.title = "";
		this.header = "";
		this.content = "";
		this.typeProperty = new SimpleIntegerProperty(NO_ALERT);
		this.result = "";
	}
	
	/**
	 * Gets the title.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the header.
	 * 
	 * @precondition none
	 * @postcondition getHeader() == header 
	 *
	 * @param header 
	 *           the new header
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	
	/**
	 * Gets the header.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the header
	 */
	public String getHeader() {
		return this.header;
	}

	/**
	 * Sets the title.
	 * 
	 * @precondition none
	 * @postcondition getTitle() == title 
	 *
	 * @param title 
	 *           the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the content.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the content
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * Sets the content.
	 *
	 * @precondition none
	 * @postcondition getContent() == content
	 *
	 * @param content 
	 *            the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the type.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the type
	 */
	public Integer getType() {
		return this.typeProperty.get();
	}

	/**
	 * Sets the result.
	 *
	 * @precondition none
	 * @postcondition getResult() == result
	 *
	 * @param result 
	 *         the new type
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	/**
	 * Gets the result.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the result
	 */
	public String getResult() {
		return this.result;
	}

	/**
	 * Sets the type.
	 * 
	 * @precondition none
	 * @postcondition setType() == type
	 *
	 * @param type 
	 *         the new type
	 */
	public void setType(int type) {
		this.typeProperty.set(type);
	}
	
	/**
	 * Sets all data fields of this AlertProperty object.
	 * 
	 * The type property needs to be set at last so that all other fields 
	 * are set when the ChangeListener for the type field is activated
	 *
	 * @precondition none
	 * @postcondition getTitle() == title && getHeader() == header
	 *                && getContent() == content && getType() == type
	 *
	 * @param type the type
	 * @param header the type
	 * @param title the title
	 * @param content the content
	 */
	public void set(int type, String title, String header, String content) {
		this.setTitle(title);
		this.setHeader(header);
		this.setContent(content);
		this.setType(type);
	}
	
	/**
	 * Sets all data fields of this AlertProperty object.
	 * 
	 * The type property needs to be set at last so that all other fields 
	 * are set when the ChangeListener for the type field is activated
	 *
	 * @precondition none
	 * @postcondition getTitle() == title && getContent() == content 
	 *                && getType() == type
	 *
	 * @param type the type
	 * @param title the title
	 * @param content the content
	 */
	public void set(int type, String title, String content) {
		this.setTitle(title);
		this.setContent(content);
		this.setType(type);
	}
	
	/**
	 * Adds the listener.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @param listener
	 *            the listener
	 */
	public void addListener(ChangeListener<? super Number> listener) {
		this.typeProperty.addListener(listener);
	}
}
