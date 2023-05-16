//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
//public class PuzzleSolverGUI extends Application {
//    private EightPuzzle puzzleSolver;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Puzzle Solver");
//
//        // Create UI components
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(10));
//        grid.setHgap(10);
//        grid.setVgap(10);
//
//        Label puzzleLabel = new Label("Enter the initial state of the puzzle:");
//        grid.add(puzzleLabel, 0, 0, 2, 1);
//
//        TextArea puzzleInput = new TextArea();
//        puzzleInput.setPrefRowCount(3);
//        grid.add(puzzleInput, 0, 1, 2, 3);
//
//        Label algorithmLabel = new Label("Choose an algorithm:");
//        grid.add(algorithmLabel, 0, 4);
//
//        ComboBox<String> algorithmChoice = new ComboBox<>();
//        algorithmChoice.getItems().addAll("Uniform Cost Search", "A* with Misplaced Tile heuristic", "A* with Manhattan Distance heuristic");
//        algorithmChoice.getSelectionModel().selectFirst();
//        grid.add(algorithmChoice, 1, 4);
//
//        Button solveButton = new Button("Solve");
//        grid.add(solveButton, 0, 5);
//
//        TextArea solutionOutput = new TextArea();
//        grid.add(solutionOutput, 0, 6, 2, 3);
//
//        // Handle solve button click
//        solveButton.setOnAction(e -> {
//            int[][] initialPuzzle = parsePuzzleInput(puzzleInput.getText());
//            String algorithm = algorithmChoice.getSelectionModel().getSelectedItem();
//            solvePuzzle(initialPuzzle, algorithm, solutionOutput);
//        });
//
//        // Create scene and set it to the stage
//        Scene scene = new Scene(grid, 400, 400);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private int[][] parsePuzzleInput(String input) {
//        int[][] puzzle = new int[3][3];
//        String[] rows = input.split("\n");
//        for (int i = 0; i < 3; i++) {
//            String[] numbers = rows[i].split("\\s+");
//            for (int j = 0; j < 3; j++) {
//                puzzle[i][j] = Integer.parseInt(numbers[j]);
//            }
//        }
//        return puzzle;
//    }
//
//    private void solvePuzzle(int[][] initialPuzzle, String algorithm, TextArea solutionOutput) {
//        int x = -1, y = -1;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (initialPuzzle[i][j] == 0) {
//                    x = i;
//                    y = j;
//                    break;
//                }
//            }
//        }
//
//        boolean useHeuristic = false;
//        boolean useManhattan = false;
//        switch (algorithm) {
//            case "Uniform Cost Search":
//                break;
//            case "A* with Misplaced Tile heuristic":
//                useHeuristic = true;
//                break;
//            case "A* with Manhattan Distance heuristic":
//                useHeuristic = true;
//                useManhattan = true;
//                break;
//        }
//
//        puzzleSolver = new EightPuzzle();
//
//        if (EightPuzzle.isSolvable(initialPuzzle)) {
//            String solution = solveAndGetSolution(initialPuzzle, x, y, useHeuristic, useManhattan);
//            solutionOutput.setText(solution);
//        } else {
//            solutionOutput.setText("The given puzzle is not solvable.");
//        }
//        private String solveAndGetSolution ( int[][] initialPuzzle, int x, int y, boolean useHeuristic,
//        boolean useManhattan){
//            StringBuilder solution = new StringBuilder();
//            puzzleSolver.solve(initialPuzzle, x, y, useHeuristic, useManhattan, node -> {
//                solution.append("g(n): ").append(node.costs).append("\n");
//                solution.append("h(n): ").append(node.calculateHeuristic()).append("\n");
//                solution.append("State:\n");
//                solution.append(getPuzzleString(node.puzzle)).append("\n\n");
//            });
//            return solution.toString();
//        }
//
//        private String getPuzzleString(int[][] puzzle){
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    sb.append(puzzle[i][j]).append(" ");
//                }
//                sb.append("\n");
//            }
//            return sb.toString();
//        }
//    }
//}
//
