package betsim.model;
public class UserManager {
	
	private static Long identifier;
	private static UserManager manager;

	private UserManager(){
		UserManager.setIdentifier(0L);
	}
	
	public static UserManager getInstance(){
		if(manager == null){
			manager = new UserManager();
		}
		return manager;
	}

	public User createNewUser(String name){
		User u = new User(name, UserManager.identifier++);
		return u;
	}

	private static void setIdentifier(Long identifier) {
		UserManager.identifier = identifier;
	}
		
}
