import javafx.application.Platform;
import javafx.scene.shape.Circle;
/*
This class creates multiple threads for the random mouse with threads
Each thread calls solve method from random mouse and runs in its own thread.
 */
public class MultipleThreads implements Runnable {
    Cell start;
    Circle mouse;
    MultipleThreads(Cell start, Circle mouse){
        this.start=start;
        this.mouse=mouse;
    }
    @Override
    public void run() {
        Platform.runLater(() -> {
            maze.paneTem.getChildren().add(mouse);
        });
        /*
        This delays the time between displaying the mouse and removing
         */
        try {
            Thread.sleep(100);

        } catch (InterruptedException ex) {
        }
        Platform.runLater(() -> {
            maze.paneTem.getChildren().remove(mouse);
        });
        RandomMouse.solve(start);
    }
}
