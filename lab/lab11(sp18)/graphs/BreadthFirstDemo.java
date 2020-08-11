package graphs;

/**
 *  @author Josh Hug
 */
public class BreadthFirstDemo {
    /* Runs a breadth first search from (1, 1) to (N, N) on the graph in the config file. */
    
    public static void main(String[] args) {
        Maze maze = new Maze("/Users/syfrankie/Desktop/syfrankie/20ss/cs61b/lab11(sp18)/graphs/maze.txt");

        int startX = 1;
        int startY = 1;
        int targetX = maze.N();
        int targetY = maze.N();

        MazeExplorer mbfp = new MazeBreadthFirstPaths(maze, startX, startY, targetX, targetY);
        mbfp.solve();
    }

}
