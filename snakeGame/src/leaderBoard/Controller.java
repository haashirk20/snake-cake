package leaderBoard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TableColumn<Player, String> player;

    @FXML
    private TableColumn<Player, Integer> rank;

    @FXML
    private TableColumn<Player, Integer> score;

    @FXML
    private TableView<Player> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rank.setCellValueFactory(new PropertyValueFactory<Player, Integer>("rank"));
        player.setCellValueFactory(new PropertyValueFactory<Player, String>("player"));
        score.setCellValueFactory(new PropertyValueFactory<Player, Integer>("score"));
    }
}

