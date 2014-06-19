package betsim.model;
import java.io.File;
import java.util.List;

public class InputableBettableEvent implements BettableEvent{

	private List<Item> bettableItems;
	private String eventTitle;
	
	public InputableBettableEvent(String filePath) throws IllegalInputException{
		
		File file = new File(filePath);
		
		this.eventTitle = EventParser.getEventTitle(file);
		this.bettableItems = EventParser.getItemsList(file);
	}
	
	@Override
	public List<Item> bettableItems() {
		return this.bettableItems;
	}

	@Override
	public String eventTitle() {
		return this.eventTitle;
	}

}
