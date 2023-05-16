import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] initial;

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
            initial = getDefaultPuzzle(choice);
        } else if (choice == 0) {
            // Prompt the user to enter their own puzzle
            System.out.println("Enter your own puzzle:");
            initial = new int[3][3];
            for (int i = 0; i < 3; ++i) {
                System.out.println("Enter numbers for row " + (i + 1) + ":");
                for (int j = 0; j < 3; ++j) {
                    initial[i][j] = scanner.nextInt();
                }
            }
        } else {
            System.out.println("Invalid choice. Using the default puzzle.");
            initial = getDefaultPuzzle(1);
        }

        int x = -1, y = -1;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
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

        EightPuzzle puzzle = new EightPuzzle();
        if (EightPuzzle.isSolvable(initial)) {
            puzzle.solve(initial, x, y, useHeuristic, useManhattan);
        } else {
            System.out.println("The given puzzle is not solvable.");
        }
    }

    // Method to get the default puzzle based on the user's choice
    public static int[][] getDefaultPuzzle(int choice) {
        switch (choice) {
            case 1: // Trivial
                return new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            case 2: // Very Easy
                return new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
            case 3: // Easy
                return new int[][]{{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
            case 4: // Doable
                return new int[][]{{0, 1, 2}, {4, 5, 3}, {7, 8, 6}};
            case 5: // Oh Boy
                return new int[][]{{8, 7, 1}, {6, 0, 2}, {5, 4, 3}};
            default:
                System.out.println("Invalid choice. Using the default puzzle (Trivial).");
                return new int[3][3];
        }
    }
}
