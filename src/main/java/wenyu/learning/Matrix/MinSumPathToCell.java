package wenyu.learning.Matrix;

/**
 * Created by Wenyu on 12/1/16.
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which
 * minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 */
public class MinSumPathToCell {

    public int minPathSum(int[][] grid) {
        int[][] aux = new int[grid.length][grid[0].length];
        aux[0][0] = grid[0][0];

        for (int i=1; i<grid[0].length; i++) {
            aux[0][i] = aux[0][i-1] + grid[0][i];
        }
        for (int i=1; i<grid.length; i++) {
            aux[i][0] = aux[i-1][0] + grid[i][0];
        }

        for (int i=1; i<grid.length; i++) {
            for (int j=1; j<grid[0].length; j++) {
                aux[i][j] = Math.min(aux[i-1][j], aux[i][j-1]) + grid[i][j];
            }
        }

        return aux[grid.length-1][grid[0].length-1];
    }
}
