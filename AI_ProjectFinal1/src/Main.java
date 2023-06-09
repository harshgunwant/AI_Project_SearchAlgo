import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] initial;
        int size;

        // Prompt the user to enter the size of the puzzle
        System.out.println("Enter the size of the puzzle:");
        size = scanner.nextInt();

        // Prompt the user to enter the initial state of the puzzle or choose a default puzzle
        System.out.println("Enter the initial state of the puzzle (or choose a default puzzle):");
        System.out.println("1. Trivial");
        System.out.println("2. Very Easy");
        System.out.println("3. Easy");
        System.out.println("4. Doable");
        System.out.println("5. Oh Boy");
        System.out.println("Enter your choice (1-5), or press 0 to enter your own puzzle:");

        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= 5) {
            initial = getDefaultPuzzle(choice, size);
        } else if (choice == 0) {
            // Prompt the user to enter their own puzzle
            System.out.println("Enter your own puzzle:");
            initial = new int[size][size];
            for (int i = 0; i < size; ++i) {
                System.out.println("Enter numbers for row " + (i + 1) + ":");
                for (int j = 0; j < size; ++j) {
                    initial[i][j] = scanner.nextInt();
                }
            }
        } else {
            System.out.println("Invalid choice. Using the default puzzle.");
            initial = getDefaultPuzzle(1, size);
        }

        int x = -1, y = -1;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (initial[i][j] == 0) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        // Prompt the user to choose an algorithm
        System.out.println("Choose an algorithm:");
        System.out.println("1. Uniform Cost Search");
        System.out.println("2. A* with the Misplaced Tile heuristic");
        System.out.println("3. A* with the Manhattan Distance heuristic");
        choice = scanner.nextInt();

        boolean useHeuristic = false, useManhattan = false;
        switch (choice) {
            case 1:
                break;
            case 2:
                useHeuristic = true;
                break;
            case 3:
                useHeuristic = true;
                useManhattan = true;
                break;
            default:
                System.out.println("Invalid choice. Using Uniform Cost Search.");
                break;
        }

        long startTime = System.currentTimeMillis();

        EightPuzzle puzzle = new EightPuzzle();
        if (EightPuzzle.isSolvable(initial)) {
            puzzle.solve(initial, x, y, useHeuristic, useManhattan);
        } else {
            System.out.println("The given puzzle is not solvable.");
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Execution time: " + executionTime + " milliseconds");
    }

    // Method to get the default puzzle based on the user's choice
    public static int[][] getDefaultPuzzle(int choice, int size) {
        // Initialize a size x size array
        int[][] defaultPuzzle = new int[size][size];
        // Populate it with a default pattern based on the choic
        int count = 1;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                defaultPuzzle[i][j] = count++;
            }
        }
        // Set the last cell as the empty cell
        defaultPuzzle[size - 1][size - 1] = 0;
        return defaultPuzzle;
    }
}

