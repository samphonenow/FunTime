package Random;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javafx.util.Pair;

public class IslandCount {

    public static void main(String [] args){
        char grid[][] = new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};

        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        Queue<Pair> que = new LinkedList<>();
        int r = grid.length;
        int c = grid[0].length;
        int result = 0;

        Set<Pair> visited = new HashSet<Pair>();

        for(Integer row = 0; row < r; ++row){
            for(Integer col = 0; col < c; ++col){
                Pair<Integer, Integer> current = new Pair<>(row, col);
                if(visited.contains(current) || grid[row][col] == '0'){
                    continue;
                }

                // Increment the number of islands
                ++result;
                visited.add(current);
                que.offer(current);
                BFS(que, grid, visited);
            }
        }

        return result;
    }

    private static void BFS(Queue<Pair> q, char[][]grid, Set<Pair> visited){

        // Push all the immediate reachable locations to queue
        while(q.peek() != null){

            int width = q.size();

            for(int k = 0; k < width; ++k){

                Pair<Integer, Integer> now = q.poll();

                Integer row = now.getKey();
                Integer col = now.getValue();

                Pair<Integer, Integer> below = new Pair<>(row+1, col);
                if(row+1 < grid.length && grid[row+1][col] == '1' && visited.contains(below) == false){
                    q.offer(below);
                    visited.add(below);
                }

                Pair<Integer, Integer> top = new Pair<>(row-1, col);
                if(row-1 >= 0 && grid[row-1][col] == '1' && visited.contains(top) == false){
                    q.offer(top);
                    visited.add(top);
                }

                Pair<Integer, Integer> right = new Pair<>(row, col+1);
                if(col+1 < grid[0].length && grid[row][col+1] == '1' && visited.contains(right) == false){
                    q.offer(right);
                    visited.add(right);
                }

                Pair<Integer, Integer> left = new Pair<>(row, col-1);
                if(col-1 >= 0 && grid[row][col-1] == '1' && visited.contains(left) == false){
                    q.offer(left);
                    visited.add(left);
                }
            }
        }
    }
}
