package Controller;

import javax.servlet.http.*;
import java.util.*;

public class SessionTracker implements HttpSessionListener, HttpSessionAttributeListener {
	private static int sessionCount = 0;
	public static ArrayList<String> sessionUsers = new ArrayList<String>();
	static {
		System.out.println("Listener Started!");
	}

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		System.out.println("\nNew session created");
		// HttpSession session = sessionEvent.getSession();
		// // System.out.println("Session Created!");
		// System.out.println("Session created: " + (String)
		// session.getAttribute("username"));
		// String username = (String) session.getAttribute("username");
		sessionCount++;
		// sessionUsers.add(username);
		// System.out.println("ArrayList is: " + sessionUsers);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		sessionCount--;
		System.out.println("Session Destroyed!");
		// HttpSession session = sessionEvent.getSession();
		// String username = (String) session.getAttribute("username");
		// sessionUsers.remove(username);
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		sessionUsers.add((String) event.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		sessionUsers.remove((String) event.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// sessionUsers.remove((String) event.getValue());
		;
	}

	public static String[] getSessionArray() {
		// return sessionCount;

		System.out.println("Count: " + sessionCount);
		String[] activeUsersArray = new String[sessionUsers.size()];
		activeUsersArray = sessionUsers.toArray(activeUsersArray);
		return activeUsersArray;
	}

}