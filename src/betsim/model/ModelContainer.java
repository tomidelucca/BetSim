package betsim.model;
//
//  Created by Tomi De Lucca on 29/05/2014.
//  Copyright (c) 2014 Tomi De Lucca. All rights reserved.
//
import java.util.List;
import java.util.Map;

public class ModelContainer implements ModelAccessor{

	private BettingCentral bettingCentral;
	private Simulation simulation;
	
	public ModelContainer(Simulation simulation) {
		this.bettingCentral = new BettingCentral();
		this.simulation = simulation;
	}
	
	@Override
	public List<Event> getEventList() {
		return bettingCentral.getEventList();
	}

	@Override
	public String getEventTitleForEvent(Event e) {
		return e.getEventName();
	}

	@Override
	public List<Item> getItemListForEvent(Event e) {
		return e.getBettableItems();
	}

	@Override
	public List<String> getActionsPerformedForEvent(Event e) {
		return simulation.getEventsHistory();
	}

	@Override
	public boolean isSimulationRunning() {
		return simulation.isRunning();
	}

	@Override
	public Map<Option, List<Double>> getItemBetPriceHistoryForItem(Item i) {
		return i.getHistory();
	}

	@Override
	public String getCurrentPricesForItem(Item i) {
		return i.print();
	}

	@Override
	public BettingCentral getBettingCentral() {
		return this.bettingCentral;
	}

}


