package messages;
import users.*;

/**
 * @author Martim Costa / Francisco Gouveia
 *
 */
public class MessageClass implements Message {
	/**
	 * owner - User that as written the message
	 */
	private User owner;
	/**
	 * title - title associated to the message
	 */
	private String title;
	/**
	 * text - text of the message
	 */
	private String text;
	/**
	 * url - URL of the message
	 */
	private String url;
	
	
	public MessageClass(User owner, String title, String text, String url) {
		
		this.owner=owner;
		this.title=title;
		this.text=text;
		this.url=url;
	}
	
	
	@Override
	public User getOwner() {
		return owner;
	}
	

	@Override
	public String getText() {
	
		return text;
	}

	@Override
	public String getTitle() {
	
		return title;
	}

	@Override
	public String getURL() {
		
		return url;
	}


	

}
