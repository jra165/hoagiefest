package application;

/**
 * OrderLine class contains the properties and methods associated with OrderLine object.
 * OrderLine represents an individual sandwich order, with its associated line number, ingredients and price
 * Properties are lineNumber, sandwich, and price
 * Methods include getSandwich, getPrice, setLineNumber, adjustLineNumber, toString
 * @author Joshua Atienza, Kyle Lee
 */
public class OrderLine {
	private int lineNumber; // a serial number created when a sandwich is added to the order
	private Sandwich sandwich;
	private double price;
	
	/**
	 * Creates an OrderLine object with its associated linNumber, sandwich, and price
	 * @param lineNumber The line number of the OrderLine
	 * @param sandwich The specific Sandwich associated with the OrderLine
	 * @param price The total price of the OrderLine
	 */
	public OrderLine(int lineNumber, Sandwich sandwich, double price) {
		this.lineNumber = lineNumber;
		this.sandwich = sandwich;
		this.price = price;
	}
	
	/**
	 * Gets the Sandwich associated with OrderLine
	 * @return this.sandwich The Sandwich associated with the OrderLine
	 */
	public Sandwich getSandwich() {
		return this.sandwich;
	}
	
	/**
	 * Gets the price associated with OrderLine
	 * @return price The price associated with the OrderLine
	 */
	public double getPrice() {		
		return price;
	}
	
	/**
	 * Decrements line number by 1
	 */
	public void adjustLineNumber() {
		this.lineNumber = this.lineNumber-1;
	}
	
	/**
	 * Converts OrderLine to its String representation
	 * @return output The String representation of OrderLine object
	 */
	@Override
	public String toString() {
		String output = String.valueOf(lineNumber+1) + " " + sandwich.toString();
		return output;
	}
	
}
