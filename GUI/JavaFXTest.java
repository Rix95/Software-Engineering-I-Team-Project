package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class JavaFXTest extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("UML To Source Code");

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        VBox left = new VBox(10);

        // Radio buttons for JAVA and PYTHON in hbox
        HBox radio = new HBox();
        RadioButton python = new RadioButton("Python");
        RadioButton java = new RadioButton("Java");
        radio.getChildren().addAll(python, java);
        Label filePathLabel = new Label("Selected File: ");
        filePathLabel.setPadding(new Insets(20, 20, 20, 20));
        left.setPadding(new Insets(20));

        // Buttons for Saving and Running Program
        Button generate = new Button();
        Button save = new Button();
        generate.setText("Generate Class Diagram");
        save.setText("Save Class Diagram");
        generate.setTextFill(Paint.valueOf("White"));
        generate.setBackground(Background.fill(Paint.valueOf("Blue")));
        save.setTextFill(Paint.valueOf("White"));
        save.setBackground(Background.fill((Paint.valueOf("Red"))));
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.BOTTOM_LEFT);
        buttons.getChildren().addAll(generate, save);

        // Set extension filters
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show the file dialog when a button is clicked
        javafx.scene.control.Button openButton = new javafx.scene.control.Button("Open File Dialog");
        openButton.setOnAction(e -> {
            fileChooser.setTitle("Open File");
            java.io.File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                filePathLabel.setText(selectedFile.getAbsolutePath());
            }
        });
        left.getChildren().addAll(radio,filePathLabel, openButton,buttons);
        javafx.scene.layout.StackPane root = new javafx.scene.layout.StackPane();
        root.getChildren().addAll(left);
        primaryStage.setScene(new javafx.scene.Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
