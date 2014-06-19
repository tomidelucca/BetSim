package betsim.model;

public class Option {
	
	private String description;
	private Double currentPrice;
	private Double totalBetsAmount;
	
	public Option(String description){
		setDescription(description);
		setCurrentPrice(1.5);
		setTotalBetsAmount(0.0);
	}

	public Option(String description, Double startingPrice){
		setDescription(description);
		setCurrentPrice(startingPrice);
		setTotalBetsAmount(0.0);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}
	
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	public Double getTotalBetsAmount() {
		return totalBetsAmount;
	}
	
	public void setTotalBetsAmount(Double totalBetsAmount) {
		this.totalBetsAmount = totalBetsAmount;
	}
	
	public String toString(){

		return getDescription();
	}
	
	public String print(){
		return getDescription() + " | Paga: " + getCurrentPrice();
	}
	
	public void isSelectedForBet(Bet bet){
		Double amount = bet.getBetAmount();
		setTotalBetsAmount(getTotalBetsAmount()+amount);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
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
		Option other = (Option) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
	
}
