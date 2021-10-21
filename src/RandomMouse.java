/*
 * Saphal Karki, Karan Aryal
 * CS 351L
 * Project 4
 */

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Class of maze solver by using random mouse algorithm
 */
public class RandomMouse {
    static Board board;
    int leftIndex = 0;
    int bottomIndex = 1;
    int rightIndex = 2;
    int topIndex = 3;
    AnimationTimer timer;
    static Cell end;
    public static Cell currMouse;
    Cell nextMouse;
    int listSize;
    static ExecutorService threads = Executors.newFixedThreadPool(1000);

    /*
    Constructor which runs the random mouse solver
     */
    public RandomMouse(Board board, Cell start, Cell end, String thread)  {
        this.end=end;
        this.board = board;
        currMouse = start;
        if (thread.equals("Yes")){
            Runnable startThread = new MultipleThreads(start, mouse(start));
            new Thread(startThread).start();
        }
        else animationTimer();
    }

    /*
    Method which runs animation
     */
    public void animationTimer(){
        timer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                long startTime = -1;
                if ((now - startTime) >=100_000_0) {
                    if(currMouse!=end) {
                        maze.paneTem.getChildren().clear();
                        logic();
                        board.drawBoard();
                    }
                }
            }
        };
        timer.start();
    }

    /*
    Method where a mouse checks any open wall on the cell
    and chooses randomly for its next cell
     */
    public int checkOpenWall(Cell cell){
        int wallIndex = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<cell.walls.length; i++){
            if (!cell.walls[i]) {
                list.add(i);
                listSize= list.size();
            }
        }
        if (list.size() > 0) {
            Random random = new Random();
            wallIndex = random.nextInt(list.size());
        }
        return list.get(wallIndex);
    }

    /*
    This method draws the circle for the given cell.
     */
    public static Circle mouse(Cell curr){
        Circle mouse = new Circle();
        mouse.setCenterX(curr.x_coordinate* maze.cellSize+maze.circleSize);
        mouse.setCenterY(curr.y_coordinate* maze.cellSize + maze.circleSize);
        mouse.setRadius(maze.circleSize);
        mouse.setFill(Color.CORAL);
        return mouse;
    }

    /*
    This method is called for random mouse with multiple thread.
    It creates multiple thread for the multiple junction and moves forward.
     */
    public static void solve(Cell curr) {
        int x=curr.x_coordinate;
        int y=curr.y_coordinate;
        if(curr==end){
            Platform.runLater(()->{
                maze.paneTem.getChildren().add(mouse(curr));
            });
            threads.shutdownNow();
        }

        board.board.get(x).get(y).setMouseVisit();

        if(y-1>=0 &&!board.board.get(x).get(y).walls[3] &&
                !board.board.get(x).get(y-1).getMouseVisit() && !threads.isShutdown()){
            Cell tem=board.board.get(x).get(y-1);
            Circle mouse=mouse(tem);
            Runnable thread = new MultipleThreads(tem, mouse);
            threads.execute(thread);
        }
        if( y+1<maze.row &&!board.board.get(x).get(y).walls[1] && !board.board.get(x).get(y+1).getMouseVisit() && !threads.isShutdown()){
            Cell tem=board.board.get(x).get(y+1);
            Circle mouse=mouse(tem);
            Runnable thread = new MultipleThreads(tem, mouse);
            threads.execute(thread);
        }
        if( x+1<maze.row &&!board.board.get(x).get(y).walls[2] && !board.board.get(x+1).get(y).getMouseVisit() && !threads.isShutdown()){
            Cell tem=board.board.get(x+1).get(y);
            Circle mouse=mouse(tem);
            Runnable thread = new MultipleThreads(tem, mouse);
            threads.execute(thread);
        }
        if(x-1>=0 &&!board.board.get(x).get(y).walls[0] && !board.board.get(x-1).get(y).getMouseVisit() && !threads.isShutdown()){
            Cell tem=board.board.get(x-1).get(y);
            Circle mouse=mouse(tem);
            Runnable thread = new MultipleThreads(tem, mouse);
            threads.execute(thread);
        }
    }

    /*
    Method where the mouse goes to another cell randomly making sure that there is an open wall
    present in between these two cells.
     */
    public void logic() {
        int index = checkOpenWall(currMouse);
        if (index == leftIndex) {
            //left wall open
            nextMouse = board.board.get(currMouse.x_coordinate - 1).get(currMouse.y_coordinate);
            nextMouse.setPath(true);
            currMouse = nextMouse;
        } else if (index == bottomIndex) {
            //bottom wall open
            nextMouse = board.board.get(currMouse.x_coordinate).get(currMouse.y_coordinate + 1);
            nextMouse.setPath(true);
            currMouse = nextMouse;
        } else if (index == rightIndex) {
            //right wall open
            nextMouse = board.board.get(currMouse.x_coordinate + 1).get(currMouse.y_coordinate);
            nextMouse.setPath(true);
            currMouse = nextMouse;
        } else if (index == topIndex) {
            //top wall open
            nextMouse = board.board.get(currMouse.x_coordinate).get(currMouse.y_coordinate - 1);
            nextMouse.setPath(true);
            currMouse = nextMouse;
        }
    }
}
