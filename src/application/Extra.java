package application;

import java.util.ArrayList;

public enum Extra {

	TOMATOES("Tomatoes"),
	LETTUCE("Lettuce"),
	ONIONS("Onions"),
	BANANA_PEPPERS("Banana Peppers"),
	CUCUMBERS("Cucumbers"),
	OLIVES("Olives"),
	SALT("Salt"),
	PEPPER("Pepper"),
	TRUFFLES("Truffles"),
	GOLD("Gold");
	
	private static final ArrayList<String> VALUES = new ArrayList<String>();
	private final String value;
	
	
	private Extra(String value) {
		this.value = value;
	}
	
	public static ArrayList<String> getValues() {
		
		for(Extra e : Extra.values()) {
			VALUES.add(e.value);
		}
		
		return VALUES;
	}
	
	
}
