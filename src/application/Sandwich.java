package application;

import java.util.ArrayList;

/**
 * Sandwich class implements the Customizable interface.
 * Sandwich class contains the properties and methods associated with the Sandwich object.
 * Methods include toString and price, which is implemented in Beef, Chicken, Fish classes
 * @author Joshua Atienza, Kyle Lee
 */
public abstract class Sandwich implements Customizable {
	
	//maximum number of extras that can be added
	static final int MAX_EXTRAS = 6;
	
	//cost of an additional extra
	static final double PER_EXTRA = 1.99;
	protected ArrayList<Extra> extras = new ArrayList<Extra>();
	
	/**
	 * Abstract method named price
	 * Calculates the total price of a given sandwich
	 * Implementation specified in respective subclasses
	 * @return finalPrice The total price of the associated sandwich
	 */
	public abstract double price();
	
	/**
	 * Converts a Sandwich to its String representation
	 * @return sandwichOrder The String representation of Sandwich
	 */
	@Override
	public String toString() {
		String sandwichOrder = "Sandwich;";
		return sandwichOrder;
	}
	

}
