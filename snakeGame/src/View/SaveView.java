package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import snakeModel.Snake;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveView {

    static String saveFileSuccess = "Saved board!!";
    static String saveFileExistsError = "Error: File already exists";
    static String saveFileNotSerError = "Error: File must end with .ser";
    private Label saveFileErrorLabel = new Label("");
    private Label saveBoardLabel = new Label(String.format("Enter name of file to save"));
    private TextField saveFileNameTextField = new TextField("");
    private Button saveBoardButton = new Button("Save board");
    SnakeView snakeView;

    /**
     * Constructor
     *
     * @param snakeView master view
     */
    public SaveView(SnakeView snakeView) {
        this.snakeView = snakeView;

        snakeView.pauseGame = true;
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(snakeView.primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: #121212;");

        saveBoardLabel.setId("SaveBoard"); // DO NOT MODIFY ID
        saveFileErrorLabel.setId("SaveFileErrorLabel");
        saveFileNameTextField.setId("SaveFileNameTextField");
        saveBoardLabel.setStyle("-fx-text-fill: #e8e6e3;");
        saveBoardLabel.setFont(new Font(16));
        saveFileErrorLabel.setStyle("-fx-text-fill: #e8e6e3;");
        saveFileErrorLabel.setFont(new Font(16));
        saveFileNameTextField.setStyle("-fx-text-fill: #e8e6e3;");
        saveFileNameTextField.setFont(new Font(16));

        String boardName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ".ser";
        saveFileNameTextField.setText(boardName);

        saveBoardButton = new Button("Save board");
        saveBoardButton.setId("SaveBoard"); // DO NOT MODIFY ID
        saveBoardButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        saveBoardButton.setPrefSize(200, 50);
        saveBoardButton.setFont(new Font(16));
        saveBoardButton.setOnAction(e -> saveBoard());

        VBox saveBoardBox = new VBox(10, saveBoardLabel, saveFileNameTextField, saveBoardButton, saveFileErrorLabel);

        dialogVbox.getChildren().add(saveBoardBox);
        Scene dialogScene = new Scene(dialogVbox, 400, 400);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    /**
     * Save the board to a file
     */
    public void saveBoard(){
        //getting inputted file name
        String name = saveFileNameTextField.getText().toLowerCase();
        //if name does not end with .ser
        if (!name.endsWith(".ser"))
            saveFileErrorLabel.setText(saveFileNotSerError);
        else {
            //grabbing list of other filenames in directory
            String directoryName = "src/boards/";
            File file = new File(directoryName + name);
            try {

                //attempting to create new file, if false then change label
                if (file.createNewFile()) {
                    snakeView.snakeGame.currDirection = snakeView.currentDirection;
                    snakeView.snakeGame.saveGame(file);
                    saveFileErrorLabel.setText(saveFileSuccess);
                }
                else{
                    saveFileErrorLabel.setText(saveFileExistsError);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
