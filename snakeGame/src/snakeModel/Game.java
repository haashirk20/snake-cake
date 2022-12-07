package snakeModel;

import View.SnakeView;

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
    private int difficultyLevel = 1; //1 = Easy, 2 = Medium, 3 = Hard

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
    public void eatFood() {
        Point snakeHead = snake.getHead();
        //if snake on food, eat food and increase body size and score
        if (snakeHead.getX() == food.getX() && snakeHead.getY() == food.getY()) {
            snake.eatFood();
            food = new Food(board.getRows(), board.getCol(), snake);
            score = snake.getFoodEaten() * this.difficultyLevel;
        }
    }

    public void setDiff(String level) {
        if (level.equals("Medium")) {
            this.difficultyLevel = 2;
        }
        else if (level.equals("Hard")) {
            this.difficultyLevel = 3;
        }
        else {
            this.difficultyLevel = 1;
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
    public int getDifficultyLevel() { return this.difficultyLevel; }
}
