package Algorithm;

import java.io.Serializable;


public class Action implements Serializable{

	String description;
	private double price = 10;
	
	public Action(String description) {
		this.description=description;
	}
	
	@Override
	public int hashCode(){
		return description.hashCode();
	}
	
	@Override
	public String toString(){
		return description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
