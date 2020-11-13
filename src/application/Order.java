package application;

import java.util.ArrayList;

public class Order implements Customizable {

	public static int lineNumber = 0; // reset for each new order
	private ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
	
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public ArrayList<OrderLine> getOrderlines() {
		return orderlines;
	}
	
	
	public void resetLineNumber() {
		lineNumber = 0;
	}
	
	public void resetOrderLines() {
		orderlines.clear();
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
			orderlines.get(i).adjustLineNumber();		// sets the line number of the orderline to the current - 1
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
		
		System.out.println("LINE #: " + lineNumber);
		System.out.println("ORDERLINE LEN #: " + orderlines.size());
		
		if(lineNumber > 0) {
			
			for(int i = 0; i < lineNumber; i++) {
				System.out.println(orderlines.get(i).toString());
				output.add(orderlines.get(i).toString());
				
			}
			
		}
		
		
		return output;
		
		
	}



	
}
