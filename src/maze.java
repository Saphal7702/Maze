/*
 * Saphal Karki, Karan Aryal
 * CS 351L
 * Project 4
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/*
Main method where all the command and generation is executed
 */
public class maze extends Application {
    public static int cellSize;
    public static int boardHeight;
    public static int boardWidth;
    public static Pane paneTem=new AnchorPane();
    public static int row;
    int col ;
    static String generator;
    static String solver;
    public static boolean randomFlag = false;
    public static int circleSize;
    static String argument1;
    static String argument2;
    static Random random = new Random();
    static int random1;
    static int random2;
    static int random3;
    static Cell start;
    static Cell end;

    /*
    Method which gets command from command prompt and
    executes the maze accordingly on the screen
     */
    @Override
    public void start(Stage primaryStage) {
        boardHeight = Integer.parseInt(argument1);
        boardWidth = Integer.parseInt(argument1);
        cellSize = Integer.parseInt(argument2);
        row = boardHeight/cellSize;
        col = boardWidth/cellSize;
        circleSize = cellSize/2;

        random1=random.nextInt(4);
        random2=random.nextInt(row);
        random3=random.nextInt(row);



        //Setting up the board
        Board board=new Board(row,col,cellSize);

        //Creating random starting and ending points on the maze
        if(random1==0){
            start=board.board.get(random2).get(0);
            end=board.board.get(random3).get(row-1);
            start.start=true;
            end.end=true;

        }
        if(random1==1){
            start=board.board.get(0).get(random2);
            end=board.board.get(col-1).get(random3);
            start.start=true;
            end.end=true;
        }
        if(random1==2){
            start=board.board.get(random2).get(row-1);
            end=board.board.get(random3).get(0);
            start.start=true;
            end.end=true;
        }
        if(random1==3){
            start=board.board.get(col-1).get(random2);
            end=board.board.get(0).get(random3);
            start.start=true;
            end.end=true;
        }

        //Generating the following
        if(generator.equals("dfs")){
            new dfs(board,start,end);
        }
        if(generator.equals("kruskal")){
            new kruskal(board);
        }
        if(generator.equals("aldous")){
            new aldous(board);
        }

        //If its not available on the list, shows error
        if(generator.equals("prim") || generator.equals("rec")
                || solver.equals("pledge") || solver.equals("routing")
        || solver.equals("astar")){
            System.out.println("Sorry, these options are not available");
            System.exit(0);
        }

        Scene scene = new Scene(paneTem, boardWidth,boardHeight);
        primaryStage.setTitle("Maze");
        primaryStage.setScene(scene);
        //Displaying on the screen
        primaryStage.show();
    }

    /*
    Main method which takes argument from the user via command prompt
     */
    public static void main(String[] args){
        argument1 = args[0];
        argument2 = args[1];
        generator = args[2];
        solver = args[3];
        launch(args);
    }
}
