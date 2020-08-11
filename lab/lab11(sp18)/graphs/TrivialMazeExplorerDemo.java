package graphs;

/**
 *  @author Josh Hug
 */
public class TrivialMazeExplorerDemo {
    public static void main(String[] args) {
        Maze maze = new Maze("/Users/syfrankie/Desktop/syfrankie/20ss/cs61b/lab11(sp18)/graphs/maze.txt");
        TrivialMazeExplorer tme = new TrivialMazeExplorer(maze);
        tme.solve();
    }
}
