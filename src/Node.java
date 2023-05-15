//The Node class implements the Comparable interface, allowing nodes to be compared based on their cost values.
public class Node implements Comparable<Node> {
    //Here we will declare some instance vaariables.
    //this represents the parent node of the current node
    Node parent;

    //lets represent the 2D Puzzle
    int[][] puzzle;

    //we also need to define the coordinatess (x and y) for the empty tile in the puzzle.
    // also defining the cost that will be considered in reaching a new node ((Parent-->Node)).
    int xCord, yCord, costs;

    //we need to take boolean to specify wether manhattan distance is being used or not.
    boolean ManhattanUsed;

    // Constructor for creating a new node
    public Node(Node parent, int[][] puzzle, int x, int y, int newX, int newY, int cost, boolean useHeuristic, boolean useManhattan) {
        // Assign parent node
        this.parent = parent;

        // Create a copy of the puzzle
        this.puzzle = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            System.arraycopy(puzzle[i], 0, this.puzzle[i], 0, 3);
        }

        // Swap values of the empty tile and the new tile
        int temp = this.puzzle[x][y];
        this.puzzle[x][y] = this.puzzle[newX][newY];
        this.puzzle[newX][newY] = temp;

        // Assign cost, coordinates, and Manhattan Distance flag
        this.costs = cost;
        this.xCord = newX;
        this.yCord = newY;
        this.ManhattanUsed = ManhattanUsed;

        // Add heuristic to the cost if useHeuristic is true
        if (useHeuristic) {
            this.costs += calculateHeuristic(this.puzzle);
        }
    }

    // Calculate the heuristic value based on the puzzle
    private int calculateHeuristic(int[][] puzzle) {
        if (ManhattanUsed) {
            return calculateManhattanDistance(puzzle);
        } else {
            return calculateMisplacedTiles(puzzle);
        }
    }

    // Calculate the Manhattan Distance heuristic value
    private int calculateManhattanDistance(int[][] puzzle) {
        int manhattanDistance = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (puzzle[i][j] != 0) {
                    int targetX = (puzzle[i][j] - 1) / 3;
                    int targetY = (puzzle[i][j] - 1) % 3;
                    manhattanDistance += Math.abs(i - targetX) + Math.abs(j - targetY);
                }
            }
        }
        return manhattanDistance;
    }

    // Calculate the number of misplaced tiles heuristic value
    private int calculateMisplacedTiles(int[][] puzzle) {
        int misplacedTiles = 0;
        int tile = 1;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (puzzle[i][j] != 0 && puzzle[i][j] != tile) {
                    misplacedTiles++;
                }
                tile++;
            }
        }
        return misplacedTiles;
    }

    @Override
    public int compareTo(Node other) {
        // Compare nodes based on their costs
        return this.costs - other.costs;
    }

    // Check if the given coordinates are valid within the puzzle grid
    public boolean isValid(int x, int y) {
        // Check if x and y are within the bounds of the puzzle grid (3x3)
        return (x >= 0 && x < 3 && y >= 0 && y < 3);
    }
}
