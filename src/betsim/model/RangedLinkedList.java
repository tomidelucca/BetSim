package betsim.model;
import java.util.LinkedList;

public class RangedLinkedList<T> extends LinkedList<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long maxSize;
	
	public RangedLinkedList(Long maxSize){
		super();
		setMaxSize(maxSize);
	}
	
	@Override
	public void push(T e){
		super.push(e);
		if(size()>getMaxSize()){
			removeLast();
		}
	}

	public Long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}

}
