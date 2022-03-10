import java.util.Scanner;
import contactNet.*;
import dataStructures.*;
import exceptions.*;
import users.*;
import groups.*;
import messages.*;



//TODO
//import *** OS VOSSOS IMPORTS AQUI ***
//EX: Se quiserem separar as excepcoes das interfaces e 
//classes de dominio (cujo pacote se chama network neste exemplo):
//import network.*;
//import network.exceptions.*;

public class Main {

	private static final String PROMPT = "> ";
	
	//constantes para os comandos do enunciado
	//isto tambem podia ser feito com um enumerado
	private static final String INSERT_USER = "IU";
	private static final String SHOW_USER = "DU";
	private static final String INSERT_CONTACT = "IC";
	private static final String REMOVE_CONTACT = "RC";
	private static final String LIST_CONTACTS = "LC";
	private static final String INSERT_GROUP = "IG";
	private static final String SHOW_GROUP = "DG";
	private static final String REMOVE_GROUP = "RG";
	private static final String INSERT_GROUP_PARTICIPANT = "IP";
	private static final String REMOVE_GROUP_PARTICIPANT = "RP";
	private static final String LIST_GROUP_PARTICIPANTS = "LP";
	private static final String INSERT_MESSAGE = "IM";
	private static final String LIST_CONTACT_MESSAGES = "LMC";
	private static final String LIST_GROUP_MESSAGES = "LMG";
	private static final String EXIT = "FIM";

	//constantes para as mensagens de output
	//mensagens de comando cexecutado
	private static final String INSERT_USER_OK = "Registo de utilizador executado.";
	private static final String INSERT_CONTACT_OK = "Registo de contacto executado.";
	private static final String REMOVE_CONTACT_OK = "Remocao de contacto executada.";
	private static final String INSERT_GROUP_OK = "Registo de grupo executado.";
	private static final String REMOVE_GROUP_OK = "Remocao de grupo executada.";
	private static final String SUBSCRIBE_GROUP_OK = "Registo de participante executado.";
	private static final String REMOVE_SUBSCRIPTION_OK = "Remocao de aderencia executada.";
	private static final String INSERT_MESSAGE_OK = "Registo de mensagem executado.";
	private static final String EXIT_MESSAGE = "Obrigado. Ate a proxima.";
	//mensagens de comando nao executado - execucao sem sucesso 
	public static final String USER_EXISTS = "Utilizador ja existente.";
	public static final String USER_NOT_EXISTS = "Inexistencia do utilizador referido.";
	public static final String CONTACT_EXISTS = "Existencia do contacto referido.";
	public static final String CONTACT_NOT_EXISTS = "Inexistencia do contacto referido.";
	public static final String CONTACT_NOT_REMOVED = "Contacto nao pode ser removido.";
	public static final String NO_CONTACTS = "Inexistencia de contactos.";
	public static final String GROUP_EXISTS = "Grupo ja existente.";
	public static final String GROUP_NOT_EXISTS = "Inexistencia do grupo referido.";
	public static final String SUBSCRIPTION_EXISTS = "Existencia de aderencia referida.";
	public static final String NO_PARTICIPANTS = "Inexistencia de participantes.";
	public static final String SUBSCRIPTION_NOT_EXISTS = "Inexistencia do participante referido.";
	public static final String SUBSCRIPTION_NOT_EXISTS_RP = "Inexistencia de aderencia referida.";	
	public static final String NO_CONTACT_MESSAGES = "Contacto nao tem mensagens.";
	public static final String NO_GROUP_MESSAGES = "Grupo nao tem mensagens.";
	
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String cmd;
		ContactNet net = new ContactNetClass();
		boolean exit = false;
		
		do {
			System.out.print(PROMPT);
			cmd = in.next();
			if (cmd.equalsIgnoreCase(INSERT_USER))
				insertUser(in, net);
			else if (cmd.equalsIgnoreCase(SHOW_USER))
				showUser(in, net);
			else if (cmd.equalsIgnoreCase(INSERT_CONTACT))
				insertContact(in, net);	
			else if (cmd.equalsIgnoreCase(REMOVE_CONTACT))
				removeContact(in, net);
			else if (cmd.equalsIgnoreCase(LIST_CONTACTS))
				listContacts(in, net);
			else if (cmd.equalsIgnoreCase(INSERT_GROUP))
				insertGroup(in, net);
			else if (cmd.equalsIgnoreCase(SHOW_GROUP))
				showGroup(in, net);
			else if (cmd.equalsIgnoreCase(REMOVE_GROUP))
				removeGroup(in, net);
			else if (cmd.equalsIgnoreCase(INSERT_GROUP_PARTICIPANT))
				subscribeGroup(in, net);
			else if (cmd.equalsIgnoreCase(REMOVE_GROUP_PARTICIPANT))
				removeSubscription(in, net);
			else if (cmd.equalsIgnoreCase(LIST_GROUP_PARTICIPANTS))
				listParticipants(in, net);		
			else if (cmd.equalsIgnoreCase(INSERT_MESSAGE))
				insertMessage(in, net);
			else if (cmd.equalsIgnoreCase(LIST_CONTACT_MESSAGES))
				listContactMessages(in, net);
			else if (cmd.equalsIgnoreCase(LIST_GROUP_MESSAGES))
				listGroupMessages(in, net);
			else if (cmd.equalsIgnoreCase(EXIT)) {
				in.nextLine(); in.nextLine();
				System.out.println(EXIT_MESSAGE);
				exit = true;
			}
			else {
				in.nextLine(); in.nextLine();
			}
		} while (!exit);

	}

	
	private static void insertUser(Scanner in, ContactNet net) {
		String login = in.next().toUpperCase();
		String name = in.nextLine().trim().toUpperCase();
		int age = in.nextInt();
		String address = in.nextLine().trim().toUpperCase();
		String profession = in.nextLine().trim().toUpperCase();
		in.nextLine();
		try {
			net.insertUser(login, name, age, address, profession);
			System.out.println(INSERT_USER_OK);
		} catch (UserExists e) {
			System.out.println(USER_EXISTS);
		}
	}

	private static void showUser(Scanner in, ContactNet net) {
		String login = in.next().trim().toUpperCase();
		in.nextLine(); 	in.nextLine();
		try {
			//TODO - completar proxima linha
		UserPriv user = net.showUser(login);
			System.out.println(user.getLogin() + " " + user.getName() + " "
					+ user.getAge());
			System.out.println(user.getLocation() + " " + user.getJob());
		} catch (UserNotExists e) {
			System.out.println(USER_NOT_EXISTS);
		}
	}

	private static void insertContact(Scanner in, ContactNet net) {
		String login1 = in.next().toUpperCase();
		String login2 = in.next().toUpperCase();
		in.nextLine(); in.nextLine();

		try {
			net.insertContact(login1, login2);
			System.out.println(INSERT_CONTACT_OK);
		} catch (UserNotExists e) {
			System.out.println(USER_NOT_EXISTS);
		} catch (ContactExists e) {
			System.out.println(CONTACT_EXISTS);
		}
	}

	private static void removeContact(Scanner in, ContactNet net) {
		String login1 = in.next().toUpperCase();
		String login2 = in.next().toUpperCase();
		in.nextLine(); in.nextLine();
		try {
			net.removeContact(login1, login2);
			System.out.println(REMOVE_CONTACT_OK);
		} catch (UserNotExists e) {
			System.out.println(USER_NOT_EXISTS);
		} catch (ContactNotExists e) {
			System.out.println(CONTACT_NOT_EXISTS);
		} catch (ContactNotRemoved e) {
			System.out.println(CONTACT_NOT_REMOVED);
		}
	}
	
	private static void listContacts(Scanner in, ContactNet net) {
		String login = in.nextLine().trim().toUpperCase();
		in.nextLine();
		try {
			//TODO - completar proxima linha
			Iterator<User> it = net.listContacts(login);
			printUsers(it);
		} catch (UserNotExists e) {
			System.out.println(USER_NOT_EXISTS);
		} catch (NoContacts e) {
			System.out.println(NO_CONTACTS);
		}
	} 
	

	private static void printUsers(Iterator<User> users) {
		while (users.hasNext()) {
			UserPriv u = users.next();
			System.out.printf("%s %s\n", u.getLogin(), u.getName());
		}
	}

	private static void insertGroup(Scanner in, ContactNet net) {
		String name = in.nextLine().trim().toUpperCase();
		String text = in.nextLine().trim().toUpperCase();
		in.nextLine();
		try {
			net.insertGroup(name, text);
			System.out.println(INSERT_GROUP_OK);
		} catch (GroupExists e) {
			System.out.println(GROUP_EXISTS);
		}
	}

	private static void showGroup(Scanner in, ContactNet net) {
		String name = in.nextLine().trim().toUpperCase();
		in.nextLine();
		try {
			
			GroupPriv g = net.showGroup(name);
			System.out.println(g.getName());
			System.out.println(g.getDescription());
		} catch (GroupNotExists e) {
			System.out.println(GROUP_NOT_EXISTS);
		}
	}

	private static void removeGroup(Scanner in, ContactNet net) {
		String name = in.nextLine().trim().toUpperCase();
		in.nextLine();
		try {
			net.removeGroup(name);
			System.out.println(REMOVE_GROUP_OK);
		} catch (GroupNotExists e) {
			System.out.println(GROUP_NOT_EXISTS);
		}

	}

	private static void subscribeGroup(Scanner in, ContactNet net) {
		String login = in.next().toUpperCase();
		String group = in.next().toUpperCase();
		in.nextLine(); in.nextLine();

		try {
			net.subscribeGroup(login, group);
			System.out.println(SUBSCRIBE_GROUP_OK);
		} catch (UserNotExists e) {
			System.out.println(USER_NOT_EXISTS);
		} catch (GroupNotExists e) {
			System.out.println(GROUP_NOT_EXISTS);
		} catch (SubscriptionExists e) {
			System.out.println(SUBSCRIPTION_EXISTS);
		}
	}

	private static void removeSubscription(Scanner in, ContactNet net) {
		String login = in.next().toUpperCase();
		String name = in.nextLine().trim().toUpperCase();
		in.nextLine();
		try {
			net.removeSubscription(login, name);
			System.out.println(REMOVE_SUBSCRIPTION_OK);
		} catch (UserNotExists e) {
			System.out.println(USER_NOT_EXISTS);
		} catch (GroupNotExists e) {
			System.out.println(GROUP_NOT_EXISTS);
		} catch (SubscriptionNotExists e) {
			System.out.println(SUBSCRIPTION_NOT_EXISTS_RP);
		}
	}

	private static void listParticipants(Scanner in, ContactNet net) {
		String group = in.nextLine().trim().toUpperCase();
		in.nextLine();
		try {
			
			Iterator<User> it = net.listParticipants(group);
			printUsers(it);
		} catch (GroupNotExists e) {
			System.out.println(GROUP_NOT_EXISTS);
		} catch (NoParticipants e) {
			System.out.println(NO_PARTICIPANTS);
		}
	}

	private static void insertMessage(Scanner in, ContactNet net) {
		String login = in.nextLine().trim().toUpperCase();
		String title = in.nextLine().trim().toUpperCase();
		String text = in.nextLine().trim().toUpperCase();
		String url = in.nextLine().trim().toUpperCase();
		in.nextLine();
		try {
			net.insertMessage(login, title, text, url);
			System.out.println(INSERT_MESSAGE_OK);
		} catch (UserNotExists e) {
			System.out.println(USER_NOT_EXISTS);
		}
	}

	private static void listContactMessages(Scanner in, ContactNet net) {
		String login1 = in.next().toUpperCase();
		String login2 = in.nextLine().trim().toUpperCase();
		in.nextLine();
		try {
			Iterator<Message> it = net.listContactMessages(login1, login2);
			printMessages(it);
		} catch (UserNotExists e) {
			System.out.println(USER_NOT_EXISTS);
		} catch (ContactNotExists e) {
			System.out.println(CONTACT_NOT_EXISTS);
		} catch (NoContactMessages e) {
			System.out.println(NO_CONTACT_MESSAGES); 
		}
	}

	private static void listGroupMessages(Scanner in, ContactNet net) {
		String name = in.next().toUpperCase();
		String login1 = in.nextLine().trim().toUpperCase();
		in.nextLine();
		try {
			Iterator<Message> it = net.listGroupMessages(name, login1);
			printMessages(it);
		} catch (GroupNotExists e) {
			System.out.println(GROUP_NOT_EXISTS);
		} catch (UserNotExists e) {
			System.out.println(USER_NOT_EXISTS);
		} catch (SubscriptionNotExists e) {
			System.out.println(SUBSCRIPTION_NOT_EXISTS);
		} catch (NoGroupMessages e) {
			System.out.println(NO_GROUP_MESSAGES);
		}
	}

	private static void printMessages(Iterator<Message> messages) {
		while (messages.hasNext()) {
			Message m = messages.next();
			System.out.printf("%s\n%s\n%s\n", m.getTitle(), m.getText(), m.getURL());
			//if (messages.hasNext())
			System.out.println();
		}	
	}

}
