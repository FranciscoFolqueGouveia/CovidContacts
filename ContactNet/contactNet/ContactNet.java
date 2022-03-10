package contactNet;
import dataStructures.*;
import exceptions.*;
import users.*;
import groups.*;
import messages.*;

/**
 * @author Martim Costa/Francisco Gouveia
 *
 */
public interface ContactNet {

	/**
	 * @param login-User login
	 * @param name- name of the User
	 * @param age- age of the User
	 * @param address- address of the User
	 * @param profession - profession of the User
	 * @throws UserExists - throws this exception if the User already exist in the system
	 * 
	 * This method will insert a new User in the system. First will check 
	 * if there is already a User with the same login, if so it will throw the @UserExists exception,
	 * else it will add the User to the system.
	 */
	void insertUser(String login, String name, int age, String address, String profession) throws UserExists;

	/**
	 * @param login -login of the User
	 * @return the User that have this login
	 * @throws UserNotExists- throws this exception if the User do not exist in the system
	 * 
	 * This method will return the the User that have that login. 
	 * First it will verify if the User exists. 
	 * If does not exist will throw the @UserNotExists, 
	 * else will find his position in the list and return the User in that position
	 *  
	 */
	User showUser(String login) throws UserNotExists;

	/**
	 * @param login1 - login of one of the Users
	 * @param login2 - login of the other User
	 * @throws UserNotExists - throws this exception if at least one of the Users does not exist
	 * @throws ContactExists - throws this exception if the Contact between them already exist
	 * 
	 * This method will create a new Contact between two Users. If them both exist, 
	 * it will verify if the Contact exist. 
	 * If so it will throw the @ContactExists exception, 
	 * else it will add the Contact in both of them.
	 * 
	 */
	void insertContact(String login1, String login2) throws UserNotExists, ContactExists;

	/**
	 * @param login1 - login of one of the Users
	 * @param login2 - login of the other User
	 * @throws UserNotExists - throws this exception if at least one of the Users does not exist
	 * @throws ContactNotExists - throws this exception if the Contact between them already exist
	 * @throws ContactNotRemoved - throws this exception if login1 equals login2
	 * 
	 * This method will remove an existent Contact between the 2 Users. 
	 * If them both exist and the Contact too, it will verify if login1 and login 2 are equal. 
	 * If so it will throw the @ContactNotRemoved exception, 
	 * else it will remove the Contact in the 2 Users
	 */
	void removeContact(String login1, String login2) throws UserNotExists, ContactNotExists, ContactNotRemoved;

	
	/**
	 * @param login - login of the User
	 * @return the iterator of the List Contacts of the User
	 * @throws UserNotExists - throws this exception if at least one of the Users does not exist
	 * @throws NoContacts - throws this exception if the User has no Contacts
	 * 
	 * This method will return the iterator of the List of Contacts of the User. 
	 * If the User exist, it will verify if he has any Contacts. 
	 * If not, it will throw the @NoContacts exception,
	 * else it will return the iterator
	 */
	Iterator<User> listContacts(String login) throws UserNotExists, NoContacts;

	/**
	 * @param name - name of the Group
	 * @param description - description of the Group
	 * @throws GroupExists - throws this exception if the Group already exist in the system
	 * 
	 * This method will insert a new Group in the system. 
	 * First it will verify if the Group already exist. 
	 * If so it throw the @GroupExists exception, else it will create a new Group.
	 */
	void insertGroup(String name, String description) throws GroupExists;

	/**
	 * @param name - name of the Group
	 * @return the Group that have that name
	 * @throws GroupNotExists - throws this exception if the Group does not exist in the system
	 * 
	 * This method will return the Group with that name. 
	 * First it will verify if the Group exist. 
	 * If not it will throw the @GroupNotExist exception, else 
	 * will find his position in the list and return the Group in that position.
	 */
	Group showGroup(String name) throws GroupNotExists;

	/**
	 * @param name - name of the Group
	 * @throws GroupNotExists - throws this exception if the Group does not exist in the system
	 * 
	 * This method will remove the Group with that name. 
	 * If the Group exist , it will remove it from the system.
	 */
	void removeGroup(String name) throws GroupNotExists;

	/**
	 * @param login - name of the User
	 * @param group - name of the Group
	 * @throws UserNotExists - throws this exception if the User does not exist
	 * @throws GroupNotExists - throws this exception if the Group does not exist
	 * @throws SubscriptionExists - throws this exception if the User is already a participant on the Group
	 * 
	 * This method will add the User as participant of the Group. 
	 * If the User and Group exist, it will register the User at the Group 
	 * and add the Group in the User Group list.
	 */
	void subscribeGroup(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionExists;

	/**
	 * @param login - login of the User
	 * @param group - name of the Group
	 * @throws UserNotExists - throws this exception if the User does not exist
	 * @throws GroupNotExists - throws this exception if the Group does not exist
	 * @throws SubscriptionNotExists - throws this exception if the User is not a participant on the Group
	 * 
	 * This method will remove the User as participant of the Group. 
	 * If the User and Group exist, and the subscription exist,
	 * it will remove the User from the Group and the Group from the User Group list.
	 */
	void removeSubscription(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionNotExists;

	
	/**
	 * @param name - name of the Group
	 * @return the iterator of the Group User list
	 * @throws GroupNotExists - throws this exception if the Group does not exist
	 * @throws NoParticipants - throws this exception if the Group has no participants at it
	 * 
	 * This method will return the iterator of the Group User list. 
	 * If the group exist , it will verify if it has any participants.
	 *  If not , it will throw the @NoParticipants exception,
	 *  else it will return the iterator.
	 * 
	 */
	Iterator<User> listParticipants(String name) throws GroupNotExists, NoParticipants;

	/**
	 * @param login - login of the User
	 * @param title - title of the Message
	 * @param text - content of the message
	 * @param url - link of the message
	 * @throws UserNotExists - throws this exception if the User does not exist
	 * 
	 * This method will create a new Message. 
	 * If the User exist, it will create the message.
	 */
	void insertMessage(String login, String title, String text, String url) throws UserNotExists;

	/**
	 * @param login1 - login of one of the Users
	 * @param login2 - login of the other User
	 * @return the iterator of Messages between the 2 Users
	 * @throws UserNotExists - throws this exception if the User does not exist
	 * @throws ContactNotExists - throws this exception if the Contact between them already exist
	 * @throws NoContactMessages - throws this exception if there is no Messages to show
	 * 
	 * This method will return the iterator of Messages between the 2 Users. 
	 * If both of the Users exist and the contact between them, 
	 * it will verify if there is any Message between them. 
	 * If there is not any Message, it will throw @NoContactMessages exception, 
	 * else it will return the iterator 
	 */
	Iterator<Message> listContactMessages(String login1, String login2)
			throws UserNotExists, ContactNotExists, NoContactMessages;

	/**
	 * @param group - name of the Group
	 * @param login - login of the User
	 * @return the iterator of the Messages that belong to the Group which the User is a participant
	 * @throws GroupNotExists - throws this exception if the Group does not exist
	 * @throws UserNotExists - throws this exception if the User does not exist
	 * @throws SubscriptionNotExists - throws this exception if the User is not a participant on the Group
	 * @throws NoGroupMessages - throws this exception if there is no Messages to show
	 * 
	 * This method will return the Iterator of Messages that belongs to the Group where the User is participant. 
	 * If the Group, User and the subscription exists, 
	 * it will verify if there is any Messages to show. 
	 * If do not, it will throw @NoGroupMessages exception, else i will return the iterator.
	 */
	Iterator<Message> listGroupMessages(String group, String login)
			throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages;
	
	
}
