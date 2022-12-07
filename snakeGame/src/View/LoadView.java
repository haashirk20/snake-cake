package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import snakeModel.Game;

import java.io.*;

public class LoadView {

    private SnakeView snakeView;

    private Label selectboardlabel;

    private Button selectboardbutton;

    private ListView<String> boardsList;


    public LoadView(SnakeView snakeView){
        snakeView.playGame = false;

        this.snakeView = snakeView;
        selectboardlabel = new Label(String.format("Current playing: sus board"));
        boardsList = new ListView<>();

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(snakeView.primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20,20,20,20));
        dialogVbox.setStyle("-fx-background-color: #123122;");

        selectboardlabel.setId("CurrentBoard");

        boardsList.setId("BoardsList");
        boardsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        getFiles(boardsList);
        selectboardbutton = new Button("Change Board");
        selectboardbutton.setId("ChangeBoard");

        selectboardbutton.setOnAction(actionEvent -> {
            try {
                selectBoard(selectboardlabel, boardsList);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            snakeView.currentDirection = snakeView.snakeGame.currDirection;
            snakeView.playGame = true;
            dialog.close();

        });

        VBox selectedBoardBox = new VBox(10, selectboardlabel, boardsList, selectboardbutton);

        boardsList.setPrefHeight(100);
        boardsList.setPrefHeight(100);

        selectboardlabel.setStyle("-fx-text-fill: #e8e6e3");
        selectboardlabel.setFont(new Font(16));

        selectboardbutton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        selectboardbutton.setPrefSize(200, 50);
        selectboardbutton.setFont(new Font(16));

        selectedBoardBox.setAlignment(Pos.CENTER);

        dialogVbox.getChildren().add(selectedBoardBox);
        Scene dialogScene = new Scene(dialogVbox, 400, 400);
        dialog.setScene(dialogScene);
        dialog.show();


    }

    private void selectBoard(Label selectboardlabel, ListView<String> boardsList) throws Exception {
        String selectedBoard = boardsList.getSelectionModel().getSelectedItem();

        try {
            snakeView.snakeGame = loadboard("src/boards/"+selectedBoard);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        selectboardlabel.setText(selectedBoard);
        snakeView.currentDirection = snakeView.snakeGame.currDirection;
        snakeView.MakeGui();
    }

    private void getFiles(ListView<String> boardsList) {
        String directoryname = "src/boards/";
        File directory = new File(directoryname);

        String[] fileNames = directory.list();
        ObservableList<String> nameList = FXCollections.observableArrayList();

        if (fileNames != null) {
            for (String name: fileNames){
                if(name.toLowerCase().endsWith(".ser")){
                    nameList.add(name);
                }
            }
        }

        boardsList.setItems(nameList);
    }

    public Game loadboard(String boardFile) throws IOException, ClassNotFoundException {
        FileInputStream file = null;
        ObjectInputStream in = null;

        file = new FileInputStream(boardFile);
        in = new ObjectInputStream(file);
        return (Game) in.readObject();
    }
}