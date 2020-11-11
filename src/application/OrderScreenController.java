package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OrderScreenController {

    @FXML
    private ComboBox<?> sandwichType;

    @FXML
    private TextArea basicIngredients;

    @FXML
    private ListView<?> ingredientSelect;

    @FXML
    private ListView<?> extraIngredientDisplay;

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
    void addIngredient(ActionEvent event) {

    }

    @FXML
    void addOrder(ActionEvent event) {

    }

    @FXML
    void clearSelections(ActionEvent event) {

    }

    @FXML
    void removeIngredient(ActionEvent event) {

    }

    @FXML
    void showOrder(ActionEvent event) {

    }

}
