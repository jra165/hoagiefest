package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class OrderSummaryController {

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

    Order order = new Order();
    
    
    void setOrderScreenController(OrderScreenController orderScreenController) {
    	order = orderScreenController.order;
    	System.out.println(order.lineNumber);
    	System.out.println(order.printOrder());
    	
    	ObservableList<String> orderLinesList = FXCollections.observableArrayList(order.toArrayList());
    	orderTextArea.setItems(orderLinesList);
    	priceTextField.setText(String.format("%.2f", order.price()));
    }
    
    
    @FXML
    void addSameOrder(ActionEvent event) {
    	
    	try {
    		String currOrderLine = orderTextArea.getSelectionModel().getSelectedItem();
        	
        	// order --> access orderlines --> using index from currOrderLine match it with orderlines index --> create new orderline instance using the fields at that index
        	
        	
        	// line number, sandwich, price
        	String[] currOrderLineArr = currOrderLine.split("\\s+");
        	int index = Integer.valueOf(currOrderLineArr[0]);
        	
        	for (int i = 0; i < order.getOrderlines().size(); i++) {
        		if (i+1 == index) {
        			// looking at specific orderline which has linenumber sandwich and price
        		
        			OrderLine target = order.getOrderlines().get(i);
        			OrderLine sandwich_orderline = new OrderLine(order.getLineNumber(), target.getSandwich(), target.getPrice());
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

    @FXML
    void clearOrder(ActionEvent event) {
    	
    	order.resetLineNumber();
    	order.resetOrderLines();
    	
		ObservableList<String> orderLinesList = FXCollections.observableArrayList(order.toArrayList());
    	orderTextArea.setItems(orderLinesList);
    	
    	//orderTextArea.getItems().clear();
    	priceTextField.clear();

    }

    @FXML
    void removeOrder(ActionEvent event) {
    	
    	
    	try {
    		String currOrderLine = orderTextArea.getSelectionModel().getSelectedItem();
        	String[] currOrderLineArr = currOrderLine.split("\\s+");
        	int index = Integer.valueOf(currOrderLineArr[0]);
        	
        	for (int i = 0; i < order.getOrderlines().size(); i++) {
        		
        		if (i+1 == index) {
        			
        			// Finds the orderline object to be removed
        			OrderLine target = order.getOrderlines().get(i); 
        			
        			
        			order.remove(target);
        			
        		}
        		
        	}
        	
    		ObservableList<String> orderLinesList = FXCollections.observableArrayList(order.toArrayList());
        	orderTextArea.setItems(orderLinesList);
        	
        	double orderPrice = order.price();
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
    

    @FXML
    void returnOrderScreen(ActionEvent event) {
    	
    	try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderScreen.fxml"));
        	Parent root = loader.load();
        	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	
        	Scene newScene = new Scene(root);
        	stage.setScene(newScene);
        	stage.show();
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    
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
    
    /*public void setListView(ArrayList<String> orderLines) {
    	ObservableList<String> orderLinesList = FXCollections.observableArrayList(orderLines);
    	orderTextArea.setItems(orderLinesList);
    }*/


}
