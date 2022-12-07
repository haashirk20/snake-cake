import View.SnakeView;
import javafx.application.Application;
import javafx.stage.Stage;
import leaderBoard.Player;
import snakeModel.Game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class runApp extends Application {

    Game game;
    SnakeView view;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.game = new Game();
        this.view = new SnakeView(this.game, primaryStage);
    }
}
