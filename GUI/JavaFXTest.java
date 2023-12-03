package GUI;
import UmlParser.UmlParser;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.io.FileWriter;

public class JavaFXTest extends Application {

    private TextArea inputTextArea;
    private TextArea outputTextArea;
    private Label filePathLabel;
    private File selectedFile;
    private File selectedSaveFile;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("UML To Source Code");
        inputTextArea = new TextArea();
        outputTextArea = new TextArea();
        filePathLabel = new Label();
        filePathLabel.setText("Selected File: ");
        filePathLabel.setPadding(new Insets(20));

        //TODO FOR TESTING PURPOSES
        outputTextArea.setEditable(false);
//
        //TODO END

        // Setting for the textArea and GridPane
        GridPane grid = new GridPane();
        Label input = new Label();
        Label output = new Label();
        input.setText("Input");
        output.setText("Output");
        grid.setMaxSize(800, 800);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.add(input, 0, 0);
        grid.add(output, 1, 0);
        grid.add(inputTextArea, 0, 1);
        grid.add(outputTextArea, 1, 1);
        inputTextArea.setPrefSize(350, 400);
        outputTextArea.setPrefSize(350, 400);

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        grid.add(filePathLabel, 0, 4, 2, 1);

        // Radio buttons for JAVA and PYTHON in hbox
        HBox radio = new HBox();
        radio.setSpacing(50);

        // New togglegroup for the radio buttons so only one can be selected
        ToggleGroup radioButtons = new ToggleGroup();
        RadioButton python = new RadioButton("Python");
        python.setDisable(true);
        RadioButton java = new RadioButton("Java");
        python.setToggleGroup(radioButtons);
        java.setToggleGroup(radioButtons);
        java.setSelected(true);

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

        // All the functions for the buttons are handled here
        openButton.setOnAction(e -> {
            openFile();
        });

        load.setOnAction(e -> {
            loadFile();
        });

        generate.setOnAction(e -> {
            generateCode();
        });

        save.setOnAction(e -> {
            saveFile();
        });


        // Set extension filters
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);


        // horizontal box for the save generate and open file buttons
        HBox buttons = new HBox();
        buttons.getChildren().addAll(openButton, load, generate, save);
        buttons.setSpacing(50);
        grid.add(buttons, 0, 5, 2, 1);

        // adding components to the main node
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        primaryStage.setScene(new javafx.scene.Scene(root, 800, 800));
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
                inputTextArea.setText(content.toString());
                filePathLabel.setText("Selected File: " + selectedFile.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
                inputTextArea.setText("Error reading the file.");
            }
        }
    }

    private void generateCode() {
        UmlParser umlParser = new UmlParser(inputTextArea.getText(), "java");
        outputTextArea.setText(umlParser.parseCodeIntoString());
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

    private void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        selectedSaveFile = fileChooser.showSaveDialog(null);

        if (selectedSaveFile != null) {
            saveToFile(selectedSaveFile, outputTextArea.getText());
            filePathLabel.setText("Saved at: " + selectedSaveFile.getAbsolutePath());
        }
    }

    private void saveToFile(File file, String content) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);

    }
}
