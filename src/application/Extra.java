package application;

import java.util.ArrayList;

/**
 * Extra is the enum class of possible extras that can be added to a sandwich.
 * Possible enum values (extras) are shown below.
 * Methods include getValues
 * @author Joshua Atienza, Kyle Lee
 */
public enum Extra {
		
		TOMATOES("Tomatoes"),
		LETTUCE("Lettuce"),
		ONIONS("Onions"),
		MUSHROOMS("Mushrooms"),
		CUCUMBERS("Cucumbers"),
		OLIVES("Olives"),
		SALT("Salt"),
		PEPPER("Pepper"),
		TRUFFLES("Truffles"),
		GOLD("Gold");
	
	private static final ArrayList<String> VALUES = new ArrayList<String>();
	private final String value;
	
	
	/**
	 * Creates an Extra with its associated value
	 * @param value The value of the enum item
	 */
	private Extra(String value) {
		this.value = value;
	}
	
	
	/**
	 * Creates an ArrayList representation of each enum value
	 * @return VALUES The respective values of each enum value in ArrayList format
	 */
	public static ArrayList<String> getValues() {
	
		for(Extra e : Extra.values()) {
			VALUES.add(e.value);
		}
		return VALUES;
	}
	
}
