package users;
import dataStructures.Iterator;
import exceptions.*;
import messages.*;
import groups.*;

/**
 * @author Martim Costa / Francisco Gouveia
 *
 */
public interface User extends UserPriv {
	
	
			
	/**
	 * @return the iterator of contacts of the User
	 */
	Iterator<User> contactsIterator();
	
	/**
	 * @return the iterator of groups where the User is registered
	 */
	Iterator<Group> groupsIterator();
	
	/**
	 * @return the iterator of Messages of the User
	 */
	Iterator<Message> messagesIterator();
	
	/**
	 * @return <code>true</code> if the User has no contacts
	 *         <code>false</code> if the opposite happens
	 */
	boolean isContactsEmpty();
	
	/**
	 * @return <code>true</code> if the User is not resgistered in any Group
	 *         <code>false</code> if the opposite happens
	 */
	boolean isGroupsEmpty();
	
	/**
	 * @return <code>true</code> if the User has no Messages 
	 *         <code>false</code> if the opposite happens
	 */
	boolean isMessagesEmpty();
	
	/**
	 * @param contact - the User that will be verified if is a contact
	 * @return <code>true</code> if it is a contact 
	 *         <code>false</code> if the opposite happens
	 *         
	 * This method will first verify if the contacts list is empty. If so it will return false, 
	 * else it will search the User in the contacts list        
	 */
	boolean hasContact(User contact);
	
	/**
	 * @param contact - the User that will be a new contact
	 * @throws ContactExists - throws this exception if the User is already a contact
	 * 
	 * This method will add the User as a contact. 
	 * It will run the list to search if the User already exists and to simultaneously search the position to insert.
	 * it search the position to insert in alphabetic order, and insert the User there
	 */
	
	void addContact(User contact) throws ContactExists;
	
	/**
	 * @param contact - the user that will be removed as contact
	 * @throws ContactNotExists - throws this exception if the User is not a contact
	 * 
	 * This method will remove the User of his contacts list. 
	 * It will verify if that User we want to remove exist and it will remove it, 
	 * if the user do not exists it throw the @ContactNotExists exception
	 */
	void removeContact(User contact) throws ContactNotExists;
	
	
	
	/**
	 * @param message - the new Message that will be added
	 * 
	 * This method will added to the User Message list and to the contacts and groups. 
	 * First it will be added to his own list. 
	 * After it will verify if the Groups list and the contacts list are empty(not at same time). 
	 * If they are not , it will be sent the message to them.
	 */
	void postMessage(Message message);
	
	/**
	 * @param message - Message that will be received
	 * 
	 * This method will add a message that is from other User 
	 */
	void receiveMessage(Message message);
		
	/**
	 * Attention because it is in the group that we verify if the user is a member of the group. 
	 * If it is, it should throw an exception and the user should not be inserted because he already belongs to the group
	 * @param group - name of the Group
	 * 
	 * This method will add the Group to the Users array of groups. The object Group must also receive the User in its members list
	 */
	void registInGroup(Group group);
	
	/**
	 * 
	 * Attention because it is in the group that we verify if the user is a member of the group. 
	 * If it is not, it should throw an exception and the user should not be removed because he does not belongs to the group
	 * 
	 * @param group - name of the Group
	 * 
	 * This method will remove the Group from the Users array of groups. The object Group must also remove the User from its members list
	 */
	void leaveGroup(Group group);

}