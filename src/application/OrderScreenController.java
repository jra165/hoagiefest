package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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


/**
 * The OrderScreenController is the class that integrates the logic of application into the UI
 * This class handles adding a sandwich and its respective extras to the order.
 * UI components shown below
 * Methods include checkIngredientList, setOrderSummaryController, initialize, addIngredient, 
 * addOrder, clearSelections, removeIngredient, changeSandwich, showOrder - all of which are described below
 * @author Joshua Atienza, Kyle Lee
 *
 */
public class OrderScreenController {
	
	ArrayList<String> ingredients = Extra.getValues();
	ArrayList<String> addOns = new ArrayList<String>();
	
	String chickenIngredients = "Fried Chicken\nSpicy Sauce\nPickles";
	String beefIngredients = "Roast Beef\nProvolone Cheese\nMustard";
	String fishIngredients = "Grilled Snapper\nCilantro\nLime";
	
	
	ObservableList<String> sandwichTypeList = FXCollections.observableArrayList("Chicken", "Fish", "Beef");
	ObservableList<String> ingredientList = FXCollections.observableArrayList(ingredients);
	ObservableList<String> addOnList = FXCollections.observableArrayList(addOns);
	
    public Order order = new Order();
    Sandwich sandwich;
	
	
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
    
    
    /**
     * Compares ingredient with extras in Extra enum class and adds to sandwich accordingly
     * @param sandwich The sandwich taking on the extras
     * @param ingredient The ingredient being added to the sandwich
     * @param action The action of either adding or removing to/from the sandwich
     */
    private void checkIngredientList(Sandwich sandwich, String ingredient, String action) {
    	
    	if (action.equals("add")) {
    		for(Extra e : Extra.values()) {
    			if(e.toString().equalsIgnoreCase(ingredient)) {
    				sandwich.add(e);
    			}
    		}
    	}
    	else if (action.equals("remove")) {
    		for(Extra e : Extra.values()) {
    			if(e.toString().equalsIgnoreCase(ingredient)) {
    				sandwich.remove(e);
    			}
    		}
    	}
    	
    }
    
    
    /**
     * Passes the Order object between the OrderSummaryController and OrderScreenController
     * @param orderSummaryController The controller that handles the OrderSummary scene
     */
    void setOrderSummaryController(OrderSummaryController orderSummaryController) {
    	order = orderSummaryController.order;
    }
    
    
    
    /**
     * Initializes the OrderScreen to its default settings
     */
    @FXML
    private void initialize() {
    	
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
 
    

    /**
     * Adds a selected extra ingredient to the sandwich, increases price
     * @param event The semantic event that indicates a user clicked 'Add>>'
     */
    @FXML
    void addIngredient(ActionEvent event) {
    	
    	String itemToAdd = ingredientSelect.getSelectionModel().getSelectedItem();
    	final double MAX_INGREDIENTS = 6;
    	
    	if (itemToAdd != null) {
    		if (addOnList.size() < MAX_INGREDIENTS) {
        		checkIngredientList(sandwich, itemToAdd, "add");
        		ingredientList.remove(itemToAdd);
        		addOnList.add(itemToAdd);
        		priceDisplay.setText(String.format("%.2f", sandwich.price()));

        	}
        	else {
        		debugArea.appendText("Maximum of 6 ingredients already added.\n");
        	}
    	}
    	
    }


    /**
     * Adds a sandwich to a new OrderLine, and then adds that OrderLine to the Order
     * @param event The semantic event that indicates a user clicked 'Add to Order'
     */
    @FXML
    void addOrder(ActionEvent event) {
     	
    	OrderLine sandwich_orderline = new OrderLine(order.getLineNumber(), sandwich, sandwich.price());
    	order.add(sandwich_orderline);
    	
    	debugArea.appendText("Sandwich created and order line added to order.\n");
    	
    	//clears current sandwich selections
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
    
    
    /**
     * Clears the current selection and resets back to default settings
     * @param event The semantic event that indicates a user clicked 'Clear Selected'
     */
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

    
    /**
     * Moves an ingredient from right to left in the removal process, decrease price
     * @param event The semantic event that indicates a user clicked '<<Remove'
     */
    @FXML
    void removeIngredient(ActionEvent event) {
    	
    	String itemToRemove = extraIngredientDisplay.getSelectionModel().getSelectedItem();
    	
    	if (itemToRemove != null) {
    		checkIngredientList(sandwich, itemToRemove, "remove");
        	addOnList.remove(itemToRemove);
        	ingredientList.add(itemToRemove);
        	
        	priceDisplay.setText(String.format("%.2f", sandwich.price()));
    	}
    	
    	
    }
    
    
    /**
     * Changes the default ingredients, photo of each sandwich type when sandwich types are chosen
     * @param event The semantic event that indicates a user clicked either 'Chicken', 'Fish', or 'Beef'
     * @throws MalformedURLException The exception thrown if URL to image failed
     * @throws IOException The exception thrown if invalid input provided
     */
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
    
    
    /**
     * Switches scenes to the OrderSummary view
     * @param event The semantic event that indicates a user clicked 'Show Order'
     */
    @FXML
    void showOrder(ActionEvent event) {
    	
        try {
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderSummary.fxml"));
        	Parent root = loader.load();
        	
        	OrderSummaryController orderSummaryController = loader.getController();
        	orderSummaryController.setOrderScreenController(this);
        	
        	Scene newScene = new Scene(root);
        	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       
        	stage.setScene(newScene);
        	stage.show();
        	
        	
        } catch(IOException e) {
        	e.printStackTrace();
        }
    	
    }
    
    
 
}
