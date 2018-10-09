// Isaac Samuel Raj Boddu
// leetcode #51 (n-Queens all solutions)


package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nqueens {

    // Main method
    public static void main(String[] args) {
        // Set the number of queens/board dimensions here
        int queens = 5;

        // Create a solutions List
        List<List<String>> sols = new ArrayList<>();

        // Board holding current state
        char[][] board = generateBoard(queens);

        solve(board, sols, 0, queens);

        sols.forEach(System.out::println);

    }

    public static void printBoard(char[][] b){
        // Utility function to print the current state of the board

        // Print each row from the board to console
        for(char[] st : b){
            System.out.println(st);
        }

    }

    private static char[][] generateBoard(int size){
        // Generates a new size*size board

        char[][] board = new char[size][size];

        // Fill the empty board with '.' representing vacant position
        for(int i = 0; i < size; ++i){
            Arrays.fill(board[i], '.');
        }

        return board;
    }

    private static boolean solve(char[][] b, List<List<String>> sols, int col, int size){
        // Solve for all possible solutions for n-Queens problem

        if (col == b.length){
            // All queens have been placed so,
            // Save the state of the board

            // Format the board as required (In this case as a list of strings)
            List<String> solution = new ArrayList<>();
            for(char[] row : b){
                String rowString = String.copyValueOf(row);
                solution.add(rowString);
            }

            // Add to the sol list
            sols.add(solution);

        }

        // Starting with the first row, try placing a queen at each row and given col number
        for(int row = 0; row < b.length; ++row){

            // Only if the move is valid, place a queen
            if (isSafe(b, row, col, size)){

                // Place the queen at this position and solve the board from the next column
                b[row][col] = 'Q';

                // If failed to solve undo the placement and continue in the current loop (Backtrack!)
                if (!solve(b, sols, col+1, size))
                    b[row][col] = '.';

            }
        }

        return false;

    }

    private static Boolean isSafe(char[][] b, int row, int col, int size){
        // Utility function which returns True if placing a queens at location b[row][col] is valid, False otherwise

        // Check towards the left in the row
        for(int i = 0; i < col; ++i)
            if (b[row][i] == 'Q') return false;

        // Check towards the top in the col
        for(int i = 0; i < row; ++i)
            if (b[i][col] == 'Q') return false;

        // Check towards the diagonal top back left
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j)
            if (b[i][j] == 'Q') return false;

        // Check towards the diagonal bottom back left
        for(int i = row + 1, j = col - 1; i < size && j >= 0; ++i, --j)
            if (b[i][j] == 'Q') return false;

        // All checks have passed
        return true;
    }
}
