package snakeModel;

import java.awt.*;
import java.io.Serializable;

import javafx.scene.image.Image;

public class Food implements Serializable {
    //instance variables
    //Image image;
    //String[] imgString = new String[]{"img/cake.png"};
    private int x,y;

    public Food(int rows, int cols, snakeModel.Snake snake){
        start:
        while (true) {
            x = (int) (Math.random() * rows);
            y = (int) (Math.random() * cols);

            for (Point point : snake.getBody()) {
                if (point.getX() == x && point.getY() == y) {
                    continue start;
                }
            }
            //image = new Image(imgString[0]);
            break;
        }
    }

    //setters
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
//    public void setImage(Image img){
//        this.image = img;
//    }

    //getters
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

//    public Image getImage(){
//        return this.image;
//    }
}
