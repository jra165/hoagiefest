package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	private Extra(String value) {
		this.value = value;
	}
	
	public static ArrayList<String> getValues() {
		//VALUES = new ArrayList<>();
		for(Extra e : Extra.values()) {
			VALUES.add(e.value);
		}
		return VALUES;
	}
	

	
}
