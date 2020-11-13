package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;


/**
 * The OrderSummaryController is the class that integrates the logic of application into the UI
 * This class handles duplicating an OrderLine, removing them, and saving order to text file.
 * UI components shown below
 * Methods include setOrderScreenController, addSameOrder, clearOrder, removeOrder, returnOrder, saveOrder
 * @author Joshua Atienza, Kyle Lee
 *
 */
public class OrderSummaryController {

    Order order = new Order();
	
    @FXML
    private ListView<String> orderTextArea;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField priceTextField;

    @FXML
    private Button saveButton;

    
    /**
     * Passes the Order object between OrderScreenController and OrderSummaryController
     * @param orderScreenController The controller that handles the OrderScreen scene
     */
    void setOrderScreenController(OrderScreenController orderScreenController) {
    	order = orderScreenController.order;    	
    	ObservableList<String> orderLinesList = FXCollections.observableArrayList(order.toArrayList());
    	orderTextArea.setItems(orderLinesList);
    	priceTextField.setEditable(false);
    	priceTextField.setText(String.format("%.2f", order.price()));
    }
    
    
    /**
     * Adds a new OrderLine to the order with the same sandwich, ingredients, and price as the selected
     * @param event The semantic event that indicates a user clicked '+ Same Order Line'
     */
    @FXML
    void addSameOrder(ActionEvent event) {
    	
    	try {
    		String currOrderLine = orderTextArea.getSelectionModel().getSelectedItem();
        	
        	
        	String[] currOrderLineArr = currOrderLine.split("\\s+");
        	int index = Integer.valueOf(currOrderLineArr[0]);
        	
        	for (int i = 0; i < order.getOrderlines().size(); i++) {
        		if (i+1 == index) {
        		
        			OrderLine target = order.getOrderlines().get(i);
        			OrderLine sandwich_orderline = new OrderLine(order.getLineNumber(), 
        					target.getSandwich(), target.getPrice());
        			order.add(sandwich_orderline);
        					
        		}
        		
        	}
        	
    		
    		
    		ObservableList<String> orderLinesList = FXCollections.observableArrayList(order.toArrayList());
        	orderTextArea.setItems(orderLinesList);
        	priceTextField.setText(String.format("%.2f", order.price()));
        	
    	}
    	catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!!");
			alert.setHeaderText("Null Pointer Exception");
			alert.setContentText("Please select an order line.");
			alert.showAndWait();
    	}
    	
    	
    }

    
    /**
     * Clears the entire order and removes all order lines
     * @param event The semantic event that indicates a user clicked 'Clear Order'
     */
    @FXML
    void clearOrder(ActionEvent event) {
    	
    	order.resetLineNumber();
    	order.resetOrderLines();
  
		ObservableList<String> orderLinesList = FXCollections.observableArrayList(order.toArrayList());
    	orderTextArea.setItems(orderLinesList);
    	priceTextField.clear();

    }

    
    /** 
     * Removes a selected order line from the order
     * @param event The semantic event that indicates a user clicked '- Remove Order Line'
     */
    @FXML
    void removeOrder(ActionEvent event) {
    		
    	try {
    		String currOrderLine = orderTextArea.getSelectionModel().getSelectedItem();
        	String[] currOrderLineArr = currOrderLine.split("\\s+");
        	int index = Integer.valueOf(currOrderLineArr[0]);
        	
        	for (int i = 0; i < order.getOrderlines().size(); i++) {
        		
        		if (i+1 == index) {
        			
        			// Finds the OrderLine object to be removed
        			OrderLine target = order.getOrderlines().get(i); 
        			order.remove(target);
        			
        		}
        		
        	}
        	
    		ObservableList<String> orderLinesList = FXCollections.observableArrayList(order.toArrayList());
        	orderTextArea.setItems(orderLinesList);
        	priceTextField.setText(String.format("%.2f", order.price()));
        	
    	}
    	catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!!");
			alert.setHeaderText("Null Pointer Exception");
			alert.setContentText("Please select an order line.");
			alert.showAndWait();
    	}
    	
    }
    

    /**
     * Switches to the OrderScreen scene
     * @param event The semantic event that indicates a user clicked 'Back'
     */
    @FXML
    void returnOrderScreen(ActionEvent event) {
    	
    	try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderScreen.fxml"));
        	Parent root = loader.load();
        	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	
        	
        	OrderScreenController orderScreenController = loader.getController();
        	orderScreenController.setOrderSummaryController(this);
        	
        	Scene newScene = new Scene(root);
        	stage.setScene(newScene);
        	stage.show();
        	
        	
        	
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    
    /**
     * Exports the entire order to a .txt file
     * @param event The semantic event that indicates a user clicked 'Save Order'
     */
    @FXML
    void saveOrder(ActionEvent event) {
    	
    	FileChooser chooser = new FileChooser();
    	chooser.setTitle("Open Target File for the Export");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		
		//get the reference of the target file
		File targeFile = chooser.showSaveDialog(stage);
			
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(targeFile));
			bf.write(order.printOrder());
			bf.flush();
			bf.close();
		}
		catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!!");
			alert.setHeaderText("Null Pointer Exception");
			alert.setContentText("Please select a file.");
			alert.showAndWait();
		}
		catch (IOException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!!");
			alert.setHeaderText("IO Exception");
			alert.setContentText("Unable to save order to file");
			alert.showAndWait();
		}

    }
    


}
