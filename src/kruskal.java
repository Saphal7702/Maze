/*
 * Saphal Karki, Karan Aryal
 * CS 351L
 * Project 4
 */
import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Class which generates the maze following kruskal algorithm
 */
public class kruskal {
    private Board board;
    private ArrayList<wall> edges;
    //Structure object of disjoint sets
    private DisjointSetDataStruct struct=new DisjointSetDataStruct();
    ArrayList<Cell> cells=new ArrayList<>();
    private int counter;

    /*
    Constructor that runs the algorithm animation everytime the maze is generated
     */
    public kruskal(Board board){
        this.board=board;
        edges= generateAllEdge();
        int count=0;
        //Setting id and creating structure sets
        for(int i=0;i<board.row;i++){
            for(int j=0;j< board.col;j++){
                board.board.get(i).get(j).setId(count);
                struct.create_set(board.board.get(i).get(j).getId());
                cells.add(board.board.get(i).get(j));
                count++;
            }
        }
        //Shuffles the walls on the list
        Collections.shuffle(edges);
        AnimationTimer timer = new AnimationTimer() {
            private long startTime = -1;
            @Override
            public void handle(long now) {
                if ((now - startTime) >=10_000) {
                    generateMaze();
                    maze.paneTem.getChildren().clear();
                    board.drawBoard();
                    if (edges.size()==counter){
                        //Executes the solver as the given command
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
                                    maze.end,
                                    "Start" );
                        }
                        if(maze.solver.equals("tremaux")){
                            new tremaux(board);
                        }
                        if(maze.solver.equals("mouse_thread")){
                            new RandomMouse(board, maze.start,
                                    maze.end,"Yes");
                        }
                        this.stop();
                    }
                    startTime = now;
                }
            }
        };
        timer.start();
    }

    /*
    Generates the maze following the kruskal's algorithm
     */
    public void generateMaze(){
        if(counter < edges.size()){
            wall current = edges.get(counter);
            if(struct.find_set(current.getLeft().getId()) !=
                    struct.find_set(current.getRight().getId())){
                board.removeWalls(current.getLeft(),current.getRight());
                current.getLeft().isVisited=true;
                current.getRight().isVisited=true;
                struct.union(current.getLeft().getId(),
                        current.getRight().getId());
            }
        }
        counter++;
    }

    /*
    Method that generates all edges inside the board
     */
    public ArrayList<wall> generateAllEdge(){
        ArrayList<wall> walls = new ArrayList<>();
        ArrayList<Cell> temp1 = new ArrayList<>();
        ArrayList<Cell> temp2 = new ArrayList<>();
        for(int i=0;i<board.row;i++){
            for(int j=0;j<board.col;j++){
                temp1.add(board.board.get(i).get(j));
                temp2.add(board.board.get(j).get(i));
            }
        }
        int counter =0;
        for(int i=0;i<board.col;i++){
            List<Cell> part1 = temp1.subList(counter, counter+board.col);
            List<Cell> part2 = temp2.subList(counter, counter+board.col);
            counter+= board.col;
            for(int j=0; j<part1.size();j++){
                if(j+1<part1.size()){
                    part1.get(j).addWallList(part1.get(j+1));
                    part2.get(j).addWallList(part2.get(j+1));
                    wall tempWall = new wall(part1.get(j), part1.get(j+1));
                    wall tempWall1 = new wall(part2.get(j), part2.get(j+1));
                    walls.add(tempWall);
                    walls.add(tempWall1);
                }
            }
        }
        return walls;
    }

    /*
     *Method that creates the wall between two cells.
     */
    public static class wall{
        private final Cell left;
        private final Cell right;
        public wall(Cell left, Cell right){
            this.left=left;
            this.right=right;
        }
        //Method that gets left wall of cell
        public Cell getLeft() {
            return left;
        }
        //Method that gets the right wall of cell
        public Cell getRight(){
            return right;
        }
    }
}