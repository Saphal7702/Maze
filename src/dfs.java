/*
 * Saphal Karki, Karan Aryal
 * CS 351L
 * Project 4
 */

import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/*
Method which generates Maze using depth first search algorithm
 */
public class dfs {
    public static Cell curr;
    ArrayList<ArrayList<Cell>> board;
    Stack<Cell> stack = new Stack<>();
    Cell next;


    /*
    Constructor which runs animation everytime the maze is generated
     */
    public dfs(Board board,Cell start, Cell end) {
        this.board = board.board;
        //Gets current starting point
        curr = start;
        stack.push(curr);
        AnimationTimer timer = new AnimationTimer() {
            private long startTime = -1;
            @Override
            public void handle(long now) {
                if ((now - startTime) >= 10) {
                    try {
                        //Logic to generate maze
                        generateMaze(board);
                        maze.paneTem.getChildren().clear();
                        //Draws the board
                        board.drawBoard();
                        if (stack.empty()) {
                            //If the user prompt a solving technique, that solver is executed
                            if (maze.solver.equals("mouse")) {
                                maze.randomFlag = true;
                                new RandomMouse(board, start,
                                        end,"No");
                            }
                            if (maze.solver.equals("wall_thread")) {
                                new wall_thread(board);
                            }
                            if (maze.solver.equals("wall")) {
                                new wallFollower(board, maze.start,
                                        maze.end, "Start");
                            }
                            if (maze.solver.equals("tremaux")) {
                                new tremaux(board);
                            }
                            if (maze.solver.equals("mouse_thread")) {
                                //maze.randomFlag = true;
                                new RandomMouse(board, start,
                                        end,"Yes");                            }
                            this.stop();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startTime = now;
                }
            }
        };
        timer.start();
    }

    /*
    Method to generate Maze using depth first search algorithm
     */
    public void generateMaze(Board board) throws InterruptedException {
        curr = stack.pop();
        curr.setVisited(true);
        stack.push(curr);
        next = board.checkNeighbour(board.board,
                curr.x_coordinate, curr.y_coordinate);
        if (next != null) {
            board.removeWalls(curr, next);
            stack.push(next);
        } else stack.pop();
    }
}
