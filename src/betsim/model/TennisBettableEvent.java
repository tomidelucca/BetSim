package betsim.model;
import java.util.ArrayList;
import java.util.List;

public class TennisBettableEvent implements BettableEvent {

	@Override
	public List<Item> bettableItems() {
		
		List<Item> iList = new ArrayList<Item>();
		
		Option o1 = new Option("Gana 1", 2.0);
		Option o2 = new Option("Gana 2", 1.87);
		
		List<Option> oList = new ArrayList<Option>();
		oList.add(o1); oList.add(o2);
		
		Item i1 = new Item("Resultado", oList);
		iList.add(i1);
		
		o1 = new Option("< 4.5", 6.04);
		o2 = new Option("> 4.5", 3.50);		
		
		oList = new ArrayList<Option>();
		oList.add(o1); oList.add(o2);
		
		i1 = new Item("Cantidad de Sets", oList);
		iList.add(i1);
		
		return iList;
	}

	@Override
	public String eventTitle() {
		return "Federer vs. Nadal";
	}
	
	

}
