package application;

import java.util.ArrayList;

public class Order implements Customizable {

	public static int lineNumber; // reset for each new order
	private ArrayList<OrderLine> orderlines;
	
	@Override
	public boolean add(Object obj) {
		
		// Come back later to reconsider when adding an orderline might fail
		
		int temp = orderlines.size();
		
		orderlines.add((OrderLine) obj);
		
		
		if (orderlines.size() == temp) {
			return false;
		}
		
		return true;
	}

	
	@Override
	public boolean remove(Object obj) {
		
		int temp = orderlines.size();
		
		return false;
	}
	
}
