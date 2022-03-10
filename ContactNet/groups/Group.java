package groups;
import dataStructures.Iterator;
import exceptions.*;
import users.*;
import messages.*;

/**
 * @author Martim Costa/Francisco Gouveia
 *
 */
public interface Group extends GroupPriv {
	
	
	
	/**
	 * @param member - the User that will be a new participant 
	 * @throws SubscriptionExists - throws this exception if the User is already a participant
	 * 
	 * This method will add the User as participant at the Group. 
	 * It will run the iterator to search if the User already exists and simultaneously to find the position to insert.
	 * If the User already exists it will throw @SubscriptionExist exception, 
	 * else will find the position for the new User in the list, 
     * knowing it will be in alphabetical order, 
     * and it will put the User in that position.
     */
	void insertParticipant(User member) throws SubscriptionExists;
	
	/**
	 * @param member - the User that will be removed
	 * @throws SubscriptionNotExists - throws this exception if the User is not a participant
	 * 
	 * This method will remove the User from the Group. 
	 * First will verify if the User is a participant. 
	 * If not, it will throw @SubscriptionNotExist exception, 
	 * else it will find the position off the User and remove it
	 */
	void removeParticipant(User member) throws SubscriptionNotExists;
	
	/**
	 * @param message - the new Message
	 * 
	 * This method will add a new Message in the Group at the first position.
	 */
	void receiveMessage(Message message);
	
	/**
	 * @param member - the User to be checked if is a participant
	 * @return <code>true</code> if the User is a participant of the Group
	 *         <code>false</code> if the opposite happens          
	 */
	
	boolean hasMember(User member);
	/**
	 * @return <code>true</code> if there is no Messages
	 *         <code>false</code> if the opposite happens             
	 */
	boolean isMessagesEmpty();
	
	/**
	 * @return <code>true</code> if there is no participants
	 *         <code>false</code> if the opposite happens      
	 */
	boolean isMembersEmpty();
	
	/**
	 * @return the iterator of participants of the Group
	 * 
	 */
	Iterator<User> membersIterator();
	
	/**
	 * @return the iterator of Messages of the Group
	 */
	Iterator<Message> messagesIterator();

	
}
