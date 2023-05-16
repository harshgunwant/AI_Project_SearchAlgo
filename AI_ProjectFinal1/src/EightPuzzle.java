import java.util.*;

public class EightPuzzle {
    public static int maxQueueSize; // Variable to track the maximum size of the priority queue
    public static int nodesExpanded; // Variable to track the number of nodes expanded

    // Method to check if the puzzle is solvable
    public static boolean isSolvable(int[][] puzzle) {
        int inversions = 0; // Variable to count the inversions
        List<Integer> puzzleNumbers = new ArrayList<>(); // List to store the numbers of the puzzle

        // Iterate over the puzzle and add the numbers to the list
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                puzzleNumbers.add(puzzle[i][j]);
            }
        }

        // Convert the list to an array
        Integer[] array = new Integer[9];
        puzzleNumbers.toArray(array);

        // Count the inversions
        for (int i = 0; i < 9; ++i) {
            for (int j = i + 1; j < 9; ++j) {
                if (array[i] != 0 && array[j] != 0 && array[i] > array[j]) {
                    inversions++;
                }
            }
        }

        // Return true if the number of inversions is even, false otherwise
        return inversions % 2 == 0;
    }

    // Method to check if the current state of the puzzle is the goal state
    public static boolean isGoal(int[][] puzzle) {
        // If the last position is not 0, return false
        if (puzzle[2][2] != 0) {
            return false;
        }

        int count = 1; // Initialize count with 1
        // Iterate over the puzzle and check if the numbers are in order
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                // If count is less than or equal to 8 and the number in the current position is not equal to count, return false
                if (count <= 8 && puzzle[i][j] != count) {
                    return false;
                }
                count++;
            }
        }

        // If all the numbers are in order, return true
        return true;
    }

    // Method to solve the puzzle
    public static void solve(int[][] initial, int x, int y, boolean useHeuristic, boolean useManhattan) {
        // Create a priority queue and add the initial state to the queue
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(null, initial, x, y, x, y, 0, useHeuristic, useManhattan));

        // Array to represent the four directions (right, down, left, up)
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Set to store the states that have already been explored
        Set<String> explored = new HashSet<>();

        // Variables to store the solution depth, maximum queue size and number of nodes expanded
        int solutionDepth = 0;
        int maxQueueSize = 0;
        int nodesExpanded = 0;

        // Iterate until the queue is empty
        while (!queue.isEmpty()) {
            // Remove the node with the highest priority from the queue
            Node current = queue.poll();

            // If the current state is the goal state, print the solution and the statistics and return
            if (isGoal(current.puzzle)) {
                printSolution(current);
                printStatistics(maxQueueSize, nodesExpanded, solutionDepth);
                return;
            }
            // Convert the current state to a string
            String currState = Arrays.deepToString(current.puzzle);

            // If the current state has not been explored yet, add it to the set of explored states
            if (!explored.contains(currState)) {
                explored.add(currState);

                // For each direction, create a new state by moving the empty space in that direction and add it to the queue
                for (int[] direction : directions) {
                    int newX = current.xCord + direction[0];
                    int newY = current.yCord + direction[1];
                    if (current.isValid(newX, newY)) {
                        Node child = new Node(current, current.puzzle, current.xCord, current.yCord, newX, newY, current.costs + 1, useHeuristic, useManhattan);
                        queue.add(child);
                    }
                }

                // Update the solution depth
                solutionDepth = current.costs;

            }

            // Update the maximum queue size
            maxQueueSize = Math.max(maxQueueSize, queue.size());

            // Increase the number of nodes expanded
            nodesExpanded++;
        }
    }


    // Method to print the statistics
    public static void printStatistics(int maxQueueSize, int nodesExpanded, int solutionDepth) {
        System.out.println("Maximum Queue Size: " + maxQueueSize);
        System.out.println("Nodes Expanded: " + nodesExpanded);
    }

    // Method to print the solution
    public static void printSolution(Node node) {
        // If the node is null, return
        if (node == null) {
            return;
        }

        // Print the parent node first
        printSolution(node.parent);

        // Then print the current node
        printPuzzle(node.puzzle);
        System.out.println();
    }

    // Method to print the state of the puzzle
    public static void printPuzzle(int[][] puzzle) {
        // Iterate over the puzzle and print each number
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }
    }
}

