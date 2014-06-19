package betsim.model;
import java.util.ArrayList;
import java.util.List;


public class User {
	private String username;
	private Long indentifier;
	private Double availableCash;
	private List<Bet> betList;

	public User(String name, Long identifier){
		setIndentifier(identifier);
		setUsername(name);
		setAvailableCash(0.0);
		setBetList(new ArrayList<Bet>());
	}
	
	public Double getAvailableCash() {
		return availableCash;
	}

	public void setAvailableCash(Double availableCash) {
		this.availableCash = availableCash;
	}

	public List<Bet> getBetList() {
		return betList;
	}

	public void setBetList(List<Bet> betList) {
		this.betList = betList;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getIndentifier() {
		return indentifier;
	}

	public void setIndentifier(Long indentifier) {
		this.indentifier = indentifier;
	}
	
	public void addCash(Double amount){
		setAvailableCash(getAvailableCash()+amount);
	}
	
	public void substractCash(Double amount){
		if ((getAvailableCash() - amount) < 0)
		{
			throw new InsufficientFundsException("Insufficient funds");
		}
		
		setAvailableCash(getAvailableCash()-amount);
	}
	
	public String toString(){
		return getIndentifier().toString();
	}
	
	public void bets(Bet bet) throws InsufficientFundsException{
			this.substractCash(bet.getBetAmount());
			betList.add(bet);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((indentifier == null) ? 0 : indentifier.hashCode());
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
		User other = (User) obj;
		if (indentifier == null) {
			if (other.indentifier != null)
				return false;
		} else if (!indentifier.equals(other.indentifier))
			return false;
		return true;
	}

}
