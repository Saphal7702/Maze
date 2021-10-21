/*
 * Saphal Karki, Karan Aryal
 * CS 351L
 * Project 4
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.Random;

/*
Class where board is initialized, each cell neighbour is checks,
walls are removed and the board is drawn
 */
public class Board {
    public int cellSize;
    public int row;
    public int col;
    ArrayList<ArrayList<Cell>> board = new ArrayList<>();

    /*
    Generating board and giving characteristics to the cell
     */
    public Board(int row, int col,int cellSize){
        for (int i=0;i<row;i++){
            ArrayList<Cell> rowCell = new ArrayList<>();
            for (int j=0;j<col;j++){
                Cell cell = new Cell(i,j);
                cell.setVisited(false);
                cell.setPath(false);
                cell.setFirstVisit(false);
                cell.setSecondVisit(false);
                rowCell.add(cell);
            }
            board.add(rowCell);
        }
        this.cellSize=cellSize;
        this.col=col;
        this.row=row;
    }

    /*
    Checking each cell's four neighbour and returning its neighbour cells
     */
    public Cell checkNeighbour(ArrayList<ArrayList<Cell>> board,
                               int x, int y){
        ArrayList<Cell> neighbour = new ArrayList<>();
        Cell current = null;
        if (((y-1)>=0) &&
                !board.get(x).get(y - 1).getVisited()) {
            neighbour.add(board.get(x).get(y - 1));
        }
        if (((x+1) < board.size()) &&
                !board.get(x + 1).get(y).getVisited()) {
            neighbour.add(board.get(x + 1).get(y));
        }
        if (((y+1) < board.size()) &&
                !board.get(x).get(y + 1).getVisited()) {
            neighbour.add(board.get(x).get(y + 1));
        }
        if (((x-1) >=0) &&
                !board.get(x - 1).get(y).getVisited()) {
            neighbour.add(board.get(x - 1).get(y));
        }

        //Picking up the random neighbour
        if (neighbour.size() > 0){
            Random random  = new Random();
            int randomNeighbour = random.nextInt(neighbour.size());
            current = neighbour.get(randomNeighbour);
        }
        return current;
    }

    /*
    Removing the walls between the current and neighbour cells
     */
    public void removeWalls(Cell curr,Cell neighbor){
        int x = curr.x_coordinate - neighbor.x_coordinate;
        int y = curr.y_coordinate - neighbor.y_coordinate;
        if(x==1){
            curr.walls[0]=false;
            neighbor.walls[2]=false;
        }
        else if(x==-1){
            curr.walls[2]=false;
            neighbor.walls[0]=false;
        }
        if(y==1){
            curr.walls[3]=false;
            neighbor.walls[1]=false;
        }
        else if(y==-1){
            curr.walls[1]=false;
            neighbor.walls[3]=false;
        }
    }

    /*
    Drawing the solution path that the cell followed inorder
     to reach the exit point
     */
    public void drawPath(int startX,int startY,int endX,
                         int endY,Color color){
        Line line=new Line(startX,startY,endX,endY);
        line.setStroke(color);
        line.setStrokeWidth(4);
        maze.paneTem.getChildren().add(line);
    }

    /*
    Method to draw the board
     */
    public void drawBoard(){
        for (int i=0;i<board.size();i++){
            for (int j=0;j<board.size();j++){
                Cell cell=board.get(i).get(j);
                cell.cellWall();
            }
        }
        //Drawing on the dfs generation
        if(dfs.curr!=null){
            if((dfs.curr.x_coordinate!=0&&
                    dfs.curr.y_coordinate!=0)&&
                    (dfs.curr.x_coordinate!=board.size()-1
                            && dfs.curr.y_coordinate!=board.size()-1)){
                maze.paneTem.getChildren().add
                        (dfs.curr.cellShape(dfs.curr,Color.RED));
            }

        }
        //Drawing on the tremaux solver
        if(tremaux.current!=null){
            if((tremaux.current.x_coordinate!=0&&tremaux.current.y_coordinate!=0)&&
                    (tremaux.current.x_coordinate!=board.size()-1&&
                            tremaux.current.y_coordinate!=board.size()-1)){
                maze.paneTem.getChildren().add
                        (tremaux.current.cellShape(tremaux.current,Color.GREEN));
            }
        }
        //Drawing on the random maze
        if(maze.randomFlag){
            Circle circle = new Circle();
            circle.setCenterX(RandomMouse.currMouse.x_coordinate*
                    maze.cellSize+maze.circleSize);
            circle.setCenterY(RandomMouse.currMouse.y_coordinate*
                    maze.cellSize + maze.circleSize);
            circle.setRadius(maze.circleSize);
            circle.setFill(Color.CORAL);
            maze.paneTem.getChildren().add(circle);
        }

    }
}
