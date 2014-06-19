package betsim.model;

public class Path {

	private Item item;
	private Option option;
	
	public Path(Item item, Option option){
		setItem(item);
		setOption(option);
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public Option getOption() {
		return option;
	}
	
	public void setOption(Option option) {
		this.option = option;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((option == null) ? 0 : option.hashCode());
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
		Path other = (Path) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (option == null) {
			if (other.option != null)
				return false;
		} else if (!option.equals(other.option))
			return false;
		return true;
	}
	
	public String toString(){
		return "[item=" + item.toString() + ",option=" + option.toString()+"]";
	}
		
}
