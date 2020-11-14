package application;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * The OrderTest class is the JUnit test class, where the all public methods are tested
 * Each test case asserts according to return type
 * @author Joshua Atienza, Kyle Lee
 *
 */
class OrderTest {

	/**
	 * Test method for {@link Order#getLineNumber()}
	 * Tests retrieving the line number of an Order object
	 */
	@Test
	void testGetLineNumber() {
		
		final double EXPECTED_LINE_NUMBER = 1;
		
		Order cfa = new Order();
		
		Chicken cs = new Chicken();
		OrderLine combo = new OrderLine(cfa.getLineNumber(),
				cs, cs.price());
		cfa.add(combo);
	
		//checks if expected line number and cfa's line number match
		assertEquals(EXPECTED_LINE_NUMBER, cfa.getLineNumber());
		
		cfa.resetOrderLines();
		
	}

	/**
	 * Test method for {@link Order#getOrderlines()}
	 * Tests if orderlines inside an Order object match the expected ArrayList of
	 * OrderLine objects
	 */
	@Test
	void testGetOrderlines() {
		
		Order deli = new Order();
		
		Chicken chicken = new Chicken();
		Beef beef = new Beef();
		Fish fish = new Fish();
		
		OrderLine orderline1 = new OrderLine(deli.getLineNumber(),
				chicken,chicken.price());
		OrderLine orderline2 = new OrderLine(deli.getLineNumber(), 
				beef, beef.price());
		OrderLine orderline3 = new OrderLine(deli.getLineNumber(), 
				fish, fish.price());
		
		deli.add(orderline1);
		deli.add(orderline2);
		deli.add(orderline3);
		
		//Expected ArrayList of orderlines
		ArrayList<OrderLine> expected_deli = new ArrayList<OrderLine>();
		expected_deli.add(orderline1);
		expected_deli.add(orderline2);
		expected_deli.add(orderline3);
		
		//checks if expected_deli and orderlines of deli object match
		assertEquals(expected_deli, deli.getOrderlines());
		
	}
	
	
	/**
	 * Test method for {@link Order#resetOrderLines()}
	 * Tests if an Order object is properly reset and cleared
	 */
	@Test
	void testResetOrderLines() {
		
		final double EXPECTED_RESET = 0;
		
		Order wendys = new Order();
		
		Beef dave = new Beef();
		OrderLine combo_og = new OrderLine(wendys.getLineNumber(),
				dave, dave.price());
		wendys.add(combo_og);
		
		wendys.resetOrderLines();
		
		//checks if Order's line number is 0 after a reset
		assertEquals(EXPECTED_RESET, wendys.getLineNumber());
		
		
	}

	/**
	 * Test method for {@link Order#add(Object))}.
	 * Tests possible test cases for adding an Object to Order object
	 */
	@Test
	void testAdd() {
		
		Order mcDonalds = new Order();
		
		Chicken mcChicken = new Chicken();
		OrderLine combo1 = new OrderLine(mcDonalds.getLineNumber(), 
				mcChicken, mcChicken.price());
		
		//check if OrderLine combo1 successfully added to mcDonalds Order
		assertTrue(mcDonalds.add(combo1));
		
		mcDonalds.resetOrderLines();
		
	}

	/**
	 * Test method for {@link Order#remove(Object))}.
	 * Tests possible test cases for removing an Object from Order object
	 */
	@Test
	void testRemove() {
		
		Order arbys = new Order();
		
		Beef rbs = new Beef();
		OrderLine combo2 = new OrderLine(arbys.getLineNumber(), 
				rbs, rbs.price());
	
		arbys.add(combo2);
		
		//checks if OrderLine combo2 successfully removed from arbys Order
		assertTrue(arbys.remove(combo2));
		
		arbys.resetOrderLines();
		
	}

	/**
	 * Test method for {@link Order#price()}.
	 * Tests possible test cases for returning price of sandwich
	 */
	@Test
	void testPrice() {
		
		final double EXPECTED_PRICE = 18.96;
		
		Order mickeyDees = new Order();
		
		Fish fof = new Fish();
		fof.add(Extra.TOMATOES);
		fof.add(Extra.CUCUMBERS);
		fof.add(Extra.TRUFFLES);
		
		OrderLine combo3 = new OrderLine(mickeyDees.getLineNumber(), 
				fof, fof.price());
		mickeyDees.add(combo3);
		
		//checks if expected total and actual price of mickeyDees Order match
		assertEquals(EXPECTED_PRICE, mickeyDees.price());
			
		mickeyDees.resetOrderLines();
		
	}
	
	
	/**
	 * Test method for {@link Order#printOrder()()}
	 * Tests if Order object is properly printed in String format
	 */
	@Test
	void testPrintOrder() {
		
		//Expected String
		String expectedString = "1 Chicken Sandwich; Fried Chicken, "
				+ "Spicy Sauce, Pickles, Extra: None, Price $8.99\n";
		
		Order popeyes = new Order();
		Chicken scs = new Chicken();
		OrderLine combo5 = new OrderLine(popeyes.getLineNumber(),
				scs, scs.price());
		popeyes.add(combo5);
		
		//checks if expected String and actual output match
		assertEquals(expectedString, popeyes.printOrder());
		
		popeyes.resetOrderLines();

	}

	
	/**
	 * Test method for {@link Order#toArrayList()()}
	 * Tests if Order object is properly converted to ArrayList String
	 * format
	 */
	@Test
	void testToArrayList() {
		
		//Expected ArrayList
		ArrayList<String> expected_receipt = new ArrayList<String>();
		expected_receipt.add("1 Fish Sandwich; Grilled Snapper, Cilantro, Lime, "
				+ "Extra: None, Price $12.99");
		expected_receipt.add("2 Chicken Sandwich; Fried Chicken, Spicy Sauce, "
				+ "Pickles, Extra: None, Price $8.99");
		
		Order bodega = new Order();
		
		Fish mahi = new Fish();
		OrderLine mahiCombo = new OrderLine(bodega.getLineNumber(),
				mahi, mahi.price());
		bodega.add(mahiCombo);
		
		Chicken parm = new Chicken();
		OrderLine parmCombo = new OrderLine(bodega.getLineNumber(),
				parm, parm.price());
		bodega.add(parmCombo);
		
		//Checks if expected ArrayList of orders matches actual output
		assertEquals(expected_receipt, bodega.toArrayList());
		
		
		
	}

}
