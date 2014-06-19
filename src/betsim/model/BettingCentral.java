package betsim.model;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BettingCentral {
	
	private List<Event> eventList;
	private List<User> userList;
	
	public BettingCentral(){
		setEventList(new ArrayList<Event>());
		setUserList(new ArrayList<User>());
	}
	
	public void placeBet (User user, Event event, Double amount, Path path) throws InsufficientFundsException{
		Bet bet = new Bet(user, event, path, amount);	
		user.bets(bet);
		event.isBetted(bet);
	}
	
	public void pay(String filePath, Event event){
		try {
			Result eventResult = new Result(filePath, event);
			
			String result = "";
			
			for(Bet b : event.getBetsMade()){
				if(!b.isWasPaid()){
					if(eventResult.isWinningPath(b.getChosenPath())){
						Double winnings = b.getBetAmount()*b.getCurrentPrice();
						winnings = (double) Math.round(winnings * 100);
						winnings = winnings/100;
						result += b + " - Won: " + winnings + ". \n";
						b.getUser().addCash(winnings);
					} else {
						result += b + " - Lost.\n";
					}
					b.setWasPaid(true);
				}
			}
			PrintWriter out;
			try {
				out = new PrintWriter("files/output.txt");
				out.println(result);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IllegalInputException e) {
			System.out.println("The result file is invalid.");
		}
	}
	
	public void registerEvent(Event e){
		getEventList().add(e);
	}
	
	public void registerUser(User u){
		getUserList().add(u);
	}

	public List<Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
