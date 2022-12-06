package View;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import snakeModel.Game;
import snakeModel.GameBoard;
import snakeModel.Snake;

public class SnakeView {


    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private GraphicsContext gc;
    int currentDirection = RIGHT;
    Game snakeGame;
    Snake snake;
    GameBoard board;
    private final int WIDTH;
    private final int HEIGHT;
    private final int ROWS;
    private final int COLUMNS;
    private final int TILE_SIZE;

    boolean colorblindMode = false;

    //color strings
    String tile1, tile2, snakeRace, scoreColor;

    public SnakeView(Game game, Stage stage) throws Exception {
        this.snakeGame = game;
        this.primaryStage = stage;
        snake = snakeGame.getSnake();
        board = snakeGame.getBoard();

        this.WIDTH = board.getWidth();
        this.HEIGHT = board.getHeight();
        this.ROWS = board.getRows();
        this.COLUMNS = board.getCol();
        this.TILE_SIZE = board.getTileSize();

        this.tile1 = board.getTileColor1();
        this.tile2 = board.getTileColor2();
        this.snakeRace = snake.getSnakeRace();
        //this.scoreColor() = "BLACK";
        MakeGui();
    }
    boolean playGame = true;
    Stage primaryStage;



    public void MakeGui() throws Exception {

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
                }else if (code == KeyCode.C){
                    changeCBM();
                }
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void changeCBM() {
        if (colorblindMode){
            board.setTileColor1("AAD751");
            board.setTileColor2("A2D149");
            snake.setSnakeRace("4674E9");
            colorblindMode = false;
        }
        else{
            board.setTileColor1("ECECEC");
            board.setTileColor2("AFA6A6");
            snake.setSnakeRace("000000");
            colorblindMode = true;
        }
    }

    private void run(GraphicsContext gc) {
        if (!playGame) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", this.WIDTH / 3.5, this.HEIGHT / 2);
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
        for (int i = 0; i < this.ROWS; i++) {
            for (int j = 0; j < this.COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web(board.getTileColor1()));
                } else {
                    gc.setFill(Color.web(board.getTileColor2()));
                }
                gc.fillRect(i * this.TILE_SIZE, j * this.TILE_SIZE, this.TILE_SIZE, this.TILE_SIZE);
            }
        }
    }

    private void drawFood(GraphicsContext gc) {
        gc.drawImage(snakeGame.getFood().getImage(), snakeGame.getFood().getX() * this.TILE_SIZE, snakeGame.getFood().getY() * this.TILE_SIZE, this.TILE_SIZE, this.TILE_SIZE);
    }

    private void drawSnake(GraphicsContext gc) {
        gc.setFill(Color.web(snake.getSnakeRace()));
        gc.fillRoundRect(snakeGame.getSnake().getHead().getX() * this.TILE_SIZE, snakeGame.getSnake().getHead().getY() * this.TILE_SIZE, this.TILE_SIZE - 1, this.TILE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeGame.getSnake().getBody().size(); i++) {
            gc.fillRoundRect(snakeGame.getSnake().getBody().get(i).getX() * this.TILE_SIZE, snakeGame.getSnake().getBody().get(i).getY() * this.TILE_SIZE, this.TILE_SIZE - 1,
                    this.TILE_SIZE - 1, 20, 20);
        }
    }


    private void drawScore() {
        if (colorblindMode)
            gc.setFill(Color.BLACK);
        else
            gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + snakeGame.getScore(), 10, 35);
    }
}