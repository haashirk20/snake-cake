package snakeModel;

import musicPlayer.Eating_SFX;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import musicPlayer.musicPlayer;

public class Game implements Serializable {
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    //initalizing instance variables

    private Snake snake;
    private Food food;
    private GameBoard board;
    private boolean gameTrue;
    private int score;
    Eating_SFX eatSound = new Eating_SFX("/Users/avirajghatora/IdeaProjects/luh-veggies/snakeGame/src/Y2Mate.is - MUNCH SOUND EFFECT  NO COPYRIGHT-iunt_lNPCP8-128k-1654069699129.wav");
    musicPlayer player = new musicPlayer("/Users/avirajghatora/IdeaProjects/luh-veggies/snakeGame/src/Y2Mate.is - Quincas Moreira - Robot City ♫ NO COPYRIGHT 8-bit Music-NAKj3HJX_tM-48k-1654121927214.wav");



    public int currDirection = RIGHT;

    //constructor
    public Game(){
        startGame();
    }

    public void loadGame(Snake snake, GameBoard board, Food food, int score){
        this.snake = snake;
        this.board = board;
        this.food = food;
        this.score = score;
    }

    public void saveGame(File file){
        try{
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void startGame(){
        gameTrue = true;
        board = new GameBoard();
        snake = new Snake(board.getRows(), board.getCol());
        food = new Food(board.getRows(), board.getCol(), snake);
        score = snake.getFoodEaten();
        currDirection = RIGHT;
    }


    //checking if game has ended
    public boolean gameState() {
        Point snakeHead = snake.getHead();
        ArrayList<Point> snakeBody= snake.getBody();

        //if snake hits edge of board
        if (snakeHead.x < 0 || snakeHead.y < 0 || snakeHead.x * board.tileSize >= board.getWidth() || snakeHead.y * board.tileSize >= board.getHeight()) {
            gameTrue = false;

        }

        //if snake hits itself
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.x == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameTrue = false;
                //  player.stopMusic();
                break;
            }
        }
        return gameTrue;
    }

    //checking if snake has eaten food
    public void eatFood() throws InterruptedException {
        Point snakeHead = snake.getHead();
        //if snake on food, eat food and increase body size and score
        if (snakeHead.getX() == food.getX() && snakeHead.getY() == food.getY()) {
            //eatSound.startMusic();

            snake.eatFood();
            food = new Food(board.getRows(), board.getCol(), snake);
            score = snake.getFoodEaten();
            //eatSound.startMusic();
            //eatSound.stopMusic();
        }

    }

    //getters
    public Food getFood() {
        return food;
    }
    public Snake getSnake() {
        return snake;
    }
    public GameBoard getBoard(){
        return board;
    }
    public int getScore(){
        return score;
    }
}
