package GUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class JavaFXTest extends Application {

    private TextArea textArea;
    private Label filePathLabel;
    private File selectedFile;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("UML To Source Code");
        textArea = new TextArea();
        filePathLabel = new Label();
        filePathLabel.setText("Selected File: ");
        filePathLabel.setPadding(new Insets(20, 20, 20, 20));

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        VBox left = new VBox(10);

        // Radio buttons for JAVA and PYTHON in hbox
        HBox radio = new HBox();

        // New togglegroup for the radio buttons so only one can be selected
        ToggleGroup radioButtons = new ToggleGroup();
        RadioButton python = new RadioButton("Python");
        RadioButton java = new RadioButton("Java");
        python.setToggleGroup(radioButtons);
        java.setToggleGroup(radioButtons);

        // Adding radio buttons to the left side of the GUI
        radio.getChildren().addAll(python, java);
        left.setPadding(new Insets(20));
        radio.setAlignment(Pos.BASELINE_LEFT);

        // Buttons for Saving and Running Program
        Button generate = new Button();
        Button save = new Button();
        generate.setText("Load");
        generate.setOnAction(e -> {
            loadFile();
        });
        save.setText("Save");
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

        // boxes to display text files when opened
        StackPane stack = new StackPane();
        stack.setMaxSize(300, 350);
        stack.setAlignment(Pos.BASELINE_RIGHT);
        stack.getChildren().add(textArea);

        // Show the file dialog when a button is clicked
        javafx.scene.control.Button openButton = new javafx.scene.control.Button("Select UML file (.txt)");
        openButton.setOnAction(e -> {
            openFile();
        });

        // adding the component that are on the left side of the GUI
        left.getChildren().addAll(filePathLabel, radio, openButton, buttons);

        // adding components to the main node
        javafx.scene.layout.StackPane root = new javafx.scene.layout.StackPane();
        root.getChildren().addAll(left, stack);
        primaryStage.setScene(new javafx.scene.Scene(root,600,500));
        primaryStage.show();
}
    // When the load button is pressed this method will be
    // called displaying the text file Into the text area object
    private void loadFile() {
        if (selectedFile != null) {
            try {
                List<String> lines = Files.readAllLines(selectedFile.toPath(), StandardCharsets.UTF_8);
                StringBuilder content = new StringBuilder();
                for (String line : lines) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
                filePathLabel.setText(selectedFile.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
                textArea.setText("Error reading the file.");
            }
        }
    }

    // made this method to display the filepath when the select a file button is pressed
    private void openFile() {
        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePathLabel.setText(selectedFile.getAbsolutePath());
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
