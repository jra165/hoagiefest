package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OrderScreenController {
	
	List<String> ingredients = Extra.getValues();
	ArrayList<String> addOns = new ArrayList<String>();
	
	ObservableList<String> sandwichTypeList = FXCollections.observableArrayList
			("Chicken", "Fish", "Beef");
	String chickenIngredients = "Fried Chicken\nSpicy Sauce\nPickles";
	String beefIngredients = "Roast Beef\nProvolone Cheese\nMustard";
	String fishIngredients = "Grilled Snapper\nCilantro\nLime";
	ObservableList<String> ingredientList = FXCollections.observableArrayList
			(ingredients);
	ObservableList<String> addOnList = FXCollections.observableArrayList(addOns);
	
    @FXML
    private ComboBox<String> sandwichType;

    @FXML
    private TextArea basicIngredients;
    
    @FXML
    private ImageView sandwichImage;

    @FXML
    private ListView<String> ingredientSelect;

    @FXML
    private ListView<String> extraIngredientDisplay;

    @FXML
    private Button addButton1;

    @FXML
    private Button removeButton;

    @FXML
    private Button clear;

    @FXML
    private Button orderButton;

    @FXML
    private TextField priceDisplay;
    
    @FXML
    private TextArea debugArea;
    
    Order order = new Order();
        
    
    //find out if this should be private
    @FXML
    private void initialize() {
    	
    	final double DEFAULT_PRICE = 8.99;
    	
    	sandwichType.setValue("Chicken");
    	sandwichType.setItems(sandwichTypeList);
    	priceDisplay.setText(String.valueOf(DEFAULT_PRICE));
    	
    	basicIngredients.setText(chickenIngredients);
    	basicIngredients.setDisable(true);
    	
    	ingredientSelect.setItems(ingredientList);
    	
    	
    	
    }
 
    //add will move an ingredient from left to right, increase price
    @FXML
    void addIngredient(ActionEvent event) {
    	
    	String sandwichMeat = sandwichType.getValue();
    	String itemToAdd = ingredientSelect.getSelectionModel().getSelectedItem();
    	
    	
    	switch(sandwichMeat) {
    	case "Chicken":
    		Chicken chicken = new Chicken();
 
    		for(Extra e : Extra.values()) {
    			
    			if(e.toString().equals(itemToAdd)) {
    				chicken.add(e);
    			}
    			
    		}
    		
    		ingredientList.remove(itemToAdd);
    		addOnList.add(itemToAdd);
    		priceDisplay.setText(String.valueOf(chicken.price()));
    	
    	case "Fish":
    		Fish fish = new Fish();
    		
    		for(Extra e : Extra.values()) {
    			
    			if(e.toString().equals(itemToAdd)) {
    				fish.add(e);
    			}
    			
    		}
    		
    		fish.add(itemToAdd);
    		ingredientList.remove(itemToAdd);
    		addOnList.add(itemToAdd);
    		priceDisplay.setText(String.valueOf(fish.price()));
    		
    	case "Beef":
    		Beef beef = new Beef();
    		
    		for(Extra e : Extra.values()) {
    			
    			if(e.toString().equals(itemToAdd)) {
    				beef.add(e);
    			}
    			
    		}
    		
    		beef.add(itemToAdd);
    		ingredientList.remove(itemToAdd);
    		addOnList.add(itemToAdd);
    		priceDisplay.setText(String.valueOf(beef.price()));
    		
    	}
  	
    }


    @FXML
    void addOrder(ActionEvent event) {
    	
    	String sandwichMeat = sandwichType.getValue();
    	
    	switch(sandwichMeat) {
    	case "Chicken":
    		Chicken chicken = new Chicken();
    		OrderLine chicken_orderline = new OrderLine(order.getLineNumber(), chicken, chicken.price());
    		order.add(chicken_orderline);
    	
    	case "Fish":
    		Fish fish = new Fish();
    		OrderLine fish_orderline = new OrderLine(order.getLineNumber(), fish, fish.price());
    		order.add(fish_orderline);
    		
    	case "Beef":
    		Beef beef = new Beef();
    		OrderLine beef_orderline = new OrderLine(order.getLineNumber(), beef, beef.price());
    		order.add(beef_orderline);
    		
    	}

    }
    
    //clear will reset back to the defaults
    @FXML
    void clearSelections(ActionEvent event) {

    }

    //remove will move an ingredient from right to left, decrease price
    @FXML
    void removeIngredient(ActionEvent event) {

    }
    
    @FXML
    void changeSandwich(ActionEvent event) throws MalformedURLException, IOException {
    	 
    	String sandwichMeat = sandwichType.getValue();
    	
    	if(sandwichMeat.equals("Chicken")) { 
    		Chicken chicken = new Chicken();
    		priceDisplay.setText(String.valueOf(chicken.price()));
    		basicIngredients.setText(chickenIngredients);
    		
    		InputStream sandwichLink = new URL("https://www.cfacdn.com/img/order/COM/Menu_Refresh/Sides/Sides%20PDP/_0000s_0013_Final__0052_CFA_PDP_Spicy-Chick-Fil-A-Sandwich_1085.png").openStream();
    		Image chickenImage = new Image(sandwichLink);
    		sandwichImage.setImage(chickenImage);
    	
    	}
    	
    	else if(sandwichMeat.equals("Fish")) {
    		Fish fish = new Fish();
    		priceDisplay.setText(String.valueOf(fish.price()));
    		basicIngredients.setText(fishIngredients);
    		
    		InputStream sandwichLink = new URL("https://mcdonalds.eg/Cms_Data/Contents/En/Media/images/Menu/large-Image/Filet-O-Fish.png").openStream();
    		Image fishImage = new Image(sandwichLink);
    		sandwichImage.setImage(fishImage);
    		
    		
    	}
    	
    	else {
    		
    		Beef beef = new Beef();
    		priceDisplay.setText(String.valueOf(beef.price()));
    		basicIngredients.setText(beefIngredients);
    		
    		InputStream sandwichLink = new URL("https://arbysrva.com/wp-content/uploads/RoastBeef_Classic.png").openStream();
    		Image beefImage = new Image(sandwichLink);
    		sandwichImage.setImage(beefImage);
    		
    	}	

    }
    
    @FXML
    void showOrder(ActionEvent event) {
    	
    
    }


}
