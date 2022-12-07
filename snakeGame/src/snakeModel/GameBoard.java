package snakeModel;

import java.io.Serializable;

public class GameBoard implements Serializable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int COLUMNS = 20;
    public static final int ROWS = 20;

    private String tileColor1,tileColor2;

    int width,height,col,row, tileSize;

    //constructor
    public GameBoard(){
        width = WIDTH;
        height = HEIGHT;
        col = COLUMNS;
        row = ROWS;
        tileSize = width / row;
        tileColor1 = "AAD751";
        tileColor2 = "A2D149";
    }

    //getters
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public int getCol(){
        return this.col;
    }
    public int getRows(){
        return this.row;
    }
    public int getTileSize(){
        return this.tileSize;
    }
    public String getTileColor1(){
        return this.tileColor1;
    }
    public String getTileColor2(){
        return this.tileColor2;
    }

    //setters
    public void setTileColor1(String color){
        this.tileColor1 = color;
    }

    public void setTileColor2(String color){
        this.tileColor2 = color;
    }
}
