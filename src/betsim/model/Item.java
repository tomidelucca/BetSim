package betsim.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Item {
	private String description;
	private List<Option> options;
	private Map<Option,List<Double>>history;

	public Item(String description, List<Option> options){
		setDescription(description);
		setOptions(options);
		
		HashMap<Option, List<Double>> map = new HashMap<Option, List<Double>>();
		for(Option o : getOptions()){
			map.put(o, new ArrayList<Double>());
		}
		
		setHistory(map);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Option> getOptions() {
		return options;
	}
	
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
	public Map<Option, List<Double>> getHistory() {
		return history;
	}

	public void setHistory(HashMap<Option, List<Double>> map) {
		this.history = map;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
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
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		return true;
	}

	public String toString(){
		return getDescription();
	}
	
	public String print(){
		String s = "";
		
		for(Option o : options){
			Integer i = options.indexOf(o);
			s += i + ". " + o.print() + "\n";
		}
		
		return s;
	}
	
	public void isSelectedForBet(Bet bet){
		Path selectedPath = bet.getChosenPath();
		Option selectedOption = selectedPath.getOption();

		selectedOption.isSelectedForBet(bet);
		
		updatePrice(bet);
	}
	
	public void updatePrice(Bet bet){
		double totalAmountBettedToItem = 0.0;
		
		for(Option o : options){
			totalAmountBettedToItem += o.getTotalBetsAmount();
		}
		
		double percentage = bet.getBetAmount()/totalAmountBettedToItem;
		
		if(percentage!=1){
			for(Option o: options){
				if(bet.getChosenPath().getOption().equals(o)){
					double newValue = o.getCurrentPrice()*(1.0-percentage);
					newValue = (double) Math.round(newValue * 100);
					newValue = newValue/100;
					newValue = newValue<1?1.01:newValue;
					o.setCurrentPrice(newValue);
				}
				else {
					double newValue = o.getCurrentPrice()*(1.0+percentage);
					newValue = (double) Math.round(newValue * 100);
					newValue = newValue/100;
					newValue = newValue>25?24.9:newValue;
					o.setCurrentPrice(newValue);
				}
			}
		}
		
		registerCurrentOptionPrices();
	}
	
	public void registerCurrentOptionPrices(){

		for(Option o : getOptions()){
			List<Double> dataList = history.get(o);	
			if(dataList == null) dataList = new LinkedList<Double>();
			dataList.add(o.getCurrentPrice());
		}
	}
}
