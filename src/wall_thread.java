/*
 * Saphal Karki, Karan Aryal
 * CS 351L
 * Project 4
 */
import java.util.ArrayList;
/*
Method which runs the wall following algorithm using multiple thread
 */
public class wall_thread {
    ArrayList<ArrayList<Cell>> board;
    public wall_thread(Board board) {
        this.board = board.board;
        Cell start1 = maze.start;
        Cell start2 = maze.end;

        //Calling both the thread and running at the same time.
        new wallFollower(board,start1,start2,"Start");
        new wallFollower(board,start2,start1,"End");
    }
}
