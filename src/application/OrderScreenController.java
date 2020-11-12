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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

public class OrderScreenController {
	
	ArrayList<String> ingredients = Extra.getValues();
	ArrayList<String> addOns = new ArrayList<String>();
	
	String chickenIngredients = "Fried Chicken\nSpicy Sauce\nPickles";
	String beefIngredients = "Roast Beef\nProvolone Cheese\nMustard";
	String fishIngredients = "Grilled Snapper\nCilantro\nLime";
	
	
	ObservableList<String> sandwichTypeList = FXCollections.observableArrayList("Chicken", "Fish", "Beef");
	ObservableList<String> ingredientList = FXCollections.observableArrayList(ingredients);
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
    
    public Order order = new Order();
    Sandwich sandwich;
    
    
    private void checkIngredientList(Sandwich sandwich, String ingredient, String action) {
    	
    	if (action.equals("add")) {
    		for(Extra e : Extra.values()) {
    			if(e.toString().equalsIgnoreCase(ingredient)) {
    				//System.out.println(e.toString());
    				sandwich.add(e);
    			}
    		}
    	}
    	else if (action.equals("remove")) {
    		for(Extra e : Extra.values()) {
    			if(e.toString().equalsIgnoreCase(ingredient)) {
    				//System.out.println(e.toString());
    				sandwich.remove(e);
    			}
    		}
    	}
    	
    }
    
    
    
    
    //find out if this should be private
    @FXML
    private void initialize() {
    	
    	//final double DEFAULT_PRICE = 8.99;
    	
    	sandwich = new Chicken();
    	
    	sandwichType.setValue("Chicken");
    	sandwichType.setItems(sandwichTypeList);
    	
    	priceDisplay.setText(String.valueOf(sandwich.price()));
    	priceDisplay.setEditable(false);
    	
    	basicIngredients.setText(chickenIngredients);
    	basicIngredients.setDisable(true);
    	
    	ingredientSelect.setItems(ingredientList);
    	extraIngredientDisplay.setItems(addOnList);
    	
    }
 
    //add will move an ingredient from left to right, increase price
    @FXML
    void addIngredient(ActionEvent event) {
    	
    	String itemToAdd = ingredientSelect.getSelectionModel().getSelectedItem();
    	
    	if (addOnList.size() < 6) {
    		checkIngredientList(sandwich, itemToAdd, "add");
    		ingredientList.remove(itemToAdd);
    		addOnList.add(itemToAdd);
    		
    		//double sandwichPrice = sandwich.price();
    		priceDisplay.setText(String.format("%.2f", sandwich.price()));

    	}
    	else {
    		debugArea.appendText("Maximum of 6 ingredients already added.\n");
    	}
  	
    }


    @FXML
    void addOrder(ActionEvent event) {
    	
    	System.out.println("INGREDIENTS ADDED: " + sandwich.extras.toString());
    	System.out.println("PRICE: " + sandwich.price());
    	
    	
    	OrderLine sandwich_orderline = new OrderLine(order.getLineNumber(), sandwich, sandwich.price());
    	order.add(sandwich_orderline);
    	
    	
    	// Clear Selection method
    	sandwich = new Chicken();
    	
    	sandwichType.setValue("Chicken");
    	sandwichType.setItems(sandwichTypeList);
    	priceDisplay.setText(String.valueOf(sandwich.price()));
    	
    	basicIngredients.setText(chickenIngredients);
    	basicIngredients.setDisable(true);
    	
    	ingredientList = FXCollections.observableArrayList(ingredients);
    	addOnList = FXCollections.observableArrayList(addOns);
    	
    	ingredientSelect.setItems(ingredientList);
    	extraIngredientDisplay.setItems(addOnList);
    	
    }
    
    //clear will reset back to the defaults
    @FXML
    void clearSelections(ActionEvent event) {
    	sandwich = new Chicken();
    	
    	sandwichType.setValue("Chicken");
    	sandwichType.setItems(sandwichTypeList);
    	priceDisplay.setText(String.valueOf(sandwich.price()));
    	
    	basicIngredients.setText(chickenIngredients);
    	basicIngredients.setDisable(true);
    	
    	ingredientList = FXCollections.observableArrayList(ingredients);
    	addOnList = FXCollections.observableArrayList(addOns);
    	
    	ingredientSelect.setItems(ingredientList);
    	extraIngredientDisplay.setItems(addOnList);
    	
    	
    }

    //remove will move an ingredient from right to left, decrease price
    @FXML
    void removeIngredient(ActionEvent event) {
    	String itemToRemove = extraIngredientDisplay.getSelectionModel().getSelectedItem();
    	
    	checkIngredientList(sandwich, itemToRemove, "remove");
    	addOnList.remove(itemToRemove);
    	ingredientList.add(itemToRemove);
    	
    	priceDisplay.setText(String.format("%.2f", sandwich.price()));
    	
    }
    
    @FXML
    void changeSandwich(ActionEvent event) throws MalformedURLException, IOException {
    	 
    	String sandwichMeat = sandwichType.getValue();
    	
    	if(sandwichMeat.equals("Chicken")) { 
    		sandwich = new Chicken();
    		priceDisplay.setText(String.valueOf(sandwich.price()));
    		basicIngredients.setText(chickenIngredients);
    		
        	ingredientList = FXCollections.observableArrayList(ingredients);
        	addOnList = FXCollections.observableArrayList(addOns);
        	
        	ingredientSelect.setItems(ingredientList);
        	extraIngredientDisplay.setItems(addOnList);
    		
    		InputStream sandwichLink = new URL("https://www.cfacdn.com/img/order/COM/Menu_Refresh/Sides/Sides%20PDP/_0000s_0013_Final__0052_CFA_PDP_Spicy-Chick-Fil-A-Sandwich_1085.png").openStream();
    		Image chickenImage = new Image(sandwichLink);
    		sandwichImage.setImage(chickenImage);
    	
    	}
    	
    	else if(sandwichMeat.equals("Fish")) {
    		sandwich = new Fish();
    		priceDisplay.setText(String.valueOf(sandwich.price()));
    		basicIngredients.setText(fishIngredients);
    		
        	ingredientList = FXCollections.observableArrayList(ingredients);
        	addOnList = FXCollections.observableArrayList(addOns);
        	
        	ingredientSelect.setItems(ingredientList);
        	extraIngredientDisplay.setItems(addOnList);
    		
    		InputStream sandwichLink = new URL("https://mcdonalds.eg/Cms_Data/Contents/En/Media/images/Menu/large-Image/Filet-O-Fish.png").openStream();
    		Image fishImage = new Image(sandwichLink);
    		sandwichImage.setImage(fishImage);
    		
    		
    	}
    	
    	else if (sandwichMeat.equals("Beef")) {
    		
    		sandwich = new Beef();
    		priceDisplay.setText(String.valueOf(sandwich.price()));
    		basicIngredients.setText(beefIngredients);
    		
        	ingredientList = FXCollections.observableArrayList(ingredients);
        	addOnList = FXCollections.observableArrayList(addOns);
        	
        	ingredientSelect.setItems(ingredientList);
        	extraIngredientDisplay.setItems(addOnList);
    		
    		InputStream sandwichLink = new URL("https://arbysrva.com/wp-content/uploads/RoastBeef_Classic.png").openStream();
    		Image beefImage = new Image(sandwichLink);
    		sandwichImage.setImage(beefImage);
    		
    	}	

    }
    
    @FXML
    void showOrder(ActionEvent event) {
    	
        try {
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderSummary.fxml"));
        	Parent root = loader.load();
        	
        	OrderSummaryController orderSummaryController = loader.getController();
        	orderSummaryController.setOrderScreenController(this);
        	//orderSummaryController.setListView(order.toArrayList());
        	
        	
        	Scene newScene = new Scene(root);
        	Stage stage = (Stage)orderButton.getScene().getWindow();
        	
        	stage.setScene(newScene);
        	stage.show();
        	
        	
        } catch(IOException e) {
        	e.printStackTrace();
        }
    	
    }
    
    
 
}
