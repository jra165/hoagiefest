package application;

public class Fish extends Sandwich {

	private static final double basePrice = 12.99;
	
	@Override
	public boolean add(Object obj) {
		
		// Adds extra ingredient into extras ArrayList
			
		//System.out.println("Enter1");
		if (extras.size() == 0) {
			extras.add((Extra) obj);
		}
		
		else if (extras.size() < 6) {
			//System.out.println("Enter2");
			// Loop to check if the ingredient already exists in the list
			for (int i = 0; i < extras.size(); i++) {
				//System.out.println("Enter3");
				if (!extras.get(i).equals(obj)) {
					extras.add((Extra) obj);
					return true;
				}
			}
			
		}
		
		
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		
		// Removes extra ingredient from extras ArrayList
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

	@Override
	public double price() {
		
		// Calculate final price of chicken sandwich which is defined as base price plus # extra ingredients times price per ingredient
		
		double finalPrice = basePrice + extras.size()*PER_EXTRA;
		
		
		return finalPrice;
		
	}

	@Override
	public String toString() {
		
		StringBuilder extraIngredients = new StringBuilder();
		
		for (int i = 0; i < extras.size(); i++) {
			extraIngredients.append(extras.get(i));
			extraIngredients.append(", ");
		}
		
		double priceItem = price();
		
		String sandwichOrder = "Fish " + super.toString() + " Grilled Snapper, Cilantro, Lime, Extra: " + extraIngredients + "Price $" + priceItem;
		
		return sandwichOrder;
		
	}

	
}
