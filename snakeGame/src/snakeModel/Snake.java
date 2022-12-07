package snakeModel;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Snake implements Serializable {
    //class variables
    private ArrayList<Point> snakeBody = new ArrayList<Point>();
    private final Point snakeHead;
    private int foodEaten;
    private int size;

    String snakeRace = "4674E9";

    //constructor
    public Snake(int rows, int col){
        //generating body
        for (int i = 0; i < 3; i++) {
            this.snakeBody.add(new Point(5, rows / 2));
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
        //moving body
        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }
        //changing direction
        switch (direction) {
            //right
            case 0 -> snakeHead.x++;
            //left
            case 1 -> snakeHead.x--;
            //up
            case 2 -> snakeHead.y--;
            //down
            case 3 -> snakeHead.y++;
            default -> {
            }
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

    public String getSnakeRace(){return snakeRace;}

    //setters
    public void setSnakeRace(String color){
        this.snakeRace = color;
    }
}
