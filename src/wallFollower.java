/*
 * Saphal Karki, Karan Aryal
 * CS 351L
 * Project 4
 */
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/*\
Method which solves the maze with the wall following algorithm
 */
public class wallFollower {
    ArrayList<ArrayList<Cell>> board;
    public Cell current;
    public  Cell next;
    public Cell start;
    public Cell end;
    AnimationTimer timer;
    public Color color;

    /*
    Constructor where the animation is shown in every solving step
     */
    public wallFollower(Board board, Cell start, Cell end, String position){
        this.board=board.board;
        this.start=start;
        this.end=end;
        current=start;
        //If the position is start
        if(position.equals("Start")){
            this.color=Color.RED;
            if(!start.walls[2]) start.setDirection(2);
            else start.setDirection(1);
        }else{
            //Starts from end
            this.color=Color.BLUE;
            if(!start.walls[3])  start.setDirection(3);
            else start.setDirection(0);
        }
        timer=new AnimationTimer() {
            private long startTime = -1;
            @Override
            public void handle(long now) {
                if ((now - startTime) >=900_000_000) {
                   if(current!=end){
                       solve(board,color);
                   }
                   else {
                       timer.stop();
                   }
                }
            }
        };
        timer.start();
    }

    /*
    Method which executes wall solving algorithm
     */
    public void solve(Board board, Color color){
        int x= current.x_coordinate;
        int y= current.y_coordinate;
        int X= current.x_coordinate*maze.cellSize;
        int Y= current.y_coordinate*maze.cellSize;
        if(current.getDirection()==2){
            //Checks right of the path head & bottom of the cell
            if(!current.walls[1]){
                board.drawPath(X+6,Y+maze.cellSize-7,X+6,
                        Y+maze.cellSize+6,color);
                current.resetDirection();
                next = this.board.get(x).get(y+1);
                next.setDirection(1);
                next.setRightPath(true);
                current = next;
            }
            //Checks forward for the path head and right of the cell
            else if(!current.walls[2]) {
                board.drawPath(X+6,Y+maze.cellSize-7,
                        X+maze.cellSize+2,Y+maze.cellSize-7,color);
                current.resetDirection();
                next = this.board.get(x+1).get(y);
                next.setDirection(2);
                next.setRightPath(true);
                current = next;
            }
            //Checks the wall on the both side and rotates
            else {
                board.drawPath(X+6,Y+maze.cellSize-7,
                        X+maze.cellSize-7,Y+maze.cellSize-7,color);
                current.rotateCounterClockWise();
            }
        }
        else if(current.getDirection()==1){
            //Checks right of the path head and bottom of the cell
            if(!current.walls[0]){
                board.drawPath(X+6,Y+6,X-6,Y+6,color);
                current.resetDirection();
                next = this.board.get(x-1).get(y);
                next.setDirection(0);
                next.setRightPath(true);
                current = next;
            }
            //Checks Forward for the path head && Right of the cell
            else if(!current.walls[1]) {
                board.drawPath(X+6,Y+6,X+6,Y+maze.cellSize+2,color);
                current.resetDirection();
                next = this.board.get(x).get(y+1);
                next.setDirection(1);
                next.setRightPath(true);
                current = next;
            }
            else {
                board.drawPath(X+6,Y+6,X+6,Y+maze.cellSize-7,color);
                current.rotateCounterClockWise();
            }
        }
        else if(current.getDirection()==3){
            //Checks Right of the path head & bottom of the cell
            if(!current.walls[2]){
                board.drawPath(X+maze.cellSize-7,Y+maze.cellSize-7,
                        X+maze.cellSize+3,Y+maze.cellSize-7,color);
                current.resetDirection();
                next = this.board.get(x+1).get(y);
                next.setDirection(2);
                next.setRightPath(true);
                current = next;
            }
            //Checks forward for the path head && Right of the cell
            else if(!current.walls[3]) {
                board.drawPath(X+maze.cellSize-7,Y+maze.cellSize-7
                        ,X+maze.cellSize-7,Y-6,color);
                current.resetDirection();
                next = this.board.get(x).get(y-1);
                next.setDirection(3);
                next.setRightPath(true);
                current = next;
            }
            else {
                board.drawPath(X+maze.cellSize-7,Y+maze.cellSize-7
                        ,X+maze.cellSize-7,Y+6,color);
                current.rotateCounterClockWise();

            }
        }else if(current.getDirection()==0){
            //Checks the right of the path head and bottom of the cell
            if(!current.walls[3]){
                board.drawPath(X+maze.cellSize-7,Y-5,
                        X+maze.cellSize-7,Y+6,color);
                current.resetDirection();
                next = this.board.get(x).get(y-1);
                next.setDirection(3);
                next.setRightPath(true);
                current = next;
            }
            //Checks the forward for the path head and right of the cell
            else if(!current.walls[0]) {
                board.drawPath(X+maze.cellSize-7,Y+6,
                        X-3,Y+6,color);
                current.resetDirection();
                next = this.board.get(x-1).get(y);
                next.setDirection(0);
                next.setPath(true);
                next.setRightPath(true);
                current = next;
            }
            else {
                board.drawPath(X+maze.cellSize-7,Y+6,
                        X+6,Y+6,color);
                current.rotateCounterClockWise();
            }
        }
    }

}
