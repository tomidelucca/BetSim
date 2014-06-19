package betsim.model;
import java.util.ArrayList;
import java.util.List;


public class SoccerBettableEvent implements BettableEvent{

	@Override
	public List<Item> bettableItems() {
		
		List<Item> items = new ArrayList<Item>();
		
		Option o1 = new Option("Gana Local");
		Option o2 = new Option("Empate");
		Option o3 = new Option("Gana Visitante");
		
		ArrayList<Option> a1 = new ArrayList<Option>();
		a1.add(o1); a1.add(o2); a1.add(o3);
		
		Item i1 = new Item("Resultado", a1);
		
		items.add(i1);
		
		o1 = new Option("< 1.5");
		o2 = new Option("> 1.5");
		
		a1 = new ArrayList<Option>();
		a1.add(o1); a1.add(o2);
		
		i1 = new Item("Cantidad de goles", a1);
		
		items.add(i1);
		
		return items;
	}
	
	@Override
	public String eventTitle() {
		return "River vs. Quilmes";
	}

}
