Author: Saphal Karki, Karan Aryal

###### **Project: Maze**

**Class Cell:**
This class creates cell object which are placed on the board. 
Each cell has its x and y coordinate, its visited status, and the path
by which it moves. It contains walls and all the walls are first set to true.
Methods
setVisited: This method marks each cell if the cell is visited or not.
getVisited: This method gets the status of the cell as visited or not.
setPath: This method sets the path of each cell movement.
getPath: This method gets the path of each cell movement.
setRightPath: This method sets the right path of the cell.
setLeftPath: This method sets the left path of the cell.
setFirstVisit: This method marks the first visited cell.
setSecondVisit: This method marks the second visited cell.
getFirstVisit: This method gets the first visited cell.
getSecondVisit: This method gets the second visited cell.
getDirection: This method get the direction of each cell.
setDirection: This method set the direction of each cell.
rotateCounterClockWise: This method rotates each wall clockwise direction.
resetDirection: This method resets the direction of each cell.
getId: This method gets the id of each cell.
setId: This method sets the id of each cell.
addWallList: This method adds the wall to the list of kruskal wall
getWallList: This method gets the wall to the list of kruskal wall
cellShape: This method generates the cell shape and returns the shape as rectangle
cellWall: This method draws lines around the cell to create walls and adds to the 
        anchorpane.
toString: Prints the string values of the cell.


**Class Board:**
In this class the maze board is initialized, each neighbour cell is checked whether it
is a valid place for the next move or not, walls of each cell are removed and formed 
according to the movement of the cell and after the formation of the maze,the board in which
the maze is formed is drawn.
Method:
Constructor Board: Here, the each cell is created on each index of the board, and at first is 
        set not visited, and the path is also set false. 
checkNeighbour : This method takes board and the current cell's x and y coordinate. It checks 
        the four neighbour and does corner check as well for the corner cells. Looks which cell is
        not visited, chooses a random cell and then marks it as visited and sets it next cell value.
        This method returns the possible neighbour cell.
removeWalls : This method takes a current cell and neighbour cell as its parameter. Taking into the 
        account of the direction of the cell, it removes the walls accordingly.
drawPath : This method draws the cell path until the maze is generated.
drawBoard : This method draws a rectangle everytime the cell moves from one box to another, this method
        is used for all the generation and solver algorithm. 

**Class maze:**
This class takes an argument from the command line, and displays the maze generator and solver passed
according to the command. This class also creates random starting point and exit point for the maze. 
Method:
Start : This method gets command line argument from the command prompt and executes the maze on the screen
        Also, this method creates random starting and ending point on the maze.
main : Main method which take argument and launches the program.

**Class dfs:**
This class generates maze following the depth first search algorithm.
Method:
dfs : In this method, we take a board and the ending cell. Random starting cell is chosen and then
        its pushed on the stack. Animation timer shows every step where the current cell is on the screen
        After the completion of the maze, when the stack is empty, then the solution of the maze is chosen
        from the user and depending on the choice, that solver is executed.
generateMaze : The method in which the algorithm is closely followed, and then the program runs according to 
        the algorithm. Here, the current cell is marked visited, then random unvisited neighbour is chosen, then
        walls are removed between two cells until the cell has reached its ending point.

**Class kruskal:**
This class generated maze following randomized kruskal's algorithm. Use of class disjointset data structure is also used.
Method:
kruskal: This method first generates all the edges, and creates the sets of each cell with each cell having their
        respective id's. Shuffles the edges and calls the generateMaze to generate maze. After the completion of 
        maze, the solution is executed according to the command and is shown in animation timer.
generateMaze: Here the logic is implemented to the generation of kruskal algorithm based maze, first list of walls 
        creates, then each set of wall contains only one cell, for each wall in random order if the cell is divided
        by the wall which belong to the distinct cell, current wall is removed, and the sets of a formerly divided cell
        is joined. 
generateAllEdges : This method generates all the edges on the board and stores it on the list of walls.
**Class Wall:**
This is a subclass of Class kruskal, here walls between two cell is created.
Method:
getLeft: Gets the left wall of the cell.
getRight: Gets the right wall of the cell.

**Class DisjointSetDataStruct:**
This class we create a disjoint data structure. As this class was only used for kruskal algorithm, We have copied the 
code from the source and cited the code source on the java file. 

**Class aldous:**
This class generates the maze following aldous-broder algorithm
Method:
aldous: This method gets a random starting cell, add the cell to the list of visited cell and chooses a random neighbour. 
        After visiting every cell, maze is generated, and the solver is passed according to the command prompt.
generateMaze: This method picks a random cell, marks it as visited and checks the neighbour cell, picks the random cell
        removes the wall between current cell and the random neighbour, marks the neighbour as visited and chooses next
        neighbour.
neighbourCellCopy: This method checks the all four neighbour and then returns a random neighbour.

**Class RandomMouse:**
This class solves the generated maze using random mouse without a thread.
Method:
RandomMouse: This method takes board, starting and ending cell, and a string value as its parameters.
        If the string passed is yes, random mouse with a thread is executed. This method starts the thread.
animationTimer: This method runs the animation on every step of the random mouse algorithm
checkOpenWall : This method checks the opening wall of the cell, depending on which side the wall is open,
        the mouse randomly moves to that point.
mouse: This method draws a circle on each cell representing a mouse.
solve: This method solves the random mouse with the thread. Everytime a junction is found, mouse replicates itself
        and then the original mouse dies. After the mouse reaches the end point all the remaining mouse thread 
        are stopped.
logic: This method is used for a mouse without thread as it randomly visits the cell until the end point is reached.

**Class MouseThreads:**
This class implements runnable to execute multiple threads less than 1000 threads in order to solve the generated maze,
using random mouse solving algorithm
Method:
run: This method runs the mouse, delays the time between displaying the mouse and deleting the mouse

**Class wallFollower:**
This class solves the maze using wall following algorithm. In this class the maze is solved without using threads
Method:
wallFollower: This method takes the starting cell for solving the maze, the animation is run until the current cell
        reaches the end cell.
solve: In this method, main logic of wall follower is applied. It follows the current cells checking all the surrounding
        path and rotating the wall if the walls have turns. Eventually stops when the current cell reaches the end.

**Class wall_thread:**
This class is used for solving the wall follower from the start and end point simultaneously.
Method
wall_thread: This method calls the wall follower class one starting from the top and another starting from the bottom.

**tremaux:**
This class is used for solving the generated maze following the tremaux solving algorithm.
Method:
tremaux: Calls the animation timer to show the animation on every step.
solve: In this method, the main solving algorithm is applied. Here when the cell finds another neighbour
        the path is marked, and it follows the path. If the path has been marked twice the cell doesn't enter 
        that path, when a junction is reached it chooses the path which hasn't been marked and follows until 
        the exit point of the maze.
checkNeighbour: Method which checks the neighbour cells and chooses the random next neighbour
        and marks it as current until the end of the cell

HOW TO RUN THE PROGRAM:
The program is executed following the guidelines that was given on the program file. 
"java -jar maze.jar 600 10 dfs wall" This command runs the maze with its window size as 600, with cell size 10 
implementing dfs maze generator with a wall as the maze solving algorithm.

BUGS:
Sometime while running the multi thread program for the wall solving algorithm. Due to the conflict of two animation
times, it shows a weird animation while solving that part. But, if its tried again, it solves correctly. Other than that
there are no bugs on the program.
