package snakeModel;

import java.awt.*;
import java.util.ArrayList;

public class Snake {
    //class variables
    private ArrayList<Point> snakeBody = new ArrayList<Point>();
    private final Point snakeHead;
    private int foodEaten;
    private int size;

    //constructor
    public Snake(int rows, int col){
        //generating body
        for (int i = 0; i < 3; i++) {
            this.snakeBody.add(new Point(col / 2, rows / 2));
        }
        snakeHead = snakeBody.get(0);
        size = snakeBody.size();
        foodEaten = 0;
    }

    //precondition, foodX,foodY == snakeHead.x, snakeHead.y
    public void eatFood(){
        this.snakeBody.add(new Point(-1, -1));
        size += 1;
        foodEaten += 1;
    }

    //movement method
    public void move(int direction){
        //changing direction
        switch (direction) {
            case 0 -> snakeHead.x++;
            case 1 -> snakeHead.x--;
            case 2 -> snakeHead.y++;
            case 3 -> snakeHead.y--;
            default -> {
            }
        }
        //moving body
        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }
    }
    //getters
    public ArrayList<Point> getBody(){
        return snakeBody;
    }

    public int getFoodEaten(){
        return foodEaten;
    }

    public Point getHead(){
        return snakeHead;
    }

    public int getSize(){
        return size;
    }
}
