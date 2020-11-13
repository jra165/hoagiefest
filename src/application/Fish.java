package application;

/**
 * Fish is a subclass of Sandwich with the properties and methods associated with the Fish object
 * Properties and methods inherited from the Sandwich class and Customizable interface
 * Additional methods include price
 * @author Joshua Atienza, Kyle lee
 */
public class Fish extends Sandwich {

	//the base price of Fish
	private static final double BASE_PRICE = 12.99;
	
	//the max number of extras that can be added
	private static final double MAX_EXTRAS = 6;
	
	/**
	 * Adds an extra ingredient to the Fish sandwich
	 * @param obj The object (extra) to be added to the Fish sandwich
	 * @return true if extra successfully added, otherwise false
	 */
	@Override
	public boolean add(Object obj) {
					
		if (extras.size() == 0) {
			extras.add((Extra) obj);
		}
		
		else if (extras.size() < MAX_EXTRAS) {
			
			// Loop to check if the ingredient already exists in the list
			for (int i = 0; i < extras.size(); i++) {
				if (!extras.get(i).equals(obj)) {
					extras.add((Extra) obj);
					return true;
				}
			}
		}
		
		return false;
	}

	
	/**
	 * Removes an extra ingredient from the Fish sandwich
	 * @param obj The object (extra) to be removed from the Fish sandwich
	 * @return true if extra successfully removed, otherwise false
	 */
	@Override
	public boolean remove(Object obj) {
		
		if (extras.size() > 0) {
			
			// Loop to check if the ingredient exists in the list
			for (int i = 0; i < extras.size(); i++) {
				if (extras.get(i).equals(obj)) {
					extras.remove(obj);
					return true;
				}
			}
		}
		
		return false;
	}

	
	/**
	 * Calculates final price of Fish sandwich
	 * Formula is base price + number of extras*cost of individual extra
	 * @return finalPrice The overall price of the Fish sandwich
	 */
	@Override
	public double price() {
		
		double finalPrice = BASE_PRICE + extras.size()*PER_EXTRA;
		return finalPrice;
		
	}

	/**
	 * Converts Fish sandwich to its String representation
	 * @return sandwichOrder The String representation of the Fish sandwich
	 */
	@Override
	public String toString() {
		
		StringBuilder extraIngredients = new StringBuilder();
		
		for (int i = 0; i < extras.size(); i++) {
			extraIngredients.append(extras.get(i));
			extraIngredients.append(", ");
		}
		
		double priceItem = price();
		
		String sandwichOrder = "Fish " + super.toString() + 
				" Grilled Snapper, Cilantro, Lime, Extra: " + 
				extraIngredients + "Price $" + priceItem;
		
		return sandwichOrder;
		
	}

	
}
