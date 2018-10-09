package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nqueens {
    public static void main(String[] args) {
        // Set the number of queens/board dimensions here
        int queens = 5;

        List<List<String>> sols = new ArrayList<List<String>>();
        char[][] board = generateBoard(queens);

        // printBoard(board);

        solve(board, sols, 0, queens);

        sols.forEach(solution -> System.out.println(solution));

    }

    public static void printBoard(char[][] b){

        for(char[] st : b){
            System.out.println(st);
        }

    }

    public static char[][] generateBoard(int size){
        char[][] board = new char[size][size];

        for(int i = 0; i < size; ++i){
            Arrays.fill(board[i], '.');
        }

        return board;
    }

    public static boolean solve(char[][] b, List<List<String>> sols, int col, int size){
        if (col == b.length){
            // Save the state of the board
            //printBoard(b);

            // Add to the sol list
            List<String> solution = new ArrayList<String>();
            for(char[] row : b){
                String rowString = String.copyValueOf(row);
                solution.add(rowString);
            }

            sols.add(solution);
            //return true;
        }

        for(int row = 0; row < b.length; ++row){
            if (isSafe(b, row, col, size)){
                // Place the queen at this position and solve the board from the next column
                b[row][col] = 'Q';

                // If failed to solve undo the placement and continue in the current loop
                if (!solve(b, sols, col+1, size))
                    b[row][col] = '.';

            }
        }

        return false;

    }

    public static Boolean isSafe(char[][] b, int row, int col, int size){

        // Check to the left in the row
        for(int i = 0; i < col; ++i)
            if (b[row][i] == 'Q') return false;

        // Check to the top in the col
        for(int i = 0; i < row; ++i)
            if (b[i][col] == 'Q') return false;

        // Check to the diagonal top back left
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j)
            if (b[i][j] == 'Q') return false;

        // Check to the diagonal bottom back left
        for(int i = row + 1, j = col - 1; i < size && j >= 0; ++i, --j)
            if (b[i][j] == 'Q') return false;

        return true;
    }
}
