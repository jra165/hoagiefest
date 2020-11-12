package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Extra {

		//String Tomatoes = "Tomatoes";
		//String Lettuce = "Lettuce";
		//String Onions = "Onions";
		
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
	
	private static final List<String> VALUES;
	private final String value;
	
	static {
		VALUES = new ArrayList<>();
		for(Extra e : Extra.values()) {
			VALUES.add(e.value);
		}
	}
	
	private Extra(String value) {
		this.value = value;
	}
	
	public static List<String> getValues() {
		return Collections.unmodifiableList(VALUES);
	}
	

	
}
