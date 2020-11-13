package application;

import java.util.ArrayList;

/**
 * The Order class implements the Customizable interface.
 * Order class represents an order of sandwiches as an ArrayList of OrderLines.
 * Properties include lineNumber
 * Methods include getLineNumber, getOrderLines, resetLineNumber, restartOrderLines, add, remove
 * price, printOrder, toArrayList
 * @author Joshua Atienza, Kyle Lee
 *
 */
public class Order implements Customizable {

	public static int lineNumber = 0; // reset for each new order
	private ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
	
	/**
	 * Gets the line number of an item in orderlines
	 * @return lineNumber The line number of an OrderLine in orderlines
	 */
	public int getLineNumber() {
		return lineNumber;
	}
	
	
	/**
	 * Gets the orderlines in the Order object
	 * @return orderlines The orderlines within the specific Order
	 */
	public ArrayList<OrderLine> getOrderlines() {
		return orderlines;
	}
	
	
	/**
	 * Resets the line number of an order line
	 */
	public void resetLineNumber() {
		lineNumber = 0;
	}
	
	
	/**
	 * Clears the orderlines ArrayList
	 */
	public void resetOrderLines() {
		orderlines.clear();
	}
	
	
	/**
	 * Adds an object (OrderLine) to orderlines
	 * @param obj The obj being added to orderlines
	 * @return true if obj successfully added, false otherwise
	 */
	@Override
	public boolean add(Object obj) {
		
		int temp = orderlines.size();
		orderlines.add((OrderLine) obj);
		
		if (orderlines.size() > temp) {
			lineNumber++;
			return true;
		}
		
		
		return false;
		
	}

	
	/**
	 * Removes an object (OrderLine) from orderlines
	 * @param obj The obj being removed from orderlines
	 * @return true if obj successfully removed, false otherwise
	 */
	@Override
	public boolean remove(Object obj) {
		
		int temp = orderlines.size();
	
		int index = orderlines.indexOf(obj);
		orderlines.remove(obj);
		
		
		// renumber the orders based on where it is removed from
		for (int i = index; i < orderlines.size(); i++) {
			
			// sets the line number of the orderline to the current - 1
			orderlines.get(i).adjustLineNumber();
			
		}
		
		if (orderlines.size() < temp) {
			lineNumber--;
			return true;
		}
	
		return false;
	}
	
	
	/**
	 * Returns the total price of the overall order
	 * @return total The total price of all orderlines added together
	 */
	public double price() {
		
		double total = 0;
		
		for (int i = 0; i < orderlines.size(); i++) {
			total += orderlines.get(i).getPrice();
		}
		
		return total;
		
	}
	
	
	/**
	 * Prints the entire order
	 * @return output The String representation of orderlines
	 */
	public String printOrder() {
		
		StringBuilder output = new StringBuilder("");
		
		if(lineNumber > 0) {
			
			for(int i = 0; i < lineNumber; i++) {
				output.append(orderlines.get(i).toString());
				output.append("\n");
			}
			
		}
		
		else {
			output.append("Empty order.");
		}
		
		return output.toString();
		
	}
	
	/**
	 * Converts orderlines to an ArrayList<String> representation
	 * @return output The ArrayList<String> representation of orderlines
	 */
	public ArrayList<String> toArrayList(){
		
		ArrayList<String> output=new ArrayList<String>();
		
		if(lineNumber > 0) {
			for(int i = 0; i < lineNumber; i++) {
				output.add(orderlines.get(i).toString());
			}
		}
		
		return output;
		
	}
	
}
