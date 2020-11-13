package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * The Main class is the class that launches the sandwich ordering application.
 * @author Joshua Atienza, Kyle Lee
 */
public class Main extends Application {
	
	/**
	 * Places UI controls in a scene and displays scene in stage
	 * @param primaryStage The stage to be generated and displayed in JavaFX
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("OrderScreen.fxml"));
			Scene scene = new Scene(root,630,750);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Sandwich Order System");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launches the application
	 * @param args Arguments passed by command line
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
