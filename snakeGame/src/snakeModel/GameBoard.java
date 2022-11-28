package snakeModel;

public class GameBoard {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int COLUMNS = 20;
    public static final int ROWS = COLUMNS;

    int width,height,col,row, tileSize;

    //constructor
    public GameBoard(){
        width = WIDTH;
        height = HEIGHT;
        col = COLUMNS;
        row = ROWS;
        tileSize = width / row;
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
}
