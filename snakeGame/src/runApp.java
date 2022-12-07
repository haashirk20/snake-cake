import View.SnakeView;
import javafx.application.Application;
import javafx.stage.Stage;
import musicPlayer.musicPlayer;
import snakeModel.Game;

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
       // musicPlayer player = new musicPlayer("C:\\Users\\Avi\\luh-veggies\\snakeGame\\src\\AdhesiveWombat - Night Shade.wav");
       // player.startMusic();

    }
}
