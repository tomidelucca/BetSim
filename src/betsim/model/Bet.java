package betsim.model;
public class Bet {

	private User user;
	private Event event;
	private Path chosenPath;
	private Double betAmount;
	private Double currentPrice;
	private boolean wasPaid;
	
	public Bet(User user, Event event, Path chosenPath, Double betAmount) {
		super();
		this.user = user;
		this.event = event;
		this.chosenPath = chosenPath;
		this.betAmount = betAmount;
		this.currentPrice = event.priceForPath(chosenPath);
		this.wasPaid = false;
	}
	
	public boolean isWasPaid() {
		return wasPaid;
	}

	public void setWasPaid(boolean wasPaid) {
		this.wasPaid = wasPaid;
	}

	public User getUser() {
		return user;
	}
	
	@Override
	public String toString(){
		String s = "";
		
		s += "User: " + getUser() + " | Event: " + getEvent() + " | Item: " + getChosenPath().getItem() + 
				" | Option: " + getChosenPath().getOption() + " | Amount: " + getBetAmount() + " | Pays: " + getCurrentPrice();
		
		return s;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Event getEvent() {
		return event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public Path getChosenPath() {
		return chosenPath;
	}
	
	public void setChosenPath(Path chosenPath) {
		this.chosenPath = chosenPath;
	}
	
	public Double getBetAmount() {
		return betAmount;
	}
	
	public void setBetAmount(Double betAmount) {
		this.betAmount = betAmount;
	}
	
	public Double getCurrentPrice() {
		return currentPrice;
	}
	
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chosenPath == null) ? 0 : chosenPath.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bet other = (Bet) obj;
		if (chosenPath == null) {
			if (other.chosenPath != null)
				return false;
		} else if (!chosenPath.equals(other.chosenPath))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
