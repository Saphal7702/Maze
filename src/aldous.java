/*
 * Saphal Karki, Karan Aryal
 * CS 351L
 * Project 4
 */
import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Class where a maze is generated with following aldous algorithm
 */
public class aldous {
    Board board;
    private Cell currentCell;
    private List<Cell> visitedCells = new ArrayList<>();
    public aldous(Board board){
        this.board=board;
        Random random = new Random();
        //Gets random starting cell
        currentCell=board.board.get(random.nextInt(board.board.size()-1)).
                get(random.nextInt(board.board.size()-1));
        visitedCells.add(currentCell);
        AnimationTimer timer = new AnimationTimer() {
            private long startTime = -1;
            @Override
            public void handle(long now) {
                if ((now - startTime) >=10_000) {
                    generateMaze();
                    maze.paneTem.getChildren().clear();
                    //redraws the board
                    board.drawBoard();
                    if(visitedCells.size()==maze.row*maze.row){
                        //If the user prompt a solving technique, that solver is executed
                        if(maze.solver.equals("mouse")){
                            maze.randomFlag = true;
                            new RandomMouse(board, maze.start,
                                    maze.end,"No");
                        }
                        if(maze.solver.equals("wall_thread")) {
                            new wall_thread(board);
                        }
                        if(maze.solver.equals("wall")) {
                            new wallFollower(board, maze.start,
                                    maze.end, "Start" );
                        }
                        if(maze.solver.equals("tremaux")){
                            new tremaux(board);
                        }
                        if(maze.solver.equals("mouse_thread")){
                            new RandomMouse(board, maze.start,
                                    maze.end,"Yes");                        }
                        this.stop();
                    }
                    startTime = now;
                }
            }
        };
        timer.start();
    }

    /*
    Method to generate Maze using aldous-broder algorithm
    */
    public void generateMaze(){
        Cell neighbour = neighbourCellCopy(currentCell.x_coordinate,
                currentCell.y_coordinate);
        if(!visitedCells.contains(neighbour)){
            board.removeWalls(currentCell, neighbour);
            currentCell.isVisited=true;
            neighbour.isVisited=true;
            visitedCells.add(neighbour);
        }
        currentCell = neighbour;
    }

    /*
    Method which checks random neighbour cells and returns that
    random cell
     */
    public Cell neighbourCellCopy(int x, int y) {
        ArrayList<Cell> arrayList = new ArrayList<>();
        if (x + 1 < board.col) {
            arrayList.add(board.board.get(x+1).get(y));
        }
        if ((x -1) >-1) {
            arrayList.add(board.board.get(x-1).get(y));
        }
        if ((y+1) < board.row) {
            arrayList.add(board.board.get(x).get(y+1));
        }
        if((y-1) >-1) {
            arrayList.add(board.board.get(x).get(y-1));

        }
        if(arrayList.size()==0){
            return null;
        }else {
            Random random = new Random();
            int rand = random.nextInt(arrayList.size());
            return arrayList.get(rand);
        }
    }
}