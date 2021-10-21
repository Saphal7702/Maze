/*
 * Saphal Karki, Karan Aryal
 * CS 351L
 * Project 4
 */

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Class which creates Cell with its features
 */
public class Cell {
    int x_coordinate;
    int y_coordinate;
    boolean isVisited;
    boolean isPath;
    boolean leftPath;
    boolean rightPath;
    boolean firstVisit;
    boolean secondVisit;
    private int id;
    boolean start;
    boolean end;
    private boolean mouseVisit=false;
    public boolean []walls={true,true,true,true};
    //Left,Bottom,Right,Top
    public boolean []dir={false,false,false,false};
    //West,South,East,North

    private List<kruskal.wall> wallList = new ArrayList<>();


    /*
    Constructor for Cell
     */
    public Cell(int x_coordinate, int y_coordinate ){
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate ;
    }

    //marks each cell visited after visiting the cell
    public void setVisited(boolean status){
        this.isVisited=status;}
    //get the visited cell

    public boolean getVisited(){
        return this.isVisited;}

    //sets the path of each cell
    public void setPath(boolean status){
        this.isPath=status;
    }
    public void setLeftPath(boolean status){
        this.leftPath=status;
    }
    //sets the right path of cell
    public void setRightPath(boolean status){
        this.rightPath=status;
    }

    //sets the first visit of the cell
    public void setFirstVisit(boolean visit){
        this.firstVisit=visit;
    }

    //sets the second visit of the cell
    public void setSecondVisit(boolean visit){
        this.secondVisit=visit;}

    //gets first visit
    public boolean getFirstVisit(){return this.firstVisit;}

    //gets the direction of each cell
    public int getDirection(){
        if (this.dir[0]){
            return 0;
        }
        if(this.dir[1]){
            return 1;
        }
        if (this.dir[2]){
            return 2;
        }
        if(this.dir[3]){
            return 3;
        }
        else return -1;
    }

    //sets the direction of each cell
    public void setDirection(int dir){
        if(dir==0){
            this.dir[0]=true;
        }
        if(dir==1){
            this.dir[1]=true;
        }if(dir==2){
            this.dir[2]=true;
        }if(dir==3){
            this.dir[3]=true;
        }
    }
    //Rotating the direction of the wall
    public void rotateCounterClockWise(){
        int currDir=this.getDirection();
        dir[currDir]=false;
        if(currDir<3){
            dir[currDir+1]=true;
        }else {
            dir[0]=true;
        }
    }
    //reseting direction of the cell
    public void resetDirection(){
        this.dir[0]=false;
        this.dir[1]=false;
        this.dir[2]=false;
        this.dir[3]=false;
    }

    //gets the id of each cell
    public int getId(){
        return id;
    }

    //sets the id of each cell
    public void setId(int id){
        this.id = id;
    }

    //Method to add the wall to the list of kruskal wall
    public void addWallList(Cell cell) {
        wallList.add(new kruskal.wall(this, cell));
    }
    //Method to get the wall list in kruskal gen
    public List<kruskal.wall> getWallList() {
        return wallList;
    }

    public void setMouseVisit(){
        this.mouseVisit=true;
    }
    public boolean getMouseVisit(){
        return this.mouseVisit;
    }

    /*
    Method to generate cell shape
    returns cell shape as a rectangle
     */
    public Rectangle cellShape(Cell cell, Color color){
        int x=cell.x_coordinate*maze.cellSize;
        int y=cell.y_coordinate*maze.cellSize;
        Rectangle rect = new Rectangle(x,y,
                maze.cellSize, maze.cellSize);
        rect.setFill(color);
        return rect;
    }

    /*
    Method to draw lines around the cell to create the wall
    and adding on the anchorpane
     */
    public void cellWall(){
        Group group = new Group();
        int x = this.x_coordinate* maze.cellSize;
        int y = this.y_coordinate* maze.cellSize;
        Line line;
        if(walls[0]){
            //LeftWall
            line = new Line(x,y,x,
                    y+maze.cellSize-1);
            line.setStrokeWidth(1);
            group.getChildren().add(line);
        }if(walls[1]){
            //BottomWall
            line = new Line(x,y+ maze.cellSize-1,
                    x+maze.cellSize-1,
                    y+ maze.cellSize-1);
            line.setStrokeWidth(1);
            group.getChildren().add(line);
        }if(walls[2]){
            //RightWall
            line = new Line(x+ maze.cellSize-1,
                    y+maze.cellSize-1,
                    x+ maze.cellSize-1,y);
            line.setStrokeWidth(1);
            group.getChildren().add(line);
        }if(walls[3]){
            //TopWall
            line = new Line(x+maze.cellSize-1,y,x,y);
            line.setStrokeWidth(1);
            group.getChildren().add(line);
        }
        if(!isVisited){
            group.getChildren().add(cellShape(
                    this,Color.MIDNIGHTBLUE));
        }
        if(firstVisit){
            Rectangle rect = new Rectangle(x,y,
                    maze.cellSize, maze.cellSize);
            rect.setFill(Color.PEACHPUFF);
            group.getChildren().add(rect);
        }
        if(secondVisit){
            Rectangle rect = new Rectangle(x,y,
                    maze.cellSize, maze.cellSize);
            rect.setFill(Color.PERU);
            group.getChildren().add(rect);
        }
        if(leftPath&&rightPath){
            Rectangle rect = new Rectangle(x,y,
                    maze.cellSize, maze.cellSize);
            rect.setFill(Color.PERU);
            group.getChildren().add(rect);
        }

        if(start){
            group.getChildren().add(cellShape(this,
                    Color.RED));
        }
        if(end){
            group.getChildren().add(cellShape(this,
                    Color.ROSYBROWN));
        }
        maze.paneTem.getChildren().add(group);
    }

    /*
    To string method of the cell
     */
    @Override
    public String toString() {
        return "Cell{" +
                "x_coordinate=" + x_coordinate +
                ", y_coordinate=" + y_coordinate +
                ", isVisited=" + isVisited +
                ", walls=" + Arrays.toString(walls) +
                '}';
    }
}
