package betsim.model;
import java.util.List;

public interface BettableEvent {
	public List<Item> bettableItems();
	public String eventTitle();
}
