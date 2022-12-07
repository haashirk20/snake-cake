import leaderBoard.Player;
import snakeModel.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {


    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    String[] imgString = new String[]{"img/cake.png"};

    private GraphicsContext gc;
    int currentDirection = RIGHT;
    Game snakeGame = new Game();
    Snake snake = snakeGame.getSnake();
    GameBoard board = snakeGame.getBoard();
    private final int WIDTH = board.getWidth();
    private final int HEIGHT = board.getHeight();
    private final int ROWS = board.getRows();
    private final int COLUMNS = board.getCol();
    private final int TILE_SIZE = board.getTileSize();
    boolean playGame = true;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Snake");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (currentDirection != LEFT) {
                        currentDirection = RIGHT;
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (currentDirection != RIGHT) {
                        currentDirection = LEFT;
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (currentDirection != DOWN) {
                        currentDirection = UP;
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (currentDirection != UP) {
                        currentDirection = DOWN;
                    }
                }
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void run(GraphicsContext gc) {
        if (!playGame) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", WIDTH / 3.5, HEIGHT / 2);
            return;
        }
        drawBackground(gc);
        drawFood(gc);
        drawSnake(gc);
        drawScore();

        snake.move(currentDirection);


        playGame = snakeGame.gameState();

        snakeGame.eatFood();
    }

    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web(board.getTileColor1()));
                } else {
                    gc.setFill(Color.web(board.getTileColor2()));
                }
                gc.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void drawFood(GraphicsContext gc) {
        gc.drawImage(new Image(imgString[0]), snakeGame.getFood().getX() * TILE_SIZE, snakeGame.getFood() .getY()* TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    private void drawSnake(GraphicsContext gc) {
        gc.setFill(Color.web("4674E9"));
        gc.fillRoundRect(snakeGame.getSnake().getHead().getX() * TILE_SIZE, snakeGame.getSnake().getHead().getY() * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeGame.getSnake().getBody().size(); i++) {
            gc.fillRoundRect(snakeGame.getSnake().getBody().get(i).getX() * TILE_SIZE, snakeGame.getSnake().getBody().get(i).getY() * TILE_SIZE, TILE_SIZE - 1,
                    TILE_SIZE - 1, 20, 20);
        }
    }


    private void drawScore() {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + snakeGame.getScore(), 10, 35);
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}