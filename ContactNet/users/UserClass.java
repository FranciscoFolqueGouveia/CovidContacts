package users;
import dataStructures.*;
import exceptions.*;
import messages.*;
import groups.*;

/**
 * @author Martim Costa / Francisco Gouveia
 *
 */
public class UserClass implements User {

	private static final int MAXGROUPS = 10;
	/**
	 * login is the identifier of the User
	 */
	private String login;
	/**
	 * name - the name associated to the User
	 */
	private String name;
	/**
	 * age - the age of the User
	 */
	private int age;
	/**
	 * location - the location of the User
	 */
	private String location;
	/**
	 * job - the User job
	 */
	private String job;
	/**
	 * This is list to save the contacts of the user
	 * We choose a list because a user may add a contact or remove a contact
	 * and because it is easier to insert and remove on a list
	 */ 
	private List<User> contacts;
	
	/**
	 * This is list to save the groups the user is in
	 * We choose a array because the user has a limit number of groups to be part of.
	 * So an array would be more efficient and wouldt have the problem of resize method, wich is expensive
	 */ 
	private Array<Group> groups;
	
	
	/**
	 * This list saves all the messages of the user. The received ones and the write ones.
	 * We choose a list because there are no limits of messages received or written
	 */
	private List<Message> messages;


	public UserClass(String login, String name, int age, String location, String job) {
		this.login = login;
		this.name = name;
		this.age = age;
		this.location = location;
		this.job = job;
		this.contacts = new SinglyLinkedList<User>();
		this.groups = new ArrayClass<Group>(MAXGROUPS);
		this.messages = new DoublyLinkedList<Message>();
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public String getJob() {

		return job;
	}
	
	public Iterator<User> contactsIterator() {
		return contacts.iterator();
	}

	public Iterator<Group> groupsIterator() {
		return groups.iterator();
	}

	public Iterator<Message> messagesIterator() {
		return messages.iterator();
	}

	public boolean isContactsEmpty() {
		return contacts.isEmpty();
	}

	public boolean isGroupsEmpty() {
		return groups.size()==0;

	}
	
	public boolean isMessagesEmpty() {
		return messages.isEmpty();

	}
	
	public boolean hasContact(User contact) {
		if (contacts.isEmpty()) {
			return false;
		} else {

			return contacts.find(contact) >= 0;
		}
	}


	@Override
	public void addContact(User contact) throws ContactExists {
			int position = positionToInsert(contact);
			contacts.add(position, contact);
		
	}

	public void removeContact(User contact) throws ContactNotExists {

		if (contacts.remove(contact)) {
		} else
			throw new ContactNotExists();

	}

	public void registInGroup(Group group) {

		groups.insertLast(group);

	}

	public void leaveGroup(Group group) {
		int pos = groups.searchIndexOf(group);
		if (pos >= 0) {
			groups.removeAt(pos);
		}

	}
	
	@Override
	public void postMessage(Message message) {
		
		this.receiveMessage(message);
		
		if (!isContactsEmpty()) {
			this.sendToContacts(message);
		}
		if (!isGroupsEmpty()) {
			this.sendToGroups(message);
		}
	}

	

	@Override
	public void receiveMessage(Message message) {
		messages.addFirst(message);
	}


	
	
	/**
	 * @param message to send to contacts
	 * this method adds the new message in the list of messages of all the contacts of the user
	 */
	private void sendToContacts(Message message) {

		Iterator<User> contactIterator = contactsIterator();

		while (contactIterator.hasNext()) {
			User contact = contactIterator.next();
			contact.receiveMessage(message);
		}

	}

	/**
	 * @param message to send to groups
	 * this method adds the new message in the list of messages of all the groups the user is in
	 */
	private void sendToGroups(Message message) {

		Iterator<Group> groupIterator = groupsIterator();

		while (groupIterator.hasNext()) {
			Group group = groupIterator.next();
			group.receiveMessage(message);
		}

	}

	/**
	 * @param user - contact to add in the contacts list of the User
	 * @return the position where the user should be inserted to stay in alphabetic order. If the list already have that User, this method throws an exception ContactExists
	 */
	private int positionToInsert(User user) throws ContactExists {
		if (contacts.isEmpty()) {
			return 0;
		} else {
			Iterator<User> auxIterator = contactsIterator();
			boolean found = false;
			int position = 0;

			while (auxIterator.hasNext() && !found) {
				User nextUser = auxIterator.next();
				if(nextUser.equals(user)) {
					throw new ContactExists();
				}
				if (sameLogins(nextUser, user)) {
					found = true;
				} else
					position++;
			}

			return position;

		}

	}

	/**
	 * @param user1 - first User to compare
	 * @param user2 - second User to compare
	 * @return <code>true</code> if the user1 login is first in the alphabetical order compared to the user2 login
	 *         <code>false</code> if the opposite happens
	 */
	private boolean sameLogins(User user1, User user2) {

		return (user1.getLogin()).compareTo(user2.getLogin())>0;
	}

}
