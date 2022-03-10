package contactNet;

import dataStructures.*;
import exceptions.*;
import users.*;
import groups.*;
import messages.*;

/**
 * @author Martim Costa / Francisco Gouveia
 *
 */
public class ContactNetClass implements ContactNet {

	/**
	 * This is list to save the users inserted in the ContactNet We choose a list
	 * because we dont have a limit number of users to insert in this list. So with
	 * a list to insert a new element we just need to create a node
	 */
	List<User> users;
	/**
	 * This is list to save the groups inserted in the ContactNet We choose a list
	 * because we dont have a limit number of groups to insert in this list. So with
	 * a list to insert a new element we just need to create a node
	 */
	List<Group> groups;

	public ContactNetClass() {

		users = new SinglyLinkedList<User>();
		groups = new SinglyLinkedList<Group>();
	}

	@Override
	public void insertUser(String login, String name, int age, String address, String profession) throws UserExists {
		if (hasUser(login)) {
			throw new UserExists();
		} else {
			User user = new UserClass(login, name, age, address, profession);
			users.addLast(user);

		}
	}

	@Override
	public User showUser(String login) throws UserNotExists {

		int position = findUser(login);
		if (position >= 0) {
			return users.get(position);
		}

		else {
			throw new UserNotExists();
		}

	}

	@Override
	public void insertContact(String login1, String login2) throws UserNotExists, ContactExists {

		User contact1 = showUser(login1);
		User contact2 = showUser(login2);

		if (login1.equalsIgnoreCase(login2)) {
			throw new ContactExists();
		}

		contact1.addContact(contact2);
		contact2.addContact(contact1);

	}

	@Override
	public void removeContact(String login1, String login2) throws UserNotExists, ContactNotExists, ContactNotRemoved {

		User contact1 = showUser(login1);
		User contact2 = showUser(login2);

		if (login1.equalsIgnoreCase(login2)) {
			throw new ContactNotRemoved();
		}

		contact1.removeContact(contact2);
		contact2.removeContact(contact1);
	}

	@Override
	public Iterator<User> listContacts(String login) throws UserNotExists, NoContacts {

		User user = showUser(login);

		if (user.isContactsEmpty()) {
			throw new NoContacts();
		}
		return user.contactsIterator();
	}

	@Override
	public void insertGroup(String name, String description) throws GroupExists {
		if (hasGroup(name)) {
			throw new GroupExists();
		} else {
			Group group = new GroupClass(name, description);
			groups.addLast(group);

		}

	}

	@Override
	public Group showGroup(String name) throws GroupNotExists {
		int position = findGroup(name);
		if (position >= 0) {
			return groups.get(position);
		} else {
			throw new GroupNotExists();

		}
	}

	@Override
	public void removeGroup(String name) throws GroupNotExists {
		Group group1 = showGroup(name);

		groups.remove(group1);

	}

	@Override
	public void subscribeGroup(String login, String name) throws UserNotExists, GroupNotExists, SubscriptionExists {
		User user = showUser(login);
		Group group = showGroup(name);

		group.insertParticipant(user);
		user.registInGroup(group);

	}

	@Override
	public void removeSubscription(String login, String group)
			throws UserNotExists, GroupNotExists, SubscriptionNotExists {

		User user = showUser(login);
		Group group1 = showGroup(group);

		group1.removeParticipant(user);
		user.leaveGroup(group1);

	}

	@Override
	public Iterator<User> listParticipants(String name) throws GroupNotExists, NoParticipants {

		Group group1 = showGroup(name);
		if (group1.isMembersEmpty()) {
			throw new NoParticipants();
		}
		return group1.membersIterator();
	}

	@Override
	public void insertMessage(String login, String title, String text, String url) throws UserNotExists {
		// TODO Auto-generated method stub
		User user = showUser(login);
		Message message = new MessageClass(user, title, text, url);

		user.postMessage(message);

	}

	@Override
	public Iterator<Message> listContactMessages(String login1, String login2)
			throws UserNotExists, ContactNotExists, NoContactMessages {
		User user1 = showUser(login1);
		User user2 = showUser(login2);

		if (!user1.hasContact(user2)) {
			if (!(login1.equalsIgnoreCase(login2))) {
				throw new ContactNotExists();
			}
		}
		if (user1.isMessagesEmpty()) {
			throw new NoContactMessages();
		}
		return user1.messagesIterator();
	}

	@Override
	public Iterator<Message> listGroupMessages(String group, String login)
			throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages {
		Group group1 = showGroup(group);
		User user = showUser(login);

		if (!group1.hasMember(user)) {
			throw new SubscriptionNotExists();
		} else if (group1.isMessagesEmpty()) {
			throw new NoGroupMessages();
		}

		return group1.messagesIterator();
	}

	/**
	 * @param login - login of the user to search index in list
	 * @return the position where the user with that login is in the users list of
	 *         ContactNet, if exists a user with that login. Otherwise return the
	 *         int value -1;
	 */
	private int findUser(String login) {

		if (users.isEmpty()) {
			return -1;
		}

		else {
			Iterator<User> auxIterator = users.iterator();
			boolean found = false;
			int position = 0;

			while (auxIterator.hasNext() && !found) {
				User user = auxIterator.next();
				if (sameLogin(user, login)) {
					found = true;
				} else
					position++;
			}

			if (found) {
				return position;
			} else
				return -1;

		}
	}

	/**
	 * @param name - name of group to search index in list
	 * @return the position where the group with that name is in the groups list of
	 *         ContactNet, if exists a group with that name. Otherwise return the
	 *         int value -1;
	 */
	private int findGroup(String name) {

		if (groups.isEmpty()) {
			return -1;
		}

		else {
			Iterator<Group> auxIterator = groups.iterator();
			boolean found = false;
			int position = 0;

			while (auxIterator.hasNext() && !found) {
				Group group = auxIterator.next();
				if (sameGroupName(group, name)) {
					found = true;
				} else
					position++;
			}

			if (found) {
				return position;
			} else
				return -1;

		}
	}

	/**
	 * @param user  - object of type User to compare
	 * @param login - String to compare
	 * @return <code>true</code> if the login of the object User is equals a
	 *         specific String else return <code>false</code>
	 */
	private boolean sameLogin(User user, String login) {
		return (user.getLogin()).equalsIgnoreCase(login);
	}

	/**
	 * @param group - object of type Group to compare
	 * @param name  - string to compare
	 * @return <code>true</code> if the name of the object Group is equals a
	 *         specific String else return <code>false</code>
	 */
	private boolean sameGroupName(Group group, String name) {
		return (group.getName()).equalsIgnoreCase(name);
	}

	/**
	 * @param login of the user to search
	 * @return <code>true</code> if the user list of the ContactNet already have a
	 *         user with that login else return <code>false</code>
	 */
	private boolean hasUser(String login) {
		boolean found = false;
		if (findUser(login) >= 0) {
			found = true;
		}

		return found;

	}

	/**
	 * @param name of the group to search
	 * @return <code>true</code> if the group list of the ContactNet already have a
	 *         group with that name else return <code>false</code>
	 */
	private boolean hasGroup(String name) {
		boolean found = false;
		if (findGroup(name) >= 0) {
			found = true;
		}

		return found;

	}

}
