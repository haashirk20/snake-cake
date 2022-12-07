package View;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.*;
import leaderBoard.LeaderBoardFile;
import leaderBoard.Player;
import musicPlayer.Eating_SFX;
import musicPlayer.musicPlayer;
import snakeModel.Game;
import snakeModel.GameBoard;
import snakeModel.Snake;

import java.util.Objects;


public class SnakeView {


    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private GraphicsContext gc;
    boolean pauseGame = false;
    int currentDirection = RIGHT;
    Game snakeGame;
    Snake snake;
    GameBoard board;
    String[] imgString = new String[]{"img/cake.png"};
    Player player;
    private int WIDTH;
    private int HEIGHT;
    private int ROWS;
    private int COLUMNS;
    private int TILE_SIZE;

    private int prev_score = 0;

    private int  eatCounter;

    Eating_SFX eatSound = new Eating_SFX("src/music/Y2Mate.is - MUNCH SOUND EFFECT  NO COPYRIGHT-iunt_lNPCP8-128k-1654069699129.wav");
    musicPlayer player1 = new musicPlayer("src/music/Y2Mate.is - Quincas Moreira - Robot City ♫ NO COPYRIGHT 8-bit Music-NAKj3HJX_tM-48k-1654121927214.wav");
    Eating_SFX gameOver = new Eating_SFX("src/music/game over - sound effect.wav");
    musicPlayer mainplayer = new musicPlayer("src/music/AdhesiveWombat - Night Shade.wav");

    boolean colorblindMode = false;

    //color strings
    String tile1, tile2, snakeRace, scoreColor;

    public SnakeView(Game game, Stage stage) throws Exception {
        this.snakeGame = game;
        this.primaryStage = stage;
        makeMenu();
    }
    boolean playGame = true;
    Stage primaryStage;

    public void makeMenu(){

        mainplayer.startMusic();

        primaryStage.setTitle("Snake Cake!");
        primaryStage.getIcons().add(new Image(imgString[0]));

        Text title = new Text();
        title.setText("Snake Cake");
        title.setStyle("-fx-font: 100px Tahoma;\n" +
                "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                "    -fx-stroke: black;\n" +
                "    -fx-stroke-width: 1;");
        //title.setFont(Font.font ("Tahoma", 100));
        title.setFill(Color.BLUE);

        //adding username box
        TextField input = new TextField();
        input.setMaxWidth(200);

        //Adding buttons
        Button startButton = new Button();

        startButton.setText("Start Game!");
        startButton.setPrefSize(180, 60);
        startButton.setOnAction(actionEvent -> {
            try {
                this.playGame = true;
                mainplayer.stopMusic(); // stops music once play game is started
                MakeGui();
                player1.startMusic();
                if(input.getText().isEmpty()){ // if user enters nothing it will be called "DEFAULT"
                    player = new Player("DEFAULT", 0);
                }else{ // initialize player with the username
                    player = new Player(input.getText().toUpperCase(), 0);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Button loadButton = new Button();
        loadButton.setText("Load Game!");
        loadButton.setPrefSize(180 , 60);
        loadButton.setOnAction(actionEvent -> {
            createLoadView();
            this.playGame = true;
            this.pauseGame = true;
        });

        Button ldButton = new Button(); //button for loading leaderboard
        ldButton.setText("Leaderboard");
        ldButton.setPrefSize(180, 60);
        ldButton.setOnAction(actionEvent -> { // load leaderboard
            createLeaderBoard();
        });

        //Beginning of difficulty GUI
        Text difficultyText = new Text();
        difficultyText.setText("Choose Difficulty and Submit:");
        difficultyText.setStyle("-fx-font: 20px Tahoma;\n" +
                "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                "    -fx-stroke: black;\n" +
                "    -fx-stroke-width: 1;");

        difficultyText.setFill(Color.RED);

        Button setDifficulty = new Button();
        setDifficulty.setText("Submit");
        setDifficulty.setPrefSize(180 , 60);

        ChoiceBox<String> choiceList = new ChoiceBox<>();
        choiceList.getItems().add("Easy");
        choiceList.getItems().add("Medium");
        choiceList.getItems().add("Hard");
        choiceList.setValue("Easy");

        setDifficulty.setOnAction(actionEvent -> setDifficulty(choiceList));
        //End of difficulty GUI

        VBox vbox = new VBox(20); // 5 is the spacing between elements in the VBox
        vbox.getChildren().addAll(input, startButton, loadButton, ldButton, difficultyText, choiceList, setDifficulty);
        vbox.setAlignment(Pos.CENTER);

        HBox titleBox = new HBox(20);
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);

        //initalize background image
        Image image = new Image("img/snakeBackground2.png");

        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

        Background background2 = new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize));

        //adding buttons to borderpane
        BorderPane root = new BorderPane();
        //root.getChildren().add(vbox);
        root.setCenter(vbox);
        root.setTop(titleBox);
        root.setBackground(background2);

        this.HEIGHT = snakeGame.getBoard().getHeight();
        this.WIDTH = snakeGame.getBoard().getWidth();
        primaryStage.setScene(new Scene(root, this.HEIGHT, this.WIDTH));
        primaryStage.show();
    }

    private void createLeaderBoard(){ // create leaderBoard GUI
        try {
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("leaderBoard/Controller.fxml")));
            Button close = new Button();
            Scene r = new Scene(root);
            primaryStage.setScene(r);
            primaryStage.show();
            close.setText("Close");
            close.setPrefSize(60, 10);
            root.getChildren().add(close);
            //if they press close then return to main menu
            close.setOnAction(actionEvent -> {
                mainplayer.stopMusic();
                makeMenu();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Method to set the difficulty level chosen by the user.
     */
    private void setDifficulty(ChoiceBox<String> choiceList) {
        String currDifficulty = choiceList.getValue();
        this.snakeGame.setDiff(currDifficulty);
    }
    private void createLoadView() {
        LoadView loadView = new LoadView(this);
    }

    private void createSaveView(){
        SaveView saveView = new SaveView(this);
    }


    public void MakeGui() throws Exception {
        snake = this.snakeGame.getSnake();
        board = this.snakeGame.getBoard();

        this.WIDTH = board.getWidth();
        this.HEIGHT = board.getHeight();
        this.ROWS = board.getRows();
        this.COLUMNS = board.getCol();
        this.TILE_SIZE = board.getTileSize();

        this.tile1 = board.getTileColor1();
        this.tile2 = board.getTileColor2();
        this.snakeRace = snake.getSnakeRace();

        primaryStage.setTitle("Snake Cake");
        Group root = new Group();
        Canvas canvas = new Canvas(this.HEIGHT, this.WIDTH);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.gc = canvas.getGraphicsContext2D();

        Timeline timeline = new Timeline(new KeyFrame( Duration.millis(150 - 20* snakeGame.getDifficultyLevel()), e -> {
            try {
                run(gc);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
                }else if (code == KeyCode.P){
                    if(pauseGame)
                        player1.startMusic();
                    else
                        player1.stopMusic();
                    pauseGame = !pauseGame;
                } else if (code == KeyCode.R && !playGame) {
                    restartGame();
                    makeMenu();
                    timeline.stop();
                } else if (code == KeyCode.M && pauseGame){
                    createSaveView();
                }
            }
        });


    }

    private void restartGame() {
        this.snakeGame.startGame();
        currentDirection = RIGHT;
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

    private void run(GraphicsContext gc) throws InterruptedException {
        drawBackground(gc);
        drawFood(gc);
        drawSnake(gc);
        drawScore();
        if (pauseGame && playGame){
            gc.setFill(Color.BLUE);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Paused", this.WIDTH / 3.5, this.HEIGHT / 2);
            player1.stopMusic();
        } else if (!playGame) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", this.WIDTH / 3.5, this.HEIGHT / 2);

            eatSound.stopMusic();// stops the eat sound
            gameOver.playSound(); // plays game over sound
            player1.stopMusic(); // stops teh background music
            //gameOver.startMusic();
            //Upload user to leaderboard when the game is over
            player.setPlayerScore(this.snakeGame.getScore());
            LeaderBoardFile ld = new LeaderBoardFile(player);
        }else{
            snake.move(currentDirection);

            playGame = snakeGame.gameState();


            if (snakeGame.getScore() != prev_score){
                prev_score = snakeGame.getScore();
                eatCounter = 0;
                //eatSound.startMusic();

            }
            if (eatCounter == 4){
                //eatSound.stopMusic();
                eatCounter = 0;
                 eatSound.startMusic();
            }
            else {
                eatCounter++;
            }

            snakeGame.eatFood();
        }

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
        gc.drawImage(new Image(imgString[0]), snakeGame.getFood().getX() * this.TILE_SIZE, snakeGame.getFood().getY() * this.TILE_SIZE, this.TILE_SIZE, this.TILE_SIZE);
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