package messages;
import users.*;

/**
 * @author Martim Costa/Francisco Gouveia
 *
 */
public interface Message {
	
	
	/**
	 * @return the writer of the Message
	 * 
	 */
	User getOwner();
	
	/**
	 * @return the text of the Message
	 */
	String getText();
	
	/**
	 * @return the title of the Message
	 */
	String getTitle();
	
	/**
	 * @return the link of the Message
	 */
	String getURL();
	
	
	

}
