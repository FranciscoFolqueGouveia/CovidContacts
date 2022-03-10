package groups;
import dataStructures.*;
import exceptions.*;
import users.*;
import messages.*;

/**
 * @author Martim Costa / Francisco Gouveia
 *
 */
public class GroupClass implements Group {

	/**
	 *  name - identifier of the Group
	 */
	private String name;

	/**
	 * description - description associated to the Group
	 */
	private String description;
	
	/**
	 * List of members (type User) of the group
	 * We choose a list because the group does not have a limit of members,
	 * and because we can add or remove members as many times as we which. 
	 * So a list is more efficient to add or remove
	 */
	private List<User> members;
	
	/**
	 * List of messages written by the members of the group
	 * We choose a list because the group does not have a limit of messages
	 * and we wont need to get a specific message . We just need to save them and iterate them
	 */
	private List<Message> messages;
	

	public GroupClass(String name, String description) {
		this.name = name;
		this.description = description;
		this.members = new SinglyLinkedList<User>();
		this.messages = new DoublyLinkedList<Message>();
	}
	
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void insertParticipant(User member) throws SubscriptionExists {
		
			int position = positionToInsert(member);
			members.add(position, member);
		
	}
	@Override
	public void removeParticipant(User member) throws SubscriptionNotExists {
		
			int position = members.find(member);
			if(position>= 0) {
			members.remove(position);}
			else throw new SubscriptionNotExists();
		
	}
	@Override
	public void receiveMessage(Message message) {
		messages.addFirst(message);
	}
			
	@Override
	public Iterator<User> membersIterator() {
		return members.iterator();
}
	@Override
	public Iterator<Message> messagesIterator() {
		return messages.iterator();
}
	
	@Override
	public boolean hasMember(User member) {
		return members.find(member)>=0;
		
	}
	@Override
	public boolean isMessagesEmpty() {
		return messages.isEmpty();
	}
	
	@Override
	public boolean isMembersEmpty() {
		return members.isEmpty();
	}

	
	
	
	
	/**
	 * @param member- the User that will be a participant
	 * @return the position where is to insert the new participant
	 * 
	 * This method will first verify if the participants list is empty. 
	 * If so it will return 0, else it will create an iterator and 2 variables
	 * one boolean and other int. 
	 * It will search in the list the position to insert the new member, 
	 * using the method "compareLogins". When it find the position , 
	 * the variable found will become true, 
	 * if not it will add 1 to the int position. 
	 * @throws SubscriptionExists 
	 */
	private int positionToInsert(User member) throws SubscriptionExists {
		if (members.isEmpty()) {
			return 0;
		} else {
			Iterator<User> auxIterator = members.iterator();
			boolean found = false;
			int position = 0;

			while (auxIterator.hasNext() && !found) {
				User user = auxIterator.next(); 
				if(user.equals(member)) {
					throw new SubscriptionExists();
				}
				if (compareLogins(user, member)) {
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
	private boolean compareLogins(User user1, User user2) {

		return (user1.getLogin()).compareTo(user2.getLogin()) > 0;
	}

	

}
