package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JavaFXTest extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a button

        Button btn = new Button("Click me");

        // Set an action for the button
        btn.setOnAction(e -> System.out.println("Button clicked!"));

        // Create a layout and add the button to it
        javafx.scene.layout.StackPane root = new javafx.scene.layout.StackPane();
        root.getChildren().add(btn);

        // Create the scene
        Scene scene = new Scene(root, 300, 250);

        // Set the stage title and scene
        primaryStage.setTitle("JavaFX Sample");
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
