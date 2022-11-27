package com.example.snakegame;


import java.math.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {


    //Game Final variables
    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH/ROWS;
    private static final String[] food_Image = new String[]{"/img/ic_orange.png"};

    //movement values
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int DOWN = 2;
    private static final int UP = 3;

    private GraphicsContext gc;
    private List<Point> snakeBody = new ArrayList();
    private Point snakeHead;
    private Image foodImage;
    private int foodX;
    private int foodY;
    //private int boolean gameOver;
    //private int currentDirection;


    @Override
    public void start(Stage stage) throws IOException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        stage.setTitle("Snake Cake");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        for (int i = 0; i < 3; i++) {
            snakeBody.add(new Point(5, ROWS/2));
        }
        snakeHead = snakeBody.get(0);
        generateFood();
        run();
    }

    private void run(){
        drawBackground(gc);
        drawFood(gc);
        drawSnake(gc);
    }

    private void drawFood(GraphicsContext gc) {
        gc.drawImage(foodImage, foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if((i+j) % 2 == 0 ){
                    gc.setFill(Color.web("AAD751"));
                }
                else{
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i + SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void generateFood(){
        start:
        while(true){
            foodX = (int)(Math.random() * ROWS);
            foodY = (int)(Math.random() * COLUMNS);

            for (Point point: snakeBody) {
                if(point.getX() == foodX && point.getY() == foodY){
                    continue start;
                }
            }

            foodImage = new Image(food_Image[(int) (Math.random() * food_Image.length)]);
            break;
        }
    }

    private void drawSnake(GraphicsContext gc){
        gc.setFill(Color.web("4674E9"));
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE,SQUARE_SIZE - 1,SQUARE_SIZE - 1, 35, 35);
        for (int i = 1; i <snakeBody.size() ; i++) {
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 20, 20);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}