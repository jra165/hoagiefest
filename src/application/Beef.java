package application;

/**
 * Beef is a subclass of Sandwich with the properties and methods associated with the Beef object
 * Properties and methods inherited from the Sandwich class and Customizable interface
 * Additional methods include price
 * @author Joshua Atienza, Kyle lee
 */
public class Beef extends Sandwich {

	//the base price of Beef
	private static final double BASE_PRICE = 10.99;		
	
	//the max number of extras that can be added
	private static final double MAX_EXTRAS = 6;
	
	/**
	 * Adds an extra ingredient to the Beef sandwich
	 * @param obj The object (extra) to be added to the Beef sandwich
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
	 * Removes an extra ingredient from the Beef sandwich
	 * @param obj The object (extra) to be removed from the Beef sandwich
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
	 * Calculates final price of Beef sandwich
	 * Formula is base price + number of extras*cost of individual extra
	 * @return finalPrice The overall price of the Beef sandwich
	 */
	@Override
	public double price() {
		
		double finalPrice = BASE_PRICE + extras.size()*PER_EXTRA;
		return finalPrice;
		
	}

	/**
	 * Converts Beef sandwich to its String representation
	 * @return sandwichOrder The String representation of the Beef sandwich
	 */
	@Override
	public String toString() {
		
		StringBuilder extraIngredients = new StringBuilder();
		String sandwichOrder;
		
		for (int i = 0; i < extras.size(); i++) {
			String ingredient  = extras.get(i).toString(); 
			ingredient = ingredient.substring(0,1).toUpperCase() + ingredient.substring(1).toLowerCase();
			extraIngredients.append(ingredient);
			extraIngredients.append(", ");
		}
		
		double priceItem = Double.valueOf(String.format("%2f", price()));
		
		if (extras.size() > 0) {
			sandwichOrder = "Beef " + super.toString() + 
					" Roast Beef, Provolone Cheese, Mustard, Extra: " + 
					extraIngredients + "Price $" + priceItem;
		}
		else {
			extraIngredients.append("None, ");
			sandwichOrder = "Beef " + super.toString() + 
			" Roast Beef, Provolone Cheese, Mustard, Extra: " + 
			extraIngredients + "Price $" + priceItem;
			
		}
		
		return sandwichOrder;
		
	}

}
