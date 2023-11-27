package GUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.*;
import javafx.scene.layout.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import javafx.stage.FileChooser.ExtensionFilter;

public class JavaFXTest extends Application {

    private TextArea textAreaLeft;
    private TextArea textAreaRight;
    private Label filePathLabel;
    private File selectedFile;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("UML To Source Code");
        textAreaLeft = new TextArea();
        textAreaRight = new TextArea();
        filePathLabel = new Label("Selected File: ");
        filePathLabel.setPadding(new Insets(20));

        // Setting for the textArea and GridPane
        GridPane grid = new GridPane();
        Label input = new Label();
        Label output = new Label();
        input.setText("Input");
        output.setText("Output");
        grid.setMaxSize(800,800);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.add(input, 0, 0);
        grid.add(output, 1, 0);
        grid.add(textAreaLeft,0,1);
        grid.add(textAreaRight,1,1);
        textAreaLeft.setPrefSize(350, 400);
        textAreaRight.setPrefSize(350, 400);

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        grid.add(filePathLabel, 0, 4, 2, 1);

        // Radio buttons for JAVA and PYTHON in hbox
        HBox radio = new HBox();
        radio.setSpacing(50);

        // New togglegroup for the radio buttons so only one can be selected
        ToggleGroup radioButtons = new ToggleGroup();
        RadioButton python = new RadioButton("Python");
        RadioButton java = new RadioButton("Java");
        python.setToggleGroup(radioButtons);
        java.setToggleGroup(radioButtons);

        // Adding radio buttons to the left side of the GUI
        radio.getChildren().addAll(python, java);
        radio.setAlignment(Pos.CENTER);
        grid.add(radio, 0, 3, 2, 1);

        // Buttons for Saving and Running Program
        Button load = new Button("Load");
        Button save = new Button("Save");
        Button generate = new Button("Generate");
        Button openButton = new Button("Select UML file (.txt)");

        // Settings for buttons colors and background
        openButton.setTextFill(Paint.valueOf("White"));
        openButton.setBackground(Background.fill(Paint.valueOf("Purple")));
        generate.setTextFill(Paint.valueOf("White"));
        generate.setBackground(Background.fill(Paint.valueOf("Green")));
        save.setTextFill(Paint.valueOf("White"));
        save.setBackground(Background.fill((Paint.valueOf("Red"))));
        load.setTextFill(Paint.valueOf("White"));
        load.setBackground(Background.fill(Paint.valueOf("Blue")));

        // Show the file dialog when Open button is clicked
        openButton.setOnAction(e -> {
            openFile();
        });

        // display text file in input when load button pressed
        load.setOnAction(e -> {
            loadFile();
        });

        // Set extension filters
        ExtensionFilter extFilter = new ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // horizontal box for the save generate and open file buttons
        HBox buttons = new HBox();
        buttons.getChildren().addAll(openButton, load, generate, save);
        buttons.setSpacing(50);
        grid.add(buttons, 0, 5, 2, 1);

        // adding components to the main node
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        primaryStage.setScene(new javafx.scene.Scene(root,800,800));
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
                textAreaLeft.setText(content.toString());
                filePathLabel.setText("Selected File: " + selectedFile.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
                textAreaLeft.setText("Error reading the file.");
            }
        }
    }

    // made this method to display the filepath when the select a file button is pressed
    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePathLabel.setText("Selected File: " + selectedFile.getAbsolutePath());
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
