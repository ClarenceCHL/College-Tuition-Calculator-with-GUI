package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class sets up the stage for the application and launches the
 * application.
 * 
 * @author Haolun Cheng
 */
public class Main extends Application {
	/**
	 * This method defines the properties of the primary stage.
	 * 
	 * @param primaryStage the stage that needs to be modified and defined
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			primaryStage.setTitle("Program 3 - Tuition Manager");
			primaryStage.setScene(new Scene(root, 700, 500));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method launches the application.
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}