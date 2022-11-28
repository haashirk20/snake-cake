package snakeModel;

import java.awt.*;
import java.util.ArrayList;

public class Game {
    //initalizing instance variables

    private Snake snake;
    private Food food;
    private GameBoard board;
    private boolean gameTrue;
    private int score;

    //constructor
    public Game(){
        gameTrue = true;
        board = new GameBoard();
        snake = new Snake(board.getRows(), board.getCol());
        food = new Food(board.getRows(), board.getCol(), snake);
        score = snake.getFoodEaten();
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
    private void eatFood() {
        Point snakeHead = snake.getHead();
        //if snake on food, eat food and increase body size and score
        if (snakeHead.getX() == food.getX() && snakeHead.getY() == food.getY()) {
            snake.eatFood();
            food = new Food(board.getRows(), board.getCol(), snake);
            score = snake.getFoodEaten();
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
