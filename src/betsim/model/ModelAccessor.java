package betsim.model;
//
//  Created by Tomi De Lucca on 29/05/2014.
//  Copyright (c) 2014 Tomi De Lucca. All rights reserved.
//

import java.util.List;
import java.util.Map;

public interface ModelAccessor {
	
	public boolean isSimulationRunning();
	
	public List<Event> getEventList();
	public String getEventTitleForEvent(Event e);
	
	public List<Item> getItemListForEvent(Event e);
	
	public List<String> getActionsPerformedForEvent(Event e);
	
	public String getCurrentPricesForItem(Item i);
	
	public Map<Option, List<Double>> getItemBetPriceHistoryForItem(Item i);
	
	public BettingCentral getBettingCentral();
}


