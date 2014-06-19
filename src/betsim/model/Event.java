package betsim.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
	
	private Date eventDate;
	private String eventName;
	private BettableEvent bettableEvent;
	private List<Bet> betsMade;
	
	public Event(BettableEvent bettableEvent){
		setEventName(bettableEvent.eventTitle());
		setBettableEvent(bettableEvent);
		setEventDate(new Date());
		setBetsMade(new ArrayList<Bet>());
	}
	
	public List<Bet> getBetsMade() {
		return betsMade;
	}

	public void setBetsMade(List<Bet> betsMade) {
		this.betsMade = betsMade;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public BettableEvent getBettableEvent() {
		return bettableEvent;
	}
	
	private void setBettableEvent(BettableEvent bettableEvent) {
		this.bettableEvent = bettableEvent;
	}
	
	public List<Item> getBettableItems() {
		return getBettableEvent().bettableItems();
	}
	
	public Double priceForPath (Path p){
		Option option = p.getOption();
		
		return option.getCurrentPrice();
	}
	
	public void isBetted(Bet bet){
		betsMade.add(bet);
	    Path p = bet.getChosenPath();
	    Item selectedItem = p.getItem();
	    
	    selectedItem.isSelectedForBet(bet);
	}

	public String print() {
		String s = getEventName() + "\n";
		
		for(Item i : getBettableItems()){
			Integer integer = getBettableItems().indexOf(i);
			s += integer + ". " + i.print() + "\n";
		}
		
		return s; 
	}
	
	public String toString(){
		return getEventName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((eventDate == null) ? 0 : eventDate.hashCode());
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
		Event other = (Event) obj;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		return true;
	}
	
	
}
