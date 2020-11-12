package application;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

public class Order implements Customizable {

	public static int lineNumber = 0; // reset for each new order
	private ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
	
	
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
	
	public ArrayList<String> toArrayList(){
		
		ArrayList<String> output=new ArrayList<String>();
		
		if(lineNumber > 0) {
			
			for(int i = 0; i < lineNumber; i++) {
				
				output.add(orderlines.get(i).toString());
				
			}
			
		}
		
		else {
			
			output.add("Empty order.");
			
		}
		
		return output;
		
		
	}
	
	
}
