package application;

/**
 * Customizable is the interface that will be implemented by Sandwich, OrderLine, Order class
 * Boolean methods, add and remove, are implemented in Beef, Chicken, Fish
 * @author Joshua Atienza, Kyle Lee
 */
public interface Customizable {
	
	/**
	 * Adds an object
	 * Implementation in Beef, Chicken, Fish classes
	 * @param obj The object to be added
	 * @return true if object successfully added, false otherwise
	 */
	boolean add(Object obj);
	
	/**
	 * Removes an object
	 * Implementation in Beef, Chicken, Fish classes
	 * @param obj The object to be removed
	 * @return true if object successfully removed, false otherwise
	 */
	boolean remove(Object obj);
	
}
