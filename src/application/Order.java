package application;

import java.util.ArrayList;

public class Order implements Customizable {

	public static int lineNumber; // reset for each new order
	private ArrayList<OrderLine> orderlines;
	
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	
	@Override
	public boolean add(Object obj) {
	
		// Come back later to reconsider when adding an orderline might fail
		
		int temp = orderlines.size();
		orderlines.add((OrderLine) obj);
		
		if (orderlines.size() > temp) {
			lineNumber++;
			return true;
		}
		
		
		return false;
	}

	
	@Override
	public boolean remove(Object obj) {
		
		int temp = orderlines.size();
	
		int index = orderlines.indexOf(obj);
		orderlines.remove(obj);
		
		
		// for loop to renumber the orders based on where it is removed from
		for (int i = index; i < orderlines.size(); i++) {
			orderlines.get(i).setLineNumber();		// sets the line number of the orderline to the current - 1
		}
		
		if (orderlines.size() < temp) {
			lineNumber--;
			return true;
		}
	
		return false;
	}
	
	
	public double price() {
		
		double total = 0;
		
		for (int i = 0; i < orderlines.size(); i++) {
			total += orderlines.get(i).getPrice();
		}
		
		return total;
		
	}
	
	
}
