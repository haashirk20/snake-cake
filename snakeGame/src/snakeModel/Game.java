package snakeModel;

import musicPlayer.Eating_SFX;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Game {
    //initalizing instance variables

    private Snake snake;
    private Food food;
    private GameBoard board;
    private boolean gameTrue;
    private int score;
    Eating_SFX eatSound = new Eating_SFX("C:\\Users\\Avi\\luh-veggies\\snakeGame\\src\\Y2Mate.is - MUNCH SOUND EFFECT  NO COPYRIGHT-iunt_lNPCP8-128k-1654069699129.wav");


    //constructor
    public Game(){
        gameTrue = true;
        board = new GameBoard();
        snake = new Snake(board.getRows(), board.getCol());
        food = new Food(board.getRows(), board.getCol(), snake);
        score = snake.getFoodEaten();
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
