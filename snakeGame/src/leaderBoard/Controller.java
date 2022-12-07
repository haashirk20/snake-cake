package leaderBoard;

import com.sun.tools.javac.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{

    private final String leaderBoardFile = "leaderBoard.txt";
    @FXML
    private TableColumn<Player, String> user;
    @FXML
    private TableColumn<Player, Integer> score;
    @FXML
    private TableView<Player> table;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private ColorPicker colourchoice;

    public void setColourchoice(javafx.event.ActionEvent event) { // set colour to the user choice
        Color myColour = colourchoice.getValue();
        scenePane.setBackground(new Background(new BackgroundFill(myColour, null, null)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // initialize the table view

        table.setItems(getLeaderBoardFIle());
        //set up the columns in the table
        user.setCellValueFactory(new PropertyValueFactory<Player, String>("player"));
        score.setCellValueFactory(new PropertyValueFactory<Player, Integer>("playerScore"));
        //load data
        if(!getLeaderBoardFIle().isEmpty()) table.setItems(getLeaderBoardFIle());
    }

    public ObservableList<Player> getLeaderBoardFIle() { // get the Players written on the file
        ObservableList<Player> objects = FXCollections.observableArrayList();
        try {
            FileInputStream fis = new FileInputStream(leaderBoardFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Player obj = null;

            boolean isExist = true;

            while (isExist) {
                if (fis.available() != 0) {
                    obj = (Player) ois.readObject();
                    objects.add(obj);
                } else {
                    isExist = false;
                }
            }
        }catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return objects;
    }
}

