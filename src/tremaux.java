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
Class which solves the maze using tremaux algorithm
 */
public class tremaux {
    ArrayList<ArrayList<Cell>> board;
    Stack<Cell> path=new Stack<>();
    public static Cell current;
    Cell next;
    Cell end;
    int count;

    /*
    Constructor where animation is shown in every solving steps
     */
    public tremaux(Board board){
        this.board=board.board;
        current=maze.start;
        path.push(current);
        end=maze.end;
        AnimationTimer timer = new AnimationTimer() {
            private long startTime = -1;
            @Override
            public void handle(long now) {
                if ((now - startTime) >=1_000_000_00) {
                    if(current!=end){
                        //Method call to solve the given maze
                        solve();
                        maze.paneTem.getChildren().clear();
                        board.drawBoard();
                    }
                    else {
                        this.stop();
                    }
                    startTime = now;
                }
            }
        };
        timer.start();
    }

    /*
    Method which solves the maze following the tremaux algorithm
     */
    public void solve(){
        count++;
        if(!path.empty()) current=path.pop();
        next=checkNeighbour(current);
        current.setFirstVisit(true);
        path.push(current);
        if(next!=null){
            path.push(next);
            current=next;
        }
        else {
            Cell tem=path.pop();
            tem.setSecondVisit(true);
        }
    }

    /*
    Method which checks the neighbour cells and chooses the random next neighbour
    and marks it as current until the end of the cell
     */
    public Cell checkNeighbour(Cell curr){
        ArrayList<Cell> neighbour = new ArrayList<>();
        if (((curr.y_coordinate-1)>=0) && !curr.walls[3]) {
            if(!board.get(curr.x_coordinate).get(curr.y_coordinate - 1).getFirstVisit()) {
                neighbour.add(board.get(curr.x_coordinate).get(curr.y_coordinate - 1));
            }
        }
        if (((curr.x_coordinate+1) < board.size()) && !curr.walls[2]) {
            if(!board.get(curr.x_coordinate + 1).get(curr.y_coordinate).getFirstVisit()){
                neighbour.add(board.get(curr.x_coordinate + 1).get(curr.y_coordinate));
            }
        }
        if (((curr.y_coordinate+1) < board.size()) && !curr.walls[1]) {
            if(!board.get(curr.x_coordinate).get(curr.y_coordinate + 1).firstVisit) {
                neighbour.add(board.get(curr.x_coordinate).get(curr.y_coordinate + 1));
            }
        }
        if (((curr.x_coordinate-1) >=0) && !curr.walls[0]) {
            if(!board.get(curr.x_coordinate - 1).get(curr.y_coordinate).firstVisit) {
                neighbour.add(board.get(curr.x_coordinate - 1).get(curr.y_coordinate));
            }
        }
        if (neighbour.size() > 0){
            Random random  = new Random();
            int randomNeighbour = random.nextInt(neighbour.size());
             return neighbour.get(randomNeighbour);
        }
        else return null;
    }
}
